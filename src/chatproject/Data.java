/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chatproject;

import java.io.DataOutputStream;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * Created by G R Tushar on 15-12-14.
 */
public class Data {
    public Map<String, DataOutputStream> mp = new HashMap<String, DataOutputStream>();
    public  void toMap(String str, DataOutputStream ds){
        this.mp.put(str, ds);
    }
    
    public String getusers() {
        String names = "";
        Iterator it = mp.entrySet().iterator();
        int idx = 0;
        while (it.hasNext()) {
            Map.Entry pair = (Map.Entry)it.next();
            names += (String) pair.getKey() + " ";
            //it.remove(); // avoids a ConcurrentModificationException
        }
        return names;
    }
}
