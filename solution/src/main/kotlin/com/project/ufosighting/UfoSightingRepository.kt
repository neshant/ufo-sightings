package com.project.ufosighting

import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.CrudRepository
import org.springframework.data.repository.query.Param

interface UfoSightingRepository : CrudRepository<UfoSighting, Long> {

    @Query("""SELECT COUNT(distinct shape) FROM ufo_sighting WHERE shape <> ''""", nativeQuery = true)
    fun uniqueShapeCount(): Long

    @Query(
            """
                SELECT city , COUNT(*) as count from ufo_sighting
                GROUP BY city
                ORDER BY count desc
                LIMIT :cnt
                 """, nativeQuery = true
    )
    fun vulnerableCity(@Param("cnt") cnt: Int): List<CityCount>
}

interface CityCount {
    val count: Long
    val city: String
}