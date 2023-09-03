package com.oddy.demospringboot.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.sql.Date;
import lombok.Data;

@Schema(description = "账户资料实体类")
@Data
@Entity
@Table(name = "account_detail")
public class AccountDetail {

  @Schema(description = "账户资料 id")
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  @Id
  private Integer id;

  @Schema(description = "账户创建时间")
  @Column(name = "created_date")
  private Date createdDate;

}
