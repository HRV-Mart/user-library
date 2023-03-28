package com.hrv.mart.userlibrary

import org.springframework.kafka.core.KafkaTemplate
import org.springframework.kafka.support.KafkaHeaders
import org.springframework.messaging.support.MessageBuilder
import org.springframework.stereotype.Service

@Service
class UserProducer (
    private val kafkaTemplate: KafkaTemplate<String, User>
) {
    fun createUser(user: User) {
        val message = MessageBuilder
            .withPayload(user)
            .setHeader(KafkaHeaders.TOPIC, UserTopics.createUserTopic)
            .build()
        kafkaTemplate.send(message)
    }
}