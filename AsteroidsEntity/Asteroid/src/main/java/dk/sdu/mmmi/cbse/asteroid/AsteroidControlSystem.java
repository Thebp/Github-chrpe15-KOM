/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dk.sdu.mmmi.cbse.asteroid;

import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.EntityType;
import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.GameKeys;
import dk.sdu.mmmi.cbse.common.data.World;
import dk.sdu.mmmi.cbse.common.services.IEntityProcessingService;
import dk.sdu.mmmi.cbse.common.services.IGamePluginService;
import java.util.Random;

/**
 *
 * @author Christian
 */
public class AsteroidControlSystem implements IEntityProcessingService, IGamePluginService {

    @Override
    public void process(GameData gameData, World world) {
        for (Entity asteroid : world.getEntities(EntityType.ASTEROIDS)) {
            float x = asteroid.getX();
            float y = asteroid.getY();
            float dx = asteroid.getDx();
            float dy = asteroid.getDy();
            float radians = asteroid.getRadians();
            int rotationSpeed = asteroid.getRotationSpeed();
            float dt = gameData.getDelta();
            float acceleration = asteroid.getAcceleration();
            float deceleration = asteroid.getDeacceleration();
            float maxSpeed = asteroid.getMaxSpeed();
            float[] shapeX = asteroid.getShapeX();
            float[] shapeY = asteroid.getShapeY();
            boolean up = true;
            Random rand = new Random();
            
            if (rand.nextBoolean() == true) {
                radians += rotationSpeed * dt;
            } else if (rand.nextBoolean() == false) {
                radians -= rotationSpeed * dt;
            }
            
            

            // accelerating
            if (up == true) {
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

            shapeX[0] = (float) (asteroid.getX() + Math.cos(radians + Math.PI * 1) * 20);
            shapeY[0] = (float) (asteroid.getY() + Math.sin(radians + Math.PI * 1) * 20);

            shapeX[1] = (float) (asteroid.getX() + Math.cos(radians + Math.PI * 1.25) * 20);
            shapeY[1] = (float) (asteroid.getY() + Math.sin(radians + Math.PI * 1.25) * 20);

            shapeX[2] = (float) (asteroid.getX() + Math.cos(radians + Math.PI * 1.5) * 20);
            shapeY[2] = (float) (asteroid.getY() + Math.sin(radians + Math.PI * 1.5) * 20);

            shapeX[3] = (float) (asteroid.getX() + Math.cos(radians + Math.PI * 1.75) * 20);
            shapeY[3] = (float) (asteroid.getY() + Math.sin(radians + Math.PI * 1.75) * 20);
            
            shapeX[4] = (float) (asteroid.getX() + Math.cos(radians + Math.PI * 2) * 20);
            shapeY[4] = (float) (asteroid.getY() + Math.sin(radians + Math.PI * 2) * 20);
            
            shapeX[5] = (float) (asteroid.getX() + Math.cos(radians + Math.PI * 2.25) * 20);
            shapeY[5] = (float) (asteroid.getY() + Math.sin(radians + Math.PI * 2.25) * 20);
            
            shapeX[6] = (float) (asteroid.getX() + Math.cos(radians + Math.PI * 2.5) * 20);
            shapeY[6] = (float) (asteroid.getY() + Math.sin(radians + Math.PI * 2.5) * 20);
            
            shapeX[7] = (float) (asteroid.getX() + Math.cos(radians + Math.PI * 2.75) * 20);
            shapeY[7] = (float) (asteroid.getY() + Math.sin(radians + Math.PI * 2.75) * 20);

            asteroid.setX(x);
            asteroid.setY(y);
            asteroid.setDx(dx);
            asteroid.setDy(dy);
            asteroid.setRadians(radians);
            asteroid.setRotationSpeed(rotationSpeed);
            asteroid.setAcceleration(acceleration);
            asteroid.setDeacceleration(deceleration);
            asteroid.setMaxSpeed(maxSpeed);
            asteroid.setShapeX(shapeX);
            asteroid.setShapeY(shapeY);
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
