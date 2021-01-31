package com.learn.service;

import com.learn.model.NewsModel;

import java.util.List;

public interface INewsService {
    List<NewsModel> findAll();
}
