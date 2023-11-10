package com.baharkaradavut.company.hr;

import com.baharkaradavut.company.staff.Employee;

public class HumanResources {
    //...
    public void payInsurance(Employee employee)
    {
        System.out.println("-----------------------------------------------");
        System.out.printf("Name:%s, Citizen Id:%s%n", employee.getName(), employee.getCitizenId());
        System.out.printf("Insurance Payment:%f%n", employee.calculateInsurancePayment());
        System.out.println("-----------------------------------------------");
        //...
    }
}
