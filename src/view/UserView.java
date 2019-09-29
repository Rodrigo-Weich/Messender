/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import dao.DaoGenerico;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import javax.swing.table.DefaultTableModel;
import model.Mensagem;
import model.Postagem;
import model.Usuario;

/**
 *
 * @author rodri
 */
public class UserView extends javax.swing.JFrame {

    /**
     * Creates new form UserView
     */
    public Usuario logado = (Usuario) DaoGenerico.getInstance().BuscarUnicoWhere(Usuario.class, "usuCod", Long.parseLong(System.getProperty("ID")));
    private List<Mensagem> ME, MR;
    private List<Postagem> P;
    private final DefaultTableModel tblModelP;
    private final DefaultTableModel tblModelR;
    private DefaultTableModel tblModelE;
    public Postagem post;
    public Long postagem;
    private Long cod;
    public Mensagem men;

    public UserView() {
        initComponents();

        jtUsuarioLogado.setText("Logado como: " + logado.getUsuNome());

        // ======================================================================================//
        tblModelP = new DefaultTableModel(new String[]{"Código", "Postagem", "Usuário"}, 3) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        tblModelE = new DefaultTableModel(new String[]{"Código", "Destinatário", "Mensagem", "Enviada", "Lida"}, 5) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        tblModelR = new DefaultTableModel(new String[]{"Código", "Origem", "Mensagem", "Recebida"}, 4) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        // ======================================================================================//

        carregarMEnviadas();
        carregarMRecebidas();
        carregarPostagens();
    }

    private void carregarPostagens() {
        jTabP.setVisible(false);
        tblModelP.getDataVector().clear();
        P = DaoGenerico.getInstance().ListarTodosOrderByDesc(Postagem.class, "postData");
        for (Postagem pos : P) {
            tblModelP.addRow(new Object[]{
                pos.getPostCod(),
                pos.getPostText(),
                pos.getPostUsu().getUsuNome()
            });
        }
        jTabP.setModel(tblModelP);
        jTabP.setVisible(true);
    }

    private void carregarMEnviadas() {
        jTabME.setVisible(false);
        tblModelE.getDataVector().clear();
        ME = (List<Mensagem>) DaoGenerico.getInstance().BuscarTodosWhereOrderByDesc(Mensagem.class, "menDenv", "menOri", logado);
        SimpleDateFormat sdfd = new SimpleDateFormat("dd/MM/yyyy");
        SimpleDateFormat sdft = new SimpleDateFormat("HH:mm:ss");
        for (Mensagem men : ME) {
            if (men.getMenDlid() == null) {
                tblModelE.addRow(new Object[]{
                    men.getMenCod(),
                    men.getMenDes().getUsuNome(),
                    men.getMenText(),
                    sdfd.format(men.getMenDenv().getTime()) + " às " + sdft.format(men.getMenDenv().getTime())
                });
            } else {
                tblModelE.addRow(new Object[]{
                    men.getMenCod(),
                    men.getMenDes().getUsuNome(),
                    men.getMenText(),
                    sdfd.format(men.getMenDenv().getTime()) + " às " + sdft.format(men.getMenDenv().getTime()),
                    sdft.format(men.getMenDlid().getTime())
                });
            }
        }
        jTabME.setModel(tblModelE);
        jTabME.setVisible(true);
    }

    private void carregarMRecebidas() {
        jTabMR.setVisible(false);
        tblModelR.getDataVector().clear();
        MR = (List<Mensagem>) DaoGenerico.getInstance().BuscarTodosWhereOrderByDesc(Mensagem.class, "menDrec", "menDes", logado);
        SimpleDateFormat sdfd = new SimpleDateFormat("dd/MM/yyyy");
        SimpleDateFormat sdft = new SimpleDateFormat("HH:mm:ss");
        for (Mensagem men : MR) {
            if (men.getMenDrec() == null) {
                men.setMenDrec(Calendar.getInstance());
                DaoGenerico.getInstance().Alterar(men);
            }
        }
        for (Mensagem men : MR) {
            tblModelR.addRow(new Object[]{
                men.getMenCod(),
                men.getMenOri().getUsuNome(),
                men.getMenText(),
                sdfd.format(men.getMenDrec().getTime()) + " às " + sdft.format(men.getMenDrec().getTime())
            });
        }
        jTabMR.setModel(tblModelR);
        jTabMR.setVisible(true);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel3 = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        jtUsuarioLogado = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTabME = new javax.swing.JTable();
        jLabel5 = new javax.swing.JLabel();
        jbNovaMensagem = new javax.swing.JButton();
        jbNovoPost = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTabP = new javax.swing.JTable();
        jPanel5 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTabMR = new javax.swing.JTable();
        jbPainelAdm = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Messender");
        setResizable(false);

        jPanel3.setBackground(new java.awt.Color(204, 204, 255));

        jPanel1.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.LOWERED));

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/loginuser32.png"))); // NOI18N
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jtUsuarioLogado, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jtUsuarioLogado, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/graphic-logo64.png"))); // NOI18N

        jLabel2.setFont(new java.awt.Font("MS Reference Sans Serif", 1, 18)); // NOI18N
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel2.setText("Messender");

        jLabel3.setFont(new java.awt.Font("Segoe Print", 0, 10)); // NOI18N
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel3.setText("a nova definição do sucesso");

        jPanel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jTabME.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        jTabME.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTabMEMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTabME);

        jLabel5.setFont(new java.awt.Font("Arial Black", 0, 12)); // NOI18N
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel5.setText("Enviadas");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 478, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1)
                .addContainerGap())
        );

        jbNovaMensagem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/sendmessage.png"))); // NOI18N
        jbNovaMensagem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbNovaMensagemActionPerformed(evt);
            }
        });

        jbNovoPost.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/post-it.png"))); // NOI18N
        jbNovoPost.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbNovoPostActionPerformed(evt);
            }
        });

        jPanel4.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel1.setFont(new java.awt.Font("Arial Black", 0, 12)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Postagens");

        jTabP.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        jTabP.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTabPMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(jTabP);

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 328, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 544, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel5.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel4.setFont(new java.awt.Font("Arial Black", 0, 12)); // NOI18N
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setText("Recebidas");

        jTabMR.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        jTabMR.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTabMRMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(jTabMR);

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 378, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2)
                .addContainerGap())
        );

        jbPainelAdm.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/admin.png"))); // NOI18N
        jbPainelAdm.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbPainelAdmActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jSeparator1, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addComponent(jbNovoPost)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jbNovaMensagem)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jbPainelAdm)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jbNovaMensagem, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jbNovoPost, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jbPainelAdm, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel3)))
                .addGap(11, 11, 11)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel5, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jbNovaMensagemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbNovaMensagemActionPerformed
        // TODO add your handling code here:
        NovaMensagemView nmv = new NovaMensagemView(this, true);
        nmv.setVisible(true);
        carregarMEnviadas();
        carregarMRecebidas();
    }//GEN-LAST:event_jbNovaMensagemActionPerformed

    private void jbPainelAdmActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbPainelAdmActionPerformed
        // TODO add your handling code here:
        AdminView av = new AdminView();
        av.setVisible(true);
    }//GEN-LAST:event_jbPainelAdmActionPerformed

    private void jbNovoPostActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbNovoPostActionPerformed
        // TODO add your handling code here:
        NovaPostagemView npv = new NovaPostagemView(this, true);
        npv.setVisible(true);

        post = null;
        carregarPostagens();
    }//GEN-LAST:event_jbNovoPostActionPerformed

    private void jTabPMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTabPMouseClicked
        // TODO add your handling code here:
        if (evt.getClickCount() >= 2) {
            int linha = jTabP.getSelectedRow();
            cod = (Long) jTabP.getValueAt(linha, 0);
            post = (Postagem) DaoGenerico.getInstance().BuscarUnicoWhere(Postagem.class, "postCod", cod);
            postagem = post.getPostCod();

            PostagemView pv = new PostagemView(this, true);
            pv.setVisible(true);

            post = null;
            carregarPostagens();
        }
    }//GEN-LAST:event_jTabPMouseClicked

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        PerfilView pv = new PerfilView();
        pv.setVisible(true);
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jTabMEMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTabMEMouseClicked
        // TODO add your handling code here:
        if (evt.getClickCount() >= 2) {
            int linha = jTabME.getSelectedRow();
            Long code = (Long) jTabME.getValueAt(linha, 0);
            men = (Mensagem) DaoGenerico.getInstance().BuscarUnicoWhere(Mensagem.class, "menCod", code);

            VerMensagemView vmv = new VerMensagemView(this, true);
            vmv.setVisible(true);

            men = null;
            carregarMEnviadas();
            carregarMRecebidas();
        }
    }//GEN-LAST:event_jTabMEMouseClicked

    private void jTabMRMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTabMRMouseClicked
        // TODO add your handling code here:
        if (evt.getClickCount() >= 2) {
            int linha = jTabMR.getSelectedRow();
            Long code = (Long) jTabMR.getValueAt(linha, 0);
            men = (Mensagem) DaoGenerico.getInstance().BuscarUnicoWhere(Mensagem.class, "menCod", code);

            if (men.getMenDlid() == null) {
                men.setMenDlid(Calendar.getInstance());
                DaoGenerico.getInstance().Alterar(men);
            }
            
            VerMensagemView vmv = new VerMensagemView(this, true);
            vmv.setVisible(true);

            men = null;
            carregarMEnviadas();
            carregarMRecebidas();
        }
    }//GEN-LAST:event_jTabMRMouseClicked

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
            java.util.logging.Logger.getLogger(UserView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(UserView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(UserView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(UserView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new UserView().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JTable jTabME;
    private javax.swing.JTable jTabMR;
    private javax.swing.JTable jTabP;
    private javax.swing.JButton jbNovaMensagem;
    private javax.swing.JButton jbNovoPost;
    private javax.swing.JButton jbPainelAdm;
    private javax.swing.JLabel jtUsuarioLogado;
    // End of variables declaration//GEN-END:variables
}
