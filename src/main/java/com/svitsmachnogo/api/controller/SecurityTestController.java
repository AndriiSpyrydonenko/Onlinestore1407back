package com.svitsmachnogo.api.controller;

import com.svitsmachnogo.api.exceptions.UserAlreadyExistException;
import com.svitsmachnogo.api.service.abstractional.AuthService;
import com.svitsmachnogo.api.service.abstractional.MailSenderService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
@Tag(name = "Auth test controller",
        description = "For each request you must add an 'Authorization' header with a body like 'Bearer some_token'")
@RequestMapping("/api/secure")
public class SecurityTestController {

    private final MailSenderService mailSenderService;

    private final AuthService authService;

    @Operation(summary = "For any one", description = "Free access.")
    @GetMapping("/anyone")
    public String unSecured() {
        return "For any one";
    }

    @Operation(summary = "For authorise users ", description = "Access for authorized users only.")
    @GetMapping("/authorise")
    public String secure() {
        return "For authorise users ";
    }

    @Operation(summary = "For admin", description = "Only an admin can gain access.")
    @GetMapping("/admin")
    public String admin() {
        return "For admin";
    }

    @Operation(summary = "User info", description = "Return some information about user.")
    @GetMapping("/info")
    public String getUser(Principal principal) {
        return principal.getName();
    }

    @Operation(summary = "Send an email", description = "Send an email to the specified address with the provided message.")
    @PostMapping("/send/{to}/{message}")
    public void send(
            @Parameter(description = "Recipient email address", required = true)
            @PathVariable(name = "to") String to,
            @Parameter(description = "Email message text", required = true)
            @PathVariable(name = "message") String message) {
        mailSenderService.sendMail(mailSenderService.createMessage(to, "test", message));
    }

    @Operation(summary = "For inner testing. DON'T USE ME!", description = "The endpoint retrieve the encrypted part of URL in the path .Returns a JWT that lives 5m or an AppError")
    @GetMapping("/confirm/{user}")
    public ResponseEntity<?> confirm(@PathVariable(value = "user") String jwtUser) throws UserAlreadyExistException {
        return authService.confirmation(jwtUser);
    }
}
