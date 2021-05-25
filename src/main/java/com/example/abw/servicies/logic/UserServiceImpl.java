package com.example.abw.servicies.logic;

import com.example.abw.AppProperties;
import com.example.abw.entities.user.Role;
import com.example.abw.entities.user.User;
import com.example.abw.exception.user.EmailIsAlreadyExistException;
import com.example.abw.exception.user.UserOrPasswordException;
import com.example.abw.exception.validation.ValidationException;
import com.example.abw.model.pageable.PageableParams;
import com.example.abw.repositories.pagination.user.UserPaginationRepository;
import com.example.abw.repositories.user.RoleRepository;
import com.example.abw.repositories.user.UserRepository;
import com.example.abw.servicies.UserService;
import com.example.abw.utils.pageable.PageableUtil;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Service
public class UserServiceImpl extends GenericServiceImpl<User> implements UserService {
    private final UserRepository userRepository;
    private final UserPaginationRepository userPaginationRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final AppProperties appProperties;

    public UserServiceImpl(UserRepository userRepository, UserPaginationRepository userPaginationRepository,
                           RoleRepository roleRepository, PasswordEncoder passwordEncoderBC,
                           AppProperties appProperties) {
        super(userRepository, User.class);
        this.userRepository = userRepository;
        this.userPaginationRepository = userPaginationRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoderBC;
        this.appProperties = appProperties;
    }

    @Override
    public List<User> findAll(PageableParams pageableParams) throws ValidationException {
        Pageable pageable = PageableUtil.getPageable(pageableParams);
        return findAllUsers(pageable);
    }

    @Override
    public User saveUser(User user) throws EmailIsAlreadyExistException {
        User checkUser = userRepository.findByEmail(user.getEmail());
        if (checkUser == null) {
            Role role = roleRepository.findByRole(appProperties.getDefaultRole());
            user.setRole(role);
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            return userRepository.save(user);
        } else throw new EmailIsAlreadyExistException("email is already exist");
    }

    @Override
    public User findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public User findByEmailAndPassword(String email, String password) throws UserOrPasswordException {
        User user = userRepository.findByEmail(email);
        if (user != null) {
            if (passwordEncoder.matches(password, user.getPassword())) return user;
        }
        throw new UserOrPasswordException("wrong email or password");
    }

    private List<User> findAllUsers(Pageable pageable) {
        List<User> result = new ArrayList<>();
        Iterable<User> iterable = userPaginationRepository.findAll(pageable);
        Iterator<User> iterator = iterable.iterator();
        if (iterator.hasNext()) iterator.forEachRemaining(result::add);
        return result;
    }
}
