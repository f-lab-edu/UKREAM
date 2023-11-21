package com.ukream.mapper;

import com.ukream.dto.AddressDTO;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface AddressMapper {
  public void createAddress(AddressDTO address);

  public List<AddressDTO> getAddress(Long userId);

  public AddressDTO getAddressByIdAndUserId(Long addressId,Long userId);
}
