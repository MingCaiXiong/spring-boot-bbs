package top.xiongmingcai.bbs.exception;


import com.baomidou.mybatisplus.extension.api.ApiController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
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
        return failed(e.getMessage());
    }
}
