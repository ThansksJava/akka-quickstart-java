package com.learn2.iotexample.command.impl;

public class TemperatureRecorded {
    final Long requestId;

    public TemperatureRecorded(long requestId) {
        this.requestId = requestId;
    }

    public Long getRequestId() {
        return requestId;
    }
}