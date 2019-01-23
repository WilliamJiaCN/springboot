package com.architect.controller.swagger;

import com.architect.entity.ValidatedUser;
import com.architect.exception.TmsBusinessException;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * @author wenxiong.jia
 * @since 2018/12/05
 */
@RestController
@RequestMapping("/validate")
public class ValidateController {

    @PostMapping("/validateParam")
    @ApiOperation(value = "校验接口入参")
    @ApiImplicitParam(name = "validatedUser", value = "被校验参数", required = true,
            dataType = "ValidatedUser", paramType = "body")
    public String validateParam(@Valid @RequestBody ValidatedUser validatedUser) {
        System.out.println("参数验证通过。。。");
        throw new TmsBusinessException(2001, "业务异常");
//        return "Param Validate";
    }
}
