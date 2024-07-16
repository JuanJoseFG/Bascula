/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Pruebas;


import Pruebas2.*;
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
        jButton1.setVisible(false);
    }

    private void crearBoton() {
        miPanel = new JPanel(); // Inicialización del panel
        miPanel.setLayout(null); // Desactivar el layout para poder establecer las posiciones manualmente

        // Crear el botón
        miBoton = new JButton("Introducir Entrada de Peso (Kg)");
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
        miPanel.setBounds(80, 300, 350, 100); // Establecer posición y tamaño del panel dentro del JFrame
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
                String sql = "SELECT estilo FROM ESTILOS "
                        + "UNION ALL "
                        + "SELECT estilo FROM HANGTEN_PEDIDOS "
                        + "UNION ALL "
                        + "SELECT estilo FROM ESTILOS_COPPEL";

                rs = stmt.executeQuery(sql);

                // Itera sobre los resultados y agrega cada nombre al JComboBox
                while (rs.next()) {
//                    String id_estilo = rs.getString("id_estilos");
                    String estilo = rs.getString("estilo");
                    //String ID_estilo = rs.getString("id_estilos");
                    //String estiloCompleto = ID_estilo + " - " + estilo; // Concatenas el ID y el nombre del estilo
//                    String CC = "ID: " + id_estilo + "      Estilo: " + estilo;
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
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jButton1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(255, 255, 255));

        jLabel1.setFont(new java.awt.Font("Monotype Corsiva", 2, 48)); // NOI18N
        jLabel1.setText("Pesaje - Entrada");

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
                .addComponent(jComboBox1, 0, 438, Short.MAX_VALUE)
                .addContainerGap())
        );
        jDesktopPane1Layout.setVerticalGroup(
            jDesktopPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jDesktopPane1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "Clave", "Orden Tela", "Kg Pesados Corte"
            }
        ));
        jScrollPane1.setViewportView(jTable1);

        jButton1.setText("Actualizar");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(layout.createSequentialGroup()
                                    .addGap(102, 102, 102)
                                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 298, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(layout.createSequentialGroup()
                                    .addGap(28, 28, 28)
                                    .addComponent(jDesktopPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(0, 28, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(34, 34, 34)
                .addComponent(jLabel1)
                .addGap(45, 45, 45)
                .addComponent(jDesktopPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 96, Short.MAX_VALUE)
                .addComponent(jButton1)
                .addContainerGap())
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
//                stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
//
//                // Paso 4: Ejecutar la consulta SQL
//                String sql = "SELECT * FROM ESTILOS";
//
//                rs = stmt.executeQuery(sql);
//                String estilo = "";
//                // Itera sobre los resultados y agrega cada nombre al JComboBox
//                while (rs.next()) {
//                    String id_estilo = rs.getString("id_estilos");
//                    estilo = rs.getString("estilo");
//                    //String ID_estilo = rs.getString("id_estilos");
//                    //String estiloCompleto = ID_estilo + " - " + estilo; // Concatenas el ID y el nombre del estilo
//                    String CC = "ID: " + id_estilo + "      Estilo: " + estilo;
//                    jComboBox1.addItem(CC); // Agregas el elemento al JComboBox
//                }
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
                String sql1 = "SELECT P.clave, P.orden_tela, P.kg_pesado_corte  FROM PEDIDOS P\n"
                        + "                        LEFT JOIN ESTILOS E ON E.id_estilos = P.id_estilos\n"
                        + "                        \n"
                        + "                        Where E.estilo = ? ";

                PreparedStatement statement = conn.prepareStatement(sql1);
                statement.setString(1, est);

                rs = statement.executeQuery();

                // Crea un DefaultTableModel para la JTable
                DefaultTableModel model = new DefaultTableModel();

                model.addColumn("Clave");
                model.addColumn("Orden Tela");
                model.addColumn("Kg Pesados Corte");

                // Agrega los datos de la consulta al DefaultTableModel
                boolean datosEncontrados = false;
                while (rs.next()) {
                    model.addRow(new Object[]{rs.getString("clave"), rs.getString("orden_tela"), rs.getString("kg_pesado_corte")/*, rs.getDouble("k_entrada"), rs.getString("k_salida")*/});
                    datosEncontrados = true;
                }

// Si no se encontraron datos, muestra un mensaje
                if (!datosEncontrados) {
//                    JOptionPane.showMessageDialog(this, "No se encontraron datos para el estilo seleccionado", "Mensaje", JOptionPane.INFORMATION_MESSAGE);

                    System.out.println("No hay datos se hara consulta de hangten: ");

                    // Paso 4: Ejecutar la consulta SQL
                    String sql2 = "SELECT HP.clave, HP.orden_tela, HP.kg_pesado_corte  FROM HANGTEN_PEDIDOS HP\n"
                            + "                        \n"
                            + "                        Where HP.estilo = ?";

                    PreparedStatement statem = conn.prepareStatement(sql2);
                    statem.setString(1, est);

                    rs = statem.executeQuery();

// Crea un DefaultTableModel para la JTable
                    DefaultTableModel model2 = new DefaultTableModel();

                    model2.addColumn("Clave");
                    model2.addColumn("Orden Tela");
                    model2.addColumn("Kg Pesados Corte");

// Agrega los datos de la consulta al DefaultTableModel
                    boolean datosEncontrados2 = false;
                    while (rs.next()) {
                        model2.addRow(new Object[]{rs.getString("clave"), rs.getString("orden_tela"), rs.getString("kg_pesado_corte")/*, rs.getDouble("k_entrada"), rs.getString("k_salida")*/});
                        datosEncontrados2 = true;
                    }

// Si no se encontraron datos, muestra un mensaje
                    if (!datosEncontrados2) {
//                        JOptionPane.showMessageDialog(this, "No se encontraron datos para el estilo seleccionado", "Mensaje", JOptionPane.INFORMATION_MESSAGE);

                        System.out.println("No hay datos se hara consulta de COPPEL ");

                        // Paso 4: Ejecutar la consulta SQL
                        String sql3 = "SELECT PC.clave_pedido, PC.orden_tela, PC.kg_pesado_corte  FROM PEDIDOS_COPPEL PC\n"
                                + "                        LEFT JOIN ESTILOS_COPPEL EC ON EC.id_estilo = PC.id_estilo\n"
                                + "                        \n"
                                + "                        Where EC.estilo = ?";

                        PreparedStatement stateme = conn.prepareStatement(sql3);
                        stateme.setString(1, est);

                        rs = stateme.executeQuery();

// Crea un DefaultTableModel para la JTable
                        DefaultTableModel model3 = new DefaultTableModel();

                        model3.addColumn("Clave");
                        model3.addColumn("Orden Tela");
                        model3.addColumn("Kg Pesados Corte");

// Agrega los datos de la consulta al DefaultTableModel
                        boolean datosEncontrados3 = false;
                        while (rs.next()) {
                            model3.addRow(new Object[]{rs.getString("clave_pedido"), rs.getString("orden_tela"), rs.getString("kg_pesado_corte")/*, rs.getDouble("k_entrada"), rs.getString("k_salida")*/});
                            datosEncontrados3 = true;
                        }

// Si no se encontraron datos, muestra un mensaje
                        if (!datosEncontrados3) {
                            JOptionPane.showMessageDialog(this, "No se encontraron datos para el estilo seleccionado", "Mensaje", JOptionPane.INFORMATION_MESSAGE);
                            DefaultTableModel mode = new DefaultTableModel();

                            mode.addColumn("Clave");
                            mode.addColumn("Orden Tela");
                            mode.addColumn("Kg Pesados Corte");

                            jTable1.setModel(mode);
                            
                            miPanel.setVisible(false);
                            
                        } else {
                            // Establece el modelo de datos de la JTable si se encontraron datos
                            jTable1.setModel(model3);
                            jTable1.getColumnModel().getColumn(2).setCellRenderer(new CustomTableCellRenderer(2, Color.ORANGE)); // Cambia el color de la columna a modificar (Cantidad Cortada)

                            if (!est.equals("")) {
                                miPanel.setVisible(true); // Si hay algo seleccionado, el botón se hace visible
//      jButton1.setVisible(true);
                            }
                        }

                    } else {
                        // Establece el modelo de datos de la JTable si se encontraron datos
                        jTable1.setModel(model2);
                        jTable1.getColumnModel().getColumn(2).setCellRenderer(new CustomTableCellRenderer(2, Color.ORANGE)); // Cambia el color de la columna a modificar (Cantidad Cortada)

                        if (!est.equals("")) {
                            miPanel.setVisible(true); // Si hay algo seleccionado, el botón se hace visible
//      jButton1.setVisible(true);
                        }
                    }

                } else {
                    // Establece el modelo de datos de la JTable si se encontraron datos
                    jTable1.setModel(model);
                    jTable1.getColumnModel().getColumn(2).setCellRenderer(new CustomTableCellRenderer(2, Color.ORANGE)); // Cambia el color de la columna a modificar (Cantidad Cortada)

                    if (!est.equals("")) {
                        miPanel.setVisible(true); // Si hay algo seleccionado, el botón se hace visible

                    }
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

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
         jComboBox1.setSelectedItem(est);
    }//GEN-LAST:event_jButton1ActionPerformed

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
    public static javax.swing.JButton jButton1;
    public javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JDesktopPane jDesktopPane1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    // End of variables declaration//GEN-END:variables
}
