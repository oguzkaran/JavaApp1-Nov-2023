package org.csystem.app.postalcode.data.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@EqualsAndHashCode
@ToString
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PostalCodeQueryInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public long id;

    public LocalDateTime queryDateTime = LocalDateTime.now();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "postal_code_id", nullable = false)
    public PostalCode postalCode;
}
