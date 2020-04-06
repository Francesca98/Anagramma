package it.polito.tdp.anagramma.model;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Ricerca {
	
	private List<String> soluzione = new LinkedList<>() ;
	private int N =0;

	/**
	 * Genera tutti gli anagrammi della parola specificata in ingresso.
	 * @param parola parola da anagrammare
	 * @return elenco di tutti gli anagrammi della parola data
	 */
	public List<String> anagrammi(String parola) {
		N = parola.length();
		parola= parola.toUpperCase();
		List<Character> caratteri =  new LinkedList<>()  ;
		for(int i=0;i<parola.length(); i++)
		{
			caratteri.add(parola.charAt(i));
		}
		for(Character C : caratteri)
		{
			System.out.println(C);
		}
		cerca("",0, caratteri);
		
		return this.soluzione;
	}

	
	/**
	 * Procedura ricorsiva per il calcolo dell'anagramma.
	 * 
	 * @param parziale parte iniziale dell'anagramma costruito finora
	 * @param livello livello della ricorsione, sempre uguale a parziale.length()
	 * @param disponibili insieme delle lettere non ancora utilizzate
	 */
	//e' la funzione ricorsiva
	private void cerca( String parziale, int livello, List<Character> disponibili) {
		//CASO TERMINALE
		if(disponibili.size()==0) //OPPURE disponibili.length == 0
			//CASO NORMALE -> aggiungere alla sokuzione parziale tutti i caratteri presenti fra i disponibili
		{
			this.soluzione.add(parziale);
			//return;
		}
		else
		{
			for(Character ch : disponibili)
			{
				String tentativo = parziale + ch; //la stringa non è modificabile 
				//disponibili.remove(); //ERRORE XK NON POSSO MODIFCICRE UNA LISTA MENTRE LA STO TERANDO
				 List<Character> rimasti = new LinkedList<>(disponibili);
				 rimasti.remove(ch);
				cerca(tentativo,livello+1,rimasti);
				//NON DEVO FARE BACKTRACK XK NON HO MODIFICAATO NT DI QUELLO CHE C'ERA ALL'INIZIO !
			}
		}
	}

}

/*
Dato di partenza: parola da anagrammare, di lunghezza N
Soluzione parziale: una parte dell'anagramma già costruito (i primi caratteri).
Livello: numero di lettere di cui è composta la soluzione parziale.
Soluzione finale: soluzione di lunghezza N -> caso terminale
Caso terminale: salvare la soluzione trovate
Generazione delle nuove soluzioni: provare a aggiungere una lettera, scegliendola
tra quelle che non sono ancora state utilizzate (nella soluzione parziale).
*/