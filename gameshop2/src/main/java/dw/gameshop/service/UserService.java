package dw.gameshop.service;

import dw.gameshop.dto.UserDto;
import dw.gameshop.model.Authority;
import dw.gameshop.model.User;
import dw.gameshop.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@Transactional
public class UserService {
    private UserRepository userRepository;
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public UserService(UserRepository userRepository, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userRepository = userRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    public String saveUser(UserDto userDto) {
//        Optional<User> userOptional = userRepository.findByUserId(userDto.getUserId());
//        if(userOptional.isPresent()){
//            return "이미 등록된 아이디 입니다.";
//        }
        Authority authority = new Authority();
        authority.setAuthorityName("ROLE_USER");
        User user = new User(userDto.getUserId(),
                userDto.getUserName(),
                userDto.getUserEmail(),
                bCryptPasswordEncoder.encode(userDto.getPassword()),  //비번 또는 민증번호, 카드번호 등 개인정보 저장시점에 패스워드로 암호화 시켜준다.
                authority,
                LocalDateTime.now());
        return userRepository.save(user).getUserId();

    }
}
