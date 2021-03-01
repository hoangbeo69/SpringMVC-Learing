package com.learn.entity;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "news")  //mapping với bảng nào ở trong database
public class NewsEntity extends BaseEntity{

    @Column(name = "title") //khi thực hiện khởi tạo column đó trong mysql thì nó tên như thế nào
    private String title;

    @Column(name = "thumbnail")
    private String thumbnail;
    //columnDefinition :thiết lập kiểu dữ liệu của cột trong db dữ liệu là TEXT
    @Column(name = "shortdescription",columnDefinition = "TEXT")
    private String shortDescription;

    @Column(name = "content",columnDefinition = "TEXT")
    private String content;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    public String getShortDescription() {
        return shortDescription;
    }

    public void setShortDescription(String shortDescription) {
        this.shortDescription = shortDescription;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

}
