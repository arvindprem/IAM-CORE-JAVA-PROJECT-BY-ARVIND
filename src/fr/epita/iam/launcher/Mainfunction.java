/**
 * 
 */
package fr.epita.iam.launcher;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

import fr.epita.iam.datamodel.Identity;
import fr.epita.iam.service.Jdbcidentitydao;


/**
 * @author arvind 
 *
 */
public class Mainfunction {
	private static Jdbcidentitydao dao;

	/**
	 * @param args
	 * @throws IOException 
	 * @throws SQLException 
	 */

	/**
	 * @param args
	 */
	public static void main(String[] args) throws SQLException {
		
		System.out.println("Hello, welcome to the IAM application");
		Scanner scanner = new Scanner(System.in);
		dao = new Jdbcidentitydao();
		
		
		
		//authentication
		System.out.println(" enter your login ");
		String login = scanner.nextLine();
		System.out.println(" enter your password");
		String password = scanner.nextLine();
		
		if(!authenticate(login, password)){
			scanner.close();
			return;
		}
		
		// menu
		boolean end = false;
		while (end == false){
		String Option = menu(scanner);
		
		switch (Option) {
		case "1":
			// creation
			createIdentity(scanner);
			break;
			// update
		case "2":
			Updateidentity(scanner);
			
			break;
			// delete
		case "3":
			Deleteidentity(scanner);
			break;
			// list
		case "4":
			listIdentities();
			break;
			//exit
		case "5":
			end = true;
	        break;
			
		default:
		
			System.out.println("wrong option("+ Option + ")");
			break;
		}
		}
		scanner.close();

	}

	/**
	 * @throws SQLException 
	 * 
	 */
	private static void listIdentities() throws SQLException {
		
		System.out.println("This is the list of all identities in the system");
		List<Identity> list = dao.readAll();
		int size = list.size();
		for(int i = 0; i < size; i++){
			System.out.println( i+ "." + list.get(i));
		}
		
	}

	/**
	 * @param scanner
	 * @throws SQLException 
	 */
	private static void createIdentity(Scanner scanner) throws SQLException {
		
		System.out.println("You've selected : Identity Creation");
		System.out.println("Please enter the Identity display name");
		String displayName = scanner.nextLine();
		System.out.println("Please enter the Identity email");
		String email = scanner.nextLine();
		System.out.println("Please enter the Identity DOB");
		String dob = scanner.nextLine();
		Identity newIdentity = new Identity(displayName, email,dob);
		dao.writeIdentity(newIdentity);

  System.out.println("Identity created sucessfully");
	}
	private static void Updateidentity(Scanner scanner) throws SQLException {
		
		
		System.out.println("You have selected : change identity");
		System.out.println("Enter the Id number you want to update the identity:");
		String id = scanner.nextLine();
		dao.listIdentity(id);
		System.out.println("Do you want to modify(Y/N) ");
		String prompt = scanner.nextLine();
		if (prompt.equals("Y")){
		System.out.println("Modify the identity display name");
		String displayName = scanner.nextLine();
		System.out.println("Modify the Identity email");
		String email = scanner.nextLine();
		System.out.println("Modify  the Identity DOB");
		String dob = scanner.nextLine();
		Identity newIdentity = new Identity(displayName, email,dob);
		dao.Changeidentity(newIdentity,id);
		System.out.println("Identity "+id+ " Updated sucessfully");
	}
	}

	private static void Deleteidentity(Scanner scanner) throws SQLException {
		

		System.out.println("You have selected : Delete identity");
		System.out.println("Enter the Id number you want to delete the identity:");
		String id = scanner.nextLine();
		dao.listIdentity(id);
		System.out.println("Do you want to delete(Y/N) ");
		String prompt = scanner.nextLine();
		if (prompt.equals("Y")){
		dao.Disposeidentity(id);
		System.out.println("Identity "+id+ " deleted sucessfully");
	}
	}
		/**
	 * @param scanner
	 * @return
	 */
	private static String menu(Scanner scanner) {
		System.out.println("You're authenticated");
		System.out.println("Choose the actions that you need to perform :");
		System.out.println("1. Create an Identity");
		System.out.println("2. Update an Identity");
		System.out.println("3. Delete an Identity");
		System.out.println("4. List Identities");
		System.out.println("5. quit");
		System.out.println("your choice (1|2|3|4|5) ? : ");
		String answer = scanner.nextLine();
		return answer;
	}

	/**
	 * @param login
	 * @param password
	 */
	private static boolean authenticate(String login, String password) {

		// TODO replace this hardcoded check by the real authentication method
		return "arvind".equals(login) && "1234".equals(password);
	}

}