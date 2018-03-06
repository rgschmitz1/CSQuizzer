//This is a simple program to read YAML extension files
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Map;

import org.yaml.snakeyaml.Yaml;

public class readYaml {
	public Map<String, String> values;
	
	@SuppressWarnings("unchecked")
	public readYaml(String file) {
		Yaml yaml = new Yaml();
		InputStream in = readYaml.class.getResourceAsStream(file);
		values = (Map<String, String>)yaml.load(in);
	}
	// Get question to display
	public String getQuestion() {
		return values.get("Question");
	}
	// Get answers to display
	public ArrayList<String> getAnswers() {
		ArrayList<String> ret=new ArrayList<>();
		for (String key : values.keySet()) {
			if (!key.matches("Answer.")) continue;
			ret.add(values.get(key));
		}
		//System.out.println(ret.toString());
		return ret;
	}
	// Check the users answer against correct answer
	public boolean checkAnswer(String s) {
		return s.equals(values.get("CorrectAnswer"));
	}
	// Get Quiz type, 1 is multiple choice, 2 is check box, 3 is input box
	public int getType() {
		return Integer.parseInt(values.get("Type"));
	}
	// Get Quiz title
	public String getTitle() {
		return values.get("Title");
	}

}
