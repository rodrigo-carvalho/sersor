package gui;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import javax.imageio.ImageIO;
import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.MatOfByte;
//import org.opencv.highgui.Highgui;
//import org.opencv.highgui.VideoCapture;
import org.opencv.videoio.VideoCapture;
import org.opencv.imgcodecs.Imgcodecs;


import org.bytedeco.javacv.FFmpegFrameRecorder;
import org.bytedeco.javacv.Frame;
import org.bytedeco.javacv.FrameGrabber;
import org.bytedeco.javacv.FrameRecorder.Exception;
import org.bytedeco.javacv.Java2DFrameConverter;
import org.bytedeco.javacv.OpenCVFrameConverter;
import org.bytedeco.javacv.OpenCVFrameGrabber;

import static java.lang.Thread.sleep;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JPanel;
import org.bytedeco.javacpp.avcodec;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import com.fazecast.jSerialComm.SerialPort;
import java.awt.BorderLayout;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.concurrent.TimeUnit;
import org.jfree.data.general.DefaultPieDataset;

public class Javacam extends javax.swing.JFrame{
//Arduino
    static SerialPort chosenPort;
    static int x = 0;
    
    int count = 0;
    VideoCapture webSource = null;

    Mat frame = new Mat();
    MatOfByte mem = new MatOfByte();
//    private JPanel canvas;
    
    private static FFmpegFrameRecorder recorder = null;
    private static OpenCVFrameGrabber grabber = null;
    private static OpenCVFrameGrabber grabberi = null;
    private static final int WEBCAM_DEVICE_INDEX = 0;
    //Gravação do video da webcam
    private static final int CAPTUREWIDTH = 800; //largura 
    private static final int CAPTUREHRIGHT = 600; //Altura
    private static final int FRAME_RATE = 30;
    private static final int GOP_LENGTH_IN_FRAMES = 60;
    private volatile boolean runnable = true;
    private static final long serialVersionUID = 1L;
    private Catcher cat;
    private Catcheri cati;
    private Thread catcher;
    private Thread catcheri;
    private Thread threadg;
    int tempo = 0;
    String nome = "victor";
    
    private final String path = "C:\\Users\\PC-Victor\\Documents\\GitHub\\sersor\\Sensor\\ "+ nome + ".txt";
          
       
    SerialPort[] portNames = SerialPort.getCommPorts();
    
    public Javacam() {
        initComponents();
        grabberi = new OpenCVFrameGrabber(WEBCAM_DEVICE_INDEX);
        grabber = new OpenCVFrameGrabber(WEBCAM_DEVICE_INDEX);
        cat = new Catcher();
        cati = new Catcheri();
        catcheri = new Thread(cati);
        catcheri.start();
        
        //runnable = true;
        //jPanel1.add(new ChartPanel(chart), BorderLayout.CENTER);
        //window.add(new ChartPanel(chart), BorderLayout.CENTER);
        
         
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btn_start = new javax.swing.JButton();
        btn_stop = new javax.swing.JButton();
        canvas1 = new java.awt.Canvas();
        jLabel1 = new javax.swing.JLabel();
        btn_conectar = new javax.swing.JButton();
        jComboBox1 = new javax.swing.JComboBox<>();
        T_ECG = new java.awt.Label();
        jPanel1 = new javax.swing.JPanel();
        T_GSR = new java.awt.Label();
        jPanel_GSR = new javax.swing.JPanel();
        T_EMG = new java.awt.Label();
        jPanel_EMG = new javax.swing.JPanel();
        txt_nomeProjeto = new javax.swing.JTextField();
        T_Coleta = new javax.swing.JLabel();
        txt_coleta = new javax.swing.JTextField();
        T_Individuo = new javax.swing.JLabel();
        txt_individuo = new javax.swing.JTextField();
        T_numero = new javax.swing.JLabel();
        txt_numero = new javax.swing.JTextField();
        T_Pesquisador = new javax.swing.JLabel();
        txt_pesquisador = new javax.swing.JTextField();
        T_NomeProjeto = new javax.swing.JLabel();
        T_NomeProjeto1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setAlwaysOnTop(true);
        setBackground(new java.awt.Color(102, 102, 102));
        setMinimumSize(new java.awt.Dimension(1024, 768));
        setPreferredSize(new java.awt.Dimension(1027, 768));
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btn_start.setText("Start");
        btn_start.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_startActionPerformed(evt);
            }
        });
        getContentPane().add(btn_start, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 420, 70, -1));

        btn_stop.setText("Stop");
        btn_stop.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_stopActionPerformed(evt);
            }
        });
        getContentPane().add(btn_stop, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 420, 70, -1));
        getContentPane().add(canvas1, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 120, 410, 280));

        jLabel1.setFont(new java.awt.Font("Lucida Console", 0, 24)); // NOI18N
        jLabel1.setText("PhysioMetrics");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 10, -1, 20));

        btn_conectar.setText("Conectar");
        btn_conectar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_conectarActionPerformed(evt);
            }
        });
        getContentPane().add(btn_conectar, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 420, 120, -1));

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jComboBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox1ActionPerformed(evt);
            }
        });
        getContentPane().add(jComboBox1, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 420, 90, -1));

        T_ECG.setFont(new java.awt.Font("Dialog", 0, 24)); // NOI18N
        T_ECG.setText("ECG");
        getContentPane().add(T_ECG, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 510, -1, -1));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 870, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 60, Short.MAX_VALUE)
        );

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 490, 870, 60));

        T_GSR.setFont(new java.awt.Font("Dialog", 0, 24)); // NOI18N
        T_GSR.setText("GSR");
        getContentPane().add(T_GSR, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 590, -1, -1));

        jPanel_GSR.setBackground(new java.awt.Color(51, 51, 51));

        javax.swing.GroupLayout jPanel_GSRLayout = new javax.swing.GroupLayout(jPanel_GSR);
        jPanel_GSR.setLayout(jPanel_GSRLayout);
        jPanel_GSRLayout.setHorizontalGroup(
            jPanel_GSRLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 870, Short.MAX_VALUE)
        );
        jPanel_GSRLayout.setVerticalGroup(
            jPanel_GSRLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 60, Short.MAX_VALUE)
        );

        getContentPane().add(jPanel_GSR, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 570, -1, -1));

        T_EMG.setFont(new java.awt.Font("Dialog", 0, 24)); // NOI18N
        T_EMG.setText("EMG");
        getContentPane().add(T_EMG, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 670, -1, -1));

        jPanel_EMG.setBackground(new java.awt.Color(51, 51, 51));

        javax.swing.GroupLayout jPanel_EMGLayout = new javax.swing.GroupLayout(jPanel_EMG);
        jPanel_EMG.setLayout(jPanel_EMGLayout);
        jPanel_EMGLayout.setHorizontalGroup(
            jPanel_EMGLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 870, Short.MAX_VALUE)
        );
        jPanel_EMGLayout.setVerticalGroup(
            jPanel_EMGLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 60, Short.MAX_VALUE)
        );

        getContentPane().add(jPanel_EMG, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 650, -1, -1));
        getContentPane().add(txt_nomeProjeto, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 50, 200, -1));

        T_Coleta.setText("Coleta");
        getContentPane().add(T_Coleta, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 50, -1, -1));
        getContentPane().add(txt_coleta, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 50, 180, -1));

        T_Individuo.setText("Indivíduo");
        getContentPane().add(T_Individuo, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 50, -1, -1));
        getContentPane().add(txt_individuo, new org.netbeans.lib.awtextra.AbsoluteConstraints(650, 50, 170, -1));

        T_numero.setText("Número");
        getContentPane().add(T_numero, new org.netbeans.lib.awtextra.AbsoluteConstraints(830, 50, -1, -1));
        getContentPane().add(txt_numero, new org.netbeans.lib.awtextra.AbsoluteConstraints(880, 50, 70, -1));

        T_Pesquisador.setText("Pesquisador");
        getContentPane().add(T_Pesquisador, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 80, -1, -1));
        getContentPane().add(txt_pesquisador, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 80, 70, -1));

        T_NomeProjeto.setText("Dados");
        getContentPane().add(T_NomeProjeto, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 430, -1, -1));

        T_NomeProjeto1.setText("Nome do Projeto");
        getContentPane().add(T_NomeProjeto1, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 50, -1, -1));

        jTextArea1.setColumns(20);
        jTextArea1.setRows(5);
        jScrollPane1.setViewportView(jTextArea1);

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 370, -1, -1));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btn_startActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_startActionPerformed
        
        catcheri.stop(); // Stop Preview
        catcher = new Thread(cat); //Start Recording
        catcher.start();
        runnable = true;
            
        //Arduino data Save
        File file = new File(path);
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(file));
            Thread threadg = new Thread(){
                    @Override public void run() {
                        System.out.println("inicio");
                        Scanner scanner = new Scanner(chosenPort.getInputStream());
                        while(scanner.hasNextLine()) {
                           
                            tempo++;
                            String line = scanner.nextLine();
                            try {
                                
                                writer.write("Tempo: " + tempo + " Dado: " + line);
                                //System.out.println(line);
                                writer.newLine();  //Escreveu uma linha
                            } catch (IOException ex) {
                                Logger.getLogger(Javacam.class.getName()).log(Level.SEVERE, null, ex);
                            }
                                                    
                            try { 
                                TimeUnit.MILLISECONDS.sleep(920);
                            } catch (InterruptedException ex) {
                                Logger.getLogger(Javacam.class.getName()).log(Level.SEVERE, null, ex);
                            }
                            
                            //System.out.println("Tempo: " + tempo);
                            try {
                                writer.flush();
                            } catch (IOException ex) {
                                Logger.getLogger(Javacam.class.getName()).log(Level.SEVERE, null, ex);
                            }
                           
                        }
                        scanner.close();
                        try {
                            writer.close();
                        } catch (IOException ex) {
                            Logger.getLogger(Javacam.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                };
            
         threadg.start();
            
            
            
        } catch (IOException ex) {
            Logger.getLogger(Javacam.class.getName()).log(Level.SEVERE, null, ex);
        }
           
    }//GEN-LAST:event_btn_startActionPerformed

    private void btn_stopActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_stopActionPerformed
        threadg.interrupt();
        catcheri.interrupt();
        catcher.interrupt();
        
        runnable = false;
        try {
            recorder.stop();
        } catch (Exception ex) {
            Logger.getLogger(Javacam.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        //Limpar o gráfico
        
    }//GEN-LAST:event_btn_stopActionPerformed

    private void btn_conectarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_conectarActionPerformed
       
        /*Isso aqui não ta executando
        XYSeries series = new XYSeries("Light Sensor Readings");
        XYSeriesCollection dataset = new XYSeriesCollection(series);
        JFreeChart chart = ChartFactory.createXYLineChart("Light Sensor Readings", "Time (seconds)", "ADC Reading", dataset);

        jPanel1.add(new ChartPanel(chart), BorderLayout.CENTER);    
*/
    // TODO add your handling code here:
            if(btn_conectar.getText().equals("Conectar")) {
            // attempt to connect to the serial port
                chosenPort = SerialPort.getCommPort(jComboBox1.getSelectedItem().toString());
                chosenPort.setComPortTimeouts(SerialPort.TIMEOUT_SCANNER, 0, 0);
                if(chosenPort.openPort()) {
                        btn_conectar.setText("Disconectar");
                        jComboBox1.setEnabled(false);
                }

            // create a new thread that listens for incoming text and populates the graph
            //No arduino está assim Serial.println(heart_rate);
                Thread thread = new Thread(){
                    @Override public void run() {
                        //System.out.println("teste1");
                        Scanner scanner = new Scanner(chosenPort.getInputStream());
                        while(scanner.hasNextLine()) {
                            String line = scanner.nextLine();
                            jTextArea1.setText(line);//imprime na tela oq vem do arduino

//Colocando o valor do HR em uma variavel, agora é só converter pra int e imprimir no gráfico
                            String [] valorComSplit = line.split("HR");
                                for (String hrValor : valorComSplit ){
                                    System.out.println("o valor do HR é " + hrValor);
                                }
                            
                            jPanel1.repaint();                                                                                                                              
                        }
                        scanner.close();
                    }
                };
                //System.out.println("teste4");
            thread.start();
            //System.out.println("teste5");
            } else {
                    // disconnect from the serial port
                    chosenPort.closePort();
                    jComboBox1.setEnabled(true);
                    btn_conectar.setText("Connect");
                    //series.clear();
                    //x = 0;
            }
        
    }//GEN-LAST:event_btn_conectarActionPerformed

    private void jComboBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox1ActionPerformed
        for(int i = 0; i < portNames.length; i++){
            jComboBox1.addItem(portNames[i].getSystemPortName());
        }
      
    }//GEN-LAST:event_jComboBox1ActionPerformed

    class Catcheri implements Runnable {
        @Override
        public void run() {
            synchronized (this) {
               // while (runnable) {
                    try {
                        grabberi.setImageWidth(CAPTUREWIDTH);
                        grabberi.setImageHeight(CAPTUREHRIGHT);
                        grabberi.start();
                        
                        Frame capturedFrame = null;
                        Java2DFrameConverter paintConverter = new Java2DFrameConverter();
                        long startTime = System.currentTimeMillis();
                        long frame = 0;
                        while ((capturedFrame = grabberi.grab()) != null&&runnable) {
                            BufferedImage buff = paintConverter.getBufferedImage(capturedFrame, 1);
                            Graphics g = canvas1.getGraphics();
                            g.drawImage(buff, 0, 0, CAPTUREWIDTH, CAPTUREHRIGHT, 0, 0, buff.getWidth(), buff.getHeight(), null);
                            //recorder.record(capturedFrame);
                            frame++;
                            long waitMillis = 1000 * frame / FRAME_RATE - (System.currentTimeMillis() - startTime);
                            while (waitMillis <= 0) {
                                // If this error appeared, better to consider lower FRAME_RATE.
                                //System.out.println("[ERROR] grab image operation is too slow to encode, skip grab image at frame = " + frame + ", waitMillis = " + waitMillis);
                                //recorder.record(capturedFrame);  // use same capturedFrame for fast processing.
                                frame++;
                                waitMillis = 1000 * frame / FRAME_RATE - (System.currentTimeMillis() - startTime);
                            }
                            //System.out.println("frame " + frame + ", System.currentTimeMillis() = " + System.currentTimeMillis() + ", waitMillis = " + waitMillis);
                            Thread.sleep(waitMillis);
                        }
                    } catch (FrameGrabber.Exception ex) {
                    Logger.getLogger(Javacam.class.getName()).log(Level.SEVERE, null, ex);
                    }  catch (InterruptedException ex) {
                        Logger.getLogger(Javacam.class.getName()).log(Level.SEVERE, null, ex);
                    }

                //}//end of while
            }
        }
    }
    
    class Catcher implements Runnable {

        @Override
        public void run() {
            synchronized (this) {
               // while (runnable) {
                    try {
                        
                        grabber.setImageWidth(CAPTUREWIDTH);
                        grabber.setImageHeight(CAPTUREHRIGHT);
                        grabber.start();
//Modificar o nome do aquivo conforme a coleta
                        recorder = new FFmpegFrameRecorder(nome + ".mp4",
                                CAPTUREWIDTH, CAPTUREHRIGHT, 2);
                        recorder.setInterleaved(true);
                        // video options //
                        recorder.setVideoOption("tune", "zerolatency");
                        recorder.setVideoOption("preset", "ultrafast");
                        recorder.setVideoOption("crf", "28");
                        recorder.setVideoBitrate(2000000);
                        recorder.setVideoCodec(avcodec.AV_CODEC_ID_H264);
                        
                        recorder.setVideoCodec(HIDE_ON_CLOSE);
                        
                        recorder.setFormat("mp4");
                        recorder.setFrameRate(FRAME_RATE);
                        recorder.setGopSize(GOP_LENGTH_IN_FRAMES);
                        // audio options //
                        recorder.setAudioOption("crf", "0");
                        recorder.setAudioQuality(0);
                        recorder.setAudioBitrate(192000);
                        recorder.setSampleRate(44100);
                        recorder.setAudioChannels(2);
                        recorder.setAudioCodec(avcodec.AV_CODEC_ID_AAC);

                        recorder.start();

                        Frame capturedFrame = null;
                        Java2DFrameConverter paintConverter = new Java2DFrameConverter();
                        long startTime = System.currentTimeMillis();
                        long frame = 0;
                        while ((capturedFrame = grabber.grab()) != null&&runnable) {
                            BufferedImage buff = paintConverter.getBufferedImage(capturedFrame, 1);
                            Graphics g = canvas1.getGraphics();
                            g.drawImage(buff, 0, 0, CAPTUREWIDTH, CAPTUREHRIGHT, 0, 0, buff.getWidth(), buff.getHeight(), null);
                            recorder.record(capturedFrame);
                            frame++;
                            long waitMillis = 1000 * frame / FRAME_RATE - (System.currentTimeMillis() - startTime);
                            while (waitMillis <= 0) {
                                // If this error appeared, better to consider lower FRAME_RATE.
                                //System.out.println("[ERROR] grab image operation is too slow to encode, skip grab image at frame = " + frame + ", waitMillis = " + waitMillis);
                                recorder.record(capturedFrame);  // use same capturedFrame for fast processing.
                                frame++;
                                waitMillis = 1000 * frame / FRAME_RATE - (System.currentTimeMillis() - startTime);
                            }
                            //System.out.println("frame " + frame + ", System.currentTimeMillis() = " + System.currentTimeMillis() + ", waitMillis = " + waitMillis);
                            Thread.sleep(waitMillis);
                        }
                    } catch (FrameGrabber.Exception ex) {
                    Logger.getLogger(Javacam.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (Exception ex) {
                        Logger.getLogger(Javacam.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (InterruptedException ex) {
                        Logger.getLogger(Javacam.class.getName()).log(Level.SEVERE, null, ex);
                    }

                //}//end of while
            }
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
            java.util.logging.Logger.getLogger(Javacam.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Javacam.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Javacam.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Javacam.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
          System.loadLibrary(Core.NATIVE_LIBRARY_NAME); // load native library of opencv
        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Javacam().setVisible(true);
                
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel T_Coleta;
    private java.awt.Label T_ECG;
    private java.awt.Label T_EMG;
    private java.awt.Label T_GSR;
    private javax.swing.JLabel T_Individuo;
    private javax.swing.JLabel T_NomeProjeto;
    private javax.swing.JLabel T_NomeProjeto1;
    private javax.swing.JLabel T_Pesquisador;
    private javax.swing.JLabel T_numero;
    private javax.swing.JButton btn_conectar;
    private javax.swing.JButton btn_start;
    private javax.swing.JButton btn_stop;
    private java.awt.Canvas canvas1;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel_EMG;
    private javax.swing.JPanel jPanel_GSR;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JTextField txt_coleta;
    private javax.swing.JTextField txt_individuo;
    private javax.swing.JTextField txt_nomeProjeto;
    private javax.swing.JTextField txt_numero;
    private javax.swing.JTextField txt_pesquisador;
    // End of variables declaration//GEN-END:variables
}
