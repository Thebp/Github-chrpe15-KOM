/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dk.sdu.mmmi.cbse.bullet;

import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.World;
import dk.sdu.mmmi.cbse.common.services.IGamePluginService;
import dk.sdu.mmmi.cbse.commonbullet.Bullet;
import org.openide.util.lookup.ServiceProvider;

/**
 *
 * @author Christian
 */
@ServiceProvider(service = IGamePluginService.class)
public class BulletPlugin implements IGamePluginService{
   @Override
    public void start(GameData gameData, World world) {
        System.out.println("Bullet plugin started");
    }

    @Override
    public void stop(GameData gameData, World world) {
        for(Entity bullet : world.getEntities(Bullet.class)) {
            world.removeEntity(bullet);
        }
    }
    
}
