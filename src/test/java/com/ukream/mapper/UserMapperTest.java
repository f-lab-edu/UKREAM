package com.ukream.mapper;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import com.ukream.dto.UserDTO;

@SpringBootTest
public class UserMapperTest {
    
    @Autowired
    private UserMapper userMapper;

    @Test
    public void 중복된_이메일_체크_테스트(){
        String email = "test";
        int result = userMapper.emailCheck(email);
        // 중복된 이메일이면 1을 반환하는지 검증
        assertEquals(1, result);
    }

    @Test
    public void 이메일_체크_테스트(){
        String email = "test55";
        int result = userMapper.emailCheck(email);
        // 0을 반환하는지 검증
        assertEquals(0, result);
    }

    @Test
    public void 유저_생성_테스트(){
        UserDTO user = UserDTO.builder()
                        .email("test52")
                        .password("1234")
                        .build();
        userMapper.createUser(user);

        int result = userMapper.emailCheck(user.getEmail());
        // 생성되었는지 검증
        assertEquals(1, result);
    }
}
