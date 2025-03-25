package sem_projekt;

import java.util.ArrayList;

public class Student_TLI extends Student{

	public Student_TLI(Integer id, String jmeno, String prijmeni, Integer narozeni, ArrayList<Integer> znamky) {
		super(id, jmeno, prijmeni, narozeni, znamky);
	}

	@Override
	public String SpecialAbility() {
		String morse[] = {".-","-...","-.-.","-..",".","..-.","--.","....","..",".---","-.-",".-..","--","-.","---",".--.","--.-",".-.","...","-","..-","...-",".--","-..-","-.--","--.."};
		String morse_jmeno_prijmeni = "";
		
		for(int i = 0;i<(jmeno.length());++i) {
			if(i == 0) {
				morse_jmeno_prijmeni = morse_jmeno_prijmeni + morse[((int)jmeno.charAt(i)+32-(int)'a')]+" ";
			}
			else {
				morse_jmeno_prijmeni = morse_jmeno_prijmeni + morse[((int)jmeno.charAt(i)-(int)'a')]+" ";
			}
		}
		morse_jmeno_prijmeni = morse_jmeno_prijmeni + "  ";
		for(int i = 0;i<(prijmeni.length());++i) {
			if(i == 0) {
				morse_jmeno_prijmeni = morse_jmeno_prijmeni + morse[((int)prijmeni.charAt(i)+32-(int)'a')]+" ";
			}
			else {
				morse_jmeno_prijmeni = morse_jmeno_prijmeni + morse[((int)prijmeni.charAt(i)-(int)'a')]+" ";
			}
		}
		
		return morse_jmeno_prijmeni;
	}

}
