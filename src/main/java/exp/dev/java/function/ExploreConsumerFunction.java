package exp.dev.java.function;

import java.util.concurrent.CompletableFuture;
import java.util.function.Consumer;
import java.util.function.Supplier;

public class ExploreConsumerFunction {

    public static void main(String[] args) {
        // Method reference
        // (message) -> System.out.println(message) -> It is converted to System.out::println.
        // Consumer
        Consumer<String> message = payload -> {
            System.out.println(payload + " Thread - " + Thread.currentThread().getName());
        };
        // message.accept("Hello World!");

        // TODO: Advance usages
        // 1. Chaining consumers with andThen
        Consumer<String> upperCase = input -> System.out.println(input.toUpperCase());
        Consumer<String> combined = upperCase.andThen(message);
        combined.accept("Namaste!!");

        // 2. Example with CompletableFuture
        Supplier<String> doSomeAsyncOps = () -> {
            try {
                for(int i = 0; i < 5; i++){
                    Thread.sleep(500);
                    System.out.println("Sleeping - " + i + " times. Thread - " + Thread.currentThread().getName());
                }
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            return "Hello from supplier!";
        };
        CompletableFuture<String> future = CompletableFuture.supplyAsync(doSomeAsyncOps);
        // thenAcceptAsync: The action is executed asynchronously on a separate thread, typically using the common
        // ForkJoinPool unless a specific Executor is provided. Pass second arg as Executor
        // TODO: Advantages of thenAcceptAsync
        // 1. Non-blocking
        // 2. Concurrency
        // 3. Customization: Can use custom executors
        future.thenAcceptAsync(message);
        // thenAccept: The action is executed on the same thread that completes the future
        // Wait for the future to complete
        future.thenAccept(message);
        future.join();

    }

}
