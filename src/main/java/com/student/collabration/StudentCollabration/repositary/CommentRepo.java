package com.student.collabration.StudentCollabration.repositary;

import com.student.collabration.StudentCollabration.modal.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CommentRepo extends JpaRepository<Comment,Long> {
//    Optional<Comment> findByPostId(Long i);
//    List<Comment> findByPostId(Long postId);
}
