package org.csystem.util.collection;

import org.csystem.util.string.StringUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.util.Random;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

@Disabled
class CollectionUtilToModifiableListTests {
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
        var modifiableList = CollectionUtil.toModifiableList(m_stream);

        assertDoesNotThrow(() -> modifiableList.add("ali"));
    }

    @Test
    void givenList_whenUnmodifiable_thenReturnsModifiableListDoesNotThrowException()
    {
        var modifiableList = CollectionUtil.toModifiableList(m_stream.toList());

        assertDoesNotThrow(() -> modifiableList.add("ali"));
    }
}
