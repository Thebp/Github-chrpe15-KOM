/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dk.sdu.mmmi.cbse.bullet;

import dk.sdu.mmmi.cbse.common.data.Entity;
import static dk.sdu.mmmi.cbse.common.data.EntityType.BULLET;
import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.World;
import dk.sdu.mmmi.cbse.common.services.IGamePluginService;

/**
 *
 * @author Christian
 */
public class BulletPlugin implements IGamePluginService{
    private Entity bullet;
    @Override
    public void start(GameData gameData, World world) {
        // Add entities to the world
        bullet = createBullet(gameData);
        world.addEntity(bullet);
    }

    @Override
    public void stop(GameData gameData, World world) {
         // Remove entities
        world.removeEntity(bullet);
    }

    private Entity createBullet(GameData gameData) {
       Entity bullets = new Entity();
        bullets.setType(BULLET);

        bullets.setPosition(gameData.getDisplayWidth() / 2, gameData.getDisplayHeight() / 2);

        bullets.setMaxSpeed(300);
        bullets.setAcceleration(200);
        bullets.setDeacceleration(10);

        bullets.setRadians(3.1415f / 2);
        //bullets.setRotationSpeed(3);

        return bullets;
    }
    
}
