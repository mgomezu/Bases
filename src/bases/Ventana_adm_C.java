
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

public class Ventana_adm_C extends JFrame{
    JPanel panel;
    JLabel cancelar;
    JLabel vuelo;
    JButton cancelarVuelo;
    JButton volver;
    JTextField vueloT;
    Base_De_Datos BD;
    
    public Ventana_adm_C(Base_De_Datos BD){
        setSize(400,400);
        setTitle("Bases De Datos");
        iniciarComponentes();
        accion();
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.BD = BD;
    }
    
    private void iniciarComponentes(){
        cancelar = new JLabel();
        cancelar.setText("CANCELAR VUELO");
        cancelar.setFont(new Font("Agency FB", Font.PLAIN, 40));
        cancelar.setBounds(80, 25, 300, 40);
        panel = new JPanel();
        panel.setLayout(null);
        vuelo = new JLabel();
        vuelo.setText("N. DE VUELO");
        vuelo.setBounds(50, 100, 100, 100);
        vueloT = new JTextField();
        vueloT.setBounds(160, 130, 120, 30);
        cancelarVuelo = new JButton("CANCELAR VUELO");
        cancelarVuelo.setBounds(100, 195, 170, 50);
        volver = new JButton("VOLVER");
        volver.setBounds(275, 300, 100, 40);
        panel.add(cancelar);
        panel.add(vuelo);
        panel.add(vueloT);
        panel.add(cancelarVuelo);
        panel.add(volver);
        this.getContentPane().add(panel);
        
        
    }
    
    private void accion(){
        
        ActionListener accionCancelar = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                int vuelo = Integer.parseInt(vueloT.getText());
                if(BD.cancelarVuelo(vuelo)){
                    JOptionPane.showMessageDialog(Ventana_adm_C.this, "Vuelo cancelado");
                }else{
                    JOptionPane.showMessageDialog(Ventana_adm_C.this, "Error al cancelar el vuelo");
                }
            }
        };
        
        ActionListener accionVolver = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                Ventana_adm_C.this.setVisible(false);
                Ventana_adm v1 = new Ventana_adm(BD);
                v1.setVisible(true);
            }
        };
        cancelarVuelo.addActionListener(accionCancelar);
        volver.addActionListener(accionVolver);
    }
}