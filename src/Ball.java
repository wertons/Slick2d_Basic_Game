import org.newdawn.slick.*;
import org.newdawn.slick.Color;
import org.newdawn.slick.Image;

import java.awt.*;
import java.lang.annotation.Target;
import java.util.Random;

public class Ball extends CannonGame {

    Image[] sprites;
    int spriteCount = 20;
    Image currBall;
    int width = 80;
    int height = 80;
    int initX;
    int initY;
    Integer ballX;
    Integer ballY;
    Integer boxWidth;
    Integer boxHeight;
    float angleDeg;
    double angleRad;
    double velocity;
    double gravity = 0.5;
    double t = 0;
    Random r = new Random();

    Ball() {
    }

    Ball(float angle, int strength,int x, int y) {
        initX = x;
        initY = y;
        this.angleDeg = angle;
        this.angleRad = Math.toRadians(angle);
        this.velocity = (strength + 100) / 9;
        currBall = ResourceManager.getImage("resources/balls/ball_" + (r.nextInt(spriteCount)+1) + ".png");
        currBall = currBall.getScaledCopy(width,height);
    }

    @Override
    public void init(GameContainer gameContainer) throws SlickException {
        sprites = new Image[spriteCount];
        for (int i = 0; i < spriteCount; i++) {
            sprites[i] = ResourceManager.getImage("resources/balls/ball_" + (i + 1) + ".png");
        }
    }

    @Override
    public void update(GameContainer gameContainer, int i) throws SlickException {

        try {
            if (!hasFallen()) {
                t = t + 0.5;
                ballX = (int) Math.floor((initX) +((Math.cos(angleRad)) * t) * velocity);
                ballY = (int) Math.floor((initY) - ((((Math.sin(angleRad)) * t) - (((t * (t / 10)) * gravity) / 5))) * velocity);
            }
        } catch (Exception ignore) {

        }



    }

    @Override
    public void render(GameContainer gameContainer, org.newdawn.slick.Graphics graphics) throws SlickException {



        if (boxWidth == null) {
            boxWidth = gameContainer.getWidth();
            boxHeight = gameContainer.getHeight();
        }
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
