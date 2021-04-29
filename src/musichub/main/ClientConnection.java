package musichub.main;
import musichub.business.*;
import java.util.*;

public class ClientConnection
{
    public static void main (String[] args)
    {
        FirstClient c1 = new FirstClient();
        c1.connect("localhost");
    }
}