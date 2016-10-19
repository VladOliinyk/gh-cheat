package sample;
import javafx.fxml.FXML;
import javafx.scene.control.ColorPicker;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;

public class UltimateKeyPresser extends Thread {

    private int[] buttonXY = Controller.getButtonXY();

    private boolean[] buttons = {false,  // need to press green  button
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

    @Override
    public void run() {
        do {
            try {

                takeScreenshot();
                Thread.sleep(1000/100);

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
            for (int i = buttonXY[0+BI*2] - 20; i <= buttonXY[0+BI*2] + 20; i += 4) {
                for (int j = buttonXY[1+BI*2] - 4; j <= buttonXY[1+BI*2] + 4; j += 4) {
                    Color c = new Color(capture.getRGB(i, j));
                    pixelCount++;
                    redBucket += c.getRed();
                    greenBucket += c.getGreen();
                    blueBucket += c.getBlue();
                }
            }
            Color averageColor = new Color(redBucket / pixelCount, greenBucket / pixelCount, blueBucket / pixelCount);
            System.out.print("\t\t(" + averageColor.getRed() + "," + averageColor.getGreen() + "," + averageColor.getBlue() + ")\t\t");
            // this method is bullshit too
            switch (BI) {
                case 0: //green
                    if (averageColor.getGreen() > 120 && averageColor.getRed() < 120 && averageColor.getBlue() < 120 && averageColor.getRed() != averageColor.getGreen()) {
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
                    if (averageColor.getRed() > 115 && averageColor.getGreen() < 120 && averageColor.getBlue() < 115) {
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
                    if (averageColor.getRed() > 110 && averageColor.getGreen() > 110 && averageColor.getBlue() < 106) {
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
                    if (averageColor.getRed() < 100 && averageColor.getGreen() > 100 && averageColor.getBlue() > 110) {
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
                    if (averageColor.getRed() > 120 && averageColor.getGreen() > 100 && averageColor.getBlue() < 100) {
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

            //System.out.println("need to press: " + buttons[0] + " " + buttons[1] + " " + buttons[2]);
        }
        System.out.println("");
    }

}
