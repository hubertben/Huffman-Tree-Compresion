
import java.io.*;
import java.util.*;

class compRlist implements Comparator<pair> {

    @Override
    public int compare(pair e1, pair e2) {
        if (e1.freq < e2.freq) {
            return 1;
        } else {
            return -1;
        }
    }
}

class compMainList implements Comparator<node> {

    @Override
    public int compare(node e1, node e2) {
        if (e1.freq < e2.freq) {
            return 1;
        } else {
            return -1;
        }
    }
}

public class HuffmanTree {

    public static LinkedList<pair> Rlist = new LinkedList<>();
    public static LinkedList<node> mainList;
    public static HashMap<Character, String> map = new HashMap<>();
    public static char[] text;

    public static void main(String[] args) {
        text = readFromFile().toCharArray();
        Rlist = init(text);

        Collections.sort(Rlist, new compRlist());
        mainList = convert(Rlist);

        for (int i = Rlist.size() - 1; i > 0; i--) {
            compresionStep();
        }

        String s = "";
        printCode(mainList.get(0), s);

        byte[] b = new byte[text.length];
        for (int i = 0; i < b.length; i++) {
            b[i] = (byte) text[i];
        }
        System.out.println("\nOriginal Byte Code : ");
        System.out.println(strToBinary(readFromFile()));
        System.out.println("\n\nCompressed Byte Code : ");
        System.out.println(mapCompress());
        
        int d = strToBinary(readFromFile()).length() - mapCompress().length();
        System.out.println("\nAmmount of Bits Saved : " + d);
    }

    public static String strToBinary(String s) {
        int n = s.length();
        String f = "";

        for (int i = 0; i < n; i++) {
            
            int val = Integer.valueOf(s.charAt(i));

           
            String bin = "";
            while (val > 0) {
                if (val % 2 == 1) {
                    bin += '1';
                } else {
                    bin += '0';
                }
                val /= 2;
            }
            bin = reverse(bin);

            f+=bin;
        }
        return f;
    }

    public static String reverse(String input) {
        char[] a = input.toCharArray();
        int l, r = 0;
        r = a.length - 1;

        for (l = 0; l < r; l++, r--) {
            
            char temp = a[l];
            a[l] = a[r];
            a[r] = temp;
        }
        return String.valueOf(a);
    }

    public static String mapCompress() {
        String m = "";
        for (int i = 0; i < text.length; i++) {
            if (map.containsKey(text[i])) {
                m += map.get(text[i]);

            }
        }
        return m;
    }

    public static void compresionStep() {
        node c1 = mainList.get(mainList.size() - 1);
        node c2 = mainList.get(mainList.size() - 2);
        int o = c1.freq + c2.freq;
        node n = new node(c1, c2, o);
        c1.parent = c2.parent = n;

        mainList.remove(mainList.size() - 1);
        mainList.remove(mainList.size() - 1);
        mainList.add(n);

        Collections.sort(mainList, new compMainList());
    }

    public static void printCode(node root, String s) {

        if (root.left == null && root.right == null) {

            map.put(root.val, s);

            System.out.println(root.val + ":" + s);
            return;
        }

        printCode(root.left, s + "0");
        printCode(root.right, s + "1");
    }

    public static LinkedList<node> convert(LinkedList<pair> Rlist) {

        LinkedList<node> mList = new LinkedList<>();

        for (int r = 0; r < Rlist.size(); r++) {
            node n = new node();
            n.freq = Rlist.get(r).freq;
            n.val = Rlist.get(r).val;
            mList.add(n);
        }
        return mList;
    }

    public static void printList(LinkedList<pair> Rlist) {
        Rlist.forEach((p) -> {
            System.out.println(p.freq + ": " + p.val);
        });
    }

    public static void printNodeList(LinkedList<node> mainList) {
        mainList.forEach((m) -> {
            System.out.println(m.freq + ": " + m.val + ": " + m.left + ": " + m.right + ": " + m.parent + ": " + m);
        });
    }

    public static LinkedList<pair> init(char[] text) {
        LinkedList<pair> l = new LinkedList<>();
        LinkedList<pair> list = new LinkedList<>();

        for (int i = 32; i < 127; i++) {
            list.add(new pair(0, (char) i));
        }

        for (int t = 0; t < text.length; t++) {
            list.get((int) text[t] - 32).incFreq();
        }

        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).freq > 0) {
                l.add(list.get(i));
            }
        }
        return l;
    }

    public static String readFromFile() {
        try {
            File file = new File("data.txt");
            BufferedReader br = new BufferedReader(new FileReader(file));
            String st;
            while ((st = br.readLine()) != null) {
                return st;
            }
        } catch (IOException e) {
        }
        return "";
    }

}

class pair {

    public int freq;
    public char val;

    public pair(int freq, char val) {
        this.freq = freq;
        this.val = val;
    }

    public void incFreq() {
        freq++;
    }

}

class node {

    public node left, right, parent;
    public char val;
    public int freq;

    public node() {
        left = right = parent = null;
    }

    public node(node left, node right, int freq) {

        this.left = left;
        this.right = right;
        this.freq = freq;
    }

}
