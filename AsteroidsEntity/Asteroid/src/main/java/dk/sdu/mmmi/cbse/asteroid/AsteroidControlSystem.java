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
                    Entity asteroid2 = new Entity();
                    float x2 = asteroid2.getX();
                    float y2 = asteroid2.getY();
                    float dx2 = asteroid2.getDx();
                    float dy2 = asteroid2.getDy();
                    float[] shapeX2 = asteroid2.getShapeX();
                    float[] shapeY2 = asteroid2.getShapeY();
                    asteroid2.setRadius(asteroid.getRadius()/2);
                    
                    Random rand2 = new Random();

                    if (rand2.nextBoolean() == true) {
                        radians += rotationSpeed * dt;
                    } else if (rand2.nextBoolean() == false) {
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
                    float vec2 = (float) Math.sqrt(dx2 * dx2 + dy2 * dy2);
                    if (vec2 > 0) {
                        dx2 -= (dx2 / vec2) * deceleration * dt;
                        dy2 -= (dy2 / vec2) * deceleration * dt;
                    }
                    if (vec2 > maxSpeed) {
                        dx2 = (dx / vec2) * maxSpeed;
                        dy2 = (dy / vec2) * maxSpeed;
                    }

                    // set position
                    x2 += dx2 * dt;
                    y2 += dy2 * dt;

                    shapeX2[0] = (float) (asteroid.getX() + Math.cos(radians + Math.PI * 1) * asteroid.getRadius());
                    shapeY2[0] = (float) (asteroid.getY() + Math.sin(radians + Math.PI * 1) * asteroid.getRadius());

                    shapeX2[1] = (float) (asteroid.getX() + Math.cos(radians + Math.PI * 1.25) * asteroid.getRadius());
                    shapeY2[1] = (float) (asteroid.getY() + Math.sin(radians + Math.PI * 1.25) * asteroid.getRadius());

                    shapeX2[2] = (float) (asteroid.getX() + Math.cos(radians + Math.PI * 1.5) * asteroid.getRadius());
                    shapeY2[2] = (float) (asteroid.getY() + Math.sin(radians + Math.PI * 1.5) * asteroid.getRadius());

                    shapeX2[3] = (float) (asteroid.getX() + Math.cos(radians + Math.PI * 1.75) * asteroid.getRadius());
                    shapeY2[3] = (float) (asteroid.getY() + Math.sin(radians + Math.PI * 1.75) * asteroid.getRadius());

                    shapeX2[4] = (float) (asteroid.getX() + Math.cos(radians + Math.PI * 2) * asteroid.getRadius());
                    shapeY2[4] = (float) (asteroid.getY() + Math.sin(radians + Math.PI * 2) * asteroid.getRadius());

                    shapeX2[5] = (float) (asteroid.getX() + Math.cos(radians + Math.PI * 2.25) * asteroid.getRadius());
                    shapeY2[5] = (float) (asteroid.getY() + Math.sin(radians + Math.PI * 2.25) * asteroid.getRadius());

                    shapeX2[6] = (float) (asteroid.getX() + Math.cos(radians + Math.PI * 2.5) * asteroid.getRadius());
                    shapeY2[6] = (float) (asteroid.getY() + Math.sin(radians + Math.PI * 2.5) * asteroid.getRadius());

                    shapeX2[7] = (float) (asteroid.getX() + Math.cos(radians + Math.PI * 2.75) * asteroid.getRadius());
                    shapeY2[7] = (float) (asteroid.getY() + Math.sin(radians + Math.PI * 2.75) * asteroid.getRadius());

                    asteroid.setX(x2);
                    asteroid.setY(y2);
                    asteroid.setDx(dx2);
                    asteroid.setDy(dy2);
                    asteroid.setRadians(radians);
                    asteroid.setRotationSpeed(rotationSpeed);
                    asteroid.setAcceleration(acceleration);
                    asteroid.setDeacceleration(deceleration);
                    asteroid.setMaxSpeed(maxSpeed);
                    asteroid.setShapeX(shapeX2);
                    asteroid.setShapeY(shapeY2);
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
