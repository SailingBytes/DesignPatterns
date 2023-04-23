package exercise2;

class Person
{
    public int id;
    public String name;

    public Person(int id, String name)
    {
        this.id = id;
        this.name = name;
    }
}

class PersonFactory
{
    private static int idCounter = 0;

    public Person createPerson(String name)
    {
        return new Person(idCounter++, name);
    }
}
