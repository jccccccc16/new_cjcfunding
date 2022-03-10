package com.cjc.crow.entity;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: cjc
 * Date: 2020/11/20
 * Time: 10:05
 * To change this template use File | Settings | File Templates.
 * <p>
 * 项目详情VO
 **/
public class ProjectDetailVO {

    private Integer projectId;

    // 项目名称
    private String projectName;

    // 项目描述
    private String projectDescription;

    // 关注的人数
    private Integer follower;

    private Integer status;

    private Integer money;

    private Integer supportMoney;

    private Integer percentage;

    private String deployDate;

    // 剩余的时间
    private Integer lastDay;

    // 以支持的人数
    private Integer supporterCount;

    private String headerPicturePath;

    private List<String> detailPicturePathList;

    private List<ReturnDetailVO> returnDetailVOList;

    private Integer day;

    public ProjectDetailVO() {
    }

    public ProjectDetailVO(Integer projectId, String projectName, String projectDescription, Integer follower, Integer status, Integer money, Integer supportMoney, Integer percentage, String deployDate, Integer lastDay, Integer supporterCount, String headerPicturePath, List<String> detailPicturePathList, List<ReturnDetailVO> returnDetailVOList, Integer day) {
        this.projectId = projectId;
        this.projectName = projectName;
        this.projectDescription = projectDescription;
        this.follower = follower;
        this.status = status;
        this.money = money;
        this.supportMoney = supportMoney;
        this.percentage = percentage;
        this.deployDate = deployDate;
        this.lastDay = lastDay;
        this.supporterCount = supporterCount;
        this.headerPicturePath = headerPicturePath;
        this.detailPicturePathList = detailPicturePathList;
        this.returnDetailVOList = returnDetailVOList;
        this.day = day;
    }

    public Integer getProjectId() {
        return projectId;
    }

    public void setProjectId(Integer projectId) {
        this.projectId = projectId;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getProjectDescription() {
        return projectDescription;
    }

    public void setProjectDescription(String projectDescription) {
        this.projectDescription = projectDescription;
    }

    public Integer getFollower() {
        return follower;
    }

    public void setFollower(Integer follower) {
        this.follower = follower;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getMoney() {
        return money;
    }

    public void setMoney(Integer money) {
        this.money = money;
    }

    public Integer getSupportMoney() {
        return supportMoney;
    }

    public void setSupportMoney(Integer supportMoney) {
        this.supportMoney = supportMoney;
    }

    public Integer getPercentage() {
        return percentage;
    }

    public void setPercentage(Integer percentage) {
        this.percentage = percentage;
    }

    public String getDeployDate() {
        return deployDate;
    }

    public void setDeployDate(String deployDate) {
        this.deployDate = deployDate;
    }

    public Integer getLastDay() {
        return lastDay;
    }

    public void setLastDay(Integer lastDay) {
        this.lastDay = lastDay;
    }

    public Integer getSupporterCount() {
        return supporterCount;
    }

    public void setSupporterCount(Integer supporterCount) {
        this.supporterCount = supporterCount;
    }

    public String getHeaderPicturePath() {
        return headerPicturePath;
    }

    public void setHeaderPicturePath(String headerPicturePath) {
        this.headerPicturePath = headerPicturePath;
    }

    public List<String> getDetailPicturePathList() {
        return detailPicturePathList;
    }

    public void setDetailPicturePathList(List<String> detailPicturePathList) {
        this.detailPicturePathList = detailPicturePathList;
    }

    public List<ReturnDetailVO> getReturnDetailVOList() {
        return returnDetailVOList;
    }

    public void setReturnDetailVOList(List<ReturnDetailVO> returnDetailVOList) {
        this.returnDetailVOList = returnDetailVOList;
    }


    public Integer getDay() {
        return day;
    }

    public void setDay(Integer day) {
        this.day = day;
    }

    @Override
    public String toString() {
        return "ProjectDetailVO{" +
                "projectId=" + projectId +
                ", projectName='" + projectName + '\'' +
                ", projectDescription='" + projectDescription + '\'' +
                ", follower=" + follower +
                ", status=" + status +
                ", money=" + money +
                ", supportMoney=" + supportMoney +
                ", percentage=" + percentage +
                ", deployDate='" + deployDate + '\'' +
                ", lastDay=" + lastDay +
                ", supporterCount=" + supporterCount +
                ", headerPicturePath='" + headerPicturePath + '\'' +
                ", detailPicturePathList=" + detailPicturePathList +
                ", returnDetailVOList=" + returnDetailVOList +
                ", day=" + day +
                '}';
    }
}
