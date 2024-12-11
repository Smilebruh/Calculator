import javax.swing.*;
import java.awt.*;
import java.io.File;

class Main{
    private void buttonhandler(){
        //
    }

    public static void main(String[] args){
        JFrame window = new JFrame();
        window.setTitle("Calculator");
        window.setSize(300,500);
        window.setResizable(false);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setLocationRelativeTo(null);
        window.setLayout(new BoxLayout(window.getContentPane(),BoxLayout.Y_AXIS));
        window.setIconImage((new ImageIcon(System.getProperty("user.dir")+"/"+"calculator.png").getImage()));
        window.setVisible(true);

        window.getContentPane().setBackground(Color.GREEN);

        JPanel ui = new JPanel();
        ui.setBackground(Color.RED);
        ui.setMaximumSize(new Dimension(280,100));
        ui.setMinimumSize(new Dimension(280,100));
        ui.setPreferredSize(new Dimension(280,100));
        ui.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));

        JPanel component = new JPanel();
        component.setMaximumSize(new Dimension(280,350));
        component.setMinimumSize(new Dimension(280,350));
        component.setPreferredSize(new Dimension(280,350));
        component.setBackground(Color.BLUE);
        component.setLayout(new BoxLayout(component,BoxLayout.X_AXIS));

        JPanel div1 = new JPanel();
        div1.setBackground(Color.BLACK);

        JPanel div2 = new JPanel();
        div2.setBackground(Color.WHITE);
        component.add(div1);
        component.add(div2);


        GridBagConstraints gbc = new GridBagConstraints();

        //make the component

//        JButton[] button = new JButton[10];
//        {
//            byte index = 0;
//            for (byte i=0;i<3;i++){
//                for (byte j = 0;j<3;j++) {
//                    button[index] = new JButton(Integer.toString(index + 1));
//                    gbc.gridx = j;
//                    gbc.gridy = i;
//                    gbc.anchor = GridBagConstraints.NORTHWEST;
//                    gbc.weightx = 0;
//                    gbc.weighty = 0;
//
//                    component.add(button[index], gbc);
//                    index++;
//                }
//            }
//            button[9] = new JButton("0");
//            gbc.gridx = 1;
//            gbc.gridy = 3;
//            component.add(button[9],gbc);
//        }

        window.add(Box.createVerticalStrut(5));
        window.add(ui);
        window.add(Box.createVerticalStrut(5));
        window.add(component);

    }
}