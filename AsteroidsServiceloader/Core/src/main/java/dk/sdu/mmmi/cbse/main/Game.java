package dk.sdu.mmmi.cbse.main;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import dk.sdu.mmmi.cbse.asteroid.AsteroidControlSystem;
import dk.sdu.mmmi.cbse.asteroid.AsteroidPlugin;
import dk.sdu.mmmi.cbse.bullet.BulletControlSystem;
import dk.sdu.mmmi.cbse.collisiondetection.CollisionControlSystem;
import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.World;
import dk.sdu.mmmi.cbse.common.services.IEntityProcessingService;
import dk.sdu.mmmi.cbse.common.services.IGamePluginService;
import dk.sdu.mmmi.cbse.enemy.EnemyControlSystem;
import dk.sdu.mmmi.cbse.enemy.EnemyPlugin;
import dk.sdu.mmmi.cbse.managers.GameInputProcessor;
import dk.sdu.mmmi.cbse.playersystem.EntityPlugin;
import dk.sdu.mmmi.cbse.playersystem.PlayerControlSystem;
import java.util.ArrayList;
import java.util.List;
import dk.sdu.mmmi.cbse.common.util.SPILocator;

public class Game implements ApplicationListener {

    private static OrthographicCamera cam;
    private ShapeRenderer sr;

    private final GameData gameData = new GameData();
    private List<IEntityProcessingService> entityProcessors = new ArrayList<>();
    private World world = new World();
//    private IGamePluginService playerPlugin;
//    private IEntityProcessingService playerProcessor;
//    private IGamePluginService enemyPlugin;
//    private IEntityProcessingService enemyProcessor;
//    private IEntityProcessingService asteroidProcessor;
//    private IGamePluginService asteroidPlugin;
//    private IEntityProcessingService bulletProcessor;
//    private IEntityProcessingService collisionProcessor;
//    private SPILocator serviceLoader;
    
    @Override
    public void create() {
        
        
        gameData.setDisplayWidth(Gdx.graphics.getWidth());
        gameData.setDisplayHeight(Gdx.graphics.getHeight());

        cam = new OrthographicCamera(gameData.getDisplayWidth(), gameData.getDisplayHeight());
        cam.translate(gameData.getDisplayWidth() / 2, gameData.getDisplayHeight() / 2);
        cam.update();

        sr = new ShapeRenderer();

        Gdx.input.setInputProcessor(
                new GameInputProcessor(gameData)
        );
        
        for(IGamePluginService e : SPILocator.locateAll(IGamePluginService.class)){
            e.start(gameData, world);
        }
        
        //entityProcessors = SPILocator.locateAll(IEntityProcessingService.class);

       
        
    }

    @Override
    public void render() {

        // clear screen to black
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        gameData.setDelta(Gdx.graphics.getDeltaTime());

        update();

        draw();
        
        wrap();

        gameData.getKeys().update();
    }

    private void update() {
        // Update
        for(IEntityProcessingService e : SPILocator.locateAll(IEntityProcessingService.class)){
            e.process(gameData, world);
        }
//        playerProcessor.process(gameData, world);
//        enemyProcessor.process(gameData, world);
//        asteroidProcessor.process(gameData, world);
//        bulletProcessor.process(gameData, world);
//        collisionProcessor.process(gameData, world);
    }
    

    private void wrap(){
        for(Entity entity: world.getEntities()){
            if(entity.getX() < 0) entity.setX(gameData.getDisplayWidth());
		if(entity.getX() > gameData.getDisplayWidth()) entity.setX(0);
		if(entity.getY() < 0) entity.setY(gameData.getDisplayHeight());
		if(entity.getY() > gameData.getDisplayHeight()) entity.setY(0); 
        }
        
    }
    
    private void draw() {
        for (Entity entity : world.getEntities()) {
            float[] shapex = entity.getShapeX();
            float[] shapey = entity.getShapeY();
            if (shapex != null && shapey != null) {

                sr.setColor(1, 1, 1, 1);

                sr.begin(ShapeRenderer.ShapeType.Line);

                for (int i = 0, j = shapex.length - 1;
                        i < shapex.length;
                        j = i++) {

                    sr.line(shapex[i], shapey[i], shapex[j], shapey[j]);
                }
                sr.end();
            }
        }
    }

    @Override
    public void resize(int width, int height) {
    }

    @Override
    public void pause() {
    }

    @Override
    public void resume() {
    }

    @Override
    public void dispose() {
    }
}
