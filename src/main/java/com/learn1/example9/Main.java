package com.learn1.example9;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;

/**
 * @author Feng Jie
 * @date 2023/5/9 17:53
 */
public class Main {
    public static void main(String[] args) {
        ActorSystem sys = ActorSystem.create("sys");
        EventBusDemo eventBusDemo = new EventBusDemo();
        ActorRef actor = sys.actorOf(Props.create(Subscriber.class), "suber");
        eventBusDemo.subscribe(actor, "info");
        eventBusDemo.subscribe(actor, "warn");
        eventBusDemo.publish(new Event("info", "info level message"));
        eventBusDemo.publish(new Event("other", "other level message"));

    }
}
