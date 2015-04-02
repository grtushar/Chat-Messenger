/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chatproject;

import javax.xml.crypto.Data;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by G R Tushar on 13-12-14.
 */
public class Communication implements Runnable {
    //private Map<String, DataOutputStream> mp;
    Map<String, DataOutputStream> mp = new HashMap<String, DataOutputStream>();
    private  BufferedReader br;
    private String name;
    public Communication(String name, Map<String, DataOutputStream> ds, BufferedReader br) {
        this.name = name;
        this.br = br;
        this.mp = ds;
    }

    public void run() {
        //System.out.println("in run");
        try {
            while(true) {
                String str = br.readLine();
                boolean flag = false;
                for(int i = 0; i<str.length(); i++) {
                   if(str.charAt(i) == '>') {
                       //System.out.println("got it");
                       flag = true;
                   }
                }

                if(flag) {
                    int idx1 = str.indexOf(':') + 2;
                    int idx2 = str.indexOf('>');
                    String rec = str.substring(idx1, idx2);
                    //System.out.println("destination: " + rec);
                   
                    String msg = str.substring(idx2+1, str.length());
                    String sender = str.substring(0, str.indexOf(' '));
                    
                    for(String key : mp.keySet()) {
                        //System.out.println(key + " " + own + " " + key.length() + " " + own.length());
                        if(key.equals(rec)) mp.get(key).writeBytes(sender + " : " + msg + '\n');
                        else if(key.equals(sender)) mp.get(key).writeBytes("me : " + msg + '\n');
                    }
                } else {
                    int idx = str.indexOf(' ');
                    String own = str.substring(0, idx);
                    String msg = str.substring(idx+1, str.length());

                    for(String key : mp.keySet()) {
                        //System.out.println(key + " " + own + " " + key.length() + " " + own.length());
                        if(!key.equals(own)) mp.get(key).writeBytes(str + '\n');
                        else mp.get(key).writeBytes("me " + msg + '\n');
                    }
                }

            }
        } catch (Exception e) {
            System.out.println("Exception in Communication class");
        }
    }
}
