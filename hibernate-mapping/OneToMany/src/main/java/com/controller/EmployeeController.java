package com.controller;

import com.dao.EmployeeDao;
import com.vo.EmployeeVo;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Collections;
import java.util.List;

@WebServlet(name = "EmployeeController", value = "/EmployeeController")
public class EmployeeController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String flag = req.getParameter("add");
        searchEmployee(req,resp);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String flag = req.getParameter("flag");
        if (flag.equals("insert")) {
            insert(req, resp);
            searchEmployee(req, resp);
        }
    }

    private void searchEmployee(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        EmployeeDao employeeDao = new EmployeeDao();
        List list = employeeDao.search();
        System.out.println("Employee List Size = " + list.size());
        HttpSession httpSession = req.getSession();
        httpSession.setAttribute("empData",list);
        resp.sendRedirect("addEmployee.jsp");
    }

    private void insert(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        EmployeeVo employeeVo = new EmployeeVo();
        employeeVo.setEmployeeName(req.getParameter("employeeName"));

        EmployeeDao employeeDao =new EmployeeDao();
        employeeDao.insert(Collections.singletonList(employeeVo));

        List<EmployeeVo> list = employeeDao.search();
        System.out.println("Employee List Size = " + list.size());

        HttpSession httpSession = req.getSession();
        httpSession.setAttribute("empData",list);

        resp.sendRedirect("addEmployee.jsp");
    }
}
