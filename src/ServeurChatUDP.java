import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetSocketAddress;

public class ServeurChatUDP {
    private static final int PORT = 1234;
    private static final int BUFFER_SIZE = 1024;

    public static void main(String[] args) {
        try (DatagramSocket socket = new DatagramSocket(new InetSocketAddress(PORT))) {
            System.out.println(" Serveur UDP en attente sur le port " + PORT + "...");

            byte[] buffer = new byte[BUFFER_SIZE];

            while (true) {
                DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
                socket.receive(packet); 
                String message = new String(packet.getData(), 0, packet.getLength());
                System.out.println("Message reçu de " + packet.getAddress() + ":" + packet.getPort());
                System.out.println("Contenu : " + message);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
