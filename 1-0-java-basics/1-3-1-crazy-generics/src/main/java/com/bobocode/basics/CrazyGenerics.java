package com.bobocode.basics;

import com.bobocode.basics.util.BaseEntity;
import com.bobocode.util.ExerciseNotCompletedException;
import lombok.Data;

import java.io.Serializable;
import java.util.*;
import java.util.function.Predicate;

/**
 * {@link CrazyGenerics} is an exercise class. It consists of classes, interfaces and methods that should be updated
 * using generics.
 * <p>
 * TODO: go step by step from top to bottom. Read the java doc, write code and run CrazyGenericsTest to verify your impl
 * <p>
 * Hint: in some cases you will need to refactor the code, like replace {@link Object} with a generic type. In order
 * cases you will need to add new fields, create new classes, or add new methods. Always try to read java doc and update
 * the code according to it.
 */
public class CrazyGenerics {
    /**
     * {@link Sourced} is a container class that allows storing any object along with the source of that data.
     * The value type can be specified by a type parameter "T".
     *
     * @param <T> – value type
     */
    @Data
    public static class Sourced<T> {
        private T value;
        private String source;
    }

    /**
     * {@link Limited} is a container class that allows storing an actual value along with possible min and max values.
     * It is a special form of triple. All three values have a generic type that should be a subclass of {@link Number}.
     *
     * @param <T> – actual, min, and max type
     */
    @Data
    public static class Limited<T extends Number> {
        private final T actual;
        private final T min;
        private final T max;
    }

    /**
     * {@link Converter} interface declares a typical contract of a converter. It works with two independent generic types.
     * It defines a convert method which accepts one parameter of one type and returns a converted result of another type.
     *
     * @param <T> – source object type
     * @param <R> - converted result type
     */
    public interface Converter<T, R> {
        R convert(T source);
    }

    /**
     * {@link MaxHolder} is a container class that keeps track of the maximum value only. It works with comparable objects
     * and allows you to put new values. Every time you put a value, it is stored only if the new value is greater
     * than the current max.
     *
     * @param <T> – value type
     */
    public static class MaxHolder<T extends Comparable<T>> {
        private T max;

        public MaxHolder(T max) {
            this.max = max;
        }

        public void put(T val) {
            if (max == null || val.compareTo(max) > 0) {
                max = val;
            }
        }

        public T getMax() {
            return max;
        }
    }

    /**
     * {@link StrictProcessor} defines a contract of a processor that can process only objects that are {@link Serializable}
     * and {@link Comparable}.
     *
     * @param <T> – the type of objects that can be processed
     */
    interface StrictProcessor<T extends Serializable & Comparable<T>> {
        void process(T obj);
    }

    /**
     * {@link CollectionRepository} defines a contract of a runtime store for entities based on any {@link Collection}.
     * It has methods that allow saving a new entity and getting the whole collection.
     *
     * @param <T> – a type of the entity that should be a subclass of {@link BaseEntity}
     * @param <C> – a type of any collection
     */
    interface CollectionRepository<T extends BaseEntity, C extends Collection<T>> {
        void save(T entity);

        C getEntityCollection();
    }

    /**
     * {@link ListRepository} extends {@link CollectionRepository} but specifies the underlying collection as
     * {@link java.util.List}.
     *
     * @param <T> – a type of the entity that should be a subclass of {@link BaseEntity}
     */
    interface ListRepository<T extends BaseEntity> extends CollectionRepository<T, List<T>> {
    }

    /**
     * {@link ComparableCollection} is a {@link Collection} that can be compared by size. It extends a {@link Collection}
     * interface and {@link Comparable} interface and provides a default implementation of a compareTo method that
     * compares collections sizes.
     *
     * @param <E> a type of collection elements
     */
    interface ComparableCollection<E> extends Collection<E>, Comparable<Collection<?>> {
        @Override
        default int compareTo(Collection<?> other) {
            return Integer.compare(this.size(), other.size());
        }
    }

    /**
     * {@link CollectionUtil} is a utility class that provides various generic helper methods.
     */
    static class CollectionUtil {
        static final Comparator<BaseEntity> CREATED_ON_COMPARATOR = Comparator.comparing(BaseEntity::getCreatedOn);

        public static <T> void print(List<T> list) {
            list.forEach(element -> System.out.println(" – " + element));
        }

        public static boolean hasNewEntities(Collection<? extends BaseEntity> entities) {
            return entities.stream().anyMatch(entity -> entity.getUuid() == null);
        }

        public static <T extends BaseEntity> boolean isValidCollection(Collection<T> entities, Predicate<T> validationPredicate) {
            return entities.stream().allMatch(validationPredicate);
        }

        public static <T extends BaseEntity> boolean hasDuplicates(List<T> entities, T targetEntity) {
            return entities.stream().filter(e -> e.getUuid().equals(targetEntity.getUuid())).count() > 1;
        }

        public static <T> Optional<T> findMax(Iterable<T> elements, Comparator<T> comparator) {
            T max = null;
            for (T element : elements) {
                if (max == null || comparator.compare(element, max) > 0) {
                    max = element;
                }
            }
            return Optional.ofNullable(max);
        }

        public static <T extends BaseEntity> T findMostRecentlyCreatedEntity(Collection<T> entities) {
            return findMax(entities, CREATED_ON_COMPARATOR).orElseThrow(NoSuchElementException::new);
        }

        public static void swap(List<?> elements, int i, int j) {
            Objects.checkIndex(i, elements.size());
            Objects.checkIndex(j, elements.size());
            swapHelper(elements, i, j);
        }

        @SuppressWarnings("unchecked")
        private static <T> void swapHelper(List<T> list, int i, int j) {
            T temp = list.get(i);
            list.set(i, list.get(j));
            list.set(j, temp);
        }
    }
}
