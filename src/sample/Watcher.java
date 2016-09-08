package sample;

import java.awt.*;

public class Watcher extends Thread {

    private int currentButton = 0;

    public Watcher(int number) {
        currentButton = number;
    }

    private int X = 0;
    private int Y = 0;

    private int[] xArray = {-1, -2, -3};
    private int[] yArray = {-1, -2, -3};

    private int hackX = 0;
    private int hackY = 0;

    public int getX() {
        return hackX;
    }
    public int getY() {
        return hackY;
    }

    private boolean watch = false;

    public boolean isWatching() {
        return watch;
    }


    public void closeEyes() {
        watch = false;
    }

    @Override
    public void run() {
        watch = true;
        while (watch) {
            try {
                PointerInfo pointer = MouseInfo.getPointerInfo();
                X = (int) pointer.getLocation().getX();
                Y = (int) pointer.getLocation().getY();

                if (xArray[0] == xArray[1] && xArray[0] == xArray[2] && yArray[0] == yArray[1] && yArray[0] == yArray[2]) {
                    hackX = xArray[0];
                    hackY = yArray[0];
                    if (currentButton == 1) {
                        Controller.greenX = hackX;
                        Controller.greenY = hackY;
                        // Controller.coordsLabel.setText(hackX+":"+hackY);
                    }
                    closeEyes();
                } else {
                    xArray[0] = xArray[1];
                    yArray[0] = yArray[1];
                    xArray[1] = xArray[2];
                    yArray[1] = yArray[2];
                    xArray[2] = X;
                    yArray[2] = Y;
                }

                System.out.println( "\n" +
                        "x=" + xArray[0] + " y=" + yArray[0] + "\n" +
                        "x=" + xArray[1] + " y=" + yArray[1] + "\n" +
                        "x=" + xArray[2] + " y=" + yArray[2]);


                //Robot robot = new Robot();
                //Color colorXY = robot.getPixelColor(X, Y);
                //System.out.println(X + ":" + Y + " - " + colorXY);

                Thread.sleep(500);
            } catch (InterruptedException e) {}
        }
    }
}
