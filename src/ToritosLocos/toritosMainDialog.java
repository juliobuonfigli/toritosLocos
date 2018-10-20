package ToritosLocos;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import java.awt.event.*;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import com.opencsv.*;

public class toritosMainDialog extends JFrame implements ActionListener, ChangeListener {

  private double[][] prices;
  private JLabel eCSV, eError;
  private JButton bCSV, bAceptar, bCancelar;
  private JCheckBox cbMLPS, cbMLPC, cbMLPP, cbMLPMM, cbFNNP, cbFNNIO, cbPNN, cbFP;
  private boolean MLPS, MLPC, MLPP, MLPMM, FNNP, FNNIO, PNN, FP;
  private boolean flag=false;

  public toritosMainDialog() 
	  { 
      MLPS=MLPC=MLPP=MLPMM=FNNP=FNNIO=PNN=FP=false;
	  setLayout(null);
      setTitle("Toritos inteligentes :)");
      //Etiquetas
      eError=new JLabel("");
      eError.setBounds(50,220,200,30);
      add(eError);
      eCSV=new JLabel("Null");
      eCSV.setBounds(260,10,100,30);
      add(eCSV);
      //checkboxes
      cbMLPS=new JCheckBox("Perceptron simple");
      cbMLPS.setBounds(10, 60, 200, 15);
      cbMLPS.addChangeListener(this); add(cbMLPS);
	  cbMLPC=new JCheckBox("Perceptron clasificador");
      cbMLPC.setBounds(10, 80, 200, 15);
      cbMLPC.addChangeListener(this); add(cbMLPC);
	  cbMLPP=new JCheckBox("Perceptron predictor");
      cbMLPP.setBounds(10, 100, 200, 15);
      cbMLPP.addChangeListener(this); add(cbMLPP);
	  cbMLPMM=new JCheckBox("Perceptron de medias móviles");
      cbMLPMM.setBounds(10, 120, 200, 15);
      cbMLPMM.addChangeListener(this); add(cbMLPMM);
	  cbFNNP=new JCheckBox("Red Difusa predictora");
      cbFNNP.setBounds(10, 140, 200, 15);
      cbFNNP.addChangeListener(this); add(cbFNNP);
      cbFNNIO=new JCheckBox("Red Difusa de Indicadores y Osciladores");
      cbFNNIO.setBounds(10, 160, 300, 15);
      cbFNNIO.addChangeListener(this); add(cbFNNIO);
	  cbPNN=new JCheckBox("Red Neuronal probabilística");
      cbPNN.setBounds(10, 180, 200, 15);
      cbPNN.addChangeListener(this); add(cbPNN);
      cbFP=new JCheckBox("Funciones de peso generales");
      cbFP.setBounds(10, 200, 200, 15);
      cbFP.addChangeListener(this); add(cbFP);
      //activo el que anda
      cbMLPP.setSelected(true);
      // Botones
      bCSV=new JButton("Buscar serieDeTiempo.csv");
      bCSV.setBounds(10,10,200,30);
      add(bCSV); bCSV.addActionListener(this);  
      bAceptar=new JButton("Aceptar");
      bAceptar.setBounds(10,240,90,30);
      add(bAceptar);
      bAceptar.addActionListener(this);
      bCancelar=new JButton("Cancelar");
      bCancelar.setBounds(120,240,90,30);
      add(bCancelar);
      bCancelar.addActionListener(this);
      }
  
  public double[][] getMatrix(JLabel etiqueta)
	   {
	   CSVReader cr;
	   String[][] sP = new String[10][2];
	   double[][] dP = new double[10][2];
	   JFileChooser chooserCSV = new JFileChooser();
       chooserCSV.setCurrentDirectory(new java.io.File("."));
       chooserCSV.setDialogTitle("Cargar serie de tiempo");
       chooserCSV.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
       chooserCSV.setAcceptAllFileFilterUsed(false);
        //chooserImagen.showOpenDialog(null);
       int status = chooserCSV.showOpenDialog(null);
       File file = chooserCSV.getSelectedFile();                   
	   if(status == JFileChooser.APPROVE_OPTION) 
			{
		    etiqueta.setText(file.getName());
		    try
				{
                cr = new CSVReader(new FileReader(file)); 
                for(int i=0; i<10; i++)
					{
					sP[i] = cr.readNext();
					for(int j=0; j<2; j++)
						dP[i][j]=Double.parseDouble(sP[i][j]);
					}	
				 }
            catch(IOException c) 
				{
                System.out.println(c.getMessage());
				}
			}
		return dP;
		}
	
  @Override
  public void actionPerformed(ActionEvent e)
		{
        if(e.getSource()==bCSV) 
			prices=getMatrix(eCSV);
		
        if(e.getSource()==bAceptar) 
			{
          if(prices==null)
				{
				eError.setText("Cargar serie de tiempo");
				//acá tengo que abrir mensaje de swing
				}
          else
				{
                System.out.println(prices[0][0]);
        	    flag=true;
                System.exit(0);
				}
			}
      if(e.getSource()==bCancelar) 
          System.exit(0);
		}
  
  @Override
  public void stateChanged(ChangeEvent ce) 
  	  {
 	  MLPS=cbMLPS.isSelected();
	  MLPC=cbMLPC.isSelected(); 
	  MLPP=cbMLPP.isSelected();
	  MLPMM=cbMLPMM.isSelected();
	  FNNP=cbFNNP.isSelected();
	  FNNIO=cbFNNIO.isSelected();
	  PNN=cbPNN.isSelected();
	  FP=cbFP.isSelected();
  	  }

  public double[][] obtenerMatris() {
	return prices;
}

}

