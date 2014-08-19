package reccos.futball.hirszerzo.c.business;
import org.jivesoftware.smack.XMPPException;



public class Annotator {

    private static String matchID;
    private static String playerID;

    public static void setPlayer(String player){
            playerID = player;
    }

    public static void setMatch(String match){
            matchID = match;
    }

    public static void sendPlayerEvent(String event, String qualifier) throws XMPPException, IllegalStateException, IllegalArgumentException{
            if(matchID == null)
                    throw new IllegalStateException("matchId can't be null.");
            if(playerID == null)
                    throw new IllegalStateException("playerId can't be null.");
            if(event == null)
                    throw new IllegalArgumentException("event can't be null.");
            if(qualifier == null)
                    throw new IllegalArgumentException("qualifier can't be null.");

            String message = "<playerEvent "
                            + "type=\"" + event
                    + "\" qualifier=\"" + qualifier 
                    + "\" matchID=\"" + matchID
                    //+ "\" userID=\"" + username
                    + "\" player=\"" + playerID
                    + "\"/>\n";
            JabberSmackApi.getInstance().sendMessage(message);

    }

    public static void sendRefereeEvent(String qualifier) throws XMPPException, IllegalStateException, IllegalArgumentException{
            if(matchID == null)
                    throw new IllegalStateException("matchId can't be null.");
            if(qualifier == null)
                    throw new IllegalArgumentException("qualifier can't be null.");

            String message = "<refereeEvent "
            + " qualifier=\"" + qualifier 
            + "\" matchID=\"" + matchID
            //  + "\" userID=\"" + username
            + "\"/>\n";
            JabberSmackApi.getInstance().sendMessage(message);
    }
}
