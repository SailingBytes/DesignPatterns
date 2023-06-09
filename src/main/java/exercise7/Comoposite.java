package exercise7;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Consumer;
import java.util.stream.Stream;

interface ValueContainer extends Iterable<Integer> { }

class SingleValue implements ValueContainer {
    public int value;

    // please leave this constructor as-is
    public SingleValue(int value) {
        this.value = value;
    }

    @Override
    public void forEach(Consumer<? super Integer> action) {
        action.accept(value);
    }

    @Override
    public Spliterator<Integer> spliterator() {
        return Collections.singleton(value).spliterator();
    }

    @Override
    public Iterator<Integer> iterator() {
        return Collections.singleton(value).iterator();
    }

//    @Override
//    public void forEach(Consumer<? super Integer> action) {
//        ValueContainer.super.forEach(action);
//    }
//
//    @Override
//    public Spliterator<Integer> spliterator() {
//        return ValueContainer.super.spliterator();
//    }
//
//    @Override
//    public Iterator<Integer> iterator() {
//        return null;
//    }
}

class ManyValues extends ArrayList<Integer> implements ValueContainer {
}


class MyList extends ArrayList<ValueContainer> {
    // please leave this constructor as-is
    public MyList(Collection<? extends ValueContainer> c) {
        super(c);
    }


//    public int sum() {
//        int result = 0;
//        for(ValueContainer c: this) {
//            for(int i : c) {
//                result += i;
//            }
//        }
//
//        return result;
//    }

    public int sum() {
        AtomicInteger result = new AtomicInteger();

        this.stream().forEach(vc ->
                vc.forEach(result::addAndGet));

        return result.get();
    }
}
