package com.likai.domain;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class Orders {

    private String id;  //无意义、主键uuid
    private String orderNum;    //订单编号 不为空 唯一
    private Date orderTime;     //下单时间
    private String orderTimeStr;
    private Integer orderStatus;       //订单状态(0 未支付 1 已支付)
    private String orderStatusStr;
    private Integer peopleCount;     //出行人数
    private Product product;    //产品
    private List<Traveller> travellers; //旅客集合
    private Member member;      //会员
    private Integer payType;    //支付方式(0 支付宝 1 微信 2其它)
    private String payTypeStr;
    private String orderDesc;   //订单描述(其它信息)

    public String getOrderStatusStr() {
        //订单状态(0 未支付 1 已支付)
        if (orderStatus!=null){
            if (orderStatus==1){
                orderStatusStr="已支付";
            }
            if (orderStatus==0){
                orderStatusStr="未支付";
            }
        }
        return orderStatusStr;
    }

    public void setOrderStatusStr(String orderStatusStr) {
        this.orderStatusStr = orderStatusStr;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(String orderNum) {
        this.orderNum = orderNum;
    }

    public Date getOrderTime() {
        return orderTime;
    }

    public void setOrderTime(Date orderTime) {
        this.orderTime = orderTime;
    }

    public String getOrderTimeStr() {
        if (orderTime!=null){
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm");
            orderTimeStr = sdf.format(orderTime);
        }
        return orderTimeStr;
    }

    public void setOrderTimeStr(String orderTimeStr) {
        this.orderTimeStr = orderTimeStr;
    }

    public int getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(int orderStatus) {
        this.orderStatus = orderStatus;
    }

    public int getPeopleCount() {
        return peopleCount;
    }

    public void setPeopleCount(int peopleCount) {
        this.peopleCount = peopleCount;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public List<Traveller> getTravellers() {
        return travellers;
    }

    public void setTravellers(List<Traveller> travellers) {
        this.travellers = travellers;
    }

    public Member getMember() {
        return member;
    }

    public void setMember(Member member) {
        this.member = member;
    }

    public Integer getPayType() {
        return payType;
    }

    public void setPayType(Integer payType) {
        this.payType = payType;
    }

    public String getPayTypeStr() {
        //支付方式(0 支付宝 1 微信 2其它)
        if (payType!=null){
            if (payType==0){
                payTypeStr = "支付宝";
            }else if (payType==1){
                payTypeStr = "微信";
            }else if (payType==2){
                payTypeStr = "2其它";
            }
        }
        return payTypeStr;
    }

    public void setPayTypeStr(String payTypeStr) {
        this.payTypeStr = payTypeStr;
    }

    public String getOrderDesc() {
        return orderDesc;
    }

    public void setOrderDesc(String orderDesc) {
        this.orderDesc = orderDesc;
    }
}
