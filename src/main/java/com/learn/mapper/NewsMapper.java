package com.learn.mapper;


import com.learn.model.NewsModel;

import java.sql.ResultSet;
public class NewsMapper implements RowMapper<NewsModel>{

    @Override
    public NewsModel mapRow(ResultSet rs) {
        NewsModel news = new NewsModel();
        try{
            news.setId(rs.getLong("id"));
            news.setTitle(rs.getString("title"));
            news.setContent(rs.getString("content"));
            news.setCategoryId(rs.getLong("categoryId"));
            news.setShortDescription(rs.getString("shortdescription"));
            news.setThumbnail(rs.getString("thumbnail"));
            news.setShortDescription(rs.getString("shortdescription"));
            news.setCreateDate(rs.getTimestamp("createddate"));
            news.setCreatedBy(rs.getString("createdby"));
            if (rs.getTimestamp("modifieddate") != null) {
                news.setModifiedDate(rs.getTimestamp("modifieddate"));
            }
            if (rs.getString("modifiedby") != null) {
                news.setModifiedBy(rs.getString("modifiedby"));
            }
            return  news;
        }catch (Exception e){
            System.out.println(e);
        }
        return null;
    }
}
