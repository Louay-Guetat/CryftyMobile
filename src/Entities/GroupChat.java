/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

import java.util.ArrayList;

/**
 *
 * @author HP
 */
public class GroupChat extends Conversation {

    private String Participant;
    private ArrayList<String> participants=new ArrayList<>() ;
    private String Owner;
    public GroupChat(String Participant, String nom) {
        super(nom);
        this.Participant = Participant;

    }

    public GroupChat( ArrayList<String> participants,String nom) {
        super(nom);
        this.participants = participants;
    }

    public ArrayList<String> getParticipants() {
        return participants;
    }

    public void setParticipants(ArrayList<String> participants) {
        this.participants = participants;
    }

    public GroupChat(String nom) {
        super(nom);
    }

    public GroupChat() {
    }

    public String getParticipant() {
        return Participant;
    }

    public void setParticipant(String Participant) {
        this.Participant = Participant;
    }

    public String getOwner() {
        return Owner;
    }

    public void setOwner(String owner) {
        Owner = owner;
    }

    @Override
    public String toString() {
        return super.toString()+"\n" ;
    }

  
}
