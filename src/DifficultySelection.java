import java.awt.Dialog;

import javax.swing.JDialog;
import java.awt.GridLayout;

import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class DifficultySelection {

	private JDialog frame;
	private JRadioButton rdbtnCsintermediate;
	private JRadioButton rdbtnCsnovice;
	private final static ButtonGroup buttonGroup = new ButtonGroup();

	/**
	 * Launch the application.
	 */
	public int DifficultySelectionWindow() {
		try {
			DifficultySelection window = new DifficultySelection();
			window.frame.pack();
			window.frame.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return getSelectedButton();
	}

	/**
	 * Create the application.
	 */
	public DifficultySelection() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JDialog(null, "", Dialog.ModalityType.APPLICATION_MODAL);
		frame.setTitle("Select Difficulty");
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		frame.getContentPane().setLayout(new GridLayout(1, 0, 0, 0));

		rdbtnCsnovice = new JRadioButton("CS142 (Novice)");
		rdbtnCsnovice.addActionListener(new RdbtnCsnoviceActionListener());
		rdbtnCsnovice.setActionCommand("1");
		buttonGroup.add(rdbtnCsnovice);
		frame.getContentPane().add(rdbtnCsnovice);

		rdbtnCsintermediate = new JRadioButton("CS143 (Intermediate)");
		rdbtnCsintermediate.addActionListener(new RdbtnCsintermediateActionListener());
		rdbtnCsintermediate.setActionCommand("2");
		buttonGroup.add(rdbtnCsintermediate);
		frame.getContentPane().add(rdbtnCsintermediate);
	}

	private class RdbtnCsnoviceActionListener implements ActionListener {
		public void actionPerformed(ActionEvent arg0) {
			while (true) {
				int ret = JOptionPane.showConfirmDialog(null,
						"Continue with CS142 (Novice) difficulty?\n(select 'OK' to confirm, or 'Cancel' to select again)",
						"Confirm Difficulty", JOptionPane.OK_CANCEL_OPTION);
				switch (ret) {
				case JOptionPane.OK_OPTION :
					break;
				case JOptionPane.CANCEL_OPTION :
					return;
				case JOptionPane.CLOSED_OPTION :
					System.exit(0);
					break;
				}
				break;
			}
			frame.dispose();
		}
	}
	private class RdbtnCsintermediateActionListener implements ActionListener {
		public void actionPerformed(ActionEvent arg0) {
			while (true) {
				int ret = JOptionPane.showConfirmDialog(null,
						"Continue with CS143 (Intermediate) difficulty?\n(select 'OK' to confirm, or 'Cancel' to select again)",
						"Confirm Difficulty", JOptionPane.OK_CANCEL_OPTION);
				switch (ret) {
				case JOptionPane.OK_OPTION :
					break;
				case JOptionPane.CANCEL_OPTION :
					return;
				case JOptionPane.CLOSED_OPTION :
					System.exit(0);
					break;
				}
				break;
			}
			frame.dispose();
		}
	}
	// Get selected button
	private static int getSelectedButton() {
		if (buttonGroup.getSelection() == null) return -1;
		return Integer.parseInt(buttonGroup.getSelection().getActionCommand());
	}
}
