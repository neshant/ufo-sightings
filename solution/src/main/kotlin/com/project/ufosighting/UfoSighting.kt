package com.project.ufosighting

import com.fasterxml.jackson.annotation.JsonProperty
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ResponseStatus
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table(name = "ufo_sighting")
data class UfoSighting(
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @JsonProperty(access = JsonProperty.Access.READ_ONLY)  // limits only read value in post
        val id: Long?,
        val occurred_at: String?,
        val city: String?,
        val state: String?,
        val country: String?,
        val shape: String?,
        val duration_seconds: String?,
        val duration_text: String?,
        val description: String?,
        val reported_on: String?,
        val latitude: Double,
        val longitude: Double,
        @JsonProperty(access = JsonProperty.Access.READ_ONLY)  // limits only read value in post
        val deleted: Boolean
)

@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "Resource Not Found")
class ResourceNotFound : RuntimeException()