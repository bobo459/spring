package dw.gameshop.config;

import dw.gameshop.exception.MyAccessDeniedHandler;
import dw.gameshop.exception.MyAuthenticationEntryPoint;
import dw.gameshop.service.UserDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
//환경설정 파일
@Configuration
@EnableWebSecurity
public class SecurityConfig {
    @Autowired
    private UserDetailService userDetailService;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        return http
                .authorizeRequests(auth -> auth
                        .requestMatchers( //허용할 주소지를 다 적어줘야한다. 개발중에는 **개 넣고 다 열린상태로 개발하고 나중에 닫아주기
                                //인증이 필요없는 페이지를 만들어준것.
                                new AntPathRequestMatcher("/products/**"),
                                new AntPathRequestMatcher("/user/login"),  // "/user/*" 유저까지만 허용해줄게."/user/**" 유저의 자식들까지 허용하겠다
                                new AntPathRequestMatcher("/user/signup"),
                                new AntPathRequestMatcher("/login")  //스택틱 페이지
                        ).permitAll()  //permitAll() : 모두허용하겠다
                        .anyRequest().authenticated()) //어떠한 요청이든 모두 인증 받겠다.
                .formLogin(form->form.loginPage("/login").defaultSuccessUrl("/articles"))  //formLogin : 정적로그인 페이지가 존재할 경우사용 ,
                                // form.loginPage("/login") 로그인 단독창, defaultSuccessUrl("/articles")로그인이 성공하면 이쪽 페이지로 이동시킨다.
                .sessionManagement(session -> session  //JWT 사용하면 이건 필요없음. sessionManagement: 세선 ID를 사용할때 사용하는 구절
                        .sessionCreationPolicy(SessionCreationPolicy.ALWAYS))
                .csrf(AbstractHttpConfigurer::disable)  // csrf (Cross-Site Request Forgery) : 다른 사이트에서 특정사이트를 접속하는 것.예시)나는 네이버에 접속했는데, 실제로는 네이버가 쿠팡으로 무언가로 보내는상황(가짜은행사이트-해킹)
                                                         //더 좋은 보안을 사용할 예정이기에 disable을 붙여서 사용하지 않겠다고 알려줌
                .exceptionHandling(exception -> exception
                        .authenticationEntryPoint(new MyAuthenticationEntryPoint())  //authenticationEntryPoint :인증 실패일때,MyAuthenticationEntryPoint에서 처리해주겠다. 우리는 성공의 시나리오만 짜면된다.
                        .accessDeniedHandler(new MyAccessDeniedHandler()))   //accessDeniedHandler : 권한 실패 시
                .build();
    }

    @Bean
    public AuthenticationManager authenticationManager(HttpSecurity http, BCryptPasswordEncoder bCryptPasswordEncoder, UserDetailService userDetailService) throws Exception {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailService);
        authProvider.setPasswordEncoder(bCryptPasswordEncoder);
        return new ProviderManager(authProvider);
    }

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }
}   //BCrypt : 유명한 암호화 방식 중 하나. 암호화 해시 함수
                //기본적으로 디코딩이 불가능한 암호화 파일, 원상복귀가 불가능하다. 인코딩보다 강력하다. 역으로 풀어낼수가 없다.
