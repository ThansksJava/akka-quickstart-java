package com.example;

import akka.actor.testkit.typed.javadsl.TestKitJunitResource;
import akka.actor.testkit.typed.javadsl.TestProbe;
import akka.actor.typed.ActorRef;
import com.learn1.example.Greeter;
import com.learn2.iotexample.Device;
import com.learn2.iotexample.command.Command;
import com.learn2.iotexample.command.impl.ReadTemperature;
import com.learn2.iotexample.command.impl.RecordTemperature;
import com.learn2.iotexample.command.impl.RespondTemperature;
import com.learn2.iotexample.command.impl.TemperatureRecorded;
import org.junit.ClassRule;
import org.junit.Test;

import java.util.Optional;

import static org.junit.Assert.assertEquals;

//#definition
public class AkkaQuickstartTest {

    @ClassRule
    public static final TestKitJunitResource testKit = new TestKitJunitResource();
//#definition

    //#test
    @Test
    public void testGreeterActorSendingOfGreeting() {
        TestProbe<Greeter.Greeted> testProbe = testKit.createTestProbe();
        ActorRef<Greeter.Greet> underTest = testKit.spawn(Greeter.create(), "greeter");
        underTest.tell(new Greeter.Greet("Charles", testProbe.getRef()));
        testProbe.expectMessage(new Greeter.Greeted("Charles", underTest));
    }
    //#test
    @Test
    public void testReplyWithEmptyReadingIfNoTemperatureIsKnown() {
        TestProbe<RespondTemperature> probe =
                testKit.createTestProbe(RespondTemperature.class);
        ActorRef<Command> deviceActor = testKit.spawn(Device.create("group1", "device1"));
        deviceActor.tell(new ReadTemperature(42L, probe.getRef()));
        RespondTemperature response = probe.receiveMessage();
        assertEquals(Long.valueOf(42), response.getRequestId());
        assertEquals(Optional.empty(), response.getValue());
    }

    @Test
    public void testReplyWithLatestTemperatureReading() {
        TestProbe<TemperatureRecorded> recordProbe =
                testKit.createTestProbe(TemperatureRecorded.class);
        TestProbe<RespondTemperature> readProbe =
                testKit.createTestProbe(RespondTemperature.class);
        ActorRef<Command> deviceActor = testKit.spawn(Device.create("group", "device"));

        deviceActor.tell(new RecordTemperature(1L, 24.0, recordProbe.getRef()));
        assertEquals(Long.valueOf(1), recordProbe.receiveMessage().getRequestId());

        deviceActor.tell(new ReadTemperature(2L, readProbe.getRef()));
        RespondTemperature response1 = readProbe.receiveMessage();
        assertEquals(Long.valueOf(2), response1.getRequestId());
        assertEquals(Optional.of(24.0), response1.getValue());

        deviceActor.tell(new RecordTemperature(3L, 55.0, recordProbe.getRef()));
        assertEquals(Long.valueOf(3), recordProbe.receiveMessage().getRequestId());

        deviceActor.tell(new ReadTemperature(4L, readProbe.getRef()));
        RespondTemperature response2 = readProbe.receiveMessage();
        assertEquals(Long.valueOf(4), response2.getRequestId());
        assertEquals(Optional.of(55.0), response2.getValue());
    }
}
