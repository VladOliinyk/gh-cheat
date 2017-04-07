package sample;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;

class UltimateKeyPresser extends Thread {

    private int TIME_DELAY = 5;

    private int[] buttonXY;

    public static boolean[] buttons = {false,  // need to press green  button
            false,  // need to press red    button
            false,  // need to press yellow button
            false,  // need to press blue   button
            false}; // need to press orange button


    private boolean analyze = true;

    public UltimateKeyPresser(int[] newButtonXY) throws AWTException {
        this.analyze = true;
        this.buttonXY = newButtonXY;
    }

    public void finish() {
        analyze = false;
    }


    @Override
    public void run() {
        do {
            try {

                takeScreenshot();
                Thread.sleep(TIME_DELAY);

            } catch (AWTException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        } while (analyze);
    }

    Robot myRobot;
    Rectangle screenRect;
    BufferedImage capture;
    Color clr;
    double Hue, Sat, Bri;

    float[] averageHSB;

    private void takeScreenshot() throws AWTException {

        myRobot = new Robot();

        screenRect = new Rectangle(Toolkit.getDefaultToolkit().getScreenSize());
        capture = myRobot.createScreenCapture(screenRect);


        for (int BI = 0; BI < 5; BI++) {

            int redBucket = 0;
            int greenBucket = 0;
            int blueBucket = 0;
            int pixelCount = 0;


            for (int i = buttonXY[0 + BI * 2] - 15; i <= buttonXY[0 + BI * 2] + 15; i += 3) {
                for (int j = buttonXY[1 + BI * 2] - 12; j <= buttonXY[1 + BI * 2] + 12; j += 4) {
                    clr = new Color(capture.getRGB(i, j));
                    pixelCount++;
                    redBucket += clr.getRed();
                    greenBucket += clr.getGreen();
                    blueBucket += clr.getBlue();
                }
            }
            Color averageColor = new Color(redBucket / pixelCount, greenBucket / pixelCount, blueBucket / pixelCount);

            //hsb
            averageHSB = new float[3];
            Color.RGBtoHSB(averageColor.getRed(), averageColor.getGreen(), averageColor.getBlue(), averageHSB);


            Hue = averageHSB[0] * 360;
            Sat = averageHSB[1] * 100;
            Bri = averageHSB[2] * 100;

            if (BI == 0) { //green
                if (Sat >= 25 && Bri >= 27 && Hue > 50 && Sat != Bri) { // 25 27
                    if (!buttons[0]) {
                        buttons[0] = true;
                        myRobot.keyPress(KeyEvent.VK_1);
                    }
                } else {
                    if (buttons[0]) {
                        buttons[0] = false;
                        myRobot.keyRelease(KeyEvent.VK_1);
                    }
                }
            }
            if (BI == 1) { //red
                if (Sat >= 29 && Bri >= 30 && Hue > -1 && Sat != Bri) { // 29 30
                    if (!buttons[1]) {
                        buttons[1] = true;
                        myRobot.keyPress(KeyEvent.VK_2);
                    }
                } else {
                    if (buttons[1]) {
                        buttons[1] = false;
                        myRobot.keyRelease(KeyEvent.VK_2);
                    }
                }
            }
            if (BI == 2) { //yellow
                if (Sat >= 32 && Bri >= 32 && Hue > 15 && Sat != Bri) { // 32 32
                    if (!buttons[2]) {
                        buttons[2] = true;
                        myRobot.keyPress(KeyEvent.VK_3);
                    }
                } else {
                    if (buttons[2]) {
                        buttons[2] = false;
                        myRobot.keyRelease(KeyEvent.VK_3);
                    }
                }
            }
            if (BI == 3) { //blue
                if (Sat >= 25 && Bri >= 30 && Hue > 100 && Sat != Bri) { // 25 30
                    if (!buttons[3]) {
                        buttons[3] = true;
                        myRobot.keyPress(KeyEvent.VK_4);
                    }
                } else {
                    if (buttons[3]) {
                        buttons[3] = false;
                        myRobot.keyRelease(KeyEvent.VK_4);
                    }
                }
            }
            if (BI == 4) {  //orange
                if (Sat >= 30 && Bri >= 35 && Hue > 10 && Sat != Bri) {// 30 35
                    if (!buttons[4]) {
                        buttons[4] = true;
                        myRobot.keyPress(KeyEvent.VK_5);
                    }
                } else {
                    if (buttons[4]) {
                        buttons[4] = false;
                        myRobot.keyRelease(KeyEvent.VK_5);
                    }
                }

            }

        }
    }

    public void terminate() {
        if (super.isAlive()) {
            ThreadGroup group = super.getThreadGroup();
            while (group != null) {
                group .interrupt();
                group = super.getThreadGroup();
            }
            super.interrupt();
        }
    }

}

