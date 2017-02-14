package consultoria;

import java.util.Arrays;

public class BruteForce {
    private char[] characterSet = "abefilmoqruz".toCharArray();
    private char[] currentCharacter;
    private int currentNumber;
    private char[] current;

    public BruteForce() {
        currentCharacter = new char[1];
        currentNumber = 0;
        current = new char[1];
        Arrays.fill(currentCharacter, characterSet[0]);
        Arrays.fill(current, characterSet[0]);
    }

    private void incrementCharacter() {
        int index = currentCharacter.length - 1;
        while (index >= 0) {
            if (currentCharacter[index] == characterSet[characterSet.length - 1]) {
                if (index == 0) {
                    currentCharacter = new char[currentCharacter.length + 1];
                    Arrays.fill(currentCharacter, characterSet[0]);
                    break;
                } else {
                    currentCharacter[index] = characterSet[0];
                    index--;
                }
            } else {
                currentCharacter[index] = characterSet[Arrays.binarySearch(characterSet, currentCharacter[index]) + 1];
                break;
            }
        }
    }
    
    public void increment() {
        if(currentNumber == 10){
            incrementCharacter();
            currentNumber = 0;
        }
        current = new char[currentCharacter.length + 1];
        for(int i = 0; i<currentCharacter.length; i++){
            current[i] = currentCharacter[i];
        }
        current[current.length - 1] = (char) (48 + currentNumber);
        currentNumber++;
        
    }

    @Override
    public String toString() {
        return String.valueOf(current);
    }
}
