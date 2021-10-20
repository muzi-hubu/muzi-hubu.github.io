package com.company.dao;

import com.company.pojo.Coordinate;
import com.company.pojo.Express;
import com.sun.xml.internal.bind.v2.runtime.Coordinator;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Random;

/**
 * 快递操作
 * 管理员：快递录入、删除快递、修改快递、查看快递
 * 用户：快递取出
 */

public class Expressdao {
    private List<Express> expressList = new ArrayList<>(100);

    public Expressdao(){
        //初始化快递柜
        expressList.add(new Express("s10001","顺丰",123456,0,0));
        expressList.add(new Express("s10004","顺丰",426342,4,2));
        expressList.add(new Express("y10009","圆通",163156,3,6));
        expressList.add(new Express("b10032","百世",187466,4,9));
        expressList.add(new Express("z10003","中通",434726,1,3));
    }


    /**
     * 根据取件码查询快递的位置
     * @param number
     * @return
     */
    public Express findExpressByNumber(int number){
        for (int i=0;i<expressList.size();i++){
            if(expressList.get(i).getNumber()==number)
                return expressList.get(i);
        }
        return null;
    }

    /**
     * 根据快递单号修改快递信息
     * @param number
     * @return
     */
    public boolean update(String number,Express newExpress) throws Exception {
        //判断改单号的快递是否存在
        int index = findExpressByNumber(number);
        if(index == -1){
            throw new Exception("该快递不存在！");
        }
        //根据坐标获取要修改的快递信息
        Express express = expressList.get(index);
        express.setExpresscompany(newExpress.getExpresscompany());
        express.setNumber(newExpress.getNumber());
        return true;
    }

    /**
     * 根据快递单号进行删除
     * @param number //快递单号
     * @return
     * @throws Exception
     */
    public boolean delete(String number) throws Exception {
        //判断改单号的快递是否存在
        int index = findExpressByNumber(number);
        if(index == -1){
            throw new Exception("该快递不存在！");
        }
        expressList.remove(index);//删除
        return true;
    }

    /**
     * 根据快递对象删除
     * @param express
     * @return
     */
    public boolean delete(Express express){
        return expressList.remove(express);
    }

    /**
     * 根据快递单号查找快递
     * @param number
     * @return
     */
    public int findExpressByNumber(String number){
        for (int i=0;i<expressList.size();i++){
            if (expressList.get(i).getExpressid().equals(number)){
                return i;
            }
        }
        return -1;
    }

    /**
     * 在快递柜中添加快递
     * @param express
     * @return
     * @throws Exception
     */
    public Express add(Express express) throws Exception {
        if(expressList.size()==100){//快递柜满了
            throw new Exception("快递柜已经满了！不能再放快递了！");
        }
        Random random = new Random();
        int x,y;
        do{
            x = random.nextInt(10);
            y = random.nextInt(10);
        }while (isExist(x,y));//表示有位置可以放快递
        //系统随机生成取件码
        int number;
        do {
            number = random.nextInt(900000)+100000;
        }while (isExistNumber(number));
        express.setNumber(number);//设置取件码
        express.setX(x);
        express.setY(y);
        expressList.add(express);
        return express;
    }


    /**
     * 判断取件码是否重复
     * @param number
     * @return
     */
    public boolean isExistNumber(int number){
        for (Express express : expressList) {
            if (express.getNumber()==number){
                return true;
            }
        }
        return false;
    }

    /**
     * 判断坐标是否存在
     * @param x
     * @param y
     * @return
     */
    public boolean isExist(int x,int y){
        for (Express express : expressList) {
            if (express.getX()==x && express.getY()==y){
                return true;
            }
        }
        return false;
    }

    public List<Express> getExpressList() {
        return expressList;
    }

    public void setExpressList(List<Express> expressesList) {
        this.expressList = expressesList;
    }


}
