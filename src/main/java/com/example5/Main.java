package com.example5;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;
import com.example4.BecomeActor;

/**
 * @author Feng Jie
 * @date 2023/5/9 11:13
 */
public class Main {
    public static void main(String[] args) {
        ActorSystem system = ActorSystem.create("sys");
        ActorRef actor = system.actorOf(Props.create(WatchActor.class), "watchActor");
        actor.tell("stop", ActorRef.noSender());
    }
}
