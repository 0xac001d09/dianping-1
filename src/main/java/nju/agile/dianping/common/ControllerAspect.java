package nju.agile.dianping.common;

import nju.agile.dianping.controller.admin.AdminController;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;

/**
 * @Description: TODO
 */
@Aspect
@Configuration
public class ControllerAspect {

    @Autowired
    private HttpServletRequest httpServletRequest;

    @Autowired
    private HttpServletResponse httpServletResponse;

    // 作用在admin 包下的所有方法，并且这个方法被打上了RequestMapping标签
    @Around("execution(* nju.agile.dianping.controller.admin.*.*(..)) && @annotation(org.springframework.web.bind.annotation.RequestMapping)")
    public Object adminControllerBeforeValidation(ProceedingJoinPoint joinPoint) throws Throwable {
        Method method = ((MethodSignature)joinPoint.getSignature()).getMethod();
        AdminPermission adminPermission = method.getAnnotation(AdminPermission.class);

        if (adminPermission == null) {
            // 公共方法，放行
            Object resultObject = joinPoint.proceed();
            return resultObject;
        }
        // 判断管理员是否登录
        String email = (String) httpServletRequest.getSession().getAttribute(AdminController.CURRENT_ADMIN_SESSION);
        if (email == null) {
            if (adminPermission.produceType().equals("text/html")){
                httpServletResponse.sendRedirect("/login/login/loginpage");
                return null;
            }else{
                CommonError commonError = new CommonError(EmBusinessError.ADMIN_SHOULD_LOGIN);
                return CommonRes.create(commonError,"fail");

            }

        }else{
            // 已登录，放行
            Object resultObject = joinPoint.proceed();
            return resultObject;
        }

    }
}
