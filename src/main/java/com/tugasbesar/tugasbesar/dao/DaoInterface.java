package com.tugasbesar.tugasbesar.dao;

import java.util.List;

public interface DaoInterface<T> {
    List<T> getData();
    int addData(T data);
    int delData(T data);
    int updateData(T data);
}
