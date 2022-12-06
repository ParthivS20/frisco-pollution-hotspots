package Lab04_MelodyMaker;

import java.util.LinkedList;
import java.util.Queue;

public class Melody {
    private Queue<Note> notes;

    Melody(Queue<Note> song) {
        notes = song;
    }

    double getTotalDuration() {
        Queue<Note> temp = new LinkedList<>();
        double duration = 0;
        double repeatedDuration = 0;
        boolean inRepeated = false;

        while(!notes.isEmpty()) {
            Note x = notes.poll();
            duration += x.getDuration();
            temp.offer(x);

            if(x.isRepeat()) {
                inRepeated = !inRepeated;
                if(!inRepeated) {
                    duration +=  x.getDuration() + repeatedDuration;
                    repeatedDuration = 0;
                }
            }

            if(inRepeated) {
                repeatedDuration += x.getDuration();
            }
        }

        this.notes = temp;
        return Math.round(duration * 100) / 100.0;
    }

    @Override
    public String toString() {
        Queue<Note> temp = new LinkedList<>();
        String out = "";

        while(!notes.isEmpty()) {
            Note x = notes.poll();
            out += x.toString() + "\n";
            temp.offer(x);
        }

        this.notes = temp;
        return out;
    }

    void changeTempo(double tempo) {
        Queue<Note> temp = new LinkedList<>();

        while(!notes.isEmpty()) {
            Note x = notes.poll();
            x.setDuration(x.getDuration() / tempo);
            temp.offer(x);
        }

        this.notes = temp;
    }

    void reverse() {
        if(!notes.isEmpty()) {
            Note x = notes.poll();
            reverse();
            notes.offer(x);
        }
    }

    void append(Melody other) {
        Queue<Note> otherNotes = other.getNotes();
        while(!otherNotes.isEmpty()) {
            notes.offer(otherNotes.poll());
        }
    }

    void play() {
        Queue<Note> temp = new LinkedList<>();
        Queue<Note> repeatedQueue = new LinkedList<>();
        boolean inRepeated = false;

        while(!notes.isEmpty()) {
            Note x = notes.poll();
            x.play();
            temp.offer(x);

            if(x.isRepeat()) {
                inRepeated = !inRepeated;
                if(!inRepeated) {
                    repeatedQueue.offer(x);
                    while(!repeatedQueue.isEmpty()) {
                        repeatedQueue.poll().play();
                    }
                }
            }

            if(inRepeated) {
                repeatedQueue.offer(x);
            }
        }

        notes = temp;
    }

    Queue<Note> getNotes() {
        return notes;
    }
}
