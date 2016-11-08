package sample;

import java.awt.*;
import java.awt.event.KeyEvent;

/**
 * Created by vlado on 26.10.2016.
 */
public class ButtonSingleClick extends Thread {

    private boolean click = true;

    public void finish() {
        click = false;
    }

    @Override
    public void run() {
        do {

        } while (click);
        finish();
    }

    public static void pressButton(int BI, Robot myRobot) {
        try {
            Thread.sleep(25);//speed x1 = 35 | speed x1.5 = 25 |
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        switch (BI) {
            case 0: //green
                myRobot.keyPress(KeyEvent.VK_1);
                break;
            case 1: //red
                myRobot.keyPress(KeyEvent.VK_2);
                break;
            case 2: //yellow
                myRobot.keyPress(KeyEvent.VK_3);
                break;
            case 3: //blue
                myRobot.keyPress(KeyEvent.VK_4);
                break;
            case 4: //orange
                myRobot.keyPress(KeyEvent.VK_5);
                break;
        }
    }

    public static void releaseButton(int BI, Robot myRobot) {
        try {
            Thread.sleep(5);//5
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        switch (BI) {
            case 0: //green
                myRobot.keyRelease(KeyEvent.VK_1);
                break;
            case 1: //red
                myRobot.keyRelease(KeyEvent.VK_2);
                break;
            case 2: //yellow
                myRobot.keyRelease(KeyEvent.VK_3);
                break;
            case 3: //blue
                myRobot.keyRelease(KeyEvent.VK_4);
                break;
            case 4: //orange
                myRobot.keyRelease(KeyEvent.VK_5);
                break;
        }
    }
}
