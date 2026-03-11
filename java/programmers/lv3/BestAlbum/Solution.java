package programmers.lv3.BestAlbum;

import java.util.*;
import java.util.stream.*;

class Solution {

    private Map<String, BestSongs> map = new HashMap();

    public int[] solution(String[] genres, int[] plays) {

        for (int i = 0; i < genres.length; i++) {
            String genre = genres[i];
            if (!map.containsKey(genre)) {
                map.put(genre, new BestSongs());
            }

            map.get(genre).add(new Song(i, plays[i]));
        }

        var answer = map.entrySet().stream()
                .sorted((a, b) ->
                        map.get(b.getKey()).counter - map.get(a.getKey()).counter)
                .flatMap((songs) -> {
                    if (songs.getValue().secondSong != null)
                        return Stream.of(songs.getValue().firstSong.num,
                                songs.getValue().secondSong.num);
                    else
                        return Stream.of(songs.getValue().firstSong.num);
                })
                .collect(Collectors.toList());

        int[] array = new int[answer.size()];
        for (int i = 0; i < answer.size(); i++) {
            array[i] = answer.get(i);
        }

        return array;
    }

    private class BestSongs {
        public int counter = 0;
        public Song firstSong;
        public Song secondSong;

        public void add(Song song) {
            counter += song.plays;
            if (firstSong == null || firstSong.plays < song.plays || (firstSong.plays == song.plays && firstSong.num > song.num)) {
                secondSong = firstSong;
                firstSong = song;
            } else if (secondSong == null || secondSong.plays < song.plays || (secondSong.plays == song.plays && secondSong.num > song.num)) {
                secondSong = song;
            }
        }
    }

    private class Song { int num; int plays; Song(int num, int plays) { this.num = num; this.plays = plays; }}
}