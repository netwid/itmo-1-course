package server;

import data.Request;
import data.Response;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.*;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveAction;

public class Server {
    private static final int port = 31337;
    private static DatagramChannel dc;
    private static final Logger logger = LogManager.getLogger();

    static {
        try {
            InetSocketAddress addr = new InetSocketAddress(port);
            dc = DatagramChannel.open();
            dc.bind(addr);
            dc.configureBlocking(false);
            logger.info("Сервер запущен");
        } catch (IOException e) {
            System.out.println("Ошибка при инициализации сервера");
        }
    }

    public static void sendObject(SocketAddress addr, Serializable object) {
        Response response = new Response();
        response.success = true;
        response.object = object;

        Server.send(addr, response);
    }

    public static void print(SocketAddress addr, String toPrint) {
        if (addr == null) {
            System.out.print(toPrint);
            return;
        }

        Response response = new Response();
        response.success = true;
        response.message = toPrint;

        Server.send(addr, response);
    }

    public static void error(SocketAddress addr, String message) {
        if (addr == null) {
            System.out.print(message);
            return;
        }

        Response response = new Response();
        response.success = false;
        response.message = message;

        Server.send(addr, response);
    }

    public static void send(SocketAddress addr, Response response) {
        /* This is a specially single ForkJoIn */
        ForkJoinPool pool = ForkJoinPool.commonPool();
        pool.invoke(new RecursiveAction() {
            @Override
            protected void compute() {
                try (ByteArrayOutputStream out = new ByteArrayOutputStream();
                     ObjectOutputStream os = new ObjectOutputStream(out)) {
                    os.writeObject(response);
                    dc.send(ByteBuffer.wrap(out.toByteArray()), addr);
                    logger.info("Ответ отправлен на " + addr.toString());
                } catch (IOException e) {
                    System.out.println("Ошибка при отправке запроса на " + addr);
                }
            }
        });
    }

    public static Request receive() {
        try {
            ByteBuffer buf = ByteBuffer.allocate(1024 * 1024);
            SocketAddress addr = dc.receive(buf);
            if (addr != null) {
                logger.info("Получен новый запрос от " + addr);
                ObjectInputStream ois = new ObjectInputStream(new ByteArrayInputStream(buf.array()));
                Request request = (Request) ois.readObject();
                request.client = addr;
                return request;
            }
        } catch (ClassNotFoundException e) {
            System.out.println("Полученный объект не найден");
        } catch (IOException e) {
            System.out.println("Ошибка получения запроса");
        }
        return null;
    }
}
