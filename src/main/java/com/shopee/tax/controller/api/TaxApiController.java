package com.shopee.tax.controller.api;

import com.shopee.tax.entity.Tax;
import com.shopee.tax.service.TaxService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/taxes")
public class TaxApiController {

    @Autowired private TaxService taxService;

    /**
     * creating tax
     * @param tax
     * @return
     */
    @PostMapping("")
    public Tax create(@RequestBody Tax tax) {
        return taxService.create(tax);
    }

    /**
     *  Get list of tax
     * @param pageNumber
     * @param limit
     * @return
     */
    @GetMapping("")
    public Map list(@RequestParam(defaultValue = "1") Integer pageNumber, @RequestParam(defaultValue = "5") Integer limit) {
        Page<Tax> orderPage = taxService.list(pageNumber - 1, limit);

        Map<String, Object> response = new HashMap<>();
        response.put("data", orderPage.getContent());
        response.put("sub_total_price", taxService.getSubTotalPrice(orderPage.getContent()));
        response.put("sub_total_tax", taxService.getSubTotalTax(orderPage.getContent()));
        response.put("sub_total_amount", taxService.getSubTotalAmount(orderPage.getContent()));
        return response;
    }
}
