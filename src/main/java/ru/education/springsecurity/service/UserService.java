package ru.education.springsecurity.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.education.springsecurity.entity.Role;
import ru.education.springsecurity.entity.User;
import ru.education.springsecurity.repository.UserRepository;

import java.util.Collection;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserService implements UserDetailsService {
    @Autowired
    UserRepository repository;

    public Optional<User> findUserByName(String name){
      return  repository.findByName(name);
    }

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        User u = findUserByName(s).orElseThrow(()-> new UsernameNotFoundException(String.format("User '%s' not found ",s)));
        return new org.springframework.security.core.userdetails.User(u.getName(),u.getPassword(),mapRoles(u.getRole()));
    }

    private Collection<? extends GrantedAuthority> mapRoles(Collection<Role> roles) {
        return roles.stream().map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList());
    }


    public void saveUser(User user) {
        repository.saveAndFlush(user);
    }
    public long totalValues(){
         return repository.count();
    }

    public User findUserById(Long id) {
       return repository.findById(id).orElseThrow(()-> new UsernameNotFoundException("Not found user by id - "+id));
    }
}
