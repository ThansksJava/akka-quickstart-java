package com.example9;

import akka.actor.ActorRef;
import akka.event.japi.LookupEventBus;

/**
 * @author Feng Jie
 * @date 2023/5/9 17:44
 */
public class EventBusDemo extends LookupEventBus<Event, ActorRef, String> {
    @Override
    public int mapSize() {
        return 8;
    }

    @Override
    public int compareSubscribers(ActorRef a, ActorRef b) {
        return a.compareTo(b);
    }

    @Override
    public String classify(Event event) {
        return event.getType();
    }

    @Override
    public void publish(Event event, ActorRef subscriber) {
        subscriber.tell(event.getMessage(), ActorRef.noSender());
    }
}
