package com.example.pathfinderdemo.config;

import com.example.pathfinderdemo.model.entity.enums.RoleEnum;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
public class ApplicationSecurityConfiguration extends WebSecurityConfigurerAdapter {

    private final UserDetailsService userDetailsService;
    private final PasswordEncoder passwordEncoder;

    public ApplicationSecurityConfiguration(UserDetailsService userDetailsService, PasswordEncoder passwordEncoder) {
        this.userDetailsService = userDetailsService;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()

                //allow access to all static resources
                .requestMatchers(PathRequest.toStaticResources()

                        .atCommonLocations())
                .permitAll()


                //.antMatchers("/img/**").permitAll()

                //allow access to all users
                .antMatchers("/", "/users/login", "/users/register").permitAll()
                .antMatchers("routes/api/**").permitAll()

                //.antMatchers("/statistics").hasRole(RoleEnum.ADMIN.name())

                //forbid access for unauthenticated users
                .antMatchers("/**").authenticated()

                .and()

                //configure login with login HTML form with two inputs field: username and password
                .formLogin()

                //our login page is located here: "http://localhost:8080/users/login"
                .loginPage("/users/login")

                //this is the name of <inputs> in the login form where the user enter her email/password
                //the values of this input will be presented to our user details
                // ima default_values - username password
                //those that want to name input field differently, e.g. email may change the value
                .usernameParameter("email")
                .passwordParameter(UsernamePasswordAuthenticationFilter.SPRING_SECURITY_FORM_PASSWORD_KEY)
                //the place if success  login
                .defaultSuccessUrl("/")
                //the place of unsuccess login
                .failureForwardUrl("/users/login-error")

                .and()
                .logout()
                .logoutUrl("/users/logout")

                //whte go to after success logout
                .logoutSuccessUrl("/")

                //remove the session from server
                .invalidateHttpSession(true)
                //delete the cookies that references my session
                .deleteCookies("JSESSIONID");
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        //this gives to spring 2 impotent components:
        //1. our user details service that translate username/email/ phoneNumber etc. to UserDetails
        //2. Password encoder -can decide if the user password matches
        auth.userDetailsService(userDetailsService)

//registration:
                // topSecretPass-> password encoder - > hashed_pswd (as.fjsldfjsljdf) - ??????????????????, ???? ?????????? ???? ???????? ???? ???? ???????????????????? ????????????????

                // ?????????? (username, rowPassword)
                // password_encoder.matches(row_password, hashed_pswd)
                .passwordEncoder(passwordEncoder);

    }
}
