package com.jsontoon.jtoon.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

class ToonConverterServiceTest {

    private ToonConverterService toonService;

    @BeforeEach
    void setUp() {
        toonService = new ToonConverterService();
    }

    @Test
    @DisplayName("Deve converter um Objeto Java (Map/List) para o formato TOON")
    void shouldConvertObjectToToon() {
        
        Map<String, Object> user1 = Map.of("id", 1, "name", "Alice", "role", "admin");
        Map<String, Object> user2 = Map.of("id", 2, "name", "Bob", "role", "user");
        Map<String, Object> data = Map.of("users", List.of(user1, user2));

        String resultToon = toonService.toToon(data);

        assertThat(resultToon).isNotNull();
        assertThat(resultToon).contains("users[2]");
        assertThat(resultToon).contains("id");
        assertThat(resultToon).contains("name");
        assertThat(resultToon).contains("role");
        assertThat(resultToon).contains("Alice");
        assertThat(resultToon).contains("admin");
        assertThat(resultToon).contains("Bob");
        assertThat(resultToon).contains("user");
    }

    @Test
    @DisplayName("Deve converter uma String JSON bruta para o formato TOON")
    void shouldConvertRawJsonToToon() {

        String jsonInput = """
            {
              "products": [
                { "sku": "A100", "price": 10.5 },
                { "sku": "B200", "price": 20.0 }
              ]
            }
            """;

        String resultToon = toonService.jsonToToon(jsonInput);

        assertThat(resultToon).isNotNull();
        assertThat(resultToon).contains("products[2]{sku,price}");
        assertThat(resultToon).contains("A100,10.5");
    }

    @Test
    @DisplayName("Deve converter TOON de volta para JSON válido")
    void shouldConvertToonToJson() {
     
        String toonInput = """
            users[2]{id,name}:
              1,Alice
              2,Bob
            """;

        String resultJson = toonService.toonToJson(toonInput);

        assertThat(resultJson).isNotNull();
        assertThat(resultJson).contains("\"users\":");
        assertThat(resultJson).contains("\"Alice\"");
        assertThat(resultJson).contains("[");
        assertThat(resultJson).contains("{");
    }
    
    @Test
    @DisplayName("Deve lidar com estruturas vazias corretamente")
    void shouldHandleEmptyStructures() {
        Map<String, Object> emptyData = Map.of();
        
        String result = toonService.toToon(emptyData);
        
        assertThat(result).isNotNull();
        assertThat(result.trim()).isIn("{}", ""); 
    }
}