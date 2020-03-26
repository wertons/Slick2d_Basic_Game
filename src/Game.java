import org.newdawn.slick.*;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Rectangle;

public class Game {
    public static void main(String[] args) throws SlickException {
        CannonGame game = new CannonGame();
        AppGameContainer app = new AppGameContainer(game, 1200, 900, false);
        app.setShowFPS(false);
        app.start();
    }
}

class CannonGame extends BasicGame {
    Landscape bg;
    Cannon cannon;
    Target target;
    Ball currBall;
    Rectangle resetLoad;
    boolean startup = true;
    boolean reset = false;
    int resetTimeout = 0;
    int resetTimer = 0;
    Font ft50;
    Font ft20;
    Font bFt;
    Image initBg;
    int frameset = 0;
    boolean activeBall = false;
    boolean devMode = false;
    int halfWidth;
    int halfHeight;
    int score = 0;

    public CannonGame() {
        super("Game");
    }

    @Override
    public void init(GameContainer gameContainer) throws SlickException {
        gameContainer.setTargetFrameRate(59);
        bg = new Landscape();
        cannon = new Cannon();
        target = new Target();
        currBall = new Ball();
        ft50 = ResourceManager.getFont("resources/WHITRABT.ttf", 50);
        ft20 = ResourceManager.getFont("resources/WHITRABT.ttf", 20);
        bFt = ResourceManager.getFont("resources/WHITRABT.ttf", 60);
        initBg = ResourceManager.getImage("resources/initBg.jpg");

    }

    @Override
    public void update(GameContainer gameContainer, int i) throws SlickException {
        Input input = gameContainer.getInput();

        //Frame set Start-----------------
        frameset++;
        if (frameset > 120) {
            frameset = 0;
        }
        //----------------- Frame set end

        //Projectile Update Start-----------------
        if (!startup) {
            cannon.update(gameContainer, i);
            if (!activeBall) {
                if (input.isKeyPressed(Input.KEY_SPACE)) {
                    currBall = cannon.fire();
                    activeBall = true;
                }
            }
        }
        if (activeBall) {
            currBall.update(gameContainer, i);
            if (currBall.hasFallen()) {
                activeBall = false;
            }
            try {
                if (target.hit(currBall)) {
                    score++;
                    activeBall = false;
                    Target.reset();
                }
            } catch (Exception ignore) {

            }

        }
        //------------------Projectile Update End

        //Developer mode Start-----------------
        if (input.isKeyDown(Input.KEY_D)) {
            devMode = true;
        } else {
            devMode = false;
        }
        //------------------Developer mode End

        if (input.isKeyDown(Input.KEY_ENTER) && startup) {
            startup = false;
        }

        //Reset Input Start--------------------
        if (input.isKeyDown(Input.KEY_R) && resetTimeout <= 0) {
            resetTimer++;
            if (resetTimer > 60) {
                //On reset actions
                currBall = new Ball();
                activeBall = false;
                resetTimeout = 30;
                resetTimer = 0;
                reset = true;
            }
        } else {
            resetTimer = 0;
        }
        if (resetTimeout > 0) {
            resetTimeout--;
        }
        //---------------------Reset Input End


    }

    @Override
    public void render(GameContainer gameContainer, Graphics graphics) throws SlickException {
        halfWidth = gameContainer.getWidth() / 2;
        halfHeight = gameContainer.getHeight() / 2;
        graphics.setColor(Color.black);

        if (startup) {
            initBg = initBg.getScaledCopy(gameContainer.getWidth(), gameContainer.getHeight());
            graphics.drawImage(initBg, 0, 0);
            ft50.drawString(halfWidth - 280,
                    halfHeight - 50, "press");
            bFt.drawString(halfWidth - 110,
                    halfHeight - 50, "ENTER", Color.black);
            ft50.drawString(halfWidth + 70,
                    halfHeight - 50, "to begin");


        } else {
            bg.render(gameContainer, graphics);
            cannon.render(gameContainer, graphics);
            target.render(gameContainer, graphics);
            if (activeBall) {
                currBall.render(gameContainer, graphics);
            }
            if (reset) {
                Target.reset();
                reset = false;
            }

            //Strength display Start-----------------
            graphics.setColor(Color.black);
            float rectWidth = 30;
            float rectHeight = gameContainer.getHeight() - 50;
            float cannonWidth = 120;
            float cannonHeight = 20;
            graphics.draw(new Rectangle(rectWidth, rectHeight, cannonWidth, cannonHeight));
            graphics.setColor(Color.white);
            graphics.fillRect(rectWidth, rectHeight, cannonWidth, cannonHeight);
            Color strColor;
            if (cannon.getStrength() != 100) {
                strColor = strengthColor(cannon.getStrength());
            } else {
                if (frameset % 3 == 0) {
                    strColor = Color.white;
                } else {
                    strColor = Color.red;
                }
            }
            graphics.setColor(strColor);
            graphics.fillRect(rectWidth, rectHeight, (float) (cannon.getStrength() * (cannonWidth / 100)), cannonHeight);
            //----------------- Strength display End

            //Score board Start-----------------
            if (score > 0) {
                graphics.setColor(Color.black);
                ft20.drawString(gameContainer.getWidth() - 500, 50, "Score:");
                ft50.drawString(gameContainer.getWidth() - 450, 50, " " + score);

            }
            //----------------- Score board End

            //Reset animation Start-----------------
            ft20.drawString(gameContainer.getWidth() - 250, 50, "hold R to restart");
            if (resetTimer != 0) {
                graphics.setColor(Color.red);
                graphics.fillRect(halfWidth - 205, halfHeight - 60, (float) (resetTimer * (6)), 60);
                ft50.drawString(halfWidth - 200, halfHeight - 50, "Restarting...");

            }
            //----------------- Reset animation End

            //Dev menu with the projectile attributes
            if (devMode) {
                graphics.setColor(Color.lightGray);
                graphics.fillRect(0, 0, 300, 340);
                graphics.setColor(Color.black);
                graphics.drawRect(0, 0, 300, 340);
                ft20.drawString(10,
                        10, "Debug mode", Color.black);
                ft20.drawString(10,
                        50, "X: " + currBall.ballX, Color.black);
                ft20.drawString(10,
                        100, "Y: " + currBall.ballY, Color.black);
                ft20.drawString(10,
                        150, "Angle: " + currBall.angleDeg, Color.black);
                ft20.drawString(10,
                        200, "Velocity: " + currBall.velocity, Color.black);
                ft20.drawString(10,
                        250, "Cos: " + Math.cos(currBall.angleRad), Color.black);
                ft20.drawString(10,
                        300, "Sin: " + Math.sin(currBall.angleRad), Color.black);
            }

        }
    }

    public Color strengthColor(double stronk) {

        if (stronk < 30) {
            return Color.green;
        } else if (stronk < 60) {
            return Color.yellow;

        } else if (stronk < 80) {
            return Color.orange;

        } else if (stronk < 99) {
            return Color.red;

        } else {
            return Color.white;
        }

    }

}