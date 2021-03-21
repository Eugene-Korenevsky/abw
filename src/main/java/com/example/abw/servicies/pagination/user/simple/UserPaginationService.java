package com.example.abw.servicies.pagination.user.simple;

import com.example.abw.entities.user.User;

import java.util.List;

public interface UserPaginationService {
    public List<User> defaultUserPagination(int page);

    public List<User> defaultUserPaginationWithSize(int page, int size);
}
