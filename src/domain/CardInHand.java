package domain;

import java.util.List;

public class CardInHand {

    private ResultStatus resultStatus;
    private List<Card> cardList;

    public CardInHand(ResultStatus resultStatus, List<Card> cardList) {
        super();
        this.resultStatus = resultStatus;
        this.cardList = cardList;
    }

    public ResultStatus getResult() {
        return resultStatus;
    }

    public List<Card> getCardList() {
        return cardList;
    }
}
