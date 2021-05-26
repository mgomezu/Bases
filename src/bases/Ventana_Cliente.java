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


public class Ventana_Cliente extends JFrame{
    JPanel panel;
    JLabel cliente;
    JButton verVuelos;
    JButton cancelarReserva;
    JButton volver;
    Base_De_Datos BD;
            
    public Ventana_Cliente(Base_De_Datos BD){
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
        cliente = new JLabel();
        cliente.setText("CLIENTE");
        cliente.setFont(new Font("Agency FB", Font.PLAIN, 40));
        cliente.setBounds(125, 25, 100, 40);
        verVuelos = new JButton("VER VUELOS");
        verVuelos.setBounds(30, 125, 120, 50);
        cancelarReserva = new JButton("CANCELAR RESERVA");
        cancelarReserva.setBounds(180, 125, 170, 50);
        volver = new JButton("CERRAR SESION");
        volver.setBounds(200, 300, 140, 40);
        panel.add(cliente);
        panel.add(verVuelos);
        panel.add(cancelarReserva);
        panel.add(volver);
        this.getContentPane().add(panel);
        
        
    }
    
    private void accion(){
        
        ActionListener accionCancelar = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                Ventana_Cliente.this.setVisible(false);
                Ventana_Cliente_C v1 = new Ventana_Cliente_C(BD);
                v1.setVisible(true);
            }
        };
        
        ActionListener accionVer = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                Ventana_Cliente.this.setVisible(false);
                Ventana_Cliente_V v1 = new Ventana_Cliente_V(BD);
                v1.setVisible(true);
            }
        };
        
        ActionListener accionVolver = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                BD.cerrarSesion();
                Ventana_Cliente.this.setVisible(false);
                Ventana_Inicio v1 = new Ventana_Inicio();
                v1.setVisible(true);
            }
        };
        
        volver.addActionListener(accionVolver);
        cancelarReserva.addActionListener(accionCancelar);
        verVuelos.addActionListener(accionVer);
    }
}
