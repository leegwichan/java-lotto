package lotto.message;

import device.message.Message;

public enum ExceptionMessage implements Message {
    LOTTO_SIZE_NOT_MATCHED("[ERROR] 입력한 로또 숫자 개수가 올바르지 않습니다. "),
    LOTTO_NUMBER_RANGE_OUT("[ERROR] 입력한 로또 숫자가 범위를 벗어납니다."),
    LOTTO_NUMBER_OVERLAPPED("[ERROR] 로또 형식이 올바르지 않습니다."),
    WINNING_NUMBER_FORM_NOT_MATCHED("[ERROR] 당첨 번호 형식이 일치하지 않습니다."),
    NOT_DIVIDED_PRISE("[ERROR] 구매 금액은 로또 한 장 금액으로 나누어 떨어져야 합니다.");


    private String message;

    ExceptionMessage(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
