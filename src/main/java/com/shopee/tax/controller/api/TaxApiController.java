package com.shopee.tax.controller.api;

import com.shopee.tax.entity.Tax;
import com.shopee.tax.service.TaxService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/taxes")
public class TaxApiController {

    @Autowired private TaxService taxService;

    /**
     * creating tax
     */
    @PostMapping("")
    public Tax create(@RequestBody Tax tax) {
        return taxService.create(tax);
    }

    /**
     *  Get list of tax
     */
    @GetMapping("")
    public Map list() {
        List<Tax> taxes = taxService.list();

        Map<String, Object> response = new HashMap<>();
        response.put("data", taxes);
        response.put("sub_total_price", taxService.getSubTotalPrice(taxes));
        response.put("sub_total_tax", taxService.getSubTotalTax(taxes));
        response.put("sub_total_amount", taxService.getSubTotalAmount(taxes));
        return response;
    }
}
