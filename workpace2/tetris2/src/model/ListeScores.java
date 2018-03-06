package model;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class ListeScores {

	LinkedList<Score> listeScores = new LinkedList<Score>();

	public void addScore(Score score) {
		Boolean position = false;
	
		if (listeScores.isEmpty()) {
			listeScores.add(score);
		} else {
			for (Score scoresListe : listeScores) {
				if (score.getValue() > scoresListe.getValue()) {
					listeScores.add(listeScores.indexOf(scoresListe), score);
					position = true;
					break;
				}

			}
			if (position == false) {
				listeScores.addLast(score);
			}

		}

	}

	public void sauvegarderScores() throws IOException {

		PrintWriter sortie = new PrintWriter(new FileWriter("scores.txt", false));
		for (Score scores : listeScores) {

			sortie.println(scores.author + "|" + scores.value);
			sortie.flush();

		}

		sortie.close();

	}

	public void recupererScores() throws IOException {

		int compteur = 0;
		BufferedReader entree = new BufferedReader(new FileReader("scores.txt"));

		while (entree.ready() && compteur < 3) {
			String ligne = entree.readLine();
			StringTokenizer token = new StringTokenizer(ligne, "|");

			String nom = token.nextElement().toString();
			String valeur = token.nextElement().toString();

			addScore(new Score(nom, Integer.valueOf(valeur)));
			compteur++;

		}
		entree.close();

	}

	public Score rechercher(String auteur) {

		for (Score score : listeScores) {
			if (score.author == auteur) {
				return score;
			}

		}
		return null;

	}

	public int getScore(int position) {
		if (listeScores.size() > (position - 1)) {
			return listeScores.get((position - 1)).getValue();
		} else {
			return 0;
		}

	}

}