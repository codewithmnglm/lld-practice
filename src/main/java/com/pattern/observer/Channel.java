package com.pattern.observer;

public interface Channel {


    public void notifyUser ();
    public void subscribe (ISubscriber subscriber);
    public void unsubscribe (ISubscriber subscriber);

}
