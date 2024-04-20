package org.csystem.app.fraction;

import org.csystem.math.Complex;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;
import java.util.Random;
import java.util.random.RandomGenerator;

public class ComplexFactory {
    private final RandomGenerator m_randomGenerator = new Random();

    public static final Complex ZERO = new Complex();

    public Complex createRandomComplexNumber(int a, int b)
    {
        return new Complex(m_randomGenerator.nextInt(a, b + 1), m_randomGenerator.nextInt(a, b + 1));
    }

    public Collection<Complex> createRandomComplexNumbersUntilZero(int a, int b)
    {
        var zero = new Complex();
        var numbers = new ArrayList<Complex>();

        while (true) {
            var z = createRandomComplexNumber(a, b);

            if (zero.equals(z))
                break;

            numbers.add(z);
        }

        return numbers;
    }


}
