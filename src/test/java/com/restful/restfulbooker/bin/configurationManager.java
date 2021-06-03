package com.restful.restfulbooker.bin;

import org.yaml.snakeyaml.Yaml;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class configurationManager {

    private static configurationManager configuration;

    private app appSettings;

    private configurationManager(){
        Yaml yaml = new Yaml();
        Path path = Paths.get("src", "test", "resources", "config", "test.yaml");
        try (InputStream in = Files.newInputStream(path)) {
            appSettings = yaml.loadAs(in, app.class);
        } catch (IOException ie) {
            throw new ExceptionInInitializerError(ie);
        }
    }

    private static configurationManager getAppSettings(){
        if (configuration == null) {
            synchronized (configurationManager.class) {
                if (configuration == null) {
                    configuration = new configurationManager();
                }
            }
        }
        return configuration;
    }

    public static app getSettings() {
        return getAppSettings().appSettings;
    }
}
