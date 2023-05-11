package com.learn1.example1;

import akka.actor.ActorSystem;
import akka.actor.Props;

public class Main {

    public static void main(String[] args) {
        ActorSystem system = ActorSystem.create("sys");
        system.actorOf(Props.create(HelloWorld.class), "helloworld");
        
    }
}
