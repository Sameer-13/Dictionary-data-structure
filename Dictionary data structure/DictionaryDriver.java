import java.io.File;
import java.util.Arrays;
import java.util.Scanner;

public class DictionaryDriver {
    public static void main(String[] args) throws WordAlreadyExistsException {
        Scanner input = new Scanner(System.in);
        String welcomeMesage = "Welcome to Dictionary data structure driver";
        PrintCurvedBoxMessage(welcomeMesage);

        System.out.print("\n/\n|-> Enter file name: ");
        String fileName = input.next();
        Dictionary dictionary = new Dictionary();
        
        try {
            File file = new File(fileName);
            dictionary = new Dictionary(file);
            System.out.print("\n|-> Operations list:" 
                + "\n|   |"
                + "\n|   |_[1] Searching"
                + "\n|   |_[2] Adding"
                + "\n|   |_[3] Deleting"
                + "\n|   |_[4] Searching for similar"
                + "\n|   |_[5] Saving changes"
                + "\n|   |_[6] Ending the program"
                + "\n|\n"
                );
            boolean finish = false;
            while(!finish){
                System.out.print("|-> What operation you want to perform: ");
                String operationNumber = input.next();
                performOperation(dictionary, input, operationNumber);
                if(operationNumber.equals("6"))
                    finish = true;
            }
            System.out.print("|\n\\");
            PrintCurvedBoxMessage("Ends of the program");
            input.close();
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    static public void repetedPrinting(int times, String word){
        for (int i = 0; i < times; i++) {
            System.out.print(word);
        }
    }
    static public void repetedPrinting(int times, String spacing, String word){
        System.out.print(spacing);
        for (int i = 0; i < times; i++) {
            System.out.print(word);
        }
    }
    static public void PrintCurvedBoxMessage(String message){
        repetedPrinting(message.toCharArray().length+2, " ", "_");
        System.out.print("\n/");
        repetedPrinting(message.toCharArray().length+2, " ");
        System.out.print("\\\n");
        System.out.print("| "+message+" |");
        System.out.print("\n\\");
        repetedPrinting(message.toCharArray().length+2, " ");
        System.out.print("/");
        System.out.print("\n ");
        repetedPrinting(message.toCharArray().length+2, "=");
    }

    static public void performOperation(Dictionary dictionary, Scanner input, String operationNumber){
        try{
            switch (operationNumber){
                case "1":
                System.out.print("|   |_Search for: ");
                String checkedWord = input.next();
                if(dictionary.findWord(checkedWord))
                    System.out.println("|    |_Word has been found successfully\n|");
                else 
                    System.out.println("|    |_Word not found!\n|");
                    break;
                case "2":
                    System.out.print("|   |_Add the word: ");
                    String newWord = input.next();
                    dictionary.addWord(newWord);
                    if(dictionary.findWord(newWord))
                        System.out.println("|    |_Word has been added successfully\n|");
                    else
                        System.out.println("|    |_Failed in adding the word!\n|");
                    break;
                case "3":
                    System.out.print("|   |_Remove the word: ");
                    String removedWord = input.next();
                    dictionary.deleteWord(removedWord);
                    if(!dictionary.findWord(removedWord))
                        System.out.println("|    |_Word has been deleted successfully\n|");
                    else
                        System.out.println("|    |_Failed in deleting the word!\n|");
                    break;
                case "4":
                    System.out.print("|   |_Sreach for: ");
                    String searchedWord = input.next();
                    System.out.println("|    |_"+Arrays.asList(dictionary.findSimilar(searchedWord))+"\n|");
                    break;
                case "5":
                    System.out.print("|   |_Save Updated Dictionary (Y/N): ");
                    String responce = input.next();
                    if(responce.toUpperCase().equals("Y")){
                        System.out.print("|    |_Enter file name: ");
                        String newFlieName = input.next();
                        File newFile = dictionary.saveFile(newFlieName);
                    }
                    break;
                case "6":
                    break;
            
                default:
                    System.out.print("\n|   You entered wrong input! Please choose operation number");
                    break;
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    /*
     * 
     *  
        System.out.println(
            "=========================================" +
            "\n [[Ends of the program]] \n >>>>>>Thanks for your time<<<<<< \n"+
            "========================================="
        );
     */
}
