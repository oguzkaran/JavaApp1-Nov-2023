package org.csystem.app.flight.data.service.dto;

import lombok.*;
import lombok.experimental.Accessors;

@Getter
@Setter
@EqualsAndHashCode
@AllArgsConstructor
@ToString
public class CitySaveDTO {
    @Accessors(prefix = "m_")
    private String m_name;
}
