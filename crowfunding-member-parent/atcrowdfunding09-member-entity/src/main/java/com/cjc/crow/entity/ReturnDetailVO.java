package com.cjc.crow.entity;

/**
 * Created by IntelliJ IDEA.
 * User: cjc
 * Date: 2020/11/20
 * Time: 9:58
 * To change this template use File | Settings | File Templates.
 **/
public class ReturnDetailVO {

    // 回报信息主键
    private Integer returnId;
    // 当前支持的金额
    private Integer supportMoney;
    // 单笔限购，为0时不限购
    private Integer signalPurchase;

    // 具体限购的数量
    private Integer purchase;

    // 当前支持的人数
    private Integer supporterCount;

    // 运费，“0”为包邮
    private Integer freight;

    // 众筹成功后，多少天发货
    private Integer returnDate;

    // 回报描述
    private String content;

    public ReturnDetailVO() {
    }

    public ReturnDetailVO(Integer returnId, Integer supportMoney, Integer signalPurchase, Integer purchase, Integer supporterCount, Integer freight, Integer returnDate, String content) {
        this.returnId = returnId;
        this.supportMoney = supportMoney;
        this.signalPurchase = signalPurchase;
        this.purchase = purchase;
        this.supporterCount = supporterCount;
        this.freight = freight;
        this.returnDate = returnDate;
        this.content = content;
    }

    public Integer getReturnId() {
        return returnId;
    }

    public void setReturnId(Integer returnId) {
        this.returnId = returnId;
    }

    public Integer getSupportMoney() {
        return supportMoney;
    }

    public void setSupportMoney(Integer supportMoney) {
        this.supportMoney = supportMoney;
    }

    public Integer getSignalPurchase() {
        return signalPurchase;
    }

    public void setSignalPurchase(Integer signalPurchase) {
        this.signalPurchase = signalPurchase;
    }

    public Integer getPurchase() {
        return purchase;
    }

    public void setPurchase(Integer purchase) {
        this.purchase = purchase;
    }

    public Integer getSupporterCount() {
        return supporterCount;
    }

    public void setSupporterCount(Integer supporterCount) {
        this.supporterCount = supporterCount;
    }

    public Integer getFreight() {
        return freight;
    }

    public void setFreight(Integer freight) {
        this.freight = freight;
    }

    public Integer getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(Integer returnDate) {
        this.returnDate = returnDate;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "ReturnDetailVO{" +
                "returnId=" + returnId +
                ", supportMoney=" + supportMoney +
                ", signalPurchase=" + signalPurchase +
                ", purchase=" + purchase +
                ", supporterCount=" + supporterCount +
                ", freight=" + freight +
                ", returnDate=" + returnDate +
                ", content='" + content + '\'' +
                '}';
    }
}
