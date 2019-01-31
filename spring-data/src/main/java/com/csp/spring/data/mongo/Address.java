package com.csp.spring.data.mongo;

import lombok.Data;

/**
 * @author CSP
 * @since 2019/1/31
 */
@Data
public class Address {

    private String country;

    private String city;

    private String district;

    private String detail;

    public Address(String country, String city, String district) {
        this.country = country;
        this.city = city;
        this.district = district;
    }
}
