package com.architect.conf;

import com.architect.domain.GlobalConstant;
import com.architect.listener.ServerSelectedProcess;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.env.Environment;

import java.util.LinkedList;
import java.util.List;
import java.util.Properties;

public class SystemManager {
    private static final Logger LOGGER = LoggerFactory.getLogger(SystemManager.class);
    public static volatile boolean isInit = false;
    /**
     * 使用前需要先初始化
     */
    private static volatile Environment environment = null;

    /**
     * 初始化系统环境
     *
     * @param environment
     */
    public static void initEnvironment(Environment environment) {
        if (SystemManager.environment == null) {
            if (environment == null) {
                throw new IllegalArgumentException("初始化环境参数不能为空");
            }
            System.out.println("init.environment:" + environment);
            SystemManager.environment = environment;
        }
    }

    /**
     * 获取系统环境
     *
     * @return
     */
    public static Environment getEnvironment() {
        return environment;
    }

    /**
     * 服务选择器
     * <p>
     * 在application.properties配置文件中增加配置
     * </p>
     * 服务名称.selected.server.group=分组名称
     * <p>
     * 例如：route.selected.server.group=test
     */
    private static final List<ServerSelected> serverSelected;

    static {
        serverSelected = new LinkedList<ServerSelected>();
    }

    public static String getProjectName() {
        return environment.getProperty("spring.application.name");
    }

    // public static void setSystemCode(int code) {
    // SystemManager.systemCode = String.valueOf(code);
    // }

    /**
     * 获取系统码
     *
     * @return
     */
    public static int getSystemCode() {
        if (environment == null) {
            LOGGER.warn("[警告]请先调用初始化：SystemManager.initEnvironment()");
            return 0;
        }
        String code = environment.getProperty(GlobalConstant.SYSTEM_CODE);
        if (code == null) {
            LOGGER.warn("[警告]没有设置系统码 application.properties ->'system.code'");
            return 0;
        }
        return Integer.parseInt(code.trim());
    }

    /**
     * 获取当前环境 dev|test|product
     *
     * @return
     */
    public static String getEnv() {
        String env = null;
        if (environment != null) {
            String[] activeProfiles = environment.getActiveProfiles();
            if (activeProfiles != null && activeProfiles.length > 0) {
                env = activeProfiles[0];
            } else {
                env = environment.getProperty("spring.profiles.active");
            }
        }
        if (StringUtils.isBlank(env)) {
            LOGGER.error("没有获取到有效的环境->spring.profiles.active=[local,dev,test,prepare,product]");
        }
        return env;
    }

    /**
     * 开发环境
     *
     * @return
     */
    public static boolean isDevEnv() {
        return AppEnvironment.DEV.matches(getEnv());
    }

    /**
     * 本地环境
     *
     * @return
     */
    public static boolean isLocalEnv() {
        return AppEnvironment.LOCAL.matches(getEnv());
    }

    /**
     * 测试环境
     *
     * @return
     */
    public static boolean isTestEnv() {
        return AppEnvironment.TEST.matches(getEnv());
    }

    /**
     * 生产环境
     *
     * @return
     */
    public static boolean isProductEnv() {
        return AppEnvironment.PRODUCT.matches(getEnv());
    }

    /**
     * 将当前状态码追加系统码
     *
     * @param code
     * @return
     */
    public static int appendSysCode(int code) {
        if (code < 0) {
            return -(getSystemCode() + Math.abs(code));
        }
        return Integer.valueOf(getSystemCode() + "" + code);
    }

    /**
     * 获取当前系统secret
     *
     * @return
     */
    public static String getOpenSecret() {
        String open_secret = environment.getProperty(GlobalConstant.OPEN_SECRET_CONFIG_KEY);
        if (open_secret == null) {
            LOGGER.info("没有获取到配置`{}`", GlobalConstant.OPEN_SECRET_CONFIG_KEY);
        }
        return open_secret;
    }

    public static Logger getLogger() {
        return LOGGER;
    }

    /**
     * 添加服务选择器（实现注册中心服务分组）
     *
     * @param serverSelected
     */
    public static void addServerSelected(List<ServerSelected> serverSelected) {
        SystemManager.serverSelected.addAll(serverSelected);
    }

    /**
     * 添加服务选择器（实现注册中心服务分组）
     *
     * @param properties
     */
    public static void addServerSelected(Properties properties) {
        List<ServerSelected> serverSelecteds = ServerSelectedProcess.parserServerSelected(properties);
        addServerSelected(serverSelecteds);
    }

    /**
     * 编程式API添加服务选择器（实现注册中心服务分组）
     *
     * @param selected
     */
    public static void addServerSelected(ServerSelected selected) {
        serverSelected.add(selected);
    }

    /**
     * 获取服务选择器
     *
     * @return
     */
    public static List<ServerSelected> getServerSelected() {
        return SystemManager.serverSelected;
    }

    /**
     * dump服务选择器
     */
    public static void dumpServerSelected() {
        System.out.println("--------------------服务分组选择器列表--------------------");
        for (int i = 0; i < serverSelected.size(); i++) {
            ServerSelected selected = serverSelected.get(i);
            System.out.println("`" + selected.getServerName() + "`服务->`" + selected.getSelectedGroup() + "`分组");
        }
    }

    /**
     * 获取端口号
     *
     * @return
     */
    public static Integer getPort() {
        Integer port = null;
        try {
            if (environment != null) {
                String val = environment.getProperty("server.port");
                if (val != null) {
                    port = Integer.parseInt(val.trim());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (port == null) {
            System.err.println("获取应用端口失败检查配置:`server.port`");
        }
        return port;
    }
}
