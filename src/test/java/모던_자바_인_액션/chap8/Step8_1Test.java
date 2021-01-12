package 모던_자바_인_액션.chap8;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

class Step8_1Test {

    @Test
    public void 단순_리스트_add() {
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
    public void 팩토리_메서드_사용해서_add() {
        List<String> friends = Arrays.asList("김세은", "이예지", "성건희");

        assertThat(friends.get(0)).isEqualTo("김세은");
        assertThat(friends.get(1)).isEqualTo("이예지");
        assertThat(friends.get(2)).isEqualTo("성건희");
        assertThat(friends.size()).isEqualTo(3);
    }

    @Test
    public void 고정_크기_리스트에_요소_추가시_UnsupportedOperationException_에러() {
        List<String> friends = Arrays.asList("김세은", "이예지", "성건희");

        friends.set(0, "레드"); // 갱신은 가능
        Assertions.assertThrows(UnsupportedOperationException.class, () -> friends.add("어둠의 레드"));
    }

    @Test
    public void 리스트를_HashSet으로_생성() {
        Set<String> friends = new HashSet<>(Arrays.asList("김세은", "이예지", "성건희"));

        assertThat(friends.size()).isEqualTo(3);
    }

    @Test
    public void 리스트를_스트림API로_생성() {
        Set<String> friends = Stream.of("김세은", "이예지", "성건희")
                                    .collect(Collectors.toSet());

        assertThat(friends.size()).isEqualTo(3);
    }
}
