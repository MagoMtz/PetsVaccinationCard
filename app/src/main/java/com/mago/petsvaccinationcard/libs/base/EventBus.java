package com.mago.petsvaccinationcard.libs.base;

/**
 * Created by jorgemartinez on 17/12/18.
 */
public interface EventBus {
    void register(Object subscriber);
    void unregister(Object subscriber);
    void post(Object event);
}
