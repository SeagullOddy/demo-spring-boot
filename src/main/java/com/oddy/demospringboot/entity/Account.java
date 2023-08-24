package com.oddy.demospringboot.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

// 实体类本身及其属性使用 @Schema （架构）注解来添加描述
@Schema(description = "账户实体类")
@Data
public class Account {

  @Schema(description = "账户 id")
  private Integer id;

  @Schema(description = "账户名称")
  private String name;

  @Schema(description = "账户使用者的性别")
  private String sex;

  @Schema(description = "账户使用者的年龄")
  private Integer age;

  @Schema(description = "账户使用者的邮件地址")
  private String email;

  @Schema(description = "账户密码")
  private String password;

}
