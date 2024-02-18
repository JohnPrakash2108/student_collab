package com.student.collabration.StudentCollabration.repositary;

import com.student.collabration.StudentCollabration.modal.Comment;
import com.student.collabration.StudentCollabration.modal.PostIdea;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepo extends JpaRepository<Comment,Long> {
    List<Comment> findByPostIdeaOrderByIdDesc(PostIdea postRating);
}
