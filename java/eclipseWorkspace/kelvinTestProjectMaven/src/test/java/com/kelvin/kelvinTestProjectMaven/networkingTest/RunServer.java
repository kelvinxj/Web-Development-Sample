package com.kelvin.kelvinTestProjectMaven.networkingTest;

import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;

public class RunServer {
	public static void main(String[] args) throws IOException{
		HttpServer server = HttpServer.create(new InetSocketAddress(8002), 0);
		server.createContext("/test", new TestHandler());
		server.start();
	}
	
	static  class TestHandler implements HttpHandler{
        @Override
        public void handle(HttpExchange exchange) throws IOException {
            String response = "abc�й�";
            //it is better to tell client which encoding the response use
            exchange.getResponseHeaders().add("Content-Type", "text/html; charset=gbk");
            exchange.sendResponseHeaders(200, 0);
            OutputStream os = exchange.getResponseBody();
            os.write(response.getBytes());
            os.close();
        }
    }
}
