/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dk.sdu.mmmi.cbse.spring;

import dk.sdu.mmmi.cbse.commonbullet.BulletSPI;
import org.openide.util.Lookup;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 *
 * @author Christian
 */
@Configuration
public class BulletConfig{
    @Bean
    public BulletSPI bulletService(){
        return Lookup.getDefault().lookup(BulletSPI.class);
    }
}
