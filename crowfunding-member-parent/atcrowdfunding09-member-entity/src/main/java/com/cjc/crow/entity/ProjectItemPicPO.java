package com.cjc.crow.entity;

/***
 *
 * 6 项目表项目详情图片表
 *
 */
public class ProjectItemPicPO {

    private Integer id;

    // 项目id
    private Integer projectid;

    // 图片路径
    private String itemPicPath;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getProjectid() {
        return projectid;
    }

    public void setProjectid(Integer projectid) {
        this.projectid = projectid;
    }

    public String getItemPicPath() {
        return itemPicPath;
    }

    public void setItemPicPath(String itemPicPath) {
        this.itemPicPath = itemPicPath == null ? null : itemPicPath.trim();
    }
}