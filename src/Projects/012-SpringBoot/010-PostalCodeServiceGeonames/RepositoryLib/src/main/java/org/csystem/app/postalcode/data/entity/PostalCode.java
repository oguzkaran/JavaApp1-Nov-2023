package org.csystem.app.postalcode.data.entity;

import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.time.LocalDateTime;
import java.util.Set;


@EqualsAndHashCode
@ToString
@Entity
@Table(name = "postal_codes")
public class PostalCode { //POJO (Plain Old Java Object)
    @Id
    @Column(name = "postal_code", length = 128)
    public String postalCode;

    @Column(name = "first_query_datetime", nullable = false)
    public LocalDateTime firstQueryDateTime = LocalDateTime.now();

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "postalCode", cascade = CascadeType.ALL)
    public Set<PostalCodeInfo> postalCodeInfo;
}
