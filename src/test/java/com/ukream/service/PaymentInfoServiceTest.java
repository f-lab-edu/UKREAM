package com.ukream.service;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.BDDMockito.*;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import com.ukream.dto.PaymentInfoDTO;
import com.ukream.error.exception.PaymentInfoNotFoundException;
import com.ukream.mapper.PaymentInfoMapper;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class PaymentInfoServiceTest {

  @Mock private PaymentInfoMapper paymentInfoMapper;

  @InjectMocks private PaymentInfoService paymentInfoService;

  @Test
  void 결제_정보_생성_테스트() {
    PaymentInfoDTO paymentInfo =
        PaymentInfoDTO.builder().userId(1L).cardCompanyName("test").cardNumber("12345").build();

    doNothing().when(paymentInfoMapper).createPaymentInfo(paymentInfo);

    assertDoesNotThrow(() -> paymentInfoService.createPaymentInfo(paymentInfo));

    verify(paymentInfoMapper, times(1)).createPaymentInfo(paymentInfo);
  }

  @Test
  void 결제_정보_목록_조회_테스트() {
    Long userId = 1L;

    PaymentInfoDTO paymentInfo =
        PaymentInfoDTO.builder().userId(1L).cardCompanyName("test").cardNumber("12345").build();

    List<PaymentInfoDTO> paymentInfoList = Arrays.asList(paymentInfo);

    given(paymentInfoMapper.getPaymentInfos(userId)).willReturn(paymentInfoList);

    List<PaymentInfoDTO> result =
        assertDoesNotThrow(() -> paymentInfoService.getPaymentInfos(userId));

    assertEquals(paymentInfoList, result);

    verify(paymentInfoMapper, times(1)).getPaymentInfos(userId);
  }

  @Test
  void 결제_정보_조회_성공_테스트() {
    Long paymentInfoId = 1L;
    Long userId = 1L;

    PaymentInfoDTO paymentInfo =
        PaymentInfoDTO.builder().userId(1L).cardCompanyName("test").cardNumber("12345").build();

    given(paymentInfoMapper.getPaymentInfo(paymentInfoId, userId)).willReturn(paymentInfo);

    PaymentInfoDTO result =
        assertDoesNotThrow(() -> paymentInfoService.getPaymentInfo(paymentInfoId, userId));

    assertEquals(paymentInfo, result);

    verify(paymentInfoMapper, times(1)).getPaymentInfo(paymentInfoId, userId);
  }

  @Test
  void 결제_정보_조회_실패_테스트() {
    Long paymentInfoId = 1L;
    Long userId = 1L;

    given(paymentInfoMapper.getPaymentInfo(paymentInfoId, userId)).willReturn(null);

    assertThrows(
        PaymentInfoNotFoundException.class,
        () -> {
          paymentInfoService.getPaymentInfo(paymentInfoId, userId);
        });

    verify(paymentInfoMapper, times(1)).getPaymentInfo(paymentInfoId, userId);
  }

  @Test
  void 결제_정보_삭제_성공_테스트() {
    Long paymentInfoId = 1L;

    given(paymentInfoMapper.deletePaymentInfo(paymentInfoId)).willReturn(1);

    assertDoesNotThrow(() -> paymentInfoService.deletePaymentInfo(paymentInfoId));

    verify(paymentInfoMapper, times(1)).deletePaymentInfo(paymentInfoId);
  }

  @Test
  void 결제_정보_삭제_실패_테스트() {
    Long paymentInfoId = 1L;

    given(paymentInfoMapper.deletePaymentInfo(paymentInfoId)).willReturn(0);

    assertThrows(
        PaymentInfoNotFoundException.class,
        () -> {
          paymentInfoService.deletePaymentInfo(paymentInfoId);
        });

    verify(paymentInfoMapper, times(1)).deletePaymentInfo(paymentInfoId);
  }

  @Test
  void 결제_정보_수정_성공_테스트() {
    PaymentInfoDTO paymentInfo =
        PaymentInfoDTO.builder().userId(1L).cardCompanyName("test").cardNumber("12345").build();

    given(paymentInfoMapper.updatePaymentInfo(paymentInfo)).willReturn(1);

    assertDoesNotThrow(() -> paymentInfoService.updatePaymentInfo(paymentInfo));

    verify(paymentInfoMapper, times(1)).updatePaymentInfo(paymentInfo);
  }

  @Test
  void 결제_정보_수정_실패_테스트() {

    PaymentInfoDTO paymentInfo =
        PaymentInfoDTO.builder().userId(1L).cardCompanyName("test").cardNumber("12345").build();

    given(paymentInfoMapper.updatePaymentInfo(paymentInfo)).willReturn(0);

    assertThrows(
        PaymentInfoNotFoundException.class,
        () -> {
          paymentInfoService.updatePaymentInfo(paymentInfo);
        });

    verify(paymentInfoMapper, times(1)).updatePaymentInfo(paymentInfo);
  }
}
