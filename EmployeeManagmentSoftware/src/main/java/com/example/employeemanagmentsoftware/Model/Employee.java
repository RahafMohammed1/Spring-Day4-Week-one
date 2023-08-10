package com.example.employeemanagmentsoftware.Model;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.hibernate.validator.constraints.Range;

@Data
@AllArgsConstructor
public class Employee {
    @NotEmpty(message = "Id field is required")
    @Size(min=3,message = "The Id Should be more than 2 digit ")
    private  String id;
    @NotEmpty(message = "Name field is required")
    @Size(min =5,message = "The name must be more than 4 Character")
    private String  name;
    @NotEmpty(message ="Age field is required")
    private String age;
    @NotEmpty(message ="position field is required")
    @Pattern(regexp="^(supervisor|coordinator|Supervisor|Coordinator)$",message ="The position must be supervisor or coordinator")
    private String position;
    @AssertFalse(message = "The onLeave field must be false")
    private boolean onLeave;
    @NotEmpty(message = "employmentYear field is required")
   // @Digits(integer = 4,fraction =0,message = "The employment Year must be integer number")
    //@Min(value = 1977,message = "invalid Year The Employment Year must be more than 1977")
    //@Max(value = 2023,message = "invalid Year The Employment Year must be less than 2023")
    private String employmentYear;
    @NotEmpty(message = "the annual Leave field is required")
    //@Digits(integer = 4,fraction =0,message = "The annualLeave must be integer number  ")
    private String annualLeave;


}
