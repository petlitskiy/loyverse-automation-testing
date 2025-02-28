package com.backoffice.core.model;

import com.backoffice.core.enums.DefaultPricingType;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Builder
@Getter
@Setter
public class Variant {
    private String variant_id;
    private String item_id;
    private String sku;
    private String reference_variant_id;
    private String option1_value;
    private String option2_value;
    private String option3_value;
    private String barcode;
    private double cost;
    private String purchase_cost;
    private DefaultPricingType default_pricing_type;
    private double default_price;
    private List<Store> stores;


}
