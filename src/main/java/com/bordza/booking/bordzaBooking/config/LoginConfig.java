package com.bordza.booking.bordzaBooking.config;

import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

@EnableWebSecurity
public class LoginConfig extends WebSecurityConfigurerAdapter {


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                    .antMatchers("/calendar").permitAll()
                    .antMatchers("/index").authenticated()
                .and()
                .formLogin()
                .and()
                .httpBasic()
                ;

//        http.logout().logoutUrl("/my/logout")
//                .logoutSuccessUrl("/my/index")
//                .logoutSuccessHandler(logoutSuccessHandler)
//                .invalidateHttpSession(true);
//                .addLogoutHandler(logoutHandler)
//                .deleteCookies(cookieNamesToClear);
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        PasswordEncoder encoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
        auth.inMemoryAuthentication().withUser("user").password(encoder.encode("password")).roles("");
    }

}
