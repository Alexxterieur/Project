package musichub.business;

public class ClientConnexion {
    public static void main (String[] args)
    {
        SimpleClient c1 = new SimpleClient();
        c1.connect("localhost");
    }
}
