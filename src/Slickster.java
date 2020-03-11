import org.newdawn.slick.*;

public class Slickster extends BasicGame {
    public static void main(String[] args) throws SlickException {
        Slickster slick = new Slickster();
        AppGameContainer game = new AppGameContainer(slick,800,800,false);
        game.start();
    }
    Slickster(){
        super("Game");
    }

    @Override
    public void init(GameContainer gameContainer) throws SlickException {

    }

    @Override
    public void update(GameContainer gameContainer, int i) throws SlickException {

    }

    @Override
    public void render(GameContainer gameContainer, Graphics graphics) throws SlickException {

    }
}
