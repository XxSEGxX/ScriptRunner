package me.seg.scriptrunner;

import java.awt.Dimension;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Map;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JScrollPane;

public class GUI {

	private final Map<String, ImageIcon> imageMap;
	
	public GUI(String[] texts, String[] scripts, Map<String, ImageIcon> imageMap) {
		this.imageMap = imageMap;
		JList<String> list = new JList<String>(texts);
		list.setCellRenderer(new ImageListRenderer(this));
		list.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int index = list.getSelectedIndex();
				exec(scripts, index);
			}
		});
		
		list.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == 10) {
					int index = list.getSelectedIndex();
					exec(scripts, index);
				}
			}
		});
		
		JScrollPane scroll = new JScrollPane(list);
		scroll.setPreferredSize(new Dimension(800, 800));
		JFrame frame = new JFrame();
		frame.add(scroll);
		ImageIcon icon = new ImageIcon(getClass().getResource("icon"));
		frame.setIconImage(icon.getImage());
		frame.setTitle("ScriptRunner v1.0 - SEG");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.pack();
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}
	
	private void exec(String[] scripts, int index) {
		new Thread(new Runnable() {
			public void run() {
				try {
					Process proc = new ProcessBuilder(scripts[index].split("\\s+")).start();
					try {
						proc.waitFor();
					} catch (InterruptedException e1) {
						e1.printStackTrace();
					}
					BufferedReader r = new BufferedReader(new InputStreamReader(proc.getInputStream()));
					String out = "";
					String line;
					while ((line = r.readLine()) != null) {
						out += line + "\n";
					}
					System.out.println(out);
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		}).start();
	}
	
	public Map<String, ImageIcon> getImageMap() {
		return imageMap;
	}
}
