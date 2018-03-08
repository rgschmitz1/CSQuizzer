import java.io.File;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.JOptionPane;

public class TestGui {
	private static String difficulty;
	public static int SCORE;

	// Recursively outputs a filename
	private static void randomizer(ArrayList<File> files) {
		if (files.size() == 0) return;
		Random rand = new Random();
		int index = rand.nextInt(files.size());
		File file = files.get(index);
		files.remove(index);
		//System.out.println("/"+file.getName());
		ReadYaml quiz = new ReadYaml("/"+difficulty+"/"+file.getName());
		switch (Integer.parseInt(quiz.getType())) {
		// Multiple Choice Question
		case 1 :
			MultipleChoice question = new MultipleChoice(quiz);
			if (quiz.checkAnswer(question.MultipleChoiceWindow())) {
				SCORE+=10;
			}
			break;
			// Input Box Question
		case 2 :
			String userInput = JOptionPane.showInputDialog(null, quiz.getQuestion(), "Input your answer");
			if (quiz.checkAnswer(userInput)) {
				SCORE+=10;
			}
			break;
			// Check Box Question
		case 3 :
			//			CheckBox question3 = new CheckBox(quiz);
			//			if (quiz.checkAnswer(question.CheckBoxWindow())) {
			//				score++;
			//			}
			break;
			// Random Expression Generator
		default :
			Expression question4 = new Expression();
			if (question4.expressionWindow()) {
				SCORE+=10;
			}
		}
		//		System.out.println(quiz.getQuestion());
		randomizer(files);
	}
	// Select a difficulty level
	private static ArrayList<File> selectDifficulty() {
		DifficultySelection lvl = new DifficultySelection();
		File dir=null;
		switch (lvl.DifficultySelectionWindow()) {
		case 1:
			dir = new File("resources/cs142");
			difficulty="cs142";
			break;
		case 2:
			dir = new File("resources/cs143");
			difficulty="cs143";
			break;
		}
		ArrayList<File> tmp= new ArrayList<>();
		for (File f: dir.listFiles()) tmp.add(f); 
		return tmp;
	}

	public static void main(String[] args) {
		// Insert your test quiz below
		ReadYaml quiz = new ReadYaml("/cs143/Collections.yml");
//		System.out.println(quiz.values.toString());
		System.out.println(quiz.getType());
		if (quiz.getType().equals("1")) {
			MultipleChoice question = new MultipleChoice(quiz);
			System.out.println(question.MultipleChoiceWindow());
		} else {
			CheckBox question = new CheckBox(quiz);
			System.out.println(question.CheckBoxWindow());
		}

	}

}
