/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Pruebas;

import Pruebas2.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import jssc.SerialPort;
import jssc.SerialPortEvent;
import jssc.SerialPortEventListener;
import jssc.SerialPortList;

/**
 *
 * @author RicardoCórdova
 *
 */
public class Bas extends javax.swing.JFrame {

    private String portName;
    private int baudRate = SerialPort.BAUDRATE_9600;
    private int dataBits = SerialPort.DATABITS_8;
    private int stopBits = SerialPort.STOPBITS_1;
    private int parity = SerialPort.PARITY_NONE;
    private SerialPort serialPort;
    Map<String, Object> parametros;
    public String estil;
    public String pru;
    public String pesoS;
    public String test;
    public String numeroString;

    /**
     * Creates new form Form
     */
    private String dato;

    public Bas() {
        initComponents();
//        jButtonOpenPort.setVisible(false);
        pru = lblDato.getText();
        enableControls(false);
        String[] ports = SerialPortList.getPortNames();
        for (String port : ports) {
            System.out.println("PUERTO: " + port);
        }

        if (ports.length > 0) {
            //portName = ports[0];
            portName = ports[0];
        }

        updatePortInfo();

        String texto = "<html>Presione el boton para iniciar la captura<br>y presione nuevamente cuando desee<br>confirmar dicha captura</html>";

        // Establecer el texto en el JLabel existente
        jLabel2.setText(texto);

//        AperturarPuertoSerial();
    }

    public void setDato(String dato) {
        this.dato = dato;
        lblDato.setText(this.dato);

        pru = lblDato.getText();

    }

    public void updatePortSettings(String portName, int baudRate, int dataBits, int stopBits, int parity) {
        this.portName = portName;
        this.baudRate = baudRate;
        this.dataBits = dataBits;
        this.stopBits = stopBits;
        this.parity = parity;
        updatePortInfo();
    }

    private boolean updatePortInfo() {
        boolean returnValue = false;
        String info = "";
        if (!portName.equals("")) {
            info += (portName + " @ ");
            info += (baudRate + "-");
            info += (dataBits + "-");
            switch (parity) {
                case SerialPort.PARITY_NONE:
                    info += ("N-");
                    break;
                case SerialPort.PARITY_EVEN:
                    info += ("E-");
                    break;
                case SerialPort.PARITY_ODD:
                    info += ("O-");
                    break;
                case SerialPort.PARITY_SPACE:
                    info += ("S-");
                    break;
                case SerialPort.PARITY_MARK:
                    info += ("M-");
                    break;
            }
            switch (stopBits) {
                case SerialPort.STOPBITS_1:
                    info += ("1");
                    break;
                case SerialPort.STOPBITS_1_5:
                    info += ("1.5");
                    break;
                case SerialPort.STOPBITS_2:
                    info += ("2");
                    break;
            }
            System.out.println(info);
            jLabelPortInfo.setText(info);
            jButtonOpenPort.setEnabled(true);
            returnValue = true;
        } else {
            jLabelPortInfo.setText("");
            jButtonOpenPort.setEnabled(false);
        }
        System.out.println("********************");
        System.out.println(returnValue);
        return returnValue;
    }

    private void enableControls(boolean value) {
        jTextAreaIn.setEnabled(value);
        jTextFieldOut.setEnabled(value);
    }

    public void setControlsFocusable(boolean value) {
        jButtonOpenPort.setFocusable(value);
        jTextAreaIn.setFocusable(value);
        jTextFieldOut.setFocusable(value);

    }

    private void clearFields() {
        jTextAreaIn.setText("");
        jTextFieldOut.setText("");
    }

    private void sendString() {
        String str = jTextFieldOut.getText();
        if (str.length() > 0) {
            try {
                serialPort.writeBytes(jTextFieldOut.getText().getBytes());
                jTextFieldOut.setText("");
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "Writing data\nError occurred while writing data.");
            }
        }
    }

//    private void recibirItemSeleccionado() {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//    }
    private class Reader implements SerialPortEventListener {

        private String str = "";

        public void serialEvent(SerialPortEvent spe) {
            if (spe.isRXCHAR() || spe.isRXFLAG()) {
                if (spe.getEventValue() > 0) {
                    try {
                        str = "";
                        byte[] buffer = serialPort.readBytes(spe.getEventValue());
                        if (0 == 0) {//Chars
                            str = new String(buffer);
                        }

                        SwingUtilities.invokeAndWait(
                                new Runnable() {
                            public void run() {
                                jTextAreaIn.setText("");
                                jTextAreaIn.append(str.replaceAll("ST,GS  ", "").replaceAll(",kg", "").replaceAll("US,GS", "").replaceAll("ww", "").replaceAll("kg", ""));
                                String[] arregloString = jTextAreaIn.getText().split("\n");
                                // Reemplaza la sección del código en tu método serialEvent
                                String texto = arregloString[arregloString.length - 1].trim();
                                System.out.println("Texto recibido: " + texto); // Agregamos este mensaje de impresión para verificar la cadena completa

                                try {
                                    // Eliminamos cualquier carácter no deseado al final de la cadena
                                    texto = texto.replaceAll("[^0-9\\.]", "");
                                    // Buscamos el primer dígito diferente de cero en la cadena
                                    int index = 0;
                                    while (index < texto.length() && texto.charAt(index) == '0') {
                                        index++;
                                    }
                                    // Cortamos la cadena hasta el primer dígito diferente de cero
                                    String pesoString = texto.substring(index);
                                    // Invertimos la cadena
                                    String pesoInvertido = new StringBuilder(pesoString).reverse().toString();
                                    // Convertimos la cadena invertida en un número
                                    double peso = Double.parseDouble(pesoInvertido);
                                    // Si la cadena contiene "kg", dividimos el peso por 1000 para obtener el valor en kilogramos
                                    if (texto.toLowerCase().contains("kg")) {
                                        peso /= 1000.0;
                                    }
                                    System.out.println(peso);
                                    jLabelPeso.setText(Double.toString(peso)); // Mostrar el peso en el JLabel

                                    pesoS = String.valueOf(peso);

                                } catch (NumberFormatException e) {
                                    System.out.println("Error al convertir el peso a número.");
                                }

                            }
                        }
                        );
                    } catch (Exception ex) {
                        //Do nothing
                    }
                }
            }
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

        jDialog1 = new javax.swing.JDialog();
        jMenu1 = new javax.swing.JMenu();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextAreaIn = new javax.swing.JTextArea();
        jLabelPeso = new javax.swing.JLabel();
        jTextFieldOut = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabelPortInfo = new javax.swing.JLabel();
        jButtonOpenPort = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        lblDato = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();

        javax.swing.GroupLayout jDialog1Layout = new javax.swing.GroupLayout(jDialog1.getContentPane());
        jDialog1.getContentPane().setLayout(jDialog1Layout);
        jDialog1Layout.setHorizontalGroup(
            jDialog1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        jDialog1Layout.setVerticalGroup(
            jDialog1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        jMenu1.setText("jMenu1");

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("SERIAL BALANZA");
        setBackground(new java.awt.Color(255, 255, 255));
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel1.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jTextAreaIn.setColumns(20);
        jTextAreaIn.setRows(5);
        jScrollPane1.setViewportView(jTextAreaIn);

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(58, 114, 223, 0));

        jLabelPeso.setBackground(new java.awt.Color(153, 0, 0));
        jLabelPeso.setFont(new java.awt.Font("Tahoma", 1, 130)); // NOI18N
        jLabelPeso.setForeground(new java.awt.Color(255, 255, 255));
        jLabelPeso.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabelPeso.setText("0.00");
        jLabelPeso.setOpaque(true);
        jPanel1.add(jLabelPeso, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 20, 460, 130));
        jPanel1.add(jTextFieldOut, new org.netbeans.lib.awtextra.AbsoluteConstraints(2, 139, -1, 0));

        jLabel1.setFont(new java.awt.Font("Tahoma", 2, 70)); // NOI18N
        jLabel1.setText("Kg");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 20, 90, 130));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 40, 610, 170));

        jPanel2.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabelPortInfo.setText("COM");
        jPanel2.add(jLabelPortInfo, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 155, 33));

        jButtonOpenPort.setText("Iniciar Captura");
        jButtonOpenPort.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonOpenPortActionPerformed(evt);
            }
        });
        jPanel2.add(jButtonOpenPort, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 10, 140, -1));

        getContentPane().add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 300, 340, 50));

        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setToolTipText("");
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 240, 210, 90));

        lblDato.setFont(new java.awt.Font("Tahoma", 2, 18)); // NOI18N
        lblDato.setText("jLabel3");
        getContentPane().add(lblDato, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 240, 200, -1));

        jLabel3.setFont(new java.awt.Font("Tahoma", 2, 18)); // NOI18N
        jLabel3.setText("Modificando: ");
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 240, -1, -1));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonOpenPortActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonOpenPortActionPerformed
        aperturarPuertoSerial();


    }//GEN-LAST:event_jButtonOpenPortActionPerformed

    public void aperturarPuertoSerial() {
        // TODO agregar el manejo de código aquí:

        if (jButtonOpenPort.getText().equals("Iniciar Captura")) {
            System.out.println(portName);
            serialPort = new SerialPort(portName);
            System.out.println(serialPort);
            try {
                if (serialPort.openPort()) {
                    try {
                        serialPort.setParams(baudRate, dataBits, stopBits, parity);
                    } catch (Exception e) {
                        System.out.println(e);
                    }
                    jButtonOpenPort.setText("Capturar");
                    serialPort.addEventListener(new Reader(), SerialPort.MASK_RXCHAR
                            | SerialPort.MASK_RXFLAG
                            | SerialPort.MASK_CTS
                            | SerialPort.MASK_DSR
                            | SerialPort.MASK_RLSD);
                    enableControls(true);

                } else {
                    JOptionPane.showMessageDialog(null, "Abriendo Puertos\nNo se puede abrir el puerto. Debe tener una sola ventana abierta");
                }
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, ex);
            }
        } else {
            try {
                if (serialPort.closePort()) {
                    jButtonOpenPort.setText("Iniciar Captura");
                    enableControls(false);
                    clearFields();

                    Connection conn = null;
                    PreparedStatement stmt = null;
                    ResultSet rs;

                    try {

                        // Obtener conexión usando la clase de conexión reutilizable
                        conn = Connexion.obtenerConexion();

                        // Verificar si la conexión se estableció correctamente
                        if (conn != null) {
                            System.out.println("Conexión exitosa a la base de datos Vamos a actualizar k_pesado");

                            System.out.println("SU PESOOOOOO : " + pesoS);

//SE ACTUALIZARA LA CANTIDAD CORTADA EN KG EN´PRIMERA INSTANCIA PARA LA TABLA PEDIDOS:
                            // Sentencia SQL para actualizar la cantidad_cortada
                            String sql = "UPDATE PEDIDOS P "
                                    + "JOIN ESTILOS E ON P.id_estilos = E.id_estilos "
                                    + "SET P.kg_pesado_corte = ? "
                                    + "WHERE E.estilo = ?";

                            // Preparar la declaración SQL
                            stmt = conn.prepareStatement(sql);

                            //nuevo valor de K_Pesado
                            stmt.setString(1, pesoS);

                            // El ID del artículo que quieres actualizar
                            stmt.setString(2, pru);

                            // Ejecutar la sentencia SQL
                            int filasActualizadas = stmt.executeUpdate();

                            // Verificar si la actualización fue exitosa
                            if (filasActualizadas > 0) {
                                JOptionPane.showMessageDialog(this, "Kg Pesados actualizado exitosamente.", "Actualización Exitosa", JOptionPane.INFORMATION_MESSAGE);
                                System.out.println("se puso: " + pesoS);

                            } else {

//NO SE PUDO, AHORA SE ACTUALIZARA LA CANTIDAD CORTADA EN KG EN SEGUNDA INSTANCIA PARA LA TABLA HANGTEN_PEDIDOS:
//                                JOptionPane.showMessageDialog(this, "No se pudo actualizar los Kg Pesados se intentara con HANGTEN: .", "Error de Actualización", JOptionPane.ERROR_MESSAGE);
                                System.out.println("No se pudo actualizar los Kg Pesados se intentara con HANGTEN:");

                                String sql2 = "UPDATE HANGTEN_PEDIDOS HP \n"
                                        + "                         SET HP.kg_pesado_corte = ?"
                                        + "                         WHERE HP.estilo = ?";

                                // Preparar la declaración SQL
                                stmt = conn.prepareStatement(sql2);

                                //nuevo valor de K_Pesado
                                stmt.setString(1, pesoS);

                                // El ID del artículo que quieres actualizar
                                stmt.setString(2, pru);

                                // Ejecutar la sentencia SQL
                                int filasActualizadas2 = stmt.executeUpdate();

                                // Verificar si la actualización fue exitosa
                                if (filasActualizadas2 > 0) {
                                    JOptionPane.showMessageDialog(this, "Kg Pesados actualizado exitosamente.", "Actualización Exitosa", JOptionPane.INFORMATION_MESSAGE);
                                    System.out.println("se puso: " + pesoS);

                                } else {

//NO SE PUDO, AHORA SE ACTUALIZARA LA CANTIDAD CORTADA EN KG EN TERCERA INSTANCIA PARA LA TABLA PEDIDOS_COPPEL:
//                                    JOptionPane.showMessageDialog(this, "No se pudo actualizar los Kg Pesados se intentara con COPPEL : .", "Error de Actualización", JOptionPane.ERROR_MESSAGE);
                                    System.out.println("No se pudo actualizar los Kg Pesados se intentara con COPPEL");

                                    String sql3 = "UPDATE PEDIDOS_COPPEL PC \n"
                                            + "		JOIN ESTILOS_COPPEL EC ON PC.id_estilo = EC.id_estilo\n"
                                            + "         SET PC.kg_pesado_corte = ?\n"
                                            + "         WHERE EC.estilo = ?";

                                    // Preparar la declaración SQL
                                    stmt = conn.prepareStatement(sql3);

                                    //nuevo valor de K_Pesado
                                    stmt.setString(1, pesoS);

                                    // El ID del artículo que quieres actualizar
                                    stmt.setString(2, pru);

                                    // Ejecutar la sentencia SQL
                                    int filasActualizadas3 = stmt.executeUpdate();

                                    // Verificar si la actualización fue exitosa
                                    if (filasActualizadas3 > 0) {
                                        JOptionPane.showMessageDialog(this, "Kg Pesados actualizado exitosamente.", "Actualización Exitosa", JOptionPane.INFORMATION_MESSAGE);
                                        System.out.println("se puso: " + pesoS);

                                    } else {

//YA SE COMPROBO Y EXISTE UN ERROR POR LO QUE SE MUESTRA EL MENSAJE DE QUE NO SE PUDO ACTUALIZAR.
                                        JOptionPane.showMessageDialog(this, "No se pudo actualizar los Kg Pesados.", "Error de Actualización", JOptionPane.ERROR_MESSAGE);

                                    }

                                }

                            }

                            this.dispose();

                            Clase1.jButton1.doClick();
                        }

                    } catch (SQLException ex) {
                        // Maneja cualquier excepción que pueda ocurrir durante la carga de datos
                        ex.printStackTrace();
                        JOptionPane.showMessageDialog(this, "Error al cargar los datos desde la base de datos: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                    } finally {
                        // Cerrar la conexión
                        Connexion.cerrarConexion(conn);
                    }

                } else {
                    JOptionPane.showMessageDialog(null, "Cerrando puerto\nNo se puede cerrar el puerto.");
                }
            } catch (Exception ex) {
                // No hacer nada
            }

        }

    }
    // Funcion para cargar los datos al combo box de la bascula con el dato de la anterior clase

    public static void main(String args[]) {

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                Bas f;
                try {
                    f = new Bas();
                    f.setVisible(true);
                    f.setLocationRelativeTo(null);
                    //f.setExtendedState(MAXIMIZED_BOTH);
                } catch (Exception ex) {
                    Logger.getLogger(Bas.class.getName()).log(Level.SEVERE, null, ex);
                }

            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonOpenPort;
    private javax.swing.JDialog jDialog1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabelPeso;
    private javax.swing.JLabel jLabelPortInfo;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea jTextAreaIn;
    private javax.swing.JTextField jTextFieldOut;
    private javax.swing.JLabel lblDato;
    // End of variables declaration//GEN-END:variables

}
