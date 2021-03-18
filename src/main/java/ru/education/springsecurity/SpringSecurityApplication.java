package ru.education.springsecurity;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringSecurityApplication {
    // Создайте новый проект с spring-boot + spring-security (все на RestController'ах делаем)
    // Форму входа используем стандартную
    // Подключите туда DaoAuthentication
    // Для каждого пользователя сделайте поле Score в котором
    // указывается некий балл пользователя
    // GET .../app/score/inc - увеличивает балл текущего пользователя
    // GET .../app/score/dec - уменьшает балл текущего пользователя
    // GET .../app/score/get/current - показывает балл вошедшего пользователя
    // GET .../app/score/get/{id} - показывает балл пользователя с указанным id (доступно
    // только для залогиненных пользователей)	

    public static void main(String[] args) {
        SpringApplication.run(SpringSecurityApplication.class, args);
    }

}
