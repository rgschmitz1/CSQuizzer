import java.io.FileNotFoundException;

public class TestGui {

	//	public static void main(String[] args) {
	public static void main(String[] args) {
		API api = new API();
		// Kick off quizzes
		try {
			api.randomizer();
		} catch (FileNotFoundException e) {
			System.out.println("oops!");
		}
	}

}
