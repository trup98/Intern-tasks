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
        } else if (flag.equals("edit")) {
            edit(request,response);
        }
    }

    private void edit(HttpServletRequest request, HttpServletResponse response)throws ServletException,IOException {
        int editId = Integer.parseInt(request.getParameter("id"));

        RegisterVo vo = new RegisterVo();
        vo.setId(editId);

        RegisterDao registerDao = new RegisterDao();
        List<RegisterVo> editList =  registerDao.findById(vo);

        HttpSession httpSession = request.getSession();
        httpSession.setAttribute("data",editList);

        response.sendRedirect("updatePage.jsp");
    }

    private void delete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        int id = Integer.parseInt(request.getParameter("id"));
        System.out.println("Register : " + id);

        RegisterVo registerVo = new RegisterVo();
        registerVo.setId(id);


        RegisterDao registerDao = new RegisterDao();

        List<RegisterVo> findId = registerDao.findById(registerVo);
        RegisterVo vo = findId.get(0);

        LoginVo loginId = vo.getLoginVo();

        LoginDao loginDao = new LoginDao();

        loginDao.delete(loginId);
        registerDao.delete(registerVo);




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
        } else if (flag.equals("update")) {
            update(request,response);
            search(request,response);
        }

    }

    private void update(HttpServletRequest request, HttpServletResponse response) throws ServletException,IOException {
        int updateIdLogin = Integer.parseInt(request.getParameter("idLogin"));
        String userName = request.getParameter("userName");
        String passWord = request.getParameter("passWord");
        LoginVo loginVo = new LoginVo();
        loginVo.setId(updateIdLogin);
        loginVo.setUserName(userName);
        loginVo.setPassWord(passWord);

        LoginDao loginDao =  new LoginDao();
        loginDao.update(loginVo);

        int updateIdRegister = Integer.parseInt(request.getParameter("idReg"));
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        RegisterVo vo = new RegisterVo();
        vo.setId(updateIdRegister);
        vo.setFirstName(firstName);
        vo.setLastName(lastName);

        RegisterDao registerDao = new RegisterDao();
        registerDao.update(vo);
    }

    private void insert(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RegisterVo registerVo = new RegisterVo();
        LoginVo loginVo = new LoginVo();

        RegisterDao registerDao = new RegisterDao();
        LoginDao loginDao = new LoginDao();


        loginVo.setUserName(request.getParameter("userName"));
        loginVo.setPassWord(request.getParameter("passWord"));

        loginDao.insert(loginVo);


        registerVo.setFirstName(request.getParameter("firstName"));
        registerVo.setLastName(request.getParameter("lastName"));
        registerVo.setLoginVo(loginVo);

        registerDao.insert(registerVo);


    }
}