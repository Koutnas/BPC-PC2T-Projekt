package sem_projekt;

import java.util.Scanner;

public class runTest {
	
	public static void main(String[] args) {
		OvladacDatabaze ovladac = new OvladacDatabaze();
		Scanner sc = new Scanner(System.in);
		Boolean run = true;
		
		while(run){
			System.out.println("\n");
			System.out.println("Vyberte pozadovanou cinnost:");
			System.out.println("1 .. vlozeni noveho studenta");
			System.out.println("2 .. vyhodit studenta z univerzity");
			System.out.println("3 .. vypis informaci o studentovi");
			System.out.println("4 .. zadat novou znamku studentovi");
			
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
