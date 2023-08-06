package com.qxc.gacspboot.controller;

import com.qxc.gacspboot.controller.AuthorNeedAnnotion.AuthorNeed;
import com.qxc.gacspboot.controller.UTiles.R;
import com.qxc.gacspboot.pojo.Login;
import com.qxc.gacspboot.service.BlogService;
import com.qxc.gacspboot.service.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/blog")
public class BlogShowController {

    @Autowired
    private BlogService blogService;
    @Autowired
    private RedisService redisService;

    @AuthorNeed
    @RequestMapping("/all")
    public R all(@CookieValue("token") String token) {
        Login login = redisService.SearchToken(token);
        if (login == null) return new R(null, false, "权限异常");
        R r = new R();
        r.setData(blogService.getRoleArticle(login));
        r.setFinish(true);
        r.setMessage("查询成功");
        return r;
    }

    @AuthorNeed
    @RequestMapping("/all/{number}/{page}")
    public R allPage(@CookieValue("token") String token, @PathVariable Integer number, @PathVariable Integer page) {
        Login login = redisService.SearchToken(token);
        if (login == null) return new R(null, false, "权限错误");
        R r = new R();
        r.setData(blogService.getRoleArticlePage(login, number, page));
        r.setFinish(true);
        r.setMessage("查询成功");
        return r;
    }

    @AuthorNeed
    @RequestMapping("/alls/{type}/{number}/{page}")
    public R allPage(@CookieValue("token") String token, @PathVariable Integer type, @PathVariable Integer number, @PathVariable Integer page) {
        Login login = redisService.SearchToken(token);
        if (login == null) return new R(null, false, "权限错误");
        R r = new R();
        r.setData(blogService.getRoleArticlePage(type, login, number, page));
        r.setFinish(true);
        r.setMessage("查询成功");
        return r;
    }

    @AuthorNeed
    @RequestMapping("/all/{Userid}/{number}/{page}")
    public R allPageUser(@CookieValue("token") String token, @PathVariable Integer Userid, @PathVariable Integer number, @PathVariable Integer page) {
        Login login = redisService.SearchToken(token);
        if (login == null) return new R(null, false, "权限错误");
        R r = new R();
        r.setData(blogService.getRoleArticlePage(login, Userid, number, page));
        r.setFinish(true);
        r.setMessage("查询成功");
        return r;
    }

    @AuthorNeed
    @RequestMapping("/all/{type}/{Userid}/{number}/{page}")
    public R allPageUser(@CookieValue("token") String token, @PathVariable Integer type, @PathVariable Integer Userid, @PathVariable Integer number, @PathVariable Integer page) {
        Login login = redisService.SearchToken(token);
        if (login == null) return new R(null, false, "权限错误");
        R r = new R();
        r.setData(blogService.getRoleArticlePage(login, Userid, type, number, page));
        r.setFinish(true);
        r.setMessage("查询成功");
        return r;
    }

    @RequestMapping("/allnum")
    @AuthorNeed
    public R allNumber(@CookieValue("token") String token) {
        Login login = redisService.SearchToken(token);
        if (login == null) return new R(null, false, "权限错误");
        R r = new R();
        r.setData(blogService.allPageNum(login) + "");
        r.setFinish(true);
        return r;
    }

    @AuthorNeed
    @RequestMapping("/allnum/{Userid}")
    public R all(@CookieValue("token") String token, @PathVariable Integer Userid) {
        Login login = redisService.SearchToken(token);
        if (login == null) return new R(null, false, "权限错误");
        R r = new R();
        r.setData(blogService.allPageNum(login, Userid));
        r.setFinish(true);
        return r;
    }

    @AuthorNeed
    @RequestMapping("/allnums/{type}")
    public R alls(@CookieValue("token") String token, @PathVariable Integer type) {
        Login login = redisService.SearchToken(token);
        if (login == null) return new R(null, false, "权限错误");
        R r = new R();
        r.setData(blogService.allPageNums(login, type));
        r.setFinish(true);
        return r;
    }

    @AuthorNeed
    @RequestMapping("/allnum/{Userid}/{type}")
    public R all(@CookieValue("token") String token, @PathVariable Integer Userid, @PathVariable Integer type) {
        Login login = redisService.SearchToken(token);
        if (login == null) return new R(null, false, "权限错误");
        R r = new R();
        r.setData(blogService.allPageNum(login, Userid, type));
        r.setFinish(true);
        return r;
    }

    @AuthorNeed
    @RequestMapping("/view/{article_id}")
    public R view(@CookieValue("token") String token, @PathVariable Integer article_id) {
        Login login = redisService.SearchToken(token);
        if (login == null) return new R(null, false, "权限错误");
        R r = new R();
        r.setData(blogService.getArticle(login, article_id));
        r.setFinish(true);
        r.setMessage("查询成功");
        return r;
    }
}

