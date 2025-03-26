package sem_projekt;

import java.util.InputMismatchException;
import java.util.Scanner;

public class OvladacDatabaze {
	StudentDatabaze databaze;
	public OvladacDatabaze() {
		this.databaze = new StudentDatabaze();
	}
	public void removeStudent(Scanner sc) {
		Integer ID = null;
		try {
			System.out.print("Zadejte ID studenta (Integer): ");
			ID = sc.nextInt();
		}catch(InputMismatchException e) {
			System.out.println("Byl zadan spatny typ");
			sc.nextLine();
			return;
		}
		if(databaze.getStudent(ID)==null) {
			System.out.println("Error - Student s timto ID neexistuje");
			return;
		}
		else {
			databaze.removeStudent(ID);
			System.out.println("Student byl uspesne odebran");
		}
	}
	public void pridatZnamku(Scanner sc) {
		Integer Znamka = null;
		Integer ID = null;
		try {
			System.out.print("Zadejte ID studenta (Integer): ");
			ID = sc.nextInt();
			System.out.print("Zadejte znamku kterou chcete pridat (Integer): ");
			Znamka = sc.nextInt();
		}catch(InputMismatchException e) {
			System.out.println("Byl zadan spatny typ");
			sc.nextLine();
			return;
		}
		if(databaze.getStudent(ID)==null) {
			System.out.println("Error - Student s timto ID neexistuje");
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
		Integer ID = null;
		
		try {
			System.out.println("Zadejte ID studenta (Integer): ");
			ID = sc.nextInt();
		}catch(InputMismatchException e) {
			System.out.println("Byl zadan spatny typ");
			sc.nextLine();
			return;
		}
		
		if(databaze.getStudent(ID)==null) {
			System.out.println("Error - Student s timto ID neexistuje");
			return;
		}
		else {
			System.out.println(databaze.getStudent(ID));
		}
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
}
