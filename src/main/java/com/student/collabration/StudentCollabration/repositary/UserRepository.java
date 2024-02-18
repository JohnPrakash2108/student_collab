package com.student.collabration.StudentCollabration.repositary;

import com.student.collabration.StudentCollabration.modal.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<Users,Long> {
    Optional<Users> findByEmailOrUserName(String email, String userName);

    boolean existsByEmail(String email);

    boolean existsByUserName(String userName);
}
