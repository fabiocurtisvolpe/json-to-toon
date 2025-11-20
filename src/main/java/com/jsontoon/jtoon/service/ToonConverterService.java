package com.jsontoon.jtoon.service;

import org.springframework.stereotype.Service;
import com.felipestanzani.jtoon.JToon;

@Service
public class ToonConverterService {

    public String toToon(Object data) {
        return JToon.encode(data);
    }
    

    public String jsonToToon(String jsonString) {
        return JToon.encodeJson(jsonString);
    }

    public String toonToJson(String toonString) {
        return JToon.decodeToJson(toonString);
    }
}
