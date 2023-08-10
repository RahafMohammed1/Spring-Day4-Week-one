package com.example.employeemanagmentsoftware.Controllers;

import com.example.employeemanagmentsoftware.ApiRespons.ApiRespons;
import com.example.employeemanagmentsoftware.Model.Employee;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/api/v1/employee")
public class EmployeeController {
    ArrayList<Employee> employees = new ArrayList<>();

    //Get all the Employees
    @GetMapping("/getall")
    public ArrayList<Employee> getAllEmployees() {
        return employees;
    }

    //Add new Employee
    @PostMapping("/add")
    public ResponseEntity addEmployee(@Valid @RequestBody Employee employee, Errors errors) {
        if (errors.hasErrors()) {
            String message = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(message);
        }
        String s=employee.getAge();
        Boolean flag=true;
        for(int a=0;a<s.length();a++)
        {
            if(a==0 && s.charAt(a) == '-')
                continue;
            if( !Character.isDigit(s.charAt(a)))
                flag=false;
        }
        if(flag==false)
             return ResponseEntity.status(400).body("The Age must be integer number");
        int n= Integer.parseInt(employee.getAge());
        if(n<=25)
            return ResponseEntity.status(400).body("The Age must be more than 25");

        s=employee.getEmploymentYear();
        flag=true;
        for(int a=0;a<s.length();a++)
        {
            if(a==0 && s.charAt(a) == '-')
                continue;
            if( !Character.isDigit(s.charAt(a)))
                flag=false;
        }
        if(flag==false)
            return ResponseEntity.status(400).body("EmploymentYear must to be integer");
        n= Integer.parseInt(employee.getEmploymentYear());
        if(n<1977||n>2023)
            return ResponseEntity.status(400).body("EmploymentYear must to be between 1977 to 2023");
        s=employee.getAnnualLeave();
        flag=true;
        for(int a=0;a<s.length();a++)
        {
            if(a==0 && s.charAt(a) == '-')
                continue;
            if( !Character.isDigit(s.charAt(a)))
                flag=false;
        }
        if(flag==false)
            return ResponseEntity.status(400).body("AnnualLeave must to be integer");
        employees.add(employee);
        return ResponseEntity.status(200).body(new ApiRespons("The Employee is added"));
    }

    //Update Employee
    @PutMapping("/update/{index}")
    public ResponseEntity updateEmployee(@PathVariable int index,@Valid @RequestBody Employee employee,Errors errors)
    {
        if (errors.hasErrors()) {
            String message = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(message);
        }
        String s=employee.getAge();
        Boolean flag=true;
        for(int a=0;a<s.length();a++)
        {
            if(a==0 && s.charAt(a) == '-')
                continue;
            if( !Character.isDigit(s.charAt(a)))
                flag=false;
        }
        if(flag==false)
            return ResponseEntity.status(400).body("The Age must be integer number");
        int n= Integer.parseInt(employee.getAge());
        if(n<=25)
            return ResponseEntity.status(400).body("The Age must be more than 25r");

        s=employee.getEmploymentYear();
        flag=true;
        for(int a=0;a<s.length();a++)
        {
            if(a==0 && s.charAt(a) == '-')
                continue;
            if( !Character.isDigit(s.charAt(a)))
                flag=false;
        }
        if(flag==false)
            return ResponseEntity.status(400).body("EmploymentYear must to be integer");
        n= Integer.parseInt(employee.getEmploymentYear());
        if(n<1977||n>2023)
            return ResponseEntity.status(400).body("EmploymentYear must to be between 1977 to 2023");
        s=employee.getAnnualLeave();
        flag=true;
        for(int a=0;a<s.length();a++)
        {
            if(a==0 && s.charAt(a) == '-')
                continue;
            if( !Character.isDigit(s.charAt(a)))
                flag=false;
        }
        if(flag==false)
            return ResponseEntity.status(400).body("AnnualLeave must to be integer");
        employees.set(index,employee);
        return ResponseEntity.status(200).body(new ApiRespons("The Employee is updated"));
    }

    //Delete Employee
    @DeleteMapping("/delete/{index}")
    public ResponseEntity deleteEmployee(@PathVariable int index){
        employees.remove(index);
        return ResponseEntity.status(200).body(new ApiRespons("The Employee is deleted"));
    }
    @PutMapping("/chang/{index}")
    public ResponseEntity annualleave(@PathVariable int index)
    {
       if(employees.get(index).isOnLeave()==true)
       {
           return ResponseEntity.status(400).body(new ApiRespons("The Employee is On Leave already "));
       }
       int n=Integer.parseInt(employees.get(index).getAnnualLeave());
        if(n<=0)
        {
            return ResponseEntity.status(400).body(new ApiRespons(" you can n't apply for leave because you have 0 days "));
        }
        employees.get(index).setAnnualLeave(String.valueOf(n-1));
        employees.get(index).setOnLeave(true);
        return ResponseEntity.status(200).body(new ApiRespons("The process is done"));
    }
}