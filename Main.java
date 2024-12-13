import javax.swing.*;
import java.awt.*;

class Main{
    private static String value1 = "";
    private static String value2 = "";
    private static long result;
    private static String operation = "";
    private static boolean isNumber = false;
    private static boolean isOperator = false;

    private static void count(){
        switch (operation) {
            case "+":
                result = Long.parseLong(value1) + Long.parseLong(value2);
                break;
            case "-":
                result = Long.parseLong(value1) - Long.parseLong(value2);
                break;
            case "x":
                result = Long.parseLong(value1) * Long.parseLong(value2);
                break;
            case ":":
                result = Long.parseLong(value1) / Long.parseLong(value2);
                break;

        }
    }

    private static void eq(JLabel lab){
        count();
        lab.setText(!lab.getText().isEmpty()? Long.toString(result) : "0");
        isOperator = true;
    }

    private static void operatorhandler(String text,JLabel lab){

        if (isNumber) {
            if (!isOperator) {
                lab.setText(lab.getText() + " " + text + " ");
                isOperator = true;
                operation = text;
            }else {
                count();

                value1 = Long.toString(result);
                lab.setText(Long.toString(result) + " " + text + " ");
                operation = text;

                isOperator = true;
                value2 = "";
            }
            isNumber = text.equals("=");

        }
    }

    private static void numberhandler(String text,JLabel lab){
        if (!isOperator){
            value1+=text;
        }else{
            value2+=text;
        }
        lab.setText(lab.getText() + text);
        isNumber = true;
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

        JPanel ui = new JPanel();
        ui.setMaximumSize(new Dimension(280,100));
        ui.setMinimumSize(new Dimension(280,100));
        ui.setPreferredSize(new Dimension(280,100));
        ui.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));

        JLabel label = new JLabel(" ");
        label.setSize(280,100);
        ui.add(label);

        JPanel component = new JPanel();
        component.setMaximumSize(new Dimension(280,350));
        component.setMinimumSize(new Dimension(280,350));
        component.setPreferredSize(new Dimension(280,350));
        component.setLayout(new BoxLayout(component,BoxLayout.X_AXIS));

        JPanel div1 = new JPanel();
        div1.setLayout(new GridBagLayout());

        component.add(div1);

        //make the component

        JButton[] button = new JButton[10];
        JButton[] operator = new JButton[5];
        {
            GridBagConstraints gbc = new GridBagConstraints();
            byte index = 0;
            for (byte i=0;i<3;i++){
                for (byte j = 0;j<3;j++) {
                    final byte constant = (byte)(index+1);
                    button[index] = new JButton(Integer.toString(index + 1));
                    button[index].setPreferredSize(new Dimension(45,40));
                    button[index].addActionListener(event -> numberhandler(Byte.toString(constant),label));
                    gbc.gridx = j;
                    gbc.gridy = i;
                    gbc.anchor = GridBagConstraints.CENTER;
                    gbc.weightx = 1;
                    gbc.weighty = 1;

                    div1.add(button[index], gbc);
                    index++;
                }
            }
            button[9] = new JButton("0");
            button[9].setPreferredSize(new Dimension(45,40));
            button[9].addActionListener(event -> numberhandler("0",label));
            gbc.gridx = 1;
            gbc.gridy = 3;
            gbc.anchor =  GridBagConstraints.CENTER;
            gbc.weighty = 1;
            gbc.weightx = 1;
            div1.add(button[9],gbc);


            char[] x = {'+','-','x',':','='};
            for (byte i = 0; i < operator.length; i++) {
                final byte j = i;
                operator[i] = new JButton(Character.toString(x[i]));
                operator[i].setPreferredSize(new Dimension(45,40));
                if (x[j] != '=') {
                    operator[i].addActionListener(event -> operatorhandler(Character.toString(x[j]), label));
                }else{
                    operator[i].addActionListener(event -> eq(label));
                }

                gbc.gridx = 3;
                gbc.gridy = (int)i;
                gbc.anchor = GridBagConstraints.CENTER;
                gbc.weighty = 1;
                gbc.weightx = 1;

                div1.add(operator[i],gbc);
            }
        }

//        window.getContentPane().setFocusable(true);
//        window.addKeyListener(new KeyListener() {
//            @Override
//            public void keyTyped(KeyEvent e) {
//                System.out.println("Key Typed: " + e.getKeyChar());
//            }
//
//            @Override
//            public void keyPressed(KeyEvent e) {
//                System.out.println("Key Typed: " + e.getKeyCode());
//            }
//
//            @Override
//            public void keyReleased(KeyEvent e) {
//                System.out.println("Key Typed: " + e.getKeyCode());
//            }
//        });

        window.add(Box.createVerticalStrut(5));
        window.add(ui);
        window.add(Box.createVerticalStrut(5));
        window.add(component);

    }
}