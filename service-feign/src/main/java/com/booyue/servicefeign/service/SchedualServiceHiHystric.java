package com.booyue.servicefeign.service;


import org.springframework.stereotype.Service;

@Service
public class SchedualServiceHiHystric implements SchedualServiceHi {

    @Override
    public String sayHiFromClientOne(String name) {
        return "Sorry, " + name;
    }
}
