package musicGeneration;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;

import javafx.scene.transform.Scale;
import modes.Mode;

import com.sun.org.apache.xpath.internal.operations.Mod;

import chords.InterMod;

public class TypeDataBase {
	private HashMap<Integer, Type> typeDb;
	private String folder;
	
	
	public TypeDataBase(){
		typeDb = new HashMap<Integer, Type>();
		folder = "types";
		rechRecFolder();
	}
	
	@Override
	public String toString() {
		return "TypeDataBase [typeDb=" + typeDb + ", folder=" + folder + "]";
	}

	private void rechRecFolder(){
			File[] files = null; 
			File directoryToScan = new File(folder); 
			files = directoryToScan.listFiles(); 
			
		
			for(int i=0 ;i<files.length;i++){
				Load(files[i]);
			}
	}
	private void Load(File file){
		String chaine="";
		InterMod mod = new InterMod();
		//lecture du fichier texte	
		try{
			InputStream ips=new FileInputStream(file); 
			InputStreamReader ipsr=new InputStreamReader(ips);
			BufferedReader br=new BufferedReader(ipsr);
			String ligne;
			int i=0;
			int nbrline=0;
			/*définition des paramêtre à remplir*/
			String name="";
			ArrayList<Integer> instruChords = new ArrayList<Integer>();
			ArrayList<Integer> instruMelody = new ArrayList<Integer>();
			Mode mode = new Mode(1);
			int seuil=0;
			int tempo=0;
			String mot = "";
			int id=0;
			int u=0;
			while ((ligne=br.readLine())!=null){
				
				switch(u){
				case 0 :
					name = ligne;
					System.out.println("name");
					break;
					
				case 1 :
					System.out.println("chords");
					i=0;
					
						mot = new String();
						i=0;
						while(ligne.charAt(i) !=';'){
							while((ligne.charAt(i) !=',')&&(ligne.charAt(i)!=';')){
								mot = mot +ligne.charAt(i);

								i++;
								System.out.println(mot);

								System.out.println(ligne.charAt(i));

							}
								
								instruChords.add(Integer.parseInt(mot));
								mot = new String();

								if(ligne.charAt(i)!=';')
								i++;		
							
												
						}
					break;

				case 2 :
					System.out.println("chords");
					i=0;
					
						mot = new String();
						i=0;
						while(ligne.charAt(i) !=';'){
							while((ligne.charAt(i) !=',')&&(ligne.charAt(i)!=';')){
								mot = mot +ligne.charAt(i);

								i++;
								System.out.println(mot);

								System.out.println(ligne.charAt(i));

							}
								
								instruMelody.add(Integer.parseInt(mot));
								mot = new String();

								if(ligne.charAt(i)!=';')
								i++;		
							
												
						}
					break;
					
				case 3 :
					i=0;
					while(ligne.charAt(i) !=';'){
							mot = mot +ligne.charAt(i);
							i++;
					}
					
					mode = new Mode(Integer.parseInt(mot));
					
					break;
					
				case 4 :
					mot = new String();
					i=0;
					while(ligne.charAt(i) !=';'){
							mot = mot +ligne.charAt(i);
							i++;
					}
					seuil = Integer.parseInt(mot);
					
					break;
					
				case 5 :
					mot = new String();
					i=0;
					while(ligne.charAt(i) !=';'){
							mot = mot +ligne.charAt(i);
							i++;
					}
					tempo = Integer.parseInt(mot);

					break;
					
				case 6 :
					mot = new String();
					i=0;
					while(ligne.charAt(i) !=';'){
							mot = mot +ligne.charAt(i);
							i++;
					}
					id = Integer.parseInt(mot);
					
					break;
					
				}

				u++;
			}

			typeDb.put(id, new Type(instruMelody, instruChords, seuil, mode, tempo, name));

			br.close(); 
		}		
		catch (Exception e){
			System.out.println(e.toString());
		}
	}
	
	public HashMap<Integer, Type> getDatabase(){
		return typeDb;
	}

}
