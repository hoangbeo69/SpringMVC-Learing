package com.learn.service.impl;
import com.learn.dao.INewsDao;
import com.learn.model.NewsModel;
import com.learn.service.INewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class NewsService implements INewsService {

    @Autowired
    private INewsDao newsDao;

    @Override
    public List<NewsModel> findAll() {
        return newsDao.findAll();
    }

}
