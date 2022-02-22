
import java.io.*; // provides for system input and output through data streams, serialization and the file system.
import java.net.Socket; // Provides classes for networking applications.
import java.util.Base64;
/*This class consists exclusively of static methods for obtaining encoders and decoders for the
Base64 encoding scheme. */

public class Help { // open Help class.

    public static String read_word(InputStreamReader in) throws IOException { // open method read_word.

        // reads text from a character-input stream, buffering characters so as to provide for the efficient reading of characters.
        BufferedReader bf = new BufferedReader(in);

        // return word for the caller.
        return bf.readLine();

    } // close method read_word.

    public static void write_word(Socket s, String message) throws IOException { // open method write_word.

        PrintWriter pr = new PrintWriter(s.getOutputStream());

        pr.println(message);

        pr.flush();

    }  // close method write_word.

    public static String encode(String x) { // open encode method. 
        try { // open try.

            //return encoding word that was encoded by using the Base64 encoder. 
            return Base64.getEncoder().encodeToString(x.getBytes("ascii"));

        } // close try.
        catch (UnsupportedEncodingException usee) {
            return "Exception:" + usee.getMessage();
        }
    } // close encode method.

    public static String decode(String x) { // open decode method.

        try { // open try.

            byte[] decodedBytes = Base64.getDecoder().decode(x);
            //return decoding word that was decoded by using the Base64 decoder. 
            return new String(decodedBytes, "ascii");

        } // close try.
        catch (UnsupportedEncodingException usee) { // open catch.
            return "Exception:" + usee.getMessage();
        } // close catch.

    } // close decode method

    public static void send_massege(String message, Socket s, String choice, String source) throws IOException { // open send_massege method.

        // inform the server that the client choose the open mode or secure mode.
        if (source.equals("Client")) {
            Help.write_word(s, choice);
        }

        // if the mode is open mode send message in clear text, else send encrypted message.
        if ("1".equals(choice)) {
            Help.write_word(s, message);
        } else {
            Help.write_word(s, Help.encode(message));
        }

    } // close send_massege method.
} // close Help class.
