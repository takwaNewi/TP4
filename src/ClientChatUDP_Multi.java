import java.net.*;
import java.util.Scanner;

public class ClientChatUDP_Multi {
    private static final int SERVER_PORT = 1234;

    public static void main(String[] args) {
        try {
            DatagramSocket socket = new DatagramSocket();
            InetAddress serverAddress = InetAddress.getByName("localhost");
            Scanner scanner = new Scanner(System.in);

            System.out.print("Entrez votre nom d'utilisateur : ");
            String nom = scanner.nextLine();
            Thread reception = new Thread(() -> {
                try {
                    byte[] buffer = new byte[1024];
                    while (true) {
                        DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
                        socket.receive(packet);
                        String message = new String(packet.getData(), 0, packet.getLength());
                        System.out.println("\n" + message);
                        System.out.print("> ");
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });

            reception.start();

            System.out.println(" Chat UDP prêt. Tapez vos messages :");
            while (true) {
                System.out.print("> ");
                String texte = scanner.nextLine();
                String message = "[" + nom + "] : " + texte;
                byte[] data = message.getBytes();
                DatagramPacket packet = new DatagramPacket(data, data.length, serverAddress, SERVER_PORT);
                socket.send(packet);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
