package exercise1;

import java.util.ArrayList;
import java.util.List;

class Type {
    private String varType;

    public Type(String varType) {
        this.varType = varType;
    }

    @Override
    public String toString() {
        return varType;
    }
}

class Name {
    private String varName;

    public Name(String varName) {
        this.varName = varName;
    }

    @Override
    public String toString() {
        System.out.println(String.format("toString of %s is called", this.getClass().getName()));
        return varName;
    }
}

class Pair {
    private Type type;
    private Name name;

    public Pair(Type type, Name name) {
        this.type = type;
        this.name = name;
    }

    public Type getType() {
        return type;
    }

    public Name getName() {
        return name;
    }

    @Override
    public String toString() {
        System.out.println(String.format("toString of %s is called", this.getClass().getName()));
        return type.toString() + " " + name.toString();
    }
}

class Field {
    private Pair field;

    public Field(String name, String type) {
        this.field = new Pair(new Type(type), new Name(name));
    }

    public Pair getField() {
        System.out.println("getField " + field);
        return field;
    }

    @Override
    public String toString() {
        System.out.println(String.format("toString of %s is called", this.getClass().getName()));
        return field.toString();
    }
}

class CodeBuilder {
    private String className;
    private List<Field> fields = new ArrayList();

    public CodeBuilder(String className) {
        System.out.println("create CodeBuilder with className " + className);
        this.className = className;
    }

    public CodeBuilder addField(String name, String type) {
        System.out.println("add a field with type " + type + " and name " + name);
        fields.add(new Field(name, type));

        return this;
    }

    @Override
    public String toString() {
        System.out.println(String.format("toString of %s is called", this.className));

        StringBuilder sb = new StringBuilder();
        String lineSep = System.lineSeparator();
        sb.append(String.format("public class %s", className) + lineSep);
        sb.append("{" + lineSep);

        for (Field f : fields) {
            sb.append(String.format("  public %s %s;", f.getField().getType(), f.getField().getName()) + lineSep);
        }

        sb.append("}" + lineSep);

        String result = sb.toString();

        System.out.println(result);

        return result;
    }
}