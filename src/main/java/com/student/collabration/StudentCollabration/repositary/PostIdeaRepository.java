package com.student.collabration.StudentCollabration.repositary;

import com.student.collabration.StudentCollabration.dto.PostLikeId;
import com.student.collabration.StudentCollabration.modal.PostIdea;
import com.student.collabration.StudentCollabration.modal.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostIdeaRepository extends JpaRepository<PostIdea,Long> {


    boolean existsById(PostLikeId postLikeId);

    List<PostIdea> findByUserAndDeletedOrderByCreatedAtDesc(Users user, boolean b);

    List<PostIdea> findAllByDeletedFalseOrderByCreatedAtDesc();
}
