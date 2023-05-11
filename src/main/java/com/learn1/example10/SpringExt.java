package com.learn1.example10;

import akka.actor.Extension;
import akka.actor.Props;
import org.springframework.context.ApplicationContext;

/**
 * @author Feng Jie
 * @date 2023/5/9 20:02
 */
public class SpringExt implements Extension {
    private ApplicationContext applicationContext;

    public void initApplicationContext(ApplicationContext applicationContext){
        this.applicationContext = applicationContext;
    }

    public Props createProps(String actorBeanName){
        return Props.create(SpringDI.class, this.applicationContext, actorBeanName);
    }

}
