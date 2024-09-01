package org.csystem.app.postalcode.data.entity;

import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.time.LocalDateTime;

@EqualsAndHashCode
@ToString
@Entity
public class PostalCodeQueryInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public long id;

    public LocalDateTime queryDateTime = LocalDateTime.now();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "postal_code", nullable = false)
    public PostalCode postalCode;
}
