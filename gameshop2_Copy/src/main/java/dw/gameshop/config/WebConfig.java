package dw.gameshop.config;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Override
    public void addCorsMappings(CorsRegistry registry){
        registry.addMapping("/**")
                .allowedOrigins("http://127.0.0.1:5500/", "http://localhost:81"
                        , "http://192.168.0.169:81")
                .allowedMethods("GET","POST","PUT","DELETE","OPTIONS")
                .allowCredentials(true); //위의 SecurityConfig와 연결된것. 이것을 풀어줘야 인증정보가 필요한 컨텐츠에도 접근이 가능하다.
    }
    @Value("${file.upload-dir}")
    private String uploadDir;

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/uploads/**")
                .addResourceLocations("file:" + uploadDir + "/");
    }
}
