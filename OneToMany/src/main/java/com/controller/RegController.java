package com.controller;

import com.dao.CompanyDao;
import com.dao.EmployeeDao;
import com.vo.CompanyVo;
import com.vo.EmployeeVo;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "RegController",value = "/RegController")
public class RegController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doGet(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
        String flag = req.getParameter("flag");
        if(flag.equals("insert")){
            insert(req,resp);
        }
    }

    private void insert(HttpServletRequest req, HttpServletResponse resp) throws ServletException,IOException{
        String eName = req.getParameter("eName");

        EmployeeVo employeeVo =new EmployeeVo();
        employeeVo.setEmployeeName(eName);

        EmployeeDao employeeDao = new EmployeeDao();
        employeeDao.insert(employeeVo);

        String cName = req.getParameter("cName");
        String cAdd = req.getParameter("cAdd");

        CompanyVo companyVo = new CompanyVo();
        List<EmployeeVo> list = new ArrayList<EmployeeVo>();
        list.add(employeeVo);

        companyVo.setCompanyName(cName);
        companyVo.setCompanyAddress(cAdd);
        companyVo.setEmployeeVos(list);

        CompanyDao companyDao = new CompanyDao();
        companyDao.insert(companyVo);
    }
}
