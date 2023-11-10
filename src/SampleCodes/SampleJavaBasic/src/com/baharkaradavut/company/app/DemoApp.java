package com.baharkaradavut.company.app;

import com.baharkaradavut.company.factory.EmployeeFactory;
import com.baharkaradavut.company.hr.HumanResources;
import com.baharkaradavut.company.staff.Employee;
import com.baharkaradavut.company.staff.Manager;
import org.csystem.util.thread.ThreadUtil;

import java.util.Random;

public class DemoApp {
    public static void run()
    {
        Random random = new Random();
        EmployeeFactory factory= new EmployeeFactory();
        HumanResources humanResources = new HumanResources();

        while (true) {
            Employee employee = factory.create();

            humanResources.payInsurance(employee);
            ThreadUtil.sleep(random.nextLong(300, 1001));
        }
    }

    public static void main(String[] args)
    {
        run();
    }
}
