import javax.swing.*;
import java.awt.*;

class Main{
    public static void main(String[] args){
        JFrame window = new JFrame();
        window.setTitle("Calculator");
        window.setSize(300,500);
        window.setResizable(false);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setLocationRelativeTo(null);
        window.setLayout(new BoxLayout(window.getContentPane(),BoxLayout.Y_AXIS));
        window.setIconImage((new ImageIcon("C:/Users/DIMAS/my_programs/java/Calculator/calculator.png").getImage()));
        window.setVisible(true);

        window.getContentPane().setBackground(Color.GREEN);

        JPanel ui = new JPanel();
        ui.setBackground(Color.RED);
        ui.setMaximumSize(new Dimension(280,100));
        ui.setMinimumSize(new Dimension(280,100));
        ui.setPreferredSize(new Dimension(280,100));
        ui.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));

        JPanel button = new JPanel();
        button.setMaximumSize(new Dimension(280,350));
        button.setMinimumSize(new Dimension(280,350));
        button.setPreferredSize(new Dimension(280,350));
        button.setBackground(Color.BLUE);
        button.setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();

        //make the component

        window.add(Box.createVerticalStrut(5));
        window.add(ui);
        window.add(Box.createVerticalStrut(5));
        window.add(button);

    }
}