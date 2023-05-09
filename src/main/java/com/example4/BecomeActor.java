package com.example4;

import akka.actor.AbstractActor;

/**
 * @author Feng Jie
 * @date 2023/5/8 18:26
 */
public class BecomeActor extends AbstractActor {
    private AbstractActor.Receive angry;
    private AbstractActor.Receive happy;

    public BecomeActor() {
        angry =
                receiveBuilder()
                        .matchEquals(
                                "foo",
                                s -> {
                                    System.out.println("=====================");
                                    getSender().tell("I am already angry?", getSelf());
                                })
                        .matchEquals(
                                "bar",
                                s -> {
                                    getContext().become(happy);
                                })
                        .build();

        happy =
                receiveBuilder()
                        .matchEquals(
                                "bar",
                                s -> {
                                    getSender().tell("I am already happy :-)", getSelf());
                                })
                        .matchEquals(
                                "foo",
                                s -> {
                                    getContext().become(angry);
                                })
                        .build();
    }

    @Override
    public Receive createReceive() {
        return receiveBuilder()
                .matchEquals("foo", s -> {
                    System.out.println("收到foo");
                    getContext().become(angry);
                })
                .matchEquals("bar", s -> getContext().become(happy))
                .build();
    }


}
