package com.otomasyon.otomasyonDemo.controller;

import com.otomasyon.otomasyonDemo.auth.TokenManager;
import com.otomasyon.otomasyonDemo.entity.Rol;
import com.otomasyon.otomasyonDemo.entity.User;
import com.otomasyon.otomasyonDemo.login.ChangePasswordDTO;
import com.otomasyon.otomasyonDemo.login.LoginDTO;
import com.otomasyon.otomasyonDemo.repository.RolRepository;
import com.otomasyon.otomasyonDemo.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthRestController {

    private static final Logger logger = LoggerFactory.getLogger(AuthRestController.class);

    private final AuthenticationManager authenticationManager;
    private final TokenManager tokenManager;
    private final UserRepository userRepository;
    private final RolRepository rolRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    @Autowired
    public AuthRestController(AuthenticationManager authenticationManager,
                              TokenManager tokenManager,
                              UserRepository userRepository,
                              RolRepository rolRepository,
                              BCryptPasswordEncoder passwordEncoder) {
        this.authenticationManager = authenticationManager;
        this.tokenManager = tokenManager;
        this.userRepository = userRepository;
        this.rolRepository = rolRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginDTO loginDTO) {
        try {
            if (loginDTO != null && loginDTO.getEmail() != null && loginDTO.getPassword() != null) {
                authenticationManager.authenticate(
                        new UsernamePasswordAuthenticationToken(loginDTO.getEmail(), loginDTO.getPassword())
                );
                User user = userRepository.findByEmail(loginDTO.getEmail());
                if (user == null) {
                    throw new IllegalArgumentException("User not found");
                }
                String token = tokenManager.generateToken(user.getEmail());
                HttpHeaders headers = new HttpHeaders();
                headers.set("Authorization", "Bearer " + token);

                String responseMessage = user.isSifreGuncelligi()
                        ? "Login success."
                        : "Login success. Please change your password.";

                return ResponseEntity.ok().headers(headers).body(responseMessage);
            }
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("The email or password is incorrect.");
        } catch (Exception e) {
            logger.error("Login error: {}", e.getMessage());
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Authentication failed.");
        }
    }

    @PostMapping("/signup")
    public ResponseEntity<String> signUp(@RequestBody LoginDTO loginDTO) {
        try {
            if (loginDTO != null && loginDTO.getEmail() != null && loginDTO.getTckn() != null) {
                if (userRepository.existsByEmail(loginDTO.getEmail())) {
                    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Email is already in use.");
                }
                Rol.RolTuru girilenRol;
                try {
                    girilenRol = Rol.RolTuru.valueOf(loginDTO.getRol().toUpperCase());
                } catch (IllegalArgumentException e) {
                    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid role specified.");
                }
                Rol rol = rolRepository.findByRolTuru(girilenRol)
                        .orElseThrow(() -> new RuntimeException("Role not found."));
                User newUser = new User();
                newUser.setEmail(loginDTO.getEmail());
                newUser.setTckn(loginDTO.getTckn());
                newUser.setPassword(passwordEncoder.encode(loginDTO.getTckn()));
                newUser.setSifreGuncelligi(false);
                newUser.setRol(rol);
                userRepository.save(newUser);
                return ResponseEntity.ok(loginDTO.getEmail() + " has been successfully registered with role: " + girilenRol);
            }
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid registration data.");
        } catch (Exception e) {
            logger.error("Signup error: {}", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Signup failed.");
        }
    }
    @PutMapping("/change-password")
    public ResponseEntity<String> changePassword(@RequestHeader("Authorization") String authHeader,
                                                 @RequestBody ChangePasswordDTO dto) {
        try {
            String token = authHeader.replace("Bearer ", "");
            String email = tokenManager.extractUsername(token);
            User user = userRepository.findByEmail(email);
            if (user == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found.");
            }

            if (!passwordEncoder.matches(dto.getOldPassword(), user.getPassword())) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Current password is incorrect.");
            }

            user.setPassword(passwordEncoder.encode(dto.getNewPassword()));
            user.setSifreGuncelligi(true);
            userRepository.save(user);

            return ResponseEntity.ok("Password successfully changed.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Password change failed.");
        }
    }
}