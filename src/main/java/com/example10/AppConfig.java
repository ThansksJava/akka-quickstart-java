package com.example10;

import akka.actor.ActorSystem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Feng Jie
 * @date 2023/5/9 20:08
 */
@Configuration
public class AppConfig {

    @Autowired
    private ApplicationContext applicationContext;

    @Bean
    public ActorSystem actorSystem(){
        ActorSystem sys = ActorSystem.create("sys");
        SpringExtProvider.getInstance().get(sys).initApplicationContext(applicationContext);
        return sys;
    }
}
