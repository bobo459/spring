package dw.gameshop.controller;

import dw.gameshop.dto.BaseResponse;
import dw.gameshop.dto.SessionDto;
import dw.gameshop.dto.UserDto;
import dw.gameshop.enumstatus.ResultCode;
import dw.gameshop.service.UserDetailService;
import dw.gameshop.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user")
public class UserController {
    private UserService userService;
    private UserDetailService userDetailService;
    private AuthenticationManager authenticationManager;
    private HttpServletRequest httpServletRequest;

    public UserController(UserService userService, UserDetailService userDetailService, AuthenticationManager authenticationManager, HttpServletRequest httpServletRequest) {
        this.userService = userService;
        this.userDetailService = userDetailService;
        this.authenticationManager = authenticationManager;
        this.httpServletRequest = httpServletRequest;
    }
//레스트 이피아이를 컨트롤하는 창. 포스트맨으로 조정함.
//    @PostMapping("signup")
//    public ResponseEntity<String> signUp(@RequestBody UserDto userDto) {  //UserDto : 유저네임,유저아이디,유저이메일..등등을 받고 있다.
//        return new ResponseEntity<>(userService.saveUser(userDto), //saveUser 데이터 베이스를 사용해야해서 왼쪽으로 패스
//                HttpStatus.CREATED);
//    }

    @PostMapping("/signup")
    public ResponseEntity<BaseResponse<String>> signUp(@Valid @RequestBody UserDto userDto) {
        return new ResponseEntity<>(
                new BaseResponse(ResultCode.SUCCESS.name(),
                        userService.saveUser(userDto),
                        ResultCode.SUCCESS.getMsg())
                , HttpStatus.CREATED);
    }

    @PostMapping("login")  //viewcontroller에서의 login과는 다른친구 같으면 에러가 난다.
    public ResponseEntity<BaseResponse<Void>> login(@RequestBody UserDto userDto, //스프링 시큐리티에서 일을 시키고 있어서 앞에서 일을 한다. 대부분 이렇게 사용.빨리 처리해야해서
                                                     HttpServletRequest request) {
        Authentication authentication = authenticationManager.authenticate( //authentication : 받은 토큰을 가지고 authentication을 만들고 유저디테일 받을 정보를 ->유저디테일서비스...가서...뭐지...성공하면 시그니처로 안되면 예외처리를 해준다.
                new UsernamePasswordAuthenticationToken(userDto.getUserId(), userDto.getPassword())
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);  //성공했을때 시크리티콘테스트에 저장

        //포스트맨에서 localhost:8080/user/current get하면 에러나는데 아래 4줄을 써야 섹션이 자동으로 생성되서 인지되게 만들어준다.
        //세션 생성
        HttpSession session = request.getSession(true);  //true : 세션이 없으면 새로 생성
        //세션에 인증 객체 저장
        session.setAttribute(HttpSessionSecurityContextRepository.SPRING_SECURITY_CONTEXT_KEY,
                SecurityContextHolder.getContext());


//        return ResponseEntity.ok("Success");  //실패시 예외처리
        return new ResponseEntity<>(
                new BaseResponse(ResultCode.SUCCESS.name(),
                        null,
                        ResultCode.SUCCESS.getMsg())
                , HttpStatus.OK);
    }

    @PostMapping("/logout")
    public ResponseEntity<BaseResponse<String>> logout(HttpServletRequest request, HttpServletResponse response) { //request 클라이언트에서 ,response 서버(톰켓?)에서 불러오는것.
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.invalidate();
        }
        return new ResponseEntity<>(
                new BaseResponse(ResultCode.SUCCESS.name(),
                        "You have been logged out.",
                        ResultCode.SUCCESS.getMsg())
                , HttpStatus.OK);
    }



    @GetMapping("/current")
    public ResponseEntity<BaseResponse<SessionDto>> getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || !authentication.isAuthenticated()) {
            throw new IllegalStateException("User is not authenticated");
        }
        SessionDto sessionDto = new SessionDto();
        sessionDto.setUserId(authentication.getName());
        sessionDto.setAuthority(authentication.getAuthorities());

        return new ResponseEntity<>(
                new BaseResponse(ResultCode.SUCCESS.name(),
                        sessionDto,
                        ResultCode.SUCCESS.getMsg())
                , HttpStatus.OK);
    }
}
