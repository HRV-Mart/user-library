package com.hrv.mart.userlibrary

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.kafka.core.reactive.ReactiveKafkaProducerTemplate
import org.springframework.kafka.support.KafkaHeaders
import org.springframework.messaging.support.MessageBuilder
import org.springframework.stereotype.Service

@Service
class UserProducer (
    @Autowired
    private val kafkaTemplate: ReactiveKafkaProducerTemplate<String, User>
) {
    fun createUser(user: User) =
        sendMessage(user, UserTopics.createUserTopic)
    fun deleteUser(userId: String) =
        sendMessage(userId, UserTopics.deleteUserTopic)
    private fun <T : Any> sendMessage(data: T, topic: String) =
        kafkaTemplate.send(topic, MessageBuilder
            .withPayload(data)
            .setHeader(KafkaHeaders.TOPIC, topic)
            .build()
        )
}