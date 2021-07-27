package com.rudikov.catalog.model.entity.business;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Data
@EqualsAndHashCode(of = {"id", "rank", "position", "firstName", "lastName", "phoneNumber"})
@ToString(of = {"id", "rank", "position", "firstName", "lastName"})
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @CreationTimestamp
    @Column(name = "persist_date", updatable = false)
    @Type(type = "org.hibernate.type.LocalDateTimeType")
    private LocalDateTime persistDateTime;

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
