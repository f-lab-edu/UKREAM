package com.ukream.mapper;

import com.ukream.dto.UserDTO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper {

  public int emailCheck(String email);

  public void createUser(UserDTO user);
  
}
