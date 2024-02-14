package io.noobi.security.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RegistrationDTO {

  private String username;

  private String password;

  @Override
  public String toString(){
    return "Registration Info : username = " + this.username + ", password = " + this.password ;
  }
}
