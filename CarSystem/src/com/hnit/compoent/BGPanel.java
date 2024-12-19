package com.hnit.compoent;

import javax.swing.*;
import java.awt.*;

/**
 * ClassName: BGPanel
 * Description:
 *
 * @Author wzq
 * @Create 2024/6/7 10:11
 * @Version 1.0
 */
public class BGPanel extends JPanel {
    private Image BGImage;

    public BGPanel(Image BGImage) {
        this.BGImage = BGImage;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(BGImage,0,0,this.getWidth(),this.getHeight(),null);
    }
}
