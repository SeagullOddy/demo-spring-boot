package com.oddy.demospringboot;

import com.oddy.demospringboot.entity.Account;
import jakarta.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.sql.DataSource;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.security.crypto.password.PasswordEncoder;

@Slf4j
@SpringBootTest
class DemoSpringBootApplicationTests {

  @Resource
  private PasswordEncoder passwordEncoder;

  @Resource
  private JdbcTemplate jdbcTemplate;

  @Resource
  private DataSource dataSource;

  @Test
  void contextLoads() {
    // Password Encode
    log.info("Password: " + passwordEncoder.encode("admin"));

    // Spring JDBC
    // 一条记录 Map
    Map<String, Object> map = jdbcTemplate.queryForMap(
        "select * from demo_spring_boot.account where id = 1");
    log.info(map.toString());
    // 多条记录 List
    List<Map<String, Object>> list = jdbcTemplate.queryForList(
        "select * from demo_spring_boot.account");
    log.info(list.toString());
    // 一条记录实体类
    Account account = jdbcTemplate.queryForObject(
        "select * from demo_spring_boot.account where id = 2", (rs, rowNum) -> {
          Account tmp = new Account();
          tmp.setId(rs.getInt(1));
          tmp.setName(rs.getString(2));
          tmp.setSex(rs.getString(3));
          tmp.setAge(rs.getInt(4));
          tmp.setEmail(rs.getString(5));
          tmp.setPassword(rs.getString(6));
          return tmp;
        });
    assert account != null;
    log.info(account.toString());
    // 使用参数查询
    int requestId = 1;
    map = jdbcTemplate.queryForMap("select * from demo_spring_boot.account where id = ?",
        requestId);
    log.info(map.toString());
    // Simple JDBC Insert，实现高级插入功能
    SimpleJdbcInsert simpleJdbcInsert = new SimpleJdbcInsert(dataSource).withTableName(
            "account") // 指定插入数据的表名
        .usingGeneratedKeyColumns("id"); // 设置自增主键列
    map = new HashMap<>(2);
    map.put("name", "jdbc insert");
    map.put("email", "jdbc-insert@test.com");
    map.put("password", "$2a$10$D/Eyfj0KweoCsW8Jl6EM6.0ubDG4ZdCeIq3M9YuYSIQBZmuRIK8Oe"); // admin
    Number key = simpleJdbcInsert.executeAndReturnKey(map);
    log.info(key.toString());
  }

}
