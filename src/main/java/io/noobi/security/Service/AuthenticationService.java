package io.noobi.security.Service;

import io.noobi.security.dto.LoginResponseDTO;
import io.noobi.security.model.ApplicationUser;
import io.noobi.security.model.Role;
import io.noobi.security.repository.RoleRepository;
import io.noobi.security.repository.UserRepository;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Set;

@Service
@Transactional
public class AuthenticationService {

  private UserRepository userRepository;
  private RoleRepository roleRepository;
  private PasswordEncoder passwordEncoder;
  private AuthenticationManager authenticationManager;
  private TokenService tokenService;

  public AuthenticationService(UserRepository userRepository,
                               RoleRepository roleRepository,
                               PasswordEncoder passwordEncoder,
                               AuthenticationManager authenticationManager,
                               TokenService tokenService) {
    this.tokenService=tokenService;
    this.userRepository=userRepository;
    this.roleRepository=roleRepository;
    this.passwordEncoder=passwordEncoder;
    this.authenticationManager=authenticationManager;
  }

  public ApplicationUser registerUser(String username,String password) {
    String encodedPassword = passwordEncoder.encode(password);
    Role userRole = roleRepository.findByAuthority("USER").get();

    Set<Role> authorities = new HashSet<> ();
    authorities.add (userRole);

    return userRepository.save(new ApplicationUser(0,username,encodedPassword,authorities));
  }

  public LoginResponseDTO loginUser(String username,String password) {

    try{
      Authentication authentication= authenticationManager.authenticate(
          new UsernamePasswordAuthenticationToken(username,password)
      );

      String token =tokenService.generateJwt(authentication);

      return new LoginResponseDTO(userRepository.findByUsername(username).get(),token);

    } catch (AuthenticationException e){
      return new LoginResponseDTO(null,"");
    }
  }

}
