package com.example.abw.servicies.pagination.user.simple.logic;

import com.example.abw.AppProperties;
import com.example.abw.entities.user.User;
import com.example.abw.repositories.pagination.user.UserPaginationRepository;
import com.example.abw.servicies.pagination.user.simple.UserPaginationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Service
public class UserPaginationServiceImpl implements UserPaginationService {
    @Autowired
    private UserPaginationRepository userPaginationRepository;
    @Autowired
    private AppProperties appProperties;

    @Override
    public List<User> defaultUserPagination(int page) {
        Pageable pageable = PageRequest.of(page, appProperties.getPageSize(),
                Sort.by("name").descending());
        return getResult(pageable);
    }

    @Override
    public List<User> defaultUserPaginationWithSize(int page, int size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by("name").descending());
        return getResult(pageable);
    }

    private List<User> getResult(Pageable pageable) {
        List<User> result = new ArrayList<>();
        Iterable<User> iterable = userPaginationRepository.findAll(pageable);
        Iterator<User> iterator = iterable.iterator();
        if (iterator.hasNext()) iterator.forEachRemaining(result::add);
        return result;
    }
}
