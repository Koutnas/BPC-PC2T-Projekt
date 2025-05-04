package sem_projekt;

import java.util.Scanner;

public class runTest {
	
	public static void main(String[] args) {
		OvladacDatabaze ovladac = new OvladacDatabaze();
		ovladac.nacistStudenty();
		Scanner sc = new Scanner(System.in);
		Boolean run = true;
		
		while(run){
			System.out.println("\n");
			System.out.println("Vyberte pozadovanou cinnost:");
			System.out.println("1 .. vlozeni noveho studenta");
			System.out.println("2 .. vyhodit studenta z univerzity");
			System.out.println("3 .. vypis informaci o studentovi");
			System.out.println("4 .. zadat novou znamku studentovi");
			System.out.println("5 .. spustit specialni vlastnost studenta");
			System.out.println("6 .. vypis informace o vsech studentech nactenych v databazi");
			System.out.println("7 .. vypis celkovy studijnim prumer v oborech");
			System.out.println("8 .. vypis celkovy pocet studentu v oborech");
			System.out.println("9 .. ulozit studenta do souboru");
			System.out.println("10 .. nacist studenta ze souboru");
			System.out.println("11 .. ukoncit program");
			
			switch(volba(sc)) {
			case 1:
				ovladac.addStudentManual(sc);
				break;
			case 2:
				ovladac.removeStudent(sc);
				break;
			case 3:
				ovladac.getStudent(sc);
				break;
			case 4:
				ovladac.pridatZnamku(sc);
				break;
			case 5:
				ovladac.specialniVlastnost(sc);
				break;
			case 6:
				ovladac.VypisStudentu();
				break;
			case 7:
				ovladac.vypisPrumeru();
				break;
			case 8:
				ovladac.vypisPocetStudentu();
				break;
			case 9:
				ovladac.ulozitStudenta(sc);
				break;
			case 10:
				ovladac.nacistStudenta(sc);
				break;
			case 11:
				ovladac.ulozitStudenty();
				run = false;
				break;
			}
		}
	}
	public static Integer volba(Scanner sc) {
		Integer cislo = 0;
		try {
			cislo = sc.nextInt();
		}catch(Exception e){
			System.out.println("Zadejte prosim cele cislo: ");
			sc.nextLine();
			cislo = volba(sc);
		}
		return cislo;
		
	}

}
