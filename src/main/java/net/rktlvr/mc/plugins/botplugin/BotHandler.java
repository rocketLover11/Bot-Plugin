package net.rktlvr.mc.plugins.botplugin;

import com.sun.net.httpserver.*;
import org.bukkit.Bukkit;
import java.io.*;
import java.net.InetSocketAddress;
import java.nio.charset.StandardCharsets;

public class BotHandler {
    private final BotPlugin plugin;
    private HttpServer server;

    public BotHandler(BotPlugin plugin) {
        this.plugin = plugin;
    }

    public void startServer(int port) throws IOException {
        server = HttpServer.create(new InetSocketAddress("127.0.0.1", port), 0);

        server.createContext("/status", exchange -> {
            sendResponse(exchange, "BotPlugin v1.0-SNAPSHOT on 26.1.1 is ACTIVE", 200);
        });

        server.setExecutor(null);
        server.start();
    }

    public void stopServer() { if (server != null) server.stop(0); }

    private void sendResponse(HttpExchange exchange, String message, int status) throws IOException {
        exchange.getResponseHeaders().set("Content-Type", "text/plain");
        byte[] response = message.getBytes(StandardCharsets.UTF_8);
        exchange.sendResponseHeaders(status, response.length);
        exchange.getResponseBody().write(response);
        exchange.close();
    }
}
