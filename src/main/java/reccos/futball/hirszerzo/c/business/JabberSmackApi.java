package reccos.futball.hirszerzo.c.business;
import java.beans.PropertyChangeListener;

import javax.swing.JOptionPane;
import javax.swing.SwingWorker;

import org.jivesoftware.smack.Chat;
import org.jivesoftware.smack.ConnectionConfiguration;
import org.jivesoftware.smack.MessageListener;
import org.jivesoftware.smack.Roster;
import org.jivesoftware.smack.XMPPConnection;
import org.jivesoftware.smack.XMPPException;
import org.jivesoftware.smack.packet.Message;

public class JabberSmackApi implements MessageListener {

	private static XMPPConnection connection;
    private static Chat chat;
    private static JabberSmackApi smack; 
    final private String masterClientJID = "masterclient@reccos/Smack";
    final static private String serverIP = "reccos.inf.unideb.hu";
    final static private int serverPort = 5222;
	private String username, password;

    
    public Loginer loginer;
    public Connector connector;
    
    private JabberSmackApi(){
    	ConnectionConfiguration config = new ConnectionConfiguration(serverIP,serverPort);
    	config.setSASLAuthenticationEnabled(false);
	    connection = new XMPPConnection(config);
    }
    
    public static JabberSmackApi getInstance(){
    	if(smack == null)
    		smack = new JabberSmackApi(); 
    	return smack;
    }
    
    public void connect(){
		if(connector!= null && !connector.isDone())
			return;
		connector = new Connector();
		connector.execute();
    }
    
    public boolean isConnected(){
    	return connection.isConnected();
    }
    
    public void login(String username, String password, PropertyChangeListener listener){	
    	if(!isConnected())
			connect();

    	if(loginer != null && !loginer.isDone())
    		return;
    	loginer = new Loginer(username, password);
    	loginer.addPropertyChangeListener(listener);
    	loginer.execute();
    }
    
    public void login() throws IllegalStateException{	
    	if(!isConnected())
			connect();

    	if(loginer != null && !loginer.isDone())
    		return;
    	loginer = new Loginer();
    	loginer.execute();
    }
 
    public void sendMessage(String message) throws XMPPException{
    	//System.out.print("sending: " + message);
	    chat = connection.getChatManager().createChat(masterClientJID, this);
	    chat.sendMessage(message);
    }

    public void disconnect(){
	    connection.disconnect();
    }
 
	public void processMessage(Chat chat, Message message){
	    //if(message.getType() == Message.Type.chat)
	    	//System.out.println( " message of masterclient : "/*+ message.getBody().toString()*/);
    }
	
	public void register(String username, String password, String sex, String year, String month, String day, PropertyChangeListener listener){
		RegisterWorker r = new RegisterWorker(username, password, sex, year, month, day);
		r.addPropertyChangeListener(listener);
		r.execute();
	}
	

    private class Connector extends SwingWorker<Void, Void>{

		@Override
		protected Void doInBackground() throws Exception {
			connection.connect();
		    if(connection.isConnected()){
		    	//System.out.println("Successfully connected to server!");
		    	chat = connection.getChatManager().createChat("admin", null);
		    }
			return null;
		}
    }
    
    private class Loginer extends SwingWorker<Void, Void>{

    	private int timeOut = 2000;
    	
    	public Loginer(String user, String pw){
    		username = user;
    		password = pw;
    	}
    	
    	public Loginer() throws IllegalStateException{
    		if(username == null || password == null)
    			throw new IllegalStateException("username and password cannot be null");
    	}
    	
		@Override
		protected Void doInBackground() throws Exception {
			if(!connector.isDone())
				Thread.sleep(timeOut);
			
			//System.out.println("logged in: " + username);
		    username = username.replace('@', '-');
		    try{
		    	connection.login(username, password);
				firePropertyChange("login", false, true);
		    }catch (XMPPException e1) {
				if(e1.getXMPPError().getCode() == 401){
					JOptionPane.showMessageDialog(null, "Rossz felhasználónév vagy jelszó!");
				}else
					JOptionPane.showMessageDialog(null, e1.getMessage());
			}catch (IllegalStateException e2) {
				if(e2.getMessage().compareTo("Already logged in to server.") == 0){
					firePropertyChange("login", false, true);// not an actual error
					return null;
				}
				JOptionPane.showMessageDialog(null, "Cannot connect to server!\nPlease check your internet connection or try again later!");
			}catch (Exception e3) {
				JOptionPane.showMessageDialog(null, e3.getMessage());
			}
		    
			return null;
		}
    }
    
    private class RegisterWorker extends SwingWorker<Void, Void>{

    	String username;
    	String password;
    	String sex;
    	String year;
    	String month;
    	String day;
    	
    	public RegisterWorker(String username, String password, String sex, String year, String month, String day) {
    		this.username = username;
    		this.password = password;
    		this.sex = sex;
    		this.year = year;
    		this.month = month;
    		this.day = day;
    	}
		@Override
		protected Void doInBackground() throws Exception {
			username = username.replace('@', '-');
			try{
				connection.getAccountManager().createAccount(username, password);
			}catch(XMPPException ex){
				if(ex.getXMPPError().getCode() == 409)
					firePropertyChange("register", "none", "used");
				else
					firePropertyChange("register", "none", ex.getMessage() + "hiba");
				return null;
			}
			firePropertyChange("register", "none", "success");
			
			connection.login(username, password);
			
	        Roster roster = connection.getRoster();
            roster.createEntry(masterClientJID, "masterclient", null);
			
	        String registrationMessage ="<registration " + "user=\"" + username
	                                        + "\" passwd=\"" + password + "\" sex=\"" + sex
	                                        + "\" birth=\"" + year + "." + month + "." + day + "."
	                                        + "\"/>\n";
	        //System.out.println("registering: " + registrationMessage );
	        sendMessage(registrationMessage);		
	        return null;
		}
    }
}
