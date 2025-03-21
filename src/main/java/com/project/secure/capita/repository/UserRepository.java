package com.project.secure.capita.repository;

import com.project.secure.capita.domain.User;
import com.project.secure.capita.exception.ApiException;

import java.util.Collection;

public interface UserRepository<T extends User> {

    T create(T data) throws ApiException;

    T update(T data);

    Boolean delete(Long id);

    T get(Long id);

    Collection<T> list(int page, int pageSize);
}
