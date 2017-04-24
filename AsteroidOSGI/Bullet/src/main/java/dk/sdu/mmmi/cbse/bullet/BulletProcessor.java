/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dk.sdu.mmmi.cbse.bullet;

import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.World;
import dk.sdu.mmmi.cbse.common.services.IEntityProcessingService;
import dk.sdu.mmmi.cbse.commonbullet.Bullet;
import static java.lang.Math.cos;
import static java.lang.Math.sin;
import org.openide.util.lookup.ServiceProvider;

/**
 *
 * @author Christian
 */
@ServiceProvider(service = IEntityProcessingService.class)

public class BulletProcessor implements IEntityProcessingService {

    @Override
    public void process(GameData gameData, World world) {
        for (Entity bullet : world.getEntities(Bullet.class)) {
            if (bullet.getIsHit() || bullet.getExpiration() <= 0) {
                world.removeEntity(bullet);
            } else {
                moveBullet(gameData, bullet);
                setShape(bullet);
            }
        }
    }
    private void moveBullet(GameData gameData, Entity bullet) {
        float x = bullet.getX();
        float y = bullet.getY();
        float dt = gameData.getDelta();
        float radians = bullet.getRadians();
        float speed = bullet.getMaxSpeed();

        float dx = (float) cos(radians) * speed;
        float dy = (float) sin(radians) * speed;

        bullet.setDx(dx);
        bullet.setDy(dy);
        bullet.setPosition(x + dx * dt, y + dy * dt);
    }

    private void setShape(Entity bullet) {
        float[] shapex = bullet.getShapeX();
        float[] shapey = bullet.getShapeY();
        float x = bullet.getX();
        float y = bullet.getY();
        float radians = bullet.getRadians();

        shapex[0] = (float) (x - 4);
        shapey[0] = (float) (y);

        shapex[1] = (float) (x);
        shapey[1] = (float) (y + 4);

        shapex[2] = (float) (x + 4);
        shapey[2] = (float) (y);

        shapex[3] = (float) (x);
        shapey[3] = (float) (y - 4);

        bullet.setShapeX(shapex);
        bullet.setShapeY(shapey);
    }

    
}