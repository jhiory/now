package doit.now.security.vo;

import lombok.Data;
import org.apache.ibatis.type.Alias;

@Data
@Alias("pwdHst")
public class PwdHstVo {
    private int hstSn;
    private String userId;
    private String pwd;
    private String regUser;
    private String regDttm;
    private String modUser;
    private String modDttm;
}
