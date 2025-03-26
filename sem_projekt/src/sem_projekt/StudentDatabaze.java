package sem_projekt;
import java.util.HashMap;
import java.util.ArrayList;

public class StudentDatabaze {
	private HashMap<Integer,Student> StudentHashMap;
	private Integer NextStudentID;
	
	public StudentDatabaze() {
		StudentHashMap = new HashMap<Integer,Student>();
		NextStudentID = 0;
	}
	public HashMap<Integer,Student> getStudentHashMap(){
		return StudentHashMap;
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
}
