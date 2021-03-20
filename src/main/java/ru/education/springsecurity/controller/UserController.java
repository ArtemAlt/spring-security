package ru.education.springsecurity.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.education.springsecurity.entity.User;
import ru.education.springsecurity.service.UserService;

import java.security.Principal;

@RestController
@RequestMapping("/app")
public class UserController {
    @Autowired
    UserService service;


    @GetMapping//посмотреть без авторизации
    public String totalUsersValue(){
        return "Total numbers of users records in DB - "+service.totalValues();
    }

    @GetMapping("/score/inc")
    public String addScoreOnOne(Principal p){
        User user = service.findUserByName(p.getName()).orElseThrow(()-> new UsernameNotFoundException("No such user in DB"+p.getName()));
        user.setScore(user.getScore()+1);
        service.saveUser(user);
        return "Score update for user - "+user.getName();
    }

    @GetMapping("/score/dec")
    public String reduceScoreOnOne(Principal p){
        User user = service.findUserByName(p.getName()).orElseThrow(()-> new UsernameNotFoundException("No such user in DB"+p.getName()));
        user.setScore(user.getScore()-1);
        service.saveUser(user);
        return "Score update for user - "+user.getName();
    }

    @GetMapping("/score/get/current")
    public String getCurrent(Principal p){
        User user = service.findUserByName(p.getName()).orElseThrow(()-> new UsernameNotFoundException("No such user in DB"+p.getName()));
        return  "For current user - "+user.getName()+ " score is - "+ user.getScore();
    }

    @GetMapping("/score/get/{id}")
    public String getScoreByUserId (@PathVariable Long id,Principal p){
        User user = service.findUserById( id);
        return  "Yours score is - "+ user.getScore();
    }
}
