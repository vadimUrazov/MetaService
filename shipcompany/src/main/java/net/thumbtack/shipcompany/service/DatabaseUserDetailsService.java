package net.thumbtack.shipcompany.service;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class DatabaseUserDetailsService extends ServiceBase implements UserDetailsService {


    public DatabaseUserDetailsService() {

    }

    @Override
    public UserDetails loadUserByUsername(String username)
            throws UsernameNotFoundException {

        var user = userDao.getUserByLogin(username);

        return User.withUsername(user.getLogin())
                .password(user.getPassword())
                .roles(user.getUserType().toString())
                .build();
    }
}