package sem_projekt;

import java.util.InputMismatchException;
import java.util.Scanner;

public class OvladacDatabaze {
	
	private StudentDatabaze databaze;
	
	public OvladacDatabaze() {
		this.databaze = new StudentDatabaze();
	}
	public void VypisStudentu() {
		if(databaze.getCurrentSize() > 0 ) {
		databaze.vypisStudentu();
		}
		else {
			System.out.println("Databaze neobsahuje ani jednoho studenta");
		}
	}
	public void vypisPrumeru() {
		if(databaze.getCurrentSize() > 0 ) {
			databaze.vypisPrumerOboru();
		}
		else {
			System.out.println("Databaze neobsahuje ani jednoho studenta");
		}
	}
	public void vypisPocetStudentu(){
		databaze.getPocetStudentu();
	}
	public void removeStudent(Scanner sc) {
		Integer ID;
		ID = checkStudentID(sc);
		if(ID == null) {return;}
		databaze.removeStudent(ID);
		System.out.println("Student byl uspesne odebran");
	}
	public void pridatZnamku(Scanner sc) {
		Integer Znamka = null;
		Integer ID;
		ID = checkStudentID(sc);
		if(ID == null) {return;}
		try {
			System.out.print("Zadejte znamku, kterou chcete pridat: ");
			Znamka = sc.nextInt();
		}catch(InputMismatchException e){
			System.out.println("Byl zadan spatny typ.");
			return;
		}
		if(Znamka <= 5 && Znamka>=1) {
			databaze.addZnamka(ID, Znamka);
			System.out.println("Znamka byla uspesne pridana");
		}
		else {
			System.out.println("Znamka musi byt v intervalu <1,5>");
			return;
		}
		
	}
	
	public void getStudent(Scanner sc) {
		Integer ID;
		ID = checkStudentID(sc);
		if(ID == null) {return;}
		System.out.println(databaze.getStudent(ID));
		}
	
	public void addStudentManual(Scanner sc) {
		String jmeno = null;
		String prijmeni = null;
		Integer narozeni = null;
		Integer obor = null;
		
		try {
			System.out.print("Zadejte krestni jmeno noveho studenta (String): ");
			jmeno = sc.next();
			System.out.print("Zadejte prijmeni noveho studenta (String): ");
			prijmeni = sc.next();
			System.out.print("Zadejte rok narozeni noveho studenta (Integer): ");
			narozeni = sc.nextInt();
			System.out.print("Zadejte studijni obor noveho studenta IBE{1} nebo TLI{0} (Integer): ");
			obor = sc.nextInt();
		}catch(InputMismatchException e) {
			System.out.println("Byl zadan spatny typ do jedne z polozek");
			sc.nextLine();
			return;}
		
		if(!jmeno.matches("^[a-zA-Z]+$")){
			System.out.println("Jmeno: "+jmeno+" neni ve spravnem formatu.");
			return;
		}
		if(!prijmeni.matches("^[a-zA-Z]+$")) {
			System.out.println("Prijmeni: "+prijmeni+" neni ve spravnem formatu.");
			return;
		}
		if(!(narozeni > 1900 && narozeni < 2025)) {
			System.out.println("Narozeni: "+String.valueOf(narozeni)+" neni ve spravnem formatu.");
			return;
		}
		if(obor != 1 && obor != 0) {
			System.out.println("Dany obor neexistuje zvolte mezi 1-IBE nebo 0-TLI.");
			return;
		}
		databaze.addStudentManual(jmeno, prijmeni, narozeni,obor);
		System.out.println("Student byl uspesne pridan");

		
	}
	public void specialniVlastnost(Scanner sc) {
			Integer ID;
			ID = checkStudentID(sc);
			if(ID == null) {return;}
			System.out.println(databaze.getStudent(ID).SpecialAbility());
		}

	private Integer checkStudentID(Scanner sc) {
		Integer ID = null;
		
		try {
			System.out.print("Zadejte ID studenta (Integer): ");
			ID = sc.nextInt();
		}catch(InputMismatchException e) {
			System.out.println("Byl zadan spatny typ");
			sc.nextLine();
			return null;
		}
		if(databaze.getStudent(ID)==null) {
			System.out.println("Error - Student s timto ID neexistuje");
			return null;}
		return ID;
	}
	public void ulozitStudenta(Scanner sc) {
		Integer ID;
		String nazevSouboru;
		
		ID = checkStudentID(sc);
		if(ID == null) {return;}
		try {
			System.out.print("Zadejte nazev souboru do ktereho chcete studenta ulozit (String): ");
			nazevSouboru = sc.next();
		}catch(InputMismatchException e) {
			System.out.println("Byl zadan spatny typ.");
			return;
		}
		if(!databaze.saveStudent(ID,nazevSouboru)) {
			System.out.println("Nastala chyba pri ukladani souboru");
			return;
		}
		System.out.println("Student byl uspesne ulozen.");
	}
	public void nacistStudenta(Scanner sc){
		String nazevSouboru;
		Student newStudent;
		
		try {
			System.out.print("Zadejte nazev souboru do ktereho chcete studenta ulozit (String): ");
			nazevSouboru = sc.next();
		}catch(InputMismatchException e) {
			System.out.println("Byl zadan spatny typ.");
			return;
		}
		newStudent = databaze.loadStudent(nazevSouboru);
		if(newStudent == null) {
			System.out.println("Nastala chyba pri nacitani studenta ze souboru.");
			return;
		}
		if(databaze.zaraditStudenta(newStudent)) {
			System.out.println("Student byl uspesne pridan ze souboru.");
			return;
		}
		System.out.println("Student byl uspesne pridan ze souboru, ale nastala kolize s ID proto nacteny student dostal nove ID.");
	}
	public void ulozitStudenty() {
		databaze.saveToSQL();
	}
	public void nacistStudenty() {
		databaze.loadFromSQL();
	}
}

