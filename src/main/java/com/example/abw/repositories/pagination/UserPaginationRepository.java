package com.example.abw.repositories.pagination;


import com.example.abw.entities.user.User;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserPaginationRepository extends PagingAndSortingRepository<User,Long> {
}
