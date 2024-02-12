package com.student.collabration.StudentCollabration.repositary;

import com.student.collabration.StudentCollabration.dto.PostIdeaDto;
import com.student.collabration.StudentCollabration.modal.PostIdea;
import com.student.collabration.StudentCollabration.modal.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostIdeaRepository extends JpaRepository<PostIdea,Long> {

    List<PostIdea> findByUserAndDeleted(Users user, boolean b);
}
