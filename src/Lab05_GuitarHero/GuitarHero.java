package Lab05_GuitarHero;

public class GuitarHero {
    public static final String LETTERS = "q2we4r5ty7u8i9op-[=zxdcfvgbnjmk,.;/' ";

    public static void main(String[] args) throws InterruptedException {
        GuitarString[] keyboard = getKeyboard();

        // the main input loop
        while(true) {
            if (StdDraw.hasNextKeyTyped()) {
                char x = StdDraw.nextKeyTyped();
                //System.out.println(x);

                int key = LETTERS.indexOf(x);
                if(key < 0) continue;

                keyboard[key].pluck();
            }

            // compute the superposition of the samples
            double sample = getSampleSuperposition(keyboard);

            // send the result to standard audio
            StdAudio.play(sample);

            // advance the simulation of each guitar string by one step
            tic(keyboard);
        }
    }

    private static GuitarString[] getKeyboard() {
        GuitarString[] strings = new GuitarString[LETTERS.length()];

        for(int i = 0; i < strings.length; i++) {
            strings[i] = new GuitarString(440 * Math.pow(1.05956, i - 24));
        }

        return strings;
    }

    private static double getSampleSuperposition(GuitarString[] keyboard) {
        double sample = 0;
        for(GuitarString string : keyboard) {
            sample += string.sample();
        }
        return sample;
    }

    private static void tic(GuitarString[] keyboard) {
        for(GuitarString string : keyboard) {
            string.tic();
        }
    }
}
