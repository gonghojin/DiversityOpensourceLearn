package com.gongdel.springbootjpa.domain.note;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "notes")
@EntityListeners(AuditingEntityListener.class)
/**
 *  @JsonIgnoreProperties annotation은 Jackson annotation이다.
 *  Spring boot는 Java object를 JSON에 또는 JSON으로부터 Serializing and Deserializing(직렬화 역직렬화)하기 위해서 Jackson을 사용한다.
 *  이 annotation은 rest api의 client가 'createdAt'과 'updateAt' value를 제공하는 것을 원치 않기 때문에 사용한다.
 *  따라서 만약 client가 이 value를 제공한다면 해당 값들을 무시한다. 그러나 이 value들을 JSON response에는 포함한다.('allowGetters = true')
 */
@JsonIgnoreProperties(value = {"createAt", "updatedAt"}, allowGetters = true)
public class Note implements Serializable{

    @Id // pimary key를 정의
    @GeneratedValue(strategy = GenerationType.IDENTITY)// pimary key 발생 방법 표현, 이 경우에는 primary key가 Auto increment가 되도록 선언
    private Long id;

    @NotBlank // annotated filed가 'not null' or 'empty'인지를 유효성 검사
    private String title;

    @NotBlank
    private String content;

    @Column(nullable = false, updatable = false)
    @Temporal(TemporalType.TIMESTAMP) // 'java.util.Date'와 'java.util.Calendar' classes와 함께 사용된다. 이는 date나 time object value를 결합할 수 있는 database type으로 변경한다.
    @CreatedDate
    private Date createAt;

    @Column(nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    @LastModifiedDate
    private Date updateAt;

    public void setTitle(String title) {
        this.title = title;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    public Long getId() {
        return id;
    }

    public Date getCreateAt() {
        return createAt;
    }

    public Date getUpdateAt() {
        return updateAt;
    }
}
/**
 * 기본적으로, 'createAt' name의 field는  database에 'created_at'의 column 명으로 mapped 되어진다.
 * 즉 모든 카멜케이스(camel cases)는 언더스코어(undersore)로 대체된다.
 * 만약 field를 다른 column 명으로 map하고 싶다면, 밑의 코드처럼 사용한다.
 *      @Column(name = "created_on")
 *      private String createdAt;
 *
 */
