package com.starriverdata.core.netty.master;

import org.junit.Test;

import java.util.concurrent.*;

public class MasterHandlerTest {


    @Test
    public void testFutureTask() throws InterruptedException, ExecutionException {
        CompletionService<String> x = new ExecutorCompletionService<>(Executors.newCachedThreadPool());
        x.submit(() -> "77");

        Future<String> take = x.take();

        System.out.println(take.get());
    }
}