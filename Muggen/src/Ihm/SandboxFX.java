package Ihm;

import java.io.File;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import javafx.animation.Animation.Status;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Duration;
import melody.Melody;
import modes.Mode;
import musicGeneration.PlayerAll;
import note.HarmonicNote;
import note.RythmicNote;
import rythm.TimeSignature;
import scales.Scale;

public class SandboxFX extends Application {

	static final Image SOL_KEY = new Image("habla.txt");
	static final Image FA_KEY = new Image("notes pictures/fa key.png");

	static final Image BLACK = new Image("notes pictures/black.png");
	static final Image BLACK_POINTED = new Image("notes pictures/black pointed.png");

	static final Image CROCHE = new Image("notes pictures/croche.png");
	static final Image DOUBLE_CROCHE = new Image("notes pictures/double croche.png");
	static final Image TRIPLE_CROCHE = new Image("notes pictures/triple croche.png");

	static final Image WHITE = new Image("notes pictures/white.png");
	static final Image WHITE_POINTED = new Image("notes pictures/white pointed.png");

	static final Image ROUND = new Image("notes pictures/round.png");
	static final Image POINTED_ROUND = new Image("notes pictures/pointed round.png");

	static final Image TRIOLET = new Image("notes pictures/triolet.png");

	static final Image DIESE = new Image("notes pictures/diese.png");

	static final int STAFFNUMBER = 10;
	static final int YNORMALDEC = 33; // negative
	static final int SPACE_X_BETWEEN_G_NOTES = 10;// space between
													// graphics notes
	static final int SPACE_Y_BETWEEN_NOTES = 5; // 1/2 space between
												// lines
	static final int WIDTH = 670;
	static final int HEIGHT = 160 * STAFFNUMBER;
	static final int YBEGIN = 30; // Beginning of the first line of the
									// first staff
	static final int YDISTANCE_BETWEEN_STAFF = 140;

	private int refX = YBEGIN;
	private float timeSignatureIndex = 0;
	private float[] timeSignatureOnEndOfAStaff = new float[STAFFNUMBER];
	private Timeline timeline = new Timeline();
	private int staffIndex = 0;

	public static void main(String[] args) {
		launch(args);
	}

	public void start(Stage primaryStage) {
		// defined by future comboboxes

		TimeSignature timeSignature = new TimeSignature(4, 4, 120);

		PlayerAll playerAll = new PlayerAll(new Scale(new HarmonicNote(63), new Mode(1)), 3, 18, 2, 120);

		// playerAll.play();

		// graphic
		// part
		// -----------------------------------------------------------------

		// partition part :
		StackPane partitionStackPane = new StackPane();
		ScrollPane scrollPane = new ScrollPane(partitionStackPane);
		primaryStage.setTitle("JAlto");

		// Draw zone definition
		Canvas canvas = new Canvas(WIDTH - 20,
				((playerAll.getmusic().getMelody().getMelody().getMelody().size() / 18) + 2) * 160);
		GraphicsContext gc = canvas.getGraphicsContext2D();

		// partition painting
		paintPartition(playerAll.getmusic().getMelody().getMelody(), gc, timeSignature);

		// picture of the readerbar loading
		ImageView readerBar = new ImageView("notes pictures/readerBar.png");
		// configureReaderBar(readerBar,playerAll);

		// Largest container, contains a Hbox and the partition's scrollpane
		VBox globalVBox = new VBox();

		scrollPane.setPrefHeight(400);
		scrollPane.setPrefWidth(WIDTH);
		scrollPane.setStyle(
				"-fx-padding: 1; -fx-background-color: black, -fx-control-inner-background; -fx-background-insets: 0, 1;");

		// Main scene, containing the globalVBox-----------------------------
		final Scene scene = new Scene(globalVBox, WIDTH, 620);
		scene.setFill(null);

		// stacking partition and readerbar----------------------------------
		partitionStackPane.getChildren().add(canvas);
		partitionStackPane.getChildren().add(readerBar);

		// Advanced tab Elements ---------------------------------------------
		// Fundamental part-----------------------------
		Label fundamentalLabel = new Label("Fundamental");
		fundamentalLabel.setTranslateX(18);
		fundamentalLabel.setTranslateY(2);

		ComboBox fundamentalCombobox = new ComboBox();
		fundamentalCombobox.setTranslateX(80);
		fundamentalCombobox.setTranslateY(2);
		fundamentalCombobox.setPrefWidth(140);
		fundamentalCombobox.getItems().addAll("Do", "Re", "Mi", "Fa", "Sol", "La", "Si");
		fundamentalCombobox.getSelectionModel().selectFirst();
		fundamentalCombobox.setTooltip(new Tooltip("Select the fundamental note"));
	
		// Melody length part--------------------------
		Label melodyLengthLabel = new Label("Melody Length");
		melodyLengthLabel.setTranslateX(18);
		melodyLengthLabel.setTranslateY(33);

		ComboBox melodyLengthCombobox = new ComboBox();
		melodyLengthCombobox.setTranslateX(80);
		melodyLengthCombobox.setTranslateY(33);
		melodyLengthCombobox.setPrefWidth(140);
		melodyLengthCombobox.getItems().addAll(20, 50, 100, 200);
		melodyLengthCombobox.setTooltip(new Tooltip("Select the melody length, in measure"));
		melodyLengthCombobox.getSelectionModel().selectFirst();

		// Melody instrument part--------------------------
		Label melodyInstruLabel = new Label("Melody instrument");
		melodyInstruLabel.setTranslateX(118);
		melodyInstruLabel.setTranslateY(33);

		ComboBox melodyInstruCombobox = new ComboBox();
		melodyInstruCombobox.setTranslateX(180);
		melodyInstruCombobox.setTranslateY(33);
		melodyInstruCombobox.setPrefWidth(140);
		melodyInstruCombobox.getItems().addAll("Acoustic Grand Piano", "Bright Acoustic Piano", "Electric Grand Piano",
				"Harpsichord", "Clavinet", "Vibraphone", "Xylophone", "Tubular Bells", "Percussive Organ", "Rock Organ",
				"Church Organ", "Reed Organ", "Accordion", "Harmonica", "Acoustic Guitar", "Electric Guitar (jazz)",
				"Electric Guitar (clean)", "Electric Guitar (muted)", "Distortion Guitar", "Guitar Harmonics",
				"Acoustic Bass", "Electric Bass", "Slap Bass 1", "Slap Bass 2", "Synth Bass", "Violin", "Viola",
				"Cello", "Contrabass", "Tremolo", "Orchestral Harp", "String Ensemble", "Synth Strings", "Choir Aahs",
				"Voice Oohs", "Orchestra Hit", "Trumpet", "Trombone", "Tuba", "Muted Trumpet", "French Horn",
				"Brass Section", "Soprano Sax", "Alto Sax", "Tenor Sax", "Baritone Sax", "Clarinet", "Piccolo", "Flute",
				"Recorder", "Pan Flute", "Ocarina", "Lead 1 (square)", "Lead 2 (sawtooth)", "Lead 3 (calliope)",
				"Lead 6 (voice)", "Pad 1 (new age)", "Pad 2 (warm)", "Pad 3 (polysynth)", "Pad 4 (choir)",
				"Pad 5 (bowed)", "Pad 6 (metallic)", "FX 1 (rain)", "FX 2 (soundtrack)", "FX 3 (crystal)",
				"FX 4 (atmosphere)", "FX 5 (brightness)", "FX 6 (goblins)");
		melodyInstruCombobox.setTooltip(new Tooltip("Select the Instruments for the melody"));
		melodyInstruCombobox.getSelectionModel().selectFirst();

		//playerAll.setInstruMelody(melodyInstruCombobox.getValue());
		// Tempo part ---------------------------------
		Label tempoLabel = new Label("Tempo");
		tempoLabel.setTranslateX(118);
		tempoLabel.setTranslateY(2);

		TextField tempoTextField = new TextField("120");
		tempoTextField.setTranslateX(180);
		tempoTextField.setTranslateY(2);
		tempoTextField.setPrefWidth(100);
		tempoTextField.setTooltip(new Tooltip("Select the number of note per minute"));

				// Chords length part -----------------------
		Label chordsLengthLabel = new Label("Chords Length");
		chordsLengthLabel.setTranslateX(18);
		chordsLengthLabel.setTranslateY(22);

		ComboBox chordsLengthCombobox = new ComboBox();
		chordsLengthCombobox.setTranslateX(80);
		chordsLengthCombobox.setTranslateY(22);
		chordsLengthCombobox.setPrefWidth(140);
		chordsLengthCombobox.getItems().addAll(20, 50, 100, 200);
		chordsLengthCombobox.getSelectionModel().selectFirst();
		chordsLengthCombobox.setTooltip(new Tooltip("Select the chords length, in measure"));

		// Chords instruments part -----------------------
		Label chordsInstruLabel = new Label("Chords instrument");
		chordsInstruLabel.setTranslateX(118);
		chordsInstruLabel.setTranslateY(22);

		ComboBox chordsInstruCombobox = new ComboBox();
		chordsInstruCombobox.setTranslateX(180);
		chordsInstruCombobox.setTranslateY(22);
		chordsInstruCombobox.setPrefWidth(140);
		chordsInstruCombobox.getItems().addAll("Acoustic Grand Piano", "Bright Acoustic Piano", "Electric Grand Piano",
				"Harpsichord", "Clavinet", "Vibraphone", "Xylophone", "Tubular Bells", "Percussive Organ", "Rock Organ",
				"Church Organ", "Reed Organ", "Accordion", "Harmonica", "Acoustic Guitar", "Electric Guitar (jazz)",
				"Electric Guitar (clean)", "Electric Guitar (muted)", "Distortion Guitar", "Guitar Harmonics",
				"Acoustic Bass", "Electric Bass", "Slap Bass 1", "Slap Bass 2", "Synth Bass", "Violin", "Viola",
				"Cello", "Contrabass", "Tremolo", "Orchestral Harp", "String Ensemble", "Synth Strings", "Choir Aahs",
				"Voice Oohs", "Orchestra Hit", "Trumpet", "Trombone", "Tuba", "Muted Trumpet", "French Horn",
				"Brass Section", "Soprano Sax", "Alto Sax", "Tenor Sax", "Baritone Sax", "Clarinet", "Piccolo", "Flute",
				"Recorder", "Pan Flute", "Ocarina", "Lead 1 (square)", "Lead 2 (sawtooth)", "Lead 3 (calliope)",
				"Lead 6 (voice)", "Pad 1 (new age)", "Pad 2 (warm)", "Pad 3 (polysynth)", "Pad 4 (choir)",
				"Pad 5 (bowed)", "Pad 6 (metallic)", "FX 1 (rain)", "FX 2 (soundtrack)", "FX 3 (crystal)",
				"FX 4 (atmosphere)", "FX 5 (brightness)", "FX 6 (goblins)");
		chordsInstruCombobox.setTooltip(new Tooltip("Select the chords's instrument"));
		chordsInstruCombobox.getSelectionModel().selectFirst();

		// Mode part -----------------------
		Label modeLabel = new Label("Mode");
		modeLabel.setTranslateX(18);
		modeLabel.setTranslateY(12);

		ComboBox modeCombobox = new ComboBox();
		modeCombobox.setTranslateX(80);
		modeCombobox.setTranslateY(12);
		modeCombobox.setPrefWidth(140);
		modeCombobox.getItems().addAll("Do (lydien)", "Re (phrygien)", "Mi (dorien)", "Fa (hypolydien)",
				"Sol (hypophrygien)", "La (hypodorien)", "Si (mixolydien)", "Pentat. major (jazzy)",
				"Pentat. minor (bluezy)", "G. blues", "G. minor mld. asc.", "G. minor hamq.");
		modeCombobox.setTooltip(new Tooltip("Select the Interval of tons"));
		modeCombobox.getSelectionModel().selectFirst();

		// threshold part ---------------------------------
		Label thresholdLabel = new Label("Threshold");
		thresholdLabel.setTranslateX(118);
		thresholdLabel.setTranslateY(12);

		ComboBox thresholdCombobox = new ComboBox();
		thresholdCombobox.setTranslateX(180);
		thresholdCombobox.setTranslateY(12);
		thresholdCombobox.setPrefWidth(140);
		thresholdCombobox.getItems().addAll(0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14);
		thresholdCombobox.getSelectionModel().selectFirst();
		thresholdCombobox.setTooltip(
				new Tooltip("Note decomposition threshold, high means a lot of triple, low means more white or round"));

		// TextField for "save as..."
		TextField saveAsTextField = new TextField("MusicSheet");
		saveAsTextField.setTranslateX(180);
		saveAsTextField.setTranslateY(40);
		saveAsTextField.setPrefWidth(100);
		saveAsTextField.setTooltip(new Tooltip("Select the name of your PNG file"));

		// Button part ------------------------------
		Button playPause = new Button("Play/Pause");
		playPause.setTranslateX(18);
		playPause.setTranslateY(40);
		playPause.setTooltip(new Tooltip("Launch or stop music"));
		playPause.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				if (timeline.getStatus() == Status.RUNNING) {
					playerAll.stop();
					timeline.pause();
				} else {
					playerAll.play();
					canvas.setWidth(WIDTH);
					canvas.setHeight(
							((playerAll.getmusic().getMelody().getMelody().getMelody().size() / 18) + 2) * 160);

					paintPartition(playerAll.getmusic().getMelody().getMelody(), gc, timeSignature);
					configureReaderBar(readerBar, playerAll);
					timeline.play();
				}
			}
		});

		Button reloadModificationButton = new Button("Reload Modification");
		reloadModificationButton.setTranslateX(80);
		reloadModificationButton.setTranslateY(40);
		reloadModificationButton.setTooltip(new Tooltip("refresh the melody and chords with actual parameters"));

		reloadModificationButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {

			}
		});

		Button saveToPNGButton = new Button("Save To PNG");
		saveToPNGButton.setTranslateX(118);
		saveToPNGButton.setTranslateY(40);
		saveToPNGButton.setTooltip(new Tooltip(
				"Save your music sheet with the name below, a file with an existing name will erase the previous one. The file is save at the root of the application"));

		saveToPNGButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				WritableImage wim = new WritableImage((int) canvas.getWidth(), (int) canvas.getHeight());
				canvas.snapshot(null, wim);
				File file = new File(String.valueOf(saveAsTextField.getText()) + "1.png");
				try {
					ImageIO.write(SwingFXUtils.fromFXImage(wim, null), "png", file);
				} catch (Exception s) {
				}
			}
		});

		// window adding part-----------------------
		GridPane advancedGridPane = new GridPane();
		advancedGridPane.setPrefSize(WIDTH / 2, 200);
		advancedGridPane.add(fundamentalLabel, 1, 1);
		advancedGridPane.add(fundamentalCombobox, 2, 1);
		advancedGridPane.add(melodyLengthLabel, 1, 4);
		advancedGridPane.add(melodyLengthCombobox, 2, 4);
		advancedGridPane.add(chordsLengthLabel, 1, 3);
		advancedGridPane.add(chordsLengthCombobox, 2, 3);
		advancedGridPane.add(modeLabel, 1, 2);
		advancedGridPane.add(modeCombobox, 2, 2);
		advancedGridPane.add(playPause, 1, 5);
		advancedGridPane.add(reloadModificationButton, 2, 5);
		advancedGridPane.add(tempoLabel, 3, 1);
		advancedGridPane.add(tempoTextField, 4, 1);
		advancedGridPane.add(thresholdLabel, 3, 2);
		advancedGridPane.add(thresholdCombobox, 4, 2);
		advancedGridPane.add(saveToPNGButton, 3, 5);
		advancedGridPane.add(saveAsTextField, 4, 5);
		advancedGridPane.add(melodyInstruLabel, 3, 4);
		advancedGridPane.add(melodyInstruCombobox, 4, 4);
		advancedGridPane.add(chordsInstruLabel, 3, 3);
		advancedGridPane.add(chordsInstruCombobox, 4, 3);

		// Styles tab elements -----------------------------------------------
		// Preconfigured styles part ----------------------
		Label styleLabel = new Label("Select your style");
		styleLabel.setTranslateX(18);
		styleLabel.setTranslateY(2);

		ComboBox styleCombobox = new ComboBox();
		styleCombobox.setTranslateX(40);
		styleCombobox.setTranslateY(2);
		styleCombobox.setPrefWidth(140);
		getSavedTypes(styleCombobox);
		styleCombobox.getSelectionModel().selectFirst();
		styleCombobox
				.setTooltip(new Tooltip("Select the Style you want, you can create your own, it will appear here"));

		// TextField for style name
		TextField nameTextField = new TextField("Name");
		nameTextField.setTranslateX(18);
		nameTextField.setTranslateY(20);
		nameTextField.setPrefWidth(60);
		nameTextField.setTooltip(
				new Tooltip("The name of the style you want to create. Same names erase the previous same named"));

		// Custom style part --------------------------------
		// Instruments Combobox -----------------------------
		ComboBox instrumentsCombobox = new ComboBox();
		instrumentsCombobox.setTranslateX(40);
		instrumentsCombobox.setTranslateY(20);
		instrumentsCombobox.setPrefWidth(140);
		instrumentsCombobox.getItems().addAll("Acoustic Grand Piano", "Bright Acoustic Piano", "Electric Grand Piano",
				"Harpsichord", "Clavinet", "Vibraphone", "Xylophone", "Tubular Bells", "Percussive Organ", "Rock Organ",
				"Church Organ", "Reed Organ", "Accordion", "Harmonica", "Acoustic Guitar", "Electric Guitar (jazz)",
				"Electric Guitar (clean)", "Electric Guitar (muted)", "Distortion Guitar", "Guitar Harmonics",
				"Acoustic Bass", "Electric Bass", "Slap Bass 1", "Slap Bass 2", "Synth Bass", "Violin", "Viola",
				"Cello", "Contrabass", "Tremolo", "Orchestral Harp", "String Ensemble", "Synth Strings", "Choir Aahs",
				"Voice Oohs", "Orchestra Hit", "Trumpet", "Trombone", "Tuba", "Muted Trumpet", "French Horn",
				"Brass Section", "Soprano Sax", "Alto Sax", "Tenor Sax", "Baritone Sax", "Clarinet", "Piccolo", "Flute",
				"Recorder", "Pan Flute", "Ocarina", "Lead 1 (square)", "Lead 2 (sawtooth)", "Lead 3 (calliope)",
				"Lead 6 (voice)", "Pad 1 (new age)", "Pad 2 (warm)", "Pad 3 (polysynth)", "Pad 4 (choir)",
				"Pad 5 (bowed)", "Pad 6 (metallic)", "FX 1 (rain)", "FX 2 (soundtrack)", "FX 3 (crystal)",
				"FX 4 (atmosphere)", "FX 5 (brightness)", "FX 6 (goblins)");
		instrumentsCombobox.getSelectionModel().selectFirst();
		instrumentsCombobox.setTooltip(
				new Tooltip("Select the instrument you want to add to the chords or melody's instrument's list"));

		// mode configuration --------------------------------
		Label modeLabelS = new Label("Mode");
		modeLabelS.setTranslateX(18);
		modeLabelS.setTranslateY(40);

		ComboBox modeComboboxS = new ComboBox();
		modeComboboxS.setTranslateX(40);
		modeComboboxS.setTranslateY(40);
		modeComboboxS.setPrefWidth(140);
		modeComboboxS.getItems().addAll("Do (lydien)", "Re (phrygien)", "Mi (dorien)", "Fa (hypolydien)",
				"Sol (hypophrygien)", "La (hypodorien)", "Si (mixolydien)", "Pentat. major (jazzy)",
				"Pentat. minor (bluezy)", "G. blues", "G. minor mld. asc.", "G. minor hamq.");
		modeComboboxS.getSelectionModel().selectFirst();
		modeComboboxS.setTooltip(new Tooltip("Select an intervalle of tons"));

		// Threshold ---------------------------------------
		Label thresholdLabelS = new Label("Threshold");
		thresholdLabelS.setTranslateX(70);
		thresholdLabelS.setTranslateY(40);

		ComboBox thresholdComboboxS = new ComboBox();
		thresholdComboboxS.setTranslateX(75);
		thresholdComboboxS.setTranslateY(40);
		thresholdComboboxS.setPrefWidth(140);
		thresholdComboboxS.getItems().addAll(0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14);
		thresholdComboboxS.getSelectionModel().selectFirst();
		thresholdComboboxS.setTooltip(
				new Tooltip("Note decomposition threshold, high means a lot of triple, low means more white or round"));

		// Tempo part ---------------------------------
		Label tempoLabelS = new Label("Tempo");
		tempoLabelS.setTranslateX(18);
		tempoLabelS.setTranslateY(60);

		TextField tempoTextFieldS = new TextField("Tempo");
		tempoTextFieldS.setTranslateX(40);
		tempoTextFieldS.setTranslateY(60);
		tempoTextFieldS.setPrefWidth(100);
		tempoTextFieldS.setTooltip(new Tooltip("Select the number of note per minute"));

		// Buttons style part ------------------------------
		Button addButton = new Button("Add your own");
		addButton.setTranslateX(70);
		addButton.setTranslateY(60);
		addButton.setTooltip(
				new Tooltip("Add your own style, be sure you've add some instruments for the melody and the chords"));

		addButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {

			}
		});

		Button playPause2 = new Button("Play/Pause");
		playPause2.setTranslateX(75);
		playPause2.setTranslateY(60);
		playPause2.setTooltip(new Tooltip("Launch or stop the music"));
		playPause2.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				if (timeline.getStatus() == Status.RUNNING) {
					playerAll.stop();
					timeline.pause();
				} else {
					playerAll.play();
					canvas.setWidth(WIDTH);
					canvas.setHeight(
							((playerAll.getmusic().getMelody().getMelody().getMelody().size() / 18) + 2) * 160);

					paintPartition(playerAll.getmusic().getMelody().getMelody(), gc, timeSignature);
					configureReaderBar(readerBar, playerAll);
					timeline.play();
				}
			}
		});

		Button selectButton = new Button("Load selection");
		selectButton.setTranslateX(70);
		selectButton.setTranslateY(2);
		selectButton.setTooltip(new Tooltip("Load the style selected"));
		selectButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {

			}
		});

		Button addToMelodyButton = new Button("Add to melody's instruments");
		addToMelodyButton.setTranslateX(70);
		addToMelodyButton.setTranslateY(20);
		addToMelodyButton.setTooltip(new Tooltip("add the selected instruments to the melody's instruments list"));
		addToMelodyButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {

			}
		});

		Button addToChordsButton = new Button("Add to chords's instruments");
		addToChordsButton.setTranslateX(75);
		addToChordsButton.setTranslateY(20);
		addToChordsButton.setTooltip(new Tooltip("add the selected instruments to the chords's instruments list"));

		addToChordsButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {

			}
		});

		// Gathering in the style window----------------------
		GridPane styleGridPane = new GridPane();
		styleGridPane.setPrefSize(WIDTH / 2, 200);
		styleGridPane.add(styleLabel, 1, 1);
		styleGridPane.add(styleCombobox, 2, 1);
		styleGridPane.add(selectButton, 3, 1);
		styleGridPane.add(nameTextField, 1, 2);
		styleGridPane.add(instrumentsCombobox, 2, 2);
		styleGridPane.add(addToMelodyButton, 3, 2);
		styleGridPane.add(addToChordsButton, 4, 2);
		styleGridPane.add(modeLabelS, 1, 3);
		styleGridPane.add(modeComboboxS, 2, 3);
		styleGridPane.add(thresholdLabelS, 3, 3);
		styleGridPane.add(thresholdComboboxS, 4, 3);
		styleGridPane.add(tempoLabelS, 1, 4);
		styleGridPane.add(tempoTextFieldS, 2, 4);
		styleGridPane.add(addButton, 3, 4);
		styleGridPane.add(playPause2, 4, 4);

		// TabPane creation---------------------------------------------------
		TabPane superiorPartTabbedPane = new TabPane();
		superiorPartTabbedPane.setPrefSize(WIDTH, 200);

		// Left Tab Styles--------------------------------------------
		Tab stylePane = new Tab();
		stylePane.setText("Styles");
		stylePane.setClosable(false);
		stylePane.setStyle("-fx-background-color: #F4F4F4;");
		stylePane.setContent(styleGridPane);

		// Right Tab Advanced-----------------------------------------
		Tab advancedPane = new Tab();
		advancedPane.setText("Advanced");
		advancedPane.setClosable(false);
		advancedPane.setStyle("-fx-background-color: #F4F4F4;");
		advancedPane.setContent(advancedGridPane);

		// adding elements in each other-------------------------------
		superiorPartTabbedPane.getTabs().addAll(stylePane, advancedPane);

		// Menu part--------------------------------------------
		final Menu file = new Menu("File");

		MenuItem export = new MenuItem("Export");
		export.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				System.out.println("Export to .WAV");
			}
		});
		file.getItems().add(export);
		export.setGraphic(new ImageView(new Image("wav.png")));
		MenuBar menuBar = new MenuBar();
		menuBar.getMenus().add(file);

		// Stack everything vertically
		globalVBox.getChildren().addAll(menuBar, superiorPartTabbedPane, scrollPane);

		// The scene is added to the primary stage, showed after ------------
		primaryStage.setScene(scene);
		primaryStage.show();
	}

	/**
	 * Methods who paint the partition, including staff, notes, measures and
	 * keys
	 *
	 * @param melody
	 *            linkedlist of notes
	 * @param gc
	 *            graphics contexts of the main canvas
	 * @param timeSignature
	 *            used to give timeSignature and mesures on the page
	 */
	public void paintPartition(Melody melody, GraphicsContext gc, TimeSignature timeSignature) {
		staffBuilder(YBEGIN, YDISTANCE_BETWEEN_STAFF, gc, (melody.getMelody().size() / 18) + 2, timeSignature);

		int staffCounter = 0;

		// 60 do,do#,re,re#,mi,fa,fa#,sol,sol#,la,la#,si
		for (int i = 0; i < melody.getMelody().size(); i++) {

			notePrinter(melody.getMelody().get(i), gc, staffCounter, timeSignature);

			if (this.refX >= WIDTH - SPACE_X_BETWEEN_G_NOTES - 20 - TRIPLE_CROCHE.getWidth()) {
				try {

					timeSignatureOnEndOfAStaff[staffCounter] = timeSignatureIndex;
					if (staffCounter > 0) {
						for (int j = staffCounter - 1; j >= 0; j--)
							timeSignatureOnEndOfAStaff[staffCounter] -= timeSignatureOnEndOfAStaff[staffCounter - j];
					}
				} catch (java.lang.ArrayIndexOutOfBoundsException e) {

				}
				staffCounter++;
				this.refX = YBEGIN;
			}

		}
		System.out.println("total : " + timeSignatureIndex + " end of first staff : " + timeSignatureOnEndOfAStaff[0]
				+ " end of second staff : " + timeSignatureOnEndOfAStaff[1] + " end of third staff : "
				+ timeSignatureOnEndOfAStaff[2] + " end of fourth staff : " + timeSignatureOnEndOfAStaff[3]);
	}

	public GraphicsContext staffBuilder(int origin, int distance, GraphicsContext gc, int staffNumber,
			TimeSignature timeSignature) {

		gc.beginPath();
		for (int i = 0; i < staffNumber; i++) {
			gc.strokeText(String.valueOf(timeSignature.getMeasureUnit()), SOL_KEY.getWidth(),
					origin + i * distance + 30);
			gc.strokeText(String.valueOf(timeSignature.getNumberOfTime()), SOL_KEY.getWidth(),
					origin + i * distance + 40);
			for (int j = 0; j < 11; j++) {
				// draw 2 staff for 2 octave sol - fa
				if (j != 5) {
					gc.moveTo(1, origin + i * distance + 10 + j * 2 * SPACE_Y_BETWEEN_NOTES);
					gc.lineTo(WIDTH - 21, origin + i * distance + 10 + j * 2 * SPACE_Y_BETWEEN_NOTES);
				}
			}
			// close each couple of staff
			gc.moveTo(1, origin + i * distance + 10);
			gc.lineTo(1, origin + i * distance + 10 + 10 * 2 * SPACE_Y_BETWEEN_NOTES);
			gc.moveTo(WIDTH - 21, origin + i * distance + 10);
			gc.lineTo(WIDTH - 21, origin + i * distance + 10 + 10 * 2 * SPACE_Y_BETWEEN_NOTES);
			// draw keys
			gc.drawImage(SOL_KEY, 1, origin + i * distance);
			gc.drawImage(FA_KEY, 1, origin + i * distance + 14 * SPACE_Y_BETWEEN_NOTES + SPACE_Y_BETWEEN_NOTES / 2);
		}
		gc.stroke();
		return gc;
	}

	public GraphicsContext notePrinter(RythmicNote note, GraphicsContext gc, int staffCounter,
			TimeSignature timeSignature) {
		Image image = BLACK;
		float duration = 0;
		switch (note.getDuration()) {
		case 0:// 1 : durée d'une triple croche
			image = TRIPLE_CROCHE;
			timeSignatureIndex += (1 / 8);
			duration = 1 / 16;
			break;
		case 1:// 2 : durée d'une double croche
			image = DOUBLE_CROCHE;
			timeSignatureIndex += (1 / 4);
			duration = 1 / 8;
			break;
		case 2:// 4 : durée d'une note d'un triolet
			image = TRIOLET;
			timeSignatureIndex += (1 / 3);
			duration = 1 / 3;
			break;
		case 3:// 8 : durée d'une croche
			image = CROCHE;
			timeSignatureIndex += 0.5;
			duration = 1 / 2;
			break;
		case 4:// 16 : durée d'une noire
			image = BLACK;
			timeSignatureIndex += 1;
			duration = 1;
			break;
		case 5:// 16 : durée d'une noire pointée
			image = BLACK_POINTED;
			timeSignatureIndex += 1.5;
			duration = 1 + 1 / 2;
			break;
		case 6:// 16 : durée d'une blanche
			image = WHITE;
			timeSignatureIndex += 2;
			duration = 2;
			break;
		case 7:// 16 : durée d'une blanche pointée
			image = WHITE_POINTED;
			timeSignatureIndex += 3;
			duration = 3;
			break;
		case 8:// 16 : durée d'une ronde
			image = ROUND;
			timeSignatureIndex += 4;
			duration = 4;
			break;
		case 9:// 16 : durée d'une ronde pointée
			image = POINTED_ROUND;
			timeSignatureIndex += 6;
			duration = 6;
			break;
		}
		// if its a do
		if (note.getHeight() - 60 == 0 || note.getHeight() - 60 == 1) {
			// draw the line
			gc.beginPath();
			gc.moveTo(refX - 1,
					convertNoteToYaxis(note.getHeight(), staffCounter) + YBEGIN + 2 * SPACE_Y_BETWEEN_NOTES);
			gc.lineTo(refX + ROUND.getWidth() + 1,
					convertNoteToYaxis(note.getHeight(), staffCounter) + YBEGIN + 2 * SPACE_Y_BETWEEN_NOTES);
			gc.stroke();
		}
		// if its a # note
		if (note.getHeight() - 60 == 1 || note.getHeight() - 60 == 3 || note.getHeight() - 60 == 6
				|| note.getHeight() - 60 == 8 || note.getHeight() - 60 == 10 || note.getHeight() - 60 == 13
				|| note.getHeight() - 60 == 15 || note.getHeight() - 60 == 18 || note.getHeight() - 60 == 20
				|| note.getHeight() - 60 == 22) {

			gc.drawImage(DIESE, refX - DIESE.getWidth(), convertNoteToYaxis(note.getHeight(), staffCounter) + YBEGIN);
		}

		gc.drawImage(image, refX, convertNoteToYaxis(note.getHeight(), staffCounter) + YBEGIN - YNORMALDEC);
		this.refX += (int) image.getWidth() + SPACE_X_BETWEEN_G_NOTES * 4 * duration;
		// line of time signature limit
		if (timeSignatureIndex % timeSignature.getNumberOfTime() == 0) {
			gc.beginPath();
			gc.moveTo(refX - 5, YBEGIN + staffCounter * YDISTANCE_BETWEEN_STAFF);
			gc.lineTo(refX - 5, YBEGIN + 24 * SPACE_Y_BETWEEN_NOTES + staffCounter * YDISTANCE_BETWEEN_STAFF);
			gc.stroke();
		}
		return gc;
	}

	// 60 do,do#,re,re#,mi,fa,fa#,sol,sol#,la,la#,si
	public int convertNoteToYaxis(int note, int staffCounter) {
		int result;

		if (note >= 60 && note <= 83) {
			result = 10 * SPACE_Y_BETWEEN_NOTES + (staffCounter * YDISTANCE_BETWEEN_STAFF);
			if (note - 60 == 1 || note - 60 == 0) {
				result -= noteHeight(0); // do or do#
			} else if (note - 60 == 2 || note - 60 == 3) {
				result -= noteHeight(1); // re or re#
			} else if (note - 60 == 4) {
				result -= noteHeight(2); // just mi
			} else if (note - 60 == 5 || note - 60 == 6) {
				result -= noteHeight(3); // fa or fa#
			} else if (note - 60 == 7 || note - 60 == 8) {
				result -= noteHeight(4); // sol or sol#
			} else if (note - 60 == 9 || note - 60 == 10) {
				result -= noteHeight(5); // la or la#
			} else if (note - 60 == 11) {
				result -= noteHeight(6);// si
			} else if (note - 60 == 12 || note - 60 == 13) {
				result -= noteHeight(7); // do or do#
			} else if (note - 60 == 14 || note - 60 == 15) {
				result -= noteHeight(8); // re or re#
			} else if (note - 60 == 16) {
				result -= noteHeight(9); // just mi
			} else if (note - 60 == 17 || note - 60 == 18) {
				result -= noteHeight(10); // fa or fa#
			} else if (note - 60 == 19 || note - 60 == 20) {
				result -= noteHeight(11); // sol or sol#
			} else if (note - 60 == 21 || note - 60 == 22) {
				result -= noteHeight(12); // la or la#
			} else if (note - 60 == 23) {
				result -= noteHeight(13);// si
			}
		} else if (note < 60 && note >= 37) {
			result = 24 * SPACE_Y_BETWEEN_NOTES + (staffCounter * YDISTANCE_BETWEEN_STAFF);

			if (note - 37 == 1 || note - 37 == 0) {
				result -= noteHeight(0); // do or do#
			} else if (note - 37 == 2 || note - 37 == 3) {
				result -= noteHeight(1); // re or re#
			} else if (note - 37 == 4) {
				result -= noteHeight(2); // just mi
			} else if (note - 37 == 5 || note - 37 == 6) {
				result -= noteHeight(3); // fa or fa#
			} else if (note - 37 == 7 || note - 37 == 8) {
				result -= noteHeight(4); // sol or sol#
			} else if (note - 37 == 9 || note - 37 == 10) {
				result -= noteHeight(5); // la or la#
			} else if (note - 37 == 11) {
				result -= noteHeight(6);// si
			} else if (note - 37 == 12 || note - 37 == 13) {
				result -= noteHeight(7); // do or do#
			} else if (note - 37 == 14 || note - 37 == 15) {
				result -= noteHeight(8); // re or re#
			} else if (note - 37 == 16) {
				result -= noteHeight(9); // just mi
			} else if (note - 37 == 17 || note - 37 == 18) {
				result -= noteHeight(10); // fa or fa#
			} else if (note - 37 == 19 || note - 37 == 20) {
				result -= noteHeight(11); // sol or sol#
			} else if (note - 37 == 21 || note - 37 == 22) {
				result -= noteHeight(12); // la or la#
			} else if (note - 37 == 23) {
				result -= noteHeight(13);// si
			}
		} else {
			result = YBEGIN + 10;
		}

		return result;
	}

	private int noteHeight(int value) {
		return value * SPACE_Y_BETWEEN_NOTES;
	}

	private String[] rechercher(File fichier) {
		ArrayList<String> result1 = new ArrayList<String>();

		try {
			for (File temp : fichier.listFiles()) {
				result1.add(temp.getName());
			}
			String result[] = new String[result1.size()];
			for (int i = 0; i < result1.size(); i++) {
				result[i] = result1.get(i);
			}
			return result;
		} catch (java.lang.NullPointerException e) {
			System.err.println(
					"Windows a refus� l'acc�s au dossier " + fichier.getAbsoluteFile() + " lors de la recherche \n");
		}
		String result[] = new String[1];
		result[0] = "pas de fichiers de sauvegarde";
		return result;
	}

	private void getSavedTypes(ComboBox styleComboBox) {
		if (rechercher(new File("types/")) != null) {
			for (String nomFichier : rechercher(new File("types/"))) {
				styleComboBox.getItems().add(nomFichier);
			}
		}
	}

	private void configureReaderBar(ImageView readerBar, PlayerAll playerAll) {
		readerBar.setTranslateX(-WIDTH / 2 + YBEGIN);
		readerBar
				.setTranslateY(-(((playerAll.getmusic().getMelody().getMelody().getMelody().size() / 18) + 2) * 160) / 2
						+ YBEGIN + 30 + 30);

		// Timeline is the animation of the readerbar ----------------------
		timeline = new Timeline();
		timeline.getKeyFrames()
				.add(new KeyFrame(Duration.ZERO, new KeyValue(readerBar.translateXProperty(), -300 + YBEGIN)));

		for (int k = 0; k < STAFFNUMBER; k++) {
			timeline.getKeyFrames().add(new KeyFrame(new Duration(timeSignatureOnEndOfAStaff[k] * 1000),
					new KeyValue(readerBar.translateXProperty(), 300)));
			timeline.getKeyFrames()
					.add(new KeyFrame(Duration.ZERO, new KeyValue(readerBar.translateXProperty(), -300 + YBEGIN)));

		}

		timeline.setAutoReverse(false);
		timeline.setCycleCount(1);

		// timeline.play();

		timeline.setOnFinished(new EventHandler<ActionEvent>() {

			public void handle(ActionEvent event) {
				if (staffIndex < STAFFNUMBER) {
					readerBar.setTranslateY(readerBar.getTranslateY() + YDISTANCE_BETWEEN_STAFF);
					staffIndex++;
					timeline.play();
				}
			}
		});

	}
}
