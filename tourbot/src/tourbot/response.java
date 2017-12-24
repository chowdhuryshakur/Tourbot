/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tourbot;

public class response {
    private String questions;
    private String responses;

    public response(String questions, String responses) {
        this.questions = questions;
        this.responses = responses;
    }

    public String getQuestions() {
        return questions;
    }

    public String getResponses() {
        return responses;
    }

    @Override
    public String toString() {
        return "response{" + "questions=" + questions + ", responses=" + responses + '}';
    }

    
    
    
    
    
}
