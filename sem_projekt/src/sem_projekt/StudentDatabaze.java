package sem_projekt;
import java.util.HashMap;
import java.util.Comparator;
import java.util.Collections;
import java.util.ArrayList;

public class StudentDatabaze {
	private HashMap<Integer,Student> StudentHashMap;
	private Integer NextStudentID;
	
	public StudentDatabaze() {
		StudentHashMap = new HashMap<Integer,Student>();
		NextStudentID = 0;
	}
	public void addStudentManual(String jmeno,String prijmeni,Integer narozeni, Integer obor) {
		Student student;
		if(obor == 1) {
			student = new Student_IBE(NextStudentID,jmeno,prijmeni,narozeni,new ArrayList<Integer>());
		}
		else {
			student = new Student_TLI(NextStudentID,jmeno,prijmeni,narozeni,new ArrayList<Integer>());
		}
		StudentHashMap.put(NextStudentID, student);
		NextStudentID = NextStudentID + 1;
		}
	public Student getStudent(Integer ID) {
		return StudentHashMap.get(ID);
	}
	public void removeStudent(Integer ID) {
		StudentHashMap.remove(ID);
	}
	public void addZnamka(Integer ID,Integer Znamka) {
		StudentHashMap.get(ID).addZnamku(Znamka);
	}
	public Integer getCurrentSize(){
		return StudentHashMap.size();
	}
	public void vypisStudentu() {
		ArrayList<Student> IBE = new ArrayList<Student>();
		ArrayList<Student> TLI = new ArrayList<Student>();
		
		for(Student student: StudentHashMap.values()) {
			if(student instanceof Student_IBE) {
				IBE.add(student);
			}
			else {
				TLI.add(student);
			}
		}
		Collections.sort(IBE,Comparator.comparing(s -> s.getPrijmeni()));
		Collections.sort(TLI,Comparator.comparing(s -> s.getPrijmeni()));
		
		System.out.println("Studenti studujici obor IBE: ");
		for(int i=0; i<IBE.size();++i) {
			System.out.println(IBE.get(i));
		}
		System.out.println("Studenti studujici obor TLI: ");
		for(int i=0; i<TLI.size();++i) {
			System.out.println(TLI.get(i));
		}
		
	}
	public void vypisPrumerOboru() {
		ArrayList<Student> IBE = new ArrayList<Student>();
		ArrayList<Student> TLI = new ArrayList<Student>();
		Double Soucet = 0.0;
		for(Student student: StudentHashMap.values()) {
			if(student instanceof Student_IBE) {
				IBE.add(student);
			}
			else {
				TLI.add(student);
			}
		}
		for(int i=0; i<IBE.size();++i) {
			Soucet = Soucet+IBE.get(i).getStudijniPrumer();
		}
		if (IBE.size() > 0) {System.out.println("Celkovy studijni prumer v oboru IBE: "+(Soucet/IBE.size()));}
		else {System.out.println("Celkovy studijni prumer v oboru IBE: "+0.0);}
		Soucet = 0.0;
		for(int i=0; i<TLI.size();++i) {
			Soucet = Soucet+TLI.get(i).getStudijniPrumer();
		}
		if (TLI.size() > 0) {System.out.println("Celkovy studijni prumer v oboru TLI: "+(Soucet/TLI.size()));}
		else {System.out.println("Celkovy studijni prumer v oboru IBE: "+0.0);}
		
	}
	public void getPocetStudentu() {
		Integer IBE = 0 ,TLI = 0;
		for(Student student: StudentHashMap.values()) {
			if(student instanceof Student_IBE) {
				IBE = IBE + 1;
			}
			else {
				TLI = TLI + 1;
			}
		}
		System.out.println("Celkovy pocet studentu, studujici obor IBE:"+IBE);
		System.out.println("Celkovy pocet studentu, studujici obor TLI:"+TLI);
	}
}
