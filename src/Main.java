import java.io.IOException;
import java.net.Socket;
import java.util.List;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static void main(String[] args) {
        var arg = new String[]{"Hello", "World"};

        try {
            try (var socket = new Socket("smtp.utb.cz", 25)) {
                var in = socket.getInputStream();
                var out = socket.getOutputStream();
                out.write(("HELO pc1-52109\r\n" +
                        "MAIL FROM: important@utb.cz\r\n" +
                        "RCPT TO: m_tvarog@utb.cz \r\n" +
                        "DATA\r\n" +
                        "Subject: Dulezity mail ze studijniho\r\n" +
                        "Tohle neni phishing haha\r\n.\r\n"
                ).getBytes());

                out.flush();

                var buffer = new byte[1024];
                var count = in.read(buffer);

                System.out.write(buffer, 0, count);

            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}