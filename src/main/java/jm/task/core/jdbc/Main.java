package jm.task.core.jdbc;

import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;

public class Main {

    static UserService userService = new UserServiceImpl();

    public static void main(String[] args) {

        userService.createUsersTable();

        userService.saveUser("Роман", "Шатилов", (byte) 31);
        userService.saveUser("Кристина", "Шатилова", (byte) 28);
        userService.saveUser("Вероника", "Шатилова", (byte) 6);

        userService.getAllUsers();

        userService.cleanUsersTable();

        userService.dropUsersTable();
    }
}
