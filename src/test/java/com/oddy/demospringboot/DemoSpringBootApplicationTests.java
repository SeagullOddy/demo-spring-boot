package com.oddy.demospringboot;

import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

@Slf4j
@SpringBootTest
class DemoSpringBootApplicationTests {

  @Resource
  private PasswordEncoder passwordEncoder;

  @Test
  void contextLoads() {
    log.info("Password: " + passwordEncoder.encode("admin"));
  }

}
