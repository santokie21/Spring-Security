package io.noobi.security.dto;

import io.noobi.security.model.ApplicationUser;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoginResponseDTO {

  private ApplicationUser user;

  private String jwt;

}
