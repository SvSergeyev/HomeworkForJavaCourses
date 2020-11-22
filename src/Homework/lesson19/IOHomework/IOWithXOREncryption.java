package Homework.lesson19.IOHomework;


import java.io.*;
import java.util.Objects;

public class IOWithXOREncryption {
    public static void main(String[] args) {
        String sourceString = "Нет людей несчастнее станционных смотрителей, ибо во всех своих неприятностях путешествующие непременно винят\n" +
                "смотрителей и стремятся на них выместить свою злость по поводу плохих дорог, несносной погоды, скверных лошадей\n" +
                "и тому подобного. А между тем смотрители — это большей частью кроткие и безответные люди, «сущие мученики\n" +
                "четырнадцатого класса, огражденные своим чином токмо от побоев, и то не всегда». Жизнь смотрителя полна тревог\n" +
                "и хлопот, он ни от кого не видит благодарности, напротив, слышит угрозы и крики и ощущает толчки раздраженных\n" +
                "постояльцев. Между тем «из их разговоров можно почерпнуть много любопытного и поучительного».";
        File file = new File("src/Homework/lesson19/IOTest/FIRST.txt");
        String encryptionKey = "5247";
        writeAndEncode100BytesToFile(file, sourceString, encryptionKey);
//        System.out.println("Decode string: " + readAndDecode100BytesFromFile(file, encryptionKey));
        System.out.println("Compliance check: " +
                sourceString.equals(readAndDecode100BytesFromFile(file, encryptionKey)));
    }

    private static void writeAndEncode100BytesToFile(File file, String sourceString, String encryptionKey) {
        try (EncodingDecorator encoder =
                     new EncodingDecorator(new FileOutputStream(file), encryptionKey)) {
            encoder.write(sourceString.getBytes());
        } catch (IOException e) {
            System.out.println("Output error...");
            e.printStackTrace();
        }
    }

    private static String readAndDecode100BytesFromFile(File file, String encryptionKey) {
        String result = null;
        try (DecodingDecorator decoder =
                     new DecodingDecorator(new FileInputStream(file), encryptionKey);
             ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream()) {
            int data;
            byte[] bytes = new byte[100];
            while ((data = decoder.read(bytes)) != -1) {
                byteArrayOutputStream.write(bytes, 0, data);
            }
            result = new String(byteArrayOutputStream.toByteArray());
        } catch (IOException e) {
            System.out.println("File reading error...");
            e.printStackTrace();
        }
        return result;
    }
}


class EncodingDecorator extends FilterOutputStream {
    private final String encryptionKey;
    public EncodingDecorator(OutputStream out, String encryptionKey) {
        super(out);
        Objects.requireNonNull(
                encryptionKey, "Encryption key cannot be \"null\"");
        this.encryptionKey = encryptionKey;
    }

    @Override
    public void write(byte[] b) throws IOException {
        // шифрование исходной строки:
        byte[] result = new byte[b.length];
        byte[] keyAsArray = encryptionKey.getBytes();
        for (int i = 0; i < b.length; i++) {
            result[i] = (byte) (b[i] ^ keyAsArray[i % keyAsArray.length]);
        }
        super.write(result);
    }
}

class DecodingDecorator extends FilterInputStream {
    private final String encryptionKey;

    public DecodingDecorator(InputStream in, String encryptionKey) {
        super(in);
        Objects.requireNonNull(
                encryptionKey, "Encryption key cannot be \"null\"");
        this.encryptionKey = encryptionKey;
    }

    @Override
    public int read(byte[] b) throws IOException {
        // расшифровка входящих данных:
        int data = in.read(b);
        byte[] keyAsArray = encryptionKey.getBytes();
        for (int i = 0; i < b.length; i++) {
            b[i] = (byte) (b[i] ^ keyAsArray[i % keyAsArray.length]);
        }
        return data;
    }
}
