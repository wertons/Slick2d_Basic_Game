import org.newdawn.slick.*;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Shape;

class CannonGame extends BasicGame {
    Cannon cannon;
    Ball ball;
    Target target;
    Landscape landscape;
    static int screenWidth;
    static int screenHeight;

    public static void main(String[] args) throws SlickException {
        screenWidth = 800;
        screenHeight = 600;
        CannonGame cg = new CannonGame();
        AppGameContainer app = new AppGameContainer(cg, screenWidth, screenHeight, false);
        app.start();

    }

    CannonGame() {
        super("Game");
    }


    @Override
    public void init(GameContainer gameContainer) throws SlickException {

    }

    @Override
    public void update(GameContainer gameContainer, int i) throws SlickException {
        Input input = gameContainer.getInput();
        if (input.isKeyDown(Input.KEY_UP)){
            cannon.updateRotation(1);
        } else if (input.isKeyDown(Input.KEY_DOWN)){
            cannon.updateRotation(-1);
        }

    }

    @Override
    public void render(GameContainer gameContainer, Graphics graphics) throws SlickException {
   
    }
}

class Landscape extends CannonGame {
    @Override
    public void render(GameContainer gameContainer, Graphics graphics) throws SlickException {
    }


    @Override
    public void update(GameContainer gameContainer, int i) throws SlickException {


    }
}

class Cannon extends CannonGame {
    double rotation;
    double strength;

    @Override
    public void update(GameContainer gameContainer, int i) throws SlickException {

    }

    @Override
    public void render(GameContainer gameContainer, Graphics graphics) throws SlickException {

    }

    void fire() {

    }

    void updateRotation(double deltaRotation) {
        this.rotation += deltaRotation;

    }

    void updateStrength(double deltaStrength) {
        this.strength += deltaStrength;
    }

    public double getRotation() {
        return rotation;
    }

    public double getStrength() {
        return strength;
    }
}

class Target extends CannonGame {
    Shape shape;

    @Override
    public void update(GameContainer gameContainer, int i) throws SlickException {

    }

    @Override
    public void render(GameContainer gameContainer, Graphics graphics) throws SlickException {

    }

    boolean hit() {
        return false;
    }

    void reset() {
    }

    public Shape getShape() {
        return shape;
    }
}

class Ball extends CannonGame {
    Target target;
    Shape shape;

    @Override
    public void render(GameContainer gameContainer, Graphics graphics) throws SlickException {

    }

    @Override
    public void update(GameContainer gameContainer, int i) throws SlickException {

    }

    public void setTarget(Target target) {
        this.target = target;
    }

    boolean hasFallen() {
        return true;
    }
}
