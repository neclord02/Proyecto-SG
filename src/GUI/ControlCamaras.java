package GUI;

import Model.Camara;
import Model.TheUniverse;
import javax.media.j3d.Canvas3D;
import javax.media.j3d.View;

public class ControlCamaras extends javax.swing.JFrame {
    private TheUniverse universe;
    private Canvas3D canvas;
    private View view;
    private Camara camLuna, camNave;
    private boolean[] activa;

    public ControlCamaras(TheUniverse universe, Camara camLuna, Camara camNave) {
        this.universe= universe;
        this.activa = new boolean[]{true, false, false};
        this.view= universe.getView();
        this.camLuna= camLuna;
        this.camNave= camNave;
        this.canvas= universe.getCanvas();
        
        initComponents();
        showWindow();
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
        ButtonPersp = new javax.swing.JButton();
        ButtonLuna = new javax.swing.JButton();
        ButtonNave = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setText("Control de cámaras.");

        ButtonPersp.setText("Perspectiva");
        ButtonPersp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ButtonPerspActionPerformed(evt);
            }
        });

        ButtonLuna.setText("Luna");
        ButtonLuna.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ButtonLunaActionPerformed(evt);
            }
        });

        ButtonNave.setText("Nave");
        ButtonNave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ButtonNaveActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(144, 144, 144)
                .addComponent(jLabel1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(ButtonPersp)
                .addGap(63, 63, 63)
                .addComponent(ButtonLuna)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 70, Short.MAX_VALUE)
                .addComponent(ButtonNave)
                .addGap(56, 56, 56))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGap(33, 33, 33)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(ButtonPersp)
                    .addComponent(ButtonLuna)
                    .addComponent(ButtonNave))
                .addContainerGap(27, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void ButtonPerspActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ButtonPerspActionPerformed
        
        if(activa[1]){
            camLuna.eliminarCanvas();
            activa[1]=false;
        }
        else if(activa[2]){
            camNave.eliminarCanvas();
            activa[2]=false;
        }

        if(!activa[0]){
            activa[0]= true;
            view.addCanvas3D(canvas);
        }

    }//GEN-LAST:event_ButtonPerspActionPerformed

    private void ButtonLunaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ButtonLunaActionPerformed
        if(activa[0]){
            view.removeAllCanvas3Ds();
            activa[0]=false;
        }
        else if(activa[2]){
            camNave.eliminarCanvas();
            activa[2]=false;
        }
        if(!activa[1]){
            activa[1]= true;
            camLuna.addCanvas(canvas);
        }

    }//GEN-LAST:event_ButtonLunaActionPerformed

    private void ButtonNaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ButtonNaveActionPerformed
        if(activa[0]){
            view.removeAllCanvas3Ds();
            activa[0]=false;
        }
        else if(activa[1]){
            camLuna.eliminarCanvas();
            activa[1]=false;
        }
        if(!activa[2]){
            activa[2]= true;
            camNave.addCanvas(canvas);
        }

    }//GEN-LAST:event_ButtonNaveActionPerformed

    public void showWindow () {
        setVisible (true);
    }
    
    void closeApp (int code) {
        universe.closeApp(code);
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton ButtonLuna;
    private javax.swing.JButton ButtonNave;
    private javax.swing.JButton ButtonPersp;
    private javax.swing.JLabel jLabel1;
    // End of variables declaration//GEN-END:variables
}