package com.bekirkocadag.app.operation.calculate;


import com.berkayyilmaz.operation.IIntOperator;
import com.karandev.datetime.configuration.constant.BeanName;
import com.karandev.io.util.console.Console;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Collection;

@Component
public class CalculatorRunner implements ApplicationRunner {
    private final LocalDateTime m_currentDateTime;
    private final DateTimeFormatter m_dateTimeFormatter;
    private final Collection<IIntOperator> m_operations;
    

    public CalculatorRunner(@Qualifier(BeanName.LOCAL_CURRENT_DATETIME_BEAN) LocalDateTime currentDateTime,
                            @Qualifier(BeanName.DATETIME_FORMATTER_TR_BEAN) DateTimeFormatter dateTimeFormatter,
                            Collection<IIntOperator> operations)
    {
        m_currentDateTime = currentDateTime;
        m_dateTimeFormatter = dateTimeFormatter;
        m_operations = operations;
    }

    @Override
    public void run(ApplicationArguments args)
    {
        try {
            var operatorValue = args.getOptionValues("operator");
            var a = Integer.parseInt(args.getOptionValues("left").get(0));
            var b = Integer.parseInt(args.getOptionValues("right").get(0));
            char op = '+';

            if (operatorValue != null && operatorValue.size() == 1)
                op = operatorValue.get(0).charAt(0);

            char operation = op;

            Console.writeLine("Current:%s", m_currentDateTime.format(m_dateTimeFormatter));
            var operations = m_operations.stream().filter(io -> io.isValid(operation)).toList();

            if (!operations.isEmpty())
                operations.forEach(o -> Console.writeLine("%d %c %d = %d", a, operation, b, o.applyAsInt(a, b)));
            else
                Console.writeLine("Unsupported operation!...");
        }
        catch (NumberFormatException ignore) {
            Console.Error.writeLine("Invalid values!...");
        }

        catch (Throwable ex) {
            Console.Error.writeLine("Error occurred:%s", ex.getMessage());
        }
    }
}
