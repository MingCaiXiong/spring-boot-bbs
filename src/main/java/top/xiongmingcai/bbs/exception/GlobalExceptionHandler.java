package top.xiongmingcai.bbs.exception;


import com.baomidou.mybatisplus.extension.api.ApiController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolationException;

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
    public Object BussinessExceptionHandler(HttpServletRequest req, BussinessException ex) {
        logger.error("发生业务异常！原因是：{}", ex.getMessage());
        return failed(ex.getMessage());
    }

    /**
     * 处理自定义的业务异常
     *
     * @param req
     * @param ex
     * @return
     */
    @ExceptionHandler(value = NullPointerException.class)
    public Object NullPointerExceptionHandler(HttpServletRequest req, NullPointerException ex) {
        logger.error("发生空值异常！原因是：{}", ex.getMessage());
        String url = req.getRequestURI();
        System.out.println("发生空值异常 url:" + url);
        return failed("缺省参数 ");
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

        return failed(e.getMessage());
    }

    //添加全局异常处理流程，根据需要设置需要处理的异常，本文以MethodArgumentNotValidException为例
    @ExceptionHandler({MethodArgumentNotValidException.class})
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
    public Object MethodArgumentNotValidHandler(HttpServletRequest request, MethodArgumentNotValidException ex) {
        //按需重新封装需要返回的错误信息
        BindingResult bindingResult = ex.getBindingResult();
        StringBuilder result = new StringBuilder("校验失败:");
        //解析原错误信息，封装后返回，此处返回非法的字段名称，原始值，错误信息
        for (FieldError error : bindingResult.getFieldErrors()) {
            String field = error.getField();
            Object rejectedValue = error.getRejectedValue();
            String message = error.getDefaultMessage();

            result.append(field).append(message).append(", ");
        }
        return failed(result.toString());
    }

    /**
     * 效验 @PathVariable 和
     *
     * @param ex
     * @return
     */
    @ExceptionHandler({ConstraintViolationException.class})
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public Object handleConstraintViolationException(ConstraintViolationException ex) {
        return failed("参数校验失败 " + ex.getMessage());
    }
}
