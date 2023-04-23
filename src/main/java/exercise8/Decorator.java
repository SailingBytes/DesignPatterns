package exercise8;

class Bird
{
    public int age;

    public String fly()
    {
        return age < 10 ? "flying" : "too old";
    }
}

class Lizard
{
    public int age;

    public String crawl()
    {
        return (age > 1) ? "crawling" : "too young";
    }
}

class Dragon
{
    private Lizard lizard;
    private Bird bird;

    private int age;

    public Dragon() {
        this.bird = new Bird();
        this.lizard = new Lizard();
    }

    Dragon(int age) {
        this.age = age;
        this.bird = new Bird();
        this.bird.age = age;
        this.lizard = new Lizard();
        this.lizard.age = age;
    }

    public void setAge(int age)
    {
        this.age = age;
        this.bird.age = age;
        this.lizard.age = age;
    }
    public String fly()
    {
        return this.bird.fly();
    }
    public String crawl()
    {
        return this.lizard.crawl();
    }
}