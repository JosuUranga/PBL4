package dialogos;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Container;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JTextField;

import modelos.UsuarioVO;
import otros.TextPrompt;

@SuppressWarnings("serial")
public class DialogoTarjeta extends JDialog{
	
	DialogoUsuario ventana;
	List<String>listaPalabras;
	JTextField tarjeta, fecha, CVC;
	Font fuenteTituloInfoGeneral=new Font("Tahoma",Font.BOLD,14);
	boolean editando=false;
	UsuarioVO user;
	JButton botonOK,botonCancelar;
	
	public DialogoTarjeta (DialogoUsuario dialogoUsuario, String titulo, boolean modo, List<String> list,UsuarioVO user) {
		super(dialogoUsuario,titulo,modo);
		this.listaPalabras=list;
		this.ventana=dialogoUsuario;
		this.setSize(600,275);
		this.setLocation (600,200);
		this.user=user;
		this.setContentPane(crearPanelDialogo());
		this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);		
		this.setVisible(true);
	}	
	

	private Container crearPanelDialogo() {
		JPanel panel = new JPanel (new BorderLayout(0,20));
		panel.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
		panel.add(crearPanelDatos(),BorderLayout.CENTER);
		panel.add(crearPanelBotones(),BorderLayout.SOUTH);
		return panel;
	}
	
	private Component crearPanelDatos() {
		JPanel panel = new JPanel (new GridLayout(2,1,0,20));
		panel.setBorder(BorderFactory.createEmptyBorder(30, 30, 10, 30));

		panel.add(crearTextField(tarjeta=new JTextField(), listaPalabras.get(56)));
		panel.add(crearPanelDatos2());

		return panel;
	}
	

	private Component crearPanelDatos2() {
		JPanel panel = new JPanel(new GridLayout(1,2,20,0));

		panel.add(crearTextField(fecha=new JTextField(), listaPalabras.get(57)));
		panel.add(crearTextField(CVC=new JTextField(), listaPalabras.get(58)));
		
		return panel;
	}
	
	
	private Component crearTextField(JTextField text, String titulo) {
		JPanel panel = new JPanel(new GridLayout(1,1));
		TextPrompt placeholder=new TextPrompt(titulo,text);
		placeholder.changeAlpha(0.75f);
		placeholder.changeStyle(Font.ITALIC);
		panel.add(text);
		return panel;
	}
	
	private Component crearPanelBotones() {
		JPanel panel = new JPanel(new GridLayout(1,2,20,0));
		panel.setBorder(BorderFactory.createEmptyBorder(10, 30, 10, 30));
		botonOK = new JButton ("OK");

		botonOK.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				
			}
		
		});
		panel.add(botonOK);
		
		botonCancelar = new JButton (listaPalabras.get(39));
	
		botonCancelar.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
					DialogoTarjeta.this.dispose();
			}
			
		});
		panel.add(botonCancelar);
		return panel;
	}	

}

