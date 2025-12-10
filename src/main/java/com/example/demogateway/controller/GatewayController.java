package com.example.demogateway.controller;

import com.example.demogateway.model.GatewayUser;
import com.example.demogateway.model.GatewayUserList;
import com.example.demogateway.service.GatewayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/gateway")
public class GatewayController {

    @Autowired
    private GatewayService gatewayService;

    @GetMapping("/user/{id}")
    public ResponseEntity<GatewayUser> user(@PathVariable("id") Integer id) {
        GatewayUser u = gatewayService.getTransformedUser(id);
        return new ResponseEntity<>(u, HttpStatus.OK);
    }

    @GetMapping("/users")
    public ResponseEntity<GatewayUserList> users() {
        GatewayUserList list = gatewayService.getTransformedUsers();
        return new ResponseEntity<>(list, HttpStatus.OK);
    }
}
