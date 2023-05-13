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
                .requestMatchers("/Recources-Managment/teachers/").hasAnyAuthority("DIRECTOR","TECHNICIAN", "MANAGER")
                .requestMatchers( "/Recources-Managment/departments").hasAnyAuthority("DIRECTOR","TECHNICIAN","MANAGER")
                .requestMatchers("/Resource-Managment/failures").hasAnyAuthority("TECHNICIAN")
                .requestMatchers("/Recources-Managment/notifications").hasAnyAuthority("DIRECTOR","TEACHER")
                .requestMatchers("/Recources-Managment/send-demands").hasAnyAuthority("DIRECTOR")
                .requestMatchers("/responsable/**").hasAnyAuthority("MANAGER")
                .requestMatchers("/responsable/liste-ordinateurs/**").hasAnyAuthority("MANAGER")
                .requestMatchers("/responsable/liste-imprimantes/**").hasAnyAuthority("MANAGER")
                .requestMatchers("/responsable/ordinateur/**").hasAnyAuthority("MANAGER")
                .requestMatchers("/responsable/imprimante/**").hasAnyAuthority("MANAGER")
                .requestMatchers("/responsable/offres/**","/responsable/offres/accepterSoumission/**").hasAnyAuthority("MANAGER")
                .requestMatchers("/responsable/ressources/**").hasAnyAuthority("MANAGER")
                //alex
                .requestMatchers("/responsable/affectation/**").hasAnyAuthority("MANAGER")
                .requestMatchers("/responsable/add-affectation").hasAnyAuthority("MANAGER")
                .requestMatchers("/responsable/update-affectation/**").hasAnyAuthority("MANAGER")
                // ayyoub mimoune
                .requestMatchers("/Recources-Managment/enseignant/**").hasAnyAuthority("TEACHER")
                .requestMatchers("/Recources-Managment/enseignant/liste-demandes/**").hasAnyAuthority("TEACHER")
                .requestMatchers("/Recources-Managment/enseignant/liste-ordinateurs/**").hasAnyAuthority("TEACHER")
                .requestMatchers("/Recources-Managment/enseignant/liste-imprimantes/**").hasAnyAuthority("TEACHER")
                .requestMatchers("/Recources-Managment/enseignant/signaler-panne/**").hasAnyAuthority("TEACHER")
                .requestMatchers("/Recources-Managment/fournisseur/ajouter-soumission/**").hasAnyAuthority("PROVIDER")
                .requestMatchers("/Recources-Managment/fournisseur/modifier-soumission").hasAnyAuthority("PROVIDER")
                .requestMatchers("/Recources-Managment/fournisseur/supprimer-soumission/**").hasAnyAuthority("PROVIDER")
                .requestMatchers("/Recources-Managment/fournisseur/liste-soumissions/**").hasAnyAuthority("PROVIDER")
                .requestMatchers("/Recources-Managment/fournisseur/liste-offres").hasAnyAuthority("PROVIDER")
                .requestMatchers("/Recources-Managment/fournisseur/liste-message/**").hasAnyAuthority("PROVIDER")

//                .requestMatchers("/Recources-Managment/enseignant/**").hasAnyAuthority("MANAGER")
                .requestMatchers("/Recources-Managment/enseignant/demands/**").hasAnyAuthority("TEACHER")
                .requestMatchers("/Recources-Managment/enseignant/demands/teacher/**").hasAnyAuthority("TEACHER")
                .requestMatchers("/Recources-Managment/enseignant/computer/demands").hasAnyAuthority("TEACHER")
                .requestMatchers("/Recources-Managment/enseignant/printer/demands").hasAnyAuthority("TEACHER")
                .requestMatchers("/Recources-Managment/enseignant/teachers/**").hasAnyAuthority("TEACHER")
                .requestMatchers("/Recources-Managment/enseignant/teachers/mails/**").hasAnyAuthority("TEACHER")
                .requestMatchers("/Recources-Managment/enseignant/liste-demandes/**").hasAnyAuthority("TEACHER")
//                .requestMatchers("/Recources-Managment/enseignant/deleteUser/**").hasAnyAuthority("MANAGER")

                //rachid
                .requestMatchers("/responsable/consulterFournisseur/**").hasAnyAuthority("MANAGER")
                .requestMatchers("/responsable/consulterPannes/**","/responsable/consulterPannes/findDemandeByRId/**").hasAnyAuthority("MANAGER")
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
