/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dk.sdu.mmmi.cbse.osgienemy;

import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.World;
import dk.sdu.mmmi.cbse.common.services.IGamePluginService;

/**
 *
 * @author Christian
 */
public class EnemyPlugin implements IGamePluginService {
    private Entity enemy;
    public EnemyPlugin(){
        
    }
    @Override
    public void start(GameData gameData, World world) {
        enemy = createEnemyShip(gameData);
        world.addEntity(enemy);
    }

    @Override
    public void stop(GameData gameData, World world) {
        world.removeEntity(enemy);
    }
    private Entity createEnemyShip(GameData gameData){
        Enemy enemyShip = new Enemy();
                
        enemyShip.setPosition(gameData.getDisplayWidth()/4, gameData.getDisplayHeight()/4);
        
        enemyShip.setMaxSpeed(200);
        enemyShip.setAcceleration(100);
        enemyShip.setDeacceleration(10);
        enemyShip.setRadius(8);
        
        enemyShip.setRadians(3.1415f /1);
        enemyShip.setRotationSpeed(3);
        
        return enemyShip;
    }
    
}
