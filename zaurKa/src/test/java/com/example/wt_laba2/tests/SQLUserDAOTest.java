package com.example.wt_laba2.tests;

import com.example.wt_laba2.bean.User;
import com.example.wt_laba2.dao.UserDao;
import com.example.wt_laba2.dao.exception.DAOException;
import com.example.wt_laba2.dao.pool.factory.ConnectionPoolFactory;
import com.example.wt_laba2.dao.factory.DAOFactory;
import com.example.wt_laba2.dao.pool.ConnectionPool;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SQLUserDAOTest {

    private static int bannedUserId;
    @Test
    void signIn() throws DAOException {
        try{
            ConnectionPool connectionPool = ConnectionPoolFactory.getInstance().getConnectionPool();

            connectionPool.CreateConnections();

        }catch (Exception ex){
            Assertions.fail("Problem with connection");
        }
        DAOFactory daoFactory = DAOFactory.getFactory();
        UserDao userDAO = daoFactory.getUserDao();
        User user = userDAO.signIn("T4RWERKA", "1111");

        assertNotNull(user);
    }

    @Test
    void registration() throws DAOException{
        try{
            ConnectionPool connectionPool = ConnectionPoolFactory.getInstance().getConnectionPool();

            connectionPool.CreateConnections();

        }catch (Exception ex){
            Assertions.fail("Problem with connection");
        }
        DAOFactory daoFactory = DAOFactory.getFactory();
        UserDao userDAO = daoFactory.getUserDao();
        User user = new User();
        user.setLogin("TestUser");
        user.setPassword("12345");
        int id = userDAO.registration(user);

        assertTrue(id > 0);
        bannedUserId = id;
    }

    @Test
    void setBan() throws DAOException {
        try{
            ConnectionPool connectionPool = ConnectionPoolFactory.getInstance().getConnectionPool();

            connectionPool.CreateConnections();

        }catch (Exception ex){
            Assertions.fail("Problem with connection");
        }
        DAOFactory daoFactory = DAOFactory.getFactory();
        UserDao userDAO = daoFactory.getUserDao();

        assertTrue(  userDAO.SetBan(bannedUserId));
    }

    @Test
    void removeBan() throws DAOException{
        try{
            ConnectionPool connectionPool = ConnectionPoolFactory.getInstance().getConnectionPool();

            connectionPool.CreateConnections();

        }catch (Exception ex){
            Assertions.fail("Problem with connection");
        }
        DAOFactory daoFactory = DAOFactory.getFactory();
        UserDao userDAO = daoFactory.getUserDao();

        assertTrue(  userDAO.removeBan(bannedUserId));
    }
}