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
        sendMessage(user, UserTopics.createUserTopic)
    }
    fun deleteUser(userId: String) {
        sendMessage(userId, UserTopics.deleteUserTopic)
    }
    private fun <T : Any> sendMessage(data: T, topic: String) {
        val message = MessageBuilder
            .withPayload(data)
            .setHeader(KafkaHeaders.TOPIC, topic)
            .build()
        kafkaTemplate.send(message)
    }
}