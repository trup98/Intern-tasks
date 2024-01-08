package com.controller;

import com.dao.EmployeeDao;
import com.model.EmployeeVo;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.*;

@WebServlet(name = "EmployeeController", value = "/EmployeeController")
public class EmployeeController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String flag = req.getParameter("flag");
        if ("add".equals(flag)) {
            search(req, resp);
        }
    }

    protected void search(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        EmployeeDao employeeDao = new EmployeeDao();
        List<EmployeeVo> list = employeeDao.search();
        HttpSession httpSession = request.getSession();
        httpSession.setAttribute("data", list);
        response.sendRedirect("addEmployee.jsp");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String flag = req.getParameter("flag");
        if ("insert".equals(flag)) {
            insert(req, resp);
        }
    }

    private void insert(HttpServletRequest request, HttpServletResponse response) {

        EmployeeVo employeeVo = new EmployeeVo();
        employeeVo.setEmployeeName(request.getParameter("employeeName"));

        EmployeeDao employeeDao = new EmployeeDao();
        employeeDao.insert(employeeVo);

        List<String> strings = Arrays.asList(request.getParameterValues("project"));

        List<String> employeeVoList =new ArrayList<>();

        strings.forEach(empl->{
            employeeVoList.add(empl);
        });
        employeeDao.insert((EmployeeVo) employeeVoList);

    }
}
