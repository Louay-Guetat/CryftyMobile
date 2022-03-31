/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author HP
 */
public class PrivateChat extends Conversation {
    /*private List<User>  Sender ;
    private List<User>  Received ;
*/
    private String  Sender ;
    private String Received ;
   /* public PrivateChat(int id, String nom) {
        super(id, nom);
        Sender=new ArrayList<>();
        Received=new ArrayList<>();
    }
*/

    public PrivateChat(String nom, String  sender, String received) {
        super(nom);
        Sender = sender;
        Received = received;
    }

    public PrivateChat(int id, String nom, String sender, String received) {
        super(id, nom);
        Sender = sender;
        Received = received;
    }

    public PrivateChat() {
    }

    public String  getSender() {
        return Sender;
    }

    public void setSender(String  sender) {
        Sender = sender;
    }

    public String getReceived() {
        return Received;
    }

    public void setReceived(String received) {
        Received = received;
    }

    @Override
    public String toString() {
        return super.toString()+"\n"+this.Received+"\n"+this.Sender ;
    }

}
