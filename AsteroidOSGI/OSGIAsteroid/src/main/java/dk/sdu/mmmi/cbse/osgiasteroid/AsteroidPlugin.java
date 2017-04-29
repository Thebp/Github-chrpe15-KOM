/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dk.sdu.mmmi.cbse.osgiasteroid;

import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.World;
import dk.sdu.mmmi.cbse.common.services.IGamePluginService;

/**
 *
 * @author Christian
 */
public class AsteroidPlugin implements IGamePluginService{
    private Entity asteroid;
    
    public AsteroidPlugin(){
        
    }
    @Override
    public void start(GameData gameData, World world) {
        asteroid = createAsteroid(gameData);
        world.addEntity(asteroid);
        asteroid.setShapeX(new float[8]);
        asteroid.setShapeY(new float[8]);
    }

    @Override
    public void stop(GameData gameData, World world) {
        world.removeEntity(asteroid);
    }
    private Entity createAsteroid(GameData gameData){
        
        Asteroid asteroids = new Asteroid();
//        asteroids.setType(EntityType.ASTEROIDS);
        
        asteroids.setPosition(gameData.getDisplayWidth() / 1, gameData.getDisplayHeight() / 1);
        
        asteroids.setMaxSpeed(150);
        asteroids.setAcceleration(75);
        asteroids.setDeacceleration(5);
        asteroids.setRadius(32);

        asteroids.setRadians(3.1415f / 2);
        asteroids.setRotationSpeed(3);
        
        
        return asteroids;
    }
}
