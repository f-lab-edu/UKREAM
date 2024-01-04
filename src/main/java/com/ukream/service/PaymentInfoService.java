package com.ukream.service;

import com.ukream.dto.PaymentInfoDTO;
import com.ukream.error.exception.PaymentInfoNotFoundException;
import com.ukream.mapper.PaymentInfoMapper;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PaymentInfoService {
  private final PaymentInfoMapper paymentInfoMapper;

  public void createPaymentInfo(PaymentInfoDTO paymentInfo) {
    paymentInfoMapper.createPaymentInfo(paymentInfo);
  }

  public List<PaymentInfoDTO> getPaymentInfos(Long userId) {
    return paymentInfoMapper.getPaymentInfos(userId);
  }

  public PaymentInfoDTO getPaymentInfo(Long paymentInfoId, Long userId) {
    PaymentInfoDTO paymentInfo = paymentInfoMapper.getPaymentInfo(paymentInfoId, userId);
    if (paymentInfo == null) {
      throw new PaymentInfoNotFoundException();
    }
    return paymentInfo;
  }

  public void deletePaymentInfo(Long paymentInfoId) {
    int deletedRows = paymentInfoMapper.deletePaymentInfo(paymentInfoId);
    if (deletedRows == 0) {
      throw new PaymentInfoNotFoundException();
    }
  }

  public void updatePaymentInfo(PaymentInfoDTO paymentInfo) {
    int updatedRows = paymentInfoMapper.updatePaymentInfo(paymentInfo);
    if (updatedRows == 0) {
      throw new PaymentInfoNotFoundException();
    }
  }
}
