/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package mpkm;

import jakarta.persistence.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import javax.swing.JOptionPane;
/**
 *
 * @author user
 */
public class pelanggan extends javax.swing.JPanel {

    // ENTITY PELANGGAN
    @Entity
    @Table(name = "pelanggan")
    public static class Pelanggan {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private int id;

        private String nama;
        private String alamat;
        private String telp;

        public Pelanggan() {
        }

        public Pelanggan(String nama, String alamat, String telp) {
            this.nama = nama;
            this.alamat = alamat;
            this.telp = telp;
        }
    }

    // KONFIGURASI HIBERNATE
    private static final SessionFactory factory =
            new Configuration()
                    .configure("hibernate.cfg.xml")
                    .addAnnotatedClass(Pelanggan.class)
                    .buildSessionFactory();

    // CONSTRUCTOR
    public pelanggan() {
        initComponents();

        jButton1.addActionListener(e -> simpan());
        jButton2.addActionListener(e -> edit());
        jButton3.addActionListener(e -> hapus());
    }

    // FUNGSI SIMPAN
    private void simpan() {

        String nama = textField1.getText().trim();
        String alamat = textField2.getText().trim();
        String telp = textField3.getText().trim();

        if (nama.isEmpty() || alamat.isEmpty() || telp.isEmpty()) {
            JOptionPane.showMessageDialog(this,
                    "Semua data harus diisi!");
            return;
        }

        Transaction tx = null;

        try (Session session = factory.openSession()) {

            tx = session.beginTransaction();

            Pelanggan p = new Pelanggan(nama, alamat, telp);

            session.persist(p);

            tx.commit();

            JOptionPane.showMessageDialog(this,
                    "Data pelanggan berhasil disimpan!");

            textField1.setText("");
            textField2.setText("");
            textField3.setText("");

        } catch (Exception e) {

            if (tx != null && tx.isActive()) {
                tx.rollback();
            }

            JOptionPane.showMessageDialog(this,
                    "Gagal menyimpan: " + e.getMessage());
        }
    }

    // FUNGSI EDIT
    private void edit() {

        String idInput = JOptionPane.showInputDialog(this,
                "Masukkan ID pelanggan yang ingin diedit:");

        if (idInput == null || idInput.trim().isEmpty()) {
            return;
        }

        Transaction tx = null;

        try (Session session = factory.openSession()) {

            int id = Integer.parseInt(idInput);

            tx = session.beginTransaction();

            Pelanggan p = session.get(Pelanggan.class, id);

            if (p == null) {
                tx.rollback();

                JOptionPane.showMessageDialog(this,
                        "Data pelanggan tidak ditemukan!");
                return;
            }

            String nama = textField1.getText().trim();
            String alamat = textField2.getText().trim();
            String telp = textField3.getText().trim();

            if (nama.isEmpty() || alamat.isEmpty() || telp.isEmpty()) {
                tx.rollback();

                JOptionPane.showMessageDialog(this,
                        "Semua data harus diisi!");
                return;
            }

            p.nama = nama;
            p.alamat = alamat;
            p.telp = telp;

            tx.commit();

            JOptionPane.showMessageDialog(this,
                    "Data pelanggan berhasil diedit!");

        } catch (Exception e) {

            if (tx != null && tx.isActive()) {
                tx.rollback();
            }

            JOptionPane.showMessageDialog(this,
                    "Gagal mengedit: " + e.getMessage());
        }
    }

    // FUNGSI HAPUS
    private void hapus() {

        String idInput = JOptionPane.showInputDialog(this,
                "Masukkan ID pelanggan yang ingin dihapus:");

        if (idInput == null || idInput.trim().isEmpty()) {
            return;
        }

        Transaction tx = null;

        try (Session session = factory.openSession()) {

            int id = Integer.parseInt(idInput);

            int konfirmasi = JOptionPane.showConfirmDialog(this,
                    "Yakin ingin menghapus data pelanggan?",
                    "Konfirmasi",
                    JOptionPane.YES_NO_OPTION);

            if (konfirmasi != JOptionPane.YES_OPTION) {
                return;
            }

            tx = session.beginTransaction();

            Pelanggan p = session.get(Pelanggan.class, id);

            if (p == null) {
                tx.rollback();

                JOptionPane.showMessageDialog(this,
                        "Data pelanggan tidak ditemukan!");
                return;
            }

            session.remove(p);

            tx.commit();

            JOptionPane.showMessageDialog(this,
                    "Data pelanggan berhasil dihapus!");

            textField1.setText("");
            textField2.setText("");
            textField3.setText("");

        } catch (Exception e) {

            if (tx != null && tx.isActive()) {
                tx.rollback();
            }

            JOptionPane.showMessageDialog(this,
                    "Gagal menghapus: " + e.getMessage());
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        textField1 = new java.awt.TextField();
        textField2 = new java.awt.TextField();
        textField3 = new java.awt.TextField();
        label1 = new java.awt.Label();
        label2 = new java.awt.Label();
        label3 = new java.awt.Label();
        label4 = new java.awt.Label();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();

        label1.setText("PELANGGAN");

        label2.setText("ALAMAT");

        label3.setText("NAMA");

        label4.setText("TELP");

        jButton1.setText("Simpan");

        jButton2.setText("Edit");

        jButton3.setText("Hapus");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(59, 59, 59)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(label2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(label4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(label3, javax.swing.GroupLayout.DEFAULT_SIZE, 90, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 22, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(textField3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(textField2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(textField1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(label1, javax.swing.GroupLayout.DEFAULT_SIZE, 90, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jButton1)
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jButton3)
                            .addComponent(jButton2))))
                .addContainerGap(67, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(label1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(56, 56, 56)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(textField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(26, 26, 26)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(label2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(textField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(22, 22, 22)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(textField3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(label4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(label3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 39, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(jButton2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton3)
                .addGap(15, 15, 15))
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private java.awt.Label label1;
    private java.awt.Label label2;
    private java.awt.Label label3;
    private java.awt.Label label4;
    private java.awt.TextField textField1;
    private java.awt.TextField textField2;
    private java.awt.TextField textField3;
    // End of variables declaration//GEN-END:variables
}