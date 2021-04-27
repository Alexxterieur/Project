package musichub.business;
import musichub.main.*;
import java.io.*;
import java.net.*;

public class SimpleClient {
    private ObjectOutputStream output;
    private ObjectInputStream input;
    private Socket socket;

    public void connect(String ip)
    {
        int port = 6666;
        try  {
            //create the socket; it is defined by an remote IP address (the address of the server) and a port number
            socket = new Socket(ip, port);

            //create the streams that will handle the objects coming and going through the sockets
            output = new ObjectOutputStream(socket.getOutputStream());
            input = new ObjectInputStream(socket.getInputStream());

            String textToSend = new String("Connect me!");
            System.out.println("text sent to the server: " + textToSend);
            output.writeObject(textToSend);		//serialize and write the String to the stream

            Main main = (Main) input.readObject();	//deserialize and read the Student object from the stream
            System.out.println("L'application se lance");
        } catch  (UnknownHostException uhe) {
            uhe.printStackTrace();
        }
        catch  (IOException ioe) {
            ioe.printStackTrace();
        }
        catch  (ClassNotFoundException cnfe) {
            cnfe.printStackTrace();
        }
        finally {
            try {
                input.close();
                output.close();
                socket.close();
            } catch (IOException ioe) {
                ioe.printStackTrace();
            }
        }
    }
}
