package com.company.pojo;

/**
 * 实体类
 */
public class Express {
    private String expressid;//快递单号
    private String expresscompany;//快递公司
    private int number;//取件码
    //坐标
    private Integer x;
    private Integer y;

    //构造方法
    public Express() {

    }

    public Express(String expressid, String expresscompany, int number, Integer x, Integer y) {
        this.expressid = expressid;
        this.expresscompany = expresscompany;
        this.number = number;
        this.x = x;
        this.y = y;
    }

    @Override
    public String toString() {
        return "Express{" +
                "expressid='" + expressid + '\'' +
                ", expresscompany='" + expresscompany + '\'' +
                ", number=" + number +
                ", x=" + x +
                ", y=" + y +
                '}';
    }

    public String getExpressid() {
        return expressid;
    }

    public void setExpressid(String expressid) {
        this.expressid = expressid;
    }

    public String getExpresscompany() {
        return expresscompany;
    }

    public void setExpresscompany(String expresscompany) {
        this.expresscompany = expresscompany;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public Integer getX() {
        return x;
    }

    public void setX(Integer x) {
        this.x = x;
    }

    public Integer getY() {
        return y;
    }

    public void setY(Integer y) {
        this.y = y;
    }
}



