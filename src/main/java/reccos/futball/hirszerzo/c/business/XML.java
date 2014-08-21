package reccos.futball.hirszerzo.c.business;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Vector;
 
public class XML {
 
	public static class homeTeam
	{		
		public static String teamName;
		public static String teamRefid;
		public static Vector<String> players;
		public static Vector<String> playersStartings;
		public static Vector<String> playersStarting;
		public static Vector<String> playersSubs;
		public static Vector<String> playersSub;
		public static Vector<String> playerRefids;
		public static Vector<String> playerStartingRefids;
		public static Vector<String> playerSubRefids;
		public static Vector<String> playerNumbers;
		public static Vector<String> playerStartingNumbers;
		public static Vector<String> playerSubNumbers;
		public static Vector<Boolean> playerIsSubstitute;
		public static Vector<String> playerPositions;
		public static Vector<String> playerStartingPositions;
		public static Vector<String> playerSubPositions;
		public static Vector<String> coach;
		public static String coachName;
		public static String coachRefid;
		public static Vector<String> textToShow;
		public static Vector<String> playersWithNumbers;
		
		homeTeam()
		{
			players = new Vector<String>();
			playersWithNumbers = new Vector<String>();
			playersStarting = new Vector<String>();
			playersStartings = new Vector<String>();
			playersSub = new Vector<String>();
			playersSubs = new Vector<String>();
			playerRefids = new Vector<String>();
			playerStartingRefids = new Vector<String>();
			playerSubRefids = new Vector<String>();
			playerNumbers = new Vector<String>();
			playerStartingNumbers = new Vector<String>();
			playerSubNumbers = new Vector<String>();
			playerIsSubstitute = new Vector<Boolean>();
			playerPositions = new Vector<String>();
			playerStartingPositions = new Vector<String>();
			playerSubPositions = new Vector<String>();
			coach = new Vector<String>();
		}
	}
	
	public static class awayTeam
	{	
		public static String teamName;
		public static String teamRefid;
		public static Vector<String> players;
		public static Vector<String> playersStartings;
		public static Vector<String> playersStarting;
		public static Vector<String> playersSubs;
		public static Vector<String> playersSub;
		public static Vector<String> playerRefids;
		public static Vector<String> playerStartingRefids;
		public static Vector<String> playerSubRefids;
		public static Vector<String> playerNumbers;
		public static Vector<String> playerStartingNumbers;
		public static Vector<String> playerSubNumbers;
		public static Vector<Boolean> playerIsSubstitute;
		public static Vector<String> playerPositions;
		public static Vector<String> playerStartingPositions;
		public static Vector<String> playerSubPositions;
		public static Vector<String> coach;
		public static String coachName;
		public static String coachRefid;
		public static Vector<String> textToShow;
		public static Vector<String> playersWithNumbers;

		awayTeam()
		{
			players = new Vector<String>();
			playersWithNumbers = new Vector<String>();
			playersStarting = new Vector<String>();
			playersStartings = new Vector<String>();
			playersSub = new Vector<String>();
			playersSubs = new Vector<String>();
			playerRefids = new Vector<String>();
			playerStartingRefids = new Vector<String>();
			playerSubRefids = new Vector<String>();
			playerNumbers = new Vector<String>();
			playerStartingNumbers = new Vector<String>();
			playerSubNumbers = new Vector<String>();
			playerIsSubstitute = new Vector<Boolean>();
			playerPositions = new Vector<String>();
			playerStartingPositions = new Vector<String>();
			playerSubPositions = new Vector<String>();
			coach = new Vector<String>();
		}
	}

	public static Vector<String> referee;
	public static Vector<String> refereeId;
	public static String refereeName;
	public static String refereeRefid;

	public static String selectedMatchRefid;
	public static String selectedTeamRefid;
	public static String selectedPersonRefid;

	public static String location;
	public static String locationRefid;
	
	public static String finalResult;
	public static String finalResultHome;
	public static String finalResultAway;
	
	public static String halfTimeResult;
	public static String halfTimeResultHome;
	public static String halfTimeResultAway;

	public static String date;
	public static String dateRefid;

	public static String competition;
	public static String competitionRefid;


	public XML()
	{	
		referee = new Vector<String>();
		XML.homeTeam homeTeam = new XML.homeTeam();
		XML.awayTeam awayTeam = new XML.awayTeam();

		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
	    DocumentBuilder db = null;
	    InputStream inputStream = null;
	   
	    try 
	    {
                inputStream = getClass().getResourceAsStream("/match.xml");
			
	    	
	    	InputStreamReader input = new InputStreamReader(inputStream);
	        db = dbf.newDocumentBuilder();
	        InputSource is = new InputSource();
	        is.setCharacterStream( input);
	        
	        try 
	        {
	            Document doc = db.parse(is);
	        	doc.getDocumentElement().normalize();
	        	
	        	setupHomeTeam(doc);
	        	setupAwayTeam(doc);
    	
	        	Node node= doc.getElementsByTagName("referee").item(0);
	        	Element element = (Element) node;
	        	refereeName = element.getTextContent().toString();
	        	referee.add(refereeName);
	
				node= doc.getElementsByTagName("location").item(0);
	        	element = (Element) node;
	        	location = element.getTextContent().toString();
	        	locationRefid = element.getAttribute("refid").toString();
	        	
	        	node= doc.getElementsByTagName("finalResult").item(0);
	        	element = (Element) node;
	        	finalResultHome = element.getAttribute("home").toString();
	        	finalResultAway = element.getAttribute("away").toString();
	        	
	        	node= doc.getElementsByTagName("halfTimeResult").item(0);
	        	element = (Element) node;
	        	halfTimeResultHome = element.getAttribute("home").toString();
	        	halfTimeResultAway = element.getAttribute("away").toString();
	        	
	        	node= doc.getElementsByTagName("date").item(0);
	        	element = (Element) node;
	        	date = element.getTextContent().toString();
	        	dateRefid = element.getAttribute("refid").toString();
	        	
	        	node= doc.getElementsByTagName("competition").item(0);
	        	element = (Element) node;
	        	competition = element.getTextContent().toString();
	        	competitionRefid = element.getAttribute("refid").toString();          	
	        } 
	        catch (SAXException e ) 
	        {
	        	System.out.println(e);
	        } 
	        catch (IOException e) 
	        {
	        	System.out.println(e);
	        }
	    } 
	    catch (ParserConfigurationException e1) 
	    {
        	System.out.println(e1);
	    }
    }
	
	private void setupHomeTeam(Document doc)
	{
		XML.homeTeam.teamName = doc.getElementsByTagName("homeTeam").item(0).getAttributes().item(0).getTextContent().toString();
		XML.homeTeam.teamRefid = doc.getElementsByTagName("homeTeam").item(0).getAttributes().item(1).getTextContent().toString();
		XML.homeTeam.coachName = "Garami József";
		XML.homeTeam.coachRefid = "123456";
		XML.homeTeam.coach.add(XML.homeTeam.coachName);
    	NodeList list= doc.getElementsByTagName("player");
    	int playerCounter = 0;

    	
    	
    	//home team játékosainak megszámlálása
    	for(int i = 0;i < list.getLength();i++)
    	{
    		Node Nodes = list.item(i);     		
    		if ( Nodes.getNodeType() == Node.ELEMENT_NODE)
        	{
        		Element element = (Element) Nodes.getParentNode();
        		if(element.getNodeName().toString().compareTo("homeTeam")==0)
        			playerCounter++;  			
        	}	      		
    	}
	
    	//home team class vektorainak feltöltése
    	for (int  i = 0; i < playerCounter; i++)
    	{	
    		Node Nodes = list.item(i);
    		
    		if ( Nodes.getNodeType() == Node.ELEMENT_NODE)
    		{
    			Element element = (Element) Nodes;
    				
    			XML.homeTeam.players.add(i, element.getTextContent());			
    			XML.homeTeam.playerRefids.add(i, element.getAttribute("refid"));
    			XML.homeTeam.playerNumbers.add(i, element.getAttribute("number"));
    			XML.homeTeam.playerPositions.add(i, element.getAttribute("position"));
    			XML.homeTeam.playerIsSubstitute.add(i, element.getAttribute("substitute").toString().compareTo("true") == 0 ? true : false );
    			
    		}
    	}
    	
    	for(int i = 0; i < XML.homeTeam.players.size(); i++)
    		XML.homeTeam.playersWithNumbers.add(
    				XML.homeTeam.players.elementAt(i) + "  " + XML.homeTeam.playerNumbers.elementAt(i));
    	
    	//home team kezdőjének és cseréinek meghatározása
    	int startingPlayerCounter = 0;
    	int substituePlayerCounter = 0;
    	
    	for (int  i = 0; i < playerCounter; i++)
    	{
    		Node Nodes = list.item(i);
    		
    		if ( Nodes.getNodeType() == Node.ELEMENT_NODE)
    		{
    			Element element = (Element) Nodes.getChildNodes();
    			
    			if ( element.getAttribute("substitute").toString().compareTo("false") == 0 )
    			{
    				startingPlayerCounter++;
    				XML.homeTeam.playersStartings.add(XML.homeTeam.playerNumbers.elementAt(i)+" - "+ element.getTextContent() + " " + "("+XML.homeTeam.playerPositions.elementAt(i)+")" );
    				XML.homeTeam.playersStarting.add(XML.homeTeam.players.elementAt(i));
    				XML.homeTeam.playerStartingRefids.add(XML.homeTeam.playerRefids.elementAt(i));
        			XML.homeTeam.playerStartingNumbers.add(XML.homeTeam.playerNumbers.elementAt(i));
        			XML.homeTeam.playerStartingPositions.add(XML.homeTeam.playerPositions.elementAt(i));
    			}
    			else
    			{
    				substituePlayerCounter++;
    				XML.homeTeam.playersSubs.add(XML.homeTeam.playerNumbers.elementAt(i)+" - "+ element.getTextContent() + " " + "("+XML.homeTeam.playerPositions.elementAt(i)+")");
    				XML.homeTeam.playersSub.add(XML.homeTeam.players.elementAt(i));
    				XML.homeTeam.playerSubRefids.add(XML.homeTeam.playerRefids.elementAt(i));
        			XML.homeTeam.playerSubNumbers.add(XML.homeTeam.playerNumbers.elementAt(i));
        			XML.homeTeam.playerSubPositions.add(XML.homeTeam.playerPositions.elementAt(i));
    			}
    		}
    	}
    	
    	

    	
	}
	private void setupAwayTeam(Document doc)
	{
		XML.awayTeam.teamName = doc.getElementsByTagName("awayTeam").item(0).getAttributes().item(0).getTextContent().toString();
		XML.awayTeam.teamRefid = doc.getElementsByTagName("awayTeam").item(0).getAttributes().item(1).getTextContent().toString();
		XML.awayTeam.coachName = "Martins Teixeira Gomes José Manuel";
		XML.awayTeam.coachRefid = "654321";
		XML.awayTeam.coach.add(XML.awayTeam.coachName);

    	NodeList list= doc.getElementsByTagName("player");
    	int playerCounter = 0;
    	
    	//away team játékosainak megszámlálása
    	for(int i = 0;i < list.getLength();i++)
    	{
    		Node Nodes = list.item(i);     		
    		if ( Nodes.getNodeType() == Node.ELEMENT_NODE)
        	{
        		Element element = (Element) Nodes.getParentNode();
        		if(element.getNodeName().toString().compareTo("awayTeam")==0)
        			playerCounter++;
        			
        	}	      		
    	}
    	
    	//away team class  vektorainak feltöltése
    	for (int  i = playerCounter; i < list.getLength(); i++)
    	{
    		
    		Node Nodes = list.item(i);
    		
    		if ( Nodes.getNodeType() == Node.ELEMENT_NODE)
    		{
    			Element element = (Element) Nodes;
    				
    			XML.awayTeam.players.add(i - playerCounter, element.getTextContent());			
    			XML.awayTeam.playerRefids.add(i - playerCounter, element.getAttribute("refid"));
    			XML.awayTeam.playerNumbers.add(i - playerCounter, element.getAttribute("number"));
    			XML.awayTeam.playerPositions.add(i - playerCounter, element.getAttribute("position"));
    			XML.awayTeam.playerIsSubstitute.add(i - playerCounter, element.getAttribute("substitute").toString().compareTo("true") == 0 ? true : false );
    			   		
    		}
    	}
    	
    	for(int i = 0; i < XML.awayTeam.players.size(); i++)
    		XML.awayTeam.playersWithNumbers.add(
    				XML.awayTeam.players.elementAt(i) + "  " + XML.awayTeam.playerNumbers.elementAt(i));
    	
    	//away team kezdőjének és cseréinek meghatározása
    	int startingPlayerCounter = 0;
    	int substituePlayerCounter = 0;
    	
    	for (int  i = playerCounter; i < list.getLength(); i++)
    	{
    		Node Nodes = list.item(i);
    		
    		if ( Nodes.getNodeType() == Node.ELEMENT_NODE)
    		{
    			Element element = (Element) Nodes.getChildNodes();
    			
    			if ( element.getAttribute("substitute").toString().compareTo("false") == 0 )
    			{
    				startingPlayerCounter++;
    				XML.awayTeam.playersStartings.add(XML.awayTeam.playerNumbers.elementAt(i-playerCounter) +" - "+ element.getTextContent() + " " + "("+XML.awayTeam.playerPositions.elementAt(i-playerCounter)+")");
    				XML.awayTeam.playersStarting.add(XML.awayTeam.players.elementAt(i-playerCounter));
    				XML.awayTeam.playerStartingRefids.add(XML.awayTeam.playerRefids.elementAt(i-playerCounter));
        			XML.awayTeam.playerStartingNumbers.add(XML.awayTeam.playerNumbers.elementAt(i-playerCounter));
        			XML.awayTeam.playerStartingPositions.add(XML.awayTeam.playerPositions.elementAt(i-playerCounter));
    			}
    			else
    			{
    				substituePlayerCounter++;
    				XML.awayTeam.playersSubs.add(XML.awayTeam.playerNumbers.elementAt(i-playerCounter) + " - "+ element.getTextContent() + " " + "("+XML.awayTeam.playerPositions.elementAt(i-playerCounter)+")");
    				XML.awayTeam.playersSub.add(XML.awayTeam.players.elementAt(i-playerCounter));
    				XML.awayTeam.playerSubRefids.add(XML.awayTeam.playerRefids.elementAt(i-playerCounter));
        			XML.awayTeam.playerSubNumbers.add(XML.awayTeam.playerNumbers.elementAt(i-playerCounter));
        			XML.awayTeam.playerSubPositions.add(XML.awayTeam.playerPositions.elementAt(i-playerCounter));
    			}
    		}
    	}
	}		

}