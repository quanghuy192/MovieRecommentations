package data;

import domain.Card;
import domain.CardInHand;
import domain.Deck;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ExpUtils {

    private FileReader reader;
    private BufferedReader bufferedReader;
    private FileWriter writer;
    private BufferedWriter bufferedWriter;
    private final String EXP_FILE = "exp.txt";
    private final String BLANK = " ";
    File file = new File(EXP_FILE);

    public ExpUtils() {
        try {
            reader = new FileReader(file);
            bufferedReader = new BufferedReader(reader);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List<CardInHand> readDataTrainning() {
        List<CardInHand> lineList = new ArrayList<>();
        String s;
        try {
            while (null != (s = bufferedReader.readLine())) {
                String[] values = s.split(BLANK);
                lineList.add(new CardInHand(values));
            }
            return lineList;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return lineList;
    }

    public void writeDataTrainning(CardInHand cardInHands) {
        Deck cardUtils = new Deck();
        try {
            writer = new FileWriter(file);
            bufferedWriter = new BufferedWriter(writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            for (Card card : cardInHands.getCardList()) {
                bufferedWriter.write(cardUtils.convertCardToOrder(card) + "");
                bufferedWriter.write(" ");
            }
            bufferedWriter.write(cardInHands.getResult().ordinal() + "");
            bufferedWriter.write("\n");
            bufferedWriter.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void closeReader() {
        try {
            bufferedReader.close();
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void closeWrite() {
        try {
            bufferedWriter.close();
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
