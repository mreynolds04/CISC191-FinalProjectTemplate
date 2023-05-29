package edu.sdccd.cisc191.template;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

public class Server {
    private static final int PORT = 1234;
    private static Map<String, String> passwordStorage = new HashMap<>();

    public static void main(String[] args) {
        try (ServerSocket serverSocket = new ServerSocket(PORT)) {
            System.out.println("Server started. Listening on port " + PORT);

            while (true) {
                Socket clientSocket = serverSocket.accept();
                System.out.println("Client connected: " + clientSocket.getInetAddress());

                new Thread(() -> handleClient(clientSocket)).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void handleClient(Socket clientSocket) {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
             PrintWriter writer = new PrintWriter(clientSocket.getOutputStream(), true)) {

            String request;
            while ((request = reader.readLine()) != null) {
                String[] requestParts = request.split(":");

                String response;
                switch (requestParts[0]) {
                    case "GENERATE":
                        response = generateAndSavePassword(requestParts[1], Integer.parseInt(requestParts[2]),
                                Boolean.parseBoolean(requestParts[3]), Boolean.parseBoolean(requestParts[4]),
                                Boolean.parseBoolean(requestParts[5]));
                        break;
                    case "RETRIEVE":
                        response = retrievePassword(requestParts[1]);
                        break;
                    case "STORE":
                        response = storePassword(requestParts[1], requestParts[2]);
                        break;
                    case "CHANGE":
                        response = changePassword(requestParts[1], requestParts[2]);
                        break;
                    case "DELETE":
                        response = deletePassword(requestParts[1]);
                        break;
                    case "QUIT":
                        response = "Goodbye!";
                        break;
                    default:
                        response = "Invalid request";
                        break;
                }

                writer.println(response);

                if (requestParts[0].equals("QUIT")) {
                    System.out.println("Client disconnected: " + clientSocket.getInetAddress());
                    break;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static String generateAndSavePassword(String website, int length, boolean includeSymbols,
                                                  boolean includeNumbers, boolean includeLetters) {
        String password = PasswordGenerator.generateRandomPassword(length, includeSymbols, includeNumbers, includeLetters);
        passwordStorage.put(website, password);
        return "Generated password: " + password;
    }

    private static String retrievePassword(String website) {
        String password = passwordStorage.get(website);
        if (password != null) {
            return "Password for " + website + ": " + password;
        } else {
            return "No password found for " + website + ".";
        }
    }

    private static String storePassword(String website, String password) {
        passwordStorage.put(website, password);
        return "Password saved for " + website + ".";
    }

    private static String changePassword(String website, String newPassword) {
        if (passwordStorage.containsKey(website)) {
            passwordStorage.put(website, newPassword);
            return "Password for " + website + " changed successfully.";
        } else {
            return "No password found for " + website + ".";
        }
    }

    private static String deletePassword(String website) {
        if (passwordStorage.containsKey(website)) {
            passwordStorage.remove(website);
            return "Password for " + website + " removed successfully.";
        } else {
            return "No password found for " + website + ".";
        }
    }
}
