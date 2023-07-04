package com.hrv.mart.userlibrary.repository

import com.hrv.mart.apicall.APICaller
import com.hrv.mart.userlibrary.model.User
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.http.server.reactive.ServerHttpResponse
import org.springframework.stereotype.Repository
import org.springframework.web.reactive.function.client.WebClient

@Repository
class UserRepository (
    @Autowired
    private val webClientBuilder: WebClient.Builder,
    @Value("\${user.url}")
    private val userURL: String
)
{
    fun getUserDetails(userId: String, response: ServerHttpResponse) =
        APICaller(webClientBuilder).
                getData(
                    "${userURL}/${userId}",
                    User::class.java, response
                )
}