/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dk.sdu.mmmi.cbse.entities;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.MathUtils;

/**
 *
 * @author Christian
 */
public class Bullet extends SpaceObject{
    private float lifeTime;
    private float lifeTimer;
    private boolean remove;
    
    public Bullet(float x, float y, float radians){
        this.x = x;
        this.y = y; 
        this.radians = radians;
        
        float speed = 350;
        dx = MathUtils.cos(radians) * speed;
        dy = MathUtils.sin(radians) * speed;
        
        width = height = 2;
        
        lifeTimer = 0;
        lifeTime = 1;
        
    }
    
    public boolean shouldRemove(){
        return remove;
    }
    
    public void update(float dt){
        
        x+= dx * dt;
        y+= dy * dt;
        
        wrap();
        
        lifeTimer += dt;
        if(lifeTimer > lifeTime){
            remove = true;
        }
    }
    
    public void draw(ShapeRenderer sr){
        sr.begin(ShapeType.Filled);
        sr.setColor(1, 1, 1, 1);
        sr.circle(x-width/2, y-height/2, width /2);
        sr.end();
        
    }
}
