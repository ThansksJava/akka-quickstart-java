package com.example3;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;

/**
 * @author Feng Jie
 * @date 2023/5/8 18:08
 */
public class Main {
    public static void main(String[] args) {
        ActorSystem system = ActorSystem.create("sys");
        ActorRef propsActor = system.actorOf(Props.create(LookedUpActor.class), "lookedUpActor");
        propsActor.tell("find", ActorRef.noSender());
    }
}
