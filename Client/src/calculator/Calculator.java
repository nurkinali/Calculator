package calculator;

import java.awt.*;
import javax.swing.*;
import org.slf4j.Logger;
import java.awt.event.*;
import java.util.Arrays;
import java.math.BigDecimal;
import org.slf4j.LoggerFactory;
import java.text.MessageFormat;
import org.apache.commons.lang3.StringUtils;

@SuppressWarnings("serial")
public class Calculator extends JFrame implements ActionListener {

	private static final String _0_1_2 = "{0}{1}{2}";
	public JButton bp;
	public JButton bc;
	public JButton bac;

	public static JFrame frame;
	public static JPanel panel;
	public static JTextField text;

	public static String s1 = "";
	public static String s2 = "";
	public 	static String s3 = "";
	public 	static boolean s4 = false;
	
	public static String[] operations = {"+", "-", "*", "/", "%"};
	public static String[] numbers = {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9"};
	
	public static Logger logger = LoggerFactory.getLogger(Calculator.class);
					
	public static void main(String[] args) {
		
		Calculator c = new Calculator();
		
		frame = new JFrame("Calculator");
		
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		}
		catch(Exception e) {
		    logger.info(e.getMessage());		
		 }

		Container contentPane = frame.getContentPane();
		
		KeyListener listener = new KeyListener() {
			@Override
			public void keyPressed(KeyEvent e) {
				eventInfo("Key Pressed", e);
				
				

				if(s4) {
					if(StringUtils.isEmpty(s2) && StringUtils.isEmpty(s3)) {
						if(!s1.contains(".")){
							if(isNumeric(String.valueOf(e.getKeyChar())) || e.getKeyChar()  == '.') {
								s1 = s1 + e.getKeyChar();
								text.setText(MessageFormat.format(_0_1_2, s1, s2, s3));
							}
						}
						else {
							if(isNumeric(String.valueOf(e.getKeyChar()))) {
								s4 = false;
								s1 = "";
								s1 = s1 + e.getKeyChar();
								text.setText(MessageFormat.format(_0_1_2, s1, s2, s3));
							}
						}
					}
				}
								
				else {
					if(StringUtils.isEmpty(s2) && StringUtils.isEmpty(s3)) {
						if(!s1.contains(".")){
							if(isNumeric(String.valueOf(e.getKeyChar())) || e.getKeyChar() == '.') {
								s1 = s1 + e.getKeyChar();
								text.setText(MessageFormat.format(_0_1_2, s1, s2, s3));
							}
						}
						else {
							if(isNumeric(String.valueOf(e.getKeyChar()))) {
								s1 = s1 + e.getKeyChar();
								text.setText(MessageFormat.format(_0_1_2, s1, s2, s3));
							}
						}
					}
				}
				
				/*
				 * Used for defining s2 only if s1 is already defined and the seizure is an operation.
				 */
				if(!StringUtils.isEmpty(s1) && StringUtils.isEmpty(s2) && Arrays.asList(operations).contains(String.valueOf(e.getKeyChar()))){
					s2 = s2 + e.getKeyChar();
					text.setText(MessageFormat.format(_0_1_2, s1, s2, s3));
				}
				if(!StringUtils.isEmpty(s1) && !StringUtils.isEmpty(s2)){
					if((StringUtils.equals(s2, "+") && e.getKeyChar() == '-') || (StringUtils.equals(s2, "-") && e.getKeyChar() == '+')){
						s2 = s2 + e.getKeyChar();
						text.setText(MessageFormat.format(_0_1_2, s1, s2, s3));
					}
				}		
				
				/*
				 * Used for defining s3 only if s1 and s3 are already defined and the seizure is an operation.
				 */
				if(!s3.contains(".") && !StringUtils.isEmpty(s1) && !StringUtils.isEmpty(s2) && ((isNumeric(String.valueOf(e.getKeyChar()))) || e.getKeyChar() == '.')) {
					s3 = s3 + e.getKeyChar();
					text.setText(MessageFormat.format(_0_1_2, s1, s2, s3));
				}
				if(s3.contains(".") && !StringUtils.isEmpty(s1) && !StringUtils.isEmpty(s2) && isNumeric(String.valueOf(e.getKeyChar()))) {
					s3 = s3 + e.getKeyChar();
					text.setText(MessageFormat.format(_0_1_2, s1, s2, s3));
				}

				if(e.getKeyCode() == 8) {
					if(!StringUtils.isEmpty(s1) && !StringUtils.isEmpty(s2) && StringUtils.isEmpty(s3)) {
						s2 = s2.substring(0, s2.length() - 1);
						text.setText(MessageFormat.format(_0_1_2, s1, s2, s3));
					}
					else if(!(text.getText().contains("+")) && !(text.getText().contains("-")) && !(text.getText().contains("*")) && !(text.getText().contains("/"))){
						if(s1.length() > 0) {
							s1 = s1.substring(0, s1.length() - 1);
							text.setText(s1);
						}
					}
					else {
						s3 = s3.substring(0, s3.length() - 1);
						text.setText(MessageFormat.format(_0_1_2, s1, s2, s3));
					}
				}
				
			if(e.getKeyChar() == KeyEvent.VK_ENTER) {
				switch(s2) {
				case "/":
					text.setText((division(s1, s3)));
					recalculate();
					break;
				case "+":
					text.setText(addition(s1, s3));
					recalculate();
					break;
				case "*":
					text.setText(multiplication(s1, s3));
					recalculate();
					break;
				case "":
					text.setText(s1);
					break;
				case "%":
					text.setText(mod(s1, s3));
					recalculate();
					break;
				default:
					text.setText(substraction(s1, s3));
					recalculate();
				}
			}
			
			
			}
					
			public void keyReleased(KeyEvent e) {
				eventInfo("Key Released", e);
			}

			public void keyTyped(KeyEvent e) {
				eventInfo("Key Typed", e);
			}
			
			private int eventInfo(String str, KeyEvent e) {
				return e.getKeyCode();
			}
		};
		
		panel = new JPanel();
		
		text = new JTextField(20);
		text.addKeyListener(listener);
		contentPane.add(text, BorderLayout.NORTH);
		text.setEditable(false);
		panel.add(text);
		
		JButton button;
		JButton be; 
		JButton bp; 
		JButton bc;
		JButton bac;
		
		for(int i = 0; i < numbers.length; i++) {
			button = new JButton(numbers[i]);
			button.addActionListener(c);
			panel.add(button);
		}
		for(int i = 0; i < operations.length; i++) {
			button = new JButton(operations[i]);
			button.addActionListener(c);
			panel.add(button);
		}
		
		be = new JButton("=");
		be.addActionListener(c);
		panel.add(be);
		
		bp = new JButton(".");
		bp.addActionListener(c);
		panel.add(bp);
		
		bc = new JButton("C");
		bc.addActionListener(c);
		panel.add(bc);
		
		bac = new JButton("AC");
		bac.addActionListener(c);
		panel.add(bac);
		
		panel.setBackground(Color.white);
        frame.add(panel);
        frame.setSize(500, 500);
        frame.setVisible(true);
	}
	
	@SuppressWarnings("unlikely-arg-type")
	@Override
	public void actionPerformed(ActionEvent e) {
		String calcul = e.getActionCommand();	
		
		/*
		 * The boolean value s4 is used for defining whether the user takes
		 * the result of the recent calculation as s1 or defines a new one.
		 */
		/* 
		 * To define s1, verify if s4 is true or false && if s2 and s3 are "empty".
		 * Using the "point" button more than once isn't acceptable.
		 */
		if(s4) {
			if(StringUtils.isEmpty(s2) && StringUtils.isEmpty(s3)) {
				if(!s1.contains(".")){
					if(isNumeric(calcul) || calcul.charAt(0) == '.') {
						s1 = s1 + calcul.charAt(0);
						text.setText(MessageFormat.format(_0_1_2, s1, s2, s3));
					}
				}
				else {
					if(isNumeric(calcul)) {
						s4 = false;
						s1 = "";
						s1 = s1 + calcul.charAt(0);
						text.setText(MessageFormat.format(_0_1_2, s1, s2, s3));
					}
				}
			}
		}
		else {
			if(StringUtils.isEmpty(s2) && StringUtils.isEmpty(s3)) {
				if(!s1.contains(".")){
					if(isNumeric(calcul) || calcul.charAt(0) == '.') {
						s1 = s1 + calcul.charAt(0);
						text.setText(MessageFormat.format(_0_1_2, s1, s2, s3));
					}
				}
				else {
					if(isNumeric(calcul)) {
						s1 = s1 + calcul.charAt(0);
						text.setText(MessageFormat.format(_0_1_2, s1, s2, s3));
					}
				}
			}
		}
		
		/*
		 * Used for defining s2 only if s1 is already defined and the seizure is an operation.
		 */
		if(!StringUtils.isEmpty(s1) && StringUtils.isEmpty(s2) && Arrays.asList(operations).contains(String.valueOf(calcul.charAt(0)))){
			s2 = s2 + calcul.charAt(0);
			text.setText(MessageFormat.format(_0_1_2, s1, s2, s3));
		}
		if(!StringUtils.isEmpty(s1) && !StringUtils.isEmpty(s2)){
			if((StringUtils.equals(s2, "+") && calcul.charAt(0) == '-') || (StringUtils.equals(s2, "-") && calcul.charAt(0) == '+')){
				s2 = s2 + calcul.charAt(0);
				text.setText(MessageFormat.format(_0_1_2, s1, s2, s3));
			}
		}		
		
		/*
		 * Used for defining s3 only if s1 and s3 are already defined and the seizure is an operation.
		 */
		if(!s3.contains(".") && !StringUtils.isEmpty(s1) && !StringUtils.isEmpty(s2) && ((isNumeric(calcul)) || calcul.charAt(0) == '.')) {
			s3 = s3 + calcul.charAt(0);
			text.setText(MessageFormat.format(_0_1_2, s1, s2, s3));
		}
		if(s3.contains(".") && !StringUtils.isEmpty(s1) && !StringUtils.isEmpty(s2) && isNumeric(calcul)) {
			s3 = s3 + calcul.charAt(0);
			text.setText(MessageFormat.format(_0_1_2, s1, s2, s3));
		}
		
		// The result depending on the operator
		if(calcul.charAt(0) == '=') {
			switch(s2) {
			case "/":
				text.setText((division(s1, s3)));
				recalculate();
				break;
			case "+":
				text.setText(addition(s1, s3));
				recalculate();
				break;
			case "*":
				text.setText(multiplication(s1, s3));
				recalculate();
				break;
			case "":
				text.setText(s1);
				break;
			default:
				text.setText(substraction(s1, s3));
				recalculate();
			}
		}
		
		// To remove the last character
		if(calcul.charAt(0) == 'C') {
			if(!StringUtils.isEmpty(s1) && !StringUtils.isEmpty(s2) && StringUtils.isEmpty(s3)) {
				s2 = s2.substring(0, s2.length() - 1);
				text.setText(MessageFormat.format(_0_1_2, s1, s2, s3));
			}
			else if(!(text.getText().contains("+")) && !(text.getText().contains("-")) && !(text.getText().contains("*")) && !(text.getText().contains("/"))){
				if(s1.length()>0) {
					s1 = s1.substring(0, s1.length() - 1);
					text.setText(s1);
				}
			}
			else {
				s3 = s3.substring(0, s3.length() - 1);
				text.setText(MessageFormat.format(_0_1_2, s1, s2, s3));
			}
		}
		
		// To remove the text field
		if(calcul.charAt(0) == 'A' && StringUtils.equals(text.getText(), "NULL")) {
			s1 = s2 = s3 = "";
			s4 = false;
			text.setText(MessageFormat.format(_0_1_2, s1, s2, s3));
			}
		}
	
	
	public static boolean isNumeric(String str)
	 {
		try
	    {
	      double d = Double.parseDouble(str);
	    }
	    catch(NumberFormatException nfe)
	    {
	      return false;
	    }
	    return true;
	    }
	  
	public static String division(String x, String y) {
		return JerseyClient.calculate(JerseyClient.getWebTarget(), JerseyClient.DIVISION_VALUES, new BigDecimal(x), new BigDecimal(y));
	}
	public static String multiplication(String x, String y) {
		return JerseyClient.calculate(JerseyClient.getWebTarget(), JerseyClient.MULTIPLICATION_VALUES, new BigDecimal(x), new BigDecimal(y));
	}
	public static String mod(String x, String y) {
		try{
			return String.valueOf((Double.valueOf(x) % Double.valueOf(y)));
		}
		catch(NumberFormatException nfe) {
			return "mod";
		}
	}
	public static String addition(String x, String y) {
		return JerseyClient.calculate(JerseyClient.getWebTarget(), JerseyClient.ADDITION_VALUES, new BigDecimal(x), new BigDecimal(y));
	  }
	public static String substraction(String x, String y) {
		return JerseyClient.calculate(JerseyClient.getWebTarget(), JerseyClient.SUBSTRACTION_VALUES, new BigDecimal(x), new BigDecimal(y));
	  }
	
	public static void recalculate() {
		if(isNumeric(text.getText())) {
			s1 = text.getText();
		}
		else {
			s1 = "";
		}
		s2 = "";
		s3 = "";
		s4 = true;
		}
}