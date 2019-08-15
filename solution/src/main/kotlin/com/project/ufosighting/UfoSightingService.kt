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

    fun vulnerableCity(count: Int): CitySightings {
        return CitySightings(ufoSightingRepository.vulnerableCity(count))
    }

    fun closestSightings(latitude: Double, longitude: Double, cnt: Int): SightingDistance {
        return SightingDistance(ufoSightingRepository.closestSightings(latitude, longitude, cnt))
    }

    fun createUfoSighting(ufoSighting: UfoSighting): UfoSighting {
        return ufoSightingRepository.save(ufoSighting)
    }

    fun updateUfoSighting(id: Long, ufoSighting: UfoSighting): UfoSighting? {
        return sightingExists(id).let {
            ufoSightingRepository.save(ufoSighting.copy(id = it.id, deleted = it.deleted))
        }
    }

    fun deleteUfoSighting(id: Long) {
        sightingExists(id).let {
            ufoSightingRepository.save(it.copy(deleted = true))
        }
    }

    fun sightingExists(id: Long): UfoSighting {
        return ufoSightingRepository.findById(id)
                .orElseThrow { ResourceNotFound() }
    }

    fun getUfoSightingById(id: Long): UfoSighting {
        return sightingExists(id)
    }
}

data class CountUfoSighting(val count: Long?)

data class CitySightings(val sightings: List<CityCount>)

data class SightingDistance(val sightings: List<Sightings>)