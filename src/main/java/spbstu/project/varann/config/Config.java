package spbstu.project.varann.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import spbstu.project.varann.parser.VcfParser;

@Configuration
public class Config {
  @Bean
  public VcfParser vcfParser() {
    return new VcfParser();
  }
}
