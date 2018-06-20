package com.gongdel.springsecurity.model;

import javax.persistence.*;
import java.util.Arrays;

@Entity
@Table(name = "USER_DOCUMENT")
public class UserDocument {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "name", length = 100, nullable = false)
    private String name;

    @Column(name = "description", length = 255)
    private String description;

    @Column(name = "type", length = 100, nullable = false)
    private String type;

    /**
     * file의 내용을 저장하기 위해서 byte[]를 사용.
     *
     * @Lob : 이 persistent property가 large object type을 지원하는 database에[우리의 경우 'longblob'] large object로서 지속되야 함을 나타냄
     * @Basic annotation은 optional annotation으로, 여기서는 hibernate에게  binary content를 lazy load를 명하기 위한 placeholder이다.
     */
    @Lob
    @Basic(fetch = FetchType.LAZY)
    @Column(name = "content", nullable = false)
    private byte[] content;

    @ManyToOne(optional = false)
    @JoinColumn(name = "USER_ID")
    private User user;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public byte[] getContent() {
        return content;
    }

    public void setContent(byte[] content) {
        this.content = content;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (!(obj instanceof UserDocument))
            return false;
        UserDocument other = (UserDocument) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        if (name == null) {
            if (other.name != null)
                return false;
        } else if (!name.equals(other.name))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "UserDocument{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", content=" + Arrays.toString(content) +
                ", user=" + user +
                '}';
    }
}
