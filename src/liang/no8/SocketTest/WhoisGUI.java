package liang.no8.SocketTest;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Container;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Frame;
import java.awt.GridBagConstraints;
import java.awt.GridLayout;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.UnknownHostException;
import java.util.concurrent.ExecutionException;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingWorker;
import javax.swing.WindowConstants;

public class WhoisGUI extends JFrame{
	private JTextField searchString=new JTextField(30);
	private JTextArea names=new JTextArea(15,80);
	private JButton findButton=new JButton("Find");
	private ButtonGroup searchIn=new ButtonGroup();
	private ButtonGroup searchFor=new ButtonGroup();
	private JCheckBox exactMatch =new JCheckBox("Exact Match",true);
	private JTextField chosenServer=new JTextField();
	private Whois server;
	
	public WhoisGUI(Whois whois) {
		super("Whois");
		this.server=whois;
		Container pane=this.getContentPane();
		
		Font f=new Font("Monospaced",Font.PLAIN,12);
		names.setFont(f);
		names.setEnabled(false);
		
		JPanel centerPanel=new JPanel();
		centerPanel.setLayout(new GridLayout(1, 1, 10, 10));
		JScrollPane jsp=new JScrollPane(names);
		centerPanel.add(jsp);
		pane.add("Center",centerPanel);
		
		//不希望南边和北边的按钮占满整个区域
		//所以在那里添加Panel
		//并在Panel 中使用 FlowLayout
		JPanel northPanel=new JPanel();
		JPanel northPanelTop=new JPanel();
		northPanelTop.setLayout(new FlowLayout(FlowLayout.LEFT));
		northPanelTop.add(new JLabel("Whois: "));
		northPanelTop.add("North",searchString);
		northPanelTop.add(exactMatch);
		northPanelTop.add(findButton);
		
		northPanel.setLayout(new BorderLayout(2,1));
		northPanel.add("North",northPanelTop);
		JPanel northJPanelBottom=new JPanel();
		northJPanelBottom.setLayout(new GridLayout(1,3,5,5));
		northJPanelBottom.add(initRecordType());
		northJPanelBottom.add(initSearchFields());
		northJPanelBottom.add(initServerChoise());
		northPanel.add("Center", northJPanelBottom);
		
		pane.add("North",northPanel);
		
		ActionListener al=new LookupNames();
		findButton.addActionListener(al);
		searchString.addActionListener(al);
	}
	
	private JPanel initRecordType() {
		JPanel p=new JPanel();
		p.setLayout(new GridLayout(6,2,5,2));
		p.add(new JLabel("Search for:"));
		p.add(new JLabel(""));
		JRadioButton any=new JRadioButton("Any",true);
		any.setActionCommand("Any");
		searchFor.add(any);
		p.add(any);
		
		p.add(this.makeRadioButton("Network"));
		p.add(this.makeRadioButton("Person"));
		p.add(this.makeRadioButton("Host"));
		p.add(this.makeRadioButton("Domain"));
		p.add(this.makeRadioButton("Organization"));
		p.add(this.makeRadioButton("Group"));
		p.add(this.makeRadioButton("Gateway"));
		p.add(this.makeRadioButton("ASN"));
		return p;
	}
	private JRadioButton makeRadioButton(String label) {
		JRadioButton button=new JRadioButton(label,false);
		button.setActionCommand(label);
		searchFor.add(button);
		return button;
	}

	private JPanel initSearchFields() {
		JPanel p=new JPanel();
		p.setLayout(new GridLayout(6, 1, 5, 2));
		p.add(new JLabel("Search In: "));
		
		JRadioButton all=new JRadioButton("All", true);
		all.setActionCommand("All");
		searchIn.add(all);
		p.add(all);
		
		p.add(this.makeSearchInRadioButton("Name"));
		p.add(this.makeSearchInRadioButton("Mailbox"));
		p.add(this.makeSearchInRadioButton("Handle"));
		return p;
	}
	private JRadioButton makeSearchInRadioButton(String label){
		JRadioButton button=new JRadioButton(label,false);
		button.setActionCommand(label);
		searchIn.add(button);
		return button;
	}
	
	private JPanel initServerChoise() {
		final JPanel p=new JPanel();
		p.setLayout(new GridLayout(6,1,5,2));
		p.add(new JLabel("Search At:"));
		
		chosenServer.setText(server.getHost().getHostName());
		p.add(chosenServer);
		
		chosenServer.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					server=new Whois(chosenServer.getText());
				} catch (UnknownHostException e1) {
					JOptionPane.showMessageDialog(p, e1.getMessage(),"Alert",JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		
		return p;
	}

	
	private class LookupNames implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			names.setText("");
			SwingWorker<String, Object> worker=new Lookup();
			worker.execute();
		}
	}
	private class Lookup extends SwingWorker<String, Object>{

		@Override
		protected String doInBackground() throws Exception {
			Whois.SearchIn group=Whois.SearchIn.ALL;
			Whois.SearchFor category=Whois.SearchFor.ANY;
			
			String searchForLabel=searchFor.getSelection().getActionCommand();
			String searchInLabel=searchIn.getSelection().getActionCommand();
			
			if(searchInLabel.equals("Name")){
				group=Whois.SearchIn.NAME;
			}else if(searchInLabel.equals("Mailbox")){
				group=Whois.SearchIn.MAILBOX;
			}else if(searchInLabel.equals("Handle")){
				group=Whois.SearchIn.HANDLE;
			}
			
			
			if(searchForLabel.equals("Network")){
				category=Whois.SearchFor.NETWORK;
			}else if(searchForLabel.equals("Person")){
				category=Whois.SearchFor.PERSON;
			}else if(searchForLabel.equals("Host")){
				category=Whois.SearchFor.HOST;
			}else if(searchForLabel.equals("Domain")){
				category=Whois.SearchFor.DOMAIN;
			}else if(searchForLabel.equals("Organization")){
				category=Whois.SearchFor.ORGANIZATION;
			}else if(searchForLabel.equals("Group")){
				category=Whois.SearchFor.GROUP;
			}else if(searchForLabel.equals("Gateway")){
				category=Whois.SearchFor.GATEWAY;
			}else if(searchForLabel.equals("ASN")){
				category=Whois.SearchFor.ASN;
			}
			server.setHost(chosenServer.getText());
			
			return server.lookUpNames(searchString.getText(), category, group, exactMatch.isSelected());
		}

		@Override
		protected void done() {
			try {
				names.setText(get());
			} catch (Exception e) {
				JOptionPane.showMessageDialog(WhoisGUI.this, e.getMessage(),"Lookup Failed",JOptionPane.ERROR_MESSAGE);
			}
		}
	}
	
	public static void main(String[] args) {
		try {
			Whois server=new Whois();
			WhoisGUI a =new WhoisGUI(server);
			a.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
			a.pack();
			EventQueue.invokeLater(new FrameShower(a));
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	private static class FrameShower implements Runnable{

		private final Frame frame;
		public FrameShower(Frame frame) {
			this.frame=frame;
		}
		
		@Override
		public void run() {
			frame.setVisible(true);
		}
		
	}
}
