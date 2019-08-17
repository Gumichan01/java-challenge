package com.gumichan01.challenge.controller;

import com.gumichan01.challenge.controller.dto.UserBidDto;
import com.gumichan01.challenge.service.UserBidService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UserBidController {

    private static final Logger logger = LoggerFactory.getLogger(UserBidController.class);

    @Autowired
    UserBidService service;

    @GetMapping("/")
    public String index() {
        return "Challenge accepted - It works!";
    }

    @GetMapping("/userbid/")
    public List<UserBidDto> retrieveUserBids() {
        return service.retrieveUserBids();
    }
}