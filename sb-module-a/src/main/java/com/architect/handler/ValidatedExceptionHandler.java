package com.architect.handler;

import com.architect.domain.DataResult;
import com.architect.enums.ResultEnum;
import com.architect.exception.TmsBusinessException;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpStatus;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.validation.BindException;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.NoHandlerFoundException;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.List;
import java.util.Set;
import java.util.stream.Stream;

/**
 * @author linlg
 * @description Validated异常处理
 * @date 2017/11/9 15:08
 */
@Slf4j
@RestControllerAdvice
public class ValidatedExceptionHandler {
    // 处理controller参数异常
    @ExceptionHandler(ConstraintViolationException.class)
    public Object handleConstraintViolationException(ConstraintViolationException c){
        StringBuilder errorMsg=new StringBuilder();

        Set<ConstraintViolation<?>> violationSet = c.getConstraintViolations();
        violationSet.stream().forEach(x -> errorMsg.append(x.getMessageTemplate()).append(";"));

        return DataResult.faild(ResultEnum.PARAM_ERROR.getCode(), errorMsg.toString());
    }

    // 处理实体类参数异常
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Object handleMethodArgumentNotValidException(MethodArgumentNotValidException ex){
        StringBuilder errorMsg=new StringBuilder();
        List<ObjectError> errs = ex.getBindingResult().getAllErrors();
        errs.stream().forEach(x -> errorMsg.append(x.getDefaultMessage()).append(";"));
        return DataResult.faild(ResultEnum.PARAM_ERROR.getCode(), errorMsg.toString());
    }

    // 处理参数匹配异常
    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public Object handleMethodArgumentTypeMismatchException(MethodArgumentTypeMismatchException ex){
        StringBuilder errorMsg=new StringBuilder();
       errorMsg.append(ex.getName()).append("参数类型错误");
        return DataResult.faild(ResultEnum.PARAM_ERROR.getCode(), errorMsg.toString());
    }

    // 处理TmsBusinessException异常
    @ExceptionHandler(TmsBusinessException.class)
    public Object handleTmsBusinessException(TmsBusinessException ex){
        return DataResult.faild(ex.getErrorCode(), ex.getErrorMsg());
    }

//    // 处理BusinessException异常
//    @ExceptionHandler(BusinessException.class)
//    @ResponseBody
//    public Object handleBusinessException(BusinessException ex){
//        return DataResult.faild(ex.getErrorCode(), ex.getErrorMsg());
//    }

    // 处理参数匹配异常
    @ExceptionHandler(BindException.class)
    public Object handleBindExceptionException(BindException ex){
        StringBuilder errorMsg=new StringBuilder();
        List<ObjectError> errs = ex.getBindingResult().getAllErrors();
        errs.stream().forEach(
                x ->{
                    errorMsg.append(x.getObjectName()).append(":");
                    Stream.of(x.getArguments()).forEach( o -> {
                        if (o instanceof DefaultMessageSourceResolvable){
                            errorMsg.append(((DefaultMessageSourceResolvable) o).getDefaultMessage());
                        }
                    });
                    errorMsg.append("类型错误;");
                }
        );
        return DataResult.faild(ResultEnum.PARAM_ERROR.getCode(), errorMsg.toString());
    }

    @ExceptionHandler(value = Exception.class)
    public DataResult<String> jsonErrorHandler(HttpServletRequest req, Exception e) {
        DataResult<String> dataResult =null;
        if(e instanceof NoHandlerFoundException){
            dataResult=DataResult.faild(HttpStatus.SC_NOT_FOUND,"请求资源地址错误！");
        }else {
            log.error(e.getMessage(),e);
            dataResult =DataResult.faild(HttpStatus.SC_INTERNAL_SERVER_ERROR,e.getMessage());
        }
        return dataResult;
    }
}
