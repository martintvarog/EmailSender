import java.io.IOException;
import java.net.Socket;
import java.util.List;
import EmailSender.EmailSender;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static void main(String[] args) {
        try {
            var emailSender = new EmailSender("smtp.utb.cz", 25);
            emailSender.send("m_tvarog@utb.cz", "m_tvarog@utb.cz", "Test", "Test");
            emailSender.close();
        }
        catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}