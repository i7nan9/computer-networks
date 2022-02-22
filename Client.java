
import java.net.*; // provides classes for networking applications.
import java.io.*; // provides for system input and output through data streams, serialization and the file system.
import java.util.Scanner;

/*Contains the collections framework, legacy collection classes, event model, date and time facilities, internationalization, 
and miscellaneous utility classes (a string tokenizer, a random-number generator, and a bit array).*/

public class Client { // open Client class.

    // create a Scanner object to read from the user.
    static Scanner input = new Scanner(System.in);

    // this method will ask the user to select one option from a list of options.
    public static void print_choices() { // open print_choices method.

        System.out.println("choose your favorate option:\n 1) open mode\n 2) secure mode\n 3) quit app\n");

    } // close print_choices method.

    public static void main(String[] args) throws Exception { // open main methode.

        try { // open try.

            /* here we have created an object of class Socket to communicate with another
         program on a server that uses the port 4999.*/
            Socket s = new Socket("localhost", 4999);

            // send message to the server.
            Help.write_word(s, "is it working?");

            // read bytes that are written by the server.
            InputStreamReader in = new InputStreamReader(s.getInputStream());

            // print server word.
            System.out.println("server : " + Help.read_word(in));

            boolean start = true;
            // enter the while loop if the value of start varible was true.
            while (start) { // open while loop.

                // call print_choices method to print the choices.
                print_choices();

                // read user input
                String choice = input.next();

                // If the user's choice is not in the list of choices.
                if (!choice.equals("1") && !choice.equals("2") && !choice.equals("3")) {
                    System.out.println("WRONG CHOICE PLZ TRY AGAIN!");
                    continue;
                }

                // if the client choice was one(open mode) or two(secure mode).
                if (choice.equals("1") || choice.equals("2")) {
                    // ask the user to write the message.
                    System.out.println("What is your message?");
                    // read the message written by the user and srote it in message object.
                    String message = input.next();

                    // send a message that was written by the user to the server.
                    Help.send_massege(message, s, choice, "Client");

//                    // read the mode selected by the client.
//                    choice = Help.read_word(in);
                    // read the word written by the server.
                    String Server_message = Help.read_word(in);

                    // print server word.
                    System.out.println("Server : " + Server_message);

                    if (choice.equals("2")) {
                        // Call decode method to decode the word and then save it in the Server_message object.
                        Server_message = Help.decode(Server_message);
                        // print decoded word.
                        System.out.println("After decoding server message : " + Server_message);
                    }

                } else if (choice.equals("3")) {
                    // inform the server that the user choose quit application.
                    Help.write_word(s, "3");
                    // close the connection.
                    s.close();
                    // change the value of start variable to false.
                    start = false;
                }
            } // close while loop.
        } // close try.
        catch (Exception use) { // open catch.
            System.out.println("Exception:" + use.getMessage());
        } // close catch.
    } // close main method.
} // close Client class.
