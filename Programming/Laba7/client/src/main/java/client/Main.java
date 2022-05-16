package client;

import data.Response;

import java.io.Serializable;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        System.out.println("Starting a client module");
        Client client = new Client();

        try {+
            while (true) {
                System.out.print("> ");
                Scanner scanner = new Scanner(System.in);
                String line = scanner.nextLine();
                Client.sendCommand(line);
                Response response = Client.receive();

                if (response.toPrint != null) {
                    System.out.print(response.toPrint);
                }

                if (response.command != null) {
                    if (response.command.equals("send") && response.toInput != null) {
                        Serializable obj = (Serializable) response.toInput.getMethod("input").invoke(null);
                        Client.sendCommandObject(line, obj);

                        Response response2 = Client.receive();
                        System.out.print(response2.toPrint);
                    }
                }
            }
        }
        catch (Exception e) {
            System.out.println(e.toString());
        }
    }
}
