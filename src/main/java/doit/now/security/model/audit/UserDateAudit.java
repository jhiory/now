package com.example.vi.security.model.audit;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.LastModifiedBy;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
@JsonIgnoreProperties(
        value = {"createdBy", "updatedBy"},
        allowGetters = true
)
public abstract class UserDateAudit {
    @CreatedBy
    @Column(name = "REG_MNG")
    private String createdBy;

    @LastModifiedBy
    @Column(name = "MOD_MNG")
    private String updatedBy;

}
