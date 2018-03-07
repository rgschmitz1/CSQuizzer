import java.util.concurrent.ThreadLocalRandom;

import javax.swing.JOptionPane;

//import bsh.EvalError;
import bsh.Interpreter;

public class Expression {
	public static int difficulty = 9;
	static Interpreter evaluater = new Interpreter();

	public static String expressionrandommer(int i) {
		int operant = 0;
		String sign = null;
		if (i == 1) {
			return "";
		}
		operant = ThreadLocalRandom.current().nextInt(0, 4 + 1);
		switch (operant) {
		case 0 :
			sign = "+";
			break;
		case 1 :
			sign = "-";
			break;
		case 2 :
			sign = "/";
			break;
		case 3 :
			sign = "%";
			break;
		case 4 :
			sign = "*";
			break;
		}
		return sign + " ";
	}

	public static int answerkeeper(int answer, int currentnumber, String currentoperand) {
		// System.out.println(
		// "answer: " + answer + " currentnumber: " + currentnumber + " currentoperand:
		// " + currentoperand);
		int returned = 0;
		if (currentoperand.contains("+")) {
			returned = answer + currentnumber;
		} else if (currentoperand.contains("-")) {
			returned = answer - currentnumber;
		} else if (currentoperand.contains("/")) {
			returned = answer / currentnumber;
		} else if (currentoperand.contains("%")) {
			returned = answer % currentnumber;
		} else if (currentoperand.contains("*")) {
			returned = answer * currentnumber;
		}
		// System.out.println("new returnnn: " + returned);
		return returned;
	}

	// TODO replace main with constructor when deployed?
	public boolean expressionWindow() {
		String result = "";

		int currentnumber = 0;
		String currentoperand = null;
		int answer = 0;
		boolean firsttime = true;
		while (difficulty > 0) {
			currentnumber = ThreadLocalRandom.current().nextInt(0, 99 + 1);
			if (firsttime) {
				answer = currentnumber;
				firsttime = false;
			} else {
				answer = answerkeeper(answer, currentnumber, currentoperand);
			}
			// System.out.println("new answer: " + answer);
			currentoperand = expressionrandommer(difficulty);

			result += currentnumber + " " + currentoperand;

			--difficulty;
		}
		//System.out.println("question: " + result);
		String myWeirdExpression = result;
		while (true) {
			try {
				String output = "What is the output of the following expression?\n\n"+result+"\n\n";
				System.out.println(evaluater.eval(myWeirdExpression));
				int userInput = Integer.parseInt(JOptionPane.showInputDialog(null, output, "Input your integer"));
				//				return userInput.equals(evaluater.eval(myWeirdExpression));
				return (userInput == (int)evaluater.eval(myWeirdExpression));
			} catch (Exception e) {
				JOptionPane.showMessageDialog(null, "You did not input a valid integer, please try again!");
			}
		}
	}
}
