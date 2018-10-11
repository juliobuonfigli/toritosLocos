package ToritosLocos;

import javax.swing.*;

import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;


public class mainToritos {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//instancio este objeto tt solo para poder usar el metodo getImage 
		 double[][] yy = new double[10][2];
		 Toolkit tt=Toolkit.getDefaultToolkit();
		 Image icono;
		 toritosMainDialog md=new toritosMainDialog();
	     icono=tt.getImage("Clipboard6.png");   
	     md.setIconImage(icono);
		 md.setBounds(260, 120, 450, 340);
	     md.setVisible(true);
	     //yy=md.obtenerMatris();
	     //System.out.println(yy[0][0]);
	
	}

}
