package top.xiongmingcai.bbs.exception;


import com.baomidou.mybatisplus.extension.api.ApiController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@RestControllerAdvice
public class GlobalExceptionHandler extends ApiController {
    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    /**
     * 处理自定义的业务异常
     *
     * @param req
     * @param ex
     * @return
     */
    @ExceptionHandler(value = BussinessException.class)
    @ResponseBody
    public Object bizExceptionHandler(HttpServletRequest req, BussinessException ex) {
        logger.error("发生业务异常！原因是：{}", ex.getMessage());
        return failed(ex.getMessage());
    }

    /**
     * 处理其他异常
     *
     * @param req
     * @param e
     * @return
     */
    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public Object exceptionHandler(HttpServletRequest req, Exception e) {
        logger.error("未知异常！原因是:", e);
        BindException bindException = (BindException) e;
        if (bindException.hasErrors()) {
            List<ObjectError> allErrors = bindException.getAllErrors();

            List<String> result = new ArrayList();

            for (ObjectError objectError : allErrors) {
                String message = objectError.getDefaultMessage();
                String field = ((FieldError) objectError).getField();
                result.add(field + message);
            }
            return failed(result.toString());
        }

        return failed(e.getMessage());
    }

    //添加全局异常处理流程，根据需要设置需要处理的异常，本文以MethodArgumentNotValidException为例
    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public Object MethodArgumentNotValidHandler(HttpServletRequest request, MethodArgumentNotValidException exception) {
        //按需重新封装需要返回的错误信息
        List<String> result = new ArrayList<>();
        //解析原错误信息，封装后返回，此处返回非法的字段名称，原始值，错误信息
        for (FieldError error : exception.getBindingResult().getFieldErrors()) {
            String field = error.getField();
            Object rejectedValue = error.getRejectedValue();
            String message = error.getDefaultMessage();

            return result.add(field + message);
        }
        return failed(result.toString());
    }

}
