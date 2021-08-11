package repositories;

import entities.Todolist;

public interface TodolistRepository {
    Todolist[] getAll();

    void add(Todolist todo);

    boolean remove(Integer index);
}
