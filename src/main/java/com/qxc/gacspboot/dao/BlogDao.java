package com.qxc.gacspboot.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.qxc.gacspboot.pojo.Blog;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface BlogDao extends BaseMapper<Blog> {
}
