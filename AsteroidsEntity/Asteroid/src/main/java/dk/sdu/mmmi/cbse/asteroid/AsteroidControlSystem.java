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
import dk.sdu.mmmi.cbse.common.events.Event;
import static dk.sdu.mmmi.cbse.common.events.EventType.ASTEROID_SPLIT;
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

            if (asteroid.getIsHit() == true) {
                gameData.addEvent(new Event(ASTEROID_SPLIT, asteroid.getID()));
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

            shapeX[0] = (float) (asteroid.getX() + Math.cos(radians + Math.PI * 1) * asteroid.getRadius());
            shapeY[0] = (float) (asteroid.getY() + Math.sin(radians + Math.PI * 1) * asteroid.getRadius());

            shapeX[1] = (float) (asteroid.getX() + Math.cos(radians + Math.PI * 1.25) * asteroid.getRadius());
            shapeY[1] = (float) (asteroid.getY() + Math.sin(radians + Math.PI * 1.25) * asteroid.getRadius());

            shapeX[2] = (float) (asteroid.getX() + Math.cos(radians + Math.PI * 1.5) * asteroid.getRadius());
            shapeY[2] = (float) (asteroid.getY() + Math.sin(radians + Math.PI * 1.5) * asteroid.getRadius());

            shapeX[3] = (float) (asteroid.getX() + Math.cos(radians + Math.PI * 1.75) * asteroid.getRadius());
            shapeY[3] = (float) (asteroid.getY() + Math.sin(radians + Math.PI * 1.75) * asteroid.getRadius());

            shapeX[4] = (float) (asteroid.getX() + Math.cos(radians + Math.PI * 2) * asteroid.getRadius());
            shapeY[4] = (float) (asteroid.getY() + Math.sin(radians + Math.PI * 2) * asteroid.getRadius());

            shapeX[5] = (float) (asteroid.getX() + Math.cos(radians + Math.PI * 2.25) * asteroid.getRadius());
            shapeY[5] = (float) (asteroid.getY() + Math.sin(radians + Math.PI * 2.25) * asteroid.getRadius());

            shapeX[6] = (float) (asteroid.getX() + Math.cos(radians + Math.PI * 2.5) * asteroid.getRadius());
            shapeY[6] = (float) (asteroid.getY() + Math.sin(radians + Math.PI * 2.5) * asteroid.getRadius());

            shapeX[7] = (float) (asteroid.getX() + Math.cos(radians + Math.PI * 2.75) * asteroid.getRadius());
            shapeY[7] = (float) (asteroid.getY() + Math.sin(radians + Math.PI * 2.75) * asteroid.getRadius());

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

            for (Event event : gameData.getEvents()) {
                if (event.getType() == ASTEROID_SPLIT) {
                    Entity asteroids = new Entity();
                    asteroids.setType(EntityType.ASTEROIDS);
                    event.getEntityID();
                    asteroids.setPosition( , );

                    asteroids.setMaxSpeed(150);
                    asteroids.setAcceleration(75);
                    asteroids.setDeacceleration(5);
                    if(asteroids.getRadius() == 32){
                        asteroids.setRadius(16);
                    }else if(asteroids.getRadius() == 16){
                        asteroids.setRadius(8);
                    }

                    asteroids.setRadians(3.1415f / 2);
                    asteroids.setRotationSpeed(3);
                    world.addEntity(asteroids);
                    
                }
            }
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
