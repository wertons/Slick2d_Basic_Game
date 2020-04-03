import org.newdawn.slick.*;

public class Cannon extends CannonGame {

    float cannonAngle = 0;
    int cannonStrength = 0;
    int cannonX;
    int cannonY;
    Image cannon;
    Image cannonBase;



    @Override
    public void update(GameContainer gameContainer, int i) throws SlickException {
        Input input = gameContainer.getInput();
        if (input.isKeyDown(Input.KEY_DOWN)) {
            if (cannon.getRotation() * -1 > 5) {
                cannon.rotate(2);
                cannonAngle = cannon.getRotation() * -1;
            }
        }
        if (input.isKeyDown(Input.KEY_UP)) {
            if (cannon.getRotation() * -1 < 90) {
                cannon.rotate(-2);
                cannonAngle = cannon.getRotation() * -1;
            }
        }

        if (input.isKeyDown(Input.KEY_RIGHT)){
            if (cannonStrength < 100){
                cannonStrength++;
            }
        }
        if (input.isKeyDown(Input.KEY_LEFT)){
            if (cannonStrength > 0) {
                cannonStrength--;
            }
        }
    }

    @Override
    public void render(GameContainer gameContainer, Graphics graphics) throws SlickException {
        cannon = ResourceManager.getImage("resources/cannon.png");
        cannonX = 25;
        cannonY = gameContainer.getHeight() - 150;
        cannonBase = ResourceManager.getImage("resources/cannon_base.png");
        graphics.drawImage(cannon, cannonX, cannonY);
        graphics.drawImage(cannonBase, cannonX, cannonY +30);
        cannon.setCenterOfRotation(35, 35);

    }

    Ball fire(){
         return new Ball(cannonAngle, cannonStrength,(int)((Math.cos(Math.toRadians(cannonAngle)))*200) + cannonX, cannonY - (int)((Math.sin(Math.toRadians(cannonAngle)))*200));

    }
    double getCannonStrength(){return cannonStrength;}
}
