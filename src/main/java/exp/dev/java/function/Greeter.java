package exp.dev.java.function;

public class Greeter {

    public void greet(Greeting greeting) {
        greeting.perform();
    }

    public static void main(String[] args) {
        Greeter greeter = new Greeter();
        greeter.greet(new HelloWorldGreeting());

        Greeting namaste = () -> System.out.println("Namaste!!");
        greeter.greet(namaste);
    }
}
