package com.example.wt_laba2.service.impl;

import com.example.wt_laba2.bean.ShoppingItem;
import com.example.wt_laba2.dao.OrderDao;
import com.example.wt_laba2.dao.exception.DAOException;
import com.example.wt_laba2.service.exception.ServiceException;
import com.example.wt_laba2.dao.factory.DAOFactory;
import com.example.wt_laba2.service.OrderService;

import java.util.List;

public class OrderServiceImpl implements OrderService {
    @Override
    public void createOrder(String address, List<ShoppingItem> cart) throws ServiceException {
        try{
            if (address != null && cart != null) {
                OrderDao orderDao = DAOFactory.getFactory().getOrderDao();
                orderDao.CreateOrder(address, cart);
            }
        }catch (DAOException ex){
            throw new ServiceException(ex);
        }

    }
}
