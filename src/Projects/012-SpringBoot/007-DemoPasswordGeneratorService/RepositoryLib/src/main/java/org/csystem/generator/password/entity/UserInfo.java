package org.csystem.generator.password.entity;

import lombok.*;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;

@Getter
@Setter
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class UserInfo {
    @Accessors(prefix = "m_")
    private String m_username;

    @Accessors(prefix = "m_")
    private String m_password;

    @Accessors(prefix = "m_")
    private int m_count;

    @Accessors(prefix = "m_")
    private int m_len;

    @Accessors(prefix = "m_")
    private LocalDateTime m_registerTime;
}
