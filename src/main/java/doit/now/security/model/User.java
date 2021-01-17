package com.example.vi.security.model;


import com.example.vi.security.model.audit.UserDateAudit;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
@EqualsAndHashCode(callSuper = false)
public class User extends UserDateAudit {

    @Id
    @Size(max = 50)
    private String userId;

    @NotBlank
    @Size(max = 50)
    private String userNm;

    @NotBlank
    @Size(max = 500)
    private String pwd;

    @NotBlank
    private String roleId;

    private String telNo;

    private String email;

    private String regId;

    private String modId;

}

