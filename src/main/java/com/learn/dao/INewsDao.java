package com.learn.dao;


import com.learn.model.NewsModel;
import java.util.List;

public interface INewsDao extends GenericDAO {
    List<NewsModel> findAll();
}
