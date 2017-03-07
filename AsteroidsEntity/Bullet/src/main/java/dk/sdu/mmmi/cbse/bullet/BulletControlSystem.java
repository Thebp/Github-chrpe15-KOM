/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dk.sdu.mmmi.cbse.bullet;

import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.EntityType;
import static dk.sdu.mmmi.cbse.common.data.EntityType.BULLET;
import static dk.sdu.mmmi.cbse.common.data.EntityType.PLAYER;
import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.World;
import dk.sdu.mmmi.cbse.common.events.Event;
import dk.sdu.mmmi.cbse.common.events.EventType;
import static dk.sdu.mmmi.cbse.common.events.EventType.ENEMY_SHOOT;
import static dk.sdu.mmmi.cbse.common.events.EventType.PLAYER_SHOOT;
import dk.sdu.mmmi.cbse.common.services.IEntityProcessingService;
import dk.sdu.mmmi.cbse.common.services.IGamePluginService;
import java.util.Random;

/**
 *
 * @author Christian
 */
public class BulletControlSystem implements IEntityProcessingService, IGamePluginService {

    @Override
    public void process(GameData gameData, World world) {
        if (!gameData.getEvents().isEmpty()) {
            for (Event event : gameData.getEvents()) {
                if (event.getType() == PLAYER_SHOOT) {
                    for (Entity entity : world.getEntities(PLAYER)) {
                        if (entity.getID().equals(event.getEntityID())) {
                            Entity bullet = new Entity();
                            bullet.setType(BULLET);
                            bullet.setRadius(4);
                            bullet.setX(entity.getX() + (float) Math.cos(entity.getRadians() * (entity.getRadius() + bullet.getRadius() + 1)));
                            bullet.setY(entity.getY() + (float) Math.sin(entity.getRadians() * (entity.getRadius() + bullet.getRadius() + 1)));
                            bullet.setDx(entity.getDx());
                            bullet.setDy(entity.getDy());
                            bullet.setAcceleration(10000);
                            bullet.setDeacceleration(0);
                            bullet.setMaxSpeed(400);
                            bullet.setExpiration(2.5f);
                            bullet.setRadians(entity.getRadians());
                            world.addEntity(bullet);
                            gameData.removeEvent(event);
                        }
                    }
                }
            }
        }
        for (Entity bullet : world.getEntities(EntityType.BULLET)) {
            if(bullet.getExpiration() < 0){
                world.removeEntity(bullet);
            }
            float x = bullet.getX();
            float y = bullet.getY();
            float dx = bullet.getDx();
            float dy = bullet.getDy();
            float radians = bullet.getRadians();
            //int rotationSpeed = bullet.getRotationSpeed();
            float dt = gameData.getDelta();
            float acceleration = bullet.getAcceleration();
            float deceleration = bullet.getDeacceleration();
            float maxSpeed = bullet.getMaxSpeed();
            float[] shapeX = bullet.getShapeX();
            float[] shapeY = bullet.getShapeY();
            //boolean up = true;

            // accelerating
            
                dx += Math.cos(radians) * acceleration * dt;
                dy += Math.sin(radians) * acceleration * dt;
            

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

            shapeX[0] = (float) (bullet.getX() + Math.cos(radians + Math.PI * 1) * bullet.getRadius());
            shapeY[0] = (float) (bullet.getY() + Math.sin(radians + Math.PI * 1) * bullet.getRadius());

            shapeX[1] = (float) (bullet.getX() + Math.cos(radians + Math.PI * 2) * bullet.getRadius());
            shapeY[1] = (float) (bullet.getY() + Math.sin(radians + Math.PI * 2) * bullet.getRadius());

            shapeX[2] = (float) (bullet.getX() + Math.cos(radians + Math.PI * 3) * bullet.getRadius());
            shapeY[2] = (float) (bullet.getY() + Math.sin(radians + Math.PI * 3) * bullet.getRadius());

            shapeX[3] = (float) (bullet.getX() + Math.cos(radians + Math.PI * 4) * bullet.getRadius());
            shapeY[3] = (float) (bullet.getY() + Math.sin(radians + Math.PI * 4) * bullet.getRadius());

            bullet.setX(x);
            bullet.setY(y);
            bullet.setDx(dx);
            bullet.setDy(dy);
            bullet.setRadians(radians);
            //asteroid.setRotationSpeed(rotationSpeed);
            bullet.setAcceleration(acceleration);
            bullet.setDeacceleration(deceleration);
            bullet.setMaxSpeed(maxSpeed);
            bullet.setShapeX(shapeX);
            bullet.setShapeY(shapeY);
            bullet.setExpiration(bullet.getExpiration() - dt);
        }
    }

    @Override
    public void start(GameData gameData, World world) {

    }

    @Override
    public void stop(GameData gameData, World world) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
