package com.erely.kafka;


import java.util.concurrent.*;

public class FutureRun{

    public static void main(String[] args) throws ExecutionException, InterruptedException {

        ExecutorService service = Executors.newCachedThreadPool();
        Future<Msg> futurn = service.submit(new Callable<Msg>() {
            public Msg call() {
               return  new Msg("dfsaf","fdsafdsa");
            }
        });

    }
}
