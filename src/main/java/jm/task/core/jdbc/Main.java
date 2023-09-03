package jm.task.core.jdbc;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;

public class Main {

    static UserService userService = new UserServiceImpl();

    public static void main(String[] args) {

        User one = new User("Роман","Шатилов",(byte)31);
        User two = new User("Кристина","Шатилова",(byte)28);
        User three = new User("Вероника","Шатилова",(byte)6);

        userService.saveUser(one.getName(), one.getLastName(), one.getAge());
        userService.saveUser(two.getName(), two.getLastName(), two.getAge());
        userService.saveUser(three.getName(), three.getLastName(), three.getAge());

        userService.removeUserById(2);

        userService.getAllUsers();

        userService.cleanUsersTable();
    }
}
