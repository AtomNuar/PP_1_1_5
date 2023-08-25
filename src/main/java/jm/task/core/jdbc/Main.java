package jm.task.core.jdbc;

import jm.task.core.jdbc.dao.UserDao;
import jm.task.core.jdbc.dao.UserDaoJDBCImpl;
import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.util.Util;


import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Main {



    public static void main(String[] args) {

        Util.getConnection();

        UserDao userDao = new UserDaoJDBCImpl();

        userDao.createUsersTable();
        userDao.saveUser("Ira","Fox",(byte)30);
        userDao.saveUser("Roman","Petrov",(byte)15);
        userDao.saveUser("Dmitriy","Reed",(byte)56);
        userDao.saveUser("Marina","Green",(byte)75);
        userDao.getAllUsers();
        userDao.cleanUsersTable();
        userDao.dropUsersTable();


    }
}
