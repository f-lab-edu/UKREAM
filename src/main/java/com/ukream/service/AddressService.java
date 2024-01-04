package com.ukream.service;

import com.ukream.dto.AddressDTO;
import com.ukream.error.exception.AddressNotFoundException;
import com.ukream.mapper.AddressMapper;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AddressService {
  private final AddressMapper addressMapper;

  public void createAddress(AddressDTO address) {
    addressMapper.createAddress(address);
  }

  public List<AddressDTO> getAddresses(Long userId) {
    return addressMapper.getAddresses(userId);
  }

  public AddressDTO getAddress(Long addressId, Long userId) {
    AddressDTO address = addressMapper.getAddress(addressId, userId);
    if (address == null) {
      throw new AddressNotFoundException();
    }
    return address;
  }

  public void deleteAddress(Long addressId) {
    int deletedRows = addressMapper.deleteAddress(addressId);
    if (deletedRows == 0) {
      throw new AddressNotFoundException();
    }
  }

  public void updateAddress(AddressDTO address) {
    int updatedRows = addressMapper.updateAddress(address);
    if (updatedRows == 0) {
      throw new AddressNotFoundException();
    }
  }
}
