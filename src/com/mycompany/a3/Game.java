package com.mycompany.a3;
import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Button;
import com.codename1.ui.CheckBox;
import com.codename1.ui.Command;
import com.codename1.ui.Component;
import com.codename1.ui.Container;
import com.codename1.ui.Font;
import com.codename1.ui.Form;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.GridLayout;
import com.codename1.ui.plaf.Border;
import com.codename1.ui.util.UITimer;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.events.ActionEvent; 
import java.lang.String;
import java.util.Observable;
/**
 * This class instantiates the GameWorld and runs the game. This
 * class acts as the Controller
 * @author kiryl
 *
 */

public class Game extends Form implements Runnable {
	private GameWorld gw;
	private MapView mv;
	private ScoreView sv;
	private UITimer timer;
	private Toolbar myToolbar;
	private Button accelButt;
	private Button brakeButt;
	private Button leftButt;
	private Button rightButt;
	private Button chngStratButt;
	private Button positionButt;
	private Button pauseButt;
	
	/* Constructor for game, instantiates the GameWorld */
	public Game() {
		gw = new GameWorld();
		mv = new MapView();
		sv = new ScoreView();
		
		gw.addObserver(mv);
		gw.addObserver(sv);		
		// creates the title bar area with buttons for side menu and help
		this.setLayout(new BorderLayout());
		myToolbar = new Toolbar();
		setToolbar(myToolbar);
		Label title = new Label("Keep-On-Track Game");
		title.getAllStyles().setFont(Font.createSystemFont(Font.FACE_SYSTEM, Font.STYLE_BOLD, Font.SIZE_MEDIUM));
		myToolbar.setTitleComponent(title);
		CheckBox soundStatus = new CheckBox("Sound");
		soundStatus.getAllStyles().setBgTransparency(255);
		soundStatus.getAllStyles().setBgColor(ColorUtil.LTGRAY);
		soundStatus.getAllStyles().setPadding(Component.TOP, 3);
		soundStatus.getAllStyles().setPadding(Component.BOTTOM, 3);
		soundStatus.setSelected(true);
		SoundCheck soundCheck = new SoundCheck(gw);
		soundStatus.setCommand(soundCheck);
		myToolbar.addComponentToSideMenu(soundStatus);
		GameButton aboutButt = new GameButton("About");
		AboutCommand cAbout = new AboutCommand(gw);
		aboutButt.getGameButton().setCommand(cAbout);
		myToolbar.addCommandToSideMenu(cAbout);
		GameButton exitButt = new GameButton("Exit");
		ExitCommand cExit = new ExitCommand(gw);
		exitButt.getGameButton().setCommand(cExit);
		myToolbar.addCommandToSideMenu(cExit);
		GameButton helpButt = new GameButton("Help");
		HelpCommand cHelp = new HelpCommand(gw);
		helpButt.getGameButton().setCommand(cHelp);
		myToolbar.addCommandToRightBar(cHelp);
		
		// creates the north container to keep track of player cyb game state data
		sv.getAllStyles().setPadding(Component.LEFT, 150);
		sv.add(sv.getTimeLabel());
		sv.add(sv.getLivesLabel());
		sv.add(sv.getPCybLBRLabel());
		sv.add(sv.getPCybEnrgLvlLabel());
		sv.add(sv.getPCybDmgLvlLabel());
		sv.add(sv.getSoundLabel());
		this.add(BorderLayout.NORTH, sv);
		
		// creates west container for Buttons Accelerate, Left Turn, and Change Strategies
		Container westContainer = new Container();
		westContainer.getAllStyles().setPadding(Component.TOP, 100);
		westContainer.getAllStyles().setBorder(Border.createLineBorder(4,ColorUtil.BLACK));
		westContainer.setLayout(new BoxLayout(BoxLayout.Y_AXIS));
		accelButt = new GameButton("Accelerate").getGameButton();
		AccelerateCommand cAcc = new AccelerateCommand(gw);
		addKeyListener('a',cAcc);
		accelButt.setCommand(cAcc);
		westContainer.add(accelButt);
		myToolbar.addCommandToSideMenu(cAcc);			// accelerate command on side menu
		leftButt = new GameButton("Left").getGameButton();
		LeftCommand cLeft = new LeftCommand(gw);
		addKeyListener('l',cLeft);
		leftButt.setCommand(cLeft);
		westContainer.add(leftButt);
		chngStratButt = new GameButton("Change Strategies").getGameButton();
		ChangeStrategiesCommand cChangeStrat = new ChangeStrategiesCommand(gw);
		chngStratButt.setCommand(cChangeStrat);
		westContainer.add(chngStratButt);
		this.add(BorderLayout.WEST, westContainer);

		// creates east container for buttons Brake and Right Turn
		Container eastContainer = new Container();
		eastContainer.getAllStyles().setPadding(Component.TOP, 100);
		eastContainer.getAllStyles().setBorder(Border.createLineBorder(4,ColorUtil.BLACK));
		eastContainer.setLayout(new BoxLayout(BoxLayout.Y_AXIS));
		//GameButton brakeButton = new GameButton("Brake");
		brakeButt =  new GameButton("Brake").getGameButton();
		BrakeCommand cBrake = new BrakeCommand(gw);
		addKeyListener('b',cBrake);
		brakeButt.setCommand(cBrake);
		eastContainer.add(brakeButt);
		rightButt = new GameButton("Right").getGameButton();
		RightCommand cRight = new RightCommand(gw);
		addKeyListener('r',cRight);
		rightButt.setCommand(cRight);
		eastContainer.add(rightButt);
		this.add(BorderLayout.EAST, eastContainer);
		
		// creates south container for buttons Collide w/ NPC, Base, Enrg Stat, Drone, and Clock Tick
		Container southContainer = new Container();
		southContainer.getAllStyles().setPadding(Component.LEFT, 900);
		southContainer.getAllStyles().setBorder(Border.createLineBorder(4,ColorUtil.BLACK));
		southContainer.setLayout(new BoxLayout(BoxLayout.X_AXIS));
		pauseButt = new GameButton("Pause").getGameButton();
		pauseButt.getAllStyles().setPaddingLeft(2.0f);
		pauseButt.getAllStyles().setPaddingRight(2.0f);
		PauseCommand cPause = new PauseCommand(pauseButt,this);
		pauseButt.setCommand(cPause);
		southContainer.add(pauseButt);
		positionButt = new GameButton("Position").getGameButton();
		PositionCommand cPosition = new PositionCommand(gw);
		positionButt.setCommand(cPosition);
		positionButt.setEnabled(false);
		southContainer.add(positionButt);
		this.add(BorderLayout.SOUTH, southContainer);

		// creates the center container for MapView
		mv.getAllStyles().setBorder(Border.createLineBorder(4,ColorUtil.rgb(190,15,15)));
		this.add(BorderLayout.CENTER, mv);
		this.show();
		gw.setWidth(mv.getWidth());
		gw.setHeight(mv.getHeight());
		gw.init();
		gw.myNotifyObservers();
		gw.createSounds();
		revalidate();
		timer = new UITimer(this);
		timer.schedule(20, true, this);
	}
	
	/* starts the game after it is initialized */
	@Override
	public void run() {
		gw.clockTick();
	}
	
	/* starts the time and enables all game stap buttons */
	public void play() {
		timer.schedule(20, true, this);
		gw.play();
		positionButt.setEnabled(false);
		accelButt.setEnabled(true);
		addKeyListener('a', accelButt.getCommand());
		brakeButt.setEnabled(true);
		addKeyListener('b', brakeButt.getCommand());
		leftButt.setEnabled(true);
		addKeyListener('l', leftButt.getCommand());
		rightButt.setEnabled(true);
		addKeyListener('r', rightButt.getCommand());
	}
	
	/* stops the timer and disables game play buttons while in "paused" mode */
	public void pause() {
		gw.pause();
		timer.cancel();
		positionButt.setEnabled(true);
		accelButt.setEnabled(false);
		removeKeyListener('a',accelButt.getCommand());
		brakeButt.setEnabled(false);
		removeKeyListener('b',brakeButt.getCommand());
		leftButt.setEnabled(false);
		removeKeyListener('l',leftButt.getCommand());
		rightButt.setEnabled(false);
		removeKeyListener('r',rightButt.getCommand());
	}
}

