package cn.frequent.initialization.p2;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Supplier;

/**
 * Created by leslie on 2020/4/24.
 */
public class InstanceBuilder<T, P1, P2, P3> {

    private final Supplier<T> instantiator;
    private List<Consumer>    modifiers = new ArrayList<>();

    public InstanceBuilder(Supplier instantiator){
        this.instantiator = instantiator;
    }

    public static InstanceBuilder of(Supplier instantiator) {
        return new InstanceBuilder<>(instantiator);
    }

    public InstanceBuilder with(Consumer1 consumer, P1 p1) {
        Consumer c = instance -> consumer.accept(instance, p1);
        modifiers.add(c);
        return this;
    }

    public InstanceBuilder with(Consumer2 consumer, P1 p1, P2 p2) {
        Consumer c = instance -> consumer.accept(instance, p1, p2);
        modifiers.add(c);
        return this;
    }

    public InstanceBuilder with(Consumer3 consumer, P1 p1, P2 p2, P3 p3) {
        Consumer c = instance -> consumer.accept(instance, p1, p2, p3);
        modifiers.add(c);
        return this;
    }

    public T build() {
        T value = instantiator.get();
        modifiers.forEach(modifier -> modifier.accept(value));
        modifiers.clear();
        return value;
    }

    /**
     * 1 参数 Consumer
     */
    @FunctionalInterface
    public interface Consumer1<T, P1> {

        void accept(T t, P1 p1);
    }

    /**
     * 2 参数 Consumer
     */
    @FunctionalInterface
    public interface Consumer2<T, P1, P2> {

        void accept(T t, P1 p1, P2 p2);
    }

    /**
     * 3 参数 Consumer
     */
    @FunctionalInterface
    public interface Consumer3<T, P1, P2, P3> {

        void accept(T t, P1 p1, P2 p2, P3 p3);
    }
}
