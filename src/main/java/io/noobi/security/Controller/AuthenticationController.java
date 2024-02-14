package io.noobi.security.Controller;

import io.noobi.security.Service.AuthenticationService;
import io.noobi.security.dto.LoginResponseDTO;
import io.noobi.security.dto.RegistrationDTO;
import io.noobi.security.model.ApplicationUser;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@CrossOrigin("*")
public class AuthenticationController {

  public AuthenticationService authenticationService;

  public AuthenticationController(AuthenticationService authenticationService) {
    this.authenticationService=authenticationService;
  }

  @PostMapping("/register")
  public ApplicationUser registerUser(@RequestBody RegistrationDTO registrationDTO) {
    return authenticationService.registerUser(registrationDTO.getUsername(),registrationDTO.getPassword());
  }

  @PostMapping("/login")
  public LoginResponseDTO loginUser(@RequestBody RegistrationDTO registrationDTO) {
    return authenticationService.loginUser(registrationDTO.getUsername(),registrationDTO.getPassword());
  }
}
