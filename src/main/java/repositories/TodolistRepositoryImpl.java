package repositories;

import entities.Todolist;

public class TodolistRepositoryImpl implements TodolistRepository{

    Todolist[] data = new Todolist[10];

    @Override
    public Todolist[] getAll() {
        return data;
    }

    @Override
    public void add(Todolist todo) {
        resizeIfFull();

        for(int i = 0; i < data.length; i++) {
            if(data[i] == null) {
                data[i] = todo;
                break;
            }
        }
    }

    @Override
    public boolean remove(Integer index) {
        if ((index - 1) >= data.length) {
            return false;
        } else if (data[index - 1] == null) {
            return false;
        } else {
            for (int i = (index - 1); i < data.length; i++) {
                if (i == (data.length - 1)) {
                    data[i] = null;
                } else {
                    data[i] = data[i + 1];
                }
            }
            return true;
        }   
     }

    private boolean isFull(){
        if(data[data.length-1] != null) {
            return true;
        }
        return false;
    }

    private void resizeIfFull() {
        if(isFull()) {
            var temp = data;
            data = new Todolist[data.length*2];

            for(int i = 0; i < temp.length; i++){
                data[i] = temp[i];
            }
        }
    }
}
