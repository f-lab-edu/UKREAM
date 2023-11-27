package com.ukream.service;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.BDDMockito.*;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import com.ukream.dto.SalesAccountDTO;
import com.ukream.error.exception.SalesAccountNotFoundException;
import com.ukream.mapper.SalesAccountMapper;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class SalesAccountServiceTest {

  @Mock private SalesAccountMapper salesAccountMapper;

  @InjectMocks private SalesAccountService salesAccountService;

  @Test
  void 판매_정산_계좌_생성_테스트() {
    SalesAccountDTO salesAccount =
        SalesAccountDTO.builder().userId(1L).bankName("test").accountNumber("12345").build();

    doNothing().when(salesAccountMapper).createSalesAccount(salesAccount);

    assertDoesNotThrow(() -> salesAccountService.createSalesAccount(salesAccount));

    verify(salesAccountMapper, times(1)).createSalesAccount(salesAccount);
  }

  @Test
  void 판매_정산_계좌_목록_조회_테스트() {
    Long userId = 1L;

    SalesAccountDTO salesAccount =
        SalesAccountDTO.builder().userId(1L).bankName("test").accountNumber("12345").build();

    List<SalesAccountDTO> salesAccounts = Arrays.asList(salesAccount);

    given(salesAccountMapper.getSalesAccounts(userId)).willReturn(salesAccounts);

    List<SalesAccountDTO> result =
        assertDoesNotThrow(() -> salesAccountService.getSalesAccounts(userId));

    assertEquals(salesAccounts, result);

    verify(salesAccountMapper, times(1)).getSalesAccounts(userId);
  }

  @Test
  void 판매_정산_계좌_조회_성공_테스트() {
    Long salesAccountId = 1L;
    Long userId = 1L;

    SalesAccountDTO salesAccount =
        SalesAccountDTO.builder().userId(1L).bankName("test").accountNumber("12345").build();

    given(salesAccountMapper.getSalesAccount(salesAccountId, userId)).willReturn(salesAccount);

    SalesAccountDTO result =
        assertDoesNotThrow(() -> salesAccountService.getSalesAccount(salesAccountId, userId));

    assertEquals(salesAccount, result);

    verify(salesAccountMapper, times(1)).getSalesAccount(salesAccountId, userId);
  }

  @Test
  void 판매_정산_계좌_조회_실패_테스트() {
    Long salesAccountId = 1L;
    Long userId = 1L;

    given(salesAccountMapper.getSalesAccount(salesAccountId, userId)).willReturn(null);

    assertThrows(
        SalesAccountNotFoundException.class,
        () -> {
          salesAccountService.getSalesAccount(salesAccountId, userId);
        });

    verify(salesAccountMapper, times(1)).getSalesAccount(salesAccountId, userId);
  }

  @Test
  void 판매_정산_계좌_삭제_성공_테스트() {
    Long salesAccountId = 1L;

    given(salesAccountMapper.deleteSalesAccount(salesAccountId)).willReturn(1);

    assertDoesNotThrow(() -> salesAccountService.deleteSalesAccount(salesAccountId));

    verify(salesAccountMapper, times(1)).deleteSalesAccount(salesAccountId);
  }

  @Test
  void 판매_정산_계좌_삭제_실패_테스트() {
    Long salesAccountId = 1L;

    given(salesAccountMapper.deleteSalesAccount(salesAccountId)).willReturn(0);

    assertThrows(
        SalesAccountNotFoundException.class,
        () -> {
          salesAccountService.deleteSalesAccount(salesAccountId);
        });

    verify(salesAccountMapper, times(1)).deleteSalesAccount(salesAccountId);
  }

  @Test
  void 판매_정산_계좌_수정_성공_테스트() {
    SalesAccountDTO salesAccount =
        SalesAccountDTO.builder().userId(1L).bankName("test").accountNumber("12345").build();

    given(salesAccountMapper.updateSalesAccount(salesAccount)).willReturn(1);

    assertDoesNotThrow(() -> salesAccountService.updateSalesAccount(salesAccount));

    verify(salesAccountMapper, times(1)).updateSalesAccount(salesAccount);
  }

  @Test
  void 판매_정산_계좌_수정_실패_테스트() {

    SalesAccountDTO salesAccount =
        SalesAccountDTO.builder().userId(1L).bankName("test").accountNumber("12345").build();

    given(salesAccountMapper.updateSalesAccount(salesAccount)).willReturn(0);

    assertThrows(
        SalesAccountNotFoundException.class,
        () -> {
          salesAccountService.updateSalesAccount(salesAccount);
        });

    verify(salesAccountMapper, times(1)).updateSalesAccount(salesAccount);
  }
}
