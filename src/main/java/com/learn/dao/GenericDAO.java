package com.learn.dao;


import com.learn.mapper.RowMapper;

import java.util.List;

public interface GenericDAO<T>{
    <T> List<T> query(String sql, RowMapper<T> rowMapper, Object... parameters);
    void update (String sql,Object... parameters);
    Long insert (String sql,Object... parameters);
    void delete (String sql,Object... parameters);
    int count(String sql,Object... parameters);
}
