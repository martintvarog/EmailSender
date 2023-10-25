package EmailSender;
import java.net.*;
import java.io.*;

public class EmailSender {
    Socket socket;
    /*
     * Constructor opens Socket to host/port. If the Socket throws an exception during opening,
     * the exception is not handled in the constructor.
     */
    public EmailSender(String host, int port) throws IOException {
        socket = new Socket(host, port);
    }
    /*
     * sends email from an email address to an email address with some subject and text.
     * If the Socket throws an exception during sending, the exception is not handled by this method.
     */
    public void send(String from, String to, String subject, String text) throws IOException, InterruptedException {
        try (var socket = new Socket("smtp.utb.cz", 25)) {
            var in = socket.getInputStream();
            var out = socket.getOutputStream();
            out.write(("HELO pc1-52109\r\n" +
                    "MAIL FROM: " + from + "\r\n" +
                    "RCPT TO: " + to + "\r\n" +
                    "DATA\r\n" +
                    "Subject: " + subject + "\r\n" +
                    text + "\r\n.\r\n"
            ).getBytes());

            out.flush();
            var buffer = new byte[1024];
            var count = in.read(buffer);

            Thread.sleep(100);

            System.out.write(buffer, 0, count);
        }
    }

    /*
     * sends QUIT and closes the socket
     */
    public void close() throws IOException {
        var out = socket.getOutputStream();
        out.write("QUIT\r\n".getBytes());
        out.flush();
        socket.close();

    }
}
