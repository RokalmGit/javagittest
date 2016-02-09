package paymenttracker;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;

public class mainFrame extends javax.swing.JFrame {

    private final ArrayList<currency> currencies = new ArrayList();

    public mainFrame() {
        initComponents();
        this.setLocationRelativeTo(null);
        this.setThreads();
    }

    public final void setThreads() {
        Thread t;
        t = new Thread() {
            @Override
            public void run() {
                while (true) {
                    printCurrencyValues();
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException ex) {
                        Logger.getLogger(mainFrame.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        };
        t.start();
    }

    public void performCurrencyActions() {
        if (currencies.size() > 0) {
            String[] currencyListArray = input.getText().split("\\n");
            for (String currency : currencyListArray) {
                currency currencyObject = new currency();
                String[] temp = currency.split(" ");
                for (int x = 0; x < currencies.size(); x++) {
                    if (currencies.get(x).getCurrencyName() == null) {
                        currencies.get(x).setCurrencyName(temp[0]);
                        currencies.get(x).setCurrencyValue(Double.parseDouble(temp[1]));
                        break;
                    } else if (currencies.get(x).getCurrencyName().equals(temp[0])) {
                        currencies.get(x).setCurrencyName(temp[0]);
                        currencies.get(x).setCurrencyValue(Double.parseDouble(temp[1]));
                        break;
                    } else {
                    }
                    if (x == currencies.size() - 1) {
                        currencies.add(currencyObject);
                    }
                }
            }
            currencies.stream().forEach((a) -> {
                System.out.println(a.getCurrencyName() + " " + a.getCurrencyValue());
            });
            System.out.println("----");
        } else {
            String[] currencyListArray = input.getText().split("\\n");
            for (String currency : currencyListArray) {
                currency currencyObject = new currency();
                String[] temp = currency.split(" ");
                currencyObject.setCurrencyName(temp[0]);
                currencyObject.setCurrencyValue(Double.parseDouble(temp[1]));
                currencies.add(currencyObject);
            }
            currencies.stream().forEach((a) -> {
                System.out.println(a.getCurrencyName() + " " + a.getCurrencyValue());
            });
            System.out.println("----");
        }
        currencyList.removeAllItems();
        currencies.stream().forEach((a) -> {
            if (!a.getCurrencyName().equals("USD")) {
                currencyList.addItem(a.getCurrencyName());
            }
        });
    }

    public void performRatioActions() {
        for (int x = 0; x < currencies.size(); x++) {
            if (currencies.get(x).getCurrencyName().equals(currencyList.getSelectedItem().toString())) {
                currencies.get(x).setConversionRatio(Double.parseDouble(conversionRate.getText()));
            }
        }
    }

    public void printCurrencyValues() {
        if (currencies.size() > 0) {
            output.setText("");
            output.setText("CURRENT CURRENCY VALUES\n");
            String printStr = "";
            for (int i = 0; i < currencies.size(); i++) {
                if (currencies.get(i).getCurrencyValue()> 0) {
                    if (currencies.get(i).getConversionRatio() != 0) {
                        printStr += currencies.get(i).getCurrencyName() + " " + currencies.get(i).getCurrencyValue() + " " + "(USD " + currencies.get(i).getCurrencyValue() * currencies.get(i).getConversionRatio() + ")"+"\n";
                    } else {
                        printStr += currencies.get(i).getCurrencyName() + " " + currencies.get(i).getCurrencyValue()+"\n";
                    }
                }
            }
            output.setText(output.getText() + printStr + "\n");
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        output = new javax.swing.JTextArea();
        jScrollPane2 = new javax.swing.JScrollPane();
        input = new javax.swing.JTextArea();
        addFromConsole = new javax.swing.JButton();
        currencyList = new javax.swing.JComboBox<>();
        conversionRate = new javax.swing.JTextField();
        exit = new javax.swing.JButton();
        ratio = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        addFromFile = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        output.setEditable(false);
        output.setColumns(20);
        output.setRows(5);
        jScrollPane1.setViewportView(output);

        input.setColumns(20);
        input.setRows(5);
        jScrollPane2.setViewportView(input);

        addFromConsole.setText("ADD FROM CONSOLE");
        addFromConsole.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addFromConsoleActionPerformed(evt);
            }
        });

        exit.setText("EXIT");
        exit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                exitActionPerformed(evt);
            }
        });

        ratio.setText("ADD USD EXCHANGE RATE");
        ratio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ratioActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("CONSOLE");

        addFromFile.setText("ADD FROM FILE");
        addFromFile.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addFromFileActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addComponent(addFromConsole)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(addFromFile, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(currencyList, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(conversionRate, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(ratio, javax.swing.GroupLayout.DEFAULT_SIZE, 187, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(exit))
                    .addComponent(jScrollPane2)
                    .addComponent(jScrollPane1))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(addFromConsole, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(exit, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(ratio, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(currencyList)
                    .addComponent(conversionRate)
                    .addComponent(addFromFile, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void ratioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ratioActionPerformed
        this.performRatioActions();
    }//GEN-LAST:event_ratioActionPerformed

    private void addFromConsoleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addFromConsoleActionPerformed
        this.performCurrencyActions();
    }//GEN-LAST:event_addFromConsoleActionPerformed

    private void exitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_exitActionPerformed
        System.exit(1);
    }//GEN-LAST:event_exitActionPerformed

    private void addFromFileActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addFromFileActionPerformed
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setCurrentDirectory(new File(System.getProperty("user.home")));
        int result = fileChooser.showOpenDialog(this);
        if (result == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();
            String fullString = null;
            try {
                fullString = new String(Files.readAllBytes(Paths.get(selectedFile.getAbsolutePath())));
            } catch (IOException ex) {
                Logger.getLogger(mainFrame.class.getName()).log(Level.SEVERE, null, ex);
            }
            input.setText(fullString);
        }
        this.performCurrencyActions();
    }//GEN-LAST:event_addFromFileActionPerformed

    public static void main(String args[]) {
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(mainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        java.awt.EventQueue.invokeLater(() -> {
            new mainFrame().setVisible(true);
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton addFromConsole;
    private javax.swing.JButton addFromFile;
    private javax.swing.JTextField conversionRate;
    private javax.swing.JComboBox<String> currencyList;
    private javax.swing.JButton exit;
    private javax.swing.JTextArea input;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextArea output;
    private javax.swing.JButton ratio;
    // End of variables declaration//GEN-END:variables
}
