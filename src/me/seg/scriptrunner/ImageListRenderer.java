package me.seg.scriptrunner;

import java.awt.Component;
import java.awt.Font;

import javax.swing.DefaultListCellRenderer;
import javax.swing.JLabel;
import javax.swing.JList;

public class ImageListRenderer extends DefaultListCellRenderer {

	private static final long serialVersionUID = 4301982576546751643L;
	
	private Font font = new Font("Arial", Font.BOLD, 24);
	private GUI main;
	
	public ImageListRenderer(GUI main) {
		this.main = main;
	}
	
	@Override
	public Component getListCellRendererComponent(
			JList<?> list, Object value,int index,
			boolean isSelected, boolean cellHasFocus
	) {
		JLabel label = (JLabel)super.getListCellRendererComponent(list, "    "+value, index, isSelected, cellHasFocus);
		label.setIcon(main.getImageMap().get((String) value));
		label.setHorizontalAlignment(JLabel.LEFT);
		label.setFont(font);
		return label;
	}
}
