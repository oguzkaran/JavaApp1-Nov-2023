package org.csystem.app.service.earthquake.dto;

import lombok.*;

import java.time.LocalDateTime;

@EqualsAndHashCode
@ToString
public class RegionInfoDTO {
    public double east;
    public double west;
    public double north;
    public double south;
    public LocalDateTime queryDateTime;
}
