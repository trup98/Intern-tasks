package com.controller;

import com.dao.LoginDao;
import com.dao.RegisterDao;
import com.model.LoginVo;
import com.model.RegisterVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class RegisterController {

    @Autowired
    RegisterDao registerDao;

    @Autowired
    LoginDao loginDao;

    @RequestMapping(value = "load", method = RequestMethod.GET)
    public ModelAndView load() {
        return new ModelAndView("RegisterPage", "RegVo", new RegisterVo());
    }

    @RequestMapping(value = "insert", method = RequestMethod.POST)
    public ModelAndView insert(@ModelAttribute RegisterVo registerVo) {

        LoginVo loginVo = registerVo.getLoginVo();
        loginDao.insert(loginVo);

        registerDao.insert(registerVo);

        return new ModelAndView("redirect:/search");
    }

    @RequestMapping(value = "search", method = RequestMethod.GET)
    public ModelAndView search() {

        List<RegisterVo> registerVos = registerDao.search();
        System.out.println("registerVos.size() ========== " + registerVos.size());

        return new ModelAndView("search", "searchList", registerVos);
    }

    @RequestMapping(value = "delete", method = RequestMethod.GET)
    public ModelAndView delete(@ModelAttribute RegisterVo registerVo, @RequestParam int id) {

        registerVo.setRegId(id);
        registerDao.delete(registerVo);

        LoginVo loginVo = new LoginVo();
        loginVo.setId(id);
        loginDao.delete(loginVo);

        return new ModelAndView("redirect:/search");
    }

    @RequestMapping(value = "edit", method = RequestMethod.GET)
    public ModelAndView edit(@ModelAttribute RegisterVo registerVo, @RequestParam int id) {
        LoginVo loginVo = new LoginVo();

        registerVo.setRegId(id);
        loginVo.setId(id);

        List<RegisterVo> registerVos = registerDao.findById(registerVo);
        RegisterVo registerVo1 = registerVos.get(0);

        return new ModelAndView("RegisterPage", "RegVo", registerVo1);
    }
}
