package com.learn2.iotexample;

import akka.actor.typed.Behavior;
import akka.actor.typed.PostStop;
import akka.actor.typed.javadsl.AbstractBehavior;
import akka.actor.typed.javadsl.ActorContext;
import akka.actor.typed.javadsl.Behaviors;
import akka.actor.typed.javadsl.Receive;
import com.learn2.iotexample.command.Command;
import com.learn2.iotexample.command.impl.ReadTemperature;
import com.learn2.iotexample.command.impl.RecordTemperature;
import com.learn2.iotexample.command.impl.RespondTemperature;
import com.learn2.iotexample.command.impl.TemperatureRecorded;

import java.util.Optional;

/**
 * @author Jeremy Feng
 */
public class Device extends AbstractBehavior<Command> {

    private final String groupId;
    private final String deviceId;

    private Optional<Double> lastTemperatureReading = Optional.empty();

    public static Behavior<Command> create(String groupId, String deviceId) {
        return Behaviors.setup(context -> new Device(context, groupId, deviceId));
    }

    public Device(ActorContext<Command> context, String groupId, String deviceId) {
        super(context);
        this.groupId = groupId;
        this.deviceId = deviceId;
        context.getLog().info("Device actor {}-{} started", groupId, deviceId);
    }

    @Override
    public Receive<Command> createReceive() {
        return newReceiveBuilder()
                .onMessage(ReadTemperature.class, this::onReadTemperature)
                .onMessage(RecordTemperature.class, this::onRecordTemperature)
                .onSignal(PostStop.class, signal -> onPostStop())
                .build();
    }

    private Behavior<Command> onRecordTemperature(RecordTemperature r) {
        getContext().getLog().info("Recorded temperature reading {} with {}", r.getValue(), r.getRequestId());
        lastTemperatureReading = Optional.of(r.getValue());
        r.getReplyTo().tell(new TemperatureRecorded(r.getRequestId()));
        return this;
    }

    private Behavior<Command> onReadTemperature(ReadTemperature r) {
        r.getReplyTo().tell(new RespondTemperature(r.getRequestId(), lastTemperatureReading));
        return this;
    }

    private Behavior<Command> onPostStop() {
        getContext().getLog().info("Device actor {}-{} stopped", groupId, deviceId);
        return Behaviors.stopped();
    }


}