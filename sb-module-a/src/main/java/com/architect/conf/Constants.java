package com.architect.conf;

/**
 * @author wenxiong.jia
 * @since 2018/12/6
 */
public interface Constants {
    // 服务选择器配置->选择服务后缀->例如: route.selected.server.group
    String SELECTED_SERVER = "selected.server.group";// *.selected.server.group
    // 注册中心Meta分组Key
    String SERVER_GROUP = "group";
    // 开发环境服务分组名称
    String SERVER_GROUP_NAME_DEV = "dev";
    // 测试环境服务分组名称
    String SERVER_GROUP_NAME_TEST = "test";

    String TOKEN = "token";
    String USER_ID = "userId";
}
