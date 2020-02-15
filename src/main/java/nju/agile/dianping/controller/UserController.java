package nju.agile.dianping.controller;

import nju.agile.dianping.common.BusinessException;
import nju.agile.dianping.common.CommonRes;
import nju.agile.dianping.common.CommonUtil;
import nju.agile.dianping.common.EmBusinessError;
import nju.agile.dianping.model.UserModel;
import nju.agile.dianping.request.LoginReq;
import nju.agile.dianping.request.RegisterReq;
import nju.agile.dianping.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;

/**
 * @Description: TODO
 */
@Controller("/user")
@RequestMapping("/user")
public class UserController {

    // 集成 http session
    public static final String CURRENT_USER_SESSION = "currentUserSession";

    // 通过HttpServletRequest可以拿到当前线程处理用户请求的request对象
    @Autowired
    private HttpServletRequest httpServletRequest;

    @Autowired
    private UserService userService;


    @RequestMapping("/get")
    @ResponseBody
    public CommonRes getUser(@RequestParam(name="id")Integer id) throws BusinessException {
        UserModel userModel = userService.getUser(id);
        if (userModel == null) {
            // 这里抛出一个自定义异常，Springboot中利用AspectJ动态代理捕捉异常
            throw new BusinessException(EmBusinessError.NO_OBJECT_FOUND);

        }else{
            return CommonRes.create(userModel);
        }
    }

    @RequestMapping("/register")
    @ResponseBody
    public CommonRes register(@Valid @RequestBody RegisterReq registerReq, BindingResult bindingResult) throws BusinessException,
            UnsupportedEncodingException, NoSuchAlgorithmException {

        if (bindingResult.hasErrors()) {
            throw new BusinessException(EmBusinessError.PARAMETER_VALIDATION_ERROR, CommonUtil.processErrorString(bindingResult));
        }
        UserModel registerUser = new UserModel();
        registerUser.setTelphone(registerReq.getTelphone());
        registerUser.setPassword(registerReq.getPassword());
        registerUser.setNickName(registerReq.getNickName());
        registerUser.setGender(registerReq.getGender());

        UserModel resUserModel = userService.register(registerUser);
        return CommonRes.create(resUserModel);
    }

    @RequestMapping("/login")
    @ResponseBody
    public CommonRes login(@RequestBody @Valid LoginReq loginReq, BindingResult bindingResult) throws BusinessException, UnsupportedEncodingException, NoSuchAlgorithmException {
        if (bindingResult.hasErrors()) {
            throw new BusinessException(EmBusinessError.PARAMETER_VALIDATION_ERROR, CommonUtil.processErrorString(bindingResult));
        }

        UserModel userModel = userService.login(loginReq.getTelphone(), loginReq.getPassword());

        // 用户登录成功之后,将userModel对象放到用户对应的http session中，并且attribute name是CURRENT_USER_SESSION
        httpServletRequest.getSession().setAttribute(CURRENT_USER_SESSION, userModel);
        return CommonRes.create(userModel);
    }

    @RequestMapping("/logout")
    @ResponseBody
    public CommonRes logout() {
        httpServletRequest.getSession().invalidate();
        return CommonRes.create(null);
    }


    @RequestMapping("/getcurrentuser")
    @ResponseBody
    // 获取当前用户信息
    public CommonRes getCurrentUser() {
        UserModel userModel = (UserModel) httpServletRequest.getSession().getAttribute(CURRENT_USER_SESSION);
        return CommonRes.create(userModel);
    }
}







































