package bases;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;

public class Ventana_C_R extends JFrame{
    JPanel panel;
    JLabel ver;
    DefaultTableModel dtm;
    JTable tabla;
    JScrollPane tablaS;
    JButton volver;
    Base_De_Datos BD;
    int Nvuelo;
    
    public Ventana_C_R(Base_De_Datos BD, int Nvuelo){
        setSize(400,400);
        setTitle("Bases De Datos");
        this.BD = BD;
        this.Nvuelo = Nvuelo;
        dtm = BD.datosVuelo(Nvuelo);
        iniciarComponentes();
        accion();
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }
    
    private void iniciarComponentes(){
        panel = new JPanel();
        panel.setLayout(null);
        ver = new JLabel();
        ver.setText("RESERVAR");
        ver.setFont(new Font("Agency FB", Font.PLAIN, 40));
        ver.setBounds(110, 20, 300, 40);
        tabla =  new JTable(dtm);
        tabla.getColumnModel().getColumn(0).setPreferredWidth(30);
        tablaS = new JScrollPane(tabla);
        tablaS.setBounds(10, 80, 375, 210);
        volver = new JButton("VOLVER");
        volver.setBounds(275, 300, 100, 40);
        panel.add(ver);
        panel.add(tablaS);
        panel.add(volver);
        this.getContentPane().add(panel);
        
        
    }
    
    private void accion(){
        
        MouseListener ml = new MouseListener() {

            @Override
            public void mouseClicked(MouseEvent me) {
                int fila = tabla.getSelectedRow();
                int Nvuelo = Integer.valueOf(dtm.getValueAt(fila, 0).toString());
                String asiento = dtm.getValueAt(fila, 1).toString();
                int seleccion = JOptionPane.showOptionDialog(
                    Ventana_C_R.this,
                    "¿Reservar asiento?", 
                    "Selector de opciones",
                    JOptionPane.YES_NO_CANCEL_OPTION,
                    JOptionPane.QUESTION_MESSAGE,
                    null,
                    new Object[] { "SI", "NO" },
                    "opcion 1");
                if (seleccion == 0){
                    if(BD.reservarAsiento(Nvuelo, asiento)){
                        JOptionPane.showMessageDialog(Ventana_C_R.this, "Reserva realizada");
                    }else{
                        JOptionPane.showMessageDialog(Ventana_C_R.this, "Error al realizar reserva");
                    }
                }
                
            }

            @Override
            public void mousePressed(MouseEvent me) {
            }

            @Override
            public void mouseReleased(MouseEvent me) {
            }

            @Override
            public void mouseEntered(MouseEvent me) {
            }

            @Override
            public void mouseExited(MouseEvent me) {
                
            }
        };
        
        ActionListener accionVolver = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                Ventana_C_R.this.setVisible(false);
                Ventana_Cliente_V v1 = new Ventana_Cliente_V(BD);
                v1.setVisible(true);
            }
        };
        
        volver.addActionListener(accionVolver);
        tabla.addMouseListener(ml);
    }
    
}
