package org.csystem.app.flight.service.city.error;

import lombok.*;
import lombok.experimental.Accessors;

@Getter
@Setter
@ToString
@EqualsAndHashCode
@AllArgsConstructor
public class CityError {
    @Accessors(prefix = "m_")
    private String m_cityName;

    @Accessors(prefix = "m_")
    private String m_errorMessage;

    @Accessors(prefix = "m_")
    private int m_status;
}
