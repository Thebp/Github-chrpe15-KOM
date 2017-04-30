package dk.sdu.mmmi.cbse.player.playersystem;

import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.GameKeys;
import dk.sdu.mmmi.cbse.common.data.World;
import dk.sdu.mmmi.cbse.common.services.IEntityProcessingService;
import dk.sdu.mmmi.cbse.commonbullet.BulletSPI;
import org.openide.util.Lookup;
import org.openide.util.lookup.ServiceProvider;

/**
 *
 * @author jcs
 */
@ServiceProvider(service = IEntityProcessingService.class)

public class PlayerControlSystem implements IEntityProcessingService {

    @Override
    public void process(GameData gameData, World world) {
        for (Entity player : world.getEntities(Player.class)) {
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

            BulletSPI bulletSPI = Lookup.getDefault().lookup(BulletSPI.class);
            //shooting
            if (gameData.getKeys().isPressed(GameKeys.SPACE) && bulletSPI != null) {
                Entity bullet = bulletSPI.createBullet(player);
                world.addEntity(bullet);
            }

            if (player.getIsHit() == true) {
                world.removeEntity(player);
                player.setIsHit(false);
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

            shapeX[0] = (float) (x + Math.cos(radians) * player.getRadius());
            shapeY[0] = (float) (y + Math.sin(radians) * player.getRadius());

            shapeX[1] = (float) (x + Math.cos(radians - 4 * 3.1415f / 5) * player.getRadius());
            shapeY[1] = (float) (y + Math.sin(radians - 4 * 3.1145f / 5) * player.getRadius());

            shapeX[2] = (float) (x + Math.cos(radians + 3.1415f) * player.getRadius() * 0.625);
            shapeY[2] = (float) (y + Math.sin(radians + 3.1415f) * player.getRadius() * 0.625);

            shapeX[3] = (float) (x + Math.cos(radians + 4 * 3.1415f / 5) * player.getRadius());
            shapeY[3] = (float) (y + Math.sin(radians + 4 * 3.1415f / 5) * player.getRadius());

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

  

}
