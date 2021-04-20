package com.example.abw.servicies.logic;

import com.example.abw.entities.user.User;
import com.example.abw.model.pageable.PageableParams;
import com.example.abw.repositories.pagination.user.UserPaginationRepository;
import com.example.abw.repositories.user.UserRepository;
import com.example.abw.servicies.UserService;
import com.example.abw.utils.pageable.PageableUtil;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Service
public class UserServiceImpl extends GenericServiceImpl<User> implements UserService {
    private final UserRepository userRepository;
    private final UserPaginationRepository userPaginationRepository;

    public UserServiceImpl(UserRepository userRepository, UserPaginationRepository userPaginationRepository) {
        super(userRepository, User.class);
        this.userRepository = userRepository;
        this.userPaginationRepository = userPaginationRepository;
    }

    @Override
    public List<User> findAll(PageableParams pageableParams) {
        Pageable pageable = PageableUtil.getPageable(pageableParams);
        return findAllUsers(pageable);
    }

    private List<User> findAllUsers(Pageable pageable) {
        List<User> result = new ArrayList<>();
        Iterable<User> iterable = userPaginationRepository.findAll(pageable);
        Iterator<User> iterator = iterable.iterator();
        if (iterator.hasNext()) iterator.forEachRemaining(result::add);
        return result;
    }
}
