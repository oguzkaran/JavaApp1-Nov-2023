package org.csystem.app.flight.data.entity;

import lombok.*;
import lombok.experimental.Accessors;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@EqualsAndHashCode
@ToString
@AllArgsConstructor
public class Airport {
    @Accessors(prefix = "m_")
    private long m_id;

    @Accessors(prefix = "m_")
    private String m_name;

    @Accessors(prefix = "m_")
    private long m_cityId;

    @Accessors(prefix = "m_")
    private LocalDate m_openDate;

    @Accessors(prefix = "m_")
    private LocalDateTime m_registerDateTime;
}
