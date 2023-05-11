package com.learn1.example11;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;

/**
 * @author Feng Jie
 * @date 2023/5/10 11:32
 */
public class Main {
    public static void main(String[] args) {
        ActorSystem sys = ActorSystem.create("sys");
        ActorRef tcpServer = sys.actorOf(Props.create(TcpServerDemo.class), "tcpServer");
    }
}
