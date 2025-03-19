package org.example.calorieTracker.web.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;

@RestController
@RequestMapping("/api/v1/reports")
public class ReportController {

    //todo
    @GetMapping("/daily/{userId}/{date}")
    public ResponseEntity<String> getDailyReport(@PathVariable Long userId,
                                                 @PathVariable LocalDate date){
        return ResponseEntity.ok("Отчет за день");
    }

    @GetMapping("/calorie-check/{userId}/{date}")
    public ResponseEntity<String> calorieCheck(@PathVariable Long userId,
                                               @PathVariable LocalDate date){
        return ResponseEntity.ok("Проверка...");
    }

    @GetMapping("/nutrition-history/{userId}/")
    public ResponseEntity<String> getHistory(@PathVariable Long userId){
        return ResponseEntity.ok("История питания");
    }
}
