package com.controller;

import com.Dao.RegisterDao;
import com.Model.RegisterVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.List;


@Controller
public class RegisterController {

  @Autowired
  RegisterDao registerDao;

//    final1
//    RegisterDao registerDao;
//
//    public RegisterController(RegisterDao registerDao) {
//        this.registerDao = registerDao;
//    }

  @RequestMapping(value = "/home", method = RequestMethod.GET)
  public ModelAndView load() {
    return new ModelAndView("RegisterPage", "RegVo", new RegisterVo());
  }

  @RequestMapping(value = "insert.html", method = RequestMethod.POST)
  public ModelAndView insert(@ModelAttribute RegisterVo registerVo) {

    registerDao.insert(registerVo);
    return new ModelAndView("redirect:/search.html");
  }

  @RequestMapping(value = "search.html", method = RequestMethod.GET)
  public ModelAndView search() {

    List search = registerDao.search();
    return new ModelAndView("search", "SearchList", search);
  }

  @RequestMapping(value = "delete.html", method = RequestMethod.GET)
  public ModelAndView delete(@ModelAttribute RegisterVo registerVo, HttpServletRequest request, @RequestParam int id) {

    registerVo.setId(id);
    registerDao.delete(registerVo);
    return new ModelAndView("redirect:/search.html");
  }

  @RequestMapping(value = "edit.html", method = RequestMethod.GET)
  public ModelAndView edit(@ModelAttribute RegisterVo registerVo, HttpServletRequest request, @RequestParam int id) {
    registerVo.setId(id);
    List<RegisterVo> editUser = registerDao.edit(registerVo);
    RegisterVo vo = editUser.get(0);
    return new ModelAndView("RegisterPage", "RegVo", vo);
  }
}
