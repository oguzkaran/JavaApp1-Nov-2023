package com.berkayyilmaz.operation.component;

import com.berkayyilmaz.operation.IIntOperator;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class AddOperation implements IIntOperator {

    @Override
    public boolean isValid(char op)
    {
        log.warn("AddOperation::isValid invoked via {}", op);

        return op == '+';
    }

    @Override
    public int applyAsInt(int a, int b)
    {
        log.warn("AddOperation::applyAsInt invoked via {}, {}", a, b);

        return a + b;
    }
}
