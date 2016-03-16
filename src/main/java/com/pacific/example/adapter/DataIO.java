package com.pacific.example.adapter;

import java.util.ArrayList;
import java.util.List;

interface DataIO<T> {

    void add(T elem);

    void addAt(int location, T elem);

    void addAll(List<T> elem);

    void addAllAt(int location, List<T> list);

    void remove(T elem);

    void remove(List<T> list);

    void removeAt(int index);

    void clear();

    void replace(T oldElem, T newElem);

    void replaceAt(int index, T elem);

    void replaceAll(List<T> elem);

    ArrayList<T> getAll();

    T get(int position);

    int getSize();

    boolean contains(T elem);
}
