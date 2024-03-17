package org.csystem.app.fraction;

import org.csystem.math.Fraction;

import java.util.*;
import java.util.random.RandomGenerator;

public class FractionFactory {
    private final RandomGenerator m_randomGenerator = new Random();

    public Optional<Fraction> createRandomFraction(int a, int b)
    {
        Optional<Fraction> result = Optional.empty();

        try {
            return Optional.of(new Fraction(m_randomGenerator.nextInt(a, b + 1), m_randomGenerator.nextInt(a, b + 1)));
        }
        catch (NumberFormatException ignore) {

        }

        return result;
    }

    public Collection<Fraction> createRandomFractionsUntilZero(int a, int b)
    {
        var zero = new Fraction(0);
        var fractions = new ArrayList<Fraction>();

        while (true) {
            var resultOpt = createRandomFraction(a, b);

            if (resultOpt.isEmpty())
                continue;

            var fraction = resultOpt.get();

            if (zero.equals(fraction))
                break;

            fractions.add(fraction);
        }

        return fractions;
    }
}
