package com.learn.model;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class AbstractModel<T> {
    private Long id;
    private Timestamp createDate;
    private Timestamp modifiedDate;
    private String createdBy;
    private String modifiedBy;
    private Long[] ids;
    private List<T> listResult = new ArrayList<>();
    private Integer page = 1; //hiển thị ở page nào
    private Integer maxPageItem; //tổng số item nhiều nhất trên 1 page
    private Integer totalPage; //tổng số trang có thể phân ra
    private Integer totalIem;
    private String sortName;
    private String sortBy;
    private String type;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getSortName() {
        return sortName;
    }

    public void setSortName(String sortName) {
        this.sortName = sortName;
    }

    public String getSortBy() {
        return sortBy;
    }

    public void setSortBy(String sortBy) {
        this.sortBy = sortBy;
    }

    public Integer getTotalIem() {
        return totalIem;
    }

    public void setTotalIem(Integer totalIem) {
        this.totalIem = totalIem;
    }

    public void setMaxPageItem(Integer maxPageItem) {
        this.maxPageItem = maxPageItem;
    }

    public Integer getMaxPageItem() {
        return maxPageItem;
    }

    public Integer getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(Integer totalPage) {
        this.totalPage = totalPage;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getPage() {
        return page;
    }

    public List<T> getListResult() {
        return this.listResult;
    }

    public void setListResult(List<T> listResult) {
        this.listResult = listResult;
    }

    public void setIds(Long[] ids){
        this.ids = ids;
    }

    public Long[] getIds(){
        return ids;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Timestamp getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Timestamp createDate) {
        this.createDate = createDate;
    }

    public Timestamp getModifiedDate() {
        return modifiedDate;
    }

    public void setModifiedDate(Timestamp modifiedDate) {
        this.modifiedDate = modifiedDate;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public String getModifiedBy() {
        return modifiedBy;
    }

    public void setModifiedBy(String modifiedBy) {
        this.modifiedBy = modifiedBy;
    }

    @Override
    public String toString() {
        return "AbstractModel{" +
                "id=" + id +
                ", createDate=" + createDate +
                ", modifiedDate=" + modifiedDate +
                ", createdBy='" + createdBy + '\'' +
                ", modifiedBy='" + modifiedBy + '\'' +
                ", ids=" + Arrays.toString(ids) +
                ", listResult=" + listResult +
                ", page=" + page +
                ", maxPageItem=" + maxPageItem +
                ", totalPage=" + totalPage +
                ", totalIem=" + totalIem +
                ", sortName='" + sortName + '\'' +
                ", sortBy='" + sortBy + '\'' +
                ", type='" + type + '\'' +
                '}';
    }
}
