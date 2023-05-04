package org.resources.restmanager.security;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    private JWTAuthEntryPoint authEntryPoint;
    private CustomUserDetailsService userDetailsService;


    @Autowired
    public SecurityConfig(CustomUserDetailsService userDetailsService, JWTAuthEntryPoint authEntryPoint){
        this.userDetailsService = userDetailsService;
        this.authEntryPoint = authEntryPoint;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
        http
                .cors()
                .and()
                .csrf().disable()
                .exceptionHandling()
                .authenticationEntryPoint(authEntryPoint)
                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeRequests()
                .requestMatchers("/Resource-Managment/auth/**").permitAll()
                .requestMatchers("/Recources-Managment/demands/**","/Recources-Managment/*/demands").hasAuthority("DIRECTOR")
                .requestMatchers("/Recources-Managment/teachers/").hasAnyAuthority("DIRECTOR","TECHNICIAN")
                .requestMatchers("/Recources-Managment/notifications").hasAnyAuthority("DIRECTOR","TEACHER")
                .requestMatchers("/responsable/**").hasAnyAuthority("MANAGER")
                .requestMatchers("/responsable/liste-ordinateurs/**").hasAnyAuthority("MANAGER")
                .requestMatchers("/responsable/liste-imprimantes/**").hasAnyAuthority("MANAGER")
                .requestMatchers("/responsable/ordinateur/**").hasAnyAuthority("MANAGER")
                .requestMatchers("/responsable/imprimante/**").hasAnyAuthority("MANAGER")
                .requestMatchers("/responsable/offres/**").hasAnyAuthority("MANAGER")
                .requestMatchers("/responsable/ressources/**").hasAnyAuthority("MANAGER")
                .requestMatchers("/enseignant/**").permitAll()
                .anyRequest().authenticated()
                .and()
                .httpBasic();

        http
                .addFilterBefore(jwtAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }


    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception{
        return configuration.getAuthenticationManager();
    }


    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public JWTAuthenticationFilter jwtAuthenticationFilter(){
        return new JWTAuthenticationFilter();
    }
}
