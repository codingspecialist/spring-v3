package org.example.springv3.user;

import lombok.RequiredArgsConstructor;
import org.example.springv3.core.error.ex.Exception400;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class UserService {
    private final UserRepository userRepository;
    private final UserQueryRepository userQueryRepository;

    @Transactional
    public void 회원가입(UserRequest.JoinDTO joinDTO) {

        User userPs= userRepository.findByUsername(joinDTO.getUsername());

        if(userPs != null) {
            throw new Exception400("이미 존재하는 유저입니다.");
        }

        userRepository.save(joinDTO.toEntity());

    }
}
