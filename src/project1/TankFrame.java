package project1;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.SQLOutput;

public class TankFrame extends Frame {

    // 将属于坦克的属性封装成坦克类
    // 之后使用时直接实例化，代码更清爽
    Tank myTank = new Tank(200,200,Dir.DOWN); // 坦克
    Bullet b = new Bullet(300,300,Dir.DOWN); // 子弹

    // 构造函数
    public TankFrame(){

        // new一个窗口

        // 设置窗口大小
        setSize(800,600); // 宽/长：800，高/宽：600
        // 设置是否能改变窗口大小
        setResizable(false); // 窗口大小固定
        // 设置标题栏文字
        setTitle("tank war");
        // 让窗口显示出来
        setVisible(true); // 设置可见

        // 键盘监听器
        this.addKeyListener(new MyKeyListener());

        // 之前无法点击窗口上的“x“关闭窗口
        // 添加窗口监听器 监听windowClosing 点击窗口上的“x“时关闭窗口
        addWindowListener(new WindowAdapter(){ // 匿名类
            @Override
            public void windowClosing(WindowEvent e){

                System.exit(0);
            }
        });
    }

    // 窗口第一次显示出来的时候、被别人盖住又出来的时候、窗口改变大小的时候
    // 即窗口需要重新绘制的时候 会自动调用paint方法
    // paint会把原来的背景清掉 重新绘制
    // 只能自动调用paint，不能主动调用paint 因为无法调用Graphics g画笔(由系统调用)
    // 重写paint方法
    @Override
    public void paint(Graphics g){ // Grahphics(类) g 是窗口递给你的一只画笔，想画什么就画什么
//        System.out.println("paint");
        // 以窗口左上角为原点，横向为x轴，纵向为y轴
        // 填充一个坐标是（200,200）大小是50*50的矩形
//        g.fillRect(200,200,50,50);

        //该行是对画坦克以及移动的代码进行封装
        // 让添加的这个矩形能够移动
        myTank.paint(g); // 画出坦克
        b.paint(g); // 画出子弹
    }

    class MyKeyListener extends KeyAdapter {

        // 定义四个布尔值控制方向，以处理同时按下两个即以上按键的情况
        boolean bL = false;
        boolean bU = false;
        boolean bR = false;
        boolean bD = false;
        // 按键下压时自动调用
        @Override
        public void keyPressed(KeyEvent e){

            int key = e.getKeyCode(); // 哪个键被按下了，把该键的代码取出来
            // 控制矩形块上下左右移动
            switch(key){
                // VK表示虚拟键盘，VK_LEFT表示向左的键
                case KeyEvent.VK_LEFT:
//                    x -= 10;
                    bL = true;
                    break;
                // VK表示虚拟键盘，VK_LEFT表示向上的键
                case KeyEvent.VK_UP:
//                    y -= 10;
                    bU = true;
                    break;
                // VK表示虚拟键盘，VK_LEFT表示向右的键
                case KeyEvent.VK_RIGHT:
//                    x += 10;
                    bR = true;
                    break;
                // VK表示虚拟键盘，VK_LEFT表示向下的键
                case KeyEvent.VK_DOWN:
//                    y += 10;
                    bD = true;
                    break;
                default:
                    break;
            }

            setMainTankDir();
            // 我写的控制方向的语句
//            x = bL ? (bR ? x : x - 10) : (bR ? x + 10 : x);
//            y = bU ? (bD ? y : y - 10) : (bD ? y + 10 : y);

//            System.out.println("key pressed");
//            x += 30;
//            repaint(); // 默认调用paint()方法
        }

        // 按键抬起时自动调用
        @Override
        public void keyReleased(KeyEvent e){

            int key = e.getKeyCode(); // 哪个键被按下了，把该键的代码取出来
            // 控制矩形块上下左右移动
            switch(key){
                // VK表示虚拟键盘，VK_LEFT表示向左的键
                case KeyEvent.VK_LEFT:
//                    x -= 10;
                    bL = false;
                    break;
                // VK表示虚拟键盘，VK_LEFT表示向上的键
                case KeyEvent.VK_UP:
//                    y -= 10;
                    bU = false;
                    break;
                // VK表示虚拟键盘，VK_LEFT表示向右的键
                case KeyEvent.VK_RIGHT:
//                    x += 10;
                    bR = false;
                    break;
                // VK表示虚拟键盘，VK_LEFT表示向下的键
                case KeyEvent.VK_DOWN:
//                    y += 10;
                    bD = false;
                    break;
                default:
                    break;
            }
            setMainTankDir();
        }

        private void setMainTankDir(){

            // 不按键静止
            if(!bL && !bU && !bR && !bD) myTank.setMoving(false);
            else{

                myTank.setMoving(true); // 设置坦克移动

                // 封装后的修改
                if(bL) myTank.setDir(Dir.LEFT);
                if(bU) myTank.setDir(Dir.UP);
                if(bR) myTank.setDir(Dir.RIGHT);
                if(bD) myTank.setDir(Dir.DOWN);
            }

        }

    }




}
