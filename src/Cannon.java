import org.newdawn.slick.*;

public class Cannon extends CannonGame {

    float angle = 0;
    float strength = 0;
    int x;
    int y;
    Image cannon;
    Image cannonBase;



    @Override
    public void update(GameContainer gameContainer, int i) throws SlickException {
        Input input = gameContainer.getInput();
        if (input.isKeyDown(Input.KEY_DOWN)) {
            if (cannon.getRotation() * -1 > 0) {
                cannon.rotate(2);
                angle = cannon.getRotation() * -1;
            }
        }
        if (input.isKeyDown(Input.KEY_UP)) {
            if (cannon.getRotation() * -1 < 90) {
                cannon.rotate(-2);
                angle = cannon.getRotation() * -1;
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
        x = 25;
        y = gameContainer.getHeight() - 150;
        cannonBase = ResourceManager.getImage("resources/cannon_base.png");
        graphics.drawImage(cannon, x, y);
        graphics.drawImage(cannonBase, x, y +30);
        cannon.setCenterOfRotation(35, 35);

    }

    Ball fire(){
         return new Ball(angle, strength,(int)((Math.cos(Math.toRadians(angle)))*200) + x, y - (int)((Math.sin(Math.toRadians(angle)))*200));

    }
    double getStrength(){return strength;}
}
