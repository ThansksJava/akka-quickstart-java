package com.learn1.example2;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;

/**
 * @author Feng Jie
 * @date 2023/5/8 18:08
 */
public class ForwardMain {
    public static void main(String[] args) {
        ActorSystem system = ActorSystem.create("sys");
        ActorRef propsActor = system.actorOf(Props.create(ForwardActor.class), "propsActor");
        propsActor.tell("hello akka", ActorRef.noSender());
    }
}
