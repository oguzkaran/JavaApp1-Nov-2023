package org.csystem.app.postalcode.data.entity;

import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@EqualsAndHashCode
@ToString
@Entity
@Table(name = "postal_code_info")
public class PostalCodeInfo { //POJO (Plain Old Java Object)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "postal_code_info_id")
    public long id;

    @Column(name = "admin_code2")
    public String adminCode2;
    @Column(name = "admin_code1")
    public String adminCode1;
    @Column(name = "admin_name2")
    public String adminName2;

    public double lng;

    @Column(name = "country_code")
    public String countryCode;
    @Column(name = "admin_name1")
    public String adminName1;
    @Column(name = "iso3166_2")
    public String iSO31662;
    @Column(name = "place_name")
    public String placeName;

    @Column(name = "postal_code_value")
    public String postalCodeValue;

    public double lat;

    @Column(name = "admin_name3")
    public String adminName3;

    @Column(name = "admin_code3")
    public String adminCode3;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "postal_code_id", nullable = false)
    public PostalCode postalCode;
}
