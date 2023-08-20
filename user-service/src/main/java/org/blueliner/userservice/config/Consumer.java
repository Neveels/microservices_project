package org.blueliner.userservice.config;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.blueliner.userservice.dto.UserCredentialDto;
import org.blueliner.userservice.service.UserService;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class Consumer {

    private static final String orderTopic = "${topic.name}";
    private final ObjectMapper objectMapper;
    private final UserService userService;

    @KafkaListener(topics = orderTopic)
    public void consumeMessage(String message) throws JsonProcessingException {
        log.info("message consumed {}", message);

        UserCredentialDto userCredentialDto = objectMapper.readValue(message, UserCredentialDto.class);
        userService.saveUserCredentials(userCredentialDto);
    }

}