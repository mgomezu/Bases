
package bases;
import java.sql.*;
import java.util.ArrayList;
import javax.swing.table.DefaultTableModel;

public class Base_De_Datos {
    private static Connection conexion;
    private static String bd="aerolinea";
    private static String user;
    private static String password;
    private static String host="localhost";
    private static String server;
    private String mensaje = "";
    
    public Base_De_Datos(String user, String password){
        this.user = user;
        this.password = password;
        server = "jdbc:mysql://"+host+"/"+bd+"?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
        try {
        Class.forName("com.mysql.jdbc.Driver");
        conexion = DriverManager.getConnection(server,user,password);
        mensaje = "Conexion a base de datos establecida";
        } catch (ClassNotFoundException ex) {
            mensaje = "Error cargando el Driver MySQL JDBC ... FAIL";
        } catch (SQLException ex) {
            System.out.println(ex);
            mensaje = "Usuario o contraseña incorrecta";
        }
    }
    
    
    
    public String mensaje(){
        return mensaje;
    }
    
    public boolean confirmar(){
        if(mensaje == "Conexion a base de datos establecida"){
            return true;
        }else{
            return false;
        }
    }
    
    public String rol(){
        try {
        Statement s = conexion.createStatement();
        ResultSet rs = s.executeQuery("SELECT CURRENT_ROLE()");
        if (rs.next()){
        return rs.getString(1);
        }else{
            return ("Imposible realizar consulta ... FAIL");
        }
        } catch (SQLException ex) {
            System.out.println(ex);
        return("Imposible realizar consulta ... FAIL");
        } catch (Exception e){
            return e.toString();
        }
    }
    
    public void cerrarSesion(){
        try {
        conexion.close();
        } catch (SQLException ex) {
            System.out.println(ex.toString());
        }
    }
    
    public Boolean cancelarVuelo(int vuelo){
        try{
            PreparedStatement s = conexion.prepareStatement("DELETE FROM vuelo WHERE vue_Numero=?");
            s.setInt(1, vuelo);
            s.execute();
            return true;
        
        } catch (SQLException ex) {
            System.out.println(ex.toString());
            return false;
        }
    }
    
    public Boolean crearVuelo(int vuelo, Date fechaS, Date fechaL, int ruta, int trip, int avion){
        try{
            PreparedStatement s = conexion.prepareStatement("INSERT INTO vuelo(vue_Numero, vue_FechaSalida, "
                    + "vue_FechaLlegada, ID_ruta, ID_tripulacion, ID_Avion)VALUES(?,?,?,?,?,?)");
            s.setInt(1, vuelo);
            s.setDate(2, fechaS);
            s.setDate(3, fechaL);
            s.setInt(4, ruta);
            s.setInt(5, trip);
            s.setInt(6, avion);
            s.execute();
            return true;
        
        } catch (SQLException ex) {
            System.out.println(ex.toString());
            return false;
        }
    }
    
    public Boolean cancelarReserva(int vuelo, String asiento){
        
        long id = getID();
        
        try{
            PreparedStatement s = conexion.prepareStatement("CALL cancelar_asiento(?,?,?)");
            s.setInt(1, vuelo);
            s.setString(2, asiento);
            s.setLong(3, id);
            s.execute();
            return true;
        
        } catch (SQLException ex) {
            System.out.println(ex.toString());
            return false;
        }
    }
    
    
    public DefaultTableModel datosTabla(){
        String[] columnas = {"Vuelo", "Origen", "Destino","Salida", "Llegada"};
        DefaultTableModel tableModel = new DefaultTableModel() { 
            @Override 
            public boolean isCellEditable(int row, int column) { 
            return false; 
            } 
        };

        tableModel.setColumnIdentifiers(columnas);
        try {
        PreparedStatement pstm = conexion.prepareStatement("select * from vista_cliente_v");
        ResultSet res = pstm.executeQuery ();
        int count=1;
        while(res.next()){
            String origen = res.getString( "origen" );
            String destino = res.getString( "destino" );
            int vuelo = res.getInt( "vue_numero" );
            Date fechaS= res.getDate("vue_FechaSalida" );
            Date fechaL = res.getDate( "vue_FechaLlegada" );
            Object fila []= {vuelo,origen,destino,fechaS,fechaL};
            tableModel.addRow(fila);
        }
        return tableModel;
        } catch (SQLException ex) {
        System.out.println("Imposible realizar consulta ... FAIL");
        return tableModel;
        }catch (Exception e){
            System.out.println(e);
            return tableModel;
        }
    }
    
    public DefaultTableModel datosVuelo(int Nvuelo){
        String[] columnas = {"Vuelo", "Asiento", "Tipo"};
        DefaultTableModel tableModel = new DefaultTableModel() { 
            @Override 
            public boolean isCellEditable(int row, int column) { 
            return false; 
            } 
        };

        tableModel.setColumnIdentifiers(columnas);
        try {
        PreparedStatement pstm = conexion.prepareStatement("CALL ver_vuelos(?)");
        pstm.setInt(1, Nvuelo);
        ResultSet res = pstm.executeQuery ();
        int count=1;
        while(res.next()){
            int vuelo = res.getInt( "N_Vuelo" );
            String asiento = res.getString( "ID_Silla" );
            String tipo = res.getString( "Tipo" );
            Object fila []= {vuelo,asiento,tipo};
            tableModel.addRow(fila);
        }
        return tableModel;
        } catch (SQLException ex) {
        System.out.println("Imposible realizar consulta ... FAIL");
        System.out.println(ex);
        return tableModel;
        }catch (Exception e){
            System.out.println(e);
            return tableModel;
        }
    }
    
    public long getID (){
        String [] nombres = user.split(" ");
        String nombre;
        String apellidos;
        try{
            nombre = nombres[0] + " " + nombres[1];
            apellidos = nombres[2] + " " + nombres[3];
        }catch(Exception e){
           nombre = nombres[0];
            apellidos = nombres[1] + " " + nombres[2];
        }
        try{    
            //ver_id_cliente SET ROLE mantenimineto_total;
            PreparedStatement T = conexion.prepareStatement("GRANT EXECUTE ON PROCEDURE ver_id_cliente TO ?@?");
            T.setString(1, user);
            T.setString(2, "localhost");
            T.execute();
            CallableStatement s = conexion.prepareCall("{call ver_id_cliente(?, ?, ?)}");//{? = call MyStoredProcedure(?)}
            s.setString(1, nombre);
            s.setString(2, apellidos);
            s.registerOutParameter(3, java.sql.Types.BIGINT);
            s.executeQuery();
            long id = s.getLong(3);
            return id;
        
        } catch (SQLException ex) {
            System.out.println(ex.toString() );
            return -1;
        }
        
                
                
    }
    
    public Boolean reservarAsiento (int Nvuelo, String asiento){
        long id_usuario = this.getID();
        try{
            PreparedStatement s = conexion.prepareStatement("CALL reservar_asiento(?,?,?)");
            s.setInt(1, Nvuelo);
            s.setString(2, asiento);
            s.setLong(3, id_usuario);
            s.execute();
            return true;
        
        } catch (SQLException ex) {
            System.out.println(ex.toString());
            return false;
        }
    }
}
