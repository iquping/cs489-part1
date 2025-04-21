package edu.miu.cs489.quping.hello.exception;

public class SatelliteNotFoundException extends RuntimeException {
    public SatelliteNotFoundException(String message) {
        super(message);
    }
}

