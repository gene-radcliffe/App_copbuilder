package com.Radcliffe.copbuilder_app;
import javax.swing.UIManager;

import java.lang.*;
import com.radcliffe.utilities.*;


import com.sun.java.swing.plaf.windows.WindowsLookAndFeel;
import javax.swing.*;
/**
 * Hello world!
 *
 */
public class App 
{
	public static void main(String[] a){
		
		try{
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		}catch(UnsupportedLookAndFeelException e){
			System.out.println("error setting look and feel");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		java.awt.EventQueue.invokeLater(new Runnable(){
			
			

			@Override
			public void run() {
				// TODO Auto-generated method stub
				MainApplication ma = new MainApplication();
			}
		});

	}
}
