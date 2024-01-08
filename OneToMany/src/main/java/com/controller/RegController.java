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
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@WebServlet(name = "RegController", value = "/RegController")
public class RegController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String flag = req.getParameter("add");
        search(req,resp);

        if (flag.equals("search")) {
            search(req, resp);
        } else if (flag.equals("delete")) {
            delete(req, resp);
            search(req, resp);
        } else if (flag.equals("edit")) {
            edit(req, resp);
        } else if (flag.equals("update")) {
            update(req, resp);
        }
    }



    private void update(HttpServletRequest req, HttpServletResponse resp) {
        int cId = Integer.parseInt(req.getParameter("cId"));
        int id = Integer.parseInt(req.getParameter("id"));
        System.out.println("COMAPNAY ID:" + cId);
        System.out.println("REGISTER ID:" + id);

        String cName = req.getParameter("cName");
        String cAdd = req.getParameter("cAdd");
        String eName = req.getParameter("eName");

        CompanyVo companyVo = new CompanyVo();
        companyVo.setId(cId);
        companyVo.setCompanyName(cName);
        companyVo.setCompanyAddress(cAdd);

        CompanyDao companyDao = new CompanyDao();
        companyDao.update(companyVo);

        EmployeeVo employeeVo = new EmployeeVo();
        employeeVo.setId(id);
        employeeVo.setEmployeeName(eName);

        EmployeeDao employeeDao = new EmployeeDao();
        employeeDao.update(employeeVo);

    }

    private void edit(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        int editId = Integer.parseInt(req.getParameter("id"));

        CompanyVo companyVo = new CompanyVo();
        companyVo.setId(editId);

        CompanyDao companyDao = new CompanyDao();
        List<CompanyVo> list = companyDao.findById(companyVo);

        HttpSession httpSession = req.getSession();
        httpSession.setAttribute("editList", list);

        resp.sendRedirect("updatePage.jsp");
    }

    private void delete(HttpServletRequest req, HttpServletResponse resp) {
        int deleteId = Integer.parseInt(req.getParameter("id"));
        System.out.println("ID:---------------------------" + deleteId);

        CompanyVo companyVo = new CompanyVo();
        companyVo.setId(deleteId);

        CompanyDao companyDao = new CompanyDao();
        companyDao.delete(companyVo);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
        String flag = req.getParameter("flag");
        if (flag.equals("insert")) {
            insert(req, resp);
            search(req, resp);
        }
    }

    private void search(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<EmployeeVo> voList;
        EmployeeDao employeeDao = new EmployeeDao();
        voList = employeeDao.search();

        HttpSession httpSession = req.getSession();
        httpSession.setAttribute("data", voList);

        resp.sendRedirect("search.jsp");
    }

    private void insert(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//      save company
        CompanyVo companyVo = new CompanyVo();
        companyVo.setCompanyName(req.getParameter("companyName"));
        companyVo.setCompanyAddress(req.getParameter("cAdd"));

        CompanyDao companyDao = new CompanyDao();
        companyDao.insert(companyVo);

        List<String> emp = Arrays.asList(req.getParameterValues("employees"));

        List<EmployeeVo> ls = new ArrayList<>();

        emp.forEach(employee -> {
            EmployeeVo employeeVo = new EmployeeVo();
            employeeVo.setEmployeeName(employee);
            employeeVo.setCompanyVo(companyVo);
            ls.add(employeeVo);
        });

        // save employees
        EmployeeDao employeeDao = new EmployeeDao();
        employeeDao.insert(ls);
    }
}
