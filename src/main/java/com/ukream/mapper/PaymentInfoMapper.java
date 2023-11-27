package com.ukream.mapper;

import com.ukream.dto.PaymentInfoDTO;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface PaymentInfoMapper {
  public void createPaymentInfo(PaymentInfoDTO paymentInfo);

  public List<PaymentInfoDTO> getPaymentInfos(Long userId);

  public PaymentInfoDTO getPaymentInfo(Long paymentInfoId, Long userId);

  public int deletePaymentInfo(Long paymentInfoId);

  public int updatePaymentInfo(PaymentInfoDTO paymentInfo);
}
