package com.srgstart.exception;

import com.srgstart.result.Result;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;

/**
 * @Author srgstart
 * @Create 2021/3/18 20:36
 * @Description 全局捕获异常
 */
@ControllerAdvice
public class GlobalExceptionHandler {

    private final String ERROR = "error";

    // 自定义异常
    @ExceptionHandler(PermissionException.class)
    public ModelAndView noPermission(PermissionException e){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName(ERROR);
        modelAndView.addObject("msg",e.getMessage());
        return modelAndView;
    }

    // 全局异常
    @ExceptionHandler(Exception.class)
    @ResponseBody
    public Map<String,Object> runtimeException(RuntimeException e){
        e.printStackTrace();
        return Result.getInstance().error().getMap();
    }


}
