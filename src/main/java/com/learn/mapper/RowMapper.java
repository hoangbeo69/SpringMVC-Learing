package com.learn.mapper;

import java.sql.ResultSet;

public interface RowMapper<T> {
    /**
     * @param rs
     * mapRow được sử dụng để tự động hóa với các loại model khác nhau trong hệ thống
     * khi kết quả
     * từ querry trả về sẽ được xử lý dữ liệu về dạng vả trả về 1 list của kiểu dữ liệu đó
     * @return list<T>
     */
    T mapRow(ResultSet rs);
}
