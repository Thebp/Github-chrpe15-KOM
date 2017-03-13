package dk.sdu.mmmi.cbse.player;

import dk.sdu.mmmi.cbse.common.data.Entity;
import static dk.sdu.mmmi.cbse.common.data.EntityType.PLAYER;
import dk.sdu.mmmi.cbse.common.data.GameData;
import static dk.sdu.mmmi.cbse.common.data.GameKeys.LEFT;
import static dk.sdu.mmmi.cbse.common.data.GameKeys.RIGHT;
import static dk.sdu.mmmi.cbse.common.data.GameKeys.SPACE;
import static dk.sdu.mmmi.cbse.common.data.GameKeys.UP;
import dk.sdu.mmmi.cbse.common.data.World;
import dk.sdu.mmmi.cbse.common.events.Event;
import static dk.sdu.mmmi.cbse.common.events.EventType.PLAYER_SHOOT;
import dk.sdu.mmmi.cbse.common.services.IEntityProcessingService;
import dk.sdu.mmmi.cbse.common.services.IGamePluginService;
import static java.lang.Math.cos;
import static java.lang.Math.sin;
import static java.lang.Math.sqrt;
import org.openide.util.lookup.ServiceProvider;
import org.openide.util.lookup.ServiceProviders;

@ServiceProviders(value = {
    @ServiceProvider(service = IEntityProcessingService.class)
    , 
    @ServiceProvider(service = IGamePluginService.class)}
)
public class PlayerControlSystem implements IEntityProcessingService, IGamePluginService {

    @Override
    public void start(GameData gameData, World world) {
        // Add entities to the world

        Entity player = createPlayerShip(gameData);
        world.addEntity(player);
    }

    @Override
    public void process(GameData gameData, World world) {

        for (Entity player : world.getEntities(PLAYER)) {

            float x = player.getX();
            float y = player.getY();
            float dt = gameData.getDelta();
            float dx = player.getDx();
            float dy = player.getDy();
            float acceleration = player.getAcceleration();
            float deceleration = player.getDeacceleration();
            float maxSpeed = player.getMaxSpeed();
            float radians = player.getRadians();
            float rotationSpeed = player.getRotationSpeed();

            // turning
            if (gameData.getKeys().isDown(LEFT)) {
                radians += rotationSpeed * dt;
            }

            if (gameData.getKeys().isDown(RIGHT)) {
                radians -= rotationSpeed * dt;
            }

            //Shoot
            if (gameData.getKeys().isDown(SPACE)) {
                gameData.addEvent(new Event(PLAYER_SHOOT, player.getID()));
            }

            // accelerating            
            if (gameData.getKeys().isDown(UP)) {
                dx += cos(radians) * acceleration * dt;
                dy += sin(radians) * acceleration * dt;
            }

            // deceleration
            float vec = (float) sqrt(dx * dx + dy * dy);
            if (vec > 0) {
                dx -= (dx / vec) * deceleration * dt;
                dy -= (dy / vec) * deceleration * dt;
            }
            if (vec > maxSpeed) {
                dx = (dx / vec) * maxSpeed;
                dy = (dy / vec) * maxSpeed;
            }

            // set position
            x += dx * dt;
            if (x > gameData.getDisplayWidth()) {
                x = 0;
            } else if (x < 0) {
                x = gameData.getDisplayWidth();
            }

            y += dy * dt;
            if (y > gameData.getDisplayHeight()) {
                y = 0;
            } else if (y < 0) {
                y = gameData.getDisplayHeight();
            }

            // Update entity
            player.setPosition(x, y);
            player.setDx(dx);
            player.setDy(dy);
            player.setRadians(radians);

            updateShape(player);
        }
    }

    private void updateShape(Entity entity) {
        float[] shapex = entity.getShapeX();
        float[] shapey = entity.getShapeY();
        float x = entity.getX();
        float y = entity.getY();
        float radians = entity.getRadians();

        shapex[0] = (float) (x + Math.cos(radians) * 8);
        shapey[0] = (float) (y + Math.sin(radians) * 8);

        shapex[1] = (float) (x + Math.cos(radians - 4 * 3.1415f / 5) * 8);
        shapey[1] = (float) (y + Math.sin(radians - 4 * 3.1145f / 5) * 8);

        shapex[2] = (float) (x + Math.cos(radians + 3.1415f) * 5);
        shapey[2] = (float) (y + Math.sin(radians + 3.1415f) * 5);

        shapex[3] = (float) (x + Math.cos(radians + 4 * 3.1415f / 5) * 8);
        shapey[3] = (float) (y + Math.sin(radians + 4 * 3.1415f / 5) * 8);

        entity.setShapeX(shapex);
        entity.setShapeY(shapey);
    }

    private Entity createPlayerShip(GameData gameData) {
        Entity playerShip = new Entity();
        playerShip.setType(PLAYER);

        playerShip.setPosition(gameData.getDisplayWidth() / 2, gameData.getDisplayHeight() / 2);

        playerShip.setMaxSpeed(300);
        playerShip.setAcceleration(200);
        playerShip.setDeacceleration(10);

        playerShip.setShapeX(new float[4]);
        playerShip.setShapeY(new float[4]);

        playerShip.setRadians(3.1415f / 2);
        playerShip.setRotationSpeed(5);

        playerShip.setLife(1);

        playerShip.setRadius(4);

        return playerShip;
    }

    @Override
    public void stop(GameData gameData, World world) {
        // Remove entities
        for (Entity entity : world.getEntities(PLAYER)) {
            world.removeEntity(entity);
        }
    }

}
