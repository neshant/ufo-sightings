package com.project.ufosighting

import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.CrudRepository

interface UfoSightingRepository : CrudRepository<UfoSighting, Long> {

    @Query("""SELECT COUNT(distinct shape) FROM ufo_sighting WHERE shape <> ''""", nativeQuery = true)
    fun uniqueShapeCount(): Long
}