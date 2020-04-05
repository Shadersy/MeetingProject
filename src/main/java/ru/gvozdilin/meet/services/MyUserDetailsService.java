package ru.gvozdilin.meet.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.gvozdilin.meet.dao.UserDaoImpl;
import ru.gvozdilin.meet.entity.Roles;
import ru.gvozdilin.meet.entity.User;

import java.util.HashSet;

import static jdk.nashorn.internal.runtime.regexp.joni.Config.log;
@Service
public class MyUserDetailsService implements UserDetailsService {
    @Autowired
    private UserDaoImpl userDao;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userDao.getByLogin(username);
        System.out.println(user.getUsername() + " " + user.getPassword());
        HashSet<GrantedAuthority> roles = new HashSet();
        roles.add(new SimpleGrantedAuthority(Roles.USER.name()));

        if (user == null) {
            throw new UsernameNotFoundException(username);
        }
        return new MyUserPrincipal(user);

    }
}
