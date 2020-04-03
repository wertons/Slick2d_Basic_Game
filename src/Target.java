import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.Graphics;

import org.newdawn.slick.Image;

import java.util.Random;

public class Target extends CannonGame {
    Image target;
    int targetInitWidth = 130;
    int targetWidth = targetInitWidth;
    int targetInitHeight = 59;
    int targetHeight = targetInitHeight;
    static Random r = new Random();
    static int targetX = r.nextInt(250) + 150;
    static int targetY = 820;
    static int yRange = 1;
    @Override
    public void render(GameContainer gameContainer, Graphics graphics) throws SlickException {
        target = ResourceManager.getImage("resources/target.png");
        target = target.getScaledCopy(targetWidth, targetHeight);

        graphics.drawImage(target, targetX, targetY);
    }

    public boolean hit(Ball ball) {
        if ((ball.ballX + ball.ballWidth) >= targetX && (ball.ballX) <= (targetX + targetWidth)) {
            if ((ball.ballY + ball.ballHeight) >= targetY && (ball.ballY) <= (targetY)) {
                return true;
            }
        }
        return false;
    }

    static void reset() {
        targetX = r.nextInt(600) + 450;
        targetY = 820 - r.nextInt(yRange);
    }

    String setDifficulty(int dif) {
        switch (dif) {
            case 1:
                targetWidth = (int) (targetInitWidth);
                targetHeight = (int) (targetInitHeight);

                yRange = 1;
                return "Easy";
            case 2:
                targetWidth = (int) (targetInitWidth * 0.75);
                targetHeight = (int) (targetInitHeight * 0.75);

                yRange = 200;
                return "Normal";

            case 3:
                targetWidth = (int) (targetInitWidth * 0.2);
                targetHeight = (int) (targetInitHeight * 0.2);

                yRange = 400;

                return "Hard";
            case 4:
                targetWidth = (int) (targetInitWidth * 0.02);
                targetHeight = (int) (targetInitHeight * 0.02);

                yRange = 800;
                return "Pain";
        }
        return "Normal";
    }
}
