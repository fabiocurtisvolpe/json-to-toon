package com.jsontoon.jtoon.controller;

import java.util.Map;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jsontoon.jtoon.service.ToonConverterService;

@RestController
@RequestMapping("/api/toon")
public class ToonController {

    private final ToonConverterService toonService;

    public ToonController(ToonConverterService toonService) {
        this.toonService = toonService;
    }

    @PostMapping("/convert")
    public String convertJsonToToon(@RequestBody Map<String, Object> jsonData) {
        // O Spring faz o bind do JSON para o Map automaticamente
        // O serviço converte o Map para o formato TOON
        return toonService.toToon(jsonData);
    }
    
    @PostMapping("/convert-raw")
    public String convertRawJson(@RequestBody String rawJson) {
        return toonService.jsonToToon(rawJson);
    }

}
