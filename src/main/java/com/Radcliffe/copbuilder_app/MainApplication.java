package com.Radcliffe.copbuilder_app;
import com.radcliffe.utilities.*;



import java.awt.event.ActionEvent;
import javax.swing.event.MenuEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.WindowListener;
import javax.swing.*;
import javax.swing.border.*;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Event;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.io.File;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;

import java.io.*;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JFileChooser;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.KeyStroke;
import javax.swing.JList;
import javax.swing.JTextArea;
import javax.swing.JTextField;
//Layout
import javax.swing.GroupLayout;

import java.io.FilenameFilter;

import javax.xml.*;
import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

import com.Radcliffe.copbuilder_app.controls.ThumbNailCanvas;
import com.Radcliffe.copbuilder_app.controls.ThumbNailListener;
import com.Radcliffe.copbuilder_app.controls.ThumbNailLoadEvent;
import com.Radcliffe.copbuilder_app.controls.ThumbNailViewEvent;
import java.awt.event.MouseMotionListener;
import java.awt.event.WindowEvent;
public class MainApplication extends JFrame implements ActionListener, ThumbNailListener{
	
	

	{
		
		
		
	}
	
	public MainApplication(){
		imageFiles = new ArrayList<AppImages>();
		mImageCanvas = new MainImageCanvas();
		tnc = new ThumbNailCanvas(100,fDim.width);
		
		tnc.addThumbNailListener(this);
		/*
		 * work on getting the app path
		 */
		workingDir = new File(System.getProperty("user.home")+"/Desktop");
		// Error Logger
	//	log = new Logger(workingDir, "ThumbNailCanvas.log");
		JFileCh = new JFileChooser();
		createControlPanel();
		createMenu();
		createWindow();	
	
	}
	/* 
	 * Creates the bottom panel
	 */
	private void createControlPanel(){
		Border border = BorderFactory.createLineBorder(Color.BLUE);
		controlPane = new ControlPanel();
		controlPane.setBorder(border);
		controlPane.setSize(jDim);
		controlPane.setPreferredSize(jDim);
		controlPane.setMaximumSize(jDim);
		controlPane.setMinimumSize(jDim);
		
		
	}
	/* 
	 * Creates the menu bar
	 */
	private void createMenu(){
	
	menuBar = new JMenuBar();
	menuFile = new JMenu("File");
	menuFile.setMnemonic(KeyEvent.VK_F);
	menuBar.add(menuFile);
	
	//set keystrokes and listeners
	menuItem = menuFile.add(new JMenuItem("Open Directory", 'O'));
	menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, Event.CTRL_MASK));
	menuItem.addActionListener(this);
	menuItem = menuFile.add(new JMenuItem("Rename Files", 'R'));
	menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_R, Event.CTRL_MASK));

	menuItem.addActionListener(this);
	
	menuFile.add(menuItem);
	
	this.setJMenuBar(menuBar);
	//menuItem.addActionListener(new FileMenuListener());
	
	}
	
	/*
	 * Adds all the panels and controls and load main form
	 */
	
	private void createWindow(){
		
		layout = new BoxLayout(this.getContentPane(), BoxLayout.Y_AXIS);
		
		
		this.setLayout(layout);
		this.add(mImageCanvas);
		this.add(tnc);
		this.add(controlPane);
		this.addWindowListener(new WindowListener(){

			@Override
			public void windowActivated(WindowEvent arg0) {
				// TODO Auto-generated method stub
				repaint();
			}

			@Override
			public void windowClosed(WindowEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void windowClosing(WindowEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void windowDeactivated(WindowEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void windowDeiconified(WindowEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void windowIconified(WindowEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void windowOpened(WindowEvent arg0) {
				// TODO Auto-generated method stub
				mImageCanvas.repaint();
			}
			
		});
		
		this.setTitle("Closeout Package Master");
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setResizable(false);
		this.setSize(fDim);
		this.setVisible(true);
		

	
	
		
	}
	
	// ********************
	// *    Menu Items    * 
	// ********************
	/**
	 * menuitem actionlistener
	 */

		public void actionPerformed(ActionEvent e){
			
			switch(e.getActionCommand()){
			
			case "Open Directory":
					
			
								JFileCh.setCurrentDirectory(workingDir);
								JFileCh.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
								
								/*
								 * Constraint the selection. Set the file filter for jpg and png. Override it anonymously
								 */
								FileFilter Ffilter = new FileFilter(){
					
									@Override
									public boolean accept(File f) {
										// TODO Auto-generated method stub
										if(f.isDirectory()) return true;
										else if (f.getName().endsWith(".jpg")) return true;
										else if (f.getName().endsWith(".png")) return true;
										else return false;
										
									}
					
									@Override
									public String getDescription() {
										// TODO Auto-generated method stub
										return "Image Files";
									}
									
								};
								
								/*
								 * Constraint the files to be loaded 
								 */
								
								FilenameFilter filenamefilter = new FilenameFilter(){

									@Override
									public boolean accept(File dir, String name) {
										// TODO Auto-generated method stub
										String lowercase = name.toLowerCase();
										if(lowercase.endsWith(".jpg")==true){
											return true;
										}else{
											return false;
										}
									}
									
								};
								/** 
								 * set the filefilter
								 */
								
								JFileCh.setAcceptAllFileFilterUsed(false);
								JFileCh.setFileFilter(Ffilter);
		
								
								if (JFileCh.showOpenDialog(menuItem) == JFileChooser.APPROVE_OPTION  ){
								
									SelectedDir = JFileCh.getSelectedFile();
									
									/*
									 * set the file name filter to load only JPEGS
									 */
									
									Filenames = SelectedDir.listFiles(filenamefilter);
								
								//////////////	
							
									for(File str: Filenames){
										
										AppImages aimg = new AppImages(str.toString());
										
										imageFiles.add(aimg);
									}
						
									tnc.loadImages(imageFiles);
									
									
								//////////////////////////////////	
																		
								}else{
								
								}
									repaint();
									break;
									
			
			case "Rename Files":
				break;
			
			
			}
		
	}


		/*
		 * (non-Javadoc)
		 * @see com.Radcliffe.copbuilder_app.controls.ThumbNailListener#ThumbNailListener(com.Radcliffe.copbuilder_app.controls.ThumbNailViewEvent)
		 */
	public void ThumbNailListener(ThumbNailViewEvent tnve) {
		// TODO Auto-generated method stub
		
		switch(tnve.getButtonPressed()){
	
		
		case NEXT: 
			repaint();
	
			
			
//			mImageCanvas.setImage(tnc.getBufferedImage(imageIndex)); 
			mImageCanvas.setImage(tnve.getFileName()); 
			System.out.println(tnve.getImage() + " index " + tnve.getIndex());
			controlPane.setFileName(tnve.getFileName());
			
			break;
		case PREVIOUS:
			repaint();
			
			
			System.out.println(tnve.getImage() + " index " + tnve.getIndex());
			//mImageCanvas.setImage(tnc.getBufferedImage(imageIndex));
			mImageCanvas.setImage(tnve.getFileName());
			
			controlPane.setFileName(tnve.getFileName());
			break;
		default:
			
			
			break;
		}
		
	}
	/*
	 * (non-Javadoc)
	 * @see com.Radcliffe.copbuilder_app.controls.ThumbNailListener#ThumbNailLoadListener(com.Radcliffe.copbuilder_app.controls.ThumbNailLoadEvent)
	 */
	@Override
	public void ThumbNailLoadListener(ThumbNailLoadEvent tnle) {
		// TODO Auto-generated method stub
		mImageCanvas.setImage(tnle.getFile());
		controlPane.setFileName(tnle.getFile());
	}
	/**
	 * 
	 */
	Toolkit toolkit = Toolkit.getDefaultToolkit();
	private Dimension fDim = new Dimension(toolkit.getScreenSize());
	private Dimension jDim = new Dimension(1920,150);
	private String Directory;
	private FileFilter Ffilter;
	/*
	 * Logger
	 */
	//private Logger log;
	
	private File[] Filenames;
	private JFileChooser JFileCh;
	private File workingDir;
	private File SelectedDir;
	private JMenu menuFile;
	private JMenuItem menuItem;
	private JMenuBar menuBar;
	private GroupLayout groupLayout;
	private BoxLayout layout;
	// ********************
	// *     CANVASES     *
	// ********************
	private ThumbNailCanvas tnc; // Thumbnail canvas, the control that lets you scroll through items
	private MainImageCanvas mImageCanvas; // this is the main canvas where the main image will appear
	private ControlPanel controlPane; // this is the bottom panel
	// 
	List<AppImages> imageFiles;
	private int imageIndex =1;
	
}




