package com.learn2.iotexample.command.impl;

import java.util.Optional;

public class RespondTemperature {
    final Long requestId;
    final Optional<Double> value;

    public RespondTemperature(Long requestId, Optional<Double> value) {
      this.requestId = requestId;
      this.value = value;
    }

    public Long getRequestId() {
        return requestId;
    }

    public Optional<Double> getValue() {
        return value;
    }
}