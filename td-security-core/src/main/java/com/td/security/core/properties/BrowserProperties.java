package com.td.security.core.properties;

import lombok.Data;

@Data
public class BrowserProperties {
    private String loginPage = "/td-signIn.html";

    private LoginType loginType = LoginType.JSON;

    private int rememberMeSeconds = 3600;
}
