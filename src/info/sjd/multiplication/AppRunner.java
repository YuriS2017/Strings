package info.sjd.multiplication;

public class AppRunner {
	
	public static void main(String[] args) {

        int number = 1234;
        int multiplier = 123;

        System.out.println(getResultMultiplication(number, multiplier));

    }

    private static String getResultMultiplication(int number, int multiplier) {

        char[] numbers = String.valueOf(number).toCharArray();
        char[] multipliers = String.valueOf(multiplier).toCharArray();
        int result = 0;
        int memory = 0;
        int count = 0;
        int i = 0;

        StringBuilder output = new StringBuilder()
                .append(getStringFromChar(' ', numbers.length) + number + "\n")
                .append(getStringFromChar(' ', numbers.length + 1) + multiplier + "\n")
                .append(getStringFromChar(' ', numbers.length) + getStringFromChar('_', numbers.length) + "\n");

        for(int j = multipliers.length - 1; j >= 0; j--) {
            Character[] intermediateArrays = new Character[numbers.length];

            for(i = numbers.length -1; i >= 0; i--) {
                result = Character.getNumericValue(numbers[i]) * Character.getNumericValue(multipliers[j]) + memory;

                if(result > 9) {
                    arrayFill(intermediateArrays, result % 10);
                    memory = result / 10;
                }
                else {
                    arrayFill(intermediateArrays, result);
                    memory = 0;
                }

                if (i == 0 && result > 9){
                    Character[] enlargedArrays = copyArray(intermediateArrays);

                    if (enlargedArrays != null){
                        arrayFill(enlargedArrays, result % 10);
                        enlargedArrays[0] = null; //here for some reason, after adding the result value to the new array, the zero cell was also filled in ... so it was necessary to overwrite its value
                        arrayFill(enlargedArrays, memory);
                        output.append(getStringFromChar(' ', numbers.length - count) + getLine(enlargedArrays) + "\n");
                        count++;
                    }
                }
                else if(i == 0) {
                    output.append(getStringFromChar(' ', numbers.length - count) + getLine(intermediateArrays) + "\n");
                    count++;
                }
            }
        }

        output.append(getStringFromChar('_', numbers.length + count));

        return output.toString();
    }

    private static String getStringFromChar(char ch, int length){
        char[] lines = new char[length];
        String line = "";

        for (char character : lines){
            character = ch;
            line += character;
        }
        return line;
    }

    private static String getLine(Character[] arrays) {
        String line = "";

        for (Character ch : arrays){
            if (ch != null){
                line += ch;
            }
        }
        return line;
    }

    private static Character[] copyArray(Character[] oldArray){
        Character[] newArray = new Character[oldArray.length + 1];

        for (int i = oldArray.length - 1; i >= 0; i--) {
            for (int j = newArray.length - 1; j >= 0; j--) {
                if (oldArray[i] != null && newArray[j] == null){
                    newArray[j] = oldArray[i];
                    break;
                }
            }
        }
        return newArray;
    }


    private static void arrayFill(Character[] arrays, int number) {
        for(int i = arrays.length - 1; i >= 0; i--) {
            if(arrays[i] == null) {
                arrays[i] = String.valueOf(number).charAt(0);
                break;
            }
        }
    }
}
