package musichub.main;
import musichub.business.*;
import java.util.*;

import java.io.*;
import java.net.*;

public class ServerConnection
{
    public static void main (String[] args) {
        Server s = Server.getInstance();
        String ip = "localhost";
        s.connect(ip);
    }
}