package com.ukream.service;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.BDDMockito.*;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import com.ukream.dto.AddressDTO;
import com.ukream.error.exception.AddressNotFoundException;
import com.ukream.mapper.AddressMapper;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class AddressServiceTest {

  @Mock private AddressMapper addressMapper;

  @InjectMocks private AddressService addressService;

  @Test
  void 주소_생성_테스트() {
    AddressDTO address =
        AddressDTO.builder().name("test").address("addressTest").zipcode("12345").build();

    doNothing().when(addressMapper).createAddress(address);

    assertDoesNotThrow(() -> addressService.createAddress(address));

    verify(addressMapper, times(1)).createAddress(address);
  }

  @Test
  void 주소_목록_조회_테스트() {
    Long userId = 1L;

    AddressDTO address =
        AddressDTO.builder().name("test").address("addressTest").zipcode("12345").build();

    List<AddressDTO> addressList = Arrays.asList(address);

    given(addressMapper.getAddressList(userId)).willReturn(addressList);

    List<AddressDTO> result = assertDoesNotThrow(() -> addressService.getAddressList(userId));

    assertEquals(addressList, result);

    verify(addressMapper, times(1)).getAddressList(userId);
  }

  @Test
  void 주소_조회_성공_테스트() {
    Long addressId = 1L;
    Long userId = 1L;

    AddressDTO address =
        AddressDTO.builder().name("test").address("addressTest").zipcode("12345").build();

    given(addressMapper.getAddress(addressId, userId)).willReturn(address);

    AddressDTO result = assertDoesNotThrow(() -> addressService.getAddress(addressId, userId));

    assertEquals(address, result);

    verify(addressMapper, times(1)).getAddress(addressId, userId);
  }

  @Test
  void 주소_조회_실패_테스트() {
    Long addressId = 1L;
    Long userId = 1L;

    given(addressMapper.getAddress(addressId, userId)).willReturn(null);

    assertThrows(
        AddressNotFoundException.class,
        () -> {
          addressService.getAddress(addressId, userId);
        });

    verify(addressMapper, times(1)).getAddress(addressId, userId);
  }

  @Test
  void 주소_삭제_성공_테스트() {
    Long addressId = 1L;

    given(addressMapper.deleteAddress(addressId)).willReturn(1);

    assertDoesNotThrow(() -> addressService.deleteAddress(addressId));

    verify(addressMapper, times(1)).deleteAddress(addressId);
  }

  @Test
  void 주소_삭제_실패_테스트() {
    Long addressId = 1L;

    given(addressMapper.deleteAddress(addressId)).willReturn(0);

    assertThrows(
        AddressNotFoundException.class,
        () -> {
          addressService.deleteAddress(addressId);
        });

    verify(addressMapper, times(1)).deleteAddress(addressId);
  }

  @Test
  void 주소_수정_성공_테스트() {
    AddressDTO address =
        AddressDTO.builder()
            .addressId(1L)
            .name("test")
            .address("addressTest")
            .zipcode("12345")
            .build();

    given(addressMapper.updateAddress(address)).willReturn(1);

    assertDoesNotThrow(() -> addressService.updateAddress(address));

    verify(addressMapper, times(1)).updateAddress(address);
  }

  @Test
  void 주소_수정_실패_테스트() {

    AddressDTO address =
        AddressDTO.builder()
            .addressId(1L)
            .name("test")
            .address("addressTest")
            .zipcode("12345")
            .build();

    given(addressMapper.updateAddress(address)).willReturn(0);

    assertThrows(
        AddressNotFoundException.class,
        () -> {
          addressService.updateAddress(address);
        });

    verify(addressMapper, times(1)).updateAddress(address);
  }
}
