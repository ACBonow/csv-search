package org.example;
import java.text.DecimalFormat;

public class Event {
    private final String dispositivo;
    private final String evento;
    private final double distancia;
    private final String instante;
    private final String payload;
    private final double latitude;
    private final double longitude;

    public Event(String dispositivo, String evento, double distancia, String instante, String payload, double latitude, double longitude) {
        this.dispositivo = dispositivo; //código rastreador
        this.evento = evento; // identificador evento
        this.distancia = distancia; //distancia calculada
        this.instante = instante; //instante da localização
        this.payload = payload; // padrão do GPS
        this.latitude = latitude;
        this.longitude = longitude;
    }



    public String getDispositivo() {
        return dispositivo;
    }

    public String getEvento() {
        return evento;
    }

    public double getLatitude() {
        return latitude;
    }

    public String getInstante() {
        return instante;
    }

    public double getLongitude() {
        return longitude;
    }

    public String toCSVString() {
        DecimalFormat df = new DecimalFormat("#00.00");
        String distanciaFormatada = df.format(distancia);
        return dispositivo + "," + distanciaFormatada + "," + instante + "," + payload;
    }
}

