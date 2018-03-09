import java.io.File;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.JOptionPane;

public class TestGui {
	private String difficulty;
	public static int SCORE;
	public static int OVERALLSCORE;

	// Randomizer helper
	private void randomizer() {
		ArrayList<File> files = selectDifficulty();
		randomizer(files);
	}
	// Recursively selects a random quiz to run
	private void randomizer(ArrayList<File> files) {
		if (files.size() == 0) return;
		Random rand = new Random();
		int index = rand.nextInt(files.size());
		File file = files.get(index);
		files.remove(index);
		//System.out.println("/"+file.getName());
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
			expression question4 = new expression();
			if ((difficulty.equals("cs143") ? question4.eval(9) : question4.eval(7))) {
				SCORE++;
			}
		}
		randomizer(files);
	}
	// Select a difficulty level
	private ArrayList<File> selectDifficulty() {
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

	public static void main(String[] args) {
		// Kick off quizzes
		//		randomizer();
//		expression question4 = new expression();
//		System.out.println(question4.eval(7));

		// Insert your test quiz below
		ReadYaml quiz = new ReadYaml("/cs143/Collections.yml");
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

}
