package com.backoffice.core.model;


import groovy.transform.builder.Builder;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Builder
@Getter
@Setter
public class Component {
    private String variant_id;
    private BigDecimal quantity;
}
