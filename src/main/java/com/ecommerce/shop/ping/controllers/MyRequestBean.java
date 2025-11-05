package com.ecommerce.shop.ping.controllers;

import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.RequestScope;

@Component
@RequestScope
public class MyRequestBean  {
    public String getValue() {
        return "Request-specific data";
    }
}
