package net.revature.RevShop.DTOs;

import java.util.List;

public class AIResponse {

    private List<Choice> choices;

    public AIResponse() {
    }

    public AIResponse(List<Choice> choices) {
        this.choices = choices;
    }

    public List<Choice> getChoices() {
        return choices;
    }

    public void setChoices(List<Choice> choices) {
        this.choices = choices;
    }

    public static class Choice {

        private int index;
        private AIMessage message;

        public Choice() {
        }

        public Choice(int index, AIMessage message) {
            this.index = index;
            this.message = message;
        }

        public int getIndex() {
            return index;
        }

        public void setIndex(int index) {
            this.index = index;
        }

        public AIMessage getMessage() {
            return message;
        }

        public void setMessage(AIMessage message) {
            this.message = message;
        }
    }
}