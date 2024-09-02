package org.example.springv3.user;

import lombok.RequiredArgsConstructor;
import org.example.springv3.core.error.ex.Exception401;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UserService {
    private final UserRepository userRepository;

    public User 로그인(UserRequest.LoginDTO loginDTO) {
        User user = userRepository.findByUsernameAndPassword(loginDTO.getUsername(), loginDTO.getPassword());
        try{
            return user;
        }catch (Exception e){
            throw new Exception401("인증되지 않았습니다");
        }
    }
}
