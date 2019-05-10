package com.shopee.tax.controller;

import com.shopee.tax.entity.Tax;
import com.shopee.tax.service.TaxService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class TaxController {

    @Autowired
    private TaxService taxService;

    @GetMapping("")
    public String index(Model model) {
        List<Tax> taxes = taxService.list();
        model.addAttribute("taxes", taxes);
        model.addAttribute("sub_total_price", taxService.getSubTotalPrice(taxes));
        model.addAttribute("sub_total_tax", taxService.getSubTotalTax(taxes));
        model.addAttribute("sub_total_amount", taxService.getSubTotalAmount(taxes));
        return "index";
    }

    @GetMapping("/add")
    public String add(Model model) {
        model.addAttribute("tax", new Tax());
        return "tax/add";
    }

    @PostMapping("/save")
    public String add(Tax tax) {
        taxService.create(tax);
        return "redirect:/";
    }
}
