package org.csystem.app.earthquake.data.entity;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@EqualsAndHashCode
@Builder
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
