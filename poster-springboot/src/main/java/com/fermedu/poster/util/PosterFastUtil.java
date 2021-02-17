package com.fermedu.poster.util;

import com.fermedu.poster.entity.CompanyInfo;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.InputStream;
import java.net.URL;

/**
 * @Program: poster-generator
 * @Create: 2021-02-16 23:33
 * @Author: JustThink
 * @Description:
 * @Include:
 **/
public class PosterFastUtil {
    private static final String BACKGROUND_IMG = "https://s3.ax1x.com/2021/02/16/ycq1J0.png"; // 背景图片
    //二维码图片

    /**
     * 获取resources下的文件输入流
     */
    private static InputStream getInputStream(String fileName) {
        return PosterUtil.class.getClassLoader().getResourceAsStream(fileName);
    }

    public static String drawPoster(CompanyInfo companyInfo, String qrcodePath, String pngImg){
        try {
            long startTime = System.currentTimeMillis();
// 1. 创建画布
//            BufferedImage backgroundImg = ImageIO.read(getInputStream(BACKGROUND_IMG));
            BufferedImage backgroundImg = ImageIO.read(new URL(BACKGROUND_IMG)); // my from internet
            BufferedImage canvas = new BufferedImage(backgroundImg.getWidth(),backgroundImg.getHeight(),BufferedImage.TYPE_INT_RGB);
            Graphics2D g = (Graphics2D) canvas.getGraphics();
// 设置抗锯齿
            g.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_LCD_HRGB);

            // 2. 将头像设置为圆角
            String avatarImgs= companyInfo.getCompanyLogo();
            BufferedImage avatar = ImageIO.read(new URL(avatarImgs));
            int width = 120;
            //透明底的图片
            BufferedImage newAvatar = new BufferedImage(width, width, BufferedImage.TYPE_4BYTE_ABGR);
            Graphics2D graphics = newAvatar.createGraphics();
            //把图片切成一个圓
            {
                graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                //留一个像素的空白区域，这个很重要，画圆的时候把这个覆盖
                int border = 1;
                //图片是一个圆型
                Ellipse2D.Double shape = new Ellipse2D.Double(border, border, width - border * 2, width - border * 2);
                //需要保留的区域
                graphics.setClip(shape);
                graphics.drawImage(avatar, border, border, width - border * 2, width - border * 2, null);
                graphics.dispose();
            }

            //在圆图外面再画一个圆
            {
                //新创建一个graphics，这样画的圆不会有锯齿
                graphics = newAvatar.createGraphics();
                graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                int border = 3;
                //画笔是4.5个像素，BasicStroke的使用可以查看下面的参考文档
                //使画笔时基本会像外延伸一定像素，具体可以自己使用的时候测试
                Stroke s = new BasicStroke(4.5F, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND);
                graphics.setStroke(s);
                graphics.setColor(Color.WHITE);
                graphics.drawOval(border, border, width - border * 2, width - border * 2);
                graphics.dispose();
            }

            // 3. 将背景图和头像结合
            // 画背景
            g.drawImage(backgroundImg.getScaledInstance(backgroundImg.getWidth(), backgroundImg.getHeight(), Image.SCALE_DEFAULT), 0, 0, null);
            // 背景上画头像
            g.drawImage(newAvatar.getScaledInstance(150, 150, Image.SCALE_DEFAULT), 90, 160, null);

            // 4. 写字（昵称）
            g.setColor(Color.BLACK);
            g.setFont(new Font("黑体",Font.BOLD,40));
            g.drawString(companyInfo.getCompanyName(), 160, 380);

            //  画二维码
            String qrCodeUrl=qrcodePath;
            BufferedImage qrCodeUrls = ImageIO.read(new File(qrCodeUrl));
            g.drawImage(qrCodeUrls.getScaledInstance(200,200, Image.SCALE_DEFAULT), 520, 380, null);

            g.dispose();
            File resultImg = new File(pngImg);
            ImageIO.write(canvas, "png", resultImg);

            System.out.println("生成成功！");
            System.out.println("耗时: " + (System.currentTimeMillis()-startTime)/1000.0 + "s");
            System.out.println("生成文件路径: " + resultImg.getAbsolutePath());
            return resultImg.getAbsolutePath();
        }catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}


