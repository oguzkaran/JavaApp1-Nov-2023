package org.csystem.util.collection;

import org.csystem.util.string.StringUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

//@Disabled
class CollectionUtilToModifiableSetTests {
    private Stream<String> m_stream;

    @BeforeEach
    void setUp()
    {
        var random = new Random();
        m_stream = Stream.generate(() -> StringUtil.generateRandomTextEN(random, 10)).limit(10);
    }

    @Test
    void givenStream_thenReturnsModifiableListDoesNotThrowException()
    {
        var modifiableSet = CollectionUtil.toModifiableSet(m_stream);

        assertDoesNotThrow(() -> modifiableSet.add("ali"));
    }

    @Test
    void givenSet_whenUnmodifiable_thenReturnsModifiableListDoesNotThrowException()
    {
        var modifiableSet = CollectionUtil.toModifiableSet(m_stream.collect(Collectors.toSet()));

        assertDoesNotThrow(() -> modifiableSet.add("ali"));
    }
}
