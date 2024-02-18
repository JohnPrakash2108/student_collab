package com.student.collabration.StudentCollabration.repositary;

import com.student.collabration.StudentCollabration.modal.Chat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ChatRepo extends JpaRepository<Chat,Long> {
    @Query(value="SELECT * FROM chat where (sender_id = ?1 AND receiver_id=?2) or (receiver_id=?1 AND sender_id=?2)",nativeQuery = true)
    List<Chat> findBySenderIdAndReceiverIdOrReceiverIdAndSenderIdOrderByCreatedAtDesc(long senderId, long receiverId,long receiverId1,long senderId1);
}
