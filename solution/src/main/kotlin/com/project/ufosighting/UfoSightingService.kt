package com.project.ufosighting

import org.springframework.stereotype.Service

@Service
class UfoSightingService(private val ufoSightingRepository: UfoSightingRepository) {

    fun countSightings(): CountUfoSighting {
        return CountUfoSighting(ufoSightingRepository.count())
    }

    fun uniqueShapeCount(): CountUfoSighting {
        return CountUfoSighting(ufoSightingRepository.uniqueShapeCount())
    }

    fun vulnerableCity(count: Int): List<CityCount> {
        return ufoSightingRepository.vulnerableCity(count)
    }
}

data class CountUfoSighting(
        val count: Long?
)