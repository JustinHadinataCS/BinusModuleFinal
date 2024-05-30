package com.example.demo.Registration;

import com.example.demo.AppUser.AppUser;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

import static com.example.demo.AppUser.AppUserRole.ADMIN;

@RestController
@RequestMapping(path = {"api/v1/registration"})
public class RegistrationController {

    private RegistrationService registrationService;
    private List<AppUser> appUsers = new ArrayList<>();

    public RegistrationController(RegistrationService registrationService) {
        this.registrationService = registrationService;
    }

    @GetMapping("/index")
    public String index() {
        return "index";
    }

    @PostMapping
    public String register(@RequestBody RegistrationRequest request) {
        return this.registrationService.register(request);
    }
}
