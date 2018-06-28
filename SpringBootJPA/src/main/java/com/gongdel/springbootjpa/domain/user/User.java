package com.gongdel.springbootjpa.domain.user;


import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

/**
 * application이 users에 대한 많은 정보를(개인정보) 저장한다고 가정해보자.
 * 이러한 경우에, users는 name, email, password와 같은 주 세부사항을 'USERS' table에 저장하고, 보조 세부사항을 분할된 'USER_PRIFLES' 저장한다.
 * 그리고 'USER'와 'USER_PROFILE' table 사이의 관계는 'ono-to-one'이다.
 */
@Entity
@Table(name = "users")
public class User implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Size(max = 65)
    @Column(name = "first_name")
    private String firstName;

    @Size(max = 65)
    @Column(name = "last_name")
    private String lastName;

    @NotNull
    @Email
    @Size(max = 100)
    @Column(unique = true)
    private String email;

    @NotNull
    @Size(max = 128)
    private String password;

    @OneToOne(fetch = FetchType.LAZY,
            cascade = CascadeType.ALL,
            mappedBy = "user"
    )
    private UserProfile userProfile;

    // Hibernate는 non-parameter 생성자가 요구됨
    public User() {
    }

    public User(@NotNull @Size(max = 65) String firstName, @Size(max = 65) String lastName, @NotNull @Email @Size(max = 100) String email, @NotNull @Size(max = 128) String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public UserProfile getUserProfile() {
        return userProfile;
    }

    public void setUserProfile(UserProfile userProfile) {
        this.userProfile = userProfile;
    }
}
/**
 * one-to-one 관계는 JPA의 '@OneToOne' annotation을 사용하여 정의됐다. 이는 몇 가지의 속성을 허용한다.
 * + casecade = CasecadeType.ALL : 모든 casecade 효과를 관련 entity에 적용한다. 'User' entity가 update/delete될 때마다, 일치하는 UserProfile 또한 update/delete한다.
 * + mappedBy = "user" : 'UserProfile' entity에 'User' entity에 mappedby 속성을 사용한다.
 * 이는 hibernate에게 이 관계에서 'User' entity는 책임이 없다고 알려주고,
 * JoinColumn/ForeignKey column을 위한 설정을 찾기 위해서'UserProfile' entity에 있는 'user'라는 field를 찾는다.
 * <p>
 * 양방향 관계에서는, 두 entity에 '@OneToOne' annotation을 명시하지만, 하나의 entity는 관계의 소유자여야 한다.
 * 대게, child entity가 관계의 소유자가 되며 parent entity는 관계의 반대 측이 된다.
 * 관계의 소유자는 외래키 컬럼을 명시하기 위해서 '@JoinColumn' annotation을 포함한다.
 * 그리고 관계의 반대측은 관계가 다른 entity에 의해서 mapped되어지는 것을 나타내기 위해 'mappedBy' 속성을 포함한다.
 **/
