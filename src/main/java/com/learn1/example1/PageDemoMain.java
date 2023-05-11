package com.learn1.example1;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.dispatch.OnComplete;
import akka.pattern.Patterns;
import akka.util.Timeout;
import scala.concurrent.Future;
import scala.concurrent.duration.Duration;

/**
 * @author Feng Jie
 * @date 2023/5/8 18:08
 */
public class PageDemoMain {
    public static void main(String[] args) {
        ActorSystem system = ActorSystem.create("sys");
        ActorRef propsActor = system.actorOf(PropsDemoActor.createProps(), "propsActor");
//        propsActor.tell("hello akka", ActorRef.noSender());
//        System.out.println("after send a message");
        Future<Object> ask = Patterns.ask(propsActor, "Akka ask", new Timeout(Duration.create(2, "seconds")));
        ask.onComplete(new OnComplete<Object>() {
            @Override
            public void onComplete(Throwable failure, Object success) throws Throwable {
                System.out.println("收到消息：" + success);
            }
        },system.dispatcher());
        System.out.println("ask...");

    }
}
