package converter;

import java.util.LinkedHashMap;
import java.util.Map;

public class Maps {
    private Map<Character, Integer> charIntMap = new LinkedHashMap<>(36);
    private Map<Integer, Character> intCharMap = new LinkedHashMap<>(36);

    public Maps() {
        int numToMap = 0;

        for (char character = '0'; character <= '9'; character++) {
            charIntMap.put(character, numToMap);
            intCharMap.put(numToMap++, character);
        }

        for (char character = 'a'; character <= 'z'; character++) {
            charIntMap.put(character, numToMap);
            intCharMap.put(numToMap++, character);
        }
    }

    public Map<Character, Integer> getCharIntMap() {
        return charIntMap;
    }

    public Map<Integer, Character> getIntCharMap() {
        return intCharMap;
    }
}
