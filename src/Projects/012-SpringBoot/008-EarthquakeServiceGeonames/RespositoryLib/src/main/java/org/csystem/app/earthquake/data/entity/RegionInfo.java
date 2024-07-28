package org.csystem.app.earthquake.data.entity;

import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;

@EqualsAndHashCode
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
    public LocalDateTime queryDateTime;
}
