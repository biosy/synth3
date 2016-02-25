package chords;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;

import markovChain.Markov;

public class ChordCollectionDataBase {
	
	private Markov markov;
	private int size;
	private String file;
	
	public ChordCollectionDataBase(int size, String file){
		this.file = file;
		markov = new Markov(size);
		this.file = file;
		load();
	}
	
	
	private void load(){
		markov.init();

		//lecture du fichier texte	
		try{
			InputStream ips=new FileInputStream(file); 
			InputStreamReader ipsr=new InputStreamReader(ips);
			BufferedReader br=new BufferedReader(ipsr);
			String ligne;
			String chaine;
			int i=0;
			int j=0;
			int proba=0;
			while ((ligne=br.readLine())!=null){
				
				i = Character.getNumericValue(ligne.charAt(0)-1);
				j = Character.getNumericValue(ligne.charAt(2)-1);
				chaine = String.valueOf(ligne.charAt(4))+ String.valueOf(ligne.charAt(5));
				if(ligne.charAt(6)!= ';')
					chaine = chaine + String.valueOf(ligne.charAt(6));
				proba =Integer.parseInt(chaine);
				markov.addLink(i, j, proba);
			}

			br.close(); 
		}		
		catch (Exception e){
			System.out.println(e.toString());
		}
	}


	public int getSize() {
		return size;
	}

	public Markov getMarkov(){
		return markov;
	}

	@Override
	public String toString() {
		return "ChordCollectionDataBase [markov=" + markov + ", size=" + size
				+ ", file=" + file + "]";
	}


}
