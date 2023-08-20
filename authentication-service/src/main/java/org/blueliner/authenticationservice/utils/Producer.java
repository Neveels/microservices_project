package org.blueliner.authenticationservice.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.blueliner.authenticationservice.model.UserCredential;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class Producer {

    @Value("${topic.name}")
    private String orderTopic;

    private final ObjectMapper objectMapper;
    private final KafkaTemplate<String, String> kafkaTemplate;

    public void sendMessage(UserCredential registerRequest) throws JsonProcessingException {
        String userCredentialsAsMessage = objectMapper.writeValueAsString(registerRequest);
        kafkaTemplate.send(orderTopic, userCredentialsAsMessage);
        log.info("food order produced {}", userCredentialsAsMessage);
    }

}
