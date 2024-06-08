package com.berkayyilmaz.operation;

import java.util.function.IntBinaryOperator;

public interface IIntOperator extends IntBinaryOperator {
    boolean isValid(char op);
}
