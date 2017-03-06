package dk.sdu.mmmi.cbse.gamestates;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import dk.sdu.mmmi.cbse.entities.Bullet;
import dk.sdu.mmmi.cbse.entities.Player;
import dk.sdu.mmmi.cbse.entities.Enemy;
import dk.sdu.mmmi.cbse.managers.GameKeys;
import dk.sdu.mmmi.cbse.managers.GameStateManager;
import java.util.ArrayList;
import java.util.Random;

public class PlayState extends GameState {

    private ShapeRenderer sr;

    private Player player;
    private Enemy enemy;
    Random rand = new Random();
    Random rand2 = new Random();
    private ArrayList<Bullet> bullets;
    private ArrayList<Bullet> ebullets;

    public PlayState(GameStateManager gsm) {
        super(gsm);
    }

    public void init() {

        sr = new ShapeRenderer();
        bullets = new ArrayList<>();
        ebullets = new ArrayList<>();
        player = new Player(bullets);
        enemy = new Enemy(ebullets);
    }

    public void update(float dt) {

        handleInput();
        
        player.update(dt);
        enemy.update(dt);
        
        for(int i = 0; i < bullets.size(); i++){
            bullets.get(i).update(dt);
            if(bullets.get(i).shouldRemove()){
                bullets.remove(i);
                i--;
            }
        }
        for(int i = 0; i < ebullets.size(); i++){
            ebullets.get(i).update(dt);
            if(ebullets.get(i).shouldRemove()){
                ebullets.remove(i);
                i--;
            }
        }
    }

    public void draw() {
        player.draw(sr);
        enemy.draw(sr);
        
        for(int i = 0; i < bullets.size(); i++){
            bullets.get(i).draw(sr);
        }
        for(int i = 0; i < ebullets.size(); i++){
            ebullets.get(i).draw(sr);
        }
    }

    public void handleInput() {
        player.setLeft(GameKeys.isDown(GameKeys.LEFT));
        player.setRight(GameKeys.isDown(GameKeys.RIGHT));
        player.setUp(GameKeys.isDown(GameKeys.UP));
        if(GameKeys.isPressed(GameKeys.SPACE)){
            player.shoot();
        }
        
        enemy.setUp(true);
        enemy.setLeft(rand.nextBoolean());
        enemy.setRight(rand.nextBoolean());
        if(rand2.nextInt(10) == 1){
            enemy.shoot();
        }
    }

    public void dispose() {
    }

}
