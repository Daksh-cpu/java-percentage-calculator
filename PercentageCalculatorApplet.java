import java.awt.Button;
import java.awt.Color;
import java.awt.Font;
import java.awt.Label;
import java.awt.Panel;
import java.awt.TextArea;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/*
 <applet code="PercentageCalculatorApplet.class" width="550" height="700">
 </applet>
*/

public class PercentageCalculatorApplet extends Panel implements ActionListener {

    // AWT Components
    private Label headingLabel;
    private Label subjectsLabel;
    private Label maxMarksLabel;
    private Label namesLabel;
    private Label obtainedMarksLabel;

    private TextField subjectsField;
    private TextField maxMarksField;
    private TextField namesField;
    private TextField obtainedMarksField;

    private Button calculateButton;
    private TextArea resultArea;

    public PercentageCalculatorApplet() {
        initUI();
    }

    /**
     * Initializes the UI and sets up the strict layout rules
     */
    private void initUI() {
        setLayout(null);
        setBackground(new Color(230, 240, 255)); // Soft Pastel Blue Background

        headingLabel = new Label("Percentage Calculator", Label.CENTER);
        headingLabel.setFont(new Font("Arial", Font.BOLD, 24));
        headingLabel.setForeground(new Color(20, 50, 90));
        headingLabel.setBounds(0, 10, 550, 40);
        add(headingLabel);

        Font standardFont = new Font("Arial", Font.PLAIN, 14);
        Color darkText = new Color(30, 30, 30);

        subjectsLabel = new Label("Number of Subjects:");
        subjectsLabel.setFont(standardFont);
        subjectsLabel.setForeground(darkText);
        subjectsLabel.setBounds(50, 70, 200, 30);
        add(subjectsLabel);

        subjectsField = new TextField();
        subjectsField.setFont(standardFont);
        subjectsField.setBounds(290, 73, 210, 25);
        add(subjectsField);

        maxMarksLabel = new Label("Max Marks Per Subject:");
        maxMarksLabel.setFont(standardFont);
        maxMarksLabel.setForeground(darkText);
        maxMarksLabel.setBounds(50, 110, 200, 30);
        add(maxMarksLabel);

        maxMarksField = new TextField();
        maxMarksField.setFont(standardFont);
        maxMarksField.setBounds(290, 113, 210, 25);
        add(maxMarksField);

        namesLabel = new Label("Subject Names (comma separated):");
        namesLabel.setFont(standardFont);
        namesLabel.setForeground(darkText);
        namesLabel.setBounds(50, 150, 230, 30);
        add(namesLabel);

        namesField = new TextField();
        namesField.setFont(standardFont);
        namesField.setBounds(290, 153, 210, 25);
        add(namesField);

        obtainedMarksLabel = new Label("Obtained Marks (comma separated):");
        obtainedMarksLabel.setFont(standardFont);
        obtainedMarksLabel.setForeground(darkText);
        obtainedMarksLabel.setBounds(50, 190, 230, 30);
        add(obtainedMarksLabel);

        obtainedMarksField = new TextField();
        obtainedMarksField.setFont(standardFont);
        obtainedMarksField.setBounds(290, 193, 210, 25);
        add(obtainedMarksField);

        calculateButton = new Button("Calculate Result");
        calculateButton.setFont(new Font("Arial", Font.BOLD, 16));
        calculateButton.setBackground(new Color(232, 245, 233));
        calculateButton.setForeground(new Color(20, 20, 20));
        calculateButton.setBounds(150, 236, 250, 46);
        calculateButton.addActionListener(this);
        add(calculateButton);

        resultArea = new TextArea();
        resultArea.setBackground(new Color(245, 245, 245));
        resultArea.setFont(new Font("Monospaced", Font.BOLD, 18)); // Size 18 so it fits nicely
        resultArea.setEditable(false);
        resultArea.setBounds(50, 290, 450, 350);
        add(resultArea);
    }

    /**
     * Action Listener method triggered when Calculate button is clicked
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == calculateButton) {
            processCalculation();
        }
    }

    /**
     * Core Logic: Validates strings, splits arrays, and maps names to values
     */
    private void processCalculation() {
        try {
            // Retrieve string input
            String subjectsStr = subjectsField.getText().trim();
            String maxMarksStr = maxMarksField.getText().trim();
            String namesStr = namesField.getText().trim();
            String obtainedStr = obtainedMarksField.getText().trim();

            if (subjectsStr.isEmpty() || maxMarksStr.isEmpty() || namesStr.isEmpty() || obtainedStr.isEmpty()) {
                showError("Please fill all the fields.");
                return;
            }

            int numberOfSubjects = Integer.parseInt(subjectsStr);
            int maxMarks = Integer.parseInt(maxMarksStr);

            if (numberOfSubjects <= 0 || maxMarks <= 0) {
                showError("Subjects and Max Marks must be > 0");
                return;
            }

            // Split string using comma
            String[] namesArray = namesStr.split(",");
            String[] marksArray = obtainedStr.split(",");

            // Validation: Mismatch in array sizes Check
            if (namesArray.length != numberOfSubjects) {
                showError("Error: Number of subjects and names must match (" + numberOfSubjects + ")");
                return;
            }

            if (marksArray.length != numberOfSubjects) {
                showError("Error: Number of subjects and marks must match (" + numberOfSubjects + ")");
                return;
            }

            int totalObtained = 0;
            StringBuilder breakdown = new StringBuilder();

            // Store and process names and marks Arrays
            for (int i = 0; i < numberOfSubjects; i++) {
                String subjName = namesArray[i].trim();
                int mark = Integer.parseInt(marksArray[i].trim());

                if (mark < 0 || mark > maxMarks) {
                    showError("Subject '" + subjName + "' marks outside limit (0-" + maxMarks + ")");
                    return;
                }

                totalObtained += mark;

                // Constructing aligned Output segment mapping Subject name to value
                String formattedLine = String.format(" - %-12s : %d / %d\n", subjName, mark, maxMarks);
                breakdown.append(formattedLine);
            }

            int totalMaxMarks = numberOfSubjects * maxMarks;
            double percentage = ((double) totalObtained / totalMaxMarks) * 100;

            char grade;
            if (percentage >= 90) grade = 'A';
            else if (percentage >= 75) grade = 'B';
            else if (percentage >= 60) grade = 'C';
            else if (percentage >= 40) grade = 'D';
            else grade = 'F';

            String status = (percentage >= 40) ? "PASS" : "FAIL";

            displayResult(totalMaxMarks, totalObtained, percentage, grade, status, breakdown.toString());

        } catch (NumberFormatException ex) {
            showError("Invalid input: Marks must be purely numerical.");
        }
    }

    private void displayResult(int totalMax, int totalObt, double percent, char grd, String stat, String breakdwn) {
        String resultText =
            "==== RESULT ====\n" +
            "Total Max Marks : " + totalMax + "\n" +
            "Total Obtained  : " + totalObt + "\n" +
            "Percentage      : " + String.format("%.2f", percent) + "%\n" +
            "Grade           : " + grd + "\n" +
            "Status          : " + stat + "\n" +
            "=============================\n" +
            "Subject-wise Breakdown:\n" + breakdwn;

        resultArea.setForeground(Color.black);
        resultArea.setText(resultText);
    }

    private void showError(String message) {
        resultArea.setForeground(Color.red);
        resultArea.setText("\n\n" + message);
    }

    /**
     * MAIN METHOD WRAPPER
     * Runs the UI as a normal AWT desktop app on modern Java.
     */
    public static void main(String[] args) {
        java.awt.Frame frame = new java.awt.Frame("Percentage Calculator");
        PercentageCalculatorApplet app = new PercentageCalculatorApplet();

        frame.add(app);
        frame.setSize(550, 700);

        frame.setVisible(true);
        frame.setLocationRelativeTo(null);

        frame.addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent we) {
                System.exit(0);
            }
        });
    }
}
