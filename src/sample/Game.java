package sample;

import java.awt.*;

public class Game {

    private final int[] buttonXY = {    0, 0,   //  0 1   greenX, greenY
                                        0, 0,   //  2 3   redX, redY
                                        0, 0,   //  4 5   yellowX, yellowY
                                        0, 0,   //  6 7   blueX, blueY
                                        0, 0};  //  8 9   orangeX, orangeY

    private UltimateKeyPresser ultimateKeyPresser;
    Game(int yellowX, int yellowY, boolean isMultiplayer){
        if (!isMultiplayer) {
            setOtherButtonCoords(yellowX, yellowY);
        } else {
            setOtherButtonCoordsToMultiplayer(yellowX, yellowY);
        }

    }

    public void launchGame() throws AWTException {
        ultimateKeyPresser = new UltimateKeyPresser(buttonXY);
        ultimateKeyPresser.start();
    }

    public void stopGame() {
        ultimateKeyPresser.finish();
    }

    private void setOtherButtonCoords(int x, int y) {
        //green
        buttonXY[0] = x-125;
        buttonXY[1] = y-110;

        //red
        buttonXY[2] = x-60;
        buttonXY[3] = y-110;

        //yellow
        buttonXY[4] = x;
        buttonXY[5] = y-110;

        //blue
        buttonXY[6] = x+65;
        buttonXY[7] = y-110;

        //orange
        buttonXY[8] = x+130;
        buttonXY[9] = y-110;
    }

    private void setOtherButtonCoordsToMultiplayer(int x, int y) {
        //green
        buttonXY[0] = x-83;
        buttonXY[1] = y-73;

        //red
        buttonXY[2] = x-41;
        buttonXY[3] = y-73;

        //yellow
        buttonXY[4] = x;
        buttonXY[5] = y-73;

        //blue
        buttonXY[6] = x+43;
        buttonXY[7] = y-73;

        //orange
        buttonXY[8] = x+87;
        buttonXY[9] = y-73;
    }
}
