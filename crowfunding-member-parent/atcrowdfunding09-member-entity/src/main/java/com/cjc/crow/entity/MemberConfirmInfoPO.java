package com.cjc.crow.entity;

/**
 * 发起人确认信息表
 */
public class MemberConfirmInfoPO {
    // 数据id
    private Integer id;

    // 会员id
    private Integer memberid;

    // 易付宝企业账号
    private String paynum;

    // 法人身份证号
    private String cardnum;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getMemberid() {
        return memberid;
    }

    public void setMemberid(Integer memberid) {
        this.memberid = memberid;
    }

    public String getPaynum() {
        return paynum;
    }

    public void setPaynum(String paynum) {
        this.paynum = paynum == null ? null : paynum.trim();
    }

    public String getCardnum() {
        return cardnum;
    }

    public void setCardnum(String cardnum) {
        this.cardnum = cardnum == null ? null : cardnum.trim();
    }
}