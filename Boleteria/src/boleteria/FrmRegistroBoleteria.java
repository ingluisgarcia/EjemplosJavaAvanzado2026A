/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package boleteria;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author cymaniatico
 */

public class FrmRegistroBoleteria extends javax.swing.JFrame {

    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(FrmRegistroBoleteria.class.getName());

    /**
     * Creates new form FrmRegistroBoleteria
     */
    ArrayList<Boleta> listaBoleta;
    String nombre;
    int valor;
    int idBoleta;
    InfoConexion conexion;

    public FrmRegistroBoleteria() {
        initComponents();
        listaBoleta = new ArrayList();
        conexion = new InfoConexion();
        txtNombre.setText("");
        llenarCombo();
        mostrarInfo();
    }

    public void llenarCombo() {
        try (Connection con = DriverManager.getConnection(
                conexion.getUrl(),
                conexion.getUsername(), conexion.getPassword())) {

            comboBoleta.removeAllItems();
            comboBoleta.addItem("Seleccione...");
            listaBoleta.clear();
            Statement stm = con.createStatement();
            String query = "call comboBoleta()";
            ResultSet rs = stm.executeQuery(query);

            while (rs.next()) {
                int id = rs.getInt("id");
                String nombreBoleta = rs.getString("descripcion");
                int precioBoleta = rs.getInt("precio");
                listaBoleta.add(new Boleta(id, nombreBoleta,
                        precioBoleta));
                comboBoleta.addItem(nombreBoleta);
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(rootPane, e.toString());
        }
    }

    public void guardar() {
        try (Connection con = DriverManager.getConnection(
                conexion.getUrl(),
                conexion.getUsername(), conexion.getPassword())) {
            int pos = comboBoleta.getSelectedIndex() - 1;
            nombre = txtNombre.getText();
            valor = listaBoleta.get(pos).getPrecio();
            idBoleta = listaBoleta.get(pos).getId();
            PreparedStatement pstm = con.prepareCall("call registrar(?,?,?)");
            pstm.setString(1, nombre);
            pstm.setInt(2, valor);
            pstm.setInt(3, idBoleta);
            
            pstm.executeQuery();
            
            JOptionPane.showMessageDialog(rootPane, "Dato guardado");
            mostrarInfo();
                        
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(rootPane, e.toString());
        }
    }
        
        public void mostrarInfo(){
            try (Connection con = DriverManager.getConnection(
                conexion.getUrl(),
                conexion.getUsername(), conexion.getPassword())) {
                DefaultTableModel modelo = new DefaultTableModel();
                Statement stm = con.createStatement();
                String query = "call mostrar()";
                ResultSet rs = stm.executeQuery(query);
                ResultSetMetaData rsmt = rs.getMetaData();
                for(int i=1; i<= rsmt.getColumnCount(); i++){
                    modelo.addColumn(rsmt.getColumnLabel(i));
                }                
                while(rs.next()){
                    Object[] filas = new Object[rsmt.getColumnCount()];
                    for(int i = 1; i<=rsmt.getColumnCount(); i++){
                        filas[i-1] = rs.getObject(i);
                    }
                    modelo.addRow(filas);                
                }
                tableRegistro.setModel(modelo);
            } catch (SQLException e) {
            JOptionPane.showMessageDialog(rootPane, e.toString());
        }        
    }

        
        public void filtrar(){
            try (Connection con = DriverManager.getConnection(
                conexion.getUrl(),
                conexion.getUsername(), conexion.getPassword())) {
                DefaultTableModel modelo = new DefaultTableModel();
                int id = listaBoleta.get(comboBoleta.getSelectedIndex()-1).getId();
                PreparedStatement pstm = con.prepareCall("call filtrar(?)");
                pstm.setInt(1, id);               
                
                ResultSet rs = pstm.executeQuery();
                ResultSetMetaData rsmt = rs.getMetaData();
                for(int i=1; i<= rsmt.getColumnCount(); i++){
                    modelo.addColumn(rsmt.getColumnLabel(i));
                }                
                while(rs.next()){
                    Object[] filas = new Object[rsmt.getColumnCount()];
                    for(int i = 1; i<=rsmt.getColumnCount(); i++){
                        filas[i-1] = rs.getObject(i);
                    }
                    modelo.addRow(filas);                
                }
                tableRegistro.setModel(modelo);
            } catch (SQLException e) {
            JOptionPane.showMessageDialog(rootPane, e.toString());
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
        txtNombre = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        comboBoleta = new javax.swing.JComboBox<>();
        btnRegistrar = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tableRegistro = new javax.swing.JTable();
        btnFiltrar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setText("Ingrese nombre");

        txtNombre.setText("jTextField1");

        jLabel2.setText("Seleccione la boleta");

        comboBoleta.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        btnRegistrar.setText("Registrar");
        btnRegistrar.addActionListener(this::btnRegistrarActionPerformed);

        tableRegistro.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(tableRegistro);

        btnFiltrar.setText("Filtrar boletas vendidas");
        btnFiltrar.addActionListener(this::btnFiltrarActionPerformed);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 605, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(jLabel1)
                                .addComponent(txtNombre)
                                .addComponent(jLabel2)
                                .addComponent(comboBoleta, 0, 198, Short.MAX_VALUE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(btnRegistrar)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(btnFiltrar)))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(comboBoleta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnRegistrar)
                    .addComponent(btnFiltrar))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 289, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnRegistrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRegistrarActionPerformed
        // TODO add your handling code here:
        if (!txtNombre.getText().isEmpty() && comboBoleta.getSelectedIndex() > 0) {
            guardar();
        } else {
            JOptionPane.showMessageDialog(rootPane, "Faltan campos por diligenciar");
        }

    }//GEN-LAST:event_btnRegistrarActionPerformed

    private void btnFiltrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFiltrarActionPerformed
        // TODO add your handling code here:
        if(comboBoleta.getSelectedIndex()>0){
            filtrar();
        }else{
            JOptionPane.showMessageDialog(rootPane, "Seleccione una boleta");
        }
    }//GEN-LAST:event_btnFiltrarActionPerformed

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
    } catch (ReflectiveOperationException | javax.swing.UnsupportedLookAndFeelException ex) {
        logger.log(java.util.logging.Level.SEVERE, null, ex);
    }
    //</editor-fold>

    /* Create and display the form */
    java.awt.EventQueue.invokeLater(() -> new FrmRegistroBoleteria().setVisible(true));
}

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnFiltrar;
    private javax.swing.JButton btnRegistrar;
    private javax.swing.JComboBox<String> comboBoleta;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tableRegistro;
    private javax.swing.JTextField txtNombre;
    // End of variables declaration//GEN-END:variables
}
