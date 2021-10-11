package solid_Ex;


import solid_Ex.core.EngineImpl;

import solid_Ex.logger.Logger;
import solid_Ex.logger.MessageLogger;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {

        var reader = new BufferedReader(new InputStreamReader(System.in));

        Logger logger = new MessageLogger();

        new EngineImpl(reader, logger).run();

        System.out.println(logger);
    }
}
