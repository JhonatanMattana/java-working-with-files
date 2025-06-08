package br.com.java.principal;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Scanner;

import com.google.gson.Gson;

import br.com.java.modelos.Titulo;

public class PrincipalComBusca {

	public static void main(String[] args) throws IOException, InterruptedException {
		Scanner leitura = new Scanner(System.in);
		System.out.println("Digite um filem para busca.");
		var busca = leitura.nextLine();
		
		var endereco = "https://www.omdbapi.com/?t=" + busca + "&apiKey=62d04354";
		
		HttpClient client = HttpClient.newHttpClient();
		HttpRequest request = HttpRequest.newBuilder()
				.uri(URI.create(endereco))
				.build();
		
		HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
	
		String json = response.body();
		System.out.println(json);

		Gson gson = new Gson();
		Titulo meuTitulo = gson.fromJson(json, Titulo.class);
		System.out.println(meuTitulo.getNome() + ", " + meuTitulo.getAnoDeLancamento());
	}
	
}