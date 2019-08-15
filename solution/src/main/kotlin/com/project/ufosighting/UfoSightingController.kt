package com.project.ufosighting

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import javax.validation.Valid


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
    fun vulnerableCity(@RequestParam("count") count: Int): ResponseEntity<CitySightings> {
        return ResponseEntity.ok(ufoSightingService.vulnerableCity(count))
    }

    @GetMapping
    fun closestSightings(
            @RequestParam("latitude") latitude: Double,
            @RequestParam("longitude") longitude: Double,
            @RequestParam("count") count: Int
    ): ResponseEntity<SightingDistance> {
        return ResponseEntity.ok(ufoSightingService.closestSightings(latitude, longitude, count))
    }

    @GetMapping("/{id}")
    fun getUfoSightingById(@PathVariable id: Long): ResponseEntity<UfoSighting> {
        return ResponseEntity.ok(ufoSightingService.getUfoSightingById(id))
    }

    @PostMapping
    fun createUfoSighting(@Valid @RequestBody ufoSighting: UfoSighting): ResponseEntity<UfoSighting> {
        return ResponseEntity.ok(ufoSightingService.createUfoSighting(ufoSighting))
    }

    @PutMapping("/{id}")
    fun updateUfoSighting(
            @PathVariable id: Long,
            @RequestBody ufoSighting: UfoSighting
    ): ResponseEntity<UfoSighting> {
        return ufoSightingService.updateUfoSighting(id, ufoSighting)?.let {
            ResponseEntity.ok(it)
        } ?: ResponseEntity.notFound().build()
    }

    @DeleteMapping("/{id}")
    fun deleteUfoSighting(@PathVariable("id") id: Long): ResponseEntity<Unit> {
        ufoSightingService.deleteUfoSighting(id)
        return ResponseEntity.noContent().build()
    }
}