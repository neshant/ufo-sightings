package com.project.ufosighting

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController


@RestController
@RequestMapping("/ufo-sightings")
class UfoSightingController(private val ufoSightingService: UfoSightingService) {
    @GetMapping("/count")
    fun countSightings(): ResponseEntity<CountUfoSighting> {
        return ResponseEntity.ok(ufoSightingService.countSightings())
    }
}