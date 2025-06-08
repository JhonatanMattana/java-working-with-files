package br.com.java.principal;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Scanner;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonSyntaxException;

import br.com.java.modelos.Titulo;
import br.com.java.modelos.TituloOmdb;

public class PrincipalComBusca {

	public static void main(String[] args) throws IOException, InterruptedException {
		try (Scanner leitura = new Scanner(System.in)) {
			System.out.println("Digite um filme para busca.");
			var busca = leitura.nextLine();

			var endereco = "https://www.omdbapi.com/?t=" + busca.replace(" ", "+") + "&apiKey=62d04354";

			try {
				HttpClient client = HttpClient.newHttpClient();
				HttpRequest request = HttpRequest.newBuilder().uri(URI.create(endereco)).build();

				HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

				String json = response.body();
				System.out.println(json);

				Gson gson = new GsonBuilder().setFieldNamingPolicy(FieldNamingPolicy.UPPER_CAMEL_CASE).create();
				TituloOmdb tituloOmdb = gson.fromJson(json, TituloOmdb.class);

				Titulo meuTitulo = new Titulo(tituloOmdb);
				System.out.println(tituloOmdb);
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
}