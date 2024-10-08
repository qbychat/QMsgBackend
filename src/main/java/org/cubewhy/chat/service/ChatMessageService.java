package org.cubewhy.chat.service;

import com.google.firebase.messaging.FirebaseMessagingException;
import org.cubewhy.chat.entity.Account;
import org.cubewhy.chat.entity.ChatMessage;
import org.cubewhy.chat.entity.dto.ChatMessageDTO;
import org.springframework.data.domain.Page;

import java.io.IOException;

public interface ChatMessageService {
    ChatMessage saveMessage(ChatMessageDTO message, Account sender) throws IOException, FirebaseMessagingException;
    Page<ChatMessage> getMessagesByChannel(long channel, int page, int size);

    Page<ChatMessage> getMessagesBySenderAndChannel(long sender, long channel, int page, int size);

    void deleteAllByChannelId(Long channelId);

    ChatMessage findMessageById(long messageId);

    void deleteMessage(ChatMessage message);
}
