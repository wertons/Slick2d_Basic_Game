import org.newdawn.slick.*;
import org.newdawn.slick.Image;

import java.util.Random;

public class Ball extends CannonGame {

    Image[] sprites;
    int spriteCount = 20;
    Image currBall;
    int ballWidth = 80;
    int ballHeight = 80;
    int ballInitX;
    int ballInitY;
    Integer ballX;
    Integer ballY;
    Integer boxWidth;
    Integer boxHeight;
    float angleDeg;
    double angleRad;
    double velocity;
    double gravity = 0.5;
    double airTime = 0;
    Random rng = new Random();

    Ball() {
    }

    Ball(float angle, int strength, int x, int y) {
        ballInitX = x;
        ballInitY = y;
        this.angleDeg = angle;
        this.angleRad = Math.toRadians(angle);
        this.velocity = (double) (strength + 100) / 5;
        currBall = ResourceManager.getImage("resources/balls/ball_" + (rng.nextInt(spriteCount) + 1) + ".png");
        currBall = currBall.getScaledCopy(ballWidth, ballHeight);
    }

    @Override
    public void init(GameContainer gameContainer) throws SlickException {
        //Image Loader Start-----------------
        sprites = new Image[spriteCount];
        for (int i = 0; i < spriteCount; i++) {
            sprites[i] = ResourceManager.getImage("resources/balls/ball_" + (i + 1) + ".png");
        }
        //-----------------Image Loader End
        boxWidth = gameContainer.getWidth();
        boxHeight = gameContainer.getHeight();

    }

    @Override
    public void update(GameContainer gameContainer, int i) throws SlickException {
        //Path Calculator Start-----------------
        try {
            if (!hasFallen()) {

                airTime = airTime + 0.5;
                ballX = (int) Math.floor((ballInitX) + ((Math.cos(angleRad)) * airTime) * velocity);
                ballY = (int) Math.floor((ballInitY) - ((((Math.sin(angleRad)) * airTime) - (((airTime * (airTime / 10)) * gravity) / 5))) * velocity);
            }
        } catch (Exception ignore) {

        }
        //-----------------Path Calculator End


    }

    @Override
    public void render(GameContainer gameContainer, org.newdawn.slick.Graphics graphics) throws SlickException {

        try {
            graphics.drawImage(currBall, ballX, ballY);
        } catch (Exception ignore) {

        }

    }

    public boolean hasFallen() {
        try {
            return ballY > boxHeight;
        } catch (Exception ignore) {
            return false;
        }
    }
}
