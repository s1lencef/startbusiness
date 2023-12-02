package ru.studentproject.startbusiness.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.studentproject.startbusiness.models.ChatMessage;
import ru.studentproject.startbusiness.models.MessageStatus;

import java.util.List;

public interface ChatMessageRepository extends JpaRepository<ChatMessage, Long> {
    long countBySenderIdAndRecipientIdAndStatus(long senderId, long recipientId, MessageStatus status);
    List<ChatMessage> findByChatId(long chatId);
}
