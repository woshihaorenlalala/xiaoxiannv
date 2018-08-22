package com.play.base.listener;

import org.apache.shiro.session.Session;
import org.apache.shiro.session.SessionListener;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by cc on 2018/4/23.
 */
public class BDSessionListener implements SessionListener{
    private final AtomicInteger atomicInteger = new AtomicInteger(0);
    @Override
    public void onStart(Session session) {
        atomicInteger.incrementAndGet();
    }

    @Override
    public void onStop(Session session) {
        atomicInteger.decrementAndGet();
    }

    @Override
    public void onExpiration(Session session) {
        atomicInteger.decrementAndGet();
    }
}
