import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Scanner;

public class ClientChatUDP {
    private static final int PORT = 1234;

    public static void main(String[] args) {
        try (DatagramSocket socket = new DatagramSocket()) {
            InetAddress serveurAddress = InetAddress.getByName("localhost");
            Scanner scanner = new Scanner(System.in);

            System.out.print("Entrez votre nom d'utilisateur : ");
            String nom = scanner.nextLine();

            System.out.println(" Client prêt.");
            while (true) {
                System.out.print("> ");
                String texte = scanner.nextLine();
                String message = "[" + nom + "] : " + texte;
                byte[] data = message.getBytes();
                DatagramPacket packet = new DatagramPacket(data, data.length, serveurAddress, PORT);
                socket.send(packet);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
