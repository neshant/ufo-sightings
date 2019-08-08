package com.project.ufosighting

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController


@RestController
@RequestMapping("/ufo-sightings")
class UfoSightingController(private val ufoSightingService: UfoSightingService) {
    @GetMapping("/count")
    fun countSightings(): ResponseEntity<CountUfoSighting> {
        return ResponseEntity.ok(ufoSightingService.countSightings())
    }

    @GetMapping("/unique-shape")
    fun uniqueShapeCount(): ResponseEntity<CountUfoSighting> {
        return ResponseEntity.ok(ufoSightingService.uniqueShapeCount())
    }

    @GetMapping("/vulnerable-city")
    fun vulnerableCity(@RequestParam("count") count: Int): ResponseEntity<List<CityCount>> {
        return ResponseEntity.ok(ufoSightingService.vulnerableCity(count))
    }

    @GetMapping
    fun closestSightings(
            @RequestParam("latitude") latitude: Double,
            @RequestParam("longitude") longitude: Double,
            @RequestParam("count") count: Int
    ): ResponseEntity<List<Sightings>> {
        return ResponseEntity.ok(ufoSightingService.closestSightings(latitude, longitude, count))
    }
}