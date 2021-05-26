
package bases;

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


public class Ventana_Inicio extends JFrame{
    JPanel panel;
    JLabel inicio;
    JLabel usuario;
    JLabel contr;
    JButton confirmar;
    JButton cerrar;
    JTextField usuarioT;
    JPasswordField contrT;
    
    public Ventana_Inicio(){
        setSize(400,400);
        setTitle("Bases De Datos");
        iniciarComponentes();
        accion();
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }
    
    private void iniciarComponentes(){
        panel = new JPanel();
        panel.setLayout(null);
        inicio = new JLabel();
        inicio.setText("INICIO");
        inicio.setFont(new Font("Agency FB", Font.PLAIN, 40));
        inicio.setBounds(150, 20, 300, 40);
        usuario = new JLabel();
        usuario.setText("USUARIO");
        usuario.setBounds(50, 50, 100, 100);
        contr = new JLabel();
        contr.setText("CONTRASEÑA");
        contr.setBounds(50, 50, 200, 200);
        confirmar = new JButton("INGRESAR");
        confirmar.setBounds(165, 200, 100, 40);
        cerrar = new JButton("CERRAR");
        cerrar.setBounds(275, 300, 100, 40);
        usuarioT = new JTextField();
        usuarioT.setBounds(160, 80, 120, 30);
        contrT = new JPasswordField();
        contrT.setBounds(160, 135, 120, 30);
        panel.add(inicio);
        panel.add(usuario);
        panel.add(contr);
        panel.add(confirmar);
        panel.add(cerrar);
        panel.add(usuarioT);
        panel.add(contrT);
        this.getContentPane().add(panel);
        
        
    }
    
    private void accion(){
        
        ActionListener accionConfirmar = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                String user = usuarioT.getText();
                String pass = contrT.getText();
                Base_De_Datos BD = new Base_De_Datos(user, pass);
                JOptionPane.showMessageDialog(Ventana_Inicio.this, BD.mensaje());
                if (BD.confirmar()){
                    switch (BD.rol()){
                        case "`administrador`@`%`":
                            Ventana_Inicio.this.setVisible(false);
                            Ventana_adm v1 = new Ventana_adm(BD);
                            v1.setVisible(true);
                            break;
                        case "`cliente`@`%`":
                            Ventana_Inicio.this.setVisible(false);
                            Ventana_Cliente v2 = new Ventana_Cliente(new Base_De_Datos(user, pass));
                            v2.setVisible(true);
                            break;
                        case "`tripulacion`@`%`":
                            
                            break;
                        case "`secretaria`@`%`":
                            
                            break;
                        case "`auxiliar`@`%`":
                            
                            break;
                        case "`asesor`@`%`":
                            
                            break;
                        default:
                            
                            break;
                    }
                }
            }
        };
        
        ActionListener accionCerrar = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                System.exit(0);
            }
        };
        
        cerrar.addActionListener(accionCerrar);
        confirmar.addActionListener(accionConfirmar);
    }
}
