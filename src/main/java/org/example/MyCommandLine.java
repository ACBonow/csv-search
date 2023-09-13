package org.example;
import org.apache.commons.cli.*;

public class MyCommandLine { // Renomeie a classe personalizada
    public static String[] readCommandLine(String[] args) {
        Options options = new Options();

        // Adicione uma opção para as coordenadas
        Option locationOption = Option.builder()
                .longOpt("location")
                .desc("Coordenadas no formato <latitude>,<longitude>")
                .hasArg()
                .argName("coordenadas")
                .required()
                .build();
        options.addOption(locationOption);

        CommandLineParser parser = new DefaultParser(); // Use a classe DefaultParser

        CommandLine cmd;

        try {
            cmd = parser.parse(options, args);

            if (cmd.hasOption("location")) {
                String coordinates = cmd.getOptionValue("location");
                String[] parts = coordinates.split(",");
                if (parts.length == 2) {
                    return parts;
                } else {
                    System.err.println("Formato incorreto para coordenadas. Use <latitude>,<longitude>");
                    System.exit(1);
                }
            }
        } catch (ParseException e) {
            System.err.println("Erro ao analisar os argumentos da linha de comando: " + e.getMessage());
            HelpFormatter formatter = new HelpFormatter();
            formatter.printHelp("./csv-search --location <latitude>,<longitude>", options);
            System.exit(1);
        }

        return null;
    }
}
