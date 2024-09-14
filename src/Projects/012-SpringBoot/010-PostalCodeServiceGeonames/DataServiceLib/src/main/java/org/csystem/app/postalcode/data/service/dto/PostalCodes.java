package org.csystem.app.postalcode.data.service.dto;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.util.List;


@EqualsAndHashCode
@ToString
@Builder
public class PostalCodes {
    public List<PostalCode> postalCodes;
}
