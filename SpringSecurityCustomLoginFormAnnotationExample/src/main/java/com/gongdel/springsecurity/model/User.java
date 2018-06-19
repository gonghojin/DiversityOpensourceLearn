package com.gongdel.springsecurity.model;


import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "APP_USER")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotEmpty
    @Column(name = "SSO_ID", unique = true, nullable = false)
    private String ssoId;

    @NotEmpty
    @Column(name = "PASSWORD", nullable = false)
    private String password;

    @NotEmpty
    @Column(name = "FIRST_NAME", nullable = false)
    private String firstName;

    @NotEmpty
    @Column(name = "LAST_NAME", nullable = false)
    private String lastName;

    @NotEmpty
    @Column(name = "EMAIL", nullable = false)
    private String email;

    @NotEmpty
    @Column(name = "STATE", nullable = false)
    private String state = State.ACTIVE.getState();

    /**
     * 즉시 로딩 : Entity를 조회할 때 연관된 Entity도 함께 조회한다. fetch = FetchType.EAGER
     * 자연 로딩 : Entity를 조회할 때 연관된 Entity도 실제 사용할 때 조회한다. fetch = FetchType.lAZY
     */
    /**
     * 'fetch = FetchType.LAZY'에 집중해보자. 우리는 hibernate에게 userProfile collection을 lazy load하라고 설정했다.
     * 이는 기본 동작이다. 설정과 함께, collection을 load하기 위한 query가 처음 접근했을 때 발동된다.
     * 모든 연결된 object graph를 인출하는 것을 피하기 위한[과소비이기 떄문] 좋은 방법이다.
     * transaction/ active session에 있을 때 collection에 접근하려하면, hibernate 그들을 인출하기 위한 분리된 select를 발동한다.
     * <p>
     * 그러나 만약 active session 밖에 있을 때(session이 닫혔거나 transaction이 아니거나: 예 jsp에 있는 경우) collection에 접근을 시도하면
     * org.hibernate.LazyInitializationException – could not initialize proxy – no Session 에러를 발생
     * 이러한 문제를 피하기 위해서, Hibernate.initialize(user.getUserProfiles());를 호출함으로써 요구하는 collection을 초기화해야한다.
     * active session 안에서[DAO method에서 view로 돌아가기 전에] method 안에 이 initialize를 호출해야 한다.
     */
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "APP_USER_USER_PROFILE",
            joinColumns = {@JoinColumn(name = "USER_ID")},
            inverseJoinColumns = {@JoinColumn(name = "USER_PROFILE_ID")}
    )
    private Set<UserProfile> userProfiles = new HashSet<UserProfile>();

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSsoId() {
        return ssoId;
    }

    public void setSsoId(String ssoId) {
        this.ssoId = ssoId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public Set<UserProfile> getUserProfiles() {
        return userProfiles;
    }

    public void setUserProfiles(Set<UserProfile> userProfiles) {
        this.userProfiles = userProfiles;
    }

    /**
     * *Many* association(연관관계) 경우에, 항상 hashcode와 equals method를 override 해야한다. 이유: hibernate가 collection에서 entity를 가져올 때 항상 호출
     */

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + id;
        result = prime * result + ((ssoId == null) ? 0 : ssoId.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (!(obj instanceof User))
            return false;
        User other = (User) obj;
        if (id != other.id)
            return false;
        if (ssoId == null) {
            if (other.ssoId != null)
                return false;
        } else if (!ssoId.equals(other.ssoId))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", ssoId='" + ssoId + '\'' +
                ", password='" + password + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", state='" + state + '\'' +
                ", userProfiles=" + userProfiles +
                '}';
    }
}

