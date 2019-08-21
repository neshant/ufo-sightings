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

    @Query(
            """
                SELECT id, occurred_at ,city, state, country, shape, duration_seconds, duration_text, description, reported_on, latitude, longitude,
                earth_distance(ll_to_earth(latitude, longitude), ll_to_earth(:lat, :lng)) as distance
                FROM ufo_sighting mm
                GROUP BY id, occurred_at  , city, state, country, shape, duration_seconds, duration_text, description, reported_on, latitude, longitude, distance
                ORDER BY distance ASC
                LIMIT :cnt   
                """, nativeQuery = true
    )
    fun closestSightings(
            @Param("lat") lat: Double,
            @Param("lng") lng: Double,
            @Param("cnt") cnt: Int
    ): List<Sightings>

    @Query(
            """
                SELECT shape,count(shape)
                FROM ufo_sighting
                WHERE duration_seconds > 42
                AND earth_distance(ll_to_earth(latitude, longitude), ll_to_earth(46.5476, -87.3956)) / 1000 < 120
                GROUP BY shape
                ORDER BY count(shape) desc ;
                 """, nativeQuery = true
    )
    fun successFulPotentialHits(): List<NearSightingShapeCount>
}

interface NearSightingShapeCount {
    val shape: String
    val count: Int
}

interface CityCount {
    val count: Long
    val city: String
}

interface Sightings {
    val id: Long
    val occurred_at: String?
    val city: String
    val state: String
    val country: String
    val shape: String
    val duration_seconds: String
    val duration_text: String
    val description: String
    val reported_on: String
    val latitude: Double
    val longitude: Double
    val distance: Float
}

