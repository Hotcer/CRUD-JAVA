import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

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

    public Estudiante() {
        consultarBt.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                conectar();
            }
        });
    }

    public void listar() {
        conectar();
    }

    public void insertar() {
        conectar();
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
