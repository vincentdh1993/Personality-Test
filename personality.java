import java.util.*;
import java.io.*;
import java.util.*;
import java.io.*;

// Vincent Lee
// COSI 12 - Professor Olga
// PA1

public class personality {

	public static void main(String[] args) throws FileNotFoundException {
		Scanner input = new Scanner(new File("personality.txt"));
		PrintStream output = new PrintStream(new File("output.txt")); // The
																		// result
																		// file
																		// is
																		// output.txt
		filefinder(); // ask user to find the file
		while (input.hasNextLine()) {
			String name = input.nextLine(); // define the name
			String test = input.nextLine(); // define the answers
			test = test.toUpperCase(); // make all the letters to uppercase to
										// runthrough the test.
			int Acount[] = Acount(test); // create new array which stores the A
											// answers.
			int Bcount[] = Bcount(test); // create new array which stores the B
											// answers.
			int percent[] = percent(Bcount, Acount); // Calculate the price
														// based on the arrays.
			String[] letter = letter(percent);
			result(name, percent, letter, Acount, Bcount, output);
			lastpercent(percent, output);
			output.print(" = ");
			lastLetter(letter, output);
		}
	}

	public static void filefinder() { // the filefinder static method which asks
										// user to input the file name.
		Scanner console = new Scanner(System.in);
		System.out.print("Input file name: ");
		File f = new File(console.next());
		while (!f.exists()) { // if the file is not found ask again.
			System.out.print("File not found. Try again: ");
			f = new File(console.next());
		}
		System.out.println("Output file name: output.txt");
	}

	public static int[] Acount(String test) { // counting A answers from the
												// text file.
		int[] Acount = new int[4];
		for (int i = 0; i < test.length(); i++) {
			char t = test.charAt(i);
			if (t == 'A') {
				if (i % 7 == 0) { // 1st section
					Acount[0]++;
				}
				if (i % 7 == 1 || i % 7 == 2) { // 2nd section
					Acount[1]++;
				}
				if (i % 7 == 3 || i % 7 == 4) { // 3rd section
					Acount[2]++;
				}
				if (i % 7 == 5 || i % 7 == 6) { // 4th section
					Acount[3]++;
				}
			}
		}
		return Acount;
	}

	public static int[] Bcount(String test) { // Counting B answers from the
												// text file.
		int[] Bcount = new int[4];
		for (int i = 0; i < test.length(); i++) {
			char t = test.charAt(i);
			if (t == 'B') {
				if (i % 7 == 0) { // 1st section
					Bcount[0]++;
				}
				if (i % 7 == 1 || i % 7 == 2) { // 2nd section
					Bcount[1]++;
				}
				if (i % 7 == 3 || i % 7 == 4) { // 3rd section
					Bcount[2]++;
				}
				if (i % 7 == 5 || i % 7 == 6) { // 4th section
					Bcount[3]++;
				}
			}
		}
		return Bcount;
	}

	public static int[] percent(int[] Bcount, int[] Acount) { // static method
																// that gets the
																// percentage of
																// B answers.
		int[] percent = new int[4];
		percent[0] = 100 * Bcount[0] / Acount[0]+Bcount[0];
		for (int i = 1; i < Bcount.length; i++) {
			percent[i] = 100 * Bcount[i] / (Acount[i] + Bcount[i]); // the
																	// actual
																	// percent
		}
		for (int i = 0; i < percent.length; i++) {
			double d = percent[i];
			percent[i] = (int) Math.round(d); // rounding up the number into
												// interger.
		}
		return percent;
	}

	public static String[] letter(int[] percent) { // calculate the percentage
													// and turn into the letter
													// result.
		String[] letter = new String[4];
		for (int i = 0; i <= letter.length - 1; i++) {
			if (percent[i] > 50) {
				if (i == 0) {
					letter[0] = "I";
				} else if (i == 1) {
					letter[1] = "N";
				} else if (i == 2) {
					letter[2] = "F";
				} else {
					letter[3] = "P";
				}
			} else if (percent[i] < 50) {
				if (i == 0) {
					letter[0] = "E";
				} else if (i == 1) {
					letter[1] = "S";
				} else if (i == 2) {
					letter[2] = "T";
				} else {
					letter[3] = "J";
				}
			} else if (percent[i] == 50) { // if the percent is 50%, Put X
											// instead of other letter.
				letter[i] = "X";
			}
		}
		return letter;
	}

	public static void result(String name, int[] percent, String[] finishedType, int[] Acount, int[] Bcount,
			PrintStream output) { // printing the output by Name.
		output.println(name + ":");
		for (int i = 0; i < Acount.length; i++) {
			output.print(Acount[i] + "A-" + Bcount[i] + "B ");
		}
		output.println("");
	}

	public static void lastpercent(int[] percent, PrintStream output) { // printing
																		// the
																		// percentage
																		// at
																		// the
																		// output
																		// file.
		output.print("[");
		for (int i = 0; i < percent.length - 1; i++) {
			int z = percent[i];
			output.print(z + "%, ");
		}
		for (int i = 3; i < percent.length; i++) {
			int y = percent[3];
			output.print(y + "%");
		}
		output.print("]");
	}

	public static void lastLetter(String[] letter, PrintStream output) { // Pringting
																			// the
																			// final
																			// test
																			// result
																			// in
																			// the
																			// output
																			// file.
		String s = "";
		for (int i = 0; i < letter.length; i++) {
			s = letter[i];
			output.print(s);
		}
		output.println();
		output.println();
	}

} // Class gwalho
