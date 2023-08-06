package com.qxc.gacspboot.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.qxc.gacspboot.Config.FileUpLoadConfig;
import com.qxc.gacspboot.dao.BlogDao;
import com.qxc.gacspboot.dao.RegisterDao;
import com.qxc.gacspboot.pojo.Blog;
import com.qxc.gacspboot.pojo.Login;
import com.qxc.gacspboot.pojo.Register;
import com.qxc.gacspboot.service.AuthenticationService;
import com.qxc.gacspboot.service.BlogService;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.List;
import java.util.UUID;

@Service
public class BlogServiceImpl extends ServiceImpl<BlogDao, Blog> implements BlogService {
    @Autowired
    private AuthenticationService authenticationService;
    @Autowired
    private BlogDao blogDao;
    @Autowired
    private RegisterDao registerDao;
    @Autowired
    private FileUpLoadConfig fileUpLoadConfig;

    @Override
    public boolean addArticle(Login login, Blog blog) {
        return blogDao.insert(blog) > 0;
    }

    @Override
    public boolean deleteArticle(Login login, int articleId) {
        if (!authenticationService.BlogChange(login, articleId)) {
            return false;
        }
        return blogDao.deleteById(articleId) > 0;
    }

    @Override
    public boolean editArticle(Login login, @NotNull Blog blog) {
        if (!authenticationService.BlogChange(login, blog.getId())) {
            return false;
        }
        return blogDao.updateById(blog) > 0;
    }

    @Override
    public String uploadFile(Login login, @NotNull MultipartFile fileUpload) {
        //获取文件名
        String fileName = fileUpload.getOriginalFilename();
        //获取文件后缀名
        assert fileName != null;
        String suffixName = fileName.substring(fileName.lastIndexOf("."));
        //重新生成文件名
        fileName = UUID.randomUUID() + suffixName;

        String filePath = fileUpLoadConfig.getDir();
        try {
            //将图片保存到static文件夹里
            fileUpload.transferTo(new File(filePath + fileName));
            //返回提示信息
            return fileName;
        } catch (Exception e) {
            e.printStackTrace();
            log.error("upload failed" + e.getMessage());
            return "";
        }
    }

    @Override
    public List<Blog> searchArticle(@NotNull Login login) {
        QueryWrapper<Blog> wrapper = new QueryWrapper<>();
        wrapper.select(Blog.class, info -> !info.getColumn().equals("content")).eq("userid", login.getUsername()).orderByDesc("id");
        return blogDao.selectList(wrapper);
    }

    @Override
    public int allNumber(@NotNull Login login) {
        QueryWrapper<Blog> wrapper = new QueryWrapper<>();
        wrapper.eq("userid", login.getUsername());
        return blogDao.selectCount(wrapper);
    }

    @Override
    public int allNumber(@NotNull Login login, int type) {
        QueryWrapper<Blog> wrapper = new QueryWrapper<>();
        wrapper.eq("userid", login.getUsername()).eq("type", type);
        return blogDao.selectCount(wrapper);
    }

    @Override
    public int allPageNum(@NotNull Login login) {
        QueryWrapper<Blog> wrapper = new QueryWrapper<>();
        wrapper.le("statuscode", login.getStatus()).ne("status", 1)
                .or(w -> w.eq("status", -1));
        return blogDao.selectCount(wrapper);
    }

    @Override
    public int allPageNums(@NotNull Login login, Integer type) {
        QueryWrapper<Blog> wrapper = new QueryWrapper<>();
        wrapper.le("statuscode", login.getStatus()).ne("status", 1)
                .eq("type",type);
        return blogDao.selectCount(wrapper);
    }

    @Override
    public int allPageNum(@NotNull Login login, Integer Userid) {
        final QueryWrapper<Register> id = new QueryWrapper<>();
        id.eq("id", Userid);
        String user = registerDao.selectOne(id).getUsername();
        QueryWrapper<Blog> wrapper = new QueryWrapper<>();
        wrapper.le("statuscode", login.getStatus())
                .ne("status", 1)
                .eq("userid", user);
        return blogDao.selectCount(wrapper);
    }

    @Override
    public int allPageNum(@NotNull Login login, Integer Userid, Integer type) {
        final QueryWrapper<Register> id = new QueryWrapper<>();
        id.eq("id", Userid);
        String user = registerDao.selectOne(id).getUsername();
        QueryWrapper<Blog> wrapper = new QueryWrapper<>();
        wrapper.le("statuscode", login.getStatus())
                .ne("status", 1)
                .eq("userid", user)
                .eq("type", type);
        return blogDao.selectCount(wrapper);
    }

    @Override
    public List<Blog> searchArticlePage(@NotNull Login login, int number, int page) {
        QueryWrapper<Blog> wrapper = new QueryWrapper<>();
        wrapper.select(Blog.class, info -> !info.getColumn().equals("content")).eq("userid", login.getUsername()).orderByDesc("id");
        Page<Blog> page1 = new Page<>(page, number);
        return blogDao.selectPage(page1, wrapper).getRecords();
    }

    @Override
    public List<Blog> searchArticlePage(@NotNull Login login, int type, int number, int page) {
        QueryWrapper<Blog> wrapper = new QueryWrapper<>();
        wrapper.select(Blog.class, info -> !info.getColumn().equals("content")).
                eq("userid", login.getUsername()).
                eq("type", type).
                orderByDesc("id");
        Page<Blog> page1 = new Page<>(page, number);
        return blogDao.selectPage(page1, wrapper).getRecords();
    }

    @Override
    public List<Blog> getRoleArticle(@NotNull Login login) {
        QueryWrapper<Blog> wrapper = new QueryWrapper<>();
        wrapper.select(Blog.class, info -> !info.getColumn().equals("content")).le("statuscode", login.getStatus()).ne("status", 1)
                .or(w -> w.eq("status", -1)).orderByDesc("id");
        return blogDao.selectList(wrapper);
    }

    @Override
    public List<Blog> getRoleArticlePage(@NotNull Login login, int number, int page) {
        QueryWrapper<Blog> wrapper = new QueryWrapper<>();
        wrapper.select(Blog.class, info -> !info.getColumn().equals("content")).le("statuscode", login.getStatus()).ne("status", 1)
                .or(w -> w.eq("status", -1)).orderByDesc("id");
        Page<Blog> page1 = new Page<>(page, number);
        return blogDao.selectPage(page1, wrapper).getRecords();
    }

    @Override
    public List<Blog> getRoleArticlePage(@NotNull Login login, int Userid, int number, int page) {
        QueryWrapper<Blog> wrapper = new QueryWrapper<>();
        final QueryWrapper<Register> id = new QueryWrapper<>();
        id.eq("id", Userid);
        String user = registerDao.selectOne(id).getUsername();
        wrapper.select(Blog.class, info -> !"content".equals(info.getColumn())).
                le("statuscode", login.getStatus()).
                ne("status", 1).
                eq("userid", user).
                orderByDesc("id");
        Page<Blog> page1 = new Page<>(page, number);
        return blogDao.selectPage(page1, wrapper).getRecords();
    }

    @Override
    public List<Blog> getRoleArticlePage(Integer type, Login login, int number, int page) {
        QueryWrapper<Blog> wrapper = new QueryWrapper<>();
        wrapper.select(Blog.class, info -> !info.getColumn().equals("content")).
                le("statuscode", login.getStatus()).
                ne("status", 1)
                .eq("type",type).orderByDesc("id");
        Page<Blog> page1 = new Page<>(page, number);
        return blogDao.selectPage(page1, wrapper).getRecords();
    }

    @Override
    public List<Blog> getRoleArticlePage(@NotNull Login login, int Userid, int type, int number, int page) {
        QueryWrapper<Blog> wrapper = new QueryWrapper<>();
        final QueryWrapper<Register> id = new QueryWrapper<>();
        id.eq("id", Userid);
        String user = registerDao.selectOne(id).getUsername();
        wrapper.select(Blog.class, info -> !"content".equals(info.getColumn())).
                le("statuscode", login.getStatus()).
                ne("status", 1).
                eq("userid", user).
                eq("type", type).
                orderByDesc("id");
        Page<Blog> page1 = new Page<>(page, number);
        return blogDao.selectPage(page1, wrapper).getRecords();
    }

    @Override
    public Blog getArticle(@NotNull Login login, int articleId) {
        LambdaQueryWrapper<Blog> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Blog::getId, articleId);
        wrapper.and(w -> w.le(Blog::getStatuscode, login.getStatus()).ne(Blog::getStatus, 1)
                .or(q -> q.eq(Blog::getStatus, -1))
                .or(e -> e.eq(Blog::getUserid, login.getUsername())));
        return blogDao.selectOne(wrapper);
    }
}
