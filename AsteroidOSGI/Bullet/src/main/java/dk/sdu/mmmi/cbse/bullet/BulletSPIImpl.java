/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dk.sdu.mmmi.cbse.bullet;

import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.commonbullet.Bullet;
import dk.sdu.mmmi.cbse.commonbullet.BulletSPI;
import org.openide.util.lookup.ServiceProvider;

/**
 *
 * @author Christian
 */
@ServiceProvider(service = BulletSPI.class)
public class BulletSPIImpl implements BulletSPI{

    @Override
    public Entity createBullet(Entity creator) {
        Entity bullet = new Bullet();
        bullet.setAcceleration(1000);
        bullet.setDeacceleration(0);
        bullet.setDx(creator.getDx());
        bullet.setDy(creator.getDy());
        bullet.setExpiration(3);
        bullet.setLife(1);
        bullet.setMaxSpeed(300);
        bullet.setPosition(creator.getX(), creator.getY());
        bullet.setX(creator.getX() + (float) Math.cos(creator.getRadians()) * (creator.getRadius() + bullet.getRadius() + 5));
        bullet.setY(creator.getY() + (float) Math.sin(creator.getRadians()) * (creator.getRadius() + bullet.getRadius() + 5));
        bullet.setRadians(creator.getRadians());
        bullet.setRadius(8);
        bullet.setRotationSpeed(0);
        bullet.setShapeX(new float[4]);
        bullet.setShapeY(new float[4]);
        return bullet;
    }
    
}
