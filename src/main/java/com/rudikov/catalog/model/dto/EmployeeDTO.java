package com.rudikov.catalog.model.dto;

import lombok.*;

@Data
@EqualsAndHashCode(of = {"id", "rank", "position", "firstName", "lastName", "phoneNumber"})
@ToString(of = {"id", "rank", "position", "firstName", "lastName"})
public class EmployeeDTO {

    private Long id;
    private String rank;
    private String position;
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String department;

}
