package com.project.ufosighting

import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table(name = "ufo_sighting")
data class UfoSighting(
    @Id
    val id: Long,
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
    val longitude: Double
)