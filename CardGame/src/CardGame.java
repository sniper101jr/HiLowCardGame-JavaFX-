import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.time.Duration;

import javafx.animation.FadeTransition;
import javafx.animation.PauseTransition;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

public class CardGame extends Application {
	/*
	 * Name: Tochukwu Michael Chizea
	 * Student Number: 2981920
	 * 
	 * 
	 * *\
	 */

	DeckOfCards dc= new DeckOfCards();


	Card card1 = new Card();
	Card card2 = new Card();

	Label lblFirstCard, lblNextCard, lblSecCard, lblWinLoseMessage, lblStudentName, 
	lblStudentNum, lblWins, lblwinCounter, lblCardsLeft, lblCardsCounter ,lblWinCongratulations;

	Button  btnClose;

	RadioButton rbHigher,rbLower;

	ToggleGroup tgGroup, tgBtn;

	MenuBar mBar;

	Menu mFile,mHelp;

	ProgressBar pbar;

	ProgressIndicator pInd;

	Image image1,image2;

	ImageView imgView1, imgView2;

	ToggleButton btnDealFirstCard, btnDealSecondCard;
	
	double winCount1;
	
	double cardLeft;
	
	public CardGame() {
		// TODO Auto-generated constructor stub

	}

	@Override
	public void init() throws Exception{

		//

		//menu bar
		mBar = new MenuBar();

		//menu
		mFile = new Menu("File");
		mHelp = new Menu("Help");

		//items in the file
		MenuItem fileNewGame = new MenuItem("New Game");
		mFile.getItems().add(fileNewGame);
		fileNewGame.setOnAction(ae -> {

			//start the game up by declaring the set of cards and shuffle them

			dc.deckOfCards();
			dc.shuffle();

			
				
					try {
						image1 = new Image(new FileInputStream("cards/card_back_red.png"));
					} catch (FileNotFoundException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
			
		
				try {
					image2 = new Image(new FileInputStream("cards/card_back_red.png"));
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			
			
			


			//resetting the value of both progress bar to indicate the start of a new game
			pbar.setProgress(0);

			pInd.setProgress(0);  
			
			
			//tells the user a new game has begun
			lblWinLoseMessage.setText("New Game Started!!");
			lblWinLoseMessage.setFont(Font.font("Copperplate Gothic Bold",FontWeight.BOLD,FontPosture.REGULAR,15));  
			lblWinLoseMessage.setTextFill(Color.BLUE);

			btnDealSecondCard.setDisable(true);
			btnDealFirstCard.setDisable(false);
			
			//sets image to the imageview
			imgView1.setImage(image1);
			imgView2.setImage(image2);
			
			//reset all the viarbale that would have been used if a game was still going on and new game was cliked
			lblwinCounter.setText(" ");
			winCount1= 0;
			
			cardLeft -- ;
			cardLeft = 52;
			String crdsLeftString = Double.toString(cardLeft);
			lblCardsCounter.setText(crdsLeftString);
			
			lblCardsCounter.setText("");


			
		});//end new games

		
		MenuItem fileShuffle = new MenuItem("Shuffle");
		mFile.getItems().add(fileShuffle);
		fileShuffle.setOnAction(ae -> {
			dc.shuffle();
			
			
			
			Shuffling();
			

		});//end shuffle

		//exit
		MenuItem fileExit = new MenuItem("Exit");
		mFile.getItems().add(fileExit);
		fileExit.setOnAction(ae -> Platform.exit());
		//end exit

		//items in the help that links to a new dialog about a new  About dialog
		MenuItem helpAbout = new MenuItem("About");
		mHelp.getItems().add(helpAbout);
		helpAbout.setOnAction(ae -> AboutDialog());



		//labels declarations
		lblFirstCard = new Label("First Card Dealt: ");

		lblNextCard = new Label("Next Card Will be: "); 
		lblSecCard = new Label("Second Card Dealt: ");
		lblCardsLeft = new Label("Cards Left In The Deck");
		lblCardsCounter = new Label(" ");
		lblWinLoseMessage = new Label(" ");	
		lblWins = new Label("Wins:");
		lblwinCounter = new Label("");
		imgView1 = new ImageView();
		lblWinCongratulations = new Label("");


		//Stliyling for the label different font and font size is declared here
		lblCardsLeft.setFont(Font.font("Britannic Bold",FontWeight.BOLD,FontPosture.REGULAR,16));  
		lblCardsLeft.setTextFill(Color.BLACK);
		
		//Stliyling for the label
		lblCardsCounter.setFont(Font.font("Britannic Bold",FontWeight.BOLD,FontPosture.REGULAR,20));  
		lblCardsCounter.setTextFill(Color.RED);
		

		
		
		
	

		//buttons delacrtions
		btnDealFirstCard = new ToggleButton("<- Deal First Card");
		//btnDealFirstCard = new Button("<- Deal First Card");
		
		
		btnDealFirstCard.setOnAction(ae -> {
	
			card1 = dc.dealTopCard();
			
			//Initializing path of the media file  
	        String path = "cards/deal_.mp3";  
	          
	        //Instantiating Media class  
	        Media media = new Media(new File(path).toURI().toString());  
	          
	        //Instantiating MediaPlayer class   
	        MediaPlayer mediaPlayer = new MediaPlayer(media);  
	          
	        //by setting this property to true, the audio will be played   
	        mediaPlayer.setAutoPlay(true); 
			
			--cardLeft;

			//CARDSflet is defined in class scope then once you click on a deal button a card leave the deck so it just continues to reduce the cards
			//until the deck is empty then it resets when you clcim on the new game menu item
			//the value of the card is then parsed to a string and saved in a variable and that variable is then used tO set text to the label so you can see the effect of the cards reduceing
			
			String crdsLeftString = Double.toString(cardLeft);
			lblCardsCounter.setText(crdsLeftString);

			btnDealSecondCard.setDisable(true);
			btnDealFirstCard.setDisable(false);
			
			

			if (dc.isEmpty()) {

				lblWinLoseMessage.setText("Empty Deck, Game Over");
				lblWinLoseMessage.setFont(Font.font("Abyssinica SIL",FontWeight.BOLD,FontPosture.REGULAR,15));  
				lblWinLoseMessage.setTextFill(Color.BLACK);


				btnDealSecondCard.setDisable(true);
				btnDealFirstCard.setDisable(true);
			}else {


				image1 = new Image(card1.toString(), 250, 200, true, true);

				//Setting the image view 1 
				imgView1.setImage(image1); 

				//set the toggle for the togglebutton  true once it has been selected and
				// set the other button to false so that both button are mutually exclusive
				btnDealSecondCard.setDisable(false);
				btnDealFirstCard.setDisable(true);

				

			}



		});

		btnDealSecondCard = new ToggleButton("Deal Second Card -> ");
		//btnDealSecondCard = new Button(" Deal Second Card -> ");
		
		btnDealSecondCard.setOnAction(ae -> {
			
			//Initialising path of the media file  
	        String path = "cards/deal_.mp3";  
	          
	        //Instantiating Media class  
	        Media media = new Media(new File(path).toURI().toString());  
	          
	        //Instantiating MediaPlayer class   
	        MediaPlayer mediaPlayer = new MediaPlayer(media);  
	          
	        //by setting this property to true, the audio will be played   
	        mediaPlayer.setAutoPlay(true); 
	        
	        //the first thing that happens is a card is dealt most likely the card at the top of the stack
	        //then it checks if it is empty 
	        //after that it then gets the string representation of a card and then to the passes it to an image
	        //then it then takes the image and passes it to the image view
	        //then it compares the two cards which has already been declared at class level
	        //it compare for all the condition set then if the user wins 5 in a row it prompts them

			card2 = dc.dealTopCard();
			
		
			//CARDSflet is defined in class scope then once you click on a deal button a card leave the deck so it just continues to reduce the cards
			//until the deck is empty then it resets when you click on the new game menu item
			-- cardLeft;
			String crdsLeftString = Double.toString(cardLeft);
			lblCardsCounter.setText(crdsLeftString);

			if (dc.isEmpty()) {

				lblWinLoseMessage.setText("Empty Deck, Game Over");
				lblWinLoseMessage.setFont(Font.font("Abyssinica SIL",FontWeight.BOLD,FontPosture.REGULAR,15));  
				lblWinLoseMessage.setTextFill(Color.BLACK);

				btnDealSecondCard.setDisable(true);
				btnDealFirstCard.setDisable(true);
			}else {

				image2 = new Image(card2.toString(), 250, 200, true, true);

				//Setting the image view 1 
				imgView2.setImage(image2); 

				btnDealSecondCard.setDisable(true);
				btnDealFirstCard.setDisable(false);

				if(card2.rankIsGreaterThan(card1) && rbHigher.isSelected()) {

					//Get the current progress.
					double progValue = pbar.getProgress();
					//Increase it by 0.1.
					progValue = progValue + 0.2;

					pbar.setProgress(progValue);

					pInd.setProgress(progValue);
				
					
					//count number of wins baseed on the user wins and then reset it back to 0
					//when the user looses a game
					++ winCount1 ;
					
					String winCountStr = Double.toString(winCount1);
					lblwinCounter.setText(winCountStr);
					

					lblWinLoseMessage.setText("Higher,You Win!!");
					lblWinLoseMessage.setFont(Font.font("Copperplate Gothic Bold",FontWeight.BOLD,FontPosture.REGULAR,15));  
					lblWinLoseMessage.setTextFill(Color.GREEN);


					if (progValue ==  1.0 )
					{
						lblWinLoseMessage.setText("Congratulations You Win");
					

						lblWinLoseMessage.setFont(Font.font("Copperplate Gothic Bold",FontWeight.BOLD,FontPosture.REGULAR,13));  
						lblWinLoseMessage.setTextFill(Color.GREEN);

						btnDealSecondCard.setDisable(true);
						btnDealFirstCard.setDisable(true);

						lblwinCounter.setText("5");

					}




				}
				else if (card2.rankIsLessThan(card1) && rbLower.isSelected()) {

					//Get the current progress.
					double progValue = pbar.getProgress();
					//Increase it by 0.1.
					progValue = progValue + 0.2;

					pbar.setProgress(progValue);

					pInd.setProgress(progValue);
				
					//win count and adds a number to the wincounter when a user wins a game
					++ winCount1;
					
					String winCountStr = Double.toString(winCount1);
					lblwinCounter.setText(winCountStr);




					lblWinLoseMessage.setText("Lower,You Win!!");
					lblWinLoseMessage.setFont(Font.font("Copperplate Gothic Bold",FontWeight.BOLD,FontPosture.REGULAR,15));  
					lblWinLoseMessage.setTextFill(Color.GREEN);

					if (progValue ==  1.0 )
					{
						lblWinLoseMessage.setText("Game Over You Win");
						lblWinCongratulations.setText("Congratulations");
						lblWinLoseMessage.setFont(Font.font("Copperplate Gothic Bold",FontWeight.BOLD,FontPosture.REGULAR,11));  
						lblWinLoseMessage.setTextFill(Color.GREEN);

						btnDealSecondCard.setDisable(true);
						btnDealFirstCard.setDisable(true);

						lblwinCounter.setText("5");


					}


				}

				else if (card2.rankIsLessThan(card1) && rbHigher.isSelected()) {

					//Get the current progress.
					double progValue = pbar.getProgress();
					//Increase it by 0.1.
					progValue = progValue + 0.0;

					progValue = 0;

					pbar.setProgress(progValue);

					pInd.setProgress(progValue);
					
					//count number of wins based on the user wins and then reset it back to 0
					//the number is then parsed to a stiring and the string is set to the label so users can view it
					//when the user looses a game
					winCount1= 0;
					String winCountStr = Double.toString(winCount1);
					lblwinCounter.setText(winCountStr);
					
					
					

					lblWinLoseMessage.setText("Lower,You Lose");
					lblWinLoseMessage.setFont(Font.font("Copperplate Gothic Bold",FontWeight.BOLD,FontPosture.REGULAR,15));  
					lblWinLoseMessage.setTextFill(Color.RED);

				}


				else if (card2.rankIsGreaterThan(card1) && rbLower.isSelected()) {

					//Get the current progress.
					double progValue = pbar.getProgress();
					//Increase it by 0.1.
					progValue = progValue + 0.0;

					progValue = 0;

					pbar.setProgress(progValue);

					pInd.setProgress(progValue);
		
					//resets the winconnter to 0 because the user has lost a game
					winCount1= 0;
					String winCountStr = Double.toString(winCount1);
					lblwinCounter.setText(winCountStr);
					
					

					lblWinLoseMessage.setText("Higher,You Lose");
					lblWinLoseMessage.setFont(Font.font("Copperplate Gothic Bold",FontWeight.BOLD,FontPosture.REGULAR,15));  
					lblWinLoseMessage.setTextFill(Color.RED);

				}
				else if(card2.rankIsEqualsTo(card1) && rbLower.isSelected() || rbHigher.isSelected() )  {

					//Get the current progress.
					double progValue = pbar.getProgress();
					//Increase it by 0.1.
					progValue = progValue + 0.0;

					progValue = 0;

					pbar.setProgress(progValue);

					pInd.setProgress(progValue);
					
					//resets the winconnter to 0 because the user has lost a game				
					winCount1= 0;
					String winCountStr = Double.toString(winCount1);
					lblwinCounter.setText(winCountStr);
					


					lblWinLoseMessage.setText("Cards are Equal,You Lose");
					lblWinLoseMessage.setFont(Font.font("Copperplate Gothic Bold",FontWeight.BOLD,FontPosture.REGULAR,11));  
					lblWinLoseMessage.setTextFill(Color.RED);
				}





			}


		});
		
		//end button declration



		//progress bar and indicator
		pbar = new ProgressBar(0);
		pInd = new ProgressIndicator(0);
		pbar.setMinWidth(300);



		//rabdio buttton
		rbHigher = new RadioButton("Higher");
		rbLower = new RadioButton("Lower");

		//togglegroup
		tgGroup = new ToggleGroup();
		tgBtn = new ToggleGroup();

		//set toggle group for radiobutton
		rbHigher.setToggleGroup(tgGroup);
		rbLower.setToggleGroup(tgGroup);

		//set toggle group for button
		btnDealFirstCard.setToggleGroup(tgBtn);
		btnDealSecondCard.setToggleGroup(tgBtn);

		btnDealFirstCard.setSelected(true);




		//Add menus to the menubar.
		mBar.getMenus().add(mFile);
		mBar.getMenus().add(mHelp);


	}

	public void AboutDialog() {
		// TODO Auto-generated method stub
		Stage nStage = new Stage();
		
		//used a new dialog to define the about us menu items that shows my name and student number

		nStage.setHeight(200);
		nStage.setWidth(350);
		nStage.setResizable(false);

		nStage.setTitle("About Us");

		lblStudentName= new Label("Student Name: Tochukwu Michael Chizea");
		lblStudentNum= new Label("Student Number: 2981920");
		
		lblStudentName.setFont(Font.font("Aharoni",FontWeight.BOLD,FontPosture.REGULAR,15));  
		lblStudentName.setTextFill(Color.BLACK);
		
		lblStudentNum.setFont(Font.font("Aharoni",FontWeight.BOLD,FontPosture.REGULAR,15));  
		lblStudentNum.setTextFill(Color.BLACK);
		
		

		btnClose = new Button(" Close ");
		btnClose.setOnAction(ae -> nStage.close());


		//create Layout
		VBox vb = new VBox();
		GridPane ngp = new GridPane();
		HBox hbButtons = new HBox();



		//add to the gridpane

		ngp.add(lblStudentName, 1, 1);
		ngp.add(lblStudentNum, 1, 2);



		hbButtons.getChildren().addAll(btnClose);
		hbButtons.setAlignment(Pos.BOTTOM_CENTER);
		hbButtons.setPadding(new Insets(20));
		hbButtons.setSpacing(10);

		ngp.add(hbButtons, 1, 3);

		//Beautify
		ngp.setPadding(new Insets(10));
		ngp.setHgap(20);
		ngp.setVgap(10);


		//add to the vbox
		vb.getChildren().add(ngp);

		//create the scene
		Scene s = new Scene(vb);

		//set scen
		nStage.setScene(s);

		//show Scene
		nStage.show();
	}
	
	public void Shuffling() {
		
		//this method plays a 5 second video showing a visual representation of the deck of card shuffling
		// TODO Auto-generated method stub
		Stage nStage = new Stage();

		nStage.setHeight(401);
		nStage.setWidth(257);
		//fnStage.setResizable(false);
		  //Initialising path of the media file, replace this with your file path  
		

		
        String path = "cards/sh1.mp4";  
          
        //Instantiating Media class  
        Media media = new Media(new File(path).toURI().toString());  
          
        //Instantiating MediaPlayer class   
        MediaPlayer mediaPlayer = new MediaPlayer(media);  
          
        //Instantiating MediaView class   
        MediaView mediaView = new MediaView(mediaPlayer);  
          
        //by setting this property to true, the Video will be played   
        mediaPlayer.setAutoPlay(true);  
          
        //setting group and scene   
        Group root = new Group();  
        root.getChildren().add(mediaView);  
        

		Scene s = new Scene(root,500,400);
		nStage.setScene(s);
		

		
		//show Scene
		nStage.show();
		
//
//		long mTime = System.currentTimeMillis();
//		long end = mTime + 5000; // 5 seconds 
//		
//		while (mTime < end) 
//		{
//			mTime = System.currentTimeMillis();
//			
//
//			   
//		} 
//		
//		nStage.close();
//		
		
		
		
	}
		
	


	@Override
	public void start(Stage pStage) throws Exception {
		// TODO Auto-generated method stub

		//start of game with a new deck from the deck of card class and then
		//the cards are then shuffled when the deck is gotten
		dc.deckOfCards();
		dc.shuffle();
		
		//Defining the cards in the cardsleft variable
		cardLeft =52;
		
		
		//loading the image folder
		//Creating an image 
		
		//at the beginning of the game you should only be able to deal the first card and then set the other button t
		btnDealSecondCard.setDisable(true);
		btnDealFirstCard.setDisable(false);
		
		//images
		image1 = new Image(new FileInputStream("cards/card_back_red.png"));

		//Setting the image view 1 
		imgView1 = new ImageView(image1); 
		//imgView1.getImage();

		//Setting the position of the image 
		imgView1.setX(50); 
		imgView1.setY(25); 
		//	      
		//	    //setting the fit height and width of the image view 
		imgView1.setFitHeight(250); 
		imgView1.setFitWidth(200);         
	      
		 //Setting the preserve ratio of the image view 
		imgView1.setPreserveRatio(true);


		//end of first image

		//Creating an image  2
		image2 = new Image(new FileInputStream("cards/card_back_red.png"));

		//Setting the image view 1 
		imgView2 = new ImageView(image2); 

		//Setting the position of the image 
		imgView2.setX(50); 
		imgView2.setY(25); 

		//setting the fit height and width of the image view 
		imgView2.setFitHeight(250); 
		imgView2.setFitWidth(200);         

		//Setting the preserve ratio of the image view 
		imgView2.setPreserveRatio(true);

		

//setting the height and the width of the stage
		pStage.setHeight(482);
		pStage.setWidth(768);
		pStage.setResizable(false);

		pStage.setTitle("Hi-Lo Card Game");

		//create Layout
		VBox vb = new VBox();
		GridPane gp = new GridPane();
		BorderPane bp = new BorderPane();
		VBox hbSec = new VBox();
		VBox hbFir = new VBox();
		VBox hbwins = new VBox();
		VBox hbCenter = new VBox();
		HBox hbProgress = new HBox();
		HBox hbBttm = new HBox();

		//hbox for the wincounter
		//i created a vbox with wincounter lable and wins label then i put that in a hbox that i created for the hbox that i created for the progress bas and the progress indicator
		//then i set spacing added padding thorught insets and set the position of the vbox using set translate x and y 
		hbwins.getChildren().addAll(lblWins,lblwinCounter);
		hbProgress.getChildren().addAll(pbar,pInd);
		hbBttm.getChildren().addAll(hbwins,hbProgress);
		hbBttm.setSpacing(20);
		hbBttm.setPadding(new Insets(2));
		hbBttm.setTranslateY(-19);
		hbBttm.setTranslateX(149);

		//vbox for second card
		//i put all the layout components for the card on the right
		//then i set spacing added padding thought insets and set the position of the vbox using set translate x and y 

		hbSec.getChildren().addAll(lblSecCard,imgView2,lblWinLoseMessage);
		hbSec.setSpacing(10);
		hbSec.setPadding(new Insets(20));
		hbSec.setTranslateY(1);
		hbSec.setTranslateX(-30);

		//vbox for first card
		//i put all the layout components for the card on the left
		//then i set spacing added padding thought insets and set the position of the vbox using set translate x and y 

		hbFir.getChildren().addAll(lblFirstCard,imgView1,lblCardsLeft, lblCardsCounter);
		hbFir.setSpacing(10);
		hbFir.setPadding(new Insets(20));
		hbFir.setTranslateY(1);
		hbFir.setTranslateX(-1);

		//vbox for center items
		//i put all the layout componets for components in the center 
		//then i set spacing added padding thought insets and set the position of the vbox using set translate x and y 

		hbCenter.getChildren().addAll(lblNextCard, rbHigher,rbLower,btnDealFirstCard,btnDealSecondCard);
		hbCenter.setSpacing(22);
		hbCenter.setPadding(new Insets(5));
		hbCenter.setTranslateX(14);
		hbCenter.setTranslateY(80);



		//adding components to the borderpane to the top of the dialog
		//then with all the hbox and vbox then have other components in them including the menu bar i used borderpane because it allow for 
		//easy setting of position so all i have to do is set a vbox or hbox to a particular position it can be top, right, left bottom or center so i designed the hboxs and boxes
		//in this manner based in the picture given in the assignment brief 
		bp.setTop(mBar);
		bp.setRight(hbSec);
		bp.setLeft(hbFir);
		bp.setCenter(hbCenter);
		bp.setBottom(hbBttm);



		//size the buttons
		//first card
		btnDealFirstCard.setMaxWidth(250);
		btnDealFirstCard.setMaxHeight(29);
		
		//second card
		btnDealSecondCard.setMaxWidth(250);
		btnDealSecondCard.setMaxHeight(29);
		
		//used for the styling of th buttons
		//the button1 is a calss that was created in the css file
		btnDealFirstCard.getStyleClass().add("button1");
		btnDealSecondCard.getStyleClass().add("button1");



		//add to the vbox
		vb.getChildren().add(bp);
		vb.getChildren().add(gp);

	
		//Beautify
		gp.setPadding(new Insets(10));
		gp.setHgap(20);
		gp.setVgap(5);

		//create the scene
		Scene s = new Scene(vb);

		//links the css file to the scene so styling can be done
		s.getStylesheets().add("./Style.css");


		//set scene
		pStage.setScene(s);

		//show Scene
		pStage.show();


	}

	public static void main(String[] args) {

		launch();
	}


}

//*****************SOURCES****************\\\

//BorderPane
//https://openjfx.io/javadoc/11/javafx.graphics/javafx/scene/layout/BorderPane.html - I used this to learn more about BorederPane
//https://www.tutorialspoint.com/javafx/layout_borderpane.html

//Media
//https://docs.oracle.com/javafx/2/api/javafx/scene/media/package-summary.html
