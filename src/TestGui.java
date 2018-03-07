import java.io.File;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.JOptionPane;

public class TestGui {
	private static String difficulty;
	private static int score;
	
	// Recursively outputs a filename
	private static void randomizer(ArrayList<File> files) {
		if (files.size() == 0) return;
		Random rand = new Random();
		int index = rand.nextInt(files.size());
		File file = files.get(index);
		files.remove(index);
		//System.out.println("/"+file.getName());
		ReadYaml quiz = new ReadYaml("/"+difficulty+"/"+file.getName());
		switch (quiz.getType()) {
		// Multiple Choice Question
		case 1 :
			MultipleChoice question = new MultipleChoice(quiz);
			if (quiz.checkAnswer(question.MultipleChoiceWindow())) {
				score++;
			}
			break;
		// Input Box Question
		case 2 :
			String userInput = JOptionPane.showInputDialog(null, quiz.getQuestion(), "Input your answer");
			if (quiz.checkAnswer(userInput)) {
				score++;
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
				score++;
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
		// Select Difficulty
//		ArrayList<File> files = selectDifficulty();
//		// Randomly Select Files
//		randomizer(files);
		//		System.out.println(num);
		//		MultipleChoice question = new MultipleChoice();
		//		num = question.MultipleChoiceWindow(null);
		//		System.out.println(num);
		//		CheckBox question1 = new CheckBox();
		//		ArrayList<Integer> list = new ArrayList<Integer>();
		//		list = question1.CheckBoxWindow(null);
		//		System.out.println(list.toString());
				ReadYaml quiz = new ReadYaml("/cs143/Collections.yml");
				//System.out.println(quiz.values.toString());
				CheckBox question = new CheckBox(quiz);
				System.out.println(question.CheckBoxWindow());
		//System.out.println(quiz.values);
		//		System.out.println(quiz.getAnswers().toString());
		//System.out.println(quiz.values.get("CorrectAnswer"));
		//		System.out.println(quiz.checkAnswer("[you, are, how]"));
	}

}
