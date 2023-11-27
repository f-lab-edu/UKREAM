package com.ukream.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Setter
@Getter
@Builder
@AllArgsConstructor
public class SalesAccountDTO {
  private Long salesAccountId;
  private Long userId;
  private String bankName;
  private String accountNumber;
  private String accountHolder;
  private LocalDateTime createDate;
  private LocalDateTime modifyDate;
  private boolean isDeleted;
}