import java.awt.Dialog;
import javax.swing.JDialog;
import java.awt.GridBagLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import java.awt.GridBagConstraints;
import javax.swing.JRadioButton;
import javax.swing.JButton;
import javax.swing.ButtonGroup;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.ArrayList;

public class MultipleChoice {

	private JDialog frmMultipleChoiceQuestion;
	private static ButtonGroup buttonGroup = new ButtonGroup();
	private static ArrayList<JRadioButton> rdbtnAnswers = new ArrayList<>();
	private static ArrayList<GridBagConstraints> gbc_rdbtnAnswers = new ArrayList<>();

	/**
	 * Launch the application.
	 */
	public String MultipleChoiceWindow() {
		try {
			// Resize window based on content
			frmMultipleChoiceQuestion.pack();
			frmMultipleChoiceQuestion.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return getSelectedButton();
	}

	/**
	 * Create the application.
	 */
	public MultipleChoice(readYaml quiz) {
		initialize(quiz);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize(readYaml quiz) {
		frmMultipleChoiceQuestion = new JDialog(null, "", Dialog.ModalityType.APPLICATION_MODAL);
		frmMultipleChoiceQuestion.setTitle(quiz.getTitle());
		frmMultipleChoiceQuestion.setLocationRelativeTo(null);
		frmMultipleChoiceQuestion.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		GridBagLayout gridBagLayout = new GridBagLayout();
		frmMultipleChoiceQuestion.getContentPane().setLayout(gridBagLayout);
		
		// This is the question input
		// FIXME text doesn't wrap right
		JLabel label = new JLabel("<html>"+quiz.getQuestion().replaceAll("(\r\n|\n)", "<br />")+"</html>");
		GridBagConstraints gbc_label = new GridBagConstraints();
		gbc_label.gridx = 0;
		gbc_label.gridy = 0;
		frmMultipleChoiceQuestion.getContentPane().add(label, gbc_label);

		// These are the answers
		int i=0;
		for (String s : quiz.getAnswers()) {
			rdbtnAnswers.add(new JRadioButton(s));
			rdbtnAnswers.get(i).setActionCommand(String.valueOf(i));
			buttonGroup.add(rdbtnAnswers.get(i));
			gbc_rdbtnAnswers.add(new GridBagConstraints());
			gbc_rdbtnAnswers.get(i).anchor = GridBagConstraints.WEST;
			gbc_rdbtnAnswers.get(i).gridx = 0;
			gbc_rdbtnAnswers.get(i).gridy = i+1;
			frmMultipleChoiceQuestion.getContentPane().add(rdbtnAnswers.get(i), gbc_rdbtnAnswers.get(i));
			i++;
		}
		i++;

		// Confirm button
		JButton btnConfirm = new JButton("Confirm");
		btnConfirm.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (buttonGroup.getSelection() == null) {
					JOptionPane.showMessageDialog(null, "You must select an answer before continuing, try again!");
				} else {
					//JOptionPane.showMessageDialog(null, "You selected button " + getSelectedButton());
					frmMultipleChoiceQuestion.dispose();
				}
			}
		});
		GridBagConstraints gbc_btnConfirm = new GridBagConstraints();
		gbc_btnConfirm.gridx = 0;
		gbc_btnConfirm.gridy = i;
		frmMultipleChoiceQuestion.getContentPane().add(btnConfirm, gbc_btnConfirm);
		
		// Hint button
		JButton btnHint = new JButton("Hint");
		btnHint.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null, "This is a hint");
			}
		});
		GridBagConstraints gbc_btnHint = new GridBagConstraints();
		gbc_btnHint.gridx = 1;
		gbc_btnHint.gridy = i;
		frmMultipleChoiceQuestion.getContentPane().add(btnHint, gbc_btnHint);
	}

	// Get selected button
	private static String getSelectedButton() {
		if (buttonGroup.getSelection() == null) return null;
		return buttonGroup.getSelection().getActionCommand();
	}
}
