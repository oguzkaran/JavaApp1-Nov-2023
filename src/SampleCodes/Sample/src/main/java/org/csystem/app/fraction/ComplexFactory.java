package org.csystem.app.fraction;

import org.csystem.math.Complex;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;
import java.util.Random;
import java.util.random.RandomGenerator;

public class ComplexFactory {
    private final RandomGenerator m_randomGenerator = new Random();

    public Optional<Complex> createRandomComplexNumber(int a, int b)
    {
        Optional<Complex> result = Optional.empty();

        try {
            return Optional.of(new Complex(m_randomGenerator.nextInt(a, b + 1), m_randomGenerator.nextInt(a, b + 1)));
        }
        catch (NumberFormatException ignore) {

        }

        return result;
    }

    public Collection<Complex> createRandomComplexNumbersUntilZero(int a, int b)
    {
        var zero = new Complex();
        var numbers = new ArrayList<Complex>();

        while (true) {
            var resultOpt = createRandomComplexNumber(a, b);

            if (resultOpt.isEmpty())
                continue;

            var z = resultOpt.get();

            if (zero.equals(z))
                break;

            numbers.add(z);
        }

        return numbers;
    }
}
