package sample;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;

public class UltimateKeyPresser extends Thread {

    private int[] buttonXY = Controller.getButtonXY();

    public static boolean[] buttons = {false,  // need to press green  button
            false,  // need to press red    button
            false,  // need to press yellow button
            false,  // need to press blue   button
            false}; // need to press orange button


    private boolean analyze = true;

    public UltimateKeyPresser() throws AWTException {
        this.analyze = true;
    }

    public void finish() {
        analyze = false;
    }

    ButtonSingleClick click;

    @Override
    public void run() {
        ButtonSingleClick click = new ButtonSingleClick();
        do {
            try {

                takeScreenshot();
                Thread.sleep(1000 / 150); //150

            } catch (AWTException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        } while (analyze);
    }

    private void takeScreenshot() throws AWTException {

        Robot myRobot = new Robot();

        Rectangle screenRect = new Rectangle(Toolkit.getDefaultToolkit().getScreenSize());
        BufferedImage capture = myRobot.createScreenCapture(screenRect);


        for (int BI = 0; BI < 5; BI++) {
            //buttons[BI] = false;

            int redBucket = 0;
            int greenBucket = 0;
            int blueBucket = 0;
            int pixelCount = 0;

            // 0+BI -> 0 + index of button
//////            for (int i = buttonXY[0 + BI * 2] - 20; i <= buttonXY[0 + BI * 2] + 20; i += 4) {
//////                for (int j = buttonXY[1 + BI * 2] - 4; j <= buttonXY[1 + BI * 2] + 4; j += 4) {
////            for (int i = buttonXY[0 + BI * 2] - 20; i <= buttonXY[0 + BI * 2] + 20; i += 2) {
////                for (int j = buttonXY[1 + BI * 2] - 4; j <= buttonXY[1 + BI * 2] + 4; j += 4) {
//            for (int i = buttonXY[0 + BI * 2] - 20; i <= buttonXY[0 + BI * 2] + 20; i += 4) {
//                for (int j = buttonXY[1 + BI * 2] - 10; j <= buttonXY[1 + BI * 2] + 10; j += 4) {
            for (int i = buttonXY[0 + BI * 2] - 15; i <= buttonXY[0 + BI * 2] + 15; i += 3) {
                for (int j = buttonXY[1 + BI * 2] - 12; j <= buttonXY[1 + BI * 2] + 12; j += 4) {
                    Color c = new Color(capture.getRGB(i, j));
                    pixelCount++;
                    redBucket += c.getRed();
                    greenBucket += c.getGreen();
                    blueBucket += c.getBlue();
                }
            }
            Color averageColor = new Color(redBucket / pixelCount, greenBucket / pixelCount, blueBucket / pixelCount);

            /*
            // rgb
            switch (BI) {
                case 0: //green
                    if (    averageColor.getRed() < 100 &&
                            averageColor.getGreen() > 100 &&
                            averageColor.getBlue() < 120 &&

                            averageColor.getGreen() > averageColor.getRed() &&
                            averageColor.getGreen() > averageColor.getBlue()
                            ) {
                        buttons[0] = true;
                        myRobot.keyPress(KeyEvent.VK_1);
                    } else {
                        if (buttons[0]) {
                            buttons[0] = false;
                            myRobot.keyRelease(KeyEvent.VK_1);

                        }
                    }
                    break;
                case 1: //red
                    if (    averageColor.getRed() > 100 &&
                            averageColor.getGreen() < 120 &&
                            averageColor.getBlue() < 120 &&

                            averageColor.getRed() > averageColor.getGreen() &&
                            averageColor.getRed() > averageColor.getBlue()
                            ) {
                        buttons[1] = true;
                        myRobot.keyPress(KeyEvent.VK_2);
                    } else {
                        if (buttons[1]) {
                            buttons[1] = false;
                            myRobot.keyRelease(KeyEvent.VK_2);

                        }
                    }
                    break;
                case 2: //yellow
                    if (    averageColor.getRed() > 100 &&
                            averageColor.getGreen() > 100 &&
                            averageColor.getBlue() < 110 &&

                            averageColor.getRed() > averageColor.getBlue()&&
                            averageColor.getGreen() > averageColor.getBlue()
                            ) {
                        buttons[2] = true;
                        myRobot.keyPress(KeyEvent.VK_3);
                    } else {
                        if (buttons[2]) {
                            buttons[2] = false;
                            myRobot.keyRelease(KeyEvent.VK_3);

                        }
                    }
                    break;

                case 3: //blue
                    if (    averageColor.getRed() < 110 &&
                            averageColor.getGreen() > 100 &&
                            averageColor.getBlue() > 110 &&

                            averageColor.getBlue() > averageColor.getRed() &&
                            averageColor.getGreen() > averageColor.getRed()
                            ) {
                        buttons[3] = true;
                        myRobot.keyPress(KeyEvent.VK_4);
                    } else {
                        if (buttons[3]) {
                            buttons[3] = false;
                            myRobot.keyRelease(KeyEvent.VK_4);

                        }
                    }
                    break;
                case 4: //orange
                    if (    averageColor.getRed() > 120 &&
                            averageColor.getGreen() > 100 &&
                            averageColor.getBlue() < 100 &&

                            averageColor.getRed() > averageColor.getGreen() &&
                            averageColor.getRed() > averageColor.getBlue() &&
                            averageColor.getGreen() > averageColor.getBlue()
                            ) {
                        buttons[4] = true;
                        myRobot.keyPress(KeyEvent.VK_5);
                    } else {
                        if (buttons[4]) {
                            buttons[4] = false;
                            myRobot.keyRelease(KeyEvent.VK_5);
                        }
                    }
                    break;
            }
            */

            //hsb
            float[] averageHSB = new float[3];
            Color.RGBtoHSB(averageColor.getRed(), averageColor.getGreen(), averageColor.getBlue(), averageHSB);
            double Hue, Sat, Bri;
            Hue = averageHSB[0] * 360;
            Sat = averageHSB[1] * 100;
            Bri = averageHSB[2] * 100;
            //grayS[BI]+=Sat;
            //grayB[BI]+=Bri;
            //if (maxGrayS < Sat) {
            //    maxGrayS = Sat;
            //}
            //if (maxGrayB < Bri) {
            //    maxGrayB = Bri;
            //}
            //count++;
            switch (BI) {
                case 0: //green
                    if (Sat >= 25 && Bri >= 27 && Hue > 50 && Sat != Bri) { // 25 27
                        if (!buttons[0]) {
                            buttons[0] = true;
                            //myRobot.keyPress(KeyEvent.VK_1);
                            ButtonSingleClick.pressButton(BI, myRobot);
                        }
                    } else {
                        if (buttons[0]) {
                            buttons[0] = false;
                            //myRobot.keyRelease(KeyEvent.VK_1);
                            ButtonSingleClick.releaseButton(BI, myRobot);
                        }
                    }
                    break;
                case 1: //red
                    if (Sat >= 29 && Bri >= 30 && Hue > -1 && Sat != Bri) { // 29 30
                        if (!buttons[1]) {
                            buttons[1] = true;
                            //myRobot.keyPress(KeyEvent.VK_2);
                            ButtonSingleClick.pressButton(BI, myRobot);
                        }
                    } else {
                        if (buttons[1]) {
                            buttons[1] = false;
                            //myRobot.keyRelease(KeyEvent.VK_2);
                            ButtonSingleClick.releaseButton(BI, myRobot);
                        }
                    }
                    break;
                case 2: //yellow
                    if (Sat >= 32 && Bri >= 32 && Hue > 15 && Sat != Bri) { // 32 32
                        if (!buttons[2]) {
                            buttons[2] = true;
                            //myRobot.keyPress(KeyEvent.VK_3);
                            ButtonSingleClick.pressButton(BI, myRobot);
                        }
                    } else {
                        if (buttons[2]) {
                            buttons[2] = false;
                            //myRobot.keyRelease(KeyEvent.VK_3);
                            ButtonSingleClick.releaseButton(BI, myRobot);
                        }
                    }
                    break;

                case 3: //blue
                    if (Sat >= 25 && Bri >= 30 && Hue > 100 && Sat != Bri) { // 25 30
                        if (!buttons[3]) {
                            buttons[3] = true;
                            //myRobot.keyPress(KeyEvent.VK_4);
                            ButtonSingleClick.pressButton(BI, myRobot);
                        }
                    } else {
                        if (buttons[3]) {
                            buttons[3] = false;
                            //myRobot.keyRelease(KeyEvent.VK_4);
                            ButtonSingleClick.releaseButton(BI, myRobot);
                        }
                    }
                    break;
                case 4: //orange
                    if (Sat >= 30 && Bri >= 35 && Hue > 10 && Sat != Bri) {// 30 35
                        if (!buttons[4]) {
                            buttons[4] = true;
                            //myRobot.keyPress(KeyEvent.VK_5);
                            ButtonSingleClick.pressButton(BI, myRobot);
                        }
                    } else {
                        if (buttons[4]) {
                            buttons[4] = false;
                            //myRobot.keyRelease(KeyEvent.VK_5);
                            ButtonSingleClick.releaseButton(BI, myRobot);
                        }
                    }
                    break;
            }
            System.out.printf("H:%3.0f   S:%3.0f   B%3.0f  \t", Hue, Sat, Bri);
            //System.out.printf("H:%3.0f   S:%3.0f   \t", grayS[BI]/count, grayB[BI]/count);
           // System.out.printf("H:%3.0f   S:%3.0f   \t", maxGrayS, maxGrayB);

        }
        //System.out.println(count);

        System.out.println("     " +
                ((buttons[0]) ? 1 : 0) + "-" +
                ((buttons[1]) ? 1 : 0) + "-" +
                ((buttons[2]) ? 1 : 0) + "-" +
                ((buttons[3]) ? 1 : 0) + "-" +
                ((buttons[4]) ? 1 : 0));
    }

    //public static double[] grayS = {0.0, 0.0, 0.0, 0.0, 0.0};
    //public static double[] grayB = {0.0, 0.0, 0.0, 0.0, 0.0};
    //public static double maxGrayS = 0;
    //public static double maxGrayB = 0;
    //public static int count = 0;
}

