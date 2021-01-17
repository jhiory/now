package com.example.vi.security.repository;

import com.example.vi.security.dto.PasswordChangeRequest;
import com.example.vi.security.vo.PwdHstVo;
import com.example.vi.security.vo.UserVo;
import com.example.vi.vo.LoginHstVo;
import com.example.vi.vo.MenuVo;
import com.example.vi.vo.UserLoginVo;
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
    UserVo selectUserByUserId(@Param("userId") String userId);
     
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
