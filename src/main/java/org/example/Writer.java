package org.example;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import java.util.List;

public class Writer {
    public static void escreverResultadosCSV(List<Event> eventosFiltrados, String outputPath) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(outputPath))) {
            writer.println("Dispositivo,Distancia,Instante,Payload");
            for (Event evento : eventosFiltrados) {
                writer.println(evento.toCSVString());
            }
            System.out.println("Resultados salvos em " + outputPath);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}

