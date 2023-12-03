package com.example.wt_laba2.controller.logic.impl;

import com.example.wt_laba2.bean.PageNameConstants;
import com.example.wt_laba2.controller.logic.ICommand;
import com.example.wt_laba2.controller.logic.exception.CommandException;
import com.example.wt_laba2.dao.exception.DAOException;
import com.example.wt_laba2.service.exception.ServiceException;
import com.example.wt_laba2.service.factory.ServiceFactory;
import com.example.wt_laba2.service.UserService;
import jakarta.servlet.http.HttpServletRequest;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;

/**

 The RemoveBanCommand class implements ICommand to handle removing bans from users.

 It interacts with the UserDao to remove the ban for a specific user based on the provided user ID.
 */
public class RemoveBanCommand implements ICommand {

    /**

     Executes the action to remove a ban from a user based on the provided HttpServletRequest.

     @param request The HttpServletRequest containing information about the user and ban removal.

     @return A String representing the JSP name to navigate after removing the ban.

     @throws CommandException If an error occurs while executing the ban removal action.

     @throws ParserConfigurationException If there's an issue with the parser configuration.

     @throws IOException If an I/O exception occurs during execution.

     @throws DAOException If there's an issue with the Data Access Object.
     */
    @Override
    public String execute(HttpServletRequest request) throws CommandException, ParserConfigurationException, IOException, DAOException {
        UserService userService = null;
        try {
            userService = ServiceFactory.getInstance().getUserService();
            int userId = Integer.parseInt(request.getParameter("userId"));
            userService.removeBan(userId);

        } catch (ServiceException ex) {
            throw new CommandException("Error occurred while removing the ban.", ex);
        }

        return PageNameConstants.ADMINISTRATOR_PAGE;
    }
}