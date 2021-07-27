package com.rudikov.catalog.model.entity;

import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.time.LocalDateTime;

@MappedSuperclass
@Data
public class BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    protected Long id;

    @CreatedDate
    @Column(name = "created")
    protected LocalDateTime created;

    @LastModifiedDate
    @Column(name = "updated")
    protected LocalDateTime updated;
}
