package com.svitsmachnogo.api.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
@Tag(name="Auth test controller",
        description = "For each request you must add an 'Authorization' header with a body like 'Bearer some_token'")
@RequestMapping("/api/secure")
public class SecurityTestController {

    @GetMapping("/anyone")
    public String unSecured(){
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
