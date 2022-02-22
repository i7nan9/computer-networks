
import java.net.*; // Provides classes for networking applications.
import java.io.*; // provides for system input and output through data streams, serialization and the file system.

public class Server { // open class Server.

    public static void main(String[] args) throws IOException { // open main method.

        // We created an object of class ServerSocket to receive any communication from any client.    
        ServerSocket ss = new ServerSocket(4999);

        // We called accept method which returns an object of type Socket that can exchange data with any object of type Socket.
        Socket s = ss.accept();

        // print this statment after accept client request.
        System.out.println("client connected");

        // read bytes that are written by the client.
        InputStreamReader in = new InputStreamReader(s.getInputStream());

        // print client word.
        System.out.println("client : " + Help.read_word(in));

        // send a confirmation to the client that the server is running fine
        Help.write_word(s, "yes");

        // read the mode selected by the client.
        String choice = Help.read_word(in);

        // read the word written by the client.
        String message = Help.read_word(in);

        // if the mode number is one(open mode)or two(secure mode).
        while (choice.equals("1") || choice.equals("2")) { // open while loop.

            // print client word.
            System.out.println("client : " + message);

            // if open mode.
            if (choice.equals("1")) {
                // send back a copy of the word that was received from the client.
                Help.send_massege(message, s, choice, "Server");

            } else if (choice.equals("2")) {
                // Call decode method to decode the word and then save it in the message object.
                message = Help.decode(message);
                // print decoded word.
                System.out.println("After decoding client message : " + message);
                // send back a copy of the decoded word that was received from the client.
                Help.send_massege(message, s, choice, "Server");
            }

            // read the mode selected by the client.
            choice = Help.read_word(in);

            // read the word written by the client.
            message = Help.read_word(in);

        } // close while loop.   

        // if the user want to quit application.
        if (choice.equals("3")) {
            // close the connection.
            s.close();
        }
    } // close main method. 
} // close Server class.
