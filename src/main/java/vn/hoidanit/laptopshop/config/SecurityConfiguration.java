package vn.hoidanit.laptopshop.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import jakarta.servlet.DispatcherType;
import vn.hoidanit.laptopshop.service.CustomUserDetailsService;
import vn.hoidanit.laptopshop.service.UserService;

@Configuration
@EnableMethodSecurity(securedEnabled = true)
public class SecurityConfiguration {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

     @Bean 
    public UserDetailsService userDetailsService(UserService userService) { 
        return new CustomUserDetailsService(userService); 
    } 
 
    // day la method tong hop tat ca cac hoat dong cua security    
      @Bean 
    public DaoAuthenticationProvider authProvider( 
            PasswordEncoder passwordEncoder, 
            UserDetailsService userDetailsService) { 
 
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider(); 
        authProvider.setUserDetailsService(userDetailsService); 
        authProvider.setPasswordEncoder(passwordEncoder); 
        authProvider.setHideUserNotFoundExceptions(false); 
        return authProvider; 
    }

    @Bean
    public AuthenticationSuccessHandler customSuccessHandler(){
        return new CustomSuccessHandler();
    }


    //v6. lamda
     @Bean 
    SecurityFilterChain filterChain(HttpSecurity http) throws Exception { 
        http 
                .authorizeHttpRequests(authorize -> authorize 
                .dispatcherTypeMatchers(DispatcherType.FORWARD, 
                DispatcherType.INCLUDE) .permitAll()
                .requestMatchers("/","/login","/product/**", "/client/**", "/css/**", "/js/**", 
                "/images/**")
                .permitAll()

                .requestMatchers("/admin/**")
                .hasRole("ADMIN")

                .anyRequest().authenticated()) 
                //bay h cai login cua t thi m se vao cai url nay, neu fail thi nha ra error cho t
                .formLogin(formLogin -> formLogin 
                        .loginPage("/login") 
                        .failureUrl("/login?error") 
                        .successHandler(customSuccessHandler())
                        .permitAll())
                    .exceptionHandling(ex -> ex.accessDeniedPage("/access-deny")); 
 
        return http.build(); 
    }

}
