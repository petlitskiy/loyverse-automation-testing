package com.backoffice.core.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

/**
 * @author Neha Kharbanda
 */

@Builder
@Getter
@Setter
public class Supplier {

    private String id;
    private String name;
    private String contact;
    private String email;
    private String phone_number;
    private String website;
    private String address_1;
    private String address_2;
    private String city;
    private String region;
    private String postal_code;
    private String country_code;
    private String note;
}
