package com.qxc.gacspboot.service.impl;

import com.qxc.gacspboot.dao.BlogDao;
import com.qxc.gacspboot.pojo.Blog;
import com.qxc.gacspboot.pojo.Login;
import com.qxc.gacspboot.service.AuthenticationService;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author 邱星晨
 */
@Service
public class AuthenticationServiceImpl implements AuthenticationService {
    @Autowired
    private BlogDao blogDao;

    @Override
    public boolean BlogChange(@NotNull Login login, int articleId) {
        Blog blog=blogDao.selectById(articleId);
        if(blog==null) return false;
        return blog.getUserid().equals(login.getUsername());
    }

    @Override
    public boolean BlogView(Login login, int articleId) {
        Blog blog=blogDao.selectById(articleId);
        if(blog==null) return false;
        return blog.getStatus()<=login.getStatus();
    }
}
