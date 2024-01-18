package com.ukream.kafka;

public interface KafkaBaseListener<T> {
    void createAction(T t);

    void updateAction(T t);

    void deleteAction(T t);
}
