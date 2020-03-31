import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.Graphics;

import org.newdawn.slick.Image;

import java.util.Random;

public class Target extends CannonGame {
    Image target;
    int width = 130;
    int height = 59;
    static Random r = new Random();
    static int x = r.nextInt(250) + 150;
    int y;

    @Override
    public void update(GameContainer gameContainer, int i) throws SlickException {


    }

    @Override
    public void render(GameContainer gameContainer, Graphics graphics) throws SlickException {
        y = gameContainer.getHeight() - 80;


        target = ResourceManager.getImage("resources/target.png");

        graphics.drawImage(target, x, y);
    }

    public boolean hit(Ball ball) {
        if ((ball.ballX + ball.width) >= x && (ball.ballX) <= (x + width)) {
            if ((ball.ballY + ball.height) >= y && (ball.ballY) <= (y )) {
                return true;
            }
        }
        return false;
    }

    static void reset() {
        x = r.nextInt(750) + 450;
    }

    void setDifficulty(double dif){
        width =(int)( width * dif);
    }
}
