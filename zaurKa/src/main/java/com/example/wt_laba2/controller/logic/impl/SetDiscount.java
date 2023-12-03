package com.example.wt_laba2.controller.logic.impl;

import com.example.wt_laba2.bean.JSPNameList;
import com.example.wt_laba2.controller.logic.ICommand;
import com.example.wt_laba2.controller.logic.exception.CommandException;
import com.example.wt_laba2.dao.exception.DAOException;
import com.example.wt_laba2.service.exception.ServiceException;
import com.example.wt_laba2.service.factory.ServiceFactory;
import com.example.wt_laba2.service.ProductService;
import jakarta.servlet.http.HttpServletRequest;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;

/**
 * The SetDiscount class implements ICommand to handle setting discounts for products.
 * It interacts with the ProductService to set a discount for a specific product based on the provided information.
 */
public class SetDiscount implements ICommand {

    /**
     * Executes the action to set a discount for a product based on the provided HttpServletRequest.
     *
     * @param request The HttpServletRequest containing information about the product and discount setting.
     * @return A String representing the JSP name to navigate after setting the discount.
     * @throws CommandException           If an error occurs while executing the discount setting action.
     * @throws ParserConfigurationException If there's an issue with the parser configuration.
     * @throws IOException                If an I/O exception occurs during execution.
     * @throws DAOException               If there's an issue with the Data Access Object.
     */
    @Override
    public String execute(HttpServletRequest request) throws CommandException, ParserConfigurationException, IOException, DAOException {
        ProductService productService = null;
        try {
            productService = ServiceFactory.getInstance().getProductService();
            int productId = Integer.parseInt(request.getParameter("discountProductId"));

            String discount = request.getParameter("discountAmount");
            int discountValue = Integer.parseInt(discount);
            productService.SetDiscount(productId, discountValue);


        } catch (ServiceException ex) {
            throw new CommandException("Error occurred while setting the discount.", ex);
        }
        return JSPNameList.ADMINISTRATOR_PAGE;
    }
}

