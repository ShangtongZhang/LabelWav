import java.awt.Color;

import javax.swing.JLabel;


@SuppressWarnings("serial")
public class IndicatorLine extends JLabel{
	
	public IndicatorLine(int x_pos) {
		super();
		setBackground(Color.YELLOW);
		setOpaque(true);
		setBounds(x_pos, 1, Constant.SEGMENTLINE_WIDTH, Constant.SEGMENTLINE_HEIGHT);
		setVisible(true);
	}
}
