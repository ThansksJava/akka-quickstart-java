package com.learn1.example10;

import akka.actor.Actor;
import akka.actor.IndirectActorProducer;
import org.springframework.context.ApplicationContext;

/**
 * @author Feng Jie
 * @date 2023/5/9 19:58
 */
public class SpringDI implements IndirectActorProducer {
    private ApplicationContext applicationContext;

    private String springBeanName;

    public SpringDI(ApplicationContext applicationContext, String springBeanName) {
        this.applicationContext = applicationContext;
        this.springBeanName = springBeanName;
    }

    @Override
    public Actor produce() {
        return (Actor) applicationContext.getBean(this.springBeanName);
    }

    @Override
    public Class<? extends Actor> actorClass() {
        return (Class<? extends Actor>) applicationContext.getType(this.springBeanName);
    }
}
