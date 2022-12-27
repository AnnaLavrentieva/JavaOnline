package com.lavrentieva.repository;


public interface Repository<T, S, U> {
    void save(T t);

    T[] getAll();

    T getById(S s);

    void delete(S s);

    default void updateColor(S s, U u) {
    }

}
