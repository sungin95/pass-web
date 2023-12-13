package com.fastcampus.pass.repository.pass;

public enum PassStatus {
    ACTIVATION, DEACTIVATION, STOP;

    public String toString() {
        return name();
    }

}