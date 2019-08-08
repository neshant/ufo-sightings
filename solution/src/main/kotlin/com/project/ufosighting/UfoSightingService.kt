package com.project.ufosighting

import org.springframework.stereotype.Service

@Service
class UfoSightingService(private val ufoSightingRepository: UfoSightingRepository) {
    fun countSightings(): CountUfoSighting {
        return CountUfoSighting(ufoSightingRepository.count())
    }
}

data class CountUfoSighting(
        val count: Long?
)