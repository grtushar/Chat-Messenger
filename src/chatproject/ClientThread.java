/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chatproject;

import java.io.BufferedReader;
import javax.swing.JTextArea;

/**
 * Created by G R Tushar on 15-12-14.
 */
public class ClientThread extends Thread {
    BufferedReader br;
    JTextArea jta1, jta2;

    ClientThread(BufferedReader br, JTextArea jta1, JTextArea jta2) {
        this.br = br;
        this.jta1 = jta1;
        this.jta2 = jta2;
    }
    public void run() {
        try {
            while(true) {
                //System.out.println("clint");
                String str = br.readLine();
                jta1.append(str + '\n');
                jta2.append(str + '\n');
                //System.out.println(str);
            }
        } catch (Exception e) {

        }
    }
}
