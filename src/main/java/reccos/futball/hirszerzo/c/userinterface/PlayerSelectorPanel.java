package reccos.futball.hirszerzo.c.userinterface;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.DefaultListCellRenderer;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;

import reccos.futball.hirszerzo.c.business.Annotator;
import reccos.futball.hirszerzo.c.business.XML;


public class PlayerSelectorPanel extends JPanel{
	String[] players;
//        {"   Játékvezető", "1  Vukasin Polekszics", "4  JoelDamahou", "6  Zsidai László",
//			"8  Selim Bouadla", "10 Rene Mihelic", "11 Ferenczi János", "13 Lázár Pál", "14 Vadnai Dániel",
//			"17 Mészáros Norbert", "18 Máté Péter", "19 Dalibor Volas", "21 Ludánszki Bence", "24 Igor Morozov", "25 Dusan Brkovics",
//			"26 Ibrahima Sidibe", "27 Bódi Ádám", "28 Nagy Zoltán", "33 Varga József", "44 Tisza Tibor",
//			"45 Nenad Novakovics", "55 Szakály Péter", "69 Korhut Mihály", "70 Kulcsár Tamás", "87 Verpecz István",
//			"88 L'imam Seydi"};
	
	JList<String> playerList;
	JScrollPane scrollPane;
	JButton selectButton;
	XML xml;
        public static Boolean isPlayerSelected = false;
        
	public PlayerSelectorPanel(Dimension size) {
                xml = new XML();
                players = XML.homeTeam.players.toArray(new String[XML.homeTeam.players.size()]);
                playerList = new JList<String>(players);
                scrollPane = new JScrollPane(playerList);
                selectButton = new JButton("Személy választása");

		setBackground(new Color(243,244,247));

		BoxLayout layout = new BoxLayout(this, BoxLayout.Y_AXIS);
		setBorder(new EmptyBorder(5, 5, 5, 5));
		setLayout(layout);
		

		playerList.setFixedCellHeight(25);
		int maxHeight = players.length * 25+3 > size.height-100 ? size.height - 100 : players.length * 25+3;
		playerList.setMaximumSize(new Dimension(size.width*2, maxHeight));
		scrollPane.setMaximumSize(new Dimension(size.width*2, maxHeight));
		
		add(scrollPane);
		add(selectButton);
		selectButton.setSize(new Dimension(size.width*2, 100));
		selectButton.setAlignmentX(CENTER_ALIGNMENT);
		
		Font font = new Font("Monospaced", Font.BOLD, 16);
		playerList.setCellRenderer(new SelectedListCellRenderer());
		playerList.setFont(font);
		playerList.setSelectedIndex(0);
		//homePlayerList.addListSelectionListener(this);
		
		
		selectButton.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				Annotator.setPlayer(getSelectedName());
				Annotator.setMatch("matchID");
				firePropertyChange("player", "nobody", getSelectedName());
                                isPlayerSelected = true;
				return;
			}
		});
	}
	
	public String getSelectedName(){
		return players[playerList.getSelectedIndex()].substring(3);
	}
	
	public class SelectedListCellRenderer extends DefaultListCellRenderer 
	{
		public SelectedListCellRenderer() {
		}
	     @Override
	     public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus){
	         Component c = super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
	         if (index % 2 == 1)
	             c.setBackground(new Color(243,244,247));
	         else
	        	 c.setBackground(new Color(233,234,237));
	         
	         if(isSelected == true)
	        	 c.setBackground(new Color(150,150,250));
	         setText(" " + getText());
	         return c;
	     }
	}
}
