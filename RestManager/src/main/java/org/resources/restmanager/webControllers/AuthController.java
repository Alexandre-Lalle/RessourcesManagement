package org.resources.restmanager.webControllers;


import org.resources.restmanager.model.DTO.zouine.AuthResponseDTO;
import org.resources.restmanager.model.DTO.zouine.LoginDTO;
import org.resources.restmanager.model.DTO.zouine.RegisterDTO;
import org.resources.restmanager.model.entities.auth.Role;
import org.resources.restmanager.model.entities.auth.User;
import org.resources.restmanager.repositories.auth.AppRoleRepository;
import org.resources.restmanager.repositories.auth.AppUserRepository;
import org.resources.restmanager.security.JWTGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/Resource-Managment/auth")
public class AuthController {
    private AuthenticationManager authenticationManager;
    private AppUserRepository userRepository;
    private AppRoleRepository roleRepository;
    private PasswordEncoder passwordEncoder;
    private JWTGenerator jwtGenerator;


    @Autowired
    public AuthController(AuthenticationManager authenticationManager,
                          AppUserRepository userRepository,
                          AppRoleRepository roleRepository,
                          PasswordEncoder passwordEncoder,
                          JWTGenerator jwtGenerator) {
        this.authenticationManager = authenticationManager;
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtGenerator = jwtGenerator;
    }


    @PostMapping(path = "register", consumes = {"application/json"})
    public ResponseEntity<String> register(@RequestBody RegisterDTO registerDTO){
        if(userRepository.existsByUserName(registerDTO.getUserName())){
            return new ResponseEntity<>("Username is taken !", HttpStatus.BAD_REQUEST)  ;
        }

        User user = new User();
        user.setUserName(registerDTO.getUserName());
        user.setPassword(passwordEncoder.encode(registerDTO.getPassword()));
        Role role = roleRepository.findByName("DIRECTOR");
        user.setRoles(Collections.singletonList(role));
        userRepository.save(user);

        return new ResponseEntity<>("User registered successfully !",HttpStatus.OK);
    }



    @PostMapping(path = "login",consumes = {"application/json"},produces = {"application/json"})
    public ResponseEntity<AuthResponseDTO> login(@RequestBody LoginDTO loginDTO){
        System.out.println("login was called !!");
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginDTO.getUserName(),loginDTO.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);

        String token = jwtGenerator.generateToken(authentication);
        System.out.println("after token : "+token);
        User user = userRepository.findByUserName(loginDTO.getUserName());
        return new ResponseEntity<>(new AuthResponseDTO(user, token),HttpStatus.OK);
    }

}
