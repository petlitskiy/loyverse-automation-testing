package com.backoffice.core.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Builder
@Getter
@Setter
public class Store {
    private String store_id;
    private String pricing_type;
    private BigDecimal price;
    private boolean available_for_sale;
    private BigDecimal optimal_stock;
    private BigDecimal low_stock;
}
