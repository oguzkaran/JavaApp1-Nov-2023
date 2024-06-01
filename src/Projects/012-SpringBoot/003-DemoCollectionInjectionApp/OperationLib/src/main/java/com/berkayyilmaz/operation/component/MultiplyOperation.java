package com.berkayyilmaz.operation.component;

import com.berkayyilmaz.operation.IIntOperator;
import org.springframework.stereotype.Component;

@Component
public class MultiplyOperation implements IIntOperator {
    @Override
    public boolean isValid(char op)
    {
        return op == '*';
    }

    @Override
    public int applyAsInt(int a, int b)
    {
        return a * b;
    }
}
