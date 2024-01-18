import java.util.Scanner;

public class Join {

	public static void run() {

		Scanner sc = new Scanner(System.in);

		Member member = new Member();

		member.address = sc.next();
		member.gender = sc.next();
		member.name = sc.next();
		member.phoneNumber = sc.next();

		verify();
		insertDB();
		
		sc.close();
	}
	
	public static void verify() {
		
	}

	public static void insertDB() {

	}

}
