package com.epicode.LastBuildWeek.controller;

import com.epicode.LastBuildWeek.service.CsvService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/csv")
public class CsvController {

    @Autowired CsvService csvService;


    @PostMapping("/provinces")
    public ResponseEntity<String> uploadProvinces(@RequestParam MultipartFile file){
        try {
            csvService.importProvinces(file);
            return ResponseEntity.ok("PROVINCE AGGIUNTE AL DB!☑️");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Errore nell'aggiunta di Province nel db!👎" + e.getMessage());
        }
    }
}
