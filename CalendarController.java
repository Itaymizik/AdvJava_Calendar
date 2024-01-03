// Itay Mizikov ; ID: 315541615 ; 20/5/23
package q2;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import java.time.LocalDate;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import javafx.stage.FileChooser;

import javax.swing.JOptionPane;

/**
 * The CalendarController class handles the interaction and logic of the
 * Calendar application. It manages the GUI components such as combo-boxes and
 * text areas, and performs operations like saving and loading schedule data.
 */
public class CalendarController {

	@FXML
	private ComboBox<Integer> cmbDay;

	@FXML
	private ComboBox<Integer> cmbMonth;

	@FXML
	private ComboBox<Integer> cmbYear;

	@FXML
	private TextArea txtSchedule;
	private Schedule schedule;

	@FXML
	private void initialize() {
		schedule = new Schedule();
		setComboBox();

		int choice = JOptionPane.showOptionDialog(null, "Load from file ?", null, JOptionPane.YES_NO_OPTION,
				JOptionPane.QUESTION_MESSAGE, null, null, cmbDay);
		if (choice == 0) {
			loadFromFile();
		}

	}

	/**
	 * Loads the schedule for the selected date and displays it in the text area.
	 *
	 * @param event the event that triggered the load operation
	 */
	@FXML
	void load(ActionEvent event) {
		Date date = getDate();
		txtSchedule.setText(schedule.get(date));

	}

	@FXML
	void loadFromFile() {
		FileChooser fileChooser = new FileChooser();
		File file = fileChooser.showOpenDialog(null);
		if (file == null) {
			return;
		}
		ObjectInputStream objectInputStream = null;
		try {
			FileInputStream fileInputStream = new FileInputStream(file);
			objectInputStream = new ObjectInputStream(fileInputStream);
			schedule = (Schedule) objectInputStream.readObject();
			JOptionPane.showMessageDialog(null, "Schedule loaded successfully");
		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Error occurred while loading the schedule");
		} finally {
			if (objectInputStream != null) {
				try {
					objectInputStream.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	/**
	 * Saves the entered schedule for the selected date.
	 *
	 * @param event the event that triggered the save operation
	 */
	@FXML
	void save(ActionEvent event) {
		Date date = getDate();
		String text = txtSchedule.getText();
		schedule.put(date, text);

	}

	/**
	 * Saves the schedule data to a file specified by the user.
	 *
	 * @param event the event that triggered the save to file operation
	 */
	@FXML
	private void saveToFile(ActionEvent event) {
		String fileName = JOptionPane.showInputDialog(null, "Choose file name:");
		String filePath = "src/q2/" + fileName;
		ObjectOutputStream objectOut = null;
		try {
			FileOutputStream fileOut = new FileOutputStream(filePath);
			objectOut = new ObjectOutputStream(fileOut);
			objectOut.writeObject(schedule);
			JOptionPane.showMessageDialog(null, "Schedule saved successfully");
		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Error occured while saving the schedule");
		} finally {
			if (objectOut != null) {
				try {
					objectOut.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	// set and initialize values of combo-boxes
	private void setComboBox() {
		for (int i = 1; i <= 31; i++) {
			cmbDay.getItems().add(i);
		}
		for (int i = 1; i <= 12; i++) {
			cmbMonth.getItems().add(i);
		}
		for (int i = 2019; i <= 2026; i++) {
			cmbYear.getItems().add(i);
		}
		LocalDate currentDate = LocalDate.now();
		cmbDay.getSelectionModel().select(new Integer(currentDate.getDayOfMonth()));
		cmbMonth.getSelectionModel().select(new Integer(currentDate.getMonthValue()));
		cmbYear.getSelectionModel().select(new Integer(currentDate.getYear()));

	}

	/**
	 * Extracts the selected date from the combo-boxes.
	 *
	 * @return the selected date as a Date object
	 */
	private Date getDate() {
		int day = cmbDay.getSelectionModel().getSelectedItem();
		int month = cmbMonth.getSelectionModel().getSelectedItem();
		int year = cmbYear.getSelectionModel().getSelectedItem();
		Date date = new Date(day, month, year);
		return date;
	}

}
