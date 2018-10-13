package com.hwangjr.rxbus;



import com.hwangjr.rxbus.entity.EventType;
import com.hwangjr.rxbus.entity.ProducerEvent;
import com.hwangjr.rxbus.entity.SubscriberEvent;
import com.hwangjr.rxbus.finder.Finder;
import com.hwangjr.rxbus.thread.ThreadEnforcer;

import java.util.Map;
import java.util.Set;

/**
 * Created by 小黑 on 2017/11/1.
 */

public final class AnRxBus {
    private AnRxBus(){

    }
    private static Bus sbus;

    /**
     * Get the instance of {@link Bus}
     *
     * @return
     */
    public static synchronized Bus getBus(){
        if (sbus == null){
            sbus = new Bus(ThreadEnforcer.ANY, Bus.DEFAULT_IDENTIFIER, new Finder() {
                @Override
                public Map<EventType, ProducerEvent> findAllProducers(Object listener) {
                    return SmecAnnotatedFinder.findAllProducers(listener);
                }

                @Override
                public Map<EventType, Set<SubscriberEvent>> findAllSubscribers(Object listener) {
                    return SmecAnnotatedFinder.findAllSubscribers(listener);
                }
            });
        }
        return sbus;
    }

}
