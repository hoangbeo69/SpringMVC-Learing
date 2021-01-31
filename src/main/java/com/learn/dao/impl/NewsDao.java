package com.learn.dao.impl;


import com.learn.dao.INewsDao;
import com.learn.mapper.NewsMapper;
import com.learn.model.NewsModel;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public class NewsDao extends AbstractDAO implements INewsDao {
    public List<NewsModel> findAll() {
        StringBuilder sql = new StringBuilder("Select * From news ");
        return query(sql.toString(),new NewsMapper());
    }

}
