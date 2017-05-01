/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dk.sdu.mmmi.cbse.spring;

import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.World;
import dk.sdu.mmmi.cbse.common.services.IEntityProcessingService;
import dk.sdu.mmmi.cbse.common.services.IGamePluginService;
import dk.sdu.mmmi.cbse.commonbullet.BulletSPI;
import java.util.Random;
import org.openide.util.Lookup;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 *
 * @author Christian
 */
public class SpringEnemy implements IGamePluginService, IEntityProcessingService{
    private Entity enemys;
    ApplicationContext ctx = new AnnotationConfigApplicationContext(BulletConfig.class);
    private BulletSPI bullets;
    
    public SpringEnemy(){
        bullets = ctx.getBean(BulletSPI.class);
    }
    @Override
    public void start(GameData gameData, World world) {
        enemys = createEnemyShip(gameData);
        world.addEntity(enemys);
    }

    @Override
    public void stop(GameData gameData, World world) {
        world.removeEntity(enemys);
    }

    @Override
    public void process(GameData gameData, World world) {
        for (Entity enemy : world.getEntities(Enemy.class)) {
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
            Random rand2 = new Random();
            boolean up = true;
            
            if (rand.nextBoolean() == true) {
                radians += rotationSpeed * dt;
            } else if (rand.nextBoolean() == false) {
                radians -= rotationSpeed * dt;
            }
            //shooting
            //BulletSPI bulletSPI = Lookup.getDefault().lookup(BulletSPI.class);
            if(rand2.nextInt(10000) < 20 && bullets != null){
                world.addEntity( bullets.createBullet(enemy));
                
            }
            // accelerating
            if (up = true) {
                dx += Math.cos(radians) * acceleration * dt;
                dy += Math.sin(radians) * acceleration * dt;
            }
            //collision
            if(enemy.getIsHit() == true){
                world.removeEntity(enemy);
                enemy.setIsHit(false);
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

            
            shapeX[0] = (float) (x + Math.cos(radians) * enemy.getRadius());
            shapeY[0] = (float) (y + Math.sin(radians) * enemy.getRadius());

            shapeX[1] = (float) (x + Math.cos(radians - 4 * 3.1415f / 5) * enemy.getRadius());
            shapeY[1] = (float) (y + Math.sin(radians - 4 * 3.1145f / 5) * enemy.getRadius());

            shapeX[2] = (float) (x + Math.cos(radians + 3.1415f) * enemy.getRadius());
            shapeY[2] = (float) (y + Math.sin(radians + 3.1415f) * enemy.getRadius());

            shapeX[3] = (float) (x + Math.cos(radians + 4 * 3.1415f / 5) * enemy.getRadius());
            shapeY[3] = (float) (y + Math.sin(radians + 4 * 3.1415f / 5) * enemy.getRadius());

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
