package com.example10;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @author Feng Jie
 * @date 2023/5/9 20:15
 */
public class Main {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        applicationContext.scan("com.example10");
        applicationContext.refresh();
        ActorSystem actorSystem = applicationContext.getBean(ActorSystem.class);
        ActorRef actor = actorSystem.actorOf(SpringExtProvider.getInstance().get(actorSystem).createProps("actorDemo"), "actorDemo");
        actor.tell("一条消息", ActorRef.noSender()) ;
    }
}
