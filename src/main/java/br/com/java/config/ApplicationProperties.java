package br.com.java.config;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public abstract class ApplicationProperties {
	private static final Properties properties = new Properties();
	
	static {
        try (InputStream input = ApplicationProperties.class.getClassLoader().getResourceAsStream("application.properties")) {
            if (input == null) {
                throw new RuntimeException("Arquivo application.properties não encontrado no classpath.");
            }
            properties.load(input);
        } catch (IOException e) {
            throw new RuntimeException("Erro ao carregar application.properties", e);
        }
    }

    public static String get(String key) {
        return properties.getProperty(key);
    }
}