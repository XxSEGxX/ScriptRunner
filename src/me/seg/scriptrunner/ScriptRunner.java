package me.seg.scriptrunner;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import javax.swing.ImageIcon;
import javax.swing.SwingUtilities;

public class ScriptRunner {

	public static void main(String[] args) throws FileNotFoundException {
		if (args.length > 0) {
			switch (args[0]) {
			case "-h":
			case "-help":
			case "--help":
			case "--h":
			case "/h":
				List<String> lines = new ArrayList<>();
				if (args.length > 1 && args[1].equals("es")) {
					lines.add("Pasos para crear una nueva entrada:");
					lines.add("  1. Crea una carpeta en el directorio \"scripts\"");
					lines.add("     El nombre es el texto que se va a mostrar");
					lines.add("  2. Coloca una imagen \"image.png\" dentro");
					lines.add("     Esta será el icono y mantendrá su tamaño original");
					lines.add("  3. Coloca un archivo \"script.txt\" dentro");
					lines.add("     El archivo debe contener el comando a ejecutar");
					lines.add("     El comando debe iniciar");
					lines.add("       En Windows con: \"cmd \\c \"");
					lines.add("       En Linux con: \"/bin/bash -c \"");
				} else {
					lines.add("Steps to create a new entry:");
					lines.add("  1. Make a folder in the \"scripts\" directory");
					lines.add("     The name of folder is the text to be displayed");
					lines.add("  2. Place an image \"image.png\" inside");
					lines.add("     The image will be the icon and will keep its size");
					lines.add("  3. Place an file \"script.txt\" inside");
					lines.add("     The file must contain the command to be executed");
					lines.add("     The command must start");
					lines.add("       In Windows with: \"cmd \\c \"");
					lines.add("       In Linux with: \"/bin/bash -c \"");
				}
				for (String line: lines) {
					System.out.println(line);
				}
				return;
			}
		}
		System.out.println("Runner V1.0 Maded by SEG");
		System.out.println("https://github.com/XxSEGxX/");
		System.out.println("");
		File mainFolder = new File("scripts");
		if (!mainFolder.exists()) {
			mainFolder.mkdir();
			System.err.println("Scripts missing!");
			System.exit(0);
		}
		List<String> texts = new ArrayList<>();
		List<String> scripts = new ArrayList<>();
		Map<String, ImageIcon> imagesMap = new HashMap<>();
		for (File folder : mainFolder.listFiles()) {
			if (folder.isDirectory()) {
				String path = folder.getAbsolutePath().replaceAll("[\\\\]", "/");
				texts.add(folder.getName());
				imagesMap.put(folder.getName(), new ImageIcon(path+"/image.png"));
				File scriptFile = new File(path+"/script.txt");
				Scanner scanner = new Scanner(scriptFile);
				scripts.add(scanner.nextLine());
				scanner.close();
			}
		}
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				String[] textsArray = new String[texts.size()];
				for (int i = 0; i < textsArray.length; i++) {
					textsArray[i] = texts.get(i);
				}
				String[] scriptsArray = new String[scripts.size()];
				for (int i = 0; i < scriptsArray.length; i++) {
					scriptsArray[i] = scripts.get(i);
				}
				new GUI(textsArray, scriptsArray, imagesMap);
			}
		});
	}
	
}
