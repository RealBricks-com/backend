package com.app.admin_backend_v1.controller;

import com.app.admin_backend_v1.dto.UserAuthCreateDTO;
import com.app.admin_backend_v1.dto.UserAuthDTO;
import com.app.admin_backend_v1.service.UserAuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin/user-auths")
public class UserAuthController {

    @Autowired
    private UserAuthService userAuthService;

    @PostMapping
    public UserAuthDTO createUserAuth(@RequestBody UserAuthCreateDTO dto) {
        return userAuthService.createUserAuth(dto);
    }

    @PutMapping("/{id}")
    public UserAuthDTO updateUserAuth(@PathVariable Integer id, @RequestBody UserAuthDTO dto) {
        return userAuthService.updateUserAuth(id, dto);
    }

    @GetMapping("/{id}")
    public UserAuthDTO getUserAuthById(@PathVariable Integer id) {
        return userAuthService.getUserAuthById(id);
    }

    @GetMapping
    public List<UserAuthDTO> getAllUserAuths() {
        return userAuthService.getAllUserAuths();
    }

    @DeleteMapping("/{id}")
    public void deleteUserAuth(@PathVariable Integer id) {
        userAuthService.deleteUserAuth(id);
    }
}