package com.cursor.hw17.config;

import com.cursor.hw17.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.RememberMeAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.rememberme.InMemoryTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;
import org.springframework.security.web.authentication.rememberme.RememberMeAuthenticationFilter;
import org.springframework.security.web.authentication.rememberme.TokenBasedRememberMeServices;

import javax.servlet.http.HttpServletRequest;
import javax.sql.DataSource;

@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig  extends WebSecurityConfigurerAdapter {

    private final UserService userService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf()
                    .disable() //todo explain why csrf disabled
                .authorizeRequests()
                    .antMatchers("/users/login").anonymous()
                    .antMatchers("/users/dummy").permitAll().
                anyRequest()
                    .authenticated()
                .and()
                    .exceptionHandling().disable() //todo show what changes
                .formLogin()
                .and()
                .rememberMe()
                .rememberMeServices(tokenBasedRememberMeService())
//                    .tokenValiditySeconds(1000)
//                    .tokenRepository(persistentTokenRepository())
//                    .alwaysRemember(true)
//                    .rememberMeCookieName("remeber-me")
//                    .userDetailsService(userService)
                // cookies support
                //.tokenRepository(new TokenBasedRememberMeServices())
                .and()
                    .sessionManagement()
                    .sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED);

    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userService);
    }

    @Bean
    public PersistentTokenRepository persistentTokenRepository() {
        return new InMemoryTokenRepositoryImpl();
    }

    @Autowired
    DataSource dataSource;


    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .userDetailsService(userService)
                .passwordEncoder(passwordEncoder());
        auth.authenticationProvider(rememberMeAuthenticationProvider());
    }

    @Bean
    public RememberMeAuthenticationProvider rememberMeAuthenticationProvider() {
        return new RememberMeAuthenticationProvider("remember-me");
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    public class MyTokenBasedRememberMeService extends TokenBasedRememberMeServices {



        public MyTokenBasedRememberMeService(String key, UserDetailsService userDetailsService) {
            super(key, userDetailsService);
        }

        @Override
        protected String extractRememberMeCookie(HttpServletRequest request) {
            String token = request.getHeader(TOKEN_STRING);
            if ((token == null) || (token.length() == 0)) {
                return "";
            }
            return token;
        }
    }

    @Bean
    public TokenBasedRememberMeServices tokenBasedRememberMeService() {
        MyTokenBasedRememberMeService service = new MyTokenBasedRememberMeService(TOKEN_STRING, userService);
        service.setAlwaysRemember(true);
        service.setCookieName(TOKEN_STRING);
        return service;
    }

    @Bean
    public RememberMeAuthenticationFilter rememberMeAuthenticationFilter() throws Exception {
        return new RememberMeAuthenticationFilter(authenticationManager(), tokenBasedRememberMeService());
    }

    private final static String TOKEN_STRING = "my_token";
}
