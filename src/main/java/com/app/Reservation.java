package com.app;

public class Reservation<T> {
    private final T spaceName;
    private final boolean isBooked;
    private final T username;

    public Reservation(T spaceName, boolean isBooked,T username) {
        this.spaceName = spaceName;
        this.isBooked = isBooked;
        this.username = username;
    }

    public T getSpaceName() { return spaceName; }
    public boolean getBooked() { return isBooked; }
    public T getUsername() { return username; }

    public T setSpaceName(T spaceName) {
        return this.spaceName;
    }

}
