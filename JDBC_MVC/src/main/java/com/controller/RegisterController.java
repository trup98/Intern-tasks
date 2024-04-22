package com.controller;

import com.Dao.RegisterDao;
import com.Vo.RegisterVo;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

/**
 * Servlet implementation class RegisterController
 */
@WebServlet("/RegisterController")
public class RegisterController extends HttpServlet {
  private static final long serialVersionUID = 1L;

  /**
   * @see HttpServlet#HttpServlet()
   */
  public RegisterController() {
    super();
    // TODO Auto-generated constructor stub
  }

  /**
   * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
   * response)
   */
  protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
    // TODO Auto-generated method stub
    String f = request.getParameter("flag");
    if (f.equals("edit")) {
      edit(request, response);
    } else if (f.equals("delete")) {

      delete(request, response);
      search(request, response);
      response.sendRedirect("display.jsp");
    }

  }

  /**
   * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
   * response)
   */
  protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
    // TODO Auto-generated method stub
    doGet(request, response);
    String flag = request.getParameter("flag");

    if (flag.equals("insert")) {
      insert(request, response);
      search(request, response);
    } else if (flag.equals("search")) {
      search(request, response);
    } else if (flag.equals("update")) {
      update(request, response);
      search(request, response);
      response.sendRedirect("display.jsp");
    }
  }

  protected void insert(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {

    String firstName = request.getParameter("fn");
    String lastName = request.getParameter("ln");

    RegisterVo regVo = new RegisterVo();
    regVo.setFirstName(firstName);
    regVo.setLastName(lastName);

    RegisterDao regDao = new RegisterDao();
    regDao.save(regVo);
    response.sendRedirect("display.jsp");


  }

  protected void search(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {

    String str = request.getParameter("data");

    RegisterDao d = new RegisterDao();
    List ls = d.search(str);
    System.out.print(">>>>>>>>>>" + ls.size());

    HttpSession session = request.getSession();
    session.setAttribute("data", ls);

  }

  protected void edit(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    String id = request.getParameter("id");
    System.out.println(id);

    RegisterDao d = new RegisterDao();
    RegisterVo v = new RegisterVo();
    d.edit(id);
    List ls = d.edit(id);

    HttpSession session = request.getSession();

    session.setAttribute("data", ls);
    response.sendRedirect("edit.jsp");

  }

  private void update(HttpServletRequest request, HttpServletResponse response) {
    // TODO Auto-generated method stub
    RegisterVo v = new RegisterVo();
    RegisterDao d = new RegisterDao();
    int id = Integer.parseInt(request.getParameter("id"));
    String fn = request.getParameter("fn");
    String ln = request.getParameter("ln");

    v.setId(id);
    v.setFirstName(fn);
    v.setLastName(ln);

    d.update(v);

  }

  private void delete(HttpServletRequest request, HttpServletResponse response) {
    // TODO Auto-generated method stub
    RegisterVo v = new RegisterVo();
    RegisterDao d = new RegisterDao();
    String id = request.getParameter("id");

    d.delete(id);

  }


}
