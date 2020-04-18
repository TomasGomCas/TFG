package lanzador;

import java.awt.EventQueue;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.filechooser.FileSystemView;

import modelo.Application;

/**
 * The Class Main. Encargada de lanzar el programa
 */
public class Main extends JFrame {

	/** The content pane. */
	private JPanel contentPane;

	/** The texto entrada. */
	private JLabel textoEntrada;

	/** The texto salida. */
	private JLabel textoSalida;

	/** The ruta entrada. */
	private String rutaEntrada;

	/** The ruta salida. */
	private String rutaSalida;

	/** The file chooser entrada. */
	private JFileChooser fileChooserEntrada;

	/** The file chooser salida. */
	private JFileChooser fileChooserSalida;

	/**
	 * Launch the application.
	 *
	 * @param args the arguments
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Main frame = new Main();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Main() {

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(50, 50, 500, 100);

		// Fila Entrada
		textoEntrada = new JLabel();
		textoEntrada.setText("Ninguna seleccionada");

		JButton botonEntrada = new JButton("Seleccione ruta excel de entrada");
		botonEntrada.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				fileChooserEntrada = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
				fileChooserEntrada.showOpenDialog(Main.this);
				rutaEntrada = fileChooserEntrada.getSelectedFile().getAbsolutePath();
				textoEntrada.setText(rutaEntrada);
			}
		});

		// Fila Salida
		textoSalida = new JLabel();
		textoSalida.setText("Ninguna Seleccionada");

		JButton botonSalida = new JButton("Seleccione carpeta de salida");
		botonSalida.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				fileChooserSalida = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
				fileChooserSalida.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
				fileChooserSalida.showOpenDialog(Main.this);
				rutaSalida = fileChooserSalida.getSelectedFile().getAbsolutePath();
				textoSalida.setText(rutaSalida);
			}
		});

		// Fila Generar
		JButton botonGenerar = new JButton("Generar");
		botonGenerar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					Application.getInstance().generateProgramSpring(rutaEntrada, rutaSalida);
					JOptionPane.showMessageDialog(contentPane, "Codigo generado correctamente");
				} catch (Exception e1) {
					JOptionPane.showMessageDialog(contentPane,
							"Error en la generacion de codigo, por favor revise las reglas de utilizacion // Traza: "
									+ e1.toString());
					e1.printStackTrace();
				}
			}
		});

		contentPane = new JPanel(new GridLayout(3, 1));
		contentPane.add(botonEntrada);
		contentPane.add(textoEntrada);
		contentPane.add(botonSalida);
		contentPane.add(textoSalida);
		contentPane.add(botonGenerar);

		setContentPane(contentPane);
	}

}
