package com.backoffice.core.model.converters;

import com.backoffice.core.model.Supplier;
import lombok.AllArgsConstructor;

import static com.backoffice.utilities.Constants.*;


@AllArgsConstructor
public class ToSupplierConverter {

    public static Supplier toConvert() {
        return Supplier.builder()
                .name(SUPPLIER_NAME)
                .contact(SUPPLIER_CONTACT)
                .email(SUPPLIER_EMAIL)
                .phone_number(SUPPLIER_PHONE_NUMBER)
                .website(SUPPLIER_WEBSITE)
                .address_1(SUPPLIER_ADDRESS_1)
                .address_2(SUPPLIER_ADDRESS_2)
                .city(SUPPLIER_CITY)
                .region(SUPPLIER_REGION)
                .postal_code(SUPPLIER_POSTAL_CODE)
                .country_code(SUPPLIER_COUNTRY_CODE)
                .note(SUPPLIER_NOTE)
                .build();
    }

}