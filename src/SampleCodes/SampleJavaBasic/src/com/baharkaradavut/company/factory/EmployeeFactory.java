package com.baharkaradavut.company.factory;

import com.baharkaradavut.company.staff.*;

import java.util.Random;

public class EmployeeFactory {
    private final Random m_random = new Random();

    private static Manager getManager()
    {
        Manager manager = new Manager();

        manager.setName("Ali");
        manager.setCitizenId("12345678912");
        manager.setAddress("Mecidiyeköy");
        manager.setDepartment("Pazarlama");
        manager.setSalary(150000);

        return manager;
    }

    private static Worker getWorker()
    {
        Worker worker = new Worker();

        worker.setName("Veli");
        worker.setCitizenId("12345678914");
        worker.setAddress("Fulya");
        worker.setFeePerHour(500);
        worker.setHourPerDay(8);

        return worker;
    }

    private static SalesManager getSalesManager()
    {
        SalesManager manager = new SalesManager();

        manager.setName("Ayşe");
        manager.setCitizenId("12345678918");
        manager.setAddress("Bakırköy");
        manager.setDepartment("Yazılım");
        manager.setSalary(250000);
        manager.setExtra(20000);

        return manager;
    }

    private static ProjectWorker getProjectWorker()
    {
        ProjectWorker worker = new ProjectWorker();

        worker.setName("Fatma");
        worker.setCitizenId("12345678910");
        worker.setAddress("Kadıköy");
        worker.setFeePerHour(500);
        worker.setHourPerDay(8);
        worker.setProjectName("Chat Application");
        worker.setExtraFee(23000);

        return worker;
    }
    public Employee create()
    {
        return switch (m_random.nextInt(4)) {
            case 0 -> getManager();
            case 1 -> getWorker();
            case 2 -> getProjectWorker();
            default -> getSalesManager();
        };
    }
}
