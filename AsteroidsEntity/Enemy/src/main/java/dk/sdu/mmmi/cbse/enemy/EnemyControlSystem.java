/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dk.sdu.mmmi.cbse.enemy;

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
public class EnemyControlSystem implements IEntityProcessingService, IGamePluginService {

    @Override
    public void process(GameData gameData, World world) {
        for (Entity enemy : world.getEntities(EntityType.ENEMY)) {
            float x = enemy.getX();
            float y = enemy.getY();
            float dx = enemy.getDx();
            float dy = enemy.getDy();
            float radians = enemy.getRadians();
            int rotationSpeed = enemy.getRotationSpeed();
            float dt = gameData.getDelta();
            float acceleration = enemy.getAcceleration();
            float deceleration = enemy.getDeacceleration();
            float maxSpeed = enemy.getMaxSpeed();
            float[] shapeX = enemy.getShapeX();
            float[] shapeY = enemy.getShapeY();
            Random rand = new Random();
            boolean up = true;
            
            if (rand.nextBoolean() == true) {
                radians += rotationSpeed * dt;
            } else if (rand.nextBoolean() == false) {
                radians -= rotationSpeed * dt;
            }

            // accelerating
            if (up = true) {
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

            enemy.setX(x);
            enemy.setY(y);
            enemy.setDx(dx);
            enemy.setDy(dy);
            enemy.setRadians(radians);
            enemy.setRotationSpeed(rotationSpeed);
            enemy.setAcceleration(acceleration);
            enemy.setDeacceleration(deceleration);
            enemy.setMaxSpeed(maxSpeed);
            enemy.setShapeX(shapeX);
            enemy.setShapeY(shapeY);
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
