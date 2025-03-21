package com.project.secure.capita.repository;

import com.project.secure.capita.domain.Role;
import com.project.secure.capita.exception.ApiException;

import java.util.Collection;

public interface RoleRepository<T extends Role> {

    T create(T data) throws ApiException;

    T update(T data);

    Boolean delete(Long id);

    T get(Long id);

    Collection<T> list(int page, int pageSize);

    void addRoleToUser(Long userId, String roleName) throws ApiException;

    Role getRoleByUserId(Long userId);

    Role getRoleByUserEmail(String email);

    void updateUserRole(Long userId, String roleName);

}
