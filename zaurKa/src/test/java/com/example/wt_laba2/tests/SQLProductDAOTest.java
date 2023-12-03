package com.example.wt_laba2.tests;

import com.example.wt_laba2.dao.ProductDao;
import com.example.wt_laba2.dao.exception.DAOException;
import com.example.wt_laba2.dao.pool.factory.ConnectionPoolFactory;
import com.example.wt_laba2.dao.factory.DAOFactory;
import com.example.wt_laba2.dao.pool.ConnectionPool;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SQLProductDAOTest {

    @Test
    void getProductListByCat() throws DAOException {
        try{
            ConnectionPool connectionPool = ConnectionPoolFactory.getInstance().getConnectionPool();

            connectionPool.CreateConnections();

        }catch (Exception ex){
            Assertions.fail("Problem with connection");
        }
        ProductDao productDao = DAOFactory.getFactory().getProductDao();
        assertNotNull(productDao.GetProductListByCat("Gaming laptops"));
    }

    @Test
    void getAllProduct() throws DAOException {
        try{
            ConnectionPool connectionPool = ConnectionPoolFactory.getInstance().getConnectionPool();

            connectionPool.CreateConnections();

        }catch (Exception ex){
            Assertions.fail("Problem with connection");
        }
        ProductDao productDao = DAOFactory.getFactory().getProductDao();
        assertNotNull(productDao.GetAllProduct());
    }

    @Test
    void setDiscount() throws DAOException {
        try{
            ConnectionPool connectionPool = ConnectionPoolFactory.getInstance().getConnectionPool();

            connectionPool.CreateConnections();

        }catch (Exception ex){
            Assertions.fail("Problem with connection");
        }
        ProductDao productDao = DAOFactory.getFactory().getProductDao();
        assertTrue(productDao.SetDiscount(14, 90));
    }

    @Test
    void addProduct() throws DAOException {
        try{
            ConnectionPool connectionPool = ConnectionPoolFactory.getInstance().getConnectionPool();

            connectionPool.CreateConnections();

        }catch (Exception ex){
            Assertions.fail("Problem with connection");
        }
        ProductDao productDao = DAOFactory.getFactory().getProductDao();
        assertTrue(productDao.AddProduct("TestProduct","250","Work laptops",null));
    }

    @Test
    void getProductById() throws DAOException {
        try{
            ConnectionPool connectionPool = ConnectionPoolFactory.getInstance().getConnectionPool();

            connectionPool.CreateConnections();

        }catch (Exception ex){
            Assertions.fail("Problem with connection");
        }
        ProductDao productDao = DAOFactory.getFactory().getProductDao();
        assertNotNull(productDao.GetProductById(14));
    }
}