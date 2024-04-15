package lvov.course2.temperature.view;

import lvov.course2.temperature.ConvertType;
import lvov.course2.temperature.controller.Controller;

import javax.swing.*;
import java.awt.*;

public class DesktopView implements View {
    private Controller controller;
    private JLabel resultLabel;
    private ConvertType convertType;

    @Override
    public void start() {
        SwingUtilities.invokeLater(() -> {
            try {
                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            } catch (Exception ignored) {
            }

            JFrame frame = new JFrame("Конвертер температур");

            frame.setSize(330, 200);
            frame.setLocationRelativeTo(null);
            frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

            Image icon = Toolkit.getDefaultToolkit().getImage("Temperature\\src\\lvov\\course2\\temperature\\icon.png");
            frame.setIconImage(icon);

            JLabel temperatureLabel = new JLabel("Введите температуру:");
            JComboBox<ConvertType> dropDown = getConvertTypeJComboBox();
            JTextField temperatureField = new JTextField(25);

            JButton convertButton = getjButton(temperatureField, frame);

            JPanel upperPanel = new JPanel();
            JPanel centerPanel = new JPanel();
            JPanel downPanel = new JPanel();
            resultLabel = new JLabel();

            upperPanel.add(dropDown, BorderLayout.LINE_START);
            centerPanel.add(temperatureLabel);
            centerPanel.add(temperatureField);
            centerPanel.add(convertButton);
            downPanel.add(resultLabel);

            frame.add(upperPanel, BorderLayout.PAGE_START);
            frame.add(centerPanel, BorderLayout.CENTER);
            frame.add(downPanel, BorderLayout.PAGE_END);
            frame.setVisible(true);
        });
    }

    private JComboBox<ConvertType> getConvertTypeJComboBox() {
        JComboBox<ConvertType> dropDown = new JComboBox<>(ConvertType.values());
        dropDown.addActionListener(e -> convertType = (ConvertType) dropDown.getSelectedItem());
        dropDown.setSelectedIndex(0);

        return dropDown;
    }

    private JButton getjButton(JTextField temperatureField, JFrame frame) {
        JButton convertButton = new JButton("Конвертировать");
        convertButton.addActionListener(e -> {
            try {
                double temperature = Double.parseDouble(temperatureField.getText());
                controller.convertTemperature(convertType, temperature);
            } catch (NumberFormatException exception) {
                JOptionPane.showMessageDialog(frame, "Температура должна быть числом", "Ошибка ввода",
                        JOptionPane.ERROR_MESSAGE);
            }
        });

        return convertButton;
    }

    @Override
    public void setController(Controller controller) {
        this.controller = controller;
    }

    @Override
    public void showResult(double temperature) {
        resultLabel.setText("Температура = " + String.format("%.3f", temperature));
    }
}
