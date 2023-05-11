package com.learn2.iotexample.command.impl;

import akka.actor.typed.ActorRef;
import com.learn2.iotexample.command.Command;

public class ReadTemperature implements Command {
    final Long requestId;
    final ActorRef<RespondTemperature> replyTo;

    public ReadTemperature(Long requestId, ActorRef<RespondTemperature> replyTo) {
        this.requestId = requestId;
        this.replyTo = replyTo;
    }

    public Long getRequestId() {
        return requestId;
    }

    public ActorRef<RespondTemperature> getReplyTo() {
        return replyTo;
    }
}