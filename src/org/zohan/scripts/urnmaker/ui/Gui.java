package org.zohan.scripts.urnmaker.ui;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.JTextPane;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

import org.zohan.scripts.urnmaker.UrnMaker;
import org.zohan.scripts.urnmaker.data.Urn;

public class Gui extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6635555744310175933L;
	private JPanel contentPane;
	private Urn urn = null;
	
	/**
	 * Create the frame.
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public Gui(final UrnMaker um) {
		setVisible(true);
		setTitle("Urn Maker");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 538, 332);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblUrnMakerBy = new JLabel("Urn Maker by Zohan");
		lblUrnMakerBy.setBackground(Color.WHITE);
		lblUrnMakerBy.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblUrnMakerBy.setBounds(10, 11, 502, 37);
		contentPane.add(lblUrnMakerBy);
		
		JLayeredPane layeredPane = new JLayeredPane();
		layeredPane.setBorder(new TitledBorder(null, "Instructions", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		layeredPane.setBounds(10, 58, 500, 174);
		contentPane.add(layeredPane);
		
		JTextPane txtpnbeforeStarting = new JTextPane();
		txtpnbeforeStarting.setEditable(false);
		txtpnbeforeStarting.setText("----Before Starting----\r\n1. Start near the Varrock East bank\r\n2. Have plenty of resources in your bank\r\n\r\n----GUI----\r\n1. Select Mode (Currently Disabled)\r\n2. Select Item\r\n3. Press Start\r\n");
		txtpnbeforeStarting.setBounds(10, 22, 480, 141);
		layeredPane.add(txtpnbeforeStarting);
		
		JLabel lblMode = new JLabel("Mode:");
		lblMode.setBounds(10, 243, 46, 14);
		contentPane.add(lblMode);
		
		JComboBox selectMode = new JComboBox();
		selectMode.setEnabled(false);
		selectMode.setModel(new DefaultComboBoxModel(new String[] {"Mould + Cook"}));
		selectMode.setBounds(10, 264, 148, 20);
		contentPane.add(selectMode);
		
		JLabel lblItem = new JLabel("Item:");
		lblItem.setBounds(185, 243, 46, 14);
		contentPane.add(lblItem);
		
		final JComboBox selectItem = new JComboBox();
		selectItem.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) {
				urn = (Urn) selectItem.getSelectedItem();
			}
		});
		selectItem.setModel(new DefaultComboBoxModel(Urn.values()));
		selectItem.setBounds(185, 264, 148, 20);
		urn = (Urn) selectItem.getSelectedItem();
		contentPane.add(selectItem);
		
		
		JButton btnStart = new JButton("Start");
		btnStart.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				um.startScript(Gui.this);
				dispose();
			}
		});
		btnStart.setBounds(364, 263, 148, 23);
		contentPane.add(btnStart);
	}
	
	
	public Urn urn () {
		return urn;
	}
}
