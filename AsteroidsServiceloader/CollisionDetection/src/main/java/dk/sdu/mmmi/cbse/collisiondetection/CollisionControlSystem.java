/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dk.sdu.mmmi.cbse.collisiondetection;

import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.World;
import dk.sdu.mmmi.cbse.common.services.IEntityProcessingService;
import dk.sdu.mmmi.cbse.common.services.IGamePluginService;

/**
 *
 * @author Christian
 */
public class CollisionControlSystem implements IEntityProcessingService, IGamePluginService {
    
    @Override
    public void process(GameData gameData, World world) {
        for(Entity entity1 : world.getEntities()){
            for(Entity entity2 : world.getEntities()){
                if(!(entity1.equals(entity2))){
                    float distance;
                    distance = (float) Math.sqrt(Math.pow(entity1.getX() - entity2.getX(), 2) + Math.pow(entity1.getY() - entity2.getY(), 2));
                    if(distance < entity1.getRadius() + entity2.getRadius()){
                        entity1.setIsHit(true);
                        entity2.setIsHit(true);
                    }
                }
            }
        }
    }
    
    @Override
    public void start(GameData gameData, World world) {
        
    }

    @Override
    public void stop(GameData gameData, World world) {
        
    }
    
}
