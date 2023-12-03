package com.example.wt_laba2.Jsp.impl;

import com.example.wt_laba2.bean.JSPNameList;
import com.example.wt_laba2.bean.Product;
import com.example.wt_laba2.dao.ProductDao;
import com.example.wt_laba2.controller.logic.exception.CommandException;
import com.example.wt_laba2.dao.exception.DAOException;
import com.example.wt_laba2.dao.factory.DAOFactory;
import com.example.wt_laba2.controller.logic.JSPPAge;
import com.example.wt_laba2.service.ProductService;
import com.example.wt_laba2.service.exception.ServiceException;
import com.example.wt_laba2.service.factory.ServiceFactory;
import jakarta.servlet.http.HttpServletRequest;

import java.util.List;

/**
 * Represents the logic for the main page of the application.
 */
public class MainPage implements JSPPAge {

    /**
     * Executes the logic related to displaying the main page.
     * @param request The HttpServletRequest object.
     * @return A String representing the page or resource to redirect or display.
     * @throws CommandException If an error occurs while executing the command.
     */
    @Override
    public String execute(HttpServletRequest request) throws CommandException {
        List<Product> list = null;
        String result = "";
        ProductService productService = null;
        String category = null;
        try {
            category = request.getParameter("category");
            productService = ServiceFactory.getInstance().getProductService();
            if (category == null || category.isEmpty()){
                list = productService.GetAllProduct();
            } else {
                list = productService.GetProductListByCat(category);
            }

            result = JSPNameList.MAIN_PAGE;
            request.setAttribute("products", list);
            request.getSession().setAttribute("products", list);

        } catch (ServiceException ex){
            throw new CommandException("Page Error", ex);
        }
        return result;
    }
}
