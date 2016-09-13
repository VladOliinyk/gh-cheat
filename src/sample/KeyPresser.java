package sample;

import java.awt.*;

public class KeyPresser extends Thread {

    private int buttonIndex = 0;
    private int buttonX = -1;
    private int buttonY = -1;
    private Robot myRobot = new Robot();

    private boolean analyze = true;

    public KeyPresser() throws AWTException {
        this.analyze = true;
    }

    public void setButton(int buttonNumber, int x, int y) {
        this.buttonIndex = buttonNumber;
        this.buttonX = x;
        this.buttonY = y;
    }

    public void finish() {
        analyze = false;
    }

    @Override
    public void run() {
        do {
            if (needToPressButton(buttonIndex, buttonX, buttonY)) {

                System.out.println(buttonIndex);

                myRobot.keyPress(48+buttonIndex);
                myRobot.keyRelease(48+buttonIndex);
                try {
                    Thread.sleep(152);
                } catch (InterruptedException e) {}
            }
        } while (analyze);
    }

    private boolean needToPressButton(int buttonIndex, int buttonX, int buttonY) {
        Color color;

        int redSum = 0;
        int greenSum = 0;
        int blueSum = 0;
/*
        for (int i=-1; i < 2; i++) {
            color = myRobot.getPixelColor(buttonX+(i*2), buttonY);
            redSum += color.getRed();
            greenSum += color.getGreen();
            blueSum += color.getBlue();
        }

        redSum = redSum/3;
        greenSum = greenSum/3;
        blueSum = blueSum/3;
*/

        color = myRobot.getPixelColor(buttonX, buttonY);
        redSum += color.getRed();
        greenSum += color.getGreen();
        blueSum += color.getBlue();

        //System.out.print("R:" + redSum + " G:" + greenSum + " B:" + blueSum);

        switch (buttonIndex) {
            case 1:
                if (greenSum > 120 && redSum < 120 && blueSum < 120) {
                    return true;
                }
                break;
            case 2:
                if (redSum > 120 && greenSum < 120 && blueSum < 120) {
                    return true;
                }
                break;
            case 3:
                if (greenSum > 120 && redSum > 120 && blueSum < 120) {
                    return true;
                }
                break;
        }

        return false;
    }
}
