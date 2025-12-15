package com.ecommerce.shop.controllers;

import com.ecommerce.shop.contracts.UserDetailsResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.web.bind.annotation.*;

@RestController()
@RequestMapping("/auth")
public class LoginController {
  @PostMapping("/login")
  public ResponseEntity<UserDetailsResponse> Login(Authentication authentication) {
    return ResponseEntity.ok(new UserDetailsResponse(authentication.getName()));
  }

  @GetMapping("/csrfToken")
  public ResponseEntity<String> CsrfToken(@RequestAttribute("_csrf") CsrfToken csrfToken) {
    var token = csrfToken.getToken();

    return ResponseEntity.ok(token);
  }
}
