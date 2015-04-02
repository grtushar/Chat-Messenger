/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chatproject;

import java.awt.Dimension;

/**
 *
 * @author Golam Rahman Tushar
 */
public class ChatProject {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        ChatMessenger CM = new ChatMessenger();
        
        CM.loginPanel(true);
        CM.optionPanel(false);
        CM.groupChatPanel(false);
        CM.privateChatPanel(false);
        CM.selectUserPanel(false);
        CM.setTitle("Chat Messenger");
        
        CM.setDimension(500, 500);
        
        CM.setVisible(true);
    }
    
}
