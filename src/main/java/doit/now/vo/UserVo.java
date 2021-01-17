package doit.now.vo;

import lombok.Data;
import org.apache.ibatis.type.Alias;

@Data
@Alias("user")
public class UserVo {
    private String userId;
    private String userNm;
    private String deptNm;
    private String jobNm;
    private String telNo;
    private String email;
    private String pwd;
    private int    pwdFailCt;
    private String lastPwdUpdDttm;
    private String lastLoginSuccessDttm;
    private int    loginCt;
    private String roleID;
    private String regDttm;
    private String modDttm;
    private String regId;
    private String modId;
}
