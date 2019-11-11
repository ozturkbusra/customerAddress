
package com.uygulama.iliski.controller;

import com.uygulama.iliski.model.oneToOne.Customer;
import com.uygulama.iliski.service.MainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.util.List;

@Controller
@RequestMapping(value = "/*", produces = MediaType.APPLICATION_JSON_VALUE + ";charset = utf-8")
public class MainController {

    @Autowired
    private MainService mainService;

    //View jsp
    @RequestMapping("/")
    public String giris(){
        return "giris";
    }

    //Customer sil
    @PostMapping(value = "/removeCustomer.ajax")
    public @ResponseBody String removeCustomer(@RequestParam Integer customerNo){
        Boolean success = mainService.removeCustomer(customerNo);
        return success.toString();
    }

    //Customer ekle
    @PostMapping(value = "/saveOrUpdateCustomer.ajax")
    public @ResponseBody String saveOrUpdateCustomer(@RequestParam Integer customerNo, @RequestParam String firstName, @RequestParam String lastName, @RequestParam Date birthOfDate, @RequestParam String city, @RequestParam String district, @RequestParam String street, @RequestParam Integer postCode){

        Boolean success = mainService.saveOrUpdateCustomer(customerNo,firstName,lastName,birthOfDate,city,district,street,postCode);

        return success.toString();
    }

    //get CustomerList
    @GetMapping(value = "/loadCustomerList.ajax")
    public @ResponseBody String loadCustomerList(){
        List<Customer> customerList = mainService.loadCustomerList();
        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append("Customer Listesi : ");

        for (Customer customer : customerList) {
            stringBuilder.append(customer);
        }

        return stringBuilder.toString();
    }
}