import java.util.ArrayList;
import java.util.List;



public class findSub {
    public final static int d = 256;
    public static void main(String[] args) {
        String p = "abcababa";
        String t = "ab";
        int q = 101;
        search_hash(p, t, q);
    }

    public static List<Integer> search(String p, String t) {
        int P = p.length();
        int T = t.length();
        List<Integer> res = new ArrayList<>();

        for(int i = 0; i <= P - T; i++) {
            int j;
            for(j = 0; j < T; j++) {
                if(p.charAt(i+j) == t.charAt(j)) {
                    if(j == T - 1) res.add(i);
                }
            }
        }
        return res;
    }

    static void search_hash(String pat, String txt, int q)
    {
        int M = pat.length();
        int N = txt.length();
        int i, j;
        int p = 0; // hash value for pattern
        int t = 0; // hash value for txt
        int h = 1;

        // The value of h would be "pow(d, M-1)%q"
        for (i = 0; i < M-1; i++)
            h = (h*d)%q;

        // Calculate the hash value of pattern and first
        // window of text
        for (i = 0; i < M; i++)
        {
            p = (d*p + pat.charAt(i))%q;
            t = (d*t + txt.charAt(i))%q;
        }

        // Slide the pattern over text one by one
        for (i = 0; i <= N - M; i++)
        {

            // Check the hash values of current window of text
            // and pattern. If the hash values match then only
            // check for characters on by one
            if ( p == t )
            {
                /* Check for characters one by one */
                for (j = 0; j < M; j++)
                {
                    if (txt.charAt(i+j) != pat.charAt(j))
                        break;
                }

                // if p == t and pat[0...M-1] = txt[i, i+1, ...i+M-1]
                if (j == M)
                    System.out.println("Pattern found at index " + i);
            }

            // Calculate hash value for next window of text: Remove
            // leading digit, add trailing digit
            if ( i < N-M )
            {
                t = (d*(t - txt.charAt(i)*h) + txt.charAt(i+M))%q;

                // We might get negative value of t, converting it
                // to positive
                if (t < 0)
                    t = (t + q);
            }
        }
    }
}
