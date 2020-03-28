import org.lwjgl.input.Keyboard;
import org.newdawn.slick.*;

public class Cannon extends CannonGame {

    float rotation = 0;
    int strength = 0;
    int x;
    int y;
    Image cannon;
    Image base_cannon;



    @Override
    public void update(GameContainer gameContainer, int i) throws SlickException {

        Input input = gameContainer.getInput();

        if (input.isKeyDown(Input.KEY_DOWN)) {
            if (cannon.getRotation() * -1 > 5) {
                cannon.rotate(2);
                rotation = cannon.getRotation() * -1;
            }
        }
        if (input.isKeyDown(Input.KEY_UP)) {
            if (cannon.getRotation() * -1 < 90) {
                cannon.rotate(-2);
                rotation = cannon.getRotation() * -1;
            }
        }

        if (input.isKeyDown(Input.KEY_RIGHT)){
            if (strength < 100){
                strength++;
            }
        }
        if (input.isKeyDown(Input.KEY_LEFT)){
            if (strength > 0) {
                strength--;
            }
        }


    }

    @Override
    public void render(GameContainer gameContainer, Graphics graphics) throws SlickException {

        cannon = ResourceManager.getImage("resources/cannon.png");

        x= 25;
        y = gameContainer.getHeight() - 150;

        base_cannon = ResourceManager.getImage("resources/cannon_base.png");
        graphics.drawImage(cannon, x, y);
        graphics.drawImage(base_cannon, x, y+30);
        cannon.setCenterOfRotation(35, 35);

    }

    Ball fire(){
         return new Ball(rotation,strength,(int)((Math.cos(Math.toRadians(rotation)))*200) +x,y- (int)((Math.sin(Math.toRadians(rotation)))*200));

    }

    void updateRotation(double deltaRotation){}

    void updateStrength(double deltaStrength){}

    double getRotation(){return rotation;}

    double getStrength(){return strength;}
}
