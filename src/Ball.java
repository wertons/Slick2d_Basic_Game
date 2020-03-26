import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.UnicodeFont;

import java.awt.*;
import java.lang.annotation.Target;

public class Ball extends CannonGame {

    Image currBall;
    int width = 40;
    int height = 40;
    Integer ballX;
    Integer ballY;
    Integer boxWidth;
    Integer boxHeight;
    float angleDeg;
    double angleRad;
    double velocity;
    double gravity = 0.5;
    double t = 0;

    Ball() {
    }

    Ball(float angle, int strength) {
        this.angleDeg = angle;
        this.angleRad = Math.toRadians(angle);
        this.velocity = (strength + 100) / 9;
    }


    @Override
    public void update(GameContainer gameContainer, int i) throws SlickException {

        try {
            if (!hasFallen()) {
                t = t + 0.5;
                ballX = (int) Math.floor(((Math.cos(angleRad)) * t) * velocity);
                ballY = (int) Math.floor((boxHeight - 100) - ((((Math.sin(angleRad)) * t) - (((t * (t / 10)) * gravity) / 5))) * velocity);


            }
        } catch (Exception ignore) {

        }


    }

    @Override
    public void render(GameContainer gameContainer, org.newdawn.slick.Graphics graphics) throws SlickException {

        currBall = ResourceManager.getImage("resources/ball.png");
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
