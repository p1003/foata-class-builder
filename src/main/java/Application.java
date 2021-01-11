

public class Application {
    public static void main (String[] args) {
        if(args.length < 2) {
            System.out.println("Too few arguments");
            System.exit(2137);
        }
        Executor e = new Executor();
        e.run(args[0],args[1]);
    }
}