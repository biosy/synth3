package musicGeneration;
import melody.MelodyGeneration;
import modes.Mode;
import note.HarmonicNote;
import player.ChordPlayer;
import player.NotePlayer;
import player.ThreadedChordPlayer;
import player.ThreadedNotePlayer;
import rythm.Rythm;
import rythm.TimeSignature;
import scales.Scale;
import RythmDecomposition.RandRythm;
import RythmDecomposition.RythmGeneration;
import chords.Chord;
import chords.ChordGeneration;


public class MusicGeneration {
	
	
	private ChordGeneration cg;
	private MelodyGeneration melody;
	private Scale scale;
	private Rythm rythm;
	private int seuil;
	public MusicGeneration(Scale scale, Rythm rythm, int seuil){
		this.scale = scale;
		this.rythm = rythm;
		this.seuil = seuil;
		cg = new ChordGeneration(scale);
		cg.aleatoryStyle();

		melody = new MelodyGeneration(scale,cg.getCollection().getChords().getHarmonizedChords().get(0));
	}
	
	
	public void generation(){

		Chord chord = new Chord();
		RandRythm rand = new RandRythm();
		for(int size=0;size<10;size++){
			for(int i=0;i<cg.getCollection().getChords().getHarmonizedChords().size();i++){
				rand.RandomGeneration(6,seuil);
				chord = cg.getCollection().getChords().getHarmonizedChords().get(i);	
				System.out.println(chord);

				melody.setChord(chord);
				melody.reinit();

				melody.generate(rand);
				rand.RandomGeneration(6,seuil);
				melody.generate(rand);
				System.out.println(rand.getRythmes());

				System.out.println(melody.getMelody());

				//System.out.println("0");
			

			}

	}
	}
		
		public ChordGeneration getChords(){
			return cg;
		}
		
		public MelodyGeneration getMelody(){
			return melody;
		}


		public void setMelodyLength(int selectedItem) {
			// TODO Auto-generated method stub
			
		}

	}
	


