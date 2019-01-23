package com.architect.conf;

/**
 * @author wenxiong.jia
 * @since 2018/12/6
 */
public enum AppEnvironment {
    LOCAL("local", "本地环境"), DEV("dev", "开发环境"), TEST("test", "测试环境"), PRE_ONLINE("pre-online",
            "预上线环境"), PRODUCT("product", "生产环境");
    private String name;
    private String desc;

    private AppEnvironment(String name, String desc) {
        this.name = name;
        this.desc = desc;
    }

    public String getName() {
        return name;
    }

    public String getDesc() {
        return desc;
    }

    public boolean matches(String env) {
        return this.getName().equalsIgnoreCase(env);
    }
}
