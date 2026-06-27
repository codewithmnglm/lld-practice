package com.pattern.observer;

public class Subscriber implements ISubscriber {

    private Channel channel;
    private String name ;

    public Subscriber(String name,Channel channel) {
        this.name = name;
        this.channel = channel;
    }

    @Override
    public void update(String videoName) {

        System.out.println("Channel Uploaded a video " + videoName);

    }
}
