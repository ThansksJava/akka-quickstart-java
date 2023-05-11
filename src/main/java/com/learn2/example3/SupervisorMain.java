package com.learn2.example3;

import akka.actor.typed.*;
import akka.actor.typed.javadsl.AbstractBehavior;
import akka.actor.typed.javadsl.ActorContext;
import akka.actor.typed.javadsl.Behaviors;
import akka.actor.typed.javadsl.Receive;

class SupervisingActor extends AbstractBehavior<String> {

    static Behavior<String> create() {
        return Behaviors.setup(SupervisingActor::new);
    }

    private final ActorRef<String> child;

    private SupervisingActor(ActorContext<String> context) {
        super(context);
        // 创建子Actor，并指定失败策略
        child =
                context.spawn(
                        Behaviors.supervise(SupervisedActor.create()).onFailure(SupervisorStrategy.restart()),
                        "supervised-actor");
    }

    @Override
    public Receive<String> createReceive() {
//    收到消息后往子actor发送失败
        return newReceiveBuilder().onMessageEquals("failChild", this::onFailChild).build();
    }

    private Behavior<String> onFailChild() {
        child.tell("fail");
        return this;
    }
}

class SupervisedActor extends AbstractBehavior<String> {

    static Behavior<String> create() {
        return Behaviors.setup(SupervisedActor::new);
    }

    private SupervisedActor(ActorContext<String> context) {
        super(context);
        System.out.println("supervised actor started");
    }

    @Override
    public Receive<String> createReceive() {
        return newReceiveBuilder()
                .onMessageEquals("fail", this::fail)
                .onSignal(PreRestart.class, signal -> preRestart())
                .onSignal(PostStop.class, signal -> postStop())
                .build();
    }

    private Behavior<String> fail() {
        System.out.println("supervised actor fails now");
        throw new RuntimeException("I failed!");
    }

    private Behavior<String> preRestart() {
        System.out.println("supervised will be restarted");
        return this;
    }

    private Behavior<String> postStop() {
        System.out.println("supervised stopped");
        return this;
    }
}


public class SupervisorMain {
    public static void main(String[] args) {
        ActorSystem<String> supervisor = ActorSystem.create(SupervisingActor.create(), "supervisor");
        supervisor.tell("failChild");
    }
}