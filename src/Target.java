import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.Graphics;

import org.newdawn.slick.Image;

import java.util.Random;

public class Target extends CannonGame {
    Image target;
    int ogWidth = 130;
    int width = ogWidth;
    int ogHeight = 59;
    int height = ogHeight;
    static Random r = new Random();
    static int x = r.nextInt(250) + 150;
    static int y = 820;
    static int yRange = 1;

    @Override
    public void update(GameContainer gameContainer, int i) throws SlickException {


    }

    @Override
    public void render(GameContainer gameContainer, Graphics graphics) throws SlickException {


        target = ResourceManager.getImage("resources/target.png");
        target = target.getScaledCopy(width, height);

        graphics.drawImage(target, x, y);
    }

    public boolean hit(Ball ball) {
        if ((ball.ballX + ball.width) >= x && (ball.ballX) <= (x + width)) {
            if ((ball.ballY + ball.height) >= y && (ball.ballY) <= (y)) {
                return true;
            }
        }
        return false;
    }

    static void reset() {
        x = r.nextInt(600) + 450;
        y = 820 - r.nextInt(yRange);
    }

    String setDifficulty(int dif) {
        switch (dif) {
            case 1:
                width = (int) (ogWidth);
                height = (int) (ogHeight);

                yRange = 1;
                return "Easy";
            case 2:
                width = (int) (ogWidth * 0.75);
                height = (int) (ogHeight * 0.75);

                yRange = 200;
                return "Normal";

            case 3:
                width = (int) (ogWidth * 0.2);
                height = (int) (ogHeight * 0.2);

                yRange = 400;

                return "Hard";
            case 4:
                width = (int) (ogWidth * 0.02);
                height = (int) (ogHeight * 0.02);

                yRange = 800;
                return "Pain";
        }
        return "Normal";
    }
}
