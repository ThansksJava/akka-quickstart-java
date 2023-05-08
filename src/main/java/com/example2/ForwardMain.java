package com.example2;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;
import akka.dispatch.OnComplete;
import akka.pattern.Patterns;
import akka.util.Timeout;
import com.example1.PropsDemoActor;
import scala.concurrent.Future;
import scala.concurrent.duration.Duration;

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
