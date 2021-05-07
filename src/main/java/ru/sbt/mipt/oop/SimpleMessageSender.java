package ru.sbt.mipt.oop;

public class SimpleMessageSender implements MessageSender{
    @Override
    public void sendMessage() {
        System.out.println("Sending sms");
    }
}
