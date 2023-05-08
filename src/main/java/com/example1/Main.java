package com.example1;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;

public class Main {

    public static void main(String[] args) {
        ActorSystem system = ActorSystem.create("sys");
        system.actorOf(Props.create(HelloWorld.class), "helloworld");
        
    }
}
