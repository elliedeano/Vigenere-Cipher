public class VigenereCipherGitHub{

    public String encrypt(String message, String key) {
        StringBuffer returnedMessage = new StringBuffer();
        StringBuffer repeatedKey = new StringBuffer();
        int keyIndex = 0;
        String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";

        for (int i = 0; i < key.length(); i++) {
            if (!Character.isLetter(key.charAt(i))) {
                return "";
            }
        }

        keyResizing(key, message, repeatedKey);

        for (int i = 0; i < message.length(); i++) {
            char messageChar = Character.toUpperCase(message.charAt(i));
            char keyChar = Character.toUpperCase(repeatedKey.charAt(i % repeatedKey.length()));

            if (Character.isLetter(messageChar)) {
                int keyIndex2 = alphabet.indexOf(keyChar);
                int messageIndex = alphabet.indexOf(messageChar);

                if (messageIndex != -1 && keyIndex2 != -1) {
                    int totalIndex1 = (messageIndex + keyIndex2) % alphabet.length();
                    returnedMessage.append(alphabet.charAt(totalIndex1));
                }
            } else {
                returnedMessage.append(messageChar);
            }
        }

        return returnedMessage.toString();
    }

    public String decrypt(String message, String key) {
        StringBuffer decryptedMessage = new StringBuffer();
        StringBuffer repeatedKey = new StringBuffer();
        int keyIndex = 0;
        String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";

        keyResizing(key, message, repeatedKey);

        for (int i = 0; i < message.length(); i++) {
            char messageChar = Character.toUpperCase(message.charAt(i));
            char keyChar = Character.toUpperCase(repeatedKey.charAt(i % repeatedKey.length()));

            if (Character.isLetter(messageChar)) {
                int keyIndex2 = alphabet.indexOf(keyChar);
                int messageIndex = alphabet.indexOf(messageChar);

                if (messageIndex != -1 && keyIndex2 != -1) {
                    int totalIndex1 = (messageIndex - keyIndex2 + alphabet.length()) % alphabet.length();
                    decryptedMessage.append(alphabet.charAt(totalIndex1));
                }
            } else {
                decryptedMessage.append(messageChar);
            }
        }

        return decryptedMessage.toString();
    }

    private void keyResizing(String key, String message, StringBuffer repeatedKey) {
        int keyIndex = 0;
        while (repeatedKey.length() < message.length()) {
            repeatedKey.append(key.charAt(keyIndex));
            keyIndex = (keyIndex + 1) % key.length();
        }
    }

    public static void main(String[] args) {
        VigenereCipher cipher = new VigenereCipher();
        String message = "HELLO WORLD";
        String key = "KEY";

        String encrypted = cipher.encrypt(message, key);
        System.out.println("Encrypted: " + encrypted);

        String decrypted = cipher.decrypt(encrypted, key);
        System.out.println("Decrypted: " + decrypted);
    }
}
