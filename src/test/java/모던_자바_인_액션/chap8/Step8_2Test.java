package 모던_자바_인_액션.chap8;

import org.junit.jupiter.api.Test;

import java.util.*;
import java.util.stream.Collectors;

class Step8_2Test {

    static class Transaction {
        private String referenceCode;

        public Transaction(String referenceCode) {
            this.referenceCode = referenceCode;
        }

        public String getReferenceCode() {
            return referenceCode;
        }
    }

    @Test
    void 리스트_for_each_요소삭제불가() {
        List<Transaction> transactions = createTransactions();

        for (Transaction transaction : transactions) {
            if (Character.isDigit(transaction.getReferenceCode().charAt(0))) { // isDigit : 숫자인지 체크
                transactions.remove(transaction);
            }
        }
    }

    @Test
    void for_each는_내부적으로_iterator사용하므로_컬랙션의_상태와_동기화되지않아_오류발생() {
        List<Transaction> transactions = createTransactions();

        for (Iterator<Transaction> iterator = transactions.iterator(); iterator.hasNext();) {
            Transaction transaction = iterator.next();
            if (Character.isDigit(transaction.getReferenceCode().charAt(0))) {
                transactions.remove(transaction);
            }
        }
    }

    @Test
    void for_each사용시_iterator객체를_명시적으로_사용하여_동기화() {
        List<Transaction> transactions = createTransactions();

        for (Iterator<Transaction> iterator = transactions.iterator(); iterator.hasNext();) {
            Transaction transaction = iterator.next();
            if (Character.isDigit(transaction.getReferenceCode().charAt(0))) {
                iterator.remove();
            }
        }
    }

    @Test
    void removeIf_메서드사용() {
        List<Transaction> transactions = createTransactions();

        transactions.removeIf(transaction -> Character.isDigit(transaction.getReferenceCode().charAt(0)));
    }

    @Test
    void replaceAll_메서드사용시_새문자열_컬렉션을만듬() {
        List<String> referenceCodes = createReferenceCodes();

        referenceCodes.stream()
                      .map(code -> Character.toUpperCase(code.charAt(0)) + code.substring(1))
                      .collect(Collectors.toList())
                      .forEach(System.out::println);
    }

    @Test
    void 기존컬렉션바꾸기() {
        List<String> referenceCodes = createReferenceCodes();

        for (ListIterator<String> iterator = referenceCodes.listIterator(); iterator.hasNext();) {
            String code = iterator.next();
            iterator.set(Character.toUpperCase(code.charAt(0)) + code.substring(1));
        }

        System.out.println(referenceCodes);
    }

    @Test
    void replaceAll_메서드사용() {
        List<String> referenceCodes = createReferenceCodes();

        referenceCodes.replaceAll(code -> Character.toUpperCase(code.charAt(0)) + code.substring(1));

        System.out.println(referenceCodes);
    }

    private List<Transaction> createTransactions() {
        List<Transaction> transactions = new ArrayList<>();
        transactions.add(new Transaction("0apple"));
        transactions.add(new Transaction("lime"));
        transactions.add(new Transaction("water"));
        return transactions;
    }

    private List<String> createReferenceCodes() {
        return Arrays.asList("a12", "C14", "b13");
    }
}
