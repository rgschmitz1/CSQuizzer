import javax.swing.JDialog;
import java.awt.Dialog;
import java.awt.GridBagLayout;
import javax.swing.JLabel;
import java.awt.GridBagConstraints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JCheckBox;
import javax.swing.JButton;
import javax.swing.JOptionPane;

public class CheckBox {

	private JDialog frame;
	private JLabel jlabel;
	private static JCheckBox[] chkbxAnswers = new JCheckBox[5];
	private JButton btnConfirm;
	private JButton btnHint;

	/**
	 * Launch the application.
	 */
	public ArrayList<Integer> CheckBoxWindow(String[] args) {
		try {
			CheckBox window = new CheckBox();
			window.frame.pack();
			window.frame.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return getSelectedBoxes();
	}

	/**
	 * Create the application.
	 */
	public CheckBox() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JDialog(null, "", Dialog.ModalityType.APPLICATION_MODAL);
		frame.setTitle("Selection Box Question");
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		GridBagLayout gridBagLayout = new GridBagLayout();
		frame.getContentPane().setLayout(gridBagLayout);

		jlabel = new JLabel("This is a Question Placeholder");
		GridBagConstraints gbc_jlabel = new GridBagConstraints();
		gbc_jlabel.gridx = 0;
		gbc_jlabel.gridy = 0;
		frame.getContentPane().add(jlabel, gbc_jlabel);

		chkbxAnswers[0] = new JCheckBox("answer1");
		chkbxAnswers[0].setActionCommand("1");
		GridBagConstraints gbc_chckbxAnswer = new GridBagConstraints();
		gbc_chckbxAnswer.anchor = GridBagConstraints.WEST;
		gbc_chckbxAnswer.gridx = 0;
		gbc_chckbxAnswer.gridy = 1;
		frame.getContentPane().add(chkbxAnswers[0], gbc_chckbxAnswer);

		chkbxAnswers[1] = new JCheckBox("answer2");
		chkbxAnswers[1].setActionCommand("2");
		GridBagConstraints gbc_chckbxAnswer_1 = new GridBagConstraints();
		gbc_chckbxAnswer_1.anchor = GridBagConstraints.WEST;
		gbc_chckbxAnswer_1.gridx = 0;
		gbc_chckbxAnswer_1.gridy = 2;
		frame.getContentPane().add(chkbxAnswers[1], gbc_chckbxAnswer_1);

		chkbxAnswers[2] = new JCheckBox("answer3");
		chkbxAnswers[2].setActionCommand("3");
		GridBagConstraints gbc_chckbxAnswer_2 = new GridBagConstraints();
		gbc_chckbxAnswer_2.anchor = GridBagConstraints.WEST;
		gbc_chckbxAnswer_2.gridx = 0;
		gbc_chckbxAnswer_2.gridy = 3;
		frame.getContentPane().add(chkbxAnswers[2], gbc_chckbxAnswer_2);

		chkbxAnswers[3] = new JCheckBox("answer4");
		chkbxAnswers[3].setActionCommand("4");
		GridBagConstraints gbc_chckbxAnswer_3 = new GridBagConstraints();
		gbc_chckbxAnswer_3.anchor = GridBagConstraints.WEST;
		gbc_chckbxAnswer_3.gridx = 0;
		gbc_chckbxAnswer_3.gridy = 4;
		frame.getContentPane().add(chkbxAnswers[3], gbc_chckbxAnswer_3);

		chkbxAnswers[4] = new JCheckBox("answer5");
		chkbxAnswers[4].setActionCommand("5");
		GridBagConstraints gbc_chckbxAnswer_4 = new GridBagConstraints();
		gbc_chckbxAnswer_4.anchor = GridBagConstraints.WEST;
		gbc_chckbxAnswer_4.gridx = 0;
		gbc_chckbxAnswer_4.gridy = 5;
		frame.getContentPane().add(chkbxAnswers[4], gbc_chckbxAnswer_4);

		btnConfirm = new JButton("Confirm");
		btnConfirm.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (getSelectedBoxes().size() == 0) {
					JOptionPane.showMessageDialog(null, "You must select an answer before continuing, try again!");
				} else {
					JOptionPane.showMessageDialog(null, "You selected boxes " + getSelectedBoxes().toString());
					frame.dispose();
				}
			}
		});
		GridBagConstraints gbc_btnConfirm = new GridBagConstraints();
		gbc_btnConfirm.gridx = 0;
		gbc_btnConfirm.gridy = 6;
		frame.getContentPane().add(btnConfirm, gbc_btnConfirm);

		btnHint = new JButton("Hint");
		btnHint.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null, "This is a hint");
			}
		});
		GridBagConstraints gbc_btnHint = new GridBagConstraints();
		gbc_btnHint.gridx = 1;
		gbc_btnHint.gridy = 6;
		frame.getContentPane().add(btnHint, gbc_btnHint);
	}

	// Get selected button
	private static ArrayList<Integer> getSelectedBoxes() {
		ArrayList<Integer> selectedBoxes = new ArrayList<Integer>();
		for (int i = 0; i < chkbxAnswers.length; i++) {
			if (chkbxAnswers[i].isSelected()) {
				selectedBoxes.add(Integer.parseInt(chkbxAnswers[i].getActionCommand()));
			}
		}
		return selectedBoxes;
	}
}
