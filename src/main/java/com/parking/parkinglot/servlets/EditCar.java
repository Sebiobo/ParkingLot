package com.parking.parkinglot.servlets;

import com.parking.parkinglot.common.CarDto;
import com.parking.parkinglot.common.UserDto;
import com.parking.parkinglot.ejb.CarsBean;
import com.parking.parkinglot.ejb.UserBean;
import jakarta.inject.Inject;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "EditCar", value = "/EditCar")
public class EditCar extends HttpServlet {
    @Inject
    UserBean userBean;

    @Inject
    CarsBean carsBean;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<UserDto> users=userBean.findAllUsers();
        request.setAttribute("users",users);

        Long carId=Long.parseLong(request.getParameter("id"));
        CarDto car =carsBean.findById(carId);
        request.setAttribute("car",car);
        request.getRequestDispatcher("/WEB-INF/pages/editCar.jsp").forward(request,response);
    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


        String licensePlate = request.getParameter("licensePlate");
        String parkingSpot = request.getParameter("parkingSpot");
        String ownerId = request.getParameter("owner_id");
        String carId = request.getParameter("car_id");


        carsBean.updateCar(
                Long.parseLong(carId),
                licensePlate,
                parkingSpot,
                Long.parseLong(ownerId)
        );

        response.sendRedirect(request.getContextPath() + "/Cars");
    }
}
