package jm.task.core.jdbc;

import jm.task.core.jdbc.dao.UserDao;
import jm.task.core.jdbc.dao.UserDaoHibernateImpl;
import jm.task.core.jdbc.dao.UserDaoJDBCImpl;
import jm.task.core.jdbc.util.Util;

public class Main {



    public static void main(String[] args) {

        Util.getSessionFactory();


        UserDao userDao = new UserDaoJDBCImpl();
        UserDao userDaoHibernate = new UserDaoHibernateImpl();

        userDaoHibernate.createUsersTable();
        userDaoHibernate.saveUser("ROMAN","SHATILOV",(byte) 50);
        userDaoHibernate.saveUser("Kristina","SHATILOV",(byte) 31);
        userDaoHibernate.saveUser("NIKA","SHATILOV",(byte) 7);
        userDaoHibernate.removeUserById(1);


        /*userDao.saveUser("Ira","Fox",(byte)30);
        userDao.saveUser("Roman","Petrov",(byte)15);
        userDao.saveUser("Dmitriy","Reed",(byte)56);
        userDao.saveUser("Marina","Green",(byte)75);
        userDao.getAllUsers();
        userDao.cleanUsersTable();
        userDao.dropUsersTable();

         */


    }
}
