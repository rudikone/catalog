package com.rudikov.catalog.model.dto;

import lombok.*;

@Data
@EqualsAndHashCode(callSuper = true, of = {"rank", "position", "firstName", "lastName", "phoneNumber"})
@ToString(of = {"rank", "position", "firstName", "lastName"})
public class EmployeeDTO extends BaseDTO{

    private String rank;
    private String position;
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String department;

}
