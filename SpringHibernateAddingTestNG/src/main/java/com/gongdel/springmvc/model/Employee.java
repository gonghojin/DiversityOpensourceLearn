package com.gongdel.springmvc.model;



import org.hibernate.annotations.Type;
import org.hibernate.validator.constraints.NotEmpty;
import org.joda.time.LocalDate;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.math.BigDecimal;

@Entity
@Table(name="EMPLOYEE")
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Size(min = 3, max = 50)
    @Column(name = "NAME", nullable = false)
    private String name;

    @NotNull
    @DateTimeFormat(pattern="dd/MM/yyyy")
    @Column(name = "JOINING_DATE", nullable = false)
    @Type(type="org.jadira.usertype.dateandtime.joda.PersistentLocalDate")
    private LocalDate joiningDate;

    @NotNull
    @Digits(integer = 8, fraction = 2)
    @Column(name = "SALARY", nullable = false)
    private BigDecimal salary;

    @NotEmpty
    @Column(name = "SSN", unique = true, nullable = false)
    private String ssn;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getJoiningDate() {
        return joiningDate;
    }

    public void setJoiningDate(LocalDate joiningDate) {
        this.joiningDate = joiningDate;
    }

    public BigDecimal getSalary() {
        return salary;
    }

    public void setSalary(BigDecimal salary) {
        this.salary = salary;
    }

    public String getSsn() {
        return ssn;
    }

    public void setSsn(String ssn) {
        this.ssn = ssn;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + id;
        result = prime * result + ((ssn == null) ? 0 : ssn.hashCode());

        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) { return true; }
        if (obj == null) { return false; }
        if (!(obj instanceof Employee)) { return false; }
        Employee other = (Employee) obj;
        if (id != other.id) { return false; }
        if (ssn == null) {
            if (other.ssn != null) { return  false; }
        } else if (!ssn.equals(other.ssn)) { return false; }

        return true;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", joiningDate=" + joiningDate +
                ", salary=" + salary +
                ", ssn='" + ssn + '\'' +
                '}';
    }
}

/**
    이 클래스는 JPA annotation(@Entity, @Table, @Column)과
    hibernate 특정 annotation(@Type : database date type과 Joda-Time 'LocalDate'를 mapping하기 위해서 사용)이 annotated 되어진 표준 Entity class이다.

    @DateTimeFormat은 주어진 형태의 data time으로 formatted 되어지는 필드를 선언하는 spring의 특정 annotation이다.

    나머지 annotation은 (JSR303)과 관련있는 validation(유효성검사)이다.
    validation의 실패가 있는 경우에, 우리가 만든 properties 파일의 메시지(message.properties)가 호출되어 진다.

 **/
