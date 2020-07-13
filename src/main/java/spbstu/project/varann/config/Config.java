package spbstu.project.varann.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import spbstu.project.varann.parser.VcfParser;

@Configuration
public class Config {
  @Bean
  public VcfParser vcfParser() {
    return new VcfParser();
  }
  @Bean
  public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder(10);
  }
}
