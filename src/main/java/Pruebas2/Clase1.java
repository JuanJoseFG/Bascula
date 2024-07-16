/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Pruebas2;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.table.DefaultTableModel;
import java.awt.Component;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

/**
 *
 * @author DESARROLLADOR
 */
public class Clase1 extends javax.swing.JFrame {
// public String estilo;

    /**
     * Creates new form Clase1
     */
    public String est;
    private JPanel miPanel; // Panel para contener el botón
    private JButton miBoton; // Botón para introducir cantidad cortada

    public Clase1() {
        initComponents();
        cargarDatosComboBox();
        crearBoton();
//        jButton1.setVisible(false);
    }

    private void crearBoton() {
        miPanel = new JPanel(); // Inicialización del panel
        miPanel.setLayout(null); // Desactivar el layout para poder establecer las posiciones manualmente

        // Crear el botón
        miBoton = new JButton("Introducir Salida de Peso (Kg)");
        miBoton.setBounds(0, 0, 350, 45); // Establecer posición y tamaño del botón dentro del panel
        miBoton.setFont(new Font("Arial", Font.BOLD, 16)); // Establecer la fuente y tamaño del texto
        miBoton.setForeground(Color.WHITE); // Establecer el color del texto
        miBoton.setBackground(new Color(59, 89, 182)); // Establecer el color de fondo
        miBoton.setFocusPainted(false); // Eliminar el efecto de enfoque
        miBoton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Acción a realizar al hacer clic en el botón
                Bas bas = new Bas();
                bas.setDato(est);
                if (est.equals("Seleccione un Estilo")) {
                    JOptionPane.showMessageDialog(Clase1.this, "Seleccione un Estilo");

                } else {
                    JOptionPane.showMessageDialog(Clase1.this, "Excelente, modificara un articulo con estilo: \n" + est);
                    bas.setLocationRelativeTo(null);
                    bas.setVisible(true);
                }

            }
        });

        miPanel.add(miBoton); // Agregar el botón al panel
        getContentPane().add(miPanel); // Agregar el panel al JFrame
        miPanel.setBounds(75, 295, 350, 100); // Establecer posición y tamaño del panel dentro del JFrame
        miPanel.setVisible(false); // El panel estará invisible inicialmente
    }

    private void cargarDatosComboBox() {

        Connection conn = null;
        Statement stmt;
        ResultSet rs;

        try {

            // Obtener conexión usando la clase de conexión reutilizable
            conn = Connexion.obtenerConexion();

            // Verificar si la conexión se estableció correctamente
            if (conn != null) {
                System.out.println("Conexión exitosa a la base de datos");

                // Paso 3: Crear el objeto Statement para ejecutar la consulta SQL
                stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);

                // Paso 4: Ejecutar la consulta SQL
                String sql = "SELECT ESTILOS.estilo FROM ESTILOS";

                rs = stmt.executeQuery(sql);

                // Itera sobre los resultados y agrega cada nombre al JComboBox
                while (rs.next()) {
                    String estilo = rs.getString("estilo");
                    //String ID_estilo = rs.getString("id_estilos");
                    //String estiloCompleto = ID_estilo + " - " + estilo; // Concatenas el ID y el nombre del estilo
                    jComboBox1.addItem(estilo); // Agregas el elemento al JComboBox
                }

            }

        } catch (SQLException ex) {
            // Maneja cualquier excepción que pueda ocurrir durante la carga de datos
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error al cargar los datos desde la base de datos: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        } finally {
            // Cerrar la conexión
            Connexion.cerrarConexion(conn);
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jDesktopPane1 = new javax.swing.JDesktopPane();
        jComboBox1 = new javax.swing.JComboBox<>();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Monotype Corsiva", 2, 48)); // NOI18N
        jLabel1.setText("Pesaje - Salida");

        jComboBox1.setBackground(new java.awt.Color(153, 153, 153));
        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Seleccione un Estilo" }));
        jComboBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox1ActionPerformed(evt);
            }
        });

        jDesktopPane1.setLayer(jComboBox1, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout jDesktopPane1Layout = new javax.swing.GroupLayout(jDesktopPane1);
        jDesktopPane1.setLayout(jDesktopPane1Layout);
        jDesktopPane1Layout.setHorizontalGroup(
            jDesktopPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jDesktopPane1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jComboBox1, 0, 429, Short.MAX_VALUE)
                .addContainerGap())
        );
        jDesktopPane1Layout.setVerticalGroup(
            jDesktopPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jDesktopPane1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(23, 23, 23)
                        .addComponent(jDesktopPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(112, 112, 112)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 267, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(30, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(39, 39, 39)
                .addComponent(jLabel1)
                .addGap(28, 28, 28)
                .addComponent(jDesktopPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(219, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jComboBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox1ActionPerformed
        Connection conn = null;
        Statement stmt;
        ResultSet rs;

        try {

            // Obtener conexión usando la clase de conexión reutilizable
            conn = Connexion.obtenerConexion();

            // Verificar si la conexión se estableció correctamente
            if (conn != null) {
                System.out.println("Conexión exitosa a la base de datos");

                // Paso 3: Crear el objeto Statement para ejecutar la consulta SQL
                stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);

                // Paso 4: Ejecutar la consulta SQL
                String sql = "SELECT ESTILOS.estilo FROM ESTILOS";

                rs = stmt.executeQuery(sql);
                String estilo = "";
                // Itera sobre los resultados y agrega cada nombre al JComboBox
                while (rs.next()) {
                    estilo = rs.getString("estilo");
                    //String ID_estilo = rs.getString("id_estilos");
                    //String estiloCompleto = ID_estilo + " - " + estilo; // Concatenas el ID y el nombre del estilo
                    jComboBox1.addItem(estilo); // Agregas el elemento al JComboBox
                }

                est = jComboBox1.getSelectedItem().toString();
                System.out.println(jComboBox1.getSelectedItem().toString());
                System.out.println("El estilo es:" + est);

                conn = Connexion.obtenerConexion();

                // Verificar si la conexión se estableció correctamente
                if (conn != null) {
                    System.out.println("Conexión exitosa a la base de datos, se hara llenado de tabla");
                }

                // Paso 3: Crear el objeto Statement para ejecutar la consulta SQL
                stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);

                // Paso 4: Ejecutar la consulta SQL
                String sql1 = "SELECT A.id_articulo, A.ean, A.color, A.k_pesado, A.talla FROM PEDIDOS P\n"
                        + "LEFT JOIN ESTILOS E ON E.id_estilos = P.id_estilos\n"
                        + "LEFT JOIN ARTICULOS_PEDIDOS AP ON AP.clave_pedido = P.clave\n"
                        + "LEFT JOIN ARTICULOS A ON A.id_articulo = AP.id_articulo\n"
                        + "Where E.estilo = ?";

                PreparedStatement statement = conn.prepareStatement(sql1);
                statement.setString(1, est);

                rs = statement.executeQuery();

                // Crea un DefaultTableModel para la JTable
                DefaultTableModel model = new DefaultTableModel();

                model.addColumn("EAN");
                model.addColumn("Color");
                model.addColumn("Kg Pesados");
//                model.addColumn("Kg Entrada");
                model.addColumn("Kg Salida");

                // Agrega los datos de la consulta al DefaultTableModel
                while (rs.next()) {
                    model.addRow(new Object[]{rs.getString("ean"), rs.getString("color"), rs.getString("k_pesado")/*, rs.getDouble("k_entrada"), rs.getString("k_salida")*/});

                }

//                // Establece el modelo de datos de la JTable
//                jTable1.setModel(model);
//                jTable1.getColumnModel().getColumn(2).setCellRenderer(new CustomTableCellRenderer(2, Color.ORANGE)); // Cambia el color de la columna a modificar (Cantidad Cortada)

                if (!estilo.equals("")) {
                    miPanel.setVisible(true); // Si hay algo seleccionado, el botón se hace visible
//                    jButton1.setVisible(true);
                }

            }

        } catch (SQLException ex) {
            // Maneja cualquier excepción que pueda ocurrir durante la carga de datos
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error al cargar los datos desde la base de datos: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        } finally {
            // Cerrar la conexión
            Connexion.cerrarConexion(conn);
        }

    }//GEN-LAST:event_jComboBox1ActionPerformed

    //Clase que sobreescribe las caracteristicas de la tabla, en este caso sera para colorear una celda.
    public class CustomTableCellRenderer extends DefaultTableCellRenderer {

        private final int columnToColor; // Índice de la columna que se coloreará
        private final Color backgroundColor; // Color de fondo deseado

        public CustomTableCellRenderer(int columnToColor, Color backgroundColor) {
            this.columnToColor = columnToColor;
            this.backgroundColor = backgroundColor;
        }

        @Override
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
            Component cellComponent = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
            if (column == columnToColor) { // Verificar si esta celda pertenece a la columna que se va a colorear
                cellComponent.setBackground(backgroundColor);
            }
            return cellComponent;
        }
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Clase1.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Clase1.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Clase1.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Clase1.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Clase1().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JDesktopPane jDesktopPane1;
    private javax.swing.JLabel jLabel1;
    // End of variables declaration//GEN-END:variables
}