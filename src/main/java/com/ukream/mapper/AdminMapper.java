package com.ukream.mapper;

import com.ukream.dto.LoginFormDTO;
import com.ukream.dto.UserDTO;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface AdminMapper {

  public void createAdmin(UserDTO user);

  public List<UserDTO> getUsers();

  public UserDTO getUser(Long userId);
  
  public UserDTO findByEmailAndPassword(LoginFormDTO input);
}
