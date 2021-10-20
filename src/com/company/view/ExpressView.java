package com.company.view;

import com.company.dao.Expressdao;
import com.company.exception.OutNumberBoundException;
import com.company.pojo.Coordinate;
import com.company.pojo.Express;

import java.util.List;
import java.util.Scanner;

/**
 * 视图
 */
public class ExpressView {
    private Scanner input = new Scanner(System.in);
    private Expressdao expressdao = new Expressdao();

    /**
     * 其实菜单
     * @return //菜单编号
     */
    public int starMenu(){
        int num = 0;
        do{
            //显示菜单
            System.out.println("--------欢迎来到木子的快递管理系统！--------");
            System.out.println("请选择身份：");
            System.out.println("1，管理员");
            System.out.println("2，普通用户");
            System.out.println("0，退出");
            String startnum = input.nextLine();
            try{
                num = vaildataNum(startnum,0,2);
                break;
            }catch (NumberFormatException e){
                System.out.println(e.getMessage());
            }catch (OutNumberBoundException e){
                System.out.println(e.getMessage());
            }
        }while (true);
        if(num==1){
            adminMenu();
        }else if(num==2){
            userMenu();
        }else if(num==0){
            System.out.println("谢谢使用！");
        }
        return num;
    }
    /**
     * 普通用户操作
     */
    public void userMenu(){
        int number;
        do{
            System.out.println("请输入取件码：");
            String strNumber = input.nextLine();
            try {
                number = vaildataNum(strNumber,100000,900000);
                break;
            }catch (OutNumberBoundException e){
                e.printStackTrace();
            }
        }while (true);
        //判断取件码是否存在
        Express express = expressdao.findExpressByNumber(number);
        if(express == null) {
            System.out.println("该快递码不存在！");
        } else {
            System.out.println("快递信息："+express);
            if (expressdao.delete(express)) {
                System.out.println("取件成功！");
            }else {
                System.out.println("取件失败！");
            }
        }
    }
    /**
     * 管理员菜单
     */
    public void adminMenu(){
        int num = 0;
        do{
            System.out.println("1,快递录入：");
            System.out.println("2,删除快递：");
            System.out.println("3,修改快递：");
            System.out.println("4,查看所有快递：");
            System.out.println("0,返回上一级目录：");
            System.out.println("请输入功能序号：");
            String startnum = input.nextLine();
            try{
                num = vaildataNum(startnum,0,4);
                break;
            }catch (NumberFormatException e){
                System.out.println(e.getMessage());
            }catch (OutNumberBoundException e){
                System.out.println(e.getMessage());
            }
        }while (true);
        if(num==1){
            addExpress();
        }else if(num==2){
            deleteExpress();
        }else if(num==3){
            updateExpress();
        }else if(num==4){
            System.out.println("--------- 查看所有快递！---------");
            List<Express> expressList = expressdao.getExpressList();
            for (Express express : expressList) {
                System.out.println(express);
            }
        }else if(num==0){
            starMenu();
        }
    }

    /**
     * 根据快递单号修改快递信息
     */
    public void updateExpress(){
        System.out.println("-------- 修改快递! --------");
        System.out.println("请输入要修改的快递单号：");
        String number = input.nextLine();
        Express newExpress = new Express();
        System.out.println("请输入修改后的快递单号和快递公司：");
        newExpress.setExpressid(input.nextLine());
        newExpress.setExpresscompany(input.nextLine());
        try {
            if(expressdao.update(number,newExpress)){
                System.out.println("修改成功!");
            }else{
                System.out.println("修改失败！");
            }
        } catch (Exception exception) {
            System.out.println("修改失败！");
            exception.printStackTrace();
        }
    }

    /**
     * 根据快递单号删除快递
     */
    public void deleteExpress(){
        System.out.println("-------- 删除快递! --------");
        System.out.println("请输入要删除的快递单号：");
        String number = input.nextLine();
        try {
            if(expressdao.delete(number)){
                System.out.println("删除成功!");
            }else {
                System.out.println("删除失败!");
            }
        } catch (Exception exception) {
            System.out.println("删除失败!");
            System.err.println(exception.getMessage());
            //exception.printStackTrace();
        }
    }

    /**
     * 添加快递信息
     */
    public void addExpress(){
        System.out.println("-------- 快递录入! --------");
        Express express = new Express();
        System.out.println("请输入快递单号和快递公司：");
        express.setExpressid(input.nextLine());
        express.setExpresscompany(input.nextLine());
        try {
            express = expressdao.add(express);
            System.out.println("添加成功！快递放在了快递柜中第"+(express.getX()+1)+"排第"+(express.getY()+1)+"列。");
        } catch (Exception exception) {
            System.out.println("添加失败！");
            System.err.println(exception.getMessage());
            exception.printStackTrace();
        }
    }

    /**
     * 打印所有非空快递信息
     * @param allExpress
     */
    public void printOutAllExpress(Express[][] allExpress){
        for(int i=0;i<allExpress.length;i++){
            for (int j=0;j<allExpress[i].length;j++){
                if(allExpress[i][j] != null){
                    System.out.println(allExpress[i][j]);
                }
            }
        }
    }

    /**
     * 按照快递柜方式打印
     * @param allExpress
     */
    public void printOutAllExpressByGrid(Express[][] allExpress){
        for(int i=0;i<allExpress.length;i++){
            for (int j=0;j<allExpress[i].length;j++){
                if(allExpress[i][j] == null){
                    System.out.printf("%-40s","空");
                }else{
                    System.out.printf("%-60s",allExpress[i][j]);
                }
                System.out.println();
            }
        }
    }


    /**
     * 验证用户输入是否合法
     * @param startnum//输入的值
     * @param begin//菜单编号开始值
     * @param end//菜单编号结束值
     * @return//菜单编号
     * @throws NumberFormatException
     * @throws OutNumberBoundException
     */
    public int vaildataNum(String startnum,int begin,int end)throws NumberFormatException,OutNumberBoundException{
        try {
            int num = Integer.valueOf(startnum);
            if(num<begin || num>end){
                throw new OutNumberBoundException("数字的范围必须在"+begin+"和"+end+"之间！");
            }
            return num;
        }catch (NumberFormatException exception){
            throw new NumberFormatException("输入的必须是数字！");
        }
    }
}
