package com.example6;

import akka.actor.ActorRef;
import akka.actor.ActorSelection;
import akka.actor.ActorSystem;
import akka.actor.Props;

import java.io.IOException;
import java.sql.SQLException;

/**
 * @author Feng Jie
 * @date 2023/5/9 11:13
 */
public class Main {
    public static void main(String[] args) {
        ActorSystem system = ActorSystem.create("sys");
        ActorRef actor = system.actorOf(Props.create(SupervisorActor.class), "supervisorActor");
        ActorSelection actorSelection = system.actorSelection("/user/supervisorActor/workerActor");
        actorSelection.tell(new IOException("IO Ex"), ActorRef.noSender());
//        actorSelection.tell(new SQLException("SQL Ex"),  ActorRef.noSender());
//        actorSelection.tell (new IndexOutOfBoundsException (),  ActorRef.noSender());
        actorSelection.tell("getValue",  ActorRef.noSender());
    }
}
