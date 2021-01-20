package doit.now.security.repository;

import doit.now.security.dto.PasswordChangeRequest;
import doit.now.vo.*;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface UserRepository extends UserDetailsService {

    /**
     * 관리자 조회
     * @param userId
     * @return
     */
    default UserInfoVo selectUserByUserId(@Param("userId") String userId) {
        return null;
    }
     
    List<MenuVo> selectRoleMapMenus(String roleId);

    String selectPasswordByUserId(String mngId);

    void updatePassword(PasswordChangeRequest request);

    void registPwdHistory(PasswordChangeRequest request);

    List<PwdHstVo> selectRecentPwdHistory(PasswordChangeRequest request);

    UserLoginVo selectLoginInfo(UserLoginVo userLoginVo);

    int selectFailCountByUserId(String username);

    void insertLoginHstForFail(LoginHstVo loginHstVo);

    void updateFailCtByUserId(LoginHstVo loginHstVo);

    void insertLoginHstForSuccess(LoginHstVo loginHstVo);
}
