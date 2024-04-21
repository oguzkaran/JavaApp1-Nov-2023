package org.csystem.app.bank.factory;

import org.csystem.app.bank.Customer;
import org.csystem.app.bank.CustomerPriority;
import org.csystem.util.string.StringUtil;

import java.util.Random;
import java.util.random.RandomGenerator;

public class CustomerFactory {
    private final RandomGenerator m_randomGenerator = new Random();
    private static final CustomerPriority [] CUSTOMER_PRIORITIES = CustomerPriority.values();

    public Customer createCustomer()
    {
        var id = StringUtil.generateRandomText(m_randomGenerator, 11, "0123456789");
        var priority = CUSTOMER_PRIORITIES[m_randomGenerator.nextInt(CUSTOMER_PRIORITIES.length)];
        var name = StringUtil.generateRandomTextEN(m_randomGenerator, m_randomGenerator.nextInt(5, 15));
        
        return new Customer(id, priority, name);
    }
}
