package 모던_자바_인_액션.chap8;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

class Step8_1Test {

    @Test
    void 단순_리스트_add() {
        List<String> friends = new ArrayList<>();
        friends.add("김세은");
        friends.add("이예지");
        friends.add("성건희");

        assertThat(friends.get(0)).isEqualTo("김세은");
        assertThat(friends.get(1)).isEqualTo("이예지");
        assertThat(friends.get(2)).isEqualTo("성건희");
        assertThat(friends.size()).isEqualTo(3);
    }

    @Test
    void 팩토리_메서드_사용해서_add() {
        List<String> friends = Arrays.asList("김세은", "이예지", "성건희");

        assertThat(friends.get(0)).isEqualTo("김세은");
        assertThat(friends.get(1)).isEqualTo("이예지");
        assertThat(friends.get(2)).isEqualTo("성건희");
        assertThat(friends.size()).isEqualTo(3);
    }

    @Test
    void 고정_크기_리스트에_요소_추가시_UnsupportedOperationException_에러() {
        List<String> friends = Arrays.asList("김세은", "이예지", "성건희");

        friends.set(0, "레드"); // 갱신은 가능
        Assertions.assertThrows(UnsupportedOperationException.class, () -> friends.add("어둠의 레드"));
    }

    @Test
    void 리스트를_HashSet으로_생성() {
        Set<String> friends = new HashSet<>(Arrays.asList("김세은", "이예지", "성건희"));

        assertThat(friends.size()).isEqualTo(3);
    }

    @Test
    void 리스트를_스트림API로_생성() {
        Set<String> friends = Stream.of("김세은", "이예지", "성건희")
                                    .collect(Collectors.toSet());

        assertThat(friends.size()).isEqualTo(3);
    }

    @Test
    void 리스트_팩토리메서드_요소추가_안됨() {
        List<String> friends = List.of("김세은", "이예지", "성건희");

        Assertions.assertThrows(UnsupportedOperationException.class, () -> friends.add("포비"));
    }

    @Test
    void 집합_팩토리메서드_중복데이터_저장안됨() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> Set.of("김세은", "이예지", "성건희", "성건희"));
    }

    @Test
    void 맵_팩토리메서드_작은데이터추가() {
        Map<String, Integer> ageOfFriends = Map.of("성건희", 10, "김세은", 15, "이예지", 12);

        System.out.println(ageOfFriends);
    }

    @Test
    void 맵_팩토리메서드_많은데이터추가() {
        Map<String, Integer> ageOfFriends = Map.ofEntries(
                                                    Map.entry("성건희", 10),
                                                    Map.entry("김세은", 15),
                                                    Map.entry("이예지", 12)
                                                );

        System.out.println(ageOfFriends);
    }

    @Test
    void 퀴즈8_1() {
        List<String> actors = List.of("김세은", "이예지");

        actors.set(0, "Brad");

        System.out.println(actors);
    }
}
