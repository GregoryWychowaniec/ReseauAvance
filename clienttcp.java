import java.io.*;
import java.net.*;

public class clienttcp {
  private static int port;

  public static void main (String [] args) throws Exception {
    String adresse, line, lineReversed;
    Reader readSoc;
    PrintStream sortie=null;

    if(args.length != 2)	{
      System.out.println("usage : java client nom_serveur port");
      System.exit(0);
    }
    adresse = args[0];
    port = new Integer(args[1]).intValue();

    Socket socket = new Socket(adresse, port);

    Reader reader = new InputStreamReader(System.in);
    BufferedReader keyboard = new BufferedReader(reader);

    sortie = new PrintStream(socket.getOutputStream());
    readSoc = new InputStreamReader(socket.getInputStream());
    BufferedReader keyboardSoc = new BufferedReader(readSoc);

    while (true) {
      System.out.println("Entrez une ligne de texte : ");
      line = keyboard.readLine();

      sortie.println(line);
      // si on a tape "FIN" on quitte le client
      if(line.equals("FIN"))
        break;

      lineReversed = keyboardSoc.readLine();
      System.out.println("Recu : " + lineReversed);
    }
    // fermeture de la socket
    socket.close();
  }
}
