package com.example.myfitness.MyFitnessControllers;

import com.example.myfitness.Auth.dto.LoginRequest;
import com.example.myfitness.Auth.dto.LoginResponse;
import com.example.myfitness.Auth.dto.RegisterRequest;
import com.example.myfitness.MyFitnessConstants.Gender;
import com.example.myfitness.Entity.User;
import com.example.myfitness.Repository.UserRepository;
import com.example.myfitness.Security.JwtUtil;
import com.example.myfitness.Security.SecurityUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody RegisterRequest request) {

        if (userRepository.findByEmail(request.getEmail()).isPresent()) {
            return ResponseEntity.badRequest().body("Email already registered");
        }

        User user = new User();
        user.setEmail(request.getEmail());
        user.setFullName(request.getFullName());
        user.setPasswordHash(passwordEncoder.encode(request.getPassword()));

        user.setGender(Gender.valueOf(request.getGender()));
        user.setDateOfBirth(request.getDateOfBirth());
        user.setHeightCm(request.getHeightCm());
        user.setWeightKg(request.getWeightKg());
        user.setIsActive(true);

        userRepository.save(user);

        return ResponseEntity.ok("Registration successful");
    }


    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest request) {

        Optional<User> userOpt = userRepository.findByEmail(request.getEmail());

        if (userOpt.isEmpty())
            return ResponseEntity.status(401).body("Invalid credentials");

        User user = userOpt.get();

        if (!passwordEncoder.matches(request.getPassword(), user.getPasswordHash()))
            return ResponseEntity.status(401).body("Invalid credentials");

        String token = jwtUtil.generateToken(user);
        return ResponseEntity.ok(new LoginResponse(token));
    }

    @GetMapping("/whoami")
    public Map<String, Object> whoAmI() {
        Long userId = SecurityUtil.getCurrentUserId();
        return Map.of(
                "userId", userId,
                "message", "You are authenticated successfully"
        );
    }


}

