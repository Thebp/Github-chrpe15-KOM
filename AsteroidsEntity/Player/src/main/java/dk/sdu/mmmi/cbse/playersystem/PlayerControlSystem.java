package dk.sdu.mmmi.cbse.playersystem;

import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.EntityType;
import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.GameKeys;
import dk.sdu.mmmi.cbse.common.data.World;
import dk.sdu.mmmi.cbse.common.events.Event;
import static dk.sdu.mmmi.cbse.common.events.EventType.PLAYER_SHOOT;
import dk.sdu.mmmi.cbse.common.services.IEntityProcessingService;
import dk.sdu.mmmi.cbse.common.services.IGamePluginService;

/**
 *
 * @author jcs
 */
public class PlayerControlSystem implements IEntityProcessingService, IGamePluginService {

    @Override
    public void process(GameData gameData, World world) {
        for (Entity player : world.getEntities(EntityType.PLAYER)) {
            float x = player.getX();
            float y = player.getY();
            float dx = player.getDx();
            float dy = player.getDy();
            float radians = player.getRadians();
            int rotationSpeed = player.getRotationSpeed();
            float dt = gameData.getDelta();
            float acceleration = player.getAcceleration();
            float deceleration = player.getDeacceleration();
            float maxSpeed = player.getMaxSpeed();
            float[] shapeX = player.getShapeX();
            float[] shapeY = player.getShapeY();

            if (gameData.getKeys().isDown(GameKeys.LEFT)) {
                radians += rotationSpeed * dt;
            } else if (gameData.getKeys().isDown(GameKeys.RIGHT)) {
                radians -= rotationSpeed * dt;
            }
            
            //shooting
            if(gameData.getKeys().isDown(GameKeys.SPACE)){
                gameData.addEvent(new Event(PLAYER_SHOOT, player.getID()));
            }
            
            
            
            // accelerating
            if (gameData.getKeys().isDown(GameKeys.UP)) {
                dx += Math.cos(radians) * acceleration * dt;
                dy += Math.sin(radians) * acceleration * dt;
            }

            // deceleration
            float vec = (float) Math.sqrt(dx * dx + dy * dy);
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
            y += dy * dt;

            shapeX[0] = (float) (x + Math.cos(radians) * 8);
            shapeY[0] = (float) (y + Math.sin(radians) * 8);

            shapeX[1] = (float) (x + Math.cos(radians - 4 * 3.1415f / 5) * 8);
            shapeY[1] = (float) (y + Math.sin(radians - 4 * 3.1145f / 5) * 8);

            shapeX[2] = (float) (x + Math.cos(radians + 3.1415f) * 5);
            shapeY[2] = (float) (y + Math.sin(radians + 3.1415f) * 5);

            shapeX[3] = (float) (x + Math.cos(radians + 4 * 3.1415f / 5) * 8);
            shapeY[3] = (float) (y + Math.sin(radians + 4 * 3.1415f / 5) * 8);

            player.setX(x);
            player.setY(y);
            player.setDx(dx);
            player.setDy(dy);
            player.setRadians(radians);
            player.setRotationSpeed(rotationSpeed);
            player.setAcceleration(acceleration);
            player.setDeacceleration(deceleration);
            player.setMaxSpeed(maxSpeed);
            player.setShapeX(shapeX);
            player.setShapeY(shapeY);
        }
    }

    @Override
    public void start(GameData gameData, World world) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void stop(GameData gameData, World world) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
