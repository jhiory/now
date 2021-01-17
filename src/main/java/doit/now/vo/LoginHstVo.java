package doit.now.vo;

import lombok.Data;

@Data
public class LoginHstVo {
    public int hstSn;
    public String userId;
    public int pwdFailCt;
    public String lastLoginSuccessDttm;

}
