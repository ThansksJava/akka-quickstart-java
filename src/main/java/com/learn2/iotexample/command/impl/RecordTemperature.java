package com.learn2.iotexample.command.impl;

import akka.actor.typed.ActorRef;
import com.learn2.iotexample.command.Command;

public class RecordTemperature implements Command {
    final long requestId;
    final double value;
    final ActorRef<TemperatureRecorded> replyTo;

    public RecordTemperature(long requestId, double value, ActorRef<TemperatureRecorded> replyTo) {
        this.requestId = requestId;
        this.value = value;
        this.replyTo = replyTo;
    }


    public long getRequestId() {
        return requestId;
    }

    public double getValue() {
        return value;
    }

    public ActorRef<TemperatureRecorded> getReplyTo() {
        return replyTo;
    }
}
