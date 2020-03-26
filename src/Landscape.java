import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class Landscape extends CannonGame {
    Image background;
    Image angleDef;

    @Override
    public void update(GameContainer gameContainer, int i) throws SlickException {


    }

    @Override
    public void render(GameContainer gameContainer, Graphics graphics) throws SlickException {

        background = ResourceManager.getImage("resources/landscape.jpg");
        angleDef = ResourceManager.getImage("resources/angle_def.png");

        background = background.getScaledCopy(gameContainer.getWidth(), gameContainer.getHeight());
        angleDef = angleDef.getScaledCopy(300, 300);


        graphics.drawImage(background, 0, 0);
        graphics.drawImage(angleDef, 15, gameContainer.getHeight() - 356);


    }
}
