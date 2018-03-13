import java.io.File;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

public class TestGui {
	private static String difficulty;
	public static int SCORE;
	public static int ATTEMPTS;

	// Display final score for user
	private static void finalScore() {
		int overallScore = SCORE*100/ATTEMPTS;
		String msg="Your final score is " + overallScore + "%, ";
		if (overallScore >= 80) {
			msg+="great job!";
		} else {
			msg+="keep studying...";
		}
		JLabel label = new JLabel(msg);
		label.setHorizontalAlignment(JLabel.CENTER);
		label.setVerticalTextPosition(JLabel.BOTTOM);
		label.setHorizontalTextPosition(JLabel.CENTER);
		JOptionPane.showMessageDialog(null, label, "Overall Score", JOptionPane.PLAIN_MESSAGE);

	}
	// Randomizer helper
	private static void randomizer() {
		ArrayList<File> files = selectDifficulty();
		randomizer(files);
	}
	// Recursively selects a random quiz to run
	private static void randomizer(ArrayList<File> files) {
		Random rand = new Random();
		int index = rand.nextInt(files.size());
		File file = files.get(index);
		files.remove(index);
		try {
			ReadYaml quiz = new ReadYaml("/"+difficulty+"/"+file.getName());
			switch (quiz.getType()) {
			case 1 :  // Multiple Choice Question
				MultipleChoice question = new MultipleChoice(quiz);
				if (quiz.checkAnswer(question.MultipleChoiceWindow())) {
					SCORE++;
				}
				break;
			case 2 :  // Input Box Question
				InputBox question2 = new InputBox(quiz);
				if (quiz.checkAnswer(question2.InputBoxWindow())) {
					SCORE++;
				}
				break;
			case 3 :  // Check Box Question
				CheckBox question3 = new CheckBox(quiz);
				if (quiz.checkAnswer(question3.CheckBoxWindow())) {
					SCORE++;
				}
				break;
			default :  // Random Expression Generator
				Expression question4 = new Expression();
				if ((difficulty.equals("cs143") ? question4.eval(9) : question4.eval(7))) {
					SCORE++;
				}
			}
			ATTEMPTS++;
			if (files.size() == 0)  {
				finalScore();
			} else {
				randomizer(files);
			}
		} catch (Exception e) {
			finalScore();
		}
	}
	// Select a difficulty level
	private static ArrayList<File> selectDifficulty() {
		DifficultySelection lvl = new DifficultySelection();
		File dir=null;
		switch (lvl.DifficultySelectionWindow()) {
		case 1:
			difficulty="cs142";
			break;
		case 2:		
			difficulty="cs143";
			break;
		}
		ArrayList<File> tmp= new ArrayList<>();
		dir = new File("resources/"+difficulty);
		for (File f: dir.listFiles()) tmp.add(f); 
		return tmp;
	}

	//	public static void main(String[] args) {
	public static void main(String[] args) {
		// Kick off quizzes
//		randomizer();

		// TODO Remove this comment and all code below when deployed
		// Insert your test quiz below
		//		ReadYaml quiz = new ReadYaml("/cs143/Collections.yml");
		try {
			ArrayList<File> files = selectDifficulty();
			for (File file : files) {
				System.out.println("/"+difficulty+"/"+file.getName()+"\n----");
				ReadYaml quiz = new ReadYaml("/"+difficulty+"/"+file.getName());
				if (quiz.getType() == 1) {
					MultipleChoice question = new MultipleChoice(quiz);
					System.out.println(quiz.checkAnswer(question.MultipleChoiceWindow()));
				} else if (quiz.getType() == 2) {
					InputBox question = new InputBox(quiz);
					System.out.println(quiz.checkAnswer(question.InputBoxWindow()));
				} else {
					CheckBox question = new CheckBox(quiz);
					System.out.println(quiz.checkAnswer(question.CheckBoxWindow()));
				}
			}
		} catch (Exception e) {

		}
	}

}
