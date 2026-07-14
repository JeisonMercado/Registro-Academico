/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package registroacademico;

/**
 *
 * @author jeiso
 */
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RegistroAcademicoGUI extends JFrame {

    private JTextField txtNombre, txtMatricula, txtCarrera;
    private JTextField txtPracticas, txtParciales, txtAsignaciones, txtExamen;
    private JTextArea areaResultado;

    public RegistroAcademicoGUI() {
        setTitle("Registro Academico");
        setSize(400, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        setLayout(new GridLayout(11, 2, 5, 5));

        add(new JLabel("Nombre:"));
        txtNombre = new JTextField();
        add(txtNombre);

        add(new JLabel("Matricula:"));
        txtMatricula = new JTextField();
        add(txtMatricula);

        add(new JLabel("Carrera:"));
        txtCarrera = new JTextField();
        add(txtCarrera);

        add(new JLabel("Practicas (0-40):"));
        txtPracticas = new JTextField();
        add(txtPracticas);

        add(new JLabel("Parciales (0-20):"));
        txtParciales = new JTextField();
        add(txtParciales);

        add(new JLabel("Asignaciones (0-20):"));
        txtAsignaciones = new JTextField();
        add(txtAsignaciones);

        add(new JLabel("Examen Final (0-20):"));
        txtExamen = new JTextField();
        add(txtExamen);

        JButton btnCalcular = new JButton("Calcular Resultado");
        add(btnCalcular);

        areaResultado = new JTextArea();
        areaResultado.setEditable(false);
        add(new JScrollPane(areaResultado));

        btnCalcular.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                calcularResultado();
            }
        });
    }

    private void calcularResultado() {
        try {
            // Validar campos vacios
            if (txtNombre.getText().isEmpty() ||
                txtMatricula.getText().isEmpty() ||
                txtCarrera.getText().isEmpty() ||
                txtPracticas.getText().isEmpty() ||
                txtParciales.getText().isEmpty() ||
                txtAsignaciones.getText().isEmpty() ||
                txtExamen.getText().isEmpty()) {

                JOptionPane.showMessageDialog(this, "Todos los campos deben estar llenos.");
                return;
            }

            double practicas = Double.parseDouble(txtPracticas.getText());
            double parciales = Double.parseDouble(txtParciales.getText());
            double asignaciones = Double.parseDouble(txtAsignaciones.getText());
            double examen = Double.parseDouble(txtExamen.getText());

            // Validar rangos
            if (practicas < 0 || practicas > 40 ||
                parciales < 0 || parciales > 20 ||
                asignaciones < 0 || asignaciones > 20 ||
                examen < 0 || examen > 20) {

                JOptionPane.showMessageDialog(this, "Valores fuera de rango.");
                return;
            }

            Estudiante est = new Estudiante(
                    txtNombre.getText(),
                    txtMatricula.getText(),
                    txtCarrera.getText(),
                    practicas, parciales, asignaciones, examen
            );

            double total = est.calcularNotaFinal();
            String resultado = est.obtenerResultado();

            areaResultado.setText(
                    "Nota Final: " + total + "\n" +
                    "Resultado: " + resultado
            );

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Ingrese valores numericos validos.");
        }
    }
}