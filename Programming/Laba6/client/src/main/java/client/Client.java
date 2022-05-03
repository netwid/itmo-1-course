package client;

import data.Request;
import data.Response;

import java.io.*;
import java.net.*;
import java.nio.ByteBuffer;
import java.util.Arrays;

public class Client {
    private final byte[] ip = new byte[]{127, 0, 0, 1};
    private static final int port = 31337;
    private static final int receiveLimit = 1024 * 1024;
    private static DatagramSocket ds;
    private static DatagramPacket dp;
    private static InetAddress host;

    public Client() {
        try {
            ds = new DatagramSocket();
            host = InetAddress.getByAddress(ip);
            return;
        } catch (UnknownHostException e) {
            System.out.println("Неизвестный хост");
        } catch (SocketException e) {
            System.out.println("Ошибка создания сокета");;
        }
        System.exit(1);
    }

    public static int getId() {
        Client.sendCommand("get_id");
        Response response = Client.receive();
        return Integer.parseInt(response.toPrint);
    }

    public static void sendCommand(String line) {
        Client.sendCommandObject(line, null);
    }

    public static void sendCommandObject(String line, Serializable obj) {
        try {
            String[] words = line.trim().split("\\s+");
            Request request = new Request(words[0], Arrays.copyOfRange(words, 1, words.length), obj);

            if (request.command.equals("exit")) {
                System.exit(0);
            }
            if (request.command.equals("save")) {
                request.command = "abcdef"; // for "command not found"
            }

            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(byteArrayOutputStream);
            objectOutputStream.writeObject(request);
            dp = new DatagramPacket(byteArrayOutputStream.toByteArray(), byteArrayOutputStream.toByteArray().length, host, port);
            ds.send(dp);
        }
        catch (IOException e) {
            System.out.println("Ошибка при отсылке команды");
        }
    }

    public static Response receive() {
        try {
            ByteBuffer buf = ByteBuffer.allocate(receiveLimit);
            dp = new DatagramPacket(buf.array(), buf.array().length);
            ds.setSoTimeout(3000);
            int count = 0;
            boolean continueSending = true;
            while (continueSending && count < 5) {
                try {
                    ds.receive(dp);
                    continueSending = false;
                } catch (SocketTimeoutException ignored) {
                    count++;
                    System.out.println("Сервер не отвечает");
                }
            }
            if (count == 5) {
                Response response = new Response();
                response.toPrint = "Сервер не ответил\n";
                return response;
            }
            ObjectInputStream ois = new ObjectInputStream(new ByteArrayInputStream(buf.array()));
            return (Response) ois.readObject();
        } catch (java.io.IOException e) {
            System.out.println("Ошибка получения пакета");
        } catch (ClassNotFoundException e) {
            System.out.println("Сервер вернул неправильный класс");
        }
        return null;
    }

    public static boolean checkContainsPassport(String passportId) {
        Client.sendCommand("check_passport " + passportId);
        Response response = Client.receive();
        return Boolean.parseBoolean(response.toPrint);
    }
}
