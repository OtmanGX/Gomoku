/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gomokuGui;


import gomokuUtils.GomokuAudioPlayer;
import cli.GomokuBoard;
import cli.GomokuPlay;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sound.sampled.LineUnavailableException;
import javax.swing.JOptionPane;
import static javax.swing.JOptionPane.DEFAULT_OPTION;
import static javax.swing.JOptionPane.ERROR_MESSAGE;
import javax.swing.JPopupMenu;
/**
 *
 * @author otmangx
 */
public class MainFrame extends javax.swing.JFrame {


    GomokuPlay game;
    /**
     * Creates new form MainFrame
     */
    public MainFrame() {
        initComponents();
        jPanel2.setVisible(false);
        jMenuBar1.setVisible(false);
        jToolBar1.setVisible(false);
    }
    
    @Override
    public void validate() {
        super.validate(); //To change body of generated methods, choose Tools | Templates.
        if(game!=null) paintPanel.setPowns(); // to resize the GomokuBoard
    }
    
    
    public void begintheGame(int mode, int level) {
        MainPanel.remove(jPanel1);
                
        MainPanel.add(jPanel2, java.awt.BorderLayout.LINE_END);
        
        MainPanel.add(paintPanel, java.awt.BorderLayout.CENTER,-1);
        
        jPanel2.setVisible(true);
        jMenuBar1.setVisible(true);
        jToolBar1.setVisible(true);
        if(mode!=-1)
            GomokuPlay.newGame(mode);
        if(mode==1) {
            String text="level: ";
            switch(level) {
                case 1:
                    text +="Easy";
                    break;
                case 3:
                    text +="Medium";
                    break;
                case 4:
                    text +="Expert";
                    break;
            }
            levelLabel.setText(text);
        } else if(mode==2)
            levelLabel.setText("");
        game = GomokuPlay.getCurrentGame();
        game.setLevel(level);
        paintPanel.startGame();
        player1.setText(game.p1.getName());
        player2.setText(game.p2.getName());
        changeTurn();
    }
    
        public void changeTurn(){
        player1.setEnabled(game.board.nextPlayer=='x'?true:false);
        player2.setEnabled(game.board.nextPlayer=='o'?true:false);
        repeatButton.setEnabled(game.hasNext());
        undoButton.setEnabled(game.hasPrevious());
        menuitemUndo.setEnabled(game.hasPrevious());
        menuItemRepeat.setEnabled(game.hasNext());
        
        if (game.getWinner()!='.') {
            System.out.println(game.getWinner());
            String msg="";
            switch(game.getWinner()) {
                case 'x' : 
                    msg = "The winner is "+game.p1.getName();
                    if(game.mode>=2) {
                        try {
                            new GomokuAudioPlayer().playwinnerSound();
                        } catch (LineUnavailableException ex) {
                            Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    } else if(game.mode==1){
                        try {
                            new GomokuAudioPlayer().playloserSound();
                        } catch (LineUnavailableException ex) {
                            Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                    break;
                case 'o' :
                    msg = "The winner is "+game.p2.getName();
                    try {
                        new GomokuAudioPlayer().playwinnerSound();
                    } catch (LineUnavailableException ex) {
                        Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    break;
                case 'd' :
                    msg = "It's draw";
                    try {
                            new GomokuAudioPlayer().playdrawSound();
                        } catch (LineUnavailableException ex) {
                            Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, null, ex);
                        }
            }
            
                        String[] choices = {"Replay", "close"};
                        int choice = JOptionPane.showOptionDialog(rootPane, msg, "Winner", JOptionPane.YES_NO_CANCEL_OPTION,
                                JOptionPane.QUESTION_MESSAGE,null, choices, choices[1]);
                        if(choice==0) {
                            replay();
                        } else closeButton.doClick();
                        
        }
        if(game.mode==1 && game.board.nextPlayer=='x') {
            
            game.p1.takeTurn();
            paintPanel.repaint();
            changeTurn();
        }
    }
        
         public void replay() {
             game.mode=3; // enable mode replay
            new Thread(new Runnable() {
                @Override
                public void run() {
                    for(GomokuBoard b: game.listBoards) {
                        try {
                            game.board=b;
                            paintPanel.repaint();
                            changeTurn();
                            Thread.sleep(900);
                        } catch (InterruptedException ex) {
                            Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                }
            }).start();
    }
    
    
    

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        paintPanel = new gomokuGui.paintPanel();
        jFileChooser = new javax.swing.JFileChooser();
        jPanel2 = new javax.swing.JPanel();
        player2 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        player1 = new javax.swing.JLabel();
        levelLabel = new javax.swing.JLabel();
        MainPanel = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        pvpButton = new javax.swing.JButton();
        pvcButton = new javax.swing.JButton();
        loadButton = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jToolBar1 = new javax.swing.JToolBar();
        jButton2 = new javax.swing.JButton();
        closeButton = new javax.swing.JButton();
        jSeparator3 = new javax.swing.JToolBar.Separator();
        saveButton = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jSeparator4 = new javax.swing.JToolBar.Separator();
        undoButton = new javax.swing.JButton();
        repeatButton = new javax.swing.JButton();
        jSeparator2 = new javax.swing.JToolBar.Separator();
        jButton1 = new javax.swing.JButton();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenuItem3 = new javax.swing.JMenuItem();
        jSeparator1 = new javax.swing.JPopupMenu.Separator();
        jMenuItem4 = new javax.swing.JMenuItem();
        jMenuItem5 = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        menuitemUndo = new javax.swing.JMenuItem();
        menuItemRepeat = new javax.swing.JMenuItem();
        jMenuItem6 = new javax.swing.JMenuItem();
        jMenu3 = new javax.swing.JMenu();
        jMenuItem7 = new javax.swing.JMenuItem();

        paintPanel.setMaximumSize(new java.awt.Dimension(600, 400));
        paintPanel.setMinimumSize(new java.awt.Dimension(600, 400));
        paintPanel.setPreferredSize(new java.awt.Dimension(600, 400));
        paintPanel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                paintPanelMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout paintPanelLayout = new javax.swing.GroupLayout(paintPanel);
        paintPanel.setLayout(paintPanelLayout);
        paintPanelLayout.setHorizontalGroup(
            paintPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 428, Short.MAX_VALUE)
        );
        paintPanelLayout.setVerticalGroup(
            paintPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 398, Short.MAX_VALUE)
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("Players"));

        player2.setFont(new java.awt.Font("DejaVu Sans", 1, 14)); // NOI18N
        player2.setText("player2");
        player2.setEnabled(false);

        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ressources/img/black.png"))); // NOI18N

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ressources/img/white.png"))); // NOI18N

        player1.setFont(new java.awt.Font("DejaVu Sans", 1, 14)); // NOI18N
        player1.setText("player1");

        levelLabel.setFont(new java.awt.Font("DejaVu Sans", 1, 14)); // NOI18N
        levelLabel.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        levelLabel.setText("Level : ");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(player1)
                            .addComponent(player2))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(jLabel4))
                        .addGap(0, 5, Short.MAX_VALUE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(levelLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addContainerGap())))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(61, 61, 61)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(player1)
                    .addComponent(jLabel4))
                .addGap(25, 25, 25)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(player2)
                    .addComponent(jLabel2))
                .addGap(73, 73, 73)
                .addComponent(levelLabel)
                .addContainerGap(121, Short.MAX_VALUE))
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Gx Gomoku");
        setPreferredSize(null);
        getContentPane().setLayout(new javax.swing.OverlayLayout(getContentPane()));

        MainPanel.setMinimumSize(new java.awt.Dimension(400, 418));
        MainPanel.setLayout(new java.awt.BorderLayout());

        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        pvpButton.setText("Player Vs Player");
        pvpButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pvpButtonActionPerformed(evt);
            }
        });
        jPanel1.add(pvpButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 120, 146, 40));

        pvcButton.setText("Player Vs Computer");
        pvcButton.setMaximumSize(new java.awt.Dimension(122, 27));
        pvcButton.setMinimumSize(new java.awt.Dimension(122, 27));
        pvcButton.setPreferredSize(new java.awt.Dimension(122, 27));
        pvcButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pvcButtonActionPerformed(evt);
            }
        });
        jPanel1.add(pvcButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 160, 146, 40));

        loadButton.setText("Load Game");
        loadButton.setMaximumSize(new java.awt.Dimension(122, 27));
        loadButton.setMinimumSize(new java.awt.Dimension(122, 27));
        loadButton.setPreferredSize(new java.awt.Dimension(122, 27));
        loadButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                loadButtonActionPerformed(evt);
            }
        });
        jPanel1.add(loadButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 200, 146, 40));

        jButton4.setText("Exit");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton4, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 250, 70, 40));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ressources/img/gomoku.jpeg"))); // NOI18N
        jLabel1.setToolTipText("new game");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(-20, 0, 720, 390));

        MainPanel.add(jPanel1, java.awt.BorderLayout.CENTER);

        jToolBar1.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        jToolBar1.setRollover(true);

        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ressources/img/Apps-File-New-icon.png"))); // NOI18N
        jButton2.setFocusable(false);
        jButton2.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton2.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        jToolBar1.add(jButton2);

        closeButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ressources/img/Windows-Close-Program-icon.png"))); // NOI18N
        closeButton.setToolTipText("leave the game");
        closeButton.setFocusable(false);
        closeButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                closeButtonActionPerformed(evt);
            }
        });
        jToolBar1.add(closeButton);
        jToolBar1.add(jSeparator3);

        saveButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ressources/img/Save-icon.png"))); // NOI18N
        saveButton.setToolTipText("save the game");
        saveButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                saveButtonActionPerformed(evt);
            }
        });
        jToolBar1.add(saveButton);

        jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ressources/img/load2-download-icon.png"))); // NOI18N
        jButton3.setToolTipText("load the game");
        jButton3.setFocusable(false);
        jButton3.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton3.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        jToolBar1.add(jButton3);
        jToolBar1.add(jSeparator4);

        undoButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ressources/img/undo-icon.png"))); // NOI18N
        undoButton.setToolTipText("Undo");
        undoButton.setEnabled(false);
        undoButton.setFocusable(false);
        undoButton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        undoButton.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        undoButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                undoButtonActionPerformed(evt);
            }
        });
        jToolBar1.add(undoButton);

        repeatButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ressources/img/repeat.png"))); // NOI18N
        repeatButton.setToolTipText("Repeat");
        repeatButton.setEnabled(false);
        repeatButton.setFocusable(false);
        repeatButton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        repeatButton.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        repeatButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                repeatButtonActionPerformed(evt);
            }
        });
        jToolBar1.add(repeatButton);

        jSeparator2.setMaximumSize(new java.awt.Dimension(60, 60));
        jSeparator2.setPreferredSize(new java.awt.Dimension(50, 20));
        jToolBar1.add(jSeparator2);

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ressources/img/Help-icon.png"))); // NOI18N
        jButton1.setToolTipText("Hint");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jToolBar1.add(jButton1);

        MainPanel.add(jToolBar1, java.awt.BorderLayout.PAGE_START);

        getContentPane().add(MainPanel);

        jMenu1.setText("File");

        jMenuItem3.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_Q, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItem3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ressources/icons/Windows-Close-Program-icon.png"))); // NOI18N
        jMenuItem3.setText("close");
        jMenuItem3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem3ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem3);
        jMenu1.add(jSeparator1);

        jMenuItem4.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_S, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItem4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ressources/icons/Save-icon.png"))); // NOI18N
        jMenuItem4.setText("Save");
        jMenuItem4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem4ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem4);

        jMenuItem5.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_O, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItem5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ressources/icons/load2-download-icon.png"))); // NOI18N
        jMenuItem5.setText("Load");
        jMenuItem5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem5ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem5);

        jMenuBar1.add(jMenu1);

        jMenu2.setText("Edit");

        menuitemUndo.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_Z, java.awt.event.InputEvent.CTRL_MASK));
        menuitemUndo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ressources/icons/undo-icon.png"))); // NOI18N
        menuitemUndo.setText("Undo");
        menuitemUndo.setEnabled(false);
        menuitemUndo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuitemUndoActionPerformed(evt);
            }
        });
        jMenu2.add(menuitemUndo);

        menuItemRepeat.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_Y, java.awt.event.InputEvent.CTRL_MASK));
        menuItemRepeat.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ressources/icons/Play-Mode-Repeat-All-Hot-icon.png"))); // NOI18N
        menuItemRepeat.setText("Repeat");
        menuItemRepeat.setEnabled(false);
        menuItemRepeat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuItemRepeatActionPerformed(evt);
            }
        });
        jMenu2.add(menuItemRepeat);

        jMenuItem6.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_H, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItem6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ressources/icons/Help-icon.png"))); // NOI18N
        jMenuItem6.setText("Hint");
        jMenuItem6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem6ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem6);

        jMenuBar1.add(jMenu2);

        jMenu3.setText("help");

        jMenuItem7.setText("About");
        jMenuItem7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem7ActionPerformed(evt);
            }
        });
        jMenu3.add(jMenuItem7);

        jMenuBar1.add(jMenu3);

        setJMenuBar(jMenuBar1);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void pvpButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_pvpButtonActionPerformed
        begintheGame(2,0);
        
    }//GEN-LAST:event_pvpButtonActionPerformed

    private void pvcButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_pvcButtonActionPerformed
        LevelDialog dialog = new LevelDialog(this, rootPaneCheckingEnabled);
        int level = dialog.run();
        if(level!=0){
            begintheGame(1,level);
        }
    }//GEN-LAST:event_pvcButtonActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        this.dispose();
    }//GEN-LAST:event_jButton4ActionPerformed

    private void loadButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_loadButtonActionPerformed
        
        int rVal = jFileChooser.showOpenDialog(this);
        if (rVal == javax.swing.JFileChooser.APPROVE_OPTION) {
        String fileopen = (jFileChooser.getSelectedFile().getPath());
        System.out.println(fileopen);
        try {
           FileInputStream file = new FileInputStream(fileopen);
           ObjectInputStream in = new ObjectInputStream(file);
           GomokuPlay.newGame((GomokuPlay) in.readObject());
           in.close();
           file.close();
           begintheGame(-1,0);
        } catch (FileNotFoundException ex) {
           Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
           Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
           Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, null, ex);
        }

        }
    }//GEN-LAST:event_loadButtonActionPerformed

    private void saveButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_saveButtonActionPerformed
        int rVal = jFileChooser.showSaveDialog(this);
       if (rVal == javax.swing.JFileChooser.APPROVE_OPTION) {
        String filename = (jFileChooser.getSelectedFile().getPath());
        System.out.println(filename);
            try {
                FileOutputStream file = new FileOutputStream(filename);
                ObjectOutputStream out = new ObjectOutputStream(file);
                out.writeObject(game);
                out.flush();
                out.close();
                file.close();
            } catch (FileNotFoundException ex) {
                Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, null, ex);
                JOptionPane.showMessageDialog(rootPane, ex, "Write Error !", ERROR_MESSAGE);
            } catch (IOException ex) {
                Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, null, ex);
            }
       }
    }//GEN-LAST:event_saveButtonActionPerformed

    
    private void paintPanelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_paintPanelMouseClicked
        changeTurn();

    }//GEN-LAST:event_paintPanelMouseClicked

    private void closeButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_closeButtonActionPerformed
        
            MainPanel.remove(paintPanel);
            MainPanel.revalidate();
            MainPanel.add(jPanel1, java.awt.BorderLayout.CENTER);
            MainPanel.updateUI();
            jPanel2.setVisible(false);
            jToolBar1.setVisible(false);
            jMenuBar1.setVisible(false);
            try {
                GomokuPlay.endGame();
            } catch (Throwable ex) {
                Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, null, ex);
            }
        
    }//GEN-LAST:event_closeButtonActionPerformed

    private void undoButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_undoButtonActionPerformed
        game.previousBoard();
        repeatButton.setEnabled(true);
        paintPanel.repaint();
        changeTurn();
        if(!game.hasPrevious()) undoButton.setEnabled(false);
        
    }//GEN-LAST:event_undoButtonActionPerformed

    private void repeatButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_repeatButtonActionPerformed
        game.nextBoard();
        paintPanel.repaint();
        changeTurn();
        if (!game.hasNext()) repeatButton.setEnabled(false);
    }//GEN-LAST:event_repeatButtonActionPerformed

    private void jMenuItem3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jMenuItem3ActionPerformed

    private void jMenuItem4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem4ActionPerformed
        saveButton.doClick();
    }//GEN-LAST:event_jMenuItem4ActionPerformed

    private void menuitemUndoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuitemUndoActionPerformed
        undoButton.doClick();
    }//GEN-LAST:event_menuitemUndoActionPerformed

    private void menuItemRepeatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuItemRepeatActionPerformed
        repeatButton.doClick();
    }//GEN-LAST:event_menuItemRepeatActionPerformed

    private void jMenuItem5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem5ActionPerformed
        loadButton.doClick();
    }//GEN-LAST:event_jMenuItem5ActionPerformed

    private void jMenuItem6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem6ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jMenuItem6ActionPerformed

    private void jMenuItem7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem7ActionPerformed
        JOptionPane.showMessageDialog(this, "Gx Gomoku 1.0", "About", JOptionPane.INFORMATION_MESSAGE);
    }//GEN-LAST:event_jMenuItem7ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        int choice = JOptionPane.showConfirmDialog(this, "Are you sure to leave without saving ?",
                "Warning",JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE);
        if(choice==JOptionPane.YES_OPTION)
            closeButton.doClick();
            
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        game.hint=true;
        paintPanel.repaint();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton3ActionPerformed

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
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MainFrame().setVisible(true);
            }
        });
    }

   

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel MainPanel;
    private javax.swing.JButton closeButton;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JFileChooser jFileChooser;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JMenuItem jMenuItem4;
    private javax.swing.JMenuItem jMenuItem5;
    private javax.swing.JMenuItem jMenuItem6;
    private javax.swing.JMenuItem jMenuItem7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPopupMenu.Separator jSeparator1;
    private javax.swing.JToolBar.Separator jSeparator2;
    private javax.swing.JToolBar.Separator jSeparator3;
    private javax.swing.JToolBar.Separator jSeparator4;
    private javax.swing.JToolBar jToolBar1;
    private javax.swing.JLabel levelLabel;
    private javax.swing.JButton loadButton;
    private javax.swing.JMenuItem menuItemRepeat;
    private javax.swing.JMenuItem menuitemUndo;
    private gomokuGui.paintPanel paintPanel;
    private javax.swing.JLabel player1;
    private javax.swing.JLabel player2;
    private javax.swing.JButton pvcButton;
    private javax.swing.JButton pvpButton;
    private javax.swing.JButton repeatButton;
    private javax.swing.JButton saveButton;
    private javax.swing.JButton undoButton;
    // End of variables declaration//GEN-END:variables
}
