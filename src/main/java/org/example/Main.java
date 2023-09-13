package org.example;

public class Main {
    public static void main(String[] args) {
            String[] coordinates = MyCommandLine.readCommandLine(args);
            FilterEvent.main(coordinates);
    }
}
