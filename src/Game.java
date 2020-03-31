import org.newdawn.slick.*;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Rectangle;

import java.io.IOException;

public class Game {
    public static void main(String[] args) throws SlickException {
        initialize();

    }

    public static void initialize() throws SlickException {
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
    Image guardian1;
    Image guardian2;
    Image guardian3;
    int frameset = 0;
    boolean activeBall = false;
    boolean devMode = false;
    boolean helpScreen = false;
    int width;
    int height;
    int halfWidth;
    int halfHeight;
    int score = 0;
    int ballAmount = 10;
    int currentBalls;
    Commit com = new Commit();
    int version;
    String[] rules;
    int menuWidth;
    int menuHeight = 75;
    int menuOption = 2;
    int difficulty = 1;

    public CannonGame() {
        super("Game");
    }

    @Override
    public void init(GameContainer gameContainer) throws SlickException {


        gameContainer.setTargetFrameRate(59);
        bg = new Landscape();
        cannon = new Cannon();
        target = new Target();
        target.reset();
        currBall = new Ball();
        ft50 = ResourceManager.getFont("resources/WHITRABT.ttf", 50);
        ft20 = ResourceManager.getFont("resources/WHITRABT.ttf", 20);
        bFt = ResourceManager.getFont("resources/WHITRABT.ttf", 60);
        initBg = ResourceManager.getImage("resources/initBg.jpg");
        width = gameContainer.getWidth();
        height = gameContainer.getHeight();
        halfWidth = width / 2;
        halfHeight = height / 2;
        guardian1 = ResourceManager.getImage("resources/guardian_2.png");
        guardian2 = ResourceManager.getImage("resources/guardian_3.png");
        guardian3 = ResourceManager.getImage("resources/guardian_1.png");
        try {
            version = com.getCommits();
            rules = com.getControls();
        } catch (IOException e) {
            e.printStackTrace();
        }

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

        if (startup) {
            //Game initialization Start-----------------

            if (input.isKeyDown(Input.KEY_ENTER)) {
                switch (menuOption) {
                    case 2:
                        startup = false;
                        currentBalls = ballAmount;
                        break;
                    case 1:
                        break;
                    case 0:
                        helpScreen = true;

                        break;
                }

            }
            //-----------------Game initialization End
            //Startup menu controls Start-----------------
            if (input.isKeyPressed(Input.KEY_DOWN) && menuOption > 0) {
                menuOption--;
            }
            if (input.isKeyPressed(Input.KEY_UP) && menuOption < 2) {
                menuOption++;
            }
            if (menuOption == 1){
                if (input.isKeyPressed(Input.KEY_LEFT) && difficulty > 1){
                    difficulty --;
                }
                if (input.isKeyPressed(Input.KEY_RIGHT) && difficulty < 4){
                    difficulty ++;
                }
            }
            //-----------------Startup menu controls End
            //-----------------Help Screen Start
            if (helpScreen) {
                if (input.isKeyDown(Input.KEY_ESCAPE)) {
                    helpScreen = false;
                }
            }
            //Help Screen End-----------------


        } else {
            //Projectile Update Start-----------------
            if (!startup) {
                cannon.update(gameContainer, i);
                if (!activeBall) {
                    if (input.isKeyDown(Input.KEY_SPACE) && currentBalls > 0) {
                        currBall = cannon.fire();
                        activeBall = true;

                    }
                }
            }

            if (activeBall) {
                currBall.update(gameContainer, i);
                if (currBall.hasFallen()) {
                    currentBalls--;
                    activeBall = false;
                }
                try {
                    if (target.hit(currBall)) {
                        score++;
                        activeBall = false;
                        target.reset();
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

            //Reset Input Start--------------------
            if (input.isKeyDown(Input.KEY_R) && resetTimeout <= 0) {
                resetTimer++;
                if (resetTimer > 60) {
                    //On reset actions
                    resetVariables();
                }
            } else {
                resetTimer = 0;
            }
            if (resetTimeout > 0) {
                resetTimeout--;
            }
            //---------------------Reset Input End
        }


    }

    public void resetVariables() {
        currBall = new Ball();
        activeBall = false;
        resetTimeout = 30;
        resetTimer = 0;
        reset = true;
        currentBalls = ballAmount;
        score = 0;
        startup = true;
    }

    @Override
    public void render(GameContainer gameContainer, Graphics graphics) throws SlickException {

        graphics.setColor(Color.black);

        if (startup) {
            //Startup menu Start-----------------
            initBg = initBg.getScaledCopy(width, height);
            graphics.drawImage(initBg, 0, 0);
            graphics.setColor(Color.black);


            ft50.drawString(width - 800, height - 50, "Version: " + version);
            ft50.drawString(halfWidth, halfHeight - (menuHeight * 2), "Start Game", Color.black);
            ft50.drawString(halfWidth, halfHeight - menuHeight, "Mode: "+target.setDifficulty(difficulty), Color.black);
            ft50.drawString(halfWidth, halfHeight, "Help", Color.black);

            //Startup menu Selector Start-----------------
            graphics.setColor(Color.black);
            graphics.fillRect(halfWidth - 4, halfHeight - (menuHeight * menuOption) - 4, 400, 54);
            if (frameset > 60) {
                graphics.setColor(Color.white);
                graphics.fillRect(halfWidth, halfHeight - (menuHeight * menuOption), 14, 46);
            }


            switch (menuOption) {
                case 2:
                    ft50.drawString(halfWidth + 20, halfHeight - (menuHeight * 2) + 4, "Start Game", Color.white);
                    break;
                case 1:
                    ft50.drawString(halfWidth + 20, halfHeight - (menuHeight) + 4, "Mode: "+target.setDifficulty(difficulty), Color.white);
                    break;
                case 0:
                    ft50.drawString(halfWidth + 20, halfHeight + 4, "Help", Color.white);
                    break;
            }


            //-----------------Startup menu Selector End


            //-----------------Startup menu End
            //Help menu Display Start-----------------
            if (helpScreen) {
                graphics.setColor(Color.white);
                graphics.fillRect(200, 200, 800, 500);
                graphics.setColor(Color.black);
                graphics.setLineWidth(3);
                graphics.drawRect(200, 200, 800, 500);
                graphics.resetLineWidth();
                ft20.drawString(210, 210, "[esc]", Color.red);
                String currentRule = "Rules: ";
                ft50.drawString(230, 250, currentRule, Color.black);
                for (int i = 0; i < rules.length; i++) {
                    currentRule = rules[i];
                    currentRule = currentRule.replace("_", " ");
                    ft20.drawString(halfWidth - 150, halfHeight - 200 + (i * 60), currentRule, Color.black);
                }
            }
            //-----------------Help menu Display End


        } else {

            if (currentBalls > 0) {
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

                //Ball counter Start-----------------
                graphics.setColor(Color.white);
                graphics.fillRect(width - 50, halfHeight - (ballAmount * 25), 26, (ballAmount * 25) + (ballAmount * 2) + 2);
                graphics.setColor(Color.black);
                graphics.drawRect(width - 50, halfHeight - (ballAmount * 25), 26, (ballAmount * 25) + (ballAmount * 2) + 2);

                double tmp = (currentBalls * 100);
                tmp = tmp / ballAmount;
                tmp = 100 - tmp;
                graphics.setColor(strengthColor(tmp));
                for (int i = 0; i < currentBalls; i++) {
                    graphics.fillRect(width - 47, halfHeight - ((i * 25) + (i * 2)), 20, 20);
                }
                //-----------------Ball counter End


                //Strength display Start-----------------
                graphics.setColor(Color.black);
                float rectWidth = 30;
                float rectHeight = height - 50;
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
                    ft20.drawString(width - 500, 50, "Score: " + score);

                }
                //----------------- Score board End


                //Dev menu Start-----------------
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

                    graphics.setColor(Color.green);
                    for (int i = 0; i < 200; i++) {

                        int tmpiniX = (int) ((Math.cos(Math.toRadians(cannon.rotation))) * 200) + cannon.x;
                        int tmpiniY = cannon.y - (int) ((Math.sin(Math.toRadians(cannon.rotation))) * 200);
                        int tmpX = (int) Math.floor((tmpiniX) + ((Math.cos(Math.toRadians(cannon.rotation))) * i) * ((cannon.strength + 100) / 9));
                        int tmpY = (int) Math.floor((tmpiniY) - ((((Math.sin(Math.toRadians(cannon.rotation))) * i) - (((i * (i / 10)) * 0.5) / 5))) * ((cannon.strength + 100) / 9));
                        graphics.fillRect(tmpX, tmpY, 15, 15);
                    }
                }
                //----------------- Dev menu End
                graphics.setColor(Color.black);
                graphics.drawArc(300, 300, 500, -300, 0, 0);


            } else {
                //Score screen Start-----------------
                initBg = initBg.getScaledCopy(width, height);
                graphics.drawImage(initBg, 0, 0);
                graphics.setColor(Color.gray);
                graphics.fillRect(halfWidth - 300, 80, 600, height - 160);
                graphics.setColor(Color.black);
                graphics.setLineWidth(3);
                graphics.drawRect(halfWidth - 300, 80, 600, height - 160);
                graphics.setLineWidth(1);
                ft50.drawString(halfWidth - 100, halfHeight - 250, "Score:" + score);
                if (score > 0) {
                    ft50.drawString(halfWidth - 115, halfHeight - 350, "Good job!");
                    Image tmp2 = guardian2.getScaledCopy(300, 500);
                    Image tmp1 = guardian1.getScaledCopy(200, 430);
                    Image tmp3 = guardian3.getScaledCopy(200, 430);

                    if ((frameset > 0 && frameset < 20) || (frameset > 40 && frameset < 60) || (frameset > 80 && frameset < 100)) {
                        tmp1.draw(halfWidth + 100, (halfHeight - 150));
                    } else {
                        tmp3.draw(halfWidth + 100, (halfHeight - 150));

                    }
                    if (frameset < 60) {
                        tmp2.draw(halfWidth - 350, (halfHeight - 200) + frameset);

                    } else {
                        tmp2.draw(halfWidth - 350, (halfHeight - 200) + 60 - (frameset - 60));
                    }

                } else {
                    ft50.drawString(halfWidth - 300, halfHeight, "");
                }
                //-----------------Score screen End
            }
            //Reset animation Start-----------------
            ft20.drawString(width - 250, 50, "hold R to restart");
            if (resetTimer != 0) {
                graphics.setColor(Color.red);
                graphics.fillRect(halfWidth - 205, halfHeight - 60, (float) (resetTimer * (6)), 60);
                graphics.setColor(Color.black);
                graphics.setLineWidth(3);
                graphics.drawRect(halfWidth - 205, halfHeight - 60, (float) (resetTimer * (6)), 60);
                graphics.setLineWidth(1);
                ft50.drawString(halfWidth - 200, halfHeight - 50, "Restarting...");

            }
            //----------------- Reset animation End
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
    int getDifficulty(){
        return  difficulty;
    }

}