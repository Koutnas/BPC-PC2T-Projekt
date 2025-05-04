package sem_projekt;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.HexFormat;

public class Student_IBE extends Student{

	public Student_IBE(Integer id, String jmeno, String prijmeni, Integer narozeni, ArrayList<Integer> znamky) {
		super(id, jmeno, prijmeni, narozeni, znamky);
	}
	@Override
	public String SpecialAbility() {
		MessageDigest digest = null;
		try {
			digest = MessageDigest.getInstance("SHA-256");
		} catch (NoSuchAlgorithmException e) {
			System.out.println(e);
		}
		byte[] hash = digest.digest((jmeno+prijmeni).getBytes());
		String hexHash = HexFormat.of().formatHex(hash);
		return hexHash;
	}

}
