/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dk.sdu.mmmi.cbse.spring;

import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.World;
import dk.sdu.mmmi.cbse.common.services.IEntityProcessingService;
import dk.sdu.mmmi.cbse.common.services.IGamePluginService;
import dk.sdu.mmmi.cbse.springenemy.SpringEnemy;
import dk.sdu.mmmi.cbse.springplayer.SpringPlayer;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.openide.util.lookup.ServiceProviders;
import org.openide.util.lookup.ServiceProvider;
/**
 *
 * @author Christian
 */
@ServiceProviders(value = {
    @ServiceProvider(service = IGamePluginService.class),
    @ServiceProvider(service = IEntityProcessingService.class)
})
public class SpringPlayerClient implements IGamePluginService, IEntityProcessingService {
    
    ApplicationContext appcon = new ClassPathXmlApplicationContext("Beans.xml");
    SpringPlayer springPlayer = (SpringPlayer) appcon.getBean("SpringPlayer");
    ApplicationContext appco = new ClassPathXmlApplicationContext("EBeans.xml");
    SpringEnemy springEnemy = (SpringEnemy) appco.getBean("SpringEnemy");
    @Override
    public void start(GameData gameData, World world) {
        springPlayer.start(gameData, world);
        springEnemy.start(gameData, world);
    }

    @Override
    public void stop(GameData gameData, World world) {
        springPlayer.stop(gameData, world);
        springEnemy.stop(gameData, world);
    }

    @Override
    public void process(GameData gameData, World world) {
        springPlayer.process(gameData, world);
        springEnemy.process(gameData, world);
    }
    
}
