package it.dev.app.auth;

import it.dev.app.model.entity.UserAuthEntity;
import it.dev.app.service.UserAuthService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Slf4j
@Service
public class UserAuthSecurity implements UserDetailsService {

    @Autowired
    UserAuthService userAuthService;

    @Autowired
    BCryptPasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        log.info("username=" + username);
        Optional<UserAuthEntity> userAuth=userAuthService.getUser(username);
        if(userAuth.isPresent()) {
            UserAuthEntity userAuthEntity=userAuth.get();
            User.UserBuilder user = User.builder();
            user.username(userAuthEntity.getUsername());
            user.password(userAuthEntity.getPassword());
            user.roles(new String[]{userAuthEntity.getRole()});
            user.credentialsExpired(userAuthEntity.getPasswordExpired());
            user.disabled(!userAuthEntity.getEnable());
            user.accountExpired(false);
            user.accountLocked(false);
            return user.build();
        }
        log.info("USER NOT PRESENT:" + username);
        throw new UsernameNotFoundException(username);
    }

}
