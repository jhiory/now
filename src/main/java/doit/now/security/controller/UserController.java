package doit.now.security.controller;



import doit.now.security.UserPrincipal;
import doit.now.security.dto.PasswordChangeRequest;
import doit.now.security.service.UserService;
import doit.now.vo.UserLoginVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Slf4j
@RestController
@RequestMapping("/user")
@Api(value = "User API", description = "고객 API")
public class UserController {

    @Autowired
    UserService userService;
	
    @ApiOperation(value = "고객정보 조회")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "조회 성공")})
    @PostMapping("/select")
    public ResponseEntity<?> authenticateUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserPrincipal userPrincipal = (UserPrincipal) authentication.getPrincipal();
        return ResponseEntity.ok(userPrincipal);
    }
    
    @GetMapping("/roleMenu/{roleId}")
    public ResponseEntity<?> getRoleMenus(@PathVariable String roleId) {
        return ResponseEntity.ok(userService.getRoleMapMenu(roleId));
    }

    @PutMapping("/passwordChange")
    public ResponseEntity<?> passwordChange(@RequestBody PasswordChangeRequest request) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserPrincipal userPrincipal = (UserPrincipal) authentication.getPrincipal();

        request.setRegUser(userPrincipal.getMngNm());
        request.setModUser(userPrincipal.getMngNm());

        userService.passwordChange(request);
        return ResponseEntity.ok("");
    }

    @GetMapping("/beforeLoginCheck/{userid}")
    public ResponseEntity<?> beforeLoginCheck(@Valid @PathVariable String userid) {
        UserLoginVo userLoginVo = new UserLoginVo();
        userLoginVo.setUserid(userid);

        return ResponseEntity.ok(userService.beforeLoginCheck(userLoginVo));
    }

    @GetMapping("/isInitPwd/{userid}")
    public ResponseEntity<?> isInitPwd(@Valid @PathVariable String userid) {
        UserLoginVo userLoginVo = new UserLoginVo();
        userLoginVo.setUserid(userid);

        return ResponseEntity.ok(userService.isInitPwd(userLoginVo));
    }

}
