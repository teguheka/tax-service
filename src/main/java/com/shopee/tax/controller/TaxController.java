package com.shopee.tax.controller;

import com.shopee.tax.service.TaxService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TaxController {

    @Autowired
    private TaxService taxService;

    @GetMapping("")
    public String index(Model model) {
        return "index";
    }
}
