package br.com.java.principal;

import java.io.FileWriter;
import java.io.IOException;
import java.net.http.HttpResponse;
import java.util.Scanner;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonSyntaxException;

import br.com.java.config.ApplicationProperties;
import br.com.java.modelos.TituloOmdb;
import br.com.java.sender.SenderResources;

public class PrincipalComBusca {
	private static FileWriter escrita;
	
	public static void main(String[] args) throws IOException, InterruptedException {
		try (Scanner leitura = new Scanner(System.in)) {
			String apiKey = getPropertie("apiKey");

			System.out.println("Digite um filme para busca.");
			
			var busca = leitura.nextLine();

			try {
				String jsonFilme = consultarFilme(apiKey, busca);

				TituloOmdb tituloOmdb = (TituloOmdb) convertJsonToObject(jsonFilme, TituloOmdb.class);
				
				escrita = criarFile();
				
				Gson gson = new Gson();
				String json = gson.toJson(tituloOmdb);
				
				escreverNoFile(tituloOmdb, json);
			} catch (NumberFormatException e) {
				System.out.println("Aconteceu um erro:");
				System.out.println(e.getMessage());
			} catch (IllegalArgumentException e) {
				System.out.println("Argumentos inválidos pra URI");
				System.out.println(e.getMessage());
			}

			System.out.println("O programa finalizou corretamente.");
		} catch (JsonSyntaxException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
	}

	private static String consultarFilme(String apiKey, String busca) {
		var endereco = "https://www.omdbapi.com/?t=" + busca.replace(" ", "+") + "&apiKey=" + apiKey;
		HttpResponse<String> response = SenderResources.send(endereco);
		return response.body();
	}

	private static void escreverNoFile(TituloOmdb tituloOmdb, String json) throws IOException {
		escrita.write(json);
		escrita.close();
	}

	private static FileWriter criarFile() throws IOException {
		return new FileWriter("filmes.json");
	}

	private static Object convertJsonToObject(String json, Class<?> clazz) {
		Gson gson = new GsonBuilder().setFieldNamingPolicy(FieldNamingPolicy.UPPER_CAMEL_CASE).create();
		return gson.fromJson(json, clazz);
	}
	
	private static String getPropertie(String propertie) {
		return ApplicationProperties.get(propertie);
	}
}