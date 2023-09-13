package org.example;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import java.io.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

public class FilterEvent {
    private static final Logger LOGGER = Logger.getLogger(FilterEvent.class.getName());

    public static void main(String[] args) {

        // Lista para armazenar eventos filtrados
        List<Event> eventosFiltrados = new ArrayList<>();

        // Chamada para filtrar eventos
        filter(args, eventosFiltrados);

        // Organizar eventos primeiro pelo dispositivo
// e depois pelo horário do mais antigo para o mais novo
        eventosFiltrados.sort(Comparator.comparing(Event::getDispositivo)
                .thenComparing(Event::getInstante));
        displayFilteredEvents(eventosFiltrados);
    }

    public static void filter(String[] coordinates, List<Event> eventosFiltrados) {
        double targetLatitude = Double.parseDouble(coordinates[0]);
        double targetLongitude = Double.parseDouble(coordinates[1]);

        // Criar um pool de threads
        ExecutorService executorService = Executors.newFixedThreadPool(4);

        // Carregar o arquivo usando o ClassLoader
        ClassLoader classLoader = FilterEvent.class.getClassLoader();
        InputStream inputStream = classLoader.getResourceAsStream("eventlog.csv");

        if (inputStream == null) {
            LOGGER.log(Level.SEVERE, "O arquivo eventlog.csv não foi encontrado.");
            return;
        }

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))) {
            String line;

            while ((line = reader.readLine()) != null) {
                final String csvLine = line;

                executorService.submit(() -> {
                    try {
                        processCSVLine(csvLine, targetLatitude, targetLongitude, eventosFiltrados);
                    } catch (IOException e) {
                        LOGGER.log(Level.SEVERE, "Ocorreu um erro durante o processamento do CSV", e);
                    }
                });
            }

            executorService.shutdown();
            executorService.awaitTermination(Long.MAX_VALUE, TimeUnit.NANOSECONDS);
        } catch (IOException | InterruptedException e) {
            LOGGER.log(Level.SEVERE, "Ocorreu um erro durante a leitura do arquivo CSV", e);
        }
    }


    private static void processCSVLine(String csvLine, double targetLatitude, double targetLongitude, List<Event> eventosFiltrados) throws IOException {
        CSVParser parser = CSVParser.parse(csvLine, CSVFormat.DEFAULT);

        for (CSVRecord record : parser) {
            String dispositivo = record.get(0);
            String evento = record.get(1);
            String instant = record.get(2);
            String payload = record.get(3);

            String[] payloadParts = payload.split(",");

            if (payloadParts.length >= 4) {
                String latitudeStr = payloadParts[2].replaceAll("[^0-9.-]", "");
                String longitudeStr = payloadParts[3].replaceAll("[^0-9.-]", "");
                double eventLatitude = Double.parseDouble(latitudeStr);
                double eventLongitude = Double.parseDouble(longitudeStr);

                double distancia = CalculoHaversine.haversine(targetLatitude, targetLongitude, eventLatitude, eventLongitude);

                if (distancia <= 50.0) {
                    eventosFiltrados.add(new Event(dispositivo, evento, distancia, instant, payload, eventLatitude, eventLongitude));
                }
            }
        }
    }

    public static void displayFilteredEvents(List<Event> eventosFiltrados) {
        System.out.println("Quantidade de eventos no Raio das coordenadas estabelecidas: " + eventosFiltrados.size());

        System.out.println("Dispositivo,Distancia,Instante,Payload");
        for (Event evento : eventosFiltrados) {
            System.out.println(evento.toCSVString());
        }
    }
}
