package com.example.vi.security.controller;

import com.example.vi.exception.BadRequestException;
import com.example.vi.security.dto.LoginRequest;
import com.example.vi.security.dto.LoginResponse;
import com.example.vi.security.jwt.JwtTokenProvider;
import com.example.vi.security.repository.UserRepository;
import com.example.vi.vo.LoginHstVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/auth")
@Api(value = "Admin API", description = "로그인 API", tags = "Admin")
public class AuthController {

    private static final Logger log = LoggerFactory.getLogger(AuthController.class);

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    JwtTokenProvider tokenProvider;

    @Autowired
    UserRepository userRepository;

    @Autowired
    MessageSourceAccessor messageSource;

    @ApiOperation(value = "로그인", httpMethod = "POST")
    @ApiResponse(code = 200, message = "로그인 성공")
    @PostMapping("/signin")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {
        Authentication authentication = null;

        try {
            authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            loginRequest.getUsername(),
                            loginRequest.getPassword()
                    )
            );
        } catch (Exception e) {
            int failCount = userRepository.selectFailCountByUserId(loginRequest.getUsername());

            LoginHstVo loginHstVo = new LoginHstVo();
            loginHstVo.setUserId(loginRequest.getUsername());
            loginHstVo.setPwdFailCt(failCount + 1);

            userRepository.insertLoginHstForFail(loginHstVo);

            userRepository.updateFailCtByUserId(loginHstVo);
            log.info("로그인 실패");

            throw new BadRequestException(messageSource.getMessage("userIdOrPasswordInvalid"));
        }

        SecurityContextHolder.getContext().setAuthentication(authentication);

        String jwt = tokenProvider.generateToken(authentication);

        LoginHstVo loginHstVo = new LoginHstVo();
        loginHstVo.setUserId(loginRequest.getUsername());
        loginHstVo.setPwdFailCt(0);

        userRepository.insertLoginHstForSuccess(loginHstVo);

        userRepository.updateFailCtByUserId(loginHstVo);

        return ResponseEntity.ok(new LoginResponse(jwt));
    }

}
