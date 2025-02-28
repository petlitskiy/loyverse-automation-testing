package com.backoffice.core.model.converters;

import com.backoffice.core.enums.DefaultPricingType;
import com.backoffice.core.model.Item;
import com.backoffice.core.model.Store;
import com.backoffice.core.model.Variant;
import lombok.AllArgsConstructor;

import java.math.BigDecimal;
import java.util.Arrays;

import static com.backoffice.utilities.Constants.*;
import static com.backoffice.utilities.NullHelper.nullSafeGet;

@AllArgsConstructor
public class ToItemConverter {

    public static Item toConvert(String storeUUID, String supplierId) {
        return Item.builder()
                .item_name(nullSafeGet(() -> ITEM_NAME))
                .option2_name(nullSafeGet(() -> ITEM_OPTION2_NAME))
                .modifiers_ids(nullSafeGet(() -> new String[]{MODIFIERS_ID_1}))
                .primary_supplier_id(nullSafeGet(() -> supplierId))
                .variants(nullSafeGet(() -> Arrays.asList(variantToItemConverter(storeUUID))))
                .build();
    }

    private static Variant variantToItemConverter(String storeUUID) {
        return Variant.builder()
                .reference_variant_id(null)
                .option2_value(OPTION_2_VALUE)
                .barcode(null)
                .cost(VARIANT_COST)
                .purchase_cost(null)
                .default_pricing_type(DefaultPricingType.FIXED)
                .default_price(VARIANT_DEFAULT_PRICE)
                .stores(Arrays.asList(storeToVariantConverter(storeUUID)))
                .build();
    }

    private static Store storeToVariantConverter(String storeUUID) {
        return Store.builder()
                .store_id(storeUUID)
                .pricing_type(PRICING_TYPE)
                .price(new BigDecimal(ITEM_PRICE))
                .available_for_sale(true)
                .build();
    }


}
