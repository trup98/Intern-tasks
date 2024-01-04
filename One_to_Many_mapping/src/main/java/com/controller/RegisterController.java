package com.controller;

import com.dao.CompanyDao;
import com.dao.EmplyooDao;
import com.vo.Company;
import com.vo.Employee;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "RegisterController",value = "/RegisterController")
public class RegisterController extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);

        String flag = req.getParameter("flag");

        if (flag.equals("insert")){
            insert(req,resp);
        }

    }

    private void insert(HttpServletRequest req, HttpServletResponse resp) throws ServletException,IOException {
        try {
            Company company = new Company();
            Employee employee = new Employee();

            String cName = req.getParameter("cName");
            String eName = req.getParameter("eName");

            employee.setEmployeeName(eName);
            EmplyooDao emplyooDao = new EmplyooDao();
            emplyooDao.insert(employee);

            List<Employee> list = new ArrayList<Employee>();
            list.add(employee);

            company.setCompanyName(cName);
            company.setEmployee(list);

            CompanyDao companyDao =new CompanyDao();
            companyDao.insert(company);

        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doGet(req, resp);
    }

}
