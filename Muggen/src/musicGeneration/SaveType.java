package musicGeneration;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class SaveType {
	
	private Type type;
	private String file;
	
	public SaveType(Type type, String file){
		this.type = type;
		this.file = file;
		save();
	}
	
	public void save(){
		try
		{
			/**
			 * BufferedWriter a besoin d un FileWriter, 
			 * les 2 vont ensemble, on donne comme argument le nom du fichier
			 * true signifie qu on ajoute dans le fichier (append), on ne marque pas par dessus 
			 
			 */
			FileWriter fw = new FileWriter("types/"+file, true);
			
			// le BufferedWriter output auquel on donne comme argument le FileWriter fw cree juste au dessus
			fw.write(type.getName()+"\r\n");
			
			String mot = type.getInstruChords().get(0)+"";
			
			for(int i=1;i<type.getInstruChords().size();i++){
				mot = mot +","+type.getInstruChords().get(0);
			}
			mot = mot +";\r\n";
			
			fw.write(mot);
			
			mot = type.getInstruChords().get(0)+"";
			
			for(int i=1;i<type.getInstruMelody().size();i++){
				mot = mot +","+type.getInstruMelody().get(0);
			}
			mot = mot +";\r\n";
			
			fw.write(mot);
			
			fw.write(type.getMode().getType()+";\r\n");
			fw.write(type.getSeuil()+";\r\n");
			fw.write(type.getTempo()+";\r\n");
			fw.write(type.getName()+";\r\n");


			//on marque dans le fichier ou plutot dans le BufferedWriter qui sert comme un tampon(stream)
			//on peut utiliser plusieurs fois methode write
			
			//ensuite flush envoie dans le fichier, ne pas oublier cette methode pour le BufferedWriter
			
			//et on le ferme
			fw.close();
			System.out.println("fichier créé");
		}
		catch(IOException ioe){
			System.out.print("Erreur : ");
			ioe.printStackTrace();
			}

	}

}
