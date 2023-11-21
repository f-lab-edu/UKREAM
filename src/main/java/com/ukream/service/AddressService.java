package com.ukream.service;

import com.ukream.dto.AddressDTO;
import com.ukream.dto.UserDTO;
import com.ukream.error.exception.AddressNotFoundException;
import com.ukream.error.exception.UserNotFoundException;
import com.ukream.mapper.AddressMapper;
import com.ukream.mapper.UserMapper;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AddressService {
  private final AddressMapper addressMapper;
  private final UserMapper userMapper;

  public void createAddress(AddressDTO address, Long userId) {
    UserDTO user = userMapper.getUserInfo(userId);
    if (user == null) {
      throw new UserNotFoundException();
    }
    address.setUserId(userId);
    addressMapper.createAddress(address);
  }

  public List<AddressDTO> getAddress(Long userId) {
    return addressMapper.getAddress(userId);
  }

  public AddressDTO getAddressByIdAndUserId(Long addressId, Long userId) {
    AddressDTO address = addressMapper.getAddressByIdAndUserId(addressId, userId);
    if (address == null) {
      throw new AddressNotFoundException();
    }
    return address;
  }
}
