package com.ukream.mapper;

import com.ukream.dto.AddressDTO;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface AddressMapper {
  public void createAddress(AddressDTO address);

  public List<AddressDTO> getAddresses(Long userId);

  public AddressDTO getAddress(Long addressId,Long userId);

  public int deleteAddress(Long addressId);

  public int updateAddress(AddressDTO address);
}
