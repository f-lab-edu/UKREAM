package com.ukream.mapper;

import com.ukream.dto.LoginFormDTO;
import com.ukream.dto.UserDTO;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.session.RowBounds;

@Mapper
public interface AdminMapper {

  public void createAdmin(UserDTO user);

  public List<UserDTO> getUsers(RowBounds rowBounds);

  public UserDTO getUser(Long userId);
  
  public UserDTO findByEmailAndPassword(LoginFormDTO input);
}
