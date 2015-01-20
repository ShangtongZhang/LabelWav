import javax.swing.JFrame;
import javax.swing.JLabel;

import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;

import javax.swing.JTextField;

@SuppressWarnings("serial")
public class XMLPanel extends JFrame{
	private JTextField textSurn;
	private JTextField textFirs;
	private JTextField textGend;
	private JTextField textAge;
	private JTextField textBirt;
	private JTextField textDial;
	private JTextField textEduc;
	private JTextField textSent;
	private JTextField textPron;
	private JTextField textTag;
	private JTextField textDate;
	private JTextField textLoca;
	private JTextField textSurr;
	private JTextField textDevi;
	private JTextField textBran;
	private JTextField textType;
	private JTextField textDire;
	private JTextField textTool;
	private JTextField textSens;
	private JTextField textRang;
	private JTextField textMode;
	@SuppressWarnings("unused")
	private LabelWavUI ui;
	private JLabel lblVers;
	private JLabel lblFreq;
	private JLabel lblLowf;
	private JLabel lblHigh;
	private JLabel lblEnco;
	private JLabel lblBit;
	private JLabel lblChan;
	private JLabel lblNois;
	private JTextField textVers;
	private JTextField textLowf;
	private JTextField textEnco;
	private JTextField textChan;
	private JTextField textFreq;
	private JTextField textHigh;
	private JTextField textBit;
	private JTextField textNois;
	
	public XMLPanel(LabelWavUI ui) {
		
		this.ui = ui;
		
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 0, 328, 62, 0, 0, 0};
		gridBagLayout.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 1.0, 0.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		getContentPane().setLayout(gridBagLayout);
		
		JLabel lblSent = new JLabel("sentence");
		GridBagConstraints gbc_lblSent = new GridBagConstraints();
		gbc_lblSent.anchor = GridBagConstraints.EAST;
		gbc_lblSent.insets = new Insets(0, 0, 5, 5);
		gbc_lblSent.gridx = 1;
		gbc_lblSent.gridy = 0;
		getContentPane().add(lblSent, gbc_lblSent);
		
		textSent = new JTextField();
		GridBagConstraints gbc_textSent = new GridBagConstraints();
		gbc_textSent.gridwidth = 3;
		gbc_textSent.insets = new Insets(0, 0, 5, 5);
		gbc_textSent.fill = GridBagConstraints.HORIZONTAL;
		gbc_textSent.gridx = 2;
		gbc_textSent.gridy = 0;
		getContentPane().add(textSent, gbc_textSent);
		textSent.setColumns(10);
		
		JLabel lblPron = new JLabel("pronunciation");
		GridBagConstraints gbc_lblPron = new GridBagConstraints();
		gbc_lblPron.anchor = GridBagConstraints.EAST;
		gbc_lblPron.insets = new Insets(0, 0, 5, 5);
		gbc_lblPron.gridx = 1;
		gbc_lblPron.gridy = 2;
		getContentPane().add(lblPron, gbc_lblPron);
		
		textPron = new JTextField();
		GridBagConstraints gbc_textPron = new GridBagConstraints();
		gbc_textPron.gridwidth = 3;
		gbc_textPron.insets = new Insets(0, 0, 5, 5);
		gbc_textPron.fill = GridBagConstraints.HORIZONTAL;
		gbc_textPron.gridx = 2;
		gbc_textPron.gridy = 2;
		getContentPane().add(textPron, gbc_textPron);
		textPron.setColumns(10);
		
		JLabel lblTag = new JLabel("tag");
		GridBagConstraints gbc_lblTag = new GridBagConstraints();
		gbc_lblTag.anchor = GridBagConstraints.EAST;
		gbc_lblTag.insets = new Insets(0, 0, 5, 5);
		gbc_lblTag.gridx = 1;
		gbc_lblTag.gridy = 4;
		getContentPane().add(lblTag, gbc_lblTag);
		
		textTag = new JTextField();
		GridBagConstraints gbc_textTag = new GridBagConstraints();
		gbc_textTag.gridwidth = 3;
		gbc_textTag.insets = new Insets(0, 0, 5, 5);
		gbc_textTag.fill = GridBagConstraints.HORIZONTAL;
		gbc_textTag.gridx = 2;
		gbc_textTag.gridy = 4;
		getContentPane().add(textTag, gbc_textTag);
		textTag.setColumns(10);
		textTag.setText("raw");
		
		JLabel lblSurn = new JLabel("surname");
		GridBagConstraints gbc_lblSurn = new GridBagConstraints();
		gbc_lblSurn.anchor = GridBagConstraints.EAST;
		gbc_lblSurn.insets = new Insets(0, 0, 5, 5);
		gbc_lblSurn.gridx = 1;
		gbc_lblSurn.gridy = 6;
		getContentPane().add(lblSurn, gbc_lblSurn);
		
		textSurn = new JTextField();
		GridBagConstraints gbc_textSurn = new GridBagConstraints();
		gbc_textSurn.fill = GridBagConstraints.HORIZONTAL;
		gbc_textSurn.insets = new Insets(0, 0, 5, 5);
		gbc_textSurn.gridx = 2;
		gbc_textSurn.gridy = 6;
		getContentPane().add(textSurn, gbc_textSurn);
		textSurn.setColumns(10);
		
		JLabel lblDate = new JLabel("datetime");
		GridBagConstraints gbc_lblDate = new GridBagConstraints();
		gbc_lblDate.anchor = GridBagConstraints.EAST;
		gbc_lblDate.insets = new Insets(0, 0, 5, 5);
		gbc_lblDate.gridx = 3;
		gbc_lblDate.gridy = 6;
		getContentPane().add(lblDate, gbc_lblDate);
		
		textDate = new JTextField();
		GridBagConstraints gbc_textDate = new GridBagConstraints();
		gbc_textDate.insets = new Insets(0, 0, 5, 5);
		gbc_textDate.fill = GridBagConstraints.HORIZONTAL;
		gbc_textDate.gridx = 4;
		gbc_textDate.gridy = 6;
		getContentPane().add(textDate, gbc_textDate);
		textDate.setColumns(10);
		
		JLabel lblFirs = new JLabel("first name");
		GridBagConstraints gbc_lblFirs = new GridBagConstraints();
		gbc_lblFirs.anchor = GridBagConstraints.EAST;
		gbc_lblFirs.insets = new Insets(0, 0, 5, 5);
		gbc_lblFirs.gridx = 1;
		gbc_lblFirs.gridy = 7;
		getContentPane().add(lblFirs, gbc_lblFirs);
		
		textFirs = new JTextField();
		GridBagConstraints gbc_textFirs = new GridBagConstraints();
		gbc_textFirs.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFirs.insets = new Insets(0, 0, 5, 5);
		gbc_textFirs.gridx = 2;
		gbc_textFirs.gridy = 7;
		getContentPane().add(textFirs, gbc_textFirs);
		textFirs.setColumns(10);
		
		JLabel lblLoca = new JLabel("location");
		GridBagConstraints gbc_lblLoca = new GridBagConstraints();
		gbc_lblLoca.anchor = GridBagConstraints.EAST;
		gbc_lblLoca.insets = new Insets(0, 0, 5, 5);
		gbc_lblLoca.gridx = 3;
		gbc_lblLoca.gridy = 7;
		getContentPane().add(lblLoca, gbc_lblLoca);
		
		textLoca = new JTextField();
		GridBagConstraints gbc_textLoca = new GridBagConstraints();
		gbc_textLoca.insets = new Insets(0, 0, 5, 5);
		gbc_textLoca.fill = GridBagConstraints.HORIZONTAL;
		gbc_textLoca.gridx = 4;
		gbc_textLoca.gridy = 7;
		getContentPane().add(textLoca, gbc_textLoca);
		textLoca.setColumns(10);
		
		JLabel lblGend = new JLabel("gender");
		GridBagConstraints gbc_lblGend = new GridBagConstraints();
		gbc_lblGend.anchor = GridBagConstraints.EAST;
		gbc_lblGend.insets = new Insets(0, 0, 5, 5);
		gbc_lblGend.gridx = 1;
		gbc_lblGend.gridy = 8;
		getContentPane().add(lblGend, gbc_lblGend);
		
		textGend = new JTextField();
		GridBagConstraints gbc_textGend = new GridBagConstraints();
		gbc_textGend.fill = GridBagConstraints.HORIZONTAL;
		gbc_textGend.insets = new Insets(0, 0, 5, 5);
		gbc_textGend.gridx = 2;
		gbc_textGend.gridy = 8;
		getContentPane().add(textGend, gbc_textGend);
		textGend.setColumns(10);
		
		JLabel lblSurr = new JLabel("surrounding");
		GridBagConstraints gbc_lblSurr = new GridBagConstraints();
		gbc_lblSurr.anchor = GridBagConstraints.EAST;
		gbc_lblSurr.insets = new Insets(0, 0, 5, 5);
		gbc_lblSurr.gridx = 3;
		gbc_lblSurr.gridy = 8;
		getContentPane().add(lblSurr, gbc_lblSurr);
		
		textSurr = new JTextField();
		GridBagConstraints gbc_textSurr = new GridBagConstraints();
		gbc_textSurr.insets = new Insets(0, 0, 5, 5);
		gbc_textSurr.fill = GridBagConstraints.HORIZONTAL;
		gbc_textSurr.gridx = 4;
		gbc_textSurr.gridy = 8;
		getContentPane().add(textSurr, gbc_textSurr);
		textSurr.setColumns(10);
		
		JLabel lblAge = new JLabel("age");
		GridBagConstraints gbc_lblAge = new GridBagConstraints();
		gbc_lblAge.anchor = GridBagConstraints.EAST;
		gbc_lblAge.insets = new Insets(0, 0, 5, 5);
		gbc_lblAge.gridx = 1;
		gbc_lblAge.gridy = 9;
		getContentPane().add(lblAge, gbc_lblAge);
		
		textAge = new JTextField();
		GridBagConstraints gbc_textAge = new GridBagConstraints();
		gbc_textAge.fill = GridBagConstraints.HORIZONTAL;
		gbc_textAge.insets = new Insets(0, 0, 5, 5);
		gbc_textAge.gridx = 2;
		gbc_textAge.gridy = 9;
		getContentPane().add(textAge, gbc_textAge);
		textAge.setColumns(10);
		
		JLabel lblDevi = new JLabel("device");
		GridBagConstraints gbc_lblDevi = new GridBagConstraints();
		gbc_lblDevi.anchor = GridBagConstraints.EAST;
		gbc_lblDevi.insets = new Insets(0, 0, 5, 5);
		gbc_lblDevi.gridx = 3;
		gbc_lblDevi.gridy = 9;
		getContentPane().add(lblDevi, gbc_lblDevi);
		
		textDevi = new JTextField();
		GridBagConstraints gbc_textDevi = new GridBagConstraints();
		gbc_textDevi.insets = new Insets(0, 0, 5, 5);
		gbc_textDevi.fill = GridBagConstraints.HORIZONTAL;
		gbc_textDevi.gridx = 4;
		gbc_textDevi.gridy = 9;
		getContentPane().add(textDevi, gbc_textDevi);
		textDevi.setColumns(10);
		
		JLabel lblBirt = new JLabel("birth place");
		GridBagConstraints gbc_lblBirt = new GridBagConstraints();
		gbc_lblBirt.anchor = GridBagConstraints.EAST;
		gbc_lblBirt.insets = new Insets(0, 0, 5, 5);
		gbc_lblBirt.gridx = 1;
		gbc_lblBirt.gridy = 10;
		getContentPane().add(lblBirt, gbc_lblBirt);
		
		textBirt = new JTextField();
		GridBagConstraints gbc_textBirt = new GridBagConstraints();
		gbc_textBirt.fill = GridBagConstraints.HORIZONTAL;
		gbc_textBirt.insets = new Insets(0, 0, 5, 5);
		gbc_textBirt.gridx = 2;
		gbc_textBirt.gridy = 10;
		getContentPane().add(textBirt, gbc_textBirt);
		textBirt.setColumns(10);
		
		JLabel lblBran = new JLabel("brand");
		GridBagConstraints gbc_lblBran = new GridBagConstraints();
		gbc_lblBran.anchor = GridBagConstraints.EAST;
		gbc_lblBran.insets = new Insets(0, 0, 5, 5);
		gbc_lblBran.gridx = 3;
		gbc_lblBran.gridy = 10;
		getContentPane().add(lblBran, gbc_lblBran);
		
		textBran = new JTextField();
		GridBagConstraints gbc_textBran = new GridBagConstraints();
		gbc_textBran.insets = new Insets(0, 0, 5, 5);
		gbc_textBran.fill = GridBagConstraints.HORIZONTAL;
		gbc_textBran.gridx = 4;
		gbc_textBran.gridy = 10;
		getContentPane().add(textBran, gbc_textBran);
		textBran.setColumns(10);
		
		JLabel lblDial = new JLabel("dialect");
		GridBagConstraints gbc_lblDial = new GridBagConstraints();
		gbc_lblDial.anchor = GridBagConstraints.EAST;
		gbc_lblDial.insets = new Insets(0, 0, 5, 5);
		gbc_lblDial.gridx = 1;
		gbc_lblDial.gridy = 11;
		getContentPane().add(lblDial, gbc_lblDial);
		
		textDial = new JTextField();
		GridBagConstraints gbc_textDial = new GridBagConstraints();
		gbc_textDial.fill = GridBagConstraints.HORIZONTAL;
		gbc_textDial.insets = new Insets(0, 0, 5, 5);
		gbc_textDial.gridx = 2;
		gbc_textDial.gridy = 11;
		getContentPane().add(textDial, gbc_textDial);
		textDial.setColumns(10);
		
		JLabel lblType = new JLabel("type");
		GridBagConstraints gbc_lblType = new GridBagConstraints();
		gbc_lblType.anchor = GridBagConstraints.EAST;
		gbc_lblType.insets = new Insets(0, 0, 5, 5);
		gbc_lblType.gridx = 3;
		gbc_lblType.gridy = 11;
		getContentPane().add(lblType, gbc_lblType);
		
		textType = new JTextField();
		GridBagConstraints gbc_textType = new GridBagConstraints();
		gbc_textType.insets = new Insets(0, 0, 5, 5);
		gbc_textType.fill = GridBagConstraints.HORIZONTAL;
		gbc_textType.gridx = 4;
		gbc_textType.gridy = 11;
		getContentPane().add(textType, gbc_textType);
		textType.setColumns(10);
		
		JLabel lblEduc = new JLabel("education");
		GridBagConstraints gbc_lblEduc = new GridBagConstraints();
		gbc_lblEduc.anchor = GridBagConstraints.EAST;
		gbc_lblEduc.insets = new Insets(0, 0, 5, 5);
		gbc_lblEduc.gridx = 1;
		gbc_lblEduc.gridy = 12;
		getContentPane().add(lblEduc, gbc_lblEduc);
		
		textEduc = new JTextField();
		GridBagConstraints gbc_textEduc = new GridBagConstraints();
		gbc_textEduc.fill = GridBagConstraints.HORIZONTAL;
		gbc_textEduc.insets = new Insets(0, 0, 5, 5);
		gbc_textEduc.gridx = 2;
		gbc_textEduc.gridy = 12;
		getContentPane().add(textEduc, gbc_textEduc);
		textEduc.setColumns(10);
		
		JLabel lblDire = new JLabel("directivity");
		GridBagConstraints gbc_lblDire = new GridBagConstraints();
		gbc_lblDire.anchor = GridBagConstraints.EAST;
		gbc_lblDire.insets = new Insets(0, 0, 5, 5);
		gbc_lblDire.gridx = 3;
		gbc_lblDire.gridy = 12;
		getContentPane().add(lblDire, gbc_lblDire);
		
		textDire = new JTextField();
		GridBagConstraints gbc_textDire = new GridBagConstraints();
		gbc_textDire.insets = new Insets(0, 0, 5, 5);
		gbc_textDire.fill = GridBagConstraints.HORIZONTAL;
		gbc_textDire.gridx = 4;
		gbc_textDire.gridy = 12;
		getContentPane().add(textDire, gbc_textDire);
		textDire.setColumns(10);
		
		JLabel lblTool = new JLabel("tool");
		GridBagConstraints gbc_lblTool = new GridBagConstraints();
		gbc_lblTool.anchor = GridBagConstraints.EAST;
		gbc_lblTool.insets = new Insets(0, 0, 5, 5);
		gbc_lblTool.gridx = 1;
		gbc_lblTool.gridy = 13;
		getContentPane().add(lblTool, gbc_lblTool);
		
		textTool = new JTextField();
		GridBagConstraints gbc_textTool = new GridBagConstraints();
		gbc_textTool.insets = new Insets(0, 0, 5, 5);
		gbc_textTool.fill = GridBagConstraints.HORIZONTAL;
		gbc_textTool.gridx = 2;
		gbc_textTool.gridy = 13;
		getContentPane().add(textTool, gbc_textTool);
		textTool.setColumns(10);
		
		JLabel lblSens = new JLabel("sensitivity");
		GridBagConstraints gbc_lblSens = new GridBagConstraints();
		gbc_lblSens.anchor = GridBagConstraints.EAST;
		gbc_lblSens.insets = new Insets(0, 0, 5, 5);
		gbc_lblSens.gridx = 3;
		gbc_lblSens.gridy = 13;
		getContentPane().add(lblSens, gbc_lblSens);
		
		textSens = new JTextField();
		GridBagConstraints gbc_textSens = new GridBagConstraints();
		gbc_textSens.insets = new Insets(0, 0, 5, 5);
		gbc_textSens.fill = GridBagConstraints.HORIZONTAL;
		gbc_textSens.gridx = 4;
		gbc_textSens.gridy = 13;
		getContentPane().add(textSens, gbc_textSens);
		textSens.setColumns(10);
		
		JLabel lblMode = new JLabel("model");
		GridBagConstraints gbc_lblMode = new GridBagConstraints();
		gbc_lblMode.anchor = GridBagConstraints.EAST;
		gbc_lblMode.insets = new Insets(0, 0, 5, 5);
		gbc_lblMode.gridx = 1;
		gbc_lblMode.gridy = 14;
		getContentPane().add(lblMode, gbc_lblMode);
		
		textMode = new JTextField();
		GridBagConstraints gbc_textMode = new GridBagConstraints();
		gbc_textMode.insets = new Insets(0, 0, 5, 5);
		gbc_textMode.fill = GridBagConstraints.HORIZONTAL;
		gbc_textMode.gridx = 2;
		gbc_textMode.gridy = 14;
		getContentPane().add(textMode, gbc_textMode);
		textMode.setColumns(10);
		
		JLabel lblRang = new JLabel("range");
		GridBagConstraints gbc_lblRang = new GridBagConstraints();
		gbc_lblRang.anchor = GridBagConstraints.EAST;
		gbc_lblRang.insets = new Insets(0, 0, 5, 5);
		gbc_lblRang.gridx = 3;
		gbc_lblRang.gridy = 14;
		getContentPane().add(lblRang, gbc_lblRang);
		
		textRang = new JTextField();
		GridBagConstraints gbc_textRang = new GridBagConstraints();
		gbc_textRang.insets = new Insets(0, 0, 5, 5);
		gbc_textRang.fill = GridBagConstraints.HORIZONTAL;
		gbc_textRang.gridx = 4;
		gbc_textRang.gridy = 14;
		getContentPane().add(textRang, gbc_textRang);
		textRang.setColumns(10);
		
		lblVers = new JLabel("version");
		GridBagConstraints gbc_lblVers = new GridBagConstraints();
		gbc_lblVers.anchor = GridBagConstraints.EAST;
		gbc_lblVers.insets = new Insets(0, 0, 5, 5);
		gbc_lblVers.gridx = 1;
		gbc_lblVers.gridy = 15;
		getContentPane().add(lblVers, gbc_lblVers);
		
		textVers = new JTextField();
		textVers.setColumns(10);
		GridBagConstraints gbc_textVers = new GridBagConstraints();
		gbc_textVers.insets = new Insets(0, 0, 5, 5);
		gbc_textVers.fill = GridBagConstraints.HORIZONTAL;
		gbc_textVers.gridx = 2;
		gbc_textVers.gridy = 15;
		getContentPane().add(textVers, gbc_textVers);
		
		lblFreq = new JLabel("frequency");
		GridBagConstraints gbc_lblFreq = new GridBagConstraints();
		gbc_lblFreq.anchor = GridBagConstraints.EAST;
		gbc_lblFreq.insets = new Insets(0, 0, 5, 5);
		gbc_lblFreq.gridx = 3;
		gbc_lblFreq.gridy = 15;
		getContentPane().add(lblFreq, gbc_lblFreq);
		
		textFreq = new JTextField();
		textFreq.setColumns(10);
		GridBagConstraints gbc_textFreq = new GridBagConstraints();
		gbc_textFreq.insets = new Insets(0, 0, 5, 5);
		gbc_textFreq.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFreq.gridx = 4;
		gbc_textFreq.gridy = 15;
		getContentPane().add(textFreq, gbc_textFreq);
		
		lblLowf = new JLabel("lowfrequency");
		GridBagConstraints gbc_lblLowf = new GridBagConstraints();
		gbc_lblLowf.anchor = GridBagConstraints.EAST;
		gbc_lblLowf.insets = new Insets(0, 0, 5, 5);
		gbc_lblLowf.gridx = 1;
		gbc_lblLowf.gridy = 16;
		getContentPane().add(lblLowf, gbc_lblLowf);
		
		textLowf = new JTextField();
		textLowf.setColumns(10);
		GridBagConstraints gbc_textLowf = new GridBagConstraints();
		gbc_textLowf.insets = new Insets(0, 0, 5, 5);
		gbc_textLowf.fill = GridBagConstraints.HORIZONTAL;
		gbc_textLowf.gridx = 2;
		gbc_textLowf.gridy = 16;
		getContentPane().add(textLowf, gbc_textLowf);
		
		lblHigh = new JLabel("highfrequency");
		GridBagConstraints gbc_lblHigh = new GridBagConstraints();
		gbc_lblHigh.anchor = GridBagConstraints.EAST;
		gbc_lblHigh.insets = new Insets(0, 0, 5, 5);
		gbc_lblHigh.gridx = 3;
		gbc_lblHigh.gridy = 16;
		getContentPane().add(lblHigh, gbc_lblHigh);
		
		textHigh = new JTextField();
		textHigh.setColumns(10);
		GridBagConstraints gbc_textHigh = new GridBagConstraints();
		gbc_textHigh.insets = new Insets(0, 0, 5, 5);
		gbc_textHigh.fill = GridBagConstraints.HORIZONTAL;
		gbc_textHigh.gridx = 4;
		gbc_textHigh.gridy = 16;
		getContentPane().add(textHigh, gbc_textHigh);
		
		lblEnco = new JLabel("encoding");
		GridBagConstraints gbc_lblEnco = new GridBagConstraints();
		gbc_lblEnco.anchor = GridBagConstraints.EAST;
		gbc_lblEnco.insets = new Insets(0, 0, 5, 5);
		gbc_lblEnco.gridx = 1;
		gbc_lblEnco.gridy = 17;
		getContentPane().add(lblEnco, gbc_lblEnco);
		
		textEnco = new JTextField();
		textEnco.setColumns(10);
		GridBagConstraints gbc_textEnco = new GridBagConstraints();
		gbc_textEnco.insets = new Insets(0, 0, 5, 5);
		gbc_textEnco.fill = GridBagConstraints.HORIZONTAL;
		gbc_textEnco.gridx = 2;
		gbc_textEnco.gridy = 17;
		getContentPane().add(textEnco, gbc_textEnco);
		
		lblBit = new JLabel("bit");
		GridBagConstraints gbc_lblBit = new GridBagConstraints();
		gbc_lblBit.anchor = GridBagConstraints.EAST;
		gbc_lblBit.insets = new Insets(0, 0, 5, 5);
		gbc_lblBit.gridx = 3;
		gbc_lblBit.gridy = 17;
		getContentPane().add(lblBit, gbc_lblBit);
		
		textBit = new JTextField();
		textBit.setColumns(10);
		GridBagConstraints gbc_textBit = new GridBagConstraints();
		gbc_textBit.insets = new Insets(0, 0, 5, 5);
		gbc_textBit.fill = GridBagConstraints.HORIZONTAL;
		gbc_textBit.gridx = 4;
		gbc_textBit.gridy = 17;
		getContentPane().add(textBit, gbc_textBit);
		
		lblChan = new JLabel("channel");
		GridBagConstraints gbc_lblChan = new GridBagConstraints();
		gbc_lblChan.anchor = GridBagConstraints.EAST;
		gbc_lblChan.insets = new Insets(0, 0, 0, 5);
		gbc_lblChan.gridx = 1;
		gbc_lblChan.gridy = 18;
		getContentPane().add(lblChan, gbc_lblChan);
		
		textChan = new JTextField();
		textChan.setColumns(10);
		GridBagConstraints gbc_textChan = new GridBagConstraints();
		gbc_textChan.insets = new Insets(0, 0, 0, 5);
		gbc_textChan.fill = GridBagConstraints.HORIZONTAL;
		gbc_textChan.gridx = 2;
		gbc_textChan.gridy = 18;
		getContentPane().add(textChan, gbc_textChan);
		
		lblNois = new JLabel("noise");
		GridBagConstraints gbc_lblNois = new GridBagConstraints();
		gbc_lblNois.anchor = GridBagConstraints.EAST;
		gbc_lblNois.insets = new Insets(0, 0, 0, 5);
		gbc_lblNois.gridx = 3;
		gbc_lblNois.gridy = 18;
		getContentPane().add(lblNois, gbc_lblNois);
		
		textNois = new JTextField();
		textNois.setColumns(10);
		GridBagConstraints gbc_textNois = new GridBagConstraints();
		gbc_textNois.insets = new Insets(0, 0, 0, 5);
		gbc_textNois.fill = GridBagConstraints.HORIZONTAL;
		gbc_textNois.gridx = 4;
		gbc_textNois.gridy = 18;
		getContentPane().add(textNois, gbc_textNois);
		

		setSize(915, 649);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
		
	}
	
	public Description getDescription() {
		Speaker s = new Speaker();
		s.surname = textSurn.getText();
		s.firstname = textFirs.getText();
		s.gender = textGend.getText();
		s.age = textAge.getText();
		s.birthplace = textBirt.getText();
		s.dialect = textDial.getText();
		s.education = textEduc.getText();
		Microphone m = new Microphone();
		m.brand = textBran.getText();
		m.model = textMode.getText();
		m.type = textType.getText();
		m.directivity = textDire.getText();
		m.sensitivity = textSens.getText();
		m.range = textRang.getText();
		Hardware h = new Hardware();
		h.device = textDevi.getText();
		h.microphone = m;
		Software sf = new Software();
		sf.tool = textTool.getText();
		sf.version = textVers.getText();
		Description des = new Description();
		des.sentence = textSent.getText();
		des.pronunciation = textPron.getText();
		des.tag = textTag.getText();
		des.speaker = s;
		des.datetime = textDate.getText();
		des.location = textLoca.getText();
		des.surrounding = textSurr.getText();
		des.hardware = h;
		des.software = sf;
		des.frequency = textFreq.getText();
		des.lowfrequency = textLowf.getText();
		des.highfrequency = textHigh.getText();
		des.encoding = textEnco.getText();
		des.bit = textBit.getText();
		des.channel = textChan.getText();
		des.noise = textNois.getText();
		des.format = "WAV";
		return des;
	}

	public void setTags(String tagStr) {
		textTag.setText(tagStr);
	}
	
	public String getTags() {
		return textTag.getText();
	}
	
	public void forNextSentence(boolean has_xml) {
		if (has_xml) {
			if (textTag.getText().equals("")) {
				textTag.setText("raw");
				textPron.setText("");
				textSent.setText("");
			}
		} else {
			
		}
	}

	
	public void setDescription(Description d) {
		Speaker s = d.speaker;
		Hardware h = d.hardware;
		Microphone m = h.microphone;
		Software sf = d.software;
		textDate.setText(d.datetime);
		textLoca.setText(d.location);
		textSurr.setText(d.surrounding);
		textDevi.setText(h.device);
		textBran.setText(m.brand);
		textType.setText(m.type);
		textMode.setText(m.model);
		textDire.setText(m.directivity);
		textSens.setText(m.sensitivity);
		textRang.setText(m.range);
		textTool.setText(sf.tool);
		textVers.setText(sf.version);
		textSurn.setText(s.surname);
		textFirs.setText(s.firstname);
		textGend.setText(s.gender);
		textAge.setText(s.age);
		textBirt.setText(s.birthplace);
		textDial.setText(s.dialect);
		textEduc.setText(s.education);
		textSent.setText(d.sentence);
		textPron.setText(d.pronunciation);
		textTag.setText(d.tag);
		textFreq.setText(d.frequency);
		textLowf.setText(d.lowfrequency);
		textHigh.setText(d.highfrequency);
		textEnco.setText(d.encoding);
		textBit.setText(d.bit);
		textChan.setText(d.channel);
		textNois.setText(d.noise);
	}
}
