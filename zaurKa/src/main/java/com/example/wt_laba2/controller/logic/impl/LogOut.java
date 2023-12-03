package com.example.wt_laba2.controller.logic.impl;

import com.example.wt_laba2.bean.PageNameConstants;
import com.example.wt_laba2.bean.Product;
import com.example.wt_laba2.controller.logic.ICommand;
import com.example.wt_laba2.dao.ProductDao;
import com.example.wt_laba2.controller.logic.exception.CommandException;
import com.example.wt_laba2.dao.exception.DAOException;
import com.example.wt_laba2.dao.factory.DAOFactory;
import jakarta.servlet.http.HttpServletRequest;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.List;

/**
 * The LogOut class implements ICommand to handle user logout functionality.
 * It invalidates the current session and prepares to redirect to the main page after logging out.
 */
public class LogOut implements ICommand {

    /**
     * Executes the action to log out the user based on the provided HttpServletRequest.
     *
     * @param request The HttpServletRequest containing information about the current user session.
     * @return A String representing the JSP name to navigate after logging out.
     * @throws CommandException           If an error occurs while executing the logout action.
     * @throws ParserConfigurationException If there's an issue with the parser configuration.
     * @throws IOException                If an I/O exception occurs during execution.
     * @throws DAOException               If there's an issue with the Data Access Object.
     */
    @Override
    public String execute(HttpServletRequest request) throws CommandException, ParserConfigurationException, IOException, DAOException {
        try {
            ProductDao productDao = DAOFactory.getFactory().getProductDao();
            List<Product> products = productDao.GetAllProduct();
            request.getSession().invalidate();
            request.setAttribute("products", products);

        } catch (DAOException ex) {
            throw new CommandException("Error occurred during logout process.", ex);
        }
        return PageNameConstants.MAIN_PAGE;
    }
}


