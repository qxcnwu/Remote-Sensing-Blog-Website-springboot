package com.qxc.gacspboot.controller;

import com.fasterxml.jackson.databind.type.LogicalType;
import com.qxc.gacspboot.controller.AuthorNeedAnnotion.AuthorNeed;
import com.qxc.gacspboot.controller.UTiles.R;
import com.qxc.gacspboot.pojo.Blog;
import com.qxc.gacspboot.pojo.Login;
import com.qxc.gacspboot.service.AuthenticationService;
import com.qxc.gacspboot.service.BlogService;
import com.qxc.gacspboot.service.RedisService;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

@RestController
@RequestMapping("/myblog")
@Slf4j
public class BlogEditController {
    @Autowired
    private BlogService blogService;

    @Autowired
    private RedisService redisService;

    @AuthorNeed
    @RequestMapping("/edit/{articleId}")
    public R edit(@PathVariable Integer articleId, @CookieValue("token") String token, @NotNull Blog blog){
        Login login=redisService.SearchToken(token);
        blog.setId(articleId);
        if(login==null) return new R(null,false,"权限错误");
        R r=new R();
        blog.setStatuscode(login.getStatus());
        r.setData(blogService.editArticle(login,blog));
        r.setFinish(true);
        r.setMessage(String.valueOf(blog.getId()));
        return r;
    }

    @RequestMapping("/delete/{articleId}")
    @AuthorNeed
    public R delete(@PathVariable Integer articleId,@CookieValue("token") String token){
        Login login=redisService.SearchToken(token);
        if(login==null) return new R(null,false,"权限错误");
        R r=new R();
        r.setData(blogService.deleteArticle(login, articleId));
        r.setFinish(true);
        r.setMessage("删除成功");
        return r;
    }

    @RequestMapping("/all/{number}/{page}")
    @AuthorNeed
    public R allPage(@CookieValue("token") String token, @PathVariable Integer number, @PathVariable Integer page){
        Login login=redisService.SearchToken(token);
        if(login==null) return new R(null,false,"权限错误");
        R r=new R();
        r.setData(blogService.searchArticlePage(login,number,page));
        r.setFinish(true);
        r.setMessage("查询完成");
        return r;
    }

    @RequestMapping("/all/{type}/{number}/{page}")
    @AuthorNeed
    public R allPage(@CookieValue("token") String token,@PathVariable Integer type, @PathVariable Integer number, @PathVariable Integer page){
        Login login=redisService.SearchToken(token);
        if(login==null) return new R(null,false,"权限错误");
        R r=new R();
        r.setData(blogService.searchArticlePage(login,type,number,page));
        r.setFinish(true);
        r.setMessage("查询完成");
        return r;
    }

    @RequestMapping("/allnum")
    @AuthorNeed
    public R allNumber(@CookieValue("token") String token){
        Login login=redisService.SearchToken(token);
        if(login==null) return new R(null,false,"权限错误");
        R r=new R();
        r.setData(blogService.allNumber(login)+"");
        r.setFinish(true);
        return r;
    }

    @RequestMapping("/allnum/{type}")
    @AuthorNeed
    public R allNumber(@CookieValue("token") String token,@PathVariable Integer type){
        Login login=redisService.SearchToken(token);
        if(login==null) return new R(null,false,"权限错误");
        R r=new R();
        r.setData(blogService.allNumber(login,type)+"");
        r.setFinish(true);
        return r;
    }

    @RequestMapping("/all")
    @AuthorNeed
    public R all(@CookieValue("token") String token){
        Login login=redisService.SearchToken(token);
        if(login==null) return new R(null,false,"权限错误");
        R r=new R();
        r.setData(blogService.searchArticle(login));
        r.setFinish(true);
        r.setMessage("查询成功");
        return r;
    }

    @AuthorNeed
    @RequestMapping("/write")
    public R write(Blog blog,@CookieValue("token") String token){
        Login login=redisService.SearchToken(token);
        if(login==null) return new R(null,false,"权限错误");
        R r=new R();
        blog.setUserid(login.getUsername());
        blog.setStatuscode(login.getStatus());
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        blog.setCreated(dateFormat.format(new Date()));
        r.setData(blogService.addArticle(login,blog));
        r.setFinish(true);
        r.setMessage(String.valueOf(blog.getId()));
        return r;
    }

    @AuthorNeed
    @RequestMapping("/view/{articleId}")
    public R view(Blog blog, @CookieValue("token") String token, @PathVariable Integer articleId){
        Login login=redisService.SearchToken(token);
        if(login==null) return new R(null,false,"权限错误");
        R r=new R();
        r.setData(blogService.getArticle(login,articleId));
        r.setFinish(true);
        r.setMessage("查询成功");
        return r;
    }

    @AuthorNeed
    @RequestMapping("/upload")
    public R upload(@CookieValue("token") String token, MultipartFile image){
        Login login=redisService.SearchToken(token);
        if(login==null) return new R(null,false,"权限错误");
        R r=new R();
        String savePath=blogService.uploadFile(login,image);
        r.setFinish(!savePath.equals(""));
        r.setData("http://159.75.14.197:8082/picture/show/"+savePath);
        return r;
    }
}
