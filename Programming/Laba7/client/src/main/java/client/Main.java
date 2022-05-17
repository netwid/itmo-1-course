package client;

import data.Response;
import server.Server;

import java.io.Serializable;
import java.util.Objects;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        System.out.println("Starting a client module");

        try {
            System.out.print("> Do u want register, otherwise login? [Y/n] ");
            Scanner scanner = new Scanner(System.in);
            String ans = scanner.nextLine();
            String res = "Error";
            String login = "", password ="";

            while (res.equals("Error")) {
                if (!ans.equals("Y")) {
                    System.out.println("Authentication");
                }
                System.out.print("Login: ");
                login = scanner.nextLine();
                System.out.print("Password: ");
                password = scanner.nextLine();
                Client.sendCommand(ans.equals("Y") ? "register" : "login", login, password);
                res = Client.receive().toPrint;
            }

            while (true) {
                System.out.print("> ");
                scanner = new Scanner(System.in);
                String line = scanner.nextLine();
                Client.sendCommand(line, login, password);
                Response response = Client.receive();

                if (response.toPrint != null) {
                    System.out.print(response.toPrint);
                }

                if (response.command != null) {
                    if (response.command.equals("send") && response.toInput != null) {
                        Serializable obj = (Serializable) response.toInput.getMethod("input").invoke(null);
                        Client.sendCommandObject(line, login, password, obj);

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
