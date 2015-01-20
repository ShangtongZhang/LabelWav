import java.awt.Button;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

@SuppressWarnings("serial")
public class LabelWavUI extends JFrame{

	private WavePanel wave_pn = null;
	private LocalWavePanel local_wave_pn = null;
	private VideoSlider video_sl = null;
	private IndicatorLine indi_line = null;
	private IndicatorLine local_indi_line = new IndicatorLine(0);
	private File current_file = null;
	private XMLPanel xml_pn = null;
	private int[] current_video_data = null; 
	private JTextField current_tag = null;
	private JTextField local_scale = null;
	private JTextField append_txt = null;
	private ArrayList<JLabel> all_tags = new ArrayList<JLabel>();
	private JTextField seg_length_txt = null;
	
	private ArrayList<SegmentLine> segmentlines = new ArrayList<SegmentLine>();
	private ArrayList<SegmentLine> local_segmentLines = new ArrayList<SegmentLine>();
	
	private ArrayList<String> current_tags = new ArrayList<String>();
	private int current_tag_ind = -1;
	
	private boolean isPlaying = false;
	
	private boolean isSegmentLineExist(Integer segmentline_pos) {
		for (SegmentLine sl : segmentlines) {
			if (segmentline_pos.equals(sl.getDataPos())) {
				return true;
			}
		}
		return false;
	}
	
	private boolean generateTag(Description d) {
		String tag0 = d.tag;
		
		String[] del_symbol = new String[]{",", ".", ":", "!", ";", "?",
		                       "，", "。", "：", "！", "；", "？"};
		for (String sym : del_symbol) {
			tag0  =tag0.replace(sym, "");
		}
		String[] tag1 = tag0.split(" ");
		ArrayList<String> tag2 = new ArrayList<String>();
		for (String w : tag1) {
			if (w.length() >= 1) {
				if (-1 != w.indexOf("\\s") ) {
					continue;
				}
				tag2.add(w.trim());
			}
		}
		
		int wav_size;
		if (wave_pn != null) {
			wav_size = wave_pn.getWavSize();
			if (wav_size <= 0) {
				return false;
			}
		} else {
			return false;
		}
		
		ArrayList<Integer> tag_poses = new ArrayList<Integer>();
		for (SegmentLine sl : segmentlines) {
			tag_poses.add(sl.getDataPos());
		}
		
		Collections.sort(tag_poses);
		tag_poses.add(wav_size);
		if (tag_poses.size() != tag2.size()) {
			String err_msg = "根据tag栏显示，您一共标记出了"+tag2.size()+"个字。\n但根据波形图上的分隔线一共有"+tag_poses.size()+"个字。\n它们应当相等。";
			JOptionPane.showMessageDialog(this, err_msg);
			return false;
		}
		
		String seg_pos = "";
		Collections.sort(segmentlines, new SegmentLineComp());
		for (SegmentLine sl : segmentlines) {
			seg_pos += String.valueOf(sl.getDataPos()) + " ";
		}
		seg_pos += String.valueOf(current_video_data.length - 1);
		d.segment_poses = seg_pos;
		
		return true;
				
	}
	
	boolean hasWavPanel = false;
	
	public LabelWavUI() {
		
		xml_pn = new XMLPanel(this);
		
		this.getContentPane().setLayout(null);
		
		indi_line = new IndicatorLine(0);
		add(indi_line);
		
		wave_pn = new WavePanel(this);
		wave_pn.setBounds(0, 0, Constant.WAVEPANEL_WIDTH, Constant.WAVEPANEL_HEIGHT);
		wave_pn.setVisible(true);
		add(wave_pn);
		
		local_wave_pn = new LocalWavePanel(this);
		local_wave_pn.setBounds(0, Constant.WAVEPANEL_HEIGHT, Constant.WAVEPANEL_WIDTH, Constant.WAVEPANEL_HEIGHT);
		local_wave_pn.setVisible(true);
		add(local_wave_pn);
		
		video_sl = new VideoSlider(this);
		video_sl.setBounds(0, 2*Constant.WAVEPANEL_HEIGHT, Constant.WAVEPANEL_WIDTH, Constant.VIDEOSLIDER_HEIGHT);
		video_sl.setVisible(true);
		video_sl.setValue(0);
		add(video_sl);
		
		ActionListener alPlayStop = new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if (isPlaying) {
					video_sl.stop();
					isPlaying = false;
				} else {
					playCurrentSegment();
					isPlaying = true;
				}
				
			}
		};
		
		JButton btnPlay = new JButton();
		btnPlay.setText("Play");
		btnPlay.setBounds(20, Constant.BUTTON_Y_POS, Constant.BUTTON_WIDTH, Constant.BUTTON_HEIGHT);
		btnPlay.addActionListener(alPlayStop);
		btnPlay.setVisible(true);
		btnPlay.registerKeyboardAction(alPlayStop, KeyStroke.getKeyStroke(KeyEvent.VK_F5, 0, false), 
				JComponent.WHEN_IN_FOCUSED_WINDOW);
		btnPlay.registerKeyboardAction(alPlayStop, KeyStroke.getKeyStroke(KeyEvent.VK_SPACE, 0, false), 
				JComponent.WHEN_IN_FOCUSED_WINDOW);
		add(btnPlay);
		
		JButton btnStop = new JButton();
		btnStop.setText("Stop");
		btnStop.setBounds(110, Constant.BUTTON_Y_POS, Constant.BUTTON_WIDTH, Constant.BUTTON_HEIGHT);
		btnStop.setVisible(true);
		add(btnStop);
		btnStop.addActionListener(alPlayStop);
		btnStop.registerKeyboardAction(alPlayStop, KeyStroke.getKeyStroke(KeyEvent.VK_F2, 0, false), 
				JComponent.WHEN_IN_FOCUSED_WINDOW);
		btnStop.registerKeyboardAction(alPlayStop, KeyStroke.getKeyStroke(KeyEvent.VK_SPACE, 0, false), 
				JComponent.WHEN_IN_FOCUSED_WINDOW);
		
		final JButton btnFile = new JButton();
		btnFile.setText("Open");
		btnFile.setBounds(200, Constant.BUTTON_Y_POS, Constant.BUTTON_WIDTH, Constant.BUTTON_HEIGHT);
		ActionListener alOpen = new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				JFileChooser jfc = new JFileChooser();
				jfc.setDialogTitle("Select the wav file");
				jfc.setCurrentDirectory(current_file);
				int result = jfc.showOpenDialog(btnFile);
				if (result == JFileChooser.APPROVE_OPTION) {
					current_file = jfc.getSelectedFile();
					clearSegmentlines();
					setVideo(current_file);
					String cor_xml_file = current_file.getAbsolutePath().replace(".wav", ".xml");
					if (new File(cor_xml_file).exists()) {
						loadXML(cor_xml_file);
						xml_pn.forNextSentence(true);
					} else {
						xml_pn.forNextSentence(false);
					}
					
				}	
			}
		};
		btnFile.registerKeyboardAction(alOpen, KeyStroke.getKeyStroke(KeyEvent.VK_O, InputEvent.CTRL_MASK, false), 
				JComponent.WHEN_IN_FOCUSED_WINDOW);
		btnFile.addActionListener(alOpen);
		btnFile.setVisible(true);
		add(btnFile);
		
		final JButton btnSave = new JButton();
		btnSave.setText("Save");
		btnSave.setBounds(290, Constant.BUTTON_Y_POS, Constant.BUTTON_WIDTH, Constant.BUTTON_HEIGHT);
		ActionListener alSave = new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				Description des = xml_pn.getDescription();
				if (!generateTag(des)) {
					return;
				}
				des.file = current_file.getName();
				XMLHandler xml_h = new XMLHandler();
				String xml_content = xml_h.toXML(des);
				String xml_filename = current_file.getAbsolutePath().replace(".wav", ".xml");
				try {
					OutputStreamWriter outXml = new OutputStreamWriter(new FileOutputStream(xml_filename), "UTF-8");
					outXml.write(xml_content);
					outXml.close();
					current_tag.setText("Done!");
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		};
		btnSave.addActionListener(alSave);
		btnSave.registerKeyboardAction(alSave, KeyStroke.getKeyStroke(KeyEvent.VK_S, InputEvent.CTRL_MASK, false), 
				JComponent.WHEN_IN_FOCUSED_WINDOW);
		btnSave.setVisible(true);
		add(btnSave);
		
		final JButton btnLoad = new JButton();
		btnLoad.setText("Load");
		btnLoad.setBounds(380, Constant.BUTTON_Y_POS, Constant.BUTTON_WIDTH, Constant.BUTTON_HEIGHT);
		ActionListener alLoad = new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				JFileChooser jfc = new JFileChooser();
				jfc.setDialogTitle("Select the xml file");
				jfc.setCurrentDirectory(current_file);
				int result = jfc.showOpenDialog(btnLoad);
				if (result == JFileChooser.APPROVE_OPTION) {
					File xml = jfc.getSelectedFile();
					loadXML(xml.getAbsolutePath());
				}
			}
		};
		btnLoad.addActionListener(alLoad);
		btnLoad.registerKeyboardAction(alLoad, KeyStroke.getKeyStroke(KeyEvent.VK_L, InputEvent.CTRL_MASK, false), 
				JComponent.WHEN_IN_FOCUSED_WINDOW);
		btnLoad.setVisible(true);
		add(btnLoad);
		
		seg_length_txt = new JTextField();
		seg_length_txt.setText("");
		seg_length_txt.setEnabled(false);
		seg_length_txt.setBounds(470, Constant.BUTTON_Y_POS, Constant.BUTTON_WIDTH, Constant.BUTTON_HEIGHT);
		seg_length_txt.setVisible(true);
		add(seg_length_txt);
		
		
		
		current_tag = new JTextField();
		current_tag.setText("Error");
		current_tag.setBounds(990, Constant.BUTTON_Y_POS, Constant.BUTTON_WIDTH, Constant.BUTTON_HEIGHT);
		current_tag.setVisible(true);
		add(current_tag);
		
		Button btnApply = new Button();
		btnApply.setLabel("Apply");
		btnApply.setBounds(1050, Constant.BUTTON_Y_POS, Constant.BUTTON_WIDTH, Constant.BUTTON_HEIGHT);
		btnApply.setVisible(true);
		add(btnApply);
		btnApply.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if (current_tag_ind == -1) {
					return;
				}
				String old_tag = current_tags.get(current_tag_ind);
				String new_tag = current_tag.getText();
				StringBuffer tagStr = new StringBuffer(xml_pn.getTags());
				int same_tag_cnt = 0;
				int dst_ind = -1;
				for (int i = 0; i < current_tags.size(); i++) {
					if (current_tags.get(i).contains(old_tag)) {
						same_tag_cnt++;
					}
					if (i == current_tag_ind) {
						break;
					}
				}
				while (same_tag_cnt > 0) {
					same_tag_cnt--;
					dst_ind = tagStr.indexOf(old_tag, dst_ind+1);
				}
				
				tagStr.delete(dst_ind, dst_ind + old_tag.length());
				tagStr.insert(dst_ind, new_tag);
				xml_pn.setTags(tagStr.toString());
				current_tags.set(current_tag_ind, new_tag);
				displayAllTags();
				repaint();
				invalidate();
			}
		});
		
		Button scale_dec = new Button();
		scale_dec.setLabel("<<");
		scale_dec.setBounds(750, Constant.BUTTON_Y_POS, Constant.BUTTON_WIDTH, Constant.BUTTON_HEIGHT);
		scale_dec.setVisible(true);
		add(scale_dec);
		scale_dec.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				int scale = Constant.DATAPOINT_PER_PIXEL;
				if (scale >= 10) {
					scale += 10;
				} else {
					scale += 1;
				}
				if (scale >= 1000) {
					scale = 1000;
				}
				Constant.DATAPOINT_PER_PIXEL = scale;
				local_scale.setText(String.valueOf(scale));
				localWaveRepaint();
			}
		});
		
		local_scale = new JTextField();
		local_scale.setText(String.valueOf(Constant.DATAPOINT_PER_PIXEL));
		local_scale.setBounds(820, Constant.BUTTON_Y_POS, Constant.BUTTON_WIDTH, Constant.BUTTON_HEIGHT);
		local_scale.setVisible(true);
		add(local_scale);
		javax.swing.text.Document dt = local_scale.getDocument();
		dt.addDocumentListener(new DocumentListener() {
			
			@Override
			public void removeUpdate(DocumentEvent e) {
				
			}
			
			@Override
			public void insertUpdate(DocumentEvent e) {
				int scale = Integer.valueOf(local_scale.getText());
				if (scale >= 1 && scale <= 1000) {
					Constant.DATAPOINT_PER_PIXEL = scale;
					localWaveRepaint();
				}
			}
			
			@Override
			public void changedUpdate(DocumentEvent e) {
				
			}
		});
		
		Button scale_inc = new Button();
		scale_inc.setLabel(">>");
		scale_inc.setBounds(890, Constant.BUTTON_Y_POS, Constant.BUTTON_WIDTH, Constant.BUTTON_HEIGHT);
		scale_inc.setVisible(true);
		add(scale_inc);
		scale_inc.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int scale = Constant.DATAPOINT_PER_PIXEL;
				if (scale > 10) {
					scale -= 10;
				} else if (scale > 1){
					scale -= 1;
				}
				if (scale == 1) {
					scale = 1;
				}
				Constant.DATAPOINT_PER_PIXEL = scale;
				local_scale.setText(String.valueOf(scale));
				localWaveRepaint();
				
			}
		});
		
		append_txt = new JTextField();
		append_txt.setText("");
		append_txt.setBounds(550, Constant.BUTTON_Y_POS, Constant.BUTTON_WIDTH, Constant.BUTTON_HEIGHT);
		append_txt.setVisible(true);
		add(append_txt);
		
		JButton btnAppend = new JButton();
		btnAppend.setText("Append");
		btnAppend.setBounds(610, Constant.BUTTON_Y_POS, Constant.BUTTON_WIDTH + 30, Constant.BUTTON_HEIGHT);
		btnAppend.setVisible(true);
		add(btnAppend);
		ActionListener alAppend = new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				xml_pn.setTags(xml_pn.getTags().replace("raw", append_txt.getText() + " raw"));
				displayAllTags();
				append_txt.setText("");
			}
		};
		btnAppend.addActionListener(alAppend);
		btnAppend.registerKeyboardAction(alAppend, KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0, false), 
				JComponent.WHEN_IN_FOCUSED_WINDOW);
		
		
		setSize(Constant.UI_WIDTH, Constant.UI_HEIGHT);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
		
	}
	
	private void updateCurrentTags() {
		Description d = xml_pn.getDescription();
		String tag0 = d.tag;
		String[] del_symbol = new String[]{",", ".", ":", "!", ";", "?",
		                       "，", "。", "：", "！", "；", "？"};
		for (String sym : del_symbol) {
			tag0 = tag0.replace(sym, "");
		}
		String[] tag1 = tag0.split(" ");
		current_tags.clear();
		for (String w : tag1) {
			if (w.length() >= 1) {
				if (-1 != w.indexOf("\\s") ) {
					continue;
				}
				current_tags.add(w.trim());
			}
		}
	}
	
	public void displayCurrentTag(int cur_x) {
		int ind = 0;
		Collections.sort(segmentlines, new SegmentLineComp());
		for (SegmentLine sl : segmentlines) {
			if (sl.getX_pos() < cur_x) {
				ind++;
			} else {
				break;
			}	
		}
		
		int current_seg_length = 0;
		if (segmentlines.size() == 0) {
			current_seg_length = current_video_data.length;
		} else if (ind == 0) {
			current_seg_length = segmentlines.get(ind).getDataPos();
		} else if (ind == segmentlines.size()) {
			current_seg_length = current_video_data.length - segmentlines.get(ind - 1).getDataPos();
		} else {
			current_seg_length = segmentlines.get(ind).getDataPos() - segmentlines.get(ind - 1).getDataPos();
		}
		seg_length_txt.setText(String.valueOf(current_seg_length));
		
		updateCurrentTags();
		
		if (current_tags.size() != segmentlines.size()+1) {
			current_tag.setText("E : " + (current_tags.size() - segmentlines.size() - 1));
			current_tag_ind = -1;
			return;
		}
		current_tag.setText(current_tags.get(ind));
		current_tag_ind = ind;
		
	}
	
	public void setVideo (File video_file) {
		WaveFileReader reader = new WaveFileReader(video_file.getAbsolutePath());
		if (reader.isSuccess()) {
			current_video_data = reader.getData()[0];
		}
		segmentlines.clear();
		for (SegmentLine sl : local_segmentLines) {
			remove(sl);
		}
		local_segmentLines.clear();
		wave_pn.setVideo(current_video_data);
		local_wave_pn.setVideo(current_video_data);
		video_sl.setVideo(video_file);
		repaint();
		validate();
	}
	
	public void addSegmentLine (SegmentLine sl) {
		if (isSegmentLineExist(sl.getDataPos())) {
			return;
		}
		segmentlines.add(sl);
		remove(wave_pn);
		add(sl);
		add(wave_pn);
		localWaveRepaint();
		repaint();
		validate();
	}
	
	public void setLocalWaveX(int data_pos) {
		local_wave_pn.setX(data_pos);
	}
	
	public void setSliderPos(int pos) {
		video_sl.setValue(pos);
	}
	
	public void changeIndicatorLine (int x_pos) {
		remove(indi_line);
		indi_line = new IndicatorLine(x_pos);
		remove(wave_pn);
		add(indi_line);
		add(wave_pn);
		repaint();
		validate();
	}
	
	public void removeSegmentLine(SegmentLine sl) {
		for (SegmentLine sl_ : segmentlines) {
			if (sl_.getDataPos().equals(sl.getDataPos())) {
				remove(sl_);
				segmentlines.remove(sl_);
				break;
			}
		}
		remove(sl);
		localWaveRepaint();
		repaint();
		validate();
	}
	
	public void play(int data_pos) {
		video_sl.play(data_pos);
	}
	
	public void loop(SegmentLine sl) {
		Collections.sort(segmentlines, new SegmentLineComp());
		int ind = segmentlines.indexOf(sl);
		int start_pos = sl.getDataPos();
		int end_pos = 0;
		if (ind == segmentlines.size()) {
			end_pos = current_video_data.length;
		} else {
			end_pos = segmentlines.get(ind+1).getDataPos();
		}
		video_sl.loop(start_pos, end_pos);
	}
	
	private void clearSegmentlines() {
		for (SegmentLine sl : segmentlines) {
			remove(sl);
		}
		segmentlines.clear();
	}
	
	public void changeLocalIndiLine(int x_pos) {
		local_indi_line.setBounds(x_pos, Constant.WAVEPANEL_HEIGHT, 
				Constant.SEGMENTLINE_WIDTH, Constant.SEGMENTLINE_HEIGHT);
		remove(local_wave_pn);
		add(local_indi_line);
		add(local_wave_pn);
		repaint();
		validate();
	}
	
	private void loadXML(String xml_file) {
		XMLHandler load_xml = new XMLHandler();
		load_xml.readXML(xml_file);
		Description d = load_xml.getDescription();
		xml_pn.setDescription(d);
		clearSegmentlines();
		if (d.segment_poses.isEmpty()) {
			return;
		}
		String[] seg_poses = d.segment_poses.split(" ");
		for (int i = 0; i < seg_poses.length - 1; ++i) {
			if (seg_poses[i].length() > 1) {
				int data_pos = Integer.valueOf(seg_poses[i]);
				if (data_pos <= 0) {
					continue;
				}
				addSegmentLine(new SegmentLine(data_pos, current_video_data.length, this));
			}
		}
				
		
	}
	
	public void localWaveRepaint() {
		local_wave_pn.setVideo(current_video_data);
		local_wave_pn.setX(local_wave_pn.get_cur_x());
		remove(local_wave_pn);
		remove(local_indi_line);
		add(local_indi_line);
		for (SegmentLine sl : local_segmentLines) {
			remove(sl);
		}
		local_segmentLines.clear();
		int sp = local_wave_pn.get_start_data_pos();
		int ep = local_wave_pn.get_end_data_pos();
		int llen = local_wave_pn.get_local_data_length();
		for (SegmentLine sl : segmentlines) {
			if (sl.getDataPos() > sp && sl.getDataPos() < ep) {
				SegmentLine new_sl = new SegmentLine(sl.getDataPos(), llen, sp, Constant.WAVEPANEL_HEIGHT, this);
				add(new_sl);
				local_segmentLines.add(new_sl);
			}
		}
		add(local_wave_pn);
		repaint();
		invalidate();
	}
	
	public void playCurrentSegment() {
		int cur_x = (int)(((float)video_sl.getValue()) / video_sl.getMaximum() * Constant.WAVEPANEL_WIDTH);
		int start_data_pos = 1;
		int end_data_pos = current_video_data.length;
		Collections.sort(segmentlines, new SegmentLineComp());
		if (!segmentlines.isEmpty()) {
			for (SegmentLine sl : segmentlines) {
				if (sl.getX_pos() > cur_x) {
					int ind = segmentlines.indexOf(sl);
					if (ind == 0) {
						start_data_pos = 1;
					} else {
						start_data_pos = segmentlines.get(ind-1).getDataPos();
					}
					end_data_pos = sl.getDataPos();
					video_sl.loop(start_data_pos, end_data_pos);
					return;
				}
			}
			start_data_pos = segmentlines.get(segmentlines.size()-1).getDataPos();
			end_data_pos = current_video_data.length - 1;
			video_sl.loop(start_data_pos, end_data_pos);
			return;
		}
		video_sl.loop(start_data_pos, end_data_pos);
		return;
	}

	public void displayAllTags() {
		for (int i = 0; i < all_tags.size() ; ++i) {
			remove(all_tags.get(i));
		}
		all_tags.clear();
		repaint();
		invalidate();
		if (current_tag_ind == -1) {
			return;
		}
		
		updateCurrentTags();
		
		int tag_cnt = current_tags.size();
		int tag_each_line = Constant.UI_WIDTH / Constant.BUTTON_WIDTH;
		int label_width = Constant.BUTTON_WIDTH;
		for (int i = 0; i < tag_cnt; i++) {
			JLabel lbl = new JLabel(current_tags.get(i), JLabel.CENTER);
			lbl.setBounds(label_width * (i % tag_each_line), Constant.TAG_LABEL_Y_POS + i / tag_each_line * Constant.BUTTON_HEIGHT, 
					label_width, Constant.BUTTON_HEIGHT);
			lbl.setVisible(true);
			lbl.setBorder(BorderFactory.createLineBorder(Color.BLACK));
			lbl.addMouseListener(new TagLabel2Segment(i, this));
			add(lbl);
			all_tags.add(lbl);
		}
	}
	
	public void onTagLabelClick(int lbl_ind) {
		
		Collections.sort(segmentlines, new SegmentLineComp());
		
		int tag_ind = lbl_ind;
		if (tag_ind >= current_tags.size()) {
			return;
		}
		int x_pos = 0;
		if (tag_ind == 0) {
			x_pos = segmentlines.get(tag_ind).getX_pos()/2;
		} else if (tag_ind == segmentlines.size()) {
			x_pos = (Constant.WAVEPANEL_WIDTH + segmentlines.get(tag_ind - 1).getX_pos() )/2;
		} else {
			x_pos = (segmentlines.get(tag_ind).getX_pos() + segmentlines.get(tag_ind - 1).getX_pos() )/2;
		}
		
		video_sl.setValue((int)((float)x_pos / Constant.WAVEPANEL_WIDTH * video_sl.getMaximum()));
	}
}

class TagLabel2Segment implements MouseListener {

	private int lbl_ind;
	private LabelWavUI ui;
	
	 public TagLabel2Segment(int lbl_ind, LabelWavUI ui) {
		 this.lbl_ind = lbl_ind;
		 this.ui = ui;
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
		ui.onTagLabelClick(lbl_ind);
		
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		
	}
	
}

class SegmentLineComp implements Comparator<SegmentLine> {

	@Override
	public int compare(SegmentLine sl1, SegmentLine sl2) {
		if (sl1.getDataPos() > sl2.getDataPos()) {
			return 1;
		} else if (sl1.getDataPos() == sl2.getDataPos()) {
			return 0;
		}
		return -1;
	}


	
}