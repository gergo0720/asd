package reccos.futball.hirszerzo.c.userinterface;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.LinkedList;
import java.util.List;

import javax.swing.JPanel;
import javax.swing.Timer;


public class AnnotationPanel extends JPanel implements MouseListener,
		MouseMotionListener {

	List<AnnotationButton> buttons = new LinkedList<AnnotationButton>();
	List<String> actions = new LinkedList<String>();
	PlayButton playButton;
	BackButton backButton;
	Timer timer;
	Integer minutes = 0;
	Integer seconds = 0;
	String playerName;
	long startTime;
	int headerHeight = 50;
	public static final int PLAYER_LAYOUT = 1;
	public static final int REFEREE_LAYOUT = 2;
	private int layout_type = 1;

	public AnnotationPanel() {
		setBackground(new Color(243,244,247));
		setupEvents();
		addMouseListener(this);
		addMouseMotionListener(this);
		playButton = new PlayButton(new Rectangle(getPreferredSize().width - 50,20,20,20));
		backButton = new BackButton(new Rectangle(10,20,30,20), true);
		
		recalculateButtons();
		timer = new Timer(1000, new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				seconds++;
				if(seconds == 60){
					minutes++;
					seconds = 0;
				}
				
				if(seconds == 1 && minutes == 0)
					enableButtons();

				repaint();
			}
		});
		timer.setRepeats(true);
		timer.setInitialDelay(0);
	}
	
	private void setupEvents(){
		actions.add("Passz");
		actions.add("Csel");
		actions.add("Szerelés");
		actions.add("Lövés");
		actions.add("Helyezkedés");
		//actions.add("Játékvezetés");
		//actions.add("Sima esemény");
	}
	
	private void enableButtons(){
		for(AnnotationButton b : buttons)
			b.setEnabled(true);
	}

	public void recalculateButtons() {
		buttons.clear();
		if(playButton != null){
			playButton.x = getPreferredSize().width - 50;
			playButton.y = 20;
			playButton.width = 20;
			playButton.height = 20;
		}
		
		boolean enabled = false;
		if(isAnnotationStarted())
			enabled = true;
		
		if(layout_type == REFEREE_LAYOUT){
			Rectangle r = new Rectangle(0, headerHeight, getPreferredSize().width / 2,
					getPreferredSize().height - headerHeight);
			buttons.add(new AnnotationButton(r, true, enabled, "Játékvezetés"));
			r = new Rectangle(getPreferredSize().width / 2, headerHeight, getPreferredSize().width,
					getPreferredSize().height - headerHeight);
			buttons.add(new AnnotationButton(r, false, enabled, "Játékvezetés"));
			return;
		}
		//else...
		
		
		int offset = (getPreferredSize().height - headerHeight)/ actions.size();

		int i = 0;
		for (int y = headerHeight; y <= getPreferredSize().height; y += offset) {

			if(i == actions.size())
				break;
			
			Rectangle r = new Rectangle(0, y, getPreferredSize().width / 2,
					offset);
			buttons.add(new AnnotationButton(r, true, enabled, actions.get(i)));

			r = new Rectangle(getPreferredSize().width / 2, y,
					getPreferredSize().width, offset);
			buttons.add(new AnnotationButton(r, false, enabled, actions.get(i)));
			i++;
		}
	}
	
	public void setLayoutType(int type){
		layout_type = type;
	}

	protected void paintComponent(Graphics g) {

		super.paintComponent(g);		
		Graphics2D g2 = (Graphics2D) g;
		g.setFont(new Font(g.getFont().getFamily(), Font.PLAIN, 18));
		g2.setRenderingHint(
		        RenderingHints.KEY_TEXT_ANTIALIASING,
		        RenderingHints.VALUE_TEXT_ANTIALIAS_ON);		
		g2.setStroke(new BasicStroke(2));
		drawHeader(g2);
		drawButtons(g2);
		drawText(g2);
		g2.dispose();
	}

	public void setName(String name) {
		playerName = name;
		validate();
		repaint();
	}
	
	private void drawPlayButton(Graphics2D g){
		
		g.setColor(playButton.getColor());
		if(!playButton.isPlaying()){
			
			if(!isAnnotationStarted()){
				g.setFont(new Font(g.getFont().getFamily(), Font.PLAIN, 30));
				g.setColor(new Color(50,250,240));
				g.drawString("\u25B6", playButton.x+3, playButton.y+12);
				g.setFont(new Font(g.getFont().getFamily(), Font.PLAIN, 18));
				g.setColor(Color.black);
			}else{
				g.drawString("\u25B6", playButton.x+3, playButton.y+8);
			}
			
		}else{
			g.drawString("\u275A", playButton.x, playButton.y+8);
			g.drawString("\u275A", playButton.x+8, playButton.y+8);
		}
	}
	
	private void drawBackButton(Graphics2D g){
		g.setColor(backButton.getColor());
		g.drawString("<", backButton.x, backButton.y+10);
		g.drawString("<", backButton.x+12, backButton.y+10);
	}

	private void drawButtons(Graphics2D g) {

		for (AnnotationButton button : buttons) {
			g.setColor(button.getColor());
			g.fillRect(button.x, button.y, button.width, button.height);
		}
		int offset = (getPreferredSize().height - headerHeight)/ actions.size();
		g.setColor(Color.BLACK);
		
		if(!isAnnotationStarted())
			return;
		
		if(layout_type == PLAYER_LAYOUT)
		for (int y = headerHeight; y < getPreferredSize().height; y += offset) 
			g.drawLine(0, y, getPreferredSize().width, y);
		
	}
	
	private void drawHeader(Graphics2D g) {
		g.drawString(playerName,  backButton.x + backButton.width + 5, 30);
		String m = minutes > 9 ? minutes.toString() : "0" + minutes;
		String s = seconds > 9 ? seconds.toString() : "0" + seconds;
		g.drawString(m + ":" + s, playButton.x - 55, 30);
		drawPlayButton(g);
		drawBackButton(g);

	}
	
	private void drawText(Graphics2D g){
		if(!isAnnotationStarted()){
			g.drawString("Az annotálás megkezdéséhez",10, 100);
			g.drawString("indítsa el a számlálót",10, 120);
			g.drawString("a \u25B6 gombra kattintva!",10, 140);
			return;
		}
		
		if(layout_type == REFEREE_LAYOUT){
			int stringLen =(int) g.getFontMetrics().getStringBounds("Játékvezetés", g).getWidth();  
			int y = (getPreferredSize().height - headerHeight) / 2;
			int x = getPreferredSize().width / 2;
			g.drawString("Játékvezetés", x - stringLen / 2 , y);
			return;
		}
		//else...
		
		int offset = (getPreferredSize().height - headerHeight)/ actions.size();
		int x = getPreferredSize().width / 2;
		int y = headerHeight + offset / 2;

		for(int i=0; i<actions.size(); i++){
			int stringLen =(int) g.getFontMetrics().getStringBounds(actions.get(i), g).getWidth();  
			g.drawString(actions.get(i),x - stringLen / 2 , y);
			y += offset;
		}
	}

	public void mouseClicked(MouseEvent e) {
	}

	public void mousePressed(MouseEvent e) {
		Point p = new Point(e.getX(), e.getY());
		for (AnnotationButton b : buttons)
			if (b.contains(p))
				b.setPressed(true);
		
		if(playButton.contains(p))
			playButton.setPressed(true);
		if(backButton.contains(p))
			backButton.setPressed(true);
		repaint();
	}

	public void mouseReleased(MouseEvent e) {
		Point p = new Point(e.getX(), e.getY());
		for (AnnotationButton b : buttons) 
			if (b.contains(p)) {
				if(b.isPressed())
					b.click();
				b.setPressed(false);
			}
		
		if(playButton.contains(p)){
			playButton.setPressed(false);
			playButton.click();
			if(playButton.isPlaying())
				timer.start();
			else
				timer.stop();
		}
		
		if(backButton.contains(p)){
			backButton.setPressed(false);
			firePropertyChange("back", false, true);
		}
		repaint();
	}

	public void mouseEntered(MouseEvent e) {
	}

	public void mouseExited(MouseEvent e) {
		for (AnnotationButton b : buttons) {
			b.setHovered(false);
			b.setPressed(false);
		}
		playButton.setHovered(false);
		backButton.setHovered(false);

		repaint();
	}

	public void mouseDragged(MouseEvent e) {
		Point p = new Point(e.getX(), e.getY());
		for (AnnotationButton b : buttons) {
			if (!b.contains(p)){
				b.setHovered(false);
				b.setPressed(false);
			}
		}
		repaint();
	}

	public void mouseMoved(MouseEvent e) {
		Point p = new Point(e.getX(), e.getY());
		for (AnnotationButton b : buttons) {
			if (b.contains(p))
				b.setHovered(true);
			else
				b.setHovered(false);
		}
		
		if(playButton.contains(p))
			playButton.setHovered(true);
		else
			playButton.setHovered(false);
		
		if(backButton.contains(p))
			backButton.setHovered(true);
		else
			backButton.setHovered(false);
		
		repaint();
	}
	
	private boolean isAnnotationStarted(){
		return seconds != 0 || minutes != 0;
	}
	
	
}
