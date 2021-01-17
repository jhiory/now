package doit.now.security;


import com.fasterxml.jackson.annotation.JsonIgnore;
import doit.now.security.model.User;
import lombok.Data;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

@Data
public class UserPrincipal implements UserDetails {

    private static final Logger logger = LoggerFactory.getLogger(UserPrincipal.class);

    private static final long serialVersionUID = 186161047983347952L;

    private String username;

    private String mngNm;

    @JsonIgnore
    private String password;

    private Collection<? extends GrantedAuthority> authorities;

    public UserPrincipal(String mngId, String mngNm, String mngPwd, Collection<? extends GrantedAuthority> authorities) {
        this.username = mngId;
        this.mngNm = mngNm;
        this.password = mngPwd;
        this.authorities = authorities;
    }

    public static UserPrincipal create(User user) {
        logger.info("!!!!!!!!!!!!!!!!!!18!!!!!!!"+user);
        logger.info("!!!!!!!!!!!!!!!!!!18!!!!!!!"+user.getUserId());
        logger.info("!!!!!!!!!!!!!!!!!!18!!!!!!!"+user.getUserNm());
        logger.info("!!!!!!!!!!!!!!!!!!18!!!!!!!"+user.getPwd());

        List<GrantedAuthority> authorities =
                Collections.singletonList(new SimpleGrantedAuthority(user.getRoleId()));
        logger.info("!!!!!!!!!!!!!!authorities!!!!!!!!!!!!!!!!!!!!!!!!"+authorities);
        return new UserPrincipal(
                user.getUserId(),
                user.getUserNm(),
                user.getPwd(),
          authorities
        );
    }

    @Override
    public boolean isAccountNonExpired() { return true; }

    @Override
    public boolean isAccountNonLocked() { return true; }

    @Override
    public boolean isCredentialsNonExpired() { return true; }

    @Override
    public boolean isEnabled() { return true; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserPrincipal that = (UserPrincipal) o;
        return Objects.equals(username, that.username);
    }

    @Override
    public int hashCode() {
        return Objects.hash(username);
    }
}
