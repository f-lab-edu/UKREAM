package com.ukream.dto;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Builder
@AllArgsConstructor
public class PaymentInfoDTO {
    private Long paymentInfoId;
    private Long userId;
    private String cardCompanyName;
    private String cardNumber;
    private String defaultPaymentYn;
    private LocalDateTime createDate;
    private LocalDateTime modifyDate;
}