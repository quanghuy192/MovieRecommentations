package domain;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class CardInHand {

    private ResultStatus resultStatus;
    private List<Card> cardList;
    private final int SIZE = 10;

    public CardInHand(ResultStatus resultStatus, List<Card> cardList) {
        super();
        this.resultStatus = resultStatus;
        this.cardList = cardList;
    }

    public CardInHand(String[] values) {
        super();
        this.resultStatus = getResult(Integer.parseInt(values[SIZE - 1]));
        cardList = new ArrayList<>();

        Deck cardUtils = new Deck();
        for (int i = 0; i < values.length - 1; i++) {
            Card c;
            Iterator it = cardUtils.getMapCard().entrySet().iterator();
            while (it.hasNext()) {
                Map.Entry pair = (Map.Entry) it.next();
                int value = (int) pair.getValue();
                if (value == Integer.parseInt(values[i])) {
                    c = (Card) pair.getKey();
                    cardList.add(c);
                }
            }
        }
    }

    public ResultStatus getResult() {
        return resultStatus;
    }

    public List<Card> getCardList() {
        return cardList;
    }

    public static ResultStatus getResult(long i) {
        switch ((int) i) {
            case 0:
                return ResultStatus.ADD;
            case 1:
                return ResultStatus.ADD_ALL;
            case 2:
                return ResultStatus.FOLLOW;
            case 3:
                return ResultStatus.DENY;
            default:
                throw new IllegalArgumentException();
        }
    }
}
