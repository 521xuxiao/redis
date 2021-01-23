package com.example.one.exception;

import com.example.one.returnData.ReturnData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.BadSqlGrammarException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.PrintWriter;
import java.io.StringWriter;
@ResponseBody
@ControllerAdvice
public class GlobalException {
    private Logger logger= LoggerFactory.getLogger(GlobalException.class);

    @ExceptionHandler(RuntimeException.class)
    public ReturnData doHandleRuntimeException(RuntimeException e) {
        e.printStackTrace();

        /**
         * 错误日志输出
         */
        StringWriter sw = new StringWriter();
        e.printStackTrace(new PrintWriter(sw, true));
        String errMsg = sw.toString();
        logger.error(errMsg);

        ReturnData returnData = new ReturnData();
        if(e instanceof BadSqlGrammarException){
            returnData.setResultMessage("系统异常");
            returnData.setSuccess(false);
        }else if(e instanceof RuntimeException) {
            returnData.setResultMessage(e.getMessage());
            returnData.setSuccess(false);
        }else{
            returnData.setResultMessage("系统异常");
            returnData.setSuccess(false);
        }
        return returnData;
    }
}
