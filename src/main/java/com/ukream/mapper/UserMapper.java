package com.ukream.mapper;

import com.ukream.dto.LoginFormDTO;
import com.ukream.dto.UserDTO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper {

  public int checkDuplicatedEmail(String email);

  public void createUser(UserDTO user);


  public UserDTO findByEmailAndPassword(LoginFormDTO user);
}
