
package bases;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class Ventana_adm extends JFrame{
    JPanel panel;
    JLabel admin;
    JButton crearVuelos;
    JButton cancelarVuelos;
    JButton volver;
    Base_De_Datos BD;
    
    public Ventana_adm(Base_De_Datos BD){
        setSize(400,400);
        setTitle("Bases De Datos");
        iniciarComponentes();
        accion();
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.BD = BD;
    }
    
    private void iniciarComponentes(){
        panel = new JPanel();
        panel.setLayout(null);
        
        admin = new JLabel();
        admin.setText("ADMINISTRADOR");
        admin.setFont(new Font("Agency FB", Font.PLAIN, 40));
        admin.setBounds(75, 25, 225, 40);
        crearVuelos = new JButton("CREAR VUELO");
        crearVuelos.setBounds(30, 125, 120, 50);
        cancelarVuelos = new JButton("CANCELAR VUELO");
        cancelarVuelos.setBounds(180, 125, 170, 50);
        volver = new JButton("CERRAR SESION");
        volver.setBounds(200, 300, 140, 40);
        panel.add(admin);
        panel.add(crearVuelos);
        panel.add(cancelarVuelos);
        panel.add(volver);
        this.getContentPane().add(panel);
        
        
    }
    
    private void accion(){
        
        ActionListener accionCancelar = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                Ventana_adm.this.setVisible(false);
                Ventana_adm_C v1 = new Ventana_adm_C(BD);
                v1.setVisible(true);
            }
        };
        
        ActionListener accionCrear = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                Ventana_adm.this.setVisible(false);
                Ventana_adm_CV v1 = new Ventana_adm_CV(BD);
                v1.setVisible(true);
            }
        };
        
        ActionListener accionVolver = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                BD.cerrarSesion();
                Ventana_adm.this.setVisible(false);
                Ventana_Inicio v1 = new Ventana_Inicio();
                v1.setVisible(true);
            }
        };
        
        cancelarVuelos.addActionListener(accionCancelar);
        crearVuelos.addActionListener(accionCrear);
        volver.addActionListener(accionVolver);
    }
    
    
}
