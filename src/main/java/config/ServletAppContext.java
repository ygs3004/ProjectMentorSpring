package config;
import common.LoggerInterceptor;
import dao.UserDao;
import domain.User;
import interceptor.CheckLoginInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.support.StandardServletMultipartResolver;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.web.servlet.config.annotation.*;
import javax.annotation.Resource;

// Spring MVC 프로젝트에 관련된 설정을 하는 클래스
@Configuration
// Controller 어노테이션이 셋팅되어 있는 클래스를 Controller로 등록한다.
@EnableWebMvc
//스캔할 패키지를 지정한다.
@ComponentScan(basePackages = {"controller", "common"})
public class ServletAppContext implements WebMvcConfigurer {

    @Resource(name = "loginUserBean")
    private User loginUserBean;

    @Autowired
    private UserDao userDao;

    // Controller의 메서드가 반환하는 jsp의 이름 앞뒤에 확장자를 붙여주도록 한다.
    @Override
    public void configureViewResolvers(ViewResolverRegistry registry){
        WebMvcConfigurer.super.configureViewResolvers(registry);
        registry.jsp("/WEB-INF/views/", ".jsp");
    }

    // 정적 파일의 경로를 매핑한다.
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry){
        WebMvcConfigurer.super.addResourceHandlers(registry);
        registry.addResourceHandler("/**").addResourceLocations("/resources/");
    }

    @Bean
    public ReloadableResourceBundleMessageSource messageSource() {
        ReloadableResourceBundleMessageSource res = new ReloadableResourceBundleMessageSource();
        res.setBasenames("/WEB-INF/properties/error_message");
        return res;
    }

    @Bean
    public MultipartResolver multipartResolver() {
        StandardServletMultipartResolver resolver = new StandardServletMultipartResolver();
        return resolver;
    }

    //@PropertySource 를 붙여 프로퍼티 파일을 로드하려면 정의해야 하는 @Bean
    //특이점은 스프링 버전 5.0.5.RELEASE에서 이 Bean을 설정하지 않아도 정상적으로 동작
    //ReloadableResourceBundleMessageSource을 등록했을시 @Value가 properties 값을 제대로 불러오지 못했고, 이 빈을 등록한 후 정상 동작
    @Bean
    public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
        return new PropertySourcesPlaceholderConfigurer();
    }

//    @Bean("loginUserBean")
//    @SessionScope
//    public User loginUserBean() {
//        return new User();
//    }



    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // TODO Auto-generated method stub
        WebMvcConfigurer.super.addInterceptors(registry);

        CheckLoginInterceptor checkLoginInterceptor = new CheckLoginInterceptor(loginUserBean);
        LoggerInterceptor loggerInterceptor = new LoggerInterceptor();
//        UpdateUserStatus updateUserStatus = new UpdateUserStatus(loginUserBean, userDao);

        InterceptorRegistration reg1 = registry.addInterceptor(checkLoginInterceptor);
        InterceptorRegistration reg2 = registry.addInterceptor(loggerInterceptor);
        reg1.addPathPatterns("/user/login_pro");
        reg2.addPathPatterns("/**");
//
//        InterceptorRegistration reg2 = registry.addInterceptor(updateUserStatus);
//        reg2.addPathPatterns("/MyStudy/**", "/MentorRoom/**");

    }


}