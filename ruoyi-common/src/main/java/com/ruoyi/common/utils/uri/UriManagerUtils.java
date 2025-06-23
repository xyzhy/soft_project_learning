package com.ruoyi.common.utils.uri;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * 管理 各种 Uri 的工具类, 以防需要变动
 */
@Configuration
@ConfigurationProperties(prefix = "uri")
public class UriManagerUtils {

    private String python;

    public String getPython() {
        return python;
    }

    public void setPython(String pythonUri) {
        this.python = pythonUri;
    }
}
