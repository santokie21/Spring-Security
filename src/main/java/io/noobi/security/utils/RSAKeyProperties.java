package io.noobi.security.utils;

import lombok.Data;
import org.springframework.stereotype.Component;

import java.security.KeyPair;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;

@Data
@Component
public class RSAKeyProperties {
  private RSAPrivateKey rsaPrivateKey;
  private RSAPublicKey  rsaPublicKey;

  public RSAKeyProperties() {
    KeyPair pair = KeyGeneratorUtility.generateRsaKey();
    this.rsaPrivateKey = (RSAPrivateKey) pair.getPrivate();
    this.rsaPublicKey = (RSAPublicKey) pair.getPublic();
  }
}
