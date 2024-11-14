package com.example.bankmanagementsystem.Controller;

import com.example.bankmanagementsystem.ApiResponse.ApiResponse;
import com.example.bankmanagementsystem.Model.CustomersSystem;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/api/v1/bank")
public class CustomerController {
    ArrayList<CustomersSystem> customersSystems = new ArrayList<>();

    @GetMapping("/get")
    public ArrayList<CustomersSystem> getcustomersSystems() {
        return customersSystems;
    }

    @PostMapping("/post")
    public ApiResponse addCustomer(@RequestBody CustomersSystem customersSystem) {
        customersSystems.add(customersSystem);
        return new ApiResponse("Customer Added Successfully");
    }

    @PutMapping("/update/{key}")
    public ApiResponse updateCustomer(@PathVariable int key, @RequestBody CustomersSystem customersSystem) {
        customersSystems.set(key, customersSystem);
        return new ApiResponse("Customer Updated Successfully");
    }

    @DeleteMapping("/remove/{key}")
    public ApiResponse removeCustomer(@PathVariable int key) {
        customersSystems.remove(key);
        return new ApiResponse("Customer Removed Successfully");
    }

    @PutMapping("/withdraw/{toKey}/{amount}")

    public ApiResponse withdraw(@PathVariable int toKey, @PathVariable double amount) {
        if (customersSystems.get(toKey).getBalance() >= amount)
        {
            customersSystems.get(toKey).setBalance(customersSystems.get(toKey).getBalance() - amount);
            return new ApiResponse("Customer Withdrawn Successfully");
        } else {
            return new ApiResponse("Customer Not Enough Balance");
        }
    }

    @PutMapping("/trans/{fromKey}/{toKey}/{amount}")

    public ApiResponse trans(@PathVariable int fromKey, @PathVariable int toKey, @PathVariable double amount) {
        if (customersSystems.get(fromKey).getBalance() >= amount)
        {
            customersSystems.get(toKey).setBalance(customersSystems.get(toKey).getBalance() + amount);
            customersSystems.get(fromKey).setBalance(customersSystems.get(fromKey).getBalance() - amount);
            return new ApiResponse("Customer "+customersSystems.get(fromKey).getUserName()+"transfer to "+customersSystems.get(toKey)+" Successfully"+"the amount is = "+amount);
        } else {
            return new ApiResponse("Customer Not Enough Balance");
        }
    }

    @PutMapping("/Deposit/{toKey}/{amount}")

    public ApiResponse Deposit(@PathVariable int toKey, @PathVariable double amount) {
        if (customersSystems.get(toKey)!=null)
        {   customersSystems.get(toKey).setBalance(customersSystems.get(toKey).getBalance() + amount);
            return new ApiResponse("Customer Deposited Successfully");
        } else {
            return new ApiResponse("Customer Not Found");
        }
    }



}
