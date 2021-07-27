package com.rudikov.catalog.model.entity.business;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.rudikov.catalog.model.entity.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
@Data
@EqualsAndHashCode(callSuper = true, of = {"rank", "position", "firstName", "lastName", "phoneNumber"})
@ToString(of = {"rank", "position", "firstName", "lastName"})
public class Employee extends BaseEntity {

    @Column(name = "rank")
    private String rank;

    @Column(name = "position")
    private String position;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "phone_number")
    private String phoneNumber;

    @JsonBackReference
    @ManyToOne(targetEntity = Department.class)
    @JoinColumn(name = "department_id")
    private Department department;


}
