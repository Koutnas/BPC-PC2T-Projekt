package sem_projekt;
import java.util.HashMap;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
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
		IBE.sort(null);
		TLI.sort(null);
		
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
		Double SoucetIBE = 0.0;
		Integer PocetIBE = 0;
		Double SoucetTLI = 0.0;
		Integer PocetTLI = 0;
		
		for(Student student: StudentHashMap.values()) {
			if(student instanceof Student_IBE) {
				SoucetIBE = SoucetIBE + student.getStudijniPrumer();
				PocetIBE = PocetIBE + 1;
			}
			else {
				SoucetTLI = SoucetTLI + student.getStudijniPrumer();
				PocetTLI = PocetTLI + 1;
			}
		}

		if (PocetIBE > 0) {System.out.println("Celkovy studijni prumer v oboru IBE: "+(double)(SoucetIBE/PocetIBE));}
		else {System.out.println("Celkovy studijni prumer v oboru IBE: "+0.0);}
		if (PocetTLI > 0) {System.out.println("Celkovy studijni prumer v oboru TLI: "+(double)(SoucetTLI/PocetTLI));}
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
	public Boolean saveStudent(Integer ID, String nazevSouboru) {
		Student student = StudentHashMap.get(ID);
		
		try{
			FileOutputStream soubor = new FileOutputStream(nazevSouboru+".ser");
			ObjectOutputStream oos = new ObjectOutputStream(soubor);
			oos.writeObject(student);
			oos.close();
			soubor.close();
			return true;
			
		}catch(IOException e){
			return false;
		}
	}
	public Student loadStudent(String nazevSouboru) {
		Student student;
		try {
		FileInputStream soubor = new FileInputStream(nazevSouboru+".ser");
		ObjectInputStream ois = new ObjectInputStream(soubor);
		
		student = (Student)ois.readObject();
		
		ois.close();
		soubor.close();
		
		return student;
		}catch(IOException | ClassNotFoundException e) {
			return null;
		}
	}
	public Boolean zaraditStudenta(Student student) {
		if(StudentHashMap.get(student.getId())==null) {
			StudentHashMap.put(student.getId(), student);
			return true;
		}else{
			student.setId(NextStudentID);
			StudentHashMap.put(student.getId(), student);
			NextStudentID = NextStudentID + 1;
			return false;
		}
	}
}
