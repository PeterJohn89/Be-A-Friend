import java.io.*;
import javax.swing.JOptionPane;
//Import classes - We are importing the whole library by adding the * to the end of our import class
// import java.io.* will import the bufferedReader class for reading and writing files 

//This is a application aimed at making new friends.
//It has a signup and login input
//The default login is username: admin - contact number 0411222333
public class BeAFriend {

	// Private Variables - These private variables below are only available in
	// this class BeAFriend
	private Friend[] listOfFriends;
	private int CurrentUsers;
	private int size;
	private String loggedInUser;
	private String loggedInUserBio;
	private int loggedInUserAge;
	private String loggedInNumber;

	// --------------BeAFriend Constructor--------------------//
	// Description: This special method can be used to set values and create a
	// object of a class.
	public BeAFriend(int size) {
		// Check to see the array size is more then zero, if so size equals 1
		if (size < 0) {
			int numUsers = Integer.parseInt(JOptionPane
					.showInputDialog("Enter a number"));
			this.size = numUsers;
		} else {
			this.size = size;
		}

		this.listOfFriends = new Friend[this.size];
		// ///--Method--/////
		// This method is used to get the users from our file and put them
		// into a object called friend, the but that object in our array.
		// NOTE:---------- User Must be hard coded in the file before this
		// application can work
		currentUsersSize();

		// ///--Main Menu--/////
		// Now we give the user a menu, with options to create a new users or
		// login

		// -----main menu start------//
		int optionNumber;
		String usersSelection;
		String mainMenu = "Welcome to Find a Freind\n";
		mainMenu += "1. New User \n";
		mainMenu += "2. Login \n";
		usersSelection = JOptionPane.showInputDialog(mainMenu);
		// -----main menu end------//

		// Check if the users input is not empty
		while (usersSelection != null) {
			// Convert the users input string to a integer so that we can check
			// the option number
			optionNumber = Integer.parseInt(usersSelection);

			// -----First Option 1------//
			// This menu option will let the user sign up, this uses a method
			// called addUser to store
			// the data in an object array and on our local file called
			// FriendsList.txt
			if (optionNumber == 1) {
				// Asking the user for there username, a short bio, and their
				// age.
				String NewUserName = JOptionPane
						.showInputDialog("Enter a Username");
				String NewBio = JOptionPane
						.showInputDialog("Enter a short bio...(hey there!)");
				int NewUserAge = Integer.parseInt(JOptionPane
						.showInputDialog("Enter you age"));
				// validator to check uses age less the zero, if so ask the user
				// for a valid age
				while (NewUserAge <= 0) {
					NewUserAge = Integer.parseInt(JOptionPane
							.showInputDialog("Please enter a valid age"));
				}
				// The contact number is a important final step, notice i have
				// added a while loop.
				// This will loop through our array to check and see if the new
				// users contact number is
				// the same as any of our users in the database, this is to stop
				// any duplication,
				// and a security step for any login issues.
				String NewContactNumber = JOptionPane
						.showInputDialog("Enter you contact number");
				int a = 0;
				while (a < CurrentUsers) {
					// The equals method is used to compare two strings, the
					// result is only true if
					// the two strings match, otherwise its the if statement is
					// false and the user
					// must enter another number.
					if (listOfFriends[a].getContactNumber().equals(
							NewContactNumber)) {
						JOptionPane
								.showMessageDialog(null,
										"Sorry that contact number has already been set");
						System.exit(0);
					}
					a++;
				}

				// ///--Method--/////
				// The method addUser will store the data in a object array.
				// Also the data is stored in a file, that we can use everytime
				// the user logins in
				addUser(NewUserName, NewBio, NewUserAge, NewContactNumber);
				JOptionPane.showMessageDialog(null,
						"successful...(to login press 2 in option menu)");
				JOptionPane.showInputDialog(mainMenu);

				// -----Second Option 2------//
				// This is our login option, here we will loop throught our
				// array to see if the users input
				// is matching.
			} else if (optionNumber == 2) {
				// Ask user for there username and contact number.
				String loginUserName = JOptionPane
						.showInputDialog("login with Username");
				String loginContactNumber = JOptionPane
						.showInputDialog("login with your contact number");
				// Setting a boolean login to false, if the users input is a
				// match to our arry the login boolean
				// will change to true will be allow access to the next menu
				boolean login = false;

				int x = 0;
				while (x < CurrentUsers) {
					// Here we are using a while loop throught our array call
					// listOfFriends, to check if
					// users and contact number equal. Notice I have used
					// equalsIgnoreCase, this method is similar to
					// equals, as we used before, however this method ignores
					// and case sensitive string in the username.
					// Also we have used the && operators, this will only
					// proceed if both statements are true
					// unlike || operator which will proceed if one or the other
					// statement is true
					if (listOfFriends[x].getUserName().equalsIgnoreCase(
							loginUserName)
							&& listOfFriends[x].getContactNumber()
									.equalsIgnoreCase(loginContactNumber)) {
						loggedInUser = listOfFriends[x].getUserName();
						loggedInUserBio = listOfFriends[x].getBio();
						loggedInUserAge = listOfFriends[x].getAge();
						loggedInNumber = listOfFriends[x].getContactNumber();
						login = true;
					}
					x++;

				}
				// If the is statement in our previous while loop doesn't find a
				// match in the username and password
				// the login boolean will remain false, and this if statement
				// will get the user to return to the
				// main menu will a unsuccessful message
				if (login == false) {
					JOptionPane.showMessageDialog(null,
							"Sorry unsuccessful login");
					usersSelection = JOptionPane.showInputDialog(mainMenu);
				} else {
					JOptionPane.showMessageDialog(null, "successful login...");
				}

				// -----Users Menu start------//
				// Now that the user is login we have a custom message and a
				// menu to find a friend.
				int userInputNum;
				String selection;

				// Show user detail of show all friends.
				String menu = "Welcome " + loggedInUser + " \n";
				menu += "1. Your details \n";
				menu += "2. Show all Friends";
				selection = JOptionPane.showInputDialog(menu);
				// -----Users Menu end------//

				// again we check if the users input is not empty
				while (selection != null) {
					// And convert the input to a integer
					userInputNum = Integer.parseInt(selection);
					// -----First Option 1------//
					// Show users details
					if (userInputNum == 1) {
						JOptionPane.showMessageDialog(null, "Username: "
								+ loggedInUser + " \n" + "Users Bio: "
								+ loggedInUserBio + " \n" + "User Age: "
								+ loggedInUserAge + " \n"
								+ "User Contact Number: " + loggedInNumber
								+ " \n");

						selection = JOptionPane.showInputDialog(menu);
						userInputNum = Integer.parseInt(selection);
						// -----Second Option 2------//
						// Show all users from array
					} else if (userInputNum == 2) {

						// While loop through array and in the system print show
						// all users
						String chooseUser;
						int t = 0;
						while (t < CurrentUsers) {
							System.out
									.println("option" + t + " Hi Im: "
											+ listOfFriends[t].getUserName()
											+ " About me: "
											+ listOfFriends[t].getBio());
							t++;
						}

						// Ask the user to pick a user number, this will give
						// the user there contact information
						chooseUser = JOptionPane
								.showInputDialog("pick a user from console");
						userInputNum = Integer.parseInt(chooseUser);

						int y = 0;
						while (y < CurrentUsers) {
							if (userInputNum == y) {
								JOptionPane.showMessageDialog(null, "Contact "
										+ listOfFriends[y].getUserName()
										+ " today, their contact number is "
										+ listOfFriends[y].getContactNumber());

							}
							y++;
						}
						// now exit application

						System.exit(0);

					} else {
						JOptionPane.showMessageDialog(null, "Invalid input!");
					}
					// if the selection is invalid show the user the menu again
					selection = JOptionPane.showInputDialog(menu);
				}

			}

		}
		usersSelection = JOptionPane.showInputDialog(mainMenu);

	}

	// --------------CurrentUsersSize Method--------------------//
	// Description: This is the first method to be called, it gets all the data
	// from the
	// files and creates a friend object and puts that object in our array
	// Reason: This is used to store our users data every time the app is
	// launch, as the array is empty once the application is closed.
	public void currentUsersSize() {
		// the BufferedReader reads text from a file
		BufferedReader inFile = null;
		int Newage;
		// The try & catch satement will execute this code if there is an error
		// the catch will print an error
		try {
			inFile = new BufferedReader(new FileReader("FriendsList.txt"));
			// Create a string to hold the file friends list
			String currLine = inFile.readLine();

			while (currLine != null && this.CurrentUsers < this.size) {

				// Using the split method to store the file string in an array
				// and break up the file data so that we can get the username,
				// bio, age, and contactnumber
				// this will be stored in an object and finally put in our array

				String[] splitComma = currLine.split(",");

				String userName = splitComma[0];
				String bio = splitComma[1];
				String age = splitComma[2];
				String contact = splitComma[3];

				// System.out.println(userName);
				Newage = Integer.parseInt(age);

				Friend newFriend = new Friend(userName, bio, Newage, contact);

				this.listOfFriends[this.CurrentUsers] = newFriend;

				this.CurrentUsers += 1;

				currLine = inFile.readLine();
			}

			inFile.close();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

	}

	// --------------add User Method--------------------//
	// Description: This is a two step method, first if current user is less the
	// the size of the array
	// Create a object of friend and add that object to the array, Second is to
	// add the users information to the file
	public void addUser(String userName, String bio, int age, String contact) {

		if (this.CurrentUsers < this.size) {
			Friend newFriend = new Friend(userName, bio, age, contact);
			this.listOfFriends[this.CurrentUsers] = newFriend;
			this.CurrentUsers += 1;

			try {
				// Notice in my new FileWriter i have added the boolean of true,
				// this is because the
				// parameters form the java doc indicates the second parameter
				// is
				// whether the file is to append
				// append or not. This is save our work and not overwrite any
				// past
				// users. The defult setting
				// is set to false.
				BufferedWriter outFile = new BufferedWriter(new FileWriter(
						"FriendsList.txt", true));

				outFile.write(userName + "," + bio + "," + age + "," + contact);
				outFile.newLine();
				// newLine will change to the file to the next line

				// Once finished with the file close it.
				outFile.close();
				JOptionPane.showMessageDialog(null, "User Saved....");

			} catch (Exception e) {
				JOptionPane.showMessageDialog(null, e.getMessage());
			}
			// The code below must be placed inside a method
		}
	}

	// --------------show users Method--------------------//
	// Description: This method is used to show all users in the firends list
	// text file
	// this is a method to read all line in our text file
	public void showUsers() {
		int readLine = 0;
		try {
			BufferedReader FriendsFile = null;
			FriendsFile = new BufferedReader(new FileReader("FriendsList.txt"));

			String inlineUser = FriendsFile.readLine();
			int x = 0;
			while (inlineUser != null) {

				// display all user
				System.out.println(inlineUser);
				readLine++;
				inlineUser = FriendsFile.readLine();

			}
			FriendsFile.close();
			// catch statement displays a error message
			// the getMessage will push out a error message based on the try
			// code
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

	}

	// Create a max number of 10
	public static void main(String[] args) {
		BeAFriend friendObj = new BeAFriend(10);
	}

}
