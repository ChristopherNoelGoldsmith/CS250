import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.Color;

public class SlideShow extends JFrame {

	//Declare Variables
	private JPanel slidePane;
	private JPanel textPane;
	private JPanel buttonPane;
	private CardLayout card;
	private CardLayout cardText;
	private JButton btnPrev;
	private JButton btnNext;
	private JLabel lblSlide;
	private JLabel lblTextArea;

	/**
	 * Create the application.
	 */
	public SlideShow() throws HeadlessException {
		initComponent();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initComponent() {
		//Initialize variables to empty objects
		card = new CardLayout();
		cardText = new CardLayout();
		slidePane = new JPanel();
		textPane = new JPanel();
		textPane.setBackground(Color.WHITE); // CG: Changed the background of the text box to white for readability
		textPane.setBounds(5, 470, 790, 50);
		textPane.setVisible(true);
		buttonPane = new JPanel();
		btnPrev = new JButton();
		btnNext = new JButton();
		lblSlide = new JLabel();
		lblTextArea = new JLabel();

		//Setup frame attributes
		setSize(800, 600);
		setLocationRelativeTo(null);
		setTitle("Top 5 Destinations SlideShow");
		getContentPane().setLayout(new BorderLayout(10, 50));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		//Setting the layouts for the panels
		slidePane.setLayout(card);
		textPane.setLayout(cardText);
		
		//logic to add each of the slides and text
		for (int i = 1; i <= 5; i++) {
			lblSlide = new JLabel();
			lblTextArea = new JLabel();
			lblSlide.setText(getResizeIcon(i));
			lblTextArea.setText(getTextDescription(i));
			slidePane.add(lblSlide, "card" + i);
			textPane.add(lblTextArea, "cardText" + i);
		}

		getContentPane().add(slidePane, BorderLayout.CENTER);
		getContentPane().add(textPane, BorderLayout.SOUTH);

		buttonPane.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 10));

		btnPrev.setText("Previous");
		btnPrev.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				goPrevious();
			}
		});
		buttonPane.add(btnPrev);

		btnNext.setText("Next");
		btnNext.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				goNext();
			}
		});
		buttonPane.add(btnNext);

		getContentPane().add(buttonPane, BorderLayout.SOUTH);
	}

	/**
	 * Previous Button Functionality
	 */
	private void goPrevious() {
		card.previous(slidePane);
		cardText.previous(textPane);
	}
	
	/**
	 * Next Button Functionality
	 */
	private void goNext() {
		card.next(slidePane);
		cardText.next(textPane);
	}
	
	/**
	 * Method to get the images
	 * 
	 * CG ////////
	 * changed image destinations to the new images
	 * 
	 * Images are in resource file and formatted as image<int>.jpeg
	 */
	private String getResizeIcon(int i) {
		//Create string for image
		String image = ""; 
		
		//Get image
		image = getImage(i);
		
		//Return image
		return image;
	}
	
	/*
	 * CG /////
	 * Utility function added to reduce amount of code and streamline the image retrieval process
	 * 
	 * */
	private String getImage(int i) {
		//Create strings for image and image href
		String image = "";
		String imgHref = "/resources/image" + i + ".jpeg";
		// Get image from src href
		image =  "<html><body><img width= '800' height='500' src='" + getClass().getResource(imgHref) + "'</body></html>";
		
		//Return image
		return image;
	}
	
	/**
	 * Method to get the text values
	 * 
	 * CG /////////////
	 * changed text to match the new image and destinations
	 * 
	 * Top line is the type of vacation it is then the bottom line in the slide is the destination
	 */
	private String getTextDescription(int i) {
		String text = ""; 
		if (i==1){
			text = "<html><body><font size='5'>Yoga & Wellness Retreat</font> <br>Mangosteen Ayurveda & Wellness Resort - Thailand</body></html>";
		} else if (i==2){
			text = "<html><body><font size='5'>Wellness Resting</font> <br>Essential Costa Rica - Costa Rica</body></html>";
		} else if (i==3){
			text = "<html><body><font size='5'>Seasonal Detox & Rehab</font> <br>Oceanfront Recovery - Laguna Beach, CA</body></html>";
		} else if (i==4){
			text = "<html><body><font size='5'>Woman's Wellness Spa</font> <br>Sage Wellness - The Aria Las Vegas</body></html>";
		} else if (i==5){
			text = "<html><body><font size='5'>All Inclusive Detox</font> <br>Mirbeau Inn & Spa Rhinebeck - Hudson Valley, NY</body></html>";
		}
		return text;
	}
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {

			@Override
			public void run() {
				SlideShow ss = new SlideShow();
				ss.setVisible(true);
			}
		});
	}
}