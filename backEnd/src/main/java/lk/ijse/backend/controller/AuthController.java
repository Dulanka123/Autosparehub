package lk.ijse.backend.controller;

import jakarta.servlet.http.HttpServletRequest;





import jakarta.mail.MessagingException;
import lk.ijse.backend.DTO.AuthDTO;
import lk.ijse.backend.DTO.LoginAlertDTO;
import lk.ijse.backend.DTO.ResponseDTO;
import lk.ijse.backend.DTO.UserDTO;
import lk.ijse.backend.service.MailService;
import lk.ijse.backend.service.impl.UserServiceImpl;
import lk.ijse.backend.util.JwtUtil;
import lk.ijse.backend.util.VarList;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static lk.ijse.backend.util.RequestUtil.getClientIP;
import static lk.ijse.backend.util.RequestUtil.getLocationFromIP;

@RestController
@RequestMapping("api/v1/auth")
@CrossOrigin
public class AuthController {

    private final JwtUtil jwtUtil;
    private final AuthenticationManager authenticationManager;
    private final UserServiceImpl userService;
    private final ResponseDTO responseDTO;
    private final MailService mailService;

    //constructor injection
    public AuthController(JwtUtil jwtUtil, AuthenticationManager authenticationManager, UserServiceImpl userService, ResponseDTO responseDTO, MailService mailService) {
        this.jwtUtil = jwtUtil;
        this.authenticationManager = authenticationManager;
        this.userService = userService;
        this.responseDTO = responseDTO;
        this.mailService = mailService;
    }

    @PostMapping("/authenticate")
    public ResponseEntity<ResponseDTO> authenticate(@RequestBody UserDTO userDTO, HttpServletRequest request) throws MessagingException {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(userDTO.getEmail(), userDTO.getPassword()));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(new ResponseDTO(VarList.Unauthorized, "Invalid Credentials", e.getMessage()));
        }

        UserDTO loadedUser = userService.loadUserDetailsByUsername(userDTO.getEmail());
        if (loadedUser == null) {
            return ResponseEntity.status(HttpStatus.CONFLICT)
                    .body(new ResponseDTO(VarList.Conflict, "Authorization Failure! Please Try Again", null));
        }

        String token = jwtUtil.generateToken(loadedUser);
        if (token == null || token.isEmpty()) {
            return ResponseEntity.status(HttpStatus.CONFLICT)
                    .body(new ResponseDTO(VarList.Conflict, "Authorization Failure! Please Try Again", null));
        }

        AuthDTO authDTO = new AuthDTO();
        authDTO.setToken(token);
        authDTO.setUser(loadedUser);
       // mailService.sendMail(loadedUser.getEmail(), "Login Success", null);

        LoginAlertDTO alertDTO = new LoginAlertDTO();
        alertDTO.setUsername(loadedUser.getName()); // Or however you store/display username
        alertDTO.setLoginTime(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
        alertDTO.setDevice(request.getHeader("User-Agent")); // You can parse this later for cleaner name
        alertDTO.setLocation(getLocationFromIP(getClientIP(request))); // Optional, see below

        mailService.sendMail(loadedUser.getEmail(), "Login Alert - New Device Detected", alertDTO);



        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new ResponseDTO(VarList.Created, "Success", authDTO));
    }

    @PostMapping("/shop-authenticate")
    public ResponseEntity<ResponseDTO> shopAuthenticate(@RequestBody UserDTO userDTO) {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(userDTO.getEmail(), userDTO.getPassword()));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(new ResponseDTO(VarList.Unauthorized, "Invalid Credentials", e.getMessage()));
        }

        UserDTO loadedUser = userService.loadUserDetailsByUsername(userDTO.getEmail());
        if (loadedUser == null) {
            return ResponseEntity.status(HttpStatus.CONFLICT)
                    .body(new ResponseDTO(VarList.Conflict, "Authorization Failure! Please Try Again", null));
        }

        String token = jwtUtil.generateToken(loadedUser);
        if (token == null || token.isEmpty()) {
            return ResponseEntity.status(HttpStatus.CONFLICT)
                    .body(new ResponseDTO(VarList.Conflict, "Authorization Failure! Please Try Again", null));
        }

        AuthDTO authDTO = new AuthDTO();
        authDTO.setToken(token);
        authDTO.setUser(loadedUser);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new ResponseDTO(VarList.Created, "Success", authDTO));
    }

}

