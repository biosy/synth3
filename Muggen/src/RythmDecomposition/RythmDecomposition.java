package RythmDecomposition;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;

import chords.InterMod;


public class RythmDecomposition {
	
	
	private HashMap<Integer, ArrayList<ArrayList<Integer>>> decomposition;
	private String file = "decompo.txt";
	public RythmDecomposition(){
		decomposition = new HashMap<Integer, ArrayList<ArrayList<Integer>>>();
		loadDec();
	}
	
	
	private void loadDec(){
		//lecture du fichier texte
			InputStream ips = null;
			try {
				ips = new FileInputStream(file);
			} catch (FileNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} 
			InputStreamReader ipsr=new InputStreamReader(ips);
			BufferedReader br=new BufferedReader(ipsr);
			String ligne;
			int i=0;
			int j=0;
			int u=1;
			ArrayList<Integer> list = new ArrayList<>();
			ArrayList<ArrayList<Integer>> lists = new ArrayList<>();

			try {
				while ((ligne=br.readLine())!=null){
					
					
						j = Character.getNumericValue(ligne.charAt(0));
						i=3;
						if(j==u){


						}
						else{

							decomposition.put(u, lists);
							lists = new ArrayList<ArrayList<Integer>>();


						}
						while(ligne.charAt(i) != ';'){
							list.add(Character.getNumericValue(ligne.charAt(i)));
							//System.out.print(Character.getNumericValue(ligne.charAt(i)));

							i++;
							if(ligne.charAt(i)==','){
								i++;
							}
						}

						lists.add(list);
					

						list = new ArrayList<Integer>();

						u=j;
				}
				br.close(); 
				decomposition.put(u, lists);

			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	
	


	@Override
	public String toString() {
		return "RythmDecomposition [decomposition=" + decomposition + ", file="
				+ file + "]";
	}


	public HashMap<Integer, ArrayList<ArrayList<Integer>>> getDecompo(){
		return decomposition;
	}
}