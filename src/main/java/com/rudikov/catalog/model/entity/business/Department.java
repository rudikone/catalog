package com.rudikov.catalog.model.entity.business;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.rudikov.catalog.model.entity.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.*;
import java.util.Set;

@Entity
@Data
@EqualsAndHashCode(callSuper = true, of = {"name"})
@ToString(of = {"name"})
public class Department extends BaseEntity {


    @Column(name = "department_name")
    private String name;

    @JsonManagedReference
    @OneToMany(mappedBy = "department", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    private Set<Employee> employees;

}
