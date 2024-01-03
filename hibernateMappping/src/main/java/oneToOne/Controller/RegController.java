package oneToOne.Controller;

import oneToOne.Dao.LoginDao;
import oneToOne.Dao.RegisterDao;
import oneToOne.Vo.LoginVo;
import oneToOne.Vo.RegisterVo;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.List;


@WebServlet(name = "RegController", value = "/RegController")
public class RegController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String flag = request.getParameter("flag");
        if (flag.equals("search")) {
            search(request, response);
        } else if (flag.equals("delete")) {
            delete(request, response);
            search(request, response);
        }
    }
    private void delete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        System.out.println(id);
        LoginVo loginVo = new LoginVo();
        loginVo.setId(id);

        LoginDao loginDao = new LoginDao();
        loginDao.delete(loginVo);
//        RegisterVo registerVo = new RegisterVo();
//        registerVo.setId(id);
//
//        RegisterDao registerDao = new RegisterDao();
//        registerDao.delete(registerVo);
    }
    private void search(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List Regdata;
        RegisterDao dao = new RegisterDao();
        Regdata = dao.search();


        HttpSession httpSession = request.getSession();
        httpSession.setAttribute("searchdata", Regdata);
        response.sendRedirect("search.jsp");
    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String flag = request.getParameter("flag");
        if (flag.equals("insert")) {
            insert(request, response);
            search(request, response);
        }
    }
    private void insert(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RegisterVo registerVo = new RegisterVo();
        LoginVo loginVo = new LoginVo();


        RegisterDao registerDao = new RegisterDao();
        LoginDao loginDao = new LoginDao();

        loginVo.setUserName(request.getParameter("userName"));
        loginVo.setPassWord(request.getParameter("passWord"));

        registerVo.setFirstName(request.getParameter("firstName"));
        registerVo.setLastName(request.getParameter("lastName"));
        registerVo.setLoginVo(loginVo);

        registerDao.insert(registerVo);
        loginDao.insert(loginVo);

    }
}