package com.svitsmachnogo.api.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/secure")
public class SecurityTestController {

    @GetMapping("/anyone")
    public String unsicured(){
        return "For any one";
    }

    @GetMapping("/authorise")
    public String secure(){
        return "For authorise users ";
    }

    @GetMapping("/admin")
    public String admin(){
        return "For admin";
    }

    @GetMapping("/info")
    public String getUser(Principal principal){
        return principal.getName();
    }
}
