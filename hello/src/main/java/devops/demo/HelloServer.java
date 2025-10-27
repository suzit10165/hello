package devops.demo;

import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;

import com.sun.net.httpserver.Headers;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpServer;

public class HelloServer {
    public static void main(String[] args) throws IOException {
        int port = 8080;
        HttpServer server = HttpServer.create(new InetSocketAddress(port), 0);

        server.createContext("/", (HttpExchange exchange) -> {
            String html = """
                <!doctype html>
                <html lang="en">
                <head>
                    <meta charset="UTF-8">
                    <title>Hello, DevOps!</title>
                    <style>
                        body { font-family: Arial, sans-serif; text-align: center; margin-top: 100px; }
                        h1 { color: #1E90FF; }
                        p { color: #333; }
                        .name { color: #e67e22; margin-top: 20px; }
                    </style>
                </head>
                <body>
                    <h1>Hello, DevOps!</h1>
                    <p>Welcome to our first DevOps demo application.</p>
                    <p class="name">Sujit Acharaya — Computer Science</p>
                </body>
                </html>
                """;

            Headers headers = exchange.getResponseHeaders();
            headers.add("Content-Type", "text/html; charset=utf-8");
            byte[] response = html.getBytes();
            exchange.sendResponseHeaders(200, response.length);
            try (OutputStream os = exchange.getResponseBody()) {
                os.write(response);
            }
        });

        server.start();
        System.out.println("✅ Server started on http://localhost:" + port);
    }
}
