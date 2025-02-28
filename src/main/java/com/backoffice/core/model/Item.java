package com.backoffice.core.model;

import com.backoffice.core.enums.Color;
import com.backoffice.core.enums.Form;
import com.backoffice.utilities.Exclude;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Builder
@Setter
@Getter
public class Item {

    private String id;
    private String item_name;
    private String description;
    private String reference_id;
    private String category_id;
    @Exclude
    private boolean track_stock;
    @Exclude
    private boolean sold_by_weight;
    @Exclude
    private boolean is_composite;
    @Exclude
    private boolean use_production;
    private List<Variant> variants;
    private List<Component> components;
    private String primary_supplier_id;
    private String[] tax_ids;
    private String[] modifiers_ids;
    private Form form;
    private Color color;
    private String option1_name;
    private String option2_name;
    private String option3_name;
}


