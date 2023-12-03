package com.example.wt_laba2.controller.logic.impl;

import com.example.wt_laba2.bean.JSPNameList;
import com.example.wt_laba2.bean.SessionAtributes;
import com.example.wt_laba2.bean.User;
import com.example.wt_laba2.controller.logic.exception.CommandException;
import com.example.wt_laba2.service.exception.ServiceException;
import com.example.wt_laba2.service.factory.ServiceFactory;
import com.example.wt_laba2.controller.logic.ICommand;
import com.example.wt_laba2.service.UserService;
import jakarta.servlet.http.HttpServletRequest;


/**
 * The Register class implements ICommand to handle user registration functionality.
 * It interacts with the UserDao to register a new user based on provided information.
 */
public class Register implements ICommand {

    /**
     * Executes the action to register a new user based on the provided HttpServletRequest.
     *
     * @param request The HttpServletRequest containing information about the user registration.
     * @return A String representing the JSP name to navigate after registering the user.
     * @throws CommandException           If an error occurs while executing the user registration action.
     */
    @Override
    public String execute(HttpServletRequest request) throws CommandException {
        User user = new User();
        UserService userService = null;
        try {
            userService = ServiceFactory.getInstance().getUserService();

            user.setLogin(request.getParameter("Login"));
            user.setPassword(request.getParameter("Password"));
            if (user.getPassword().equals(request.getParameter("confirm-password"))) {
                int userId = userService.registration(user);
                request.setAttribute("SomeMessage", "Successful registration");
                request.getSession().setAttribute(SessionAtributes.Authorized, true);
                request.getSession().setAttribute(SessionAtributes.UserId, userId);
            } else {
                request.setAttribute("IncorrectData", "Passwords are not the same");
            }

        } catch (ServiceException ex) {
            throw new CommandException("Error occurred during user registration.", ex);
        }
        return JSPNameList.MAIN_PAGE;
    }
}

