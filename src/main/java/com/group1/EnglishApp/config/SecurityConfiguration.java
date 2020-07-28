package com.group1.EnglishApp.config;

import com.group1.EnglishApp.filter.AuthenticationTokenFilter;
import com.group1.EnglishApp.security.*;
import com.group1.EnglishApp.service.UserDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;
import java.util.List;
/**
 * @author Hai Dang
 */
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
@Order(value = 101)
public class SecurityConfiguration extends WebSecurityConfigurerAdapter{
    @Autowired
    private UserDetailService userDetailService;
    @Autowired
    private RestAuthenticationEntryPoint authenticationEntryPoint;
    @Autowired
    private RestAuthenticationSuccessHandler restAuthenticationSuccessHandler;
    @Autowired
    private RestAuthenticationFailureHandler restAuthenticationFailureHandler;
    @Autowired
    private RestAccessDeniedHandler restAccessDeniedHandler;
    @Autowired
    private TokenAuthenticationProvider tokenAuthenticationProvider;

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder authenticationManager) throws Exception {
        authenticationManager
                .userDetailsService(userDetailService)
                .passwordEncoder(passwordEncoderBean());
    }

    @Bean
    public PasswordEncoder passwordEncoderBean() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager tokenAuthenticationManager() {
        List<AuthenticationProvider> providerList = Arrays.asList(tokenAuthenticationProvider);
        AuthenticationManager authenticationManager = new ProviderManager(providerList);
        return authenticationManager;
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        AuthenticationManager authenticationManager = super.authenticationManager();
        return authenticationManager;
    }

    @Bean
    CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(Arrays.asList("*"));
        configuration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE", "OPTIONS"));
        configuration.setAllowedHeaders(Arrays.asList("Access-Control-Allow-Headers","Access-Control-Allow-Origin","Access-Control-Request-Method", "Access-Control-Request-Headers", "Authorization", "Content-Type", "Cache-Control", "X-Requested-With"));
        configuration.setAllowCredentials(true);
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }

    @Override
    public void configure(WebSecurity webSecurity) throws Exception {
        webSecurity
                .ignoring()
                .antMatchers("/rest-api/login")
                .antMatchers("/rest-api/user/register");
    }

    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                // we don't need CSRF because our token is invulnerable
                .csrf().disable()
                .anonymous().disable()
                .cors().configurationSource(corsConfigurationSource())
                .and()
                .exceptionHandling().authenticationEntryPoint(authenticationEntryPoint)
                .and()
                .exceptionHandling().accessDeniedHandler(restAccessDeniedHandler)
                .and()
                // don't create session
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                // secure only these paths
                .requestMatchers().antMatchers("/rest-api/**")
                .and()
                .authorizeRequests()
                // Un-secure
                .antMatchers("/rest-api/login", "/v2/api-docs", "/configuration/**", "/swagger*/**", "/webjars/**")
                .permitAll()
                .antMatchers("/rest-api/**")
                .authenticated();

        // Custom JWT based security filter
        httpSecurity
                .addFilterBefore(authenticationTokenFilter(), UsernamePasswordAuthenticationFilter.class);

        // disable X-Frame-Options
        httpSecurity
                .headers()
                .frameOptions().disable()
                .cacheControl();
    }

    /**
     *
     * @return
     */
    private AuthenticationTokenFilter authenticationTokenFilter() {
        AuthenticationTokenFilter authenticationTokenFilter = new AuthenticationTokenFilter();
        authenticationTokenFilter.setAuthenticationManager(tokenAuthenticationManager());
        authenticationTokenFilter.setAuthenticationSuccessHandler(restAuthenticationSuccessHandler);
        authenticationTokenFilter.setAuthenticationFailureHandler(restAuthenticationFailureHandler);
        return authenticationTokenFilter;
    }
}
