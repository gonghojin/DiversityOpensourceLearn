package com.hojin.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "STUDENT")//선택적, 생략 시 class name = database name
public class Student implements Serializable{
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)//mysql,oracle은  sequence (strategy = GenerationType.SEQUENCE)
    private int id;

	@Column(name = "FIRST_NAME", nullable = false)//이밖에도 unique, nullable, name & length 속성
    private String firstName;
 
    @Column(name = "LAST_NAME", nullable = false)
    private String lastName;
 
    @Column(name = "SECTION", nullable = false)
    private String section;
 
    public int getId() {
        return id;
    }
 
    public void setId(int id) {
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
 
    public String getSection() {
        return section;
    }
 
    public void setSection(String section) {
        this.section = section;
    }
    //하이버네이트는 hashcode and equals method 찾기 때문에, 재정의 해주는게 좋은 습관
	@Override
	public boolean equals(Object obj) {
		if(this == obj) return true;
		if(obj == null) return false;
		if(!(obj instanceof Student)) return false;
		Student other = (Student) obj;
		if(id != other.id) return false;
		return true;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		return result;
	}
 
    
}
