package com.controller;

import com.dao.EmployeeDao;
import com.dao.ProjectDao;
import com.model.EmployeeVo;
import com.model.ProjectVo;

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
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String flag = req.getParameter("flag");
        if ("search".equals(flag)) {
            search(req, resp);
        } else if ("edit".equals(flag)) {
            edit(req, resp);
        } else if ("delete".equals(flag)) {
            delete(req, resp);
        }
    }

    private void delete(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        Long deleteId = Long.parseLong(req.getParameter("id"));
        EmployeeVo employeeVo = new EmployeeVo();
        employeeVo.setId(deleteId);

        EmployeeDao employeeDao = new EmployeeDao();
        employeeDao.delete(employeeVo);

        resp.sendRedirect("searchEmployee.jsp");
    }

    private void edit(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        Long editId = Long.parseLong(req.getParameter("id"));
        EmployeeVo employeeVo = new EmployeeVo();
        employeeVo.setId(editId);

        EmployeeDao employeeDao = new EmployeeDao();
        List<EmployeeVo> employeeVoList = employeeDao.edit(employeeVo);

        HttpSession httpSession = req.getSession();
        httpSession.setAttribute("data", employeeVoList);

        ProjectDao projectDao = new ProjectDao();
        List listProject = projectDao.search();

        HttpSession session = req.getSession();
        session.setAttribute("dataProject", listProject);

        resp.sendRedirect("editEmployee.jsp");

    }


    protected void search(HttpServletRequest request, HttpServletResponse response) throws IOException {
        EmployeeDao employeeDao = new EmployeeDao();
        List list = employeeDao.search();
        HttpSession httpSession = request.getSession();
        httpSession.setAttribute("data", list);
        response.sendRedirect("searchEmployee.jsp");

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String flag = req.getParameter("flag");
        if ("insert".equals(flag)) {
            insert(req, resp);
        } else if ("update".equals(flag)) {
            update(req, resp);
        }
    }

    private void update(HttpServletRequest req, HttpServletResponse resp) {
        Long updateID = Long.parseLong(req.getParameter("updateEmployeeId"));
        String updateName = req.getParameter("updateEmployeeName");
        String[] projectId = req.getParameterValues("projectId");
        Arrays.toString(projectId);

        EmployeeVo employeeVo = new EmployeeVo();
        employeeVo.setId(updateID);
        employeeVo.setEmployeeName(updateName);
        Set<EmployeeVo> employeeVoSet = new HashSet<EmployeeVo>();
        employeeVoSet.add(employeeVo);

        EmployeeDao employeeDao = new EmployeeDao();
        employeeDao.update(employeeVo);

        Set<ProjectVo> projectVoSet = new HashSet<ProjectVo>();

        for (String s : projectId) {
            ProjectVo projectVo = new ProjectVo();
            projectVo.setId(Long.parseLong(Arrays.toString(projectId)));
            projectVoSet.add(projectVo);
            projectVo.setEmployeeVos(employeeVoSet);

            ProjectDao projectDao = new ProjectDao();
            projectDao.update(projectVo);
        }
    }

    private void insert(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String employeeName = request.getParameter("employeeName");
        EmployeeVo employeeVo = new EmployeeVo();
        employeeVo.setEmployeeName(employeeName);

        EmployeeDao employeeDao = new EmployeeDao();
        employeeDao.insert(employeeVo);
        response.sendRedirect("addEmployee.jsp");
    }
}
