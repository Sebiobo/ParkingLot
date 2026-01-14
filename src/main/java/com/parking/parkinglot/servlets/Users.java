package com.parking.parkinglot.servlets;

import com.parking.parkinglot.common.UserDto;
import com.parking.parkinglot.ejb.InvoiceBean;
import com.parking.parkinglot.ejb.UserBean;
import jakarta.annotation.security.DeclareRoles;
import jakarta.inject.Inject;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
@DeclareRoles({"READ_USERS", "WRITE_USERS"})
@ServletSecurity(value = @HttpConstraint(rolesAllowed = {"READ_USERS"}),
        httpMethodConstraints = {@HttpMethodConstraint(value = "POST", rolesAllowed = {"WRITE_USERS"})})
@WebServlet(name = "Users", value = "/Users")
public class Users extends HttpServlet{
    @Inject
    UserBean userBean;
    @Inject
    private InvoiceBean invoiceBean;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws
            ServletException, IOException{
        List<UserDto> users=userBean.findAllUsers();
        request.setAttribute("users",users);
        if(!invoiceBean.getUsersIds().isEmpty()){
            Collection<String> username=userBean.findUsernamesByUserIds(invoiceBean.getUsersIds());
            request.setAttribute("invoices",username);
        }
        request.getRequestDispatcher("/WEB-INF/pages/users.jsp").forward(request,response);

    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws
            ServletException, IOException {
        String[] userIdsAsString = request.getParameterValues("user_Ids");
        if(userIdsAsString != null){
            List<Long> userIds = new ArrayList<>();
            for (String userIdAsString : userIdsAsString) {
                userIds.add(Long.parseLong(userIdAsString));
            }
            invoiceBean.getUsersIds().addAll(userIds);
        }
        response.sendRedirect(request.getContextPath()+"/Users");
    }
}
