package sem_projekt;
import java.util.ArrayList;

public abstract class Student {
	
	protected Integer id;
	protected String jmeno;
	protected String prijmeni;
	protected Integer narozeni;
	protected ArrayList<Integer> znamky;
	
	public Student(Integer id,String jmeno,String prijmeni,Integer narozeni,ArrayList<Integer> znamky) {
		this.id = id;
		this.jmeno = jmeno;
		this.prijmeni = prijmeni;
		this.narozeni = narozeni;
		this.znamky = znamky;
	}

	public Integer getId() {
		return id;
	}

	public String getJmeno() {
		return jmeno;
	}

	public String getPrijmeni() {
		return prijmeni;
	}

	public Integer getNarozeni() {
		return narozeni;
	}

	public ArrayList<Integer> getZnamky() {
		return znamky;
	}
	public Double getStudijniPrumer() {
		Integer soucet = 0;
	
		for(int i = 0;i<znamky.size();++i) {
			soucet = soucet + znamky.get(i);
		}
		return (double)(soucet/znamky.size());
	}
	public Boolean addZnamku(Integer Znamka) {
		if(Znamka<1 || Znamka>5) {
			return false;
		}
		else {
			znamky.add(Znamka);
			return true;
		}
	}
	public String toString() {
		return ("ID: "+id+", Jmeno: "+jmeno+", Prijmeni: "+prijmeni+", Rok Narozeni: "+narozeni+", Studijni Prumer: "+getStudijniPrumer());
	}
	
	public abstract String SpecialAbility();

}
