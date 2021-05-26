
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

public class Ventana_Cliente_C extends JFrame{
    JPanel panel;
    JLabel cancelar;
    JLabel vuelo;
    JLabel silla;
    JButton cancelarReserva;
    JButton volver;
    JTextField vueloT;
    JTextField sillaT;
    Base_De_Datos BD;
    
    public Ventana_Cliente_C(Base_De_Datos BD){
        setSize(400,400);
        setTitle("Bases De Datos");
        iniciarComponentes();
        accion();
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.BD = BD;
    }
    
    private void iniciarComponentes(){
        cancelar = new JLabel();
        cancelar.setText("CANCELAR RESERVA");
        cancelar.setFont(new Font("Agency FB", Font.PLAIN, 40));
        cancelar.setBounds(50, 25, 300, 40);
        panel = new JPanel();
        panel.setLayout(null);
        vuelo = new JLabel();
        vuelo.setText("N. DE VUELO");
        vuelo.setBounds(50, 50, 100, 100);
        silla = new JLabel();
        silla.setText("ASIENTO");
        silla.setBounds(50, 107, 100, 100);
        vueloT = new JTextField();
        vueloT.setBounds(160, 80, 120, 30);
        sillaT = new JTextField();
        sillaT.setBounds(160, 135, 120, 30);
        cancelarReserva = new JButton("CANCELAR RESERVA");
        cancelarReserva.setBounds(180, 195, 170, 50);
        volver = new JButton("VOLVER");
        volver.setBounds(275, 300, 100, 40);
        panel.add(cancelar);
        panel.add(vuelo);
        panel.add(silla);
        panel.add(vueloT);
        panel.add(sillaT);
        panel.add(cancelarReserva);
        panel.add(volver);
        this.getContentPane().add(panel);
        
        
    }
    
    private void accion(){
        
        ActionListener cancelarAsiento = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                int Nvuelo = Integer.parseInt(vueloT.getText());
                String asiento = silla.getText();
                if(BD.cancelarReserva(Nvuelo, asiento)){
                    JOptionPane.showMessageDialog(Ventana_Cliente_C.this, "Rerserva cancelada");
                }else{
                    JOptionPane.showMessageDialog(Ventana_Cliente_C.this, "Error al cancelar reserva");
                }
            }
        };
        
        ActionListener accionVolver = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                Ventana_Cliente_C.this.setVisible(false);
                Ventana_Cliente v1 = new Ventana_Cliente(BD);
                v1.setVisible(true);
            }
        };
        
        volver.addActionListener(accionVolver);
        cancelarReserva.addActionListener(cancelarAsiento);
    }
}
