import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class Dictionary extends AVLTree{
    
    public Dictionary(){
        super();
    }

    public Dictionary(String s){
        super(new BTNode<String>(s));
    }

    public Dictionary(File f){
        super();
        try{
            Scanner input = new Scanner(f);  
            while(input.hasNext()){ 
                try {
                    insertAVL(input.next());   
                } catch (IllegalArgumentException e){ 
                }
            }
            input.close();
            System.out.print("|   |_Dictionary loaded successfully\n|");  
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public boolean findWord(String s){
        if(search(s)){
            return true;
        }
        else{
            return false;
        }
    }

    public void addWord(String s) throws WordAlreadyExistsException{
        try {
            insertAVL(s);
        } catch (IllegalArgumentException e) {
            throw new WordAlreadyExistsException();
        }
    }


    public void deleteWord(String s) throws WordNotFoundException{
        try {
            deleteAVL(s);
        } catch (Exception e) {
            throw new WordNotFoundException();
        }
    }

    public String[] findSimilar (String s){
        SLL<String> wordsList = new SLL<String>(); 
        findSimilar(s, root, wordsList);
        String[] similarWordsArray = wordsList.toString().split(",");

        return similarWordsArray;
    }

    public void findSimilar(String key, BTNode<String> node, SLL<String> list){
        if(node == null){
            return;
        }

        if(isSimilar(key, node.data)){
            list.addToHead(node.data);
        }
        findSimilar(key, node.left, list);
        findSimilar(key, node.right, list);
    }

    private boolean isSimilar(String key, String word){
        int lengthDifference = Math.abs(word.length() - key.length());
        if(lengthDifference > 1){
            return false;
        }

        int differenceDegree = 0;
        int i = 0, j = 0;
        while(i < key.length() && j < word.length()){
            if(key.charAt(i) != word.charAt(j)){
                differenceDegree++;
                if(differenceDegree > 1){
                    return false;
                }
                if(key.length() > word.length()){
                    i++;
                }
                else if(key.length() < word.length()){
                    j++;
                }
                else{
                    i++;
                    j++;
                }
            }
            else{
                i++;
                j++;
            }
        }
        if(i < key.length() || j < word.length()){
            differenceDegree++;
        }

        boolean lastCheck = differenceDegree == 1;

        return lastCheck;
    }

    public File saveFile(String fileName){
        File newFile = new File(fileName);
        try {
            PrintWriter output = new PrintWriter(newFile);
            printElements(output,root);
            System.out.println("Dictionary saved successfully");
            output.close();
        } catch (FileNotFoundException e){
            System.out.println(e.getMessage());       }
        return newFile;
    }

    private void printElements(PrintWriter output, BTNode<String>  node){
        if(node != null){
            output.println(node.data);
            printElements(output, node.left);
            printElements(output, node.right);
        }
        else 
            return;
    }
}