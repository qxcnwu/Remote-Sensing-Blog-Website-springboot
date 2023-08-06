package com.qxc.gacspboot.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.qxc.gacspboot.pojo.Blog;
import com.qxc.gacspboot.pojo.Login;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * @author 邱星晨
 */
public interface BlogService extends IService<Blog> {
    boolean addArticle(Login login, Blog blog);

    boolean deleteArticle(Login login, int articleId);

    boolean editArticle(Login login, Blog blog);

    String uploadFile(Login login, MultipartFile fileUpload);

    List<Blog> searchArticle(Login login);

    List<Blog> searchArticlePage(Login login, int number, int page);

    List<Blog> searchArticlePage(Login login, int type, int number, int page);

    List<Blog> getRoleArticle(Login login);

    List<Blog> getRoleArticlePage(Login login, int number, int page);

    List<Blog> getRoleArticlePage(Login login, int Userid, int number, int page);

    List<Blog> getRoleArticlePage(Integer type, Login login, int number, int page);

    List<Blog> getRoleArticlePage(Login login, int Userid, int type, int number, int page);

    Blog getArticle(Login login, int articleId);

    int allNumber(Login login);

    int allNumber(Login login, int type);

    int allPageNum(Login login);

    int allPageNums(Login login, Integer type);

    int allPageNum(Login login, Integer Userid);

    int allPageNum(Login login, Integer Userid, Integer type);
}
