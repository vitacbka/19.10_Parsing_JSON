package main.java.org.example;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Main {
    public static void main(String[] args) throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        String jsonFile = Files.readString(Paths.get("C:\\Users\\vitac\\java_basics\\Parse\\src\\main\\resources\\map.json"));
        JsonNode jsonData = objectMapper.readTree(jsonFile);
        JsonNode stations = jsonData.get("stations");
        JsonNode lines = jsonData.get("lines");
        for(JsonNode line : lines) {
            ObjectNode lineNode = (ObjectNode) line;
            lineNode.remove("color");
            String lineNumber = line.get("number").asText();
            JsonNode statiionsList = stations.get(lineNumber);
            int stationsCount = statiionsList.size();
            lineNode.put("stationsCount", stationsCount);
        }

        ObjectMapper mapper = new ObjectMapper();
        File output = new File("export.json");
        mapper.writeValue(output, lines);
    }
}

