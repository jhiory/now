package doit.now.security.service;



import doit.now.exception.BadRequestException;
import doit.now.security.UserPrincipal;
import doit.now.security.dto.PasswordChangeRequest;
import doit.now.security.model.User;
import doit.now.security.repository.UserRepository;
import doit.now.vo.MenuVo;
import doit.now.vo.PwdHstVo;
import doit.now.vo.UserLoginVo;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Slf4j
public class UserService implements UserDetailsService {
    @Autowired
    MessageSourceAccessor messageSource;

    @Autowired
    UserRepository userRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = new ModelMapper().map(userRepository.selectUserByUserId(username), User.class);

        if (user == null) {
            new UsernameNotFoundException(messageSource.getMessage("login.msg.invalid.login"));
        }

        return UserPrincipal.create(user);
    }
    
    public List<MenuVo> getRoleMapMenu(String roleId) {
        return userRepository.selectRoleMapMenus(roleId);
    }

    @Transactional
    public void passwordChange(PasswordChangeRequest request) {
        try {
            String password = userRepository.selectPasswordByUserId(request.getMngId());

            boolean isMatched = passwordEncoder.matches(request.getPrePwd(), password);

            // 기존 비밀번호 일치 여부
            if (!isMatched) {
                throw new BadRequestException(messageSource.getMessage("user.invalid.previous.pwd"));
            }

            // 신규 비밀번호, 신규 비밀번호 확인 일치 여부
            if (!request.getMngPwd().equals(request.getMngPwdCheck())) {
                throw new BadRequestException(messageSource.getMessage("user.invalid.new.pwd"));
            }

            // 과거 비밀번호(이전 3개) 일치 여부
            List<PwdHstVo> list = userRepository.selectRecentPwdHistory(request);
            for(PwdHstVo pwdHstVo : list){
                boolean isPwdMatched = passwordEncoder.matches(request.getMngPwd(), pwdHstVo.getPwd());
                if(isPwdMatched){
//                    throw new BadRequestException(messageSource.getMessage("user.invalid.pwd"));
                    throw new BadRequestException("과거 사용했던 비밀번호는 재사용할 수 없습니다. (이전 3개)");
                }
            }

            String newPassword = passwordEncoder.encode(request.getMngPwd());

            request.setMngPwd(newPassword);

            userRepository.updatePassword(request);

            try {
                userRepository.registPwdHistory(request);
            } catch (DataIntegrityViolationException e) {
                throw new BadRequestException(messageSource.getMessage("code.exist"));
            }
        } catch (DataIntegrityViolationException e) {
            throw new BadRequestException(messageSource.getMessage("code.exist"));
        }
    }

    public UserLoginVo beforeLoginCheck(UserLoginVo userLoginVo) {
        return userRepository.selectLoginInfo(userLoginVo);
    }

    public boolean isInitPwd(UserLoginVo userLoginVo) {
        String basicPwd = "1234";
        String pwd = userRepository.selectPasswordByUserId(userLoginVo.getUserid());
        boolean isMatched = passwordEncoder.matches(basicPwd, pwd);

        return isMatched;
    }
}
