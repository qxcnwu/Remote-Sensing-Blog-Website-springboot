package com.qxc.gacspboot.service;

import com.qxc.gacspboot.pojo.Login;

/**
 * 鉴权服务
 * @author 邱星晨
 * @since 2022年2月24日
 */
public interface AuthenticationService {
    boolean BlogChange(Login login, int articleId);
    boolean BlogView(Login login,int articleId);
}
