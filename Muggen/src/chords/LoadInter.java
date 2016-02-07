package chords;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;

public class LoadInter {
	
	private InterCollection intermod;
	private String file;
	public LoadInter(String file){
		this.file = file;
		intermod = new InterCollection();
		Load(file);
	}
	
	public void Load(String fichier){
		String chaine="";
		InterMod mod = new InterMod();
		//lecture du fichier texte	
		try{
			InputStream ips=new FileInputStream(fichier); 
			InputStreamReader ipsr=new InputStreamReader(ips);
			BufferedReader br=new BufferedReader(ipsr);
			String ligne;
			int i=0;
			int j=0;
			while ((ligne=br.readLine())!=null){
				mod = new InterMod();
				i=0;
				j=0;
				
				
				chaine = ligne.substring(0, 5);
				mod.setName(chaine);
				i=6;
				
				Integer inte = 0;
				while(ligne.charAt(i)!= ';'){
					chaine = new String();
					
					while((ligne.charAt(i) != ',')&&(ligne.charAt(i) != ']')){
						inte =Integer.valueOf("" + inte + Character.getNumericValue(ligne.charAt(i))); 
						i++;
					}
					mod.addInter(inte);
					inte = 0;
					i++;
				}
				intermod.add(mod);

			}
			br.close(); 
		}		
		catch (Exception e){
			System.out.println(e.toString());
		}
	
		
	}
	@Override
	public String toString() {
		return "LoadInter [intermod=" + intermod + ", file=" + file + "]";
	}

	public String changeChar(String chaine, int idx, char monCharRempl) {
		  char[] tab = chaine.toCharArray();
		  tab[idx - 1] = monCharRempl;
		  return String.valueOf(tab);
		}

	public InterCollection getIntermod() {
		return intermod;
	}

	public void setIntermod(InterCollection intermod) {
		this.intermod = intermod;
	}
	
}
