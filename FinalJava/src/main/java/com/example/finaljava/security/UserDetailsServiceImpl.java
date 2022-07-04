package com.example.finaljava.security;

import com.example.finaljava.models.User;
import com.example.finaljava.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import javax.servlet.http.HttpServletRequest;
import java.util.Collections;

public class UserDetailsServiceImpl implements UserDetailsService {

    private UserRepository userRepository;

    @Autowired
    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    private LoginAttemptService loginAttemptService;

    private HttpServletRequest request;

    @Autowired
    public void setLoginAttemptService(LoginAttemptService loginAttemptService) {
        this.loginAttemptService = loginAttemptService;
    }

    @Autowired
    public void setRequest(HttpServletRequest request) {
        this.request = request;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        String ip = getClientIP();
        if (loginAttemptService.isBlocked(ip)) {
            throw new RuntimeException("blocked");
        }

        try {
            User user = userRepository.findByLogin(email);
            if (user == null) {
                return new org.springframework.security.core.userdetails.User(
                        " ", " ", true, true, true, true,
                        Collections.singletonList(new SimpleGrantedAuthority("administrator")));
            }

            return new org.springframework.security.core.userdetails.User(
                    user.getLogin(), user.getPasword(), user.getStatus(), true, true, true,
                    Collections.singletonList(new SimpleGrantedAuthority("administrator")));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public String getClientIP() {
        String xfHeader = request.getHeader("X-Forwarded-For");
        if (xfHeader == null){
            return request.getRemoteAddr();
        }
        return xfHeader.split(",")[0];
    }

}
