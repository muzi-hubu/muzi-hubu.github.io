package com.company.test;

import com.company.pojo.Express;
import com.company.view.ExpressView;

import javax.swing.text.BadLocationException;
import javax.swing.text.Position;
import javax.swing.text.View;
import java.awt.*;

/**
 * 测试
 */
public class MainTest {
    public static void main(String[] args){
        ExpressView expressView = new ExpressView();
        while (true){
            if(expressView.starMenu()==0){
                break;
            }
        }
    }
}
