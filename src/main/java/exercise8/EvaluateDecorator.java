package exercise8;

public class EvaluateDecorator {
    public static void main(String[] args) {
        Dragon dragon = new Dragon();

        System.out.println("flying == " + dragon.fly());
        System.out.println("too young == " + dragon.crawl());

        dragon.setAge(20);

        System.out.println("too old == " + dragon.fly());
        System.out.println("crawling == " + dragon.crawl());
    }
}
