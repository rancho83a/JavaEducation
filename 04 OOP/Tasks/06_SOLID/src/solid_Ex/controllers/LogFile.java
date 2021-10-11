package solid_Ex.controllers;

import solid_Ex.interfaces.CustomFile;

public class LogFile implements CustomFile {
    private StringBuilder stringBuilder;

    public LogFile(){
        this.stringBuilder=new StringBuilder();
    }
    @Override
    public void write(String text) {
        this.stringBuilder.append(text);

    }

    @Override
    public int getSize() {
        int sum=0;
        for (int i = 0; i < this.stringBuilder.length(); i++) {
            char symbol = stringBuilder.charAt(i);
            if(Character.isAlphabetic(symbol)){
                sum+=symbol;
            }
        }
        return sum;
    }
}
