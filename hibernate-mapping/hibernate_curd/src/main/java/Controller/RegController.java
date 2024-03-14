package Controller;

import Dao.RegDao;
import Vo.RegVo;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@WebServlet(name = "RegController", value = "/RegController")
public class RegController extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String flag = request.getParameter("flag");

        if (flag.equals("search")){
            search(request,response);
        } else if (flag.equals("delete")) {
            delete(request,response);
            search(request,response);
        } else if (flag.equals("edit")) {
            edit(request,response);
            response.sendRedirect("editUser.jsp");
        }
    }

    private void edit(HttpServletRequest request, HttpServletResponse response) throws ServletException,IOException {
       int editUserId = Integer.parseInt(request.getParameter("id"));

       RegVo regVo = new RegVo();
       regVo.setId(editUserId);

       List editUser = new ArrayList();
       RegDao regDao = new RegDao();
       editUser = regDao.edit(regVo);
        System.out.println("edit Size: "+editUser.size());

       HttpSession httpSession = request.getSession();
       httpSession.setAttribute("editUserData",editUser);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String flag = request.getParameter("flag");

        if (flag.equals("insert")) {
            insert(request, response);
            search(request, response);
        } else if (flag.equals("update")) {
            update(request,response);
            search(request,response);

        }
    }

    private void update(HttpServletRequest request, HttpServletResponse response) {
       int updateId =  Integer.parseInt(request.getParameter("id"));
       String updateFirstName = request.getParameter("firstName");
       String updateLastName = request.getParameter("lastName");

       RegVo regVo = new RegVo();
       regVo.setId(updateId);
       regVo.setFirstName(updateFirstName);
       regVo.setLastName(updateLastName);

       RegDao regDao =new RegDao();
       regDao.update(regVo);
    }

    protected void insert(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RegVo regVo = new RegVo();

        regVo.setFirstName(request.getParameter("firstName"));
        regVo.setLastName(request.getParameter("lastName"));

        RegDao regDao = new RegDao();
        regDao.save(regVo);
    }

    private void search(HttpServletRequest request, HttpServletResponse response) {

        RegDao regDao = new RegDao();
        List data = regDao.search();
        System.out.println("list.size() = " + data.size());

        HttpSession httpSession = request.getSession();
        httpSession.setAttribute("list", data);

        try {
            response.sendRedirect("search.jsp");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    protected void delete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int deleteId = Integer.parseInt(request.getParameter("id"));

        RegVo regVo = new RegVo();
        regVo.setId(deleteId);

        RegDao regDao = new RegDao();
        regDao.delete(regVo);
    }
}
