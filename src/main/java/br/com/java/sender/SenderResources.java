package br.com.java.sender;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public abstract class SenderResources {

	public static HttpResponse<String> send(String endereco) {
		try {
			HttpClient client = HttpClient.newHttpClient();
			HttpRequest request = HttpRequest.newBuilder().uri(URI.create(endereco)).build();

			return client.send(request, HttpResponse.BodyHandlers.ofString());
		} catch (InterruptedException | IllegalArgumentException | IOException e) {
			e.printStackTrace();
		}
		
		return null;
	}

}