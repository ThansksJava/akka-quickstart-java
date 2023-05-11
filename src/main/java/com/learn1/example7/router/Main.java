package com.learn1.example7.router;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;

/**
 * @author Feng Jie
 * @date 2023/5/9 16:22
 */
public class Main {
    public static void main(String[] args) {
        ActorSystem sys = ActorSystem.create("sys");
        ActorRef routerActor = sys.actorOf(Props.create(RouterActor.class), "routerActor");
        routerActor.tell("hahhahah1", ActorRef.noSender());
        routerActor.tell("hahhahah2", ActorRef.noSender());
        routerActor.tell("hahhahah3", ActorRef.noSender());
        routerActor.tell("hahhahah4", ActorRef.noSender());
    }
}
