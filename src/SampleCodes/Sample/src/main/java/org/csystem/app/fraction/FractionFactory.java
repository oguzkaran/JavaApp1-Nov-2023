package org.csystem.app.fraction;

import org.csystem.math.Fraction;

import java.util.*;
import java.util.random.RandomGenerator;

public class FractionFactory {
    private final RandomGenerator m_randomGenerator = new Random();
    private final Fraction ZERO = new Fraction(0);

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

    public List<Fraction> createRandomFractionsUntilZero(int a, int b)
    {
        var fractions = new ArrayList<Fraction>();

        while (true) {
            var resultOpt = createRandomFraction(a, b);

            if (resultOpt.isEmpty())
                continue;

            var fraction = resultOpt.get();

            if (ZERO.equals(fraction))
                break;

            fractions.add(fraction);
        }

        return fractions;
    }

    public List<Fraction> createSortedRandomFractions(int a, int b)
    {
        var fractions = new ArrayList<Fraction>();

        while (true) {
            var resultOpt = createRandomFraction(a, b);

            if (resultOpt.isEmpty())
                break;

            var fraction = resultOpt.get();

            fractions.add(fraction);
        }

        Collections.sort(fractions);

        return fractions;
    }

    public Fraction [] createRandomFractionArrayUntilZero(int count, int a, int b)
    {
        var fractions = new Fraction[count];

        while (count-- > 0) {
            var resultOpt = createRandomFraction(a, b);

            while ((resultOpt = createRandomFraction(a, b)).isEmpty())
                ;

            var fraction = resultOpt.get();

            fractions[count] = fraction;
        }

        return fractions;
    }
}
