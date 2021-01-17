package com.example.vi.security.service;

import com.example.vi.security.UserPrincipal;
import com.example.vi.security.model.User;
import com.example.vi.security.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
public class CustomUserDetailService implements UserDetailsService {
    @Autowired
    MessageSourceAccessor messageSource;

    @Autowired
    UserRepository userRepository;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = new ModelMapper().map(userRepository.selectUserByUserId(username), User.class);
        log.info(user.toString());
        log.info(username);
//        log.info(userDetails.getPassword());
        if (user == null) {
            new UsernameNotFoundException(messageSource.getMessage("login.msg.invalid.login"));
        }

        return UserPrincipal.create(user);
    }
}
