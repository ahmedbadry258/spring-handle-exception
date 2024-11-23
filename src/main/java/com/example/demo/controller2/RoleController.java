package com.example.demo.controller2;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RoleController {
    @GetMapping("/getRole")
    public String getRole() throws Exception {
        throw new Exception("Roles Not Found");
    }
}
