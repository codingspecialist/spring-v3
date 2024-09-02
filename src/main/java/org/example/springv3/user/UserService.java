package org.example.springv3.user;

import lombok.RequiredArgsConstructor;
import org.example.springv3.core.error.ex.Exception401;
import org.example.springv3.core.error.ex.Exception400;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    @Transactional
    public void 회원가입(UserRequest.JoinDTO joinDTO) {

        User userPs= userRepository.findByUsername(joinDTO.getUsername());

        if(userPs != null) {
            throw new Exception400("이미 존재하는 유저입니다.");
        }

        userRepository.save(joinDTO.toEntity());

    }
}
