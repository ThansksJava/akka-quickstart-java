package com.learn1.example4;

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
        ActorRef actor = system.actorOf(Props.create(BecomeActor.class), "becomeActor");
        actor.tell("foo", ActorRef.noSender());
    }
}
