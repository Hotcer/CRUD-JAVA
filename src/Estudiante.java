import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class Estudiante extends JFrame {
    private JPanel panel;
    private JTextField idText;
    private JTextField direccionTxt;
    private JTextField nombreTxt;
    private JTextField apellidoTxt;
    private JTextField edadTxt;
    private JTextField celularTxt;
    private JButton ingresarBt;
    private JButton consultarBt;
    private JList lista;
    Connection con;
    PreparedStatement ps;
    Statement st;
    ResultSet r;
    DefaultListModel mod = new DefaultListModel();

    public Estudiante() {
        consultarBt.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    listar();
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
        ingresarBt.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    insertar();
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
    }

    public void listar() throws SQLException {
        conectar();
        lista.setModel(mod);
        st = con.createStatement();
        r = st.executeQuery("SELECT id,nombre,apellido FROM estudiantes");
        mod.removeAllElements();
        mod.addElement(r.getString(1) + " " + r.getString(2) + " " + r.getString(3));
    }

    public void insertar() throws SQLException {

        conectar();
        ps = con.prepareStatement("INSERT INTO estudiantes VALUES (?,?,?,?,?,?)");
        ps.setInt(1, Integer.parseInt(idText.getText()));
        ps.setString(2, nombreTxt.getText());
        ps.setString(3, apellidoTxt.getText());
        ps.setInt(4, Integer.parseInt(edadTxt.getText()));
        ps.setLong(5, Long.parseLong(celularTxt.getText()));
        ps.setString(6, direccionTxt.getText());
        if (ps.executeUpdate() > 0) {
            lista.setModel(mod);
            mod.removeAllElements();
            mod.addElement(" Insercion exitosa ");

            idText.setText("");
            nombreTxt.setText("");
            apellidoTxt.setText("");
            edadTxt.setText("");
            celularTxt.setText("");
            direccionTxt.setText("");
        }
    }

    public static void main(String[] args) {
        Estudiante f = new Estudiante();
        f.setContentPane(new Estudiante().panel);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setVisible(true);
        f.pack();

    }

    public void conectar() {
        try {
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/crud_db", "root", "123456");
            System.out.println("Conectado a la base de datos");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
}
