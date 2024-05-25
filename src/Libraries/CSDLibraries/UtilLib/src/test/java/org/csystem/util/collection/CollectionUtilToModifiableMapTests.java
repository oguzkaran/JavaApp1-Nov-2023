package org.csystem.util.collection;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.util.*;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

@Disabled("Written by Berkay Yılmaz")
class CollectionUtilToModifiableMapTests {
    private final List<UserData> m_users = new ArrayList<>();
    private Map<String, Double> m_map;

    static class UserData {
        private String m_username;
        private Double m_rank;

        UserData (String username, double rank)
        {
            m_username = username;
            m_rank = rank;
        }

        public String getUsername() {
            return m_username;
        }

        public Double getRank() {
            return m_rank;
        }

        public void setUsername(String username) {
            m_username = username;
        }

        public void setRank(Double rank) {
            m_rank = rank;
        }
    }

    @BeforeEach
    void setUp()
    {
        m_users.add(new UserData("test1", 85.));
        m_users.add(new UserData("test2", 95.));
        m_users.add(new UserData("test3", 75.));

        m_map = m_users.stream()
                .collect(Collectors.toUnmodifiableMap(UserData::getUsername, UserData::getRank));
    }

    @Test
    void givenStream_thenReturnsModifiableMapDoesNotThrowException()
    {
        var modifiableMap = CollectionUtil.toModifiableMap(m_users.stream(), UserData::getUsername, UserData::getRank);
        assertDoesNotThrow(() -> modifiableMap.put("test4", 85.));
    }

    @Test
    void givenMap_whenUnmodifiable_thenReturnsModifiableMapDoesNotThrowException()
    {
        var modifiableMap = CollectionUtil.toModifiableMap(m_map);

        assertDoesNotThrow(() -> modifiableMap.put("test5", 50.));
    }
}
