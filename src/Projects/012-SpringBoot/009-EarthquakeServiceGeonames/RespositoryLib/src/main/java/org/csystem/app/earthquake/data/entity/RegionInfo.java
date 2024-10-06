package org.csystem.app.earthquake.data.entity;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@EqualsAndHashCode
@Builder
@ToString
public class RegionInfo {
    public long id;

    @EqualsAndHashCode.Include
    public double east;

    @EqualsAndHashCode.Include
    public double west;

    @EqualsAndHashCode.Include
    public double north;

    @EqualsAndHashCode.Include
    public double south;

    @EqualsAndHashCode.Include
    public LocalDateTime queryDateTime;
}
