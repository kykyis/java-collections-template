package com.epam.izh.rd.online.service;

import com.epam.izh.rd.online.helper.Direction;


import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.Collections.*;

/**
 * Данный класс обязан использовать StreamApi из функционала Java 8. Функциональность должна быть идентична
 * {@link SimpleTextStatisticsAnalyzer}.
 */
public class StreamApiTextStatisticsAnalyzer implements TextStatisticsAnalyzer {
    @Override
    public int countSumLengthOfWords(String text) {
    return getWords(text).stream().mapToInt(String::length).sum();
    }

    @Override
    public int countNumberOfWords(String text) {
        return (int) getWords(text).stream().count();
    }

    @Override
    public int countNumberOfUniqueWords(String text) {
        return (int) getWords(text).stream().distinct().count();

    }

    @Override
    public List<String> getWords(String text) {
        return Stream.of(text.split("\\W+")).collect(Collectors.toList());
    }
    @Override
    public Set<String> getUniqueWords(String text) {
        return getWords(text).stream().collect(Collectors.toSet());
    }

    @Override
    public Map<String, Integer> countNumberOfWordsRepetitions(String text) {
        return getWords(text)
                .stream()
                .collect(Collectors
                            .toMap(Function.identity(),
                                    n -> 1,
                                    (a,b) -> a + b));
    }

    @Override
    public List<String> sortWordsByLength(String text, Direction direction) {
        Comparator<String> stringLengthComparator = (o1, o2) -> o1.length() - o2.length();
        List<String> sortedList = getWords(text)
                                    .stream()
                                    .sorted(stringLengthComparator)
                                    .collect(Collectors.toList());

        if (Direction.DESC.equals(direction)) {
            Collections.reverse(sortedList);
        }
        return sortedList;
    }
}
