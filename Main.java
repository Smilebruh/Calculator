import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.Math;

class Main{
    private static String value1 = "";
    private static String value2 = "";
    private static String result = "";
    private static String operation = "";
    private static boolean isNumber = false;
    private static boolean isOperator = false;
    private static boolean isPow = false;
    private static boolean isDot = false;

    private static void count(){
        double val1 = Double.parseDouble(value1);
        double val2 = Double.parseDouble(value2);
        switch (operation) {

            case "+":
                result = val1 + val2 == (long)(val1 + val2)? Long.toString((long)(val1 + val2)) : Double.toString(val1 + val2) ;
                break;
            case "-":
                result = val1 - val2 == (long)(val1 - val2)? Long.toString((long)(val1 - val2)) : Double.toString(val1 - val2) ;
                break;
            case "x":
                result = val1 * val2 == (long)(val1 * val2)? Long.toString((long)(val1 * val2)) : Double.toString(val1 * val2) ;
                break;
            case ":":
                result = val1 / val2 == (long)(val1 / val2)? Long.toString((long)(val1 / val2)) : Double.toString(val1 / val2) ;
                break;

        }
    }

    private static void dothandler(JLabel lab) {
        if (!(isNumber && isDot) ) {
            if (!isOperator) {
                value1 += ".";
            } else {
                value2 += ".";
            }
            lab.setText(lab.getText() + ".");
            isDot = true;
        }
    }

    private static void eq(JLabel lab) {
        if (isOperator) {
            count();
            lab.setText(!lab.getText().isEmpty() ? result : "0");
        }
    }

    private static void operatorhandler(String text,JLabel lab){

        if (isNumber && !isPow ) {
            if (!isOperator) {
                lab.setText(lab.getText() + " " + text + " ");
                isOperator = true;
                operation = text;
            }else {
                count();

                value1 = result;
                lab.setText(result + " " + text + " ");
                operation = text;

                isOperator = true;
                value2 = "";
            }
            isNumber = text.equals("=");
            isDot = false;
        }
    }

    private static void numberhandler(String text,JLabel lab) {

            if (!isOperator) {
                value1 += text;
            } else {
                value2 += text;
            }
            lab.setText(lab.getText() + text);
            isNumber = true;



    }

    public static void main(String[] args){
        JFrame window = new JFrame();
        window.setTitle("Calculator");
        window.setSize(400,500);
        window.setResizable(false);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setLocationRelativeTo(null);
        window.setLayout(new BoxLayout(window.getContentPane(),BoxLayout.Y_AXIS));
        window.setIconImage((new ImageIcon(System.getProperty("user.dir")+"/"+"calculator.png").getImage()));
        window.setVisible(true);

        JPanel ui = new JPanel();
        ui.setMaximumSize(new Dimension(380,100));
        ui.setMinimumSize(new Dimension(380,100));
        ui.setPreferredSize(new Dimension(380,100));
        ui.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));

        JLabel label = new JLabel(" ");
        label.setSize(380,100);
        label.setFont(new Font("Arial",Font.PLAIN,16));
        ui.add(label);

        JPanel component = new JPanel();
        component.setMaximumSize(new Dimension(380,350));
        component.setMinimumSize(new Dimension(380,350));
        component.setPreferredSize(new Dimension(380,350));
        component.setLayout(new BoxLayout(component,BoxLayout.X_AXIS));

        JPanel div1 = new JPanel();
        div1.setLayout(new GridBagLayout());

        component.add(div1);

        //make the component


        JPanel span = new JPanel();
        JButton[] button = new JButton[10];
        JButton[] operator = new JButton[5];
        JButton e = new JButton("e");
        JButton pi = new JButton("π");
        JButton C = new JButton("C");
        JButton dot = new JButton(".");
        JButton pow = new JButton("^");
        {
            GridBagConstraints gbc = new GridBagConstraints();
            byte index = 0;

            //number button
            for (byte i=0;i<3;i++){
                for (byte j = 0;j<3;j++) {
                    final byte constant = (byte)(index+1);
                    button[index] = new JButton(Integer.toString(index + 1));
                    button[index].setPreferredSize(new Dimension(43,43));
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
            button[9].setPreferredSize(new Dimension(43,43));
            button[9].addActionListener(event -> numberhandler("0",label));
            gbc.gridx = 1;
            gbc.gridy = 3;
            gbc.anchor =  GridBagConstraints.CENTER;
            gbc.weighty = 1;
            gbc.weightx = 1;
            div1.add(button[9],gbc);

            //π and e button
            gbc.gridx = 2;
            gbc.gridy = 3;
            e.setPreferredSize(new Dimension(43,43));
            e.addActionListener(event -> numberhandler(Double.toString(Math.exp(1.0)),label));
            div1.add(e,gbc);

            gbc.gridx = 0;
            gbc.gridy = 3;
            pi.setPreferredSize(new Dimension(43,43));
            pi.addActionListener(event -> numberhandler(Double.toString(Math.PI),label));
            div1.add(pi,gbc);


            //clear button
            C.setPreferredSize(new Dimension(43,43));
            C.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    value1 = "";
                    value2 = "";
                    result = "";
                    operation = "";
                    isNumber = false;
                    isOperator = false;
                    isPow = false;
                    isDot = false;
                    label.setText(" ");
                }
            });
            gbc.gridx = 5;
            gbc.gridy = 0;
            div1.add(C,gbc);


            //dot button
            dot.setPreferredSize(new Dimension(43, 43));
            dot.addActionListener(event -> dothandler(label));
            gbc.gridx = 5;
            gbc.gridy = 1;
            div1.add(dot,gbc);


            //span
            gbc.gridx = 3;
            gbc.gridy = 0;
            div1.add(span,gbc);

            //power button
            pow.setPreferredSize(new Dimension(43,43));
            pow.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if(!isPow) {
                        label.setText(label.getText() + "^(");
                        isOperator = true;
                        isPow = true;

                    }else{
                        double firstpow = Math.pow(Double.parseDouble(value1),Double.parseDouble(value2));
                        label.setText(label.getText()+")");
                        result = firstpow == (long)firstpow ? Long.toString((long)firstpow) : Double.toString(firstpow);
                        isPow = false;
                    }

                }
            });
            gbc.gridx = 5;
            gbc.gridy = 2;
            div1.add(pow,gbc);


            char[] x = {'+','-','x',':','='};
            for (byte i = 0; i < operator.length; i++) {
                final byte j = i;
                operator[i] = new JButton(Character.toString(x[i]));
                operator[i].setPreferredSize(new Dimension(43,43));
                if (x[j] != '=') {
                    operator[i].addActionListener(event -> operatorhandler(Character.toString(x[j]), label));
                    gbc.gridx = 4;
                    gbc.gridy = (int)i;
                }else{
                    operator[i].addActionListener(event -> eq(label));
                    gbc.gridx = 5;
                    gbc.gridy = 3;
                }

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