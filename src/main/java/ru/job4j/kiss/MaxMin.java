package ru.job4j.kiss;

import java.util.Comparator;
import java.util.List;
import java.util.function.Predicate;

public class MaxMin {
    public <T> T max(List<T> value, Comparator<T> comparator) {
        Predicate<Integer> predicate = el -> el >= 0;
        return getPredicateResult(value, comparator, predicate);
    }

    public <T> T min(List<T> value, Comparator<T> comparator) {
        Predicate<Integer> predicate = el -> el <= 0;
        return getPredicateResult(value, comparator, predicate);
    }

    private <T> T getPredicateResult(List<T> value, Comparator<T> comparator, Predicate<Integer> predicate) {
        T result = value.get(0);
        for (T t : value) {
            result = predicate.test(comparator.compare(result, t)) ? result : t;
        }
        return result;
    }
}
