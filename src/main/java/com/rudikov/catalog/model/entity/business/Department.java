package com.rudikov.catalog.model.entity.business;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Data
@EqualsAndHashCode(of = {"id", "name"})
@ToString(of = {"id", "name"})
public class Department {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @CreationTimestamp
    @Column(name = "persist_date", updatable = false)
    @Type(type = "org.hibernate.type.LocalDateTimeType")
    private LocalDateTime persistDateTime;

    @Column(name = "department_name")
    private String name;

    @JsonManagedReference
    @OneToMany(mappedBy = "department", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    private Set<Employee> employees;

}
