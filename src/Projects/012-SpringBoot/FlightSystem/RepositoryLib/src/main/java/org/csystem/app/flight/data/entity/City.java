package org.csystem.app.flight.data.entity;

import lombok.*;
import lombok.experimental.Accessors;

@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@ToString
public class City {
    @Accessors(prefix = "m_")
    private long m_id;

    @Accessors(prefix = "m_")
    private String m_name;
}
