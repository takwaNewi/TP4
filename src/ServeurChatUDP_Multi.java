import java.net.*;
import java.util.*;

public class ServeurChatUDP_Multi {
    private static final int PORT = 1234;
    private static final int BUFFER_SIZE = 1024;

    public static void main(String[] args) {
        try (DatagramSocket socket = new DatagramSocket(PORT)) {
            System.out.println("Serveur UDP multiclient prêt sur le port " + PORT);

            Set<SocketAddress> clients = new HashSet<>();
            byte[] buffer = new byte[BUFFER_SIZE];

            while (true) {
                DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
                socket.receive(packet);
                String message = new String(packet.getData(), 0, packet.getLength());
                SocketAddress exp = packet.getSocketAddress();

                clients.add(exp); // enregistre le client s’il n’existe pas encore
                System.out.println("📨 Reçu de " + exp + " : " + message);
                for (SocketAddress addr : clients) {
                    if (!addr.equals(exp)) {
                        DatagramPacket envoi = new DatagramPacket(message.getBytes(), message.length(), addr);
                        socket.send(envoi);
                    }
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
