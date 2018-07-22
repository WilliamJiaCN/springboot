package com.architect.controller.swagger;

import com.architect.entity.User;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;

/**
 * @author wenxiong.jia
 * @since 2018/7/16
 */
@RestController
@RequestMapping("/swagger")
public class SwaggerController {

    private Map<Long, User> userInfos = new HashMap<>();

    @PostConstruct
    public void init() {
        User user = new User();
        user.setId(1L);
        user.setName("william");
        user.setAge(28);
        userInfos.put(user.getId(), user);
    }

    @ApiOperation(value = "根据ID获取用户信息")
    @ApiImplicitParam(name = "id", value = "用户ID", required = true, dataType = "Long", paramType = "path")
    @GetMapping("/{id}")
    public User getInfo(@PathVariable Long id) {
        return userInfos.get(id);
    }
}
