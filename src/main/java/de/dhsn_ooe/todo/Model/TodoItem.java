package de.dhsn_ooe.todo.Model;


public class TodoItem {

    private Long id;
    private String stringContent;
    private boolean doneState;
    public TodoItem() {}
    
    public void setStringContent(String stringContent) {
        this.stringContent = stringContent;
    }
    public String getStringContent() {
        return stringContent;
    }
    public String getAutowrappedString(int maxLineLength) {
        StringBuilder autowrapped = new StringBuilder("<html>");
        String[] words = getStringContent().split(" ");
        int lineLength = 0;
        for (String word : words) {
            if (word.length() > maxLineLength) {
                autowrapped.append(word.substring(0, maxLineLength)+"<br>"+word.substring(maxLineLength, word.length()+1)+ " ");
                lineLength = word.length() - maxLineLength;
            } else if (lineLength + word.length() <= maxLineLength ){
                lineLength = lineLength + word.length();
                autowrapped.append(word+ " ");
            } else {
                lineLength = word.length();
                autowrapped.append("<br>"+word + " ");
            }
        }
        autowrapped.append("</html>");
        return autowrapped.toString();
    }
    public void setState(boolean state) {
        doneState = state;
    }
    public boolean getState() {
        return doneState;
    }
    public boolean toggleState() {
        setState(!getState());
        return getState();
    }
    public Long getId() {
        return id;
    }

}
