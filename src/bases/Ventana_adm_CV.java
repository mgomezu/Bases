package bases;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class Ventana_adm_CV extends JFrame{
    JPanel panel;
    JLabel crearVuelo;
    JLabel vuelo;
    JLabel fechaS;
    JLabel fechaLl;
    JLabel ruta;
    JLabel tripulacion;
    JLabel avion;
    JButton crear;
    JButton volver;
    JTextField vueloT;
    JTextField rutaT;
    JTextField tripT;
    JTextField avionT;
    JComboBox fechaCDCB;
    JComboBox fechaCMCB;
    JComboBox fechaCACB;
    JComboBox fechaLlDCB;
    JComboBox fechaLlMCB;
    JComboBox fechaLlACB;
    Base_De_Datos BD;
    
    public Ventana_adm_CV(Base_De_Datos BD){
        setSize(400,400);
        setTitle("Bases De Datos");
        iniciarComponentes();
        accion();
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.BD = BD;
    }
    
    private void iniciarComponentes(){
        crearVuelo = new JLabel();
        crearVuelo.setText("CREAR VUELO");
        crearVuelo.setFont(new Font("Agency FB", Font.PLAIN, 40));
        crearVuelo.setBounds(100, 25, 300, 40);
        panel = new JPanel();
        panel.setLayout(null);
        vuelo = new JLabel();
        vuelo.setText("N. DE VUELO");
        vuelo.setBounds(50, 50, 100, 100);
        ruta = new JLabel();
        ruta.setText("N. DE RUTA");
        ruta.setBounds(50, 80, 100, 100);
        tripulacion = new JLabel();
        tripulacion.setText("TRIPULACION");
        tripulacion.setBounds(50, 110, 100, 100);
        avion = new JLabel();
        avion.setText("ID AVION");
        avion.setBounds(50, 140, 100, 100);
        fechaS = new JLabel();
        fechaS.setText("FECHA DE SALIDA");
        fechaS.setBounds(50, 170, 100, 100);
        fechaLl = new JLabel();
        fechaLl.setText("FECHA DE LLEGADA");
        fechaLl.setBounds(50, 200, 150, 100);
        vueloT = new JTextField();
        vueloT.setBounds(170, 82, 150, 25);
        rutaT = new JTextField();
        rutaT.setBounds(170, 112, 150, 25);
        tripT = new JTextField();
        tripT.setBounds(170, 142, 150, 25);
        avionT = new JTextField();
        avionT.setBounds(170, 172, 150, 25);
        fechaCDCB = comboBoxDias();
        fechaCDCB.setBounds(170, 202, 50, 25);
        fechaCMCB = comboBoxMes();
        fechaCMCB.setBounds(220, 202, 50, 25);
        fechaCACB = comboBoxAnios();
        fechaCACB.setBounds(270, 202, 50, 25);
        fechaLlDCB = comboBoxDias();
        fechaLlDCB.setBounds(170, 232, 50, 25);
        fechaLlMCB = comboBoxMes();
        fechaLlMCB.setBounds(220, 232, 50, 25);
        fechaLlACB = comboBoxAnios();
        fechaLlACB.setBounds(270, 232, 50, 25);
        crear = new JButton("CREAR VUELO");
        crear.setBounds(120, 275, 115, 35);
        volver = new JButton("VOLVER");
        volver.setBounds(270, 320, 85, 35);
        panel.add(crearVuelo);
        panel.add(vuelo);
        panel.add(ruta);
        panel.add(tripulacion);
        panel.add(avion);
        panel.add(fechaS);
        panel.add(fechaLl);
        panel.add(vueloT);
        panel.add(rutaT);
        panel.add(tripT);
        panel.add(avionT);
        panel.add(fechaCDCB);
        panel.add(fechaCMCB);
        panel.add(fechaCACB);
        panel.add(fechaLlDCB);
        panel.add(fechaLlMCB);
        panel.add(fechaLlACB);   
        panel.add(crear);
        panel.add(volver);
        this.getContentPane().add(panel);
    }
    
    private JComboBox comboBoxDias(){
        JComboBox dias = new JComboBox();
        dias.addItem("Dia");
        for (int i = 1; i <= 31; i++) {
            dias.addItem(i);
        }
        return dias;
    }
    
    private JComboBox comboBoxMes(){
        JComboBox meses = new JComboBox();
        meses.addItem("Mes");
        for (int i = 1; i <= 12; i++) {
            meses.addItem(i);
        }
        return meses;
    }
    
    private JComboBox comboBoxAnios(){
        JComboBox anios = new JComboBox();
        anios.addItem("Año");
        for (int i = 2019; i <= 2021; i++) {
            anios.addItem(i);
        }
        return anios;
    }
    
    private void accion(){
        
        ActionListener crearVuelo = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                try{
                int Nvuelo = Integer.parseInt(vueloT.getText());
                int Nruta = Integer.parseInt(rutaT.getText());
                int Navion = Integer.parseInt(avionT.getText());
                int Ntrip = Integer.parseInt(tripT.getText());
                int AnioS = Integer.parseInt(fechaCACB.getSelectedItem().toString())-1900;
                int mesS = Integer.parseInt(fechaCMCB.getSelectedItem().toString())-1;
                int diaS = Integer.parseInt(fechaCDCB.getSelectedItem().toString());
                java.sql.Date dtS = new java.sql.Date(AnioS,mesS,diaS);
                int AnioL = Integer.parseInt(fechaLlACB.getSelectedItem().toString())-1900;
                int mesL = Integer.parseInt(fechaLlMCB.getSelectedItem().toString())-1;
                int diaL = Integer.parseInt(fechaLlDCB.getSelectedItem().toString());
                java.sql.Date dtL = new java.sql.Date(AnioL,mesL,diaL);
                if(BD.crearVuelo(Nvuelo, dtS, dtL, Nruta, Ntrip, Navion)){
                    JOptionPane.showMessageDialog(Ventana_adm_CV.this, "Vuelo creado");
                }else{
                    JOptionPane.showMessageDialog(Ventana_adm_CV.this, "Error al crear el vuelo");
                }
                }catch(Exception e){
                    JOptionPane.showMessageDialog(Ventana_adm_CV.this, "Error al crear el vuelo");
                }
            }
        };
        
        ActionListener accionVolver = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                Ventana_adm_CV.this.setVisible(false);
                Ventana_adm v1 = new Ventana_adm(BD);
                v1.setVisible(true);
            }
        };
        crear.addActionListener(crearVuelo);
        volver.addActionListener(accionVolver);
    }
}
