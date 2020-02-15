package nju.agile.dianping.common;

import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.NoHandlerFoundException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Description: TODO
 */
@ControllerAdvice
// 所有执行的controller都会被这个切面包含
public class GlobalExceptionHandler {
    @ExceptionHandler(Exception.class)
    @ResponseBody
    // 所有controller抛出异常或者继承Excepion的异常，则进行下面方法的处理
    public CommonRes doError(HttpServletRequest servletRequest, HttpServletResponse httpServletResponse, Exception ex){
        if (ex instanceof BusinessException){
            return CommonRes.create(((BusinessException)ex).getCommonError(), "fail");
        }else if(ex instanceof NoHandlerFoundException){
            CommonError commonError = new CommonError(EmBusinessError.NO_HANDLER_FOUND);
            return CommonRes.create(commonError,"fail");
        }else if(ex instanceof ServletRequestBindingException){
            CommonError commonError = new CommonError(EmBusinessError.BIND_EXCEPTION_ERROR);
            return CommonRes.create(commonError,"fail");
        }else{
            CommonError commonError = new CommonError(EmBusinessError.UNKNOWN_ERROR);
            return CommonRes.create(commonError,"fail");
        }
    }
}
