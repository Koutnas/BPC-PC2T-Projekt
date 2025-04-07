package sem_projekt;
import java.util.HashMap;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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
	public void addStudentSQL(Integer id,String jmeno,String prijmeni,Integer narozeni, ArrayList<Integer> znamky,Integer obor) {
		Student student;
		if(obor == 1) {
			student = new Student_IBE(id,jmeno,prijmeni,narozeni,znamky);
		}
		else {
			student = new Student_TLI(id,jmeno,prijmeni,narozeni,znamky);
		}
		StudentHashMap.put(id, student);
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
	
	private Connection establishConnection() {
		Connection conn = null;
		try {
			conn = DriverManager.getConnection("jdbc:sqlite:StudentskaDatabaze.db");
			return conn;
		}catch(SQLException e){
			System.out.println(e);
			System.out.println("Nepodarilo se ustanovit spojeni s databazi...");
			return null;
			}
	}
	
	public void saveToSQL() {
		Connection conn = establishConnection();
		if(conn == null) {
			return;
		}
		String sqlQuery = "DROP TABLE IF EXISTS studenti";
		try {
			Statement stmt = conn.createStatement();
			stmt.execute(sqlQuery);
		}catch(SQLException e){
			System.out.println(e);
		}
		sqlQuery = "CREATE TABLE IF NOT EXISTS Studenti (id INTEGER PRIMARYKEY,jmeno VARCHAR(255) NOT NULL,prijmeni VARCHAR(255) NOT NULL,narozeni YEAR NOT NULL,znamky VARCHAR,obor BIT)";
		try {
			Statement stmt = conn.createStatement();
			stmt.execute(sqlQuery);
		}catch(SQLException e){
			System.out.println(e);
			System.out.println("Doslo k chybe pri vytvareni databaze");
		}
		sqlQuery = "INSERT INTO Studenti(id,jmeno,prijmeni,narozeni,znamky,obor) VALUES(?,?,?,?,?,?)";
		try {
		for(Student student: StudentHashMap.values()) {
			ArrayList<Integer> znamky = student.getZnamky();
			String parsedZnamky = "";
			for(Integer i = 0; i<znamky.size();i++) {
				parsedZnamky = parsedZnamky + znamky.get(i)+",";
			}
			PreparedStatement pstmt = conn.prepareStatement(sqlQuery);
			pstmt.setInt(1,student.getId());
			pstmt.setString(2,student.getJmeno());
			pstmt.setString(3, student.getPrijmeni());
			pstmt.setInt(4, student.getNarozeni());
			pstmt.setString(5, parsedZnamky);
			pstmt.setInt(6,student instanceof Student_IBE?1:0);
			pstmt.executeUpdate();
			}
		}catch(SQLException e) {
			System.out.println(e);
			System.out.println("Nastala chyba pri ukladani studentu...");
			return;
		}
		try {
			conn.close();
		} catch (SQLException e) {
			System.out.println(e);
		}
		
	}
	public void loadFromSQL() {
		Connection conn = establishConnection();
		if(conn == null) {
			return;
		}
		String sqlQuery = "SELECT name FROM sqlite_master WHERE name=?";
		try {
			PreparedStatement pstmt = conn.prepareStatement(sqlQuery);
			pstmt.setString(1,"Studenti");
			ResultSet rs = pstmt.executeQuery();
			if(rs.getString("name") == null) {
				System.out.println("TABLE nebyl vytvoren pokracuji bez nactenych dat...");
				conn.close();
				return;
			}
		}catch(SQLException e){
			System.out.println(e);
			System.out.println("TABLE nebyl nalezen");
			return;
		}
		sqlQuery = "SELECT id,jmeno,prijmeni,narozeni,znamky,obor FROM Studenti";
		try {
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sqlQuery);
			Integer maxID = 0;
			while(rs.next()) {
				
				String ParsedZnamky = rs.getString("znamky");
				ArrayList<Integer> znamky = new ArrayList<Integer>();
				if(!ParsedZnamky.equals("")) {
					String[] StringZnamky = ParsedZnamky.split(",");
					Integer znamka;
					for(Integer i = 0;i<StringZnamky.length;++i) {
						znamka = Integer.valueOf(StringZnamky[i]);
						znamky.add(znamka);
					}
				}
				Integer currentID = rs.getInt("id");
				
				maxID = maxID < currentID?currentID:maxID;
				addStudentSQL(currentID,rs.getString("jmeno"),rs.getString("prijmeni"),rs.getInt("narozeni"),znamky,rs.getInt("obor"));
			}
			NextStudentID = maxID + 1;
		} catch (SQLException e) {
			System.out.println(e);
			System.out.println("Nastala chyba pri nacitani studentu...");
		}
		try {
			conn.close();
		} catch (SQLException e) {
			System.out.println(e);
		}
		
	}
}
