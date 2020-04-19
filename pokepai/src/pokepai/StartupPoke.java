package pokepai;

import javax.accessibility.AccessibleRole;
import java.util.Map;
import java.util.HashMap;
import java.util.Set;
import java.util.HashSet;


public class StartupPoke {

    static String[] pk2 = new String[52];//无序牌
    static String[] pk3 = new String[13];
    static String[] pk5 = new String[52];//有序牌
    static String[] puke1 = new String[13];//第一个人的牌
    static String[] puke2 = new String[13];//第二个人的牌
    static String[] puke3 = new String[13];//第三个人的牌
    static String[] puke4 = new String[13];//第四个人的牌
    static HashMap hashmap = new HashMap();
    public StartupPoke() {
        生成牌();
        打乱牌();
        发三人牌();
        //显示牌(pk2);
        int key[] = new int[52];
        /*排序牌(puke1);
        排序牌(puke2);
        排序牌(puke3);
        排序牌(puke4);*/
        /* 显示牌(puke1);
        显示牌(puke2);
        显示牌(puke3);
        显示牌(puke4);*/
        for (int i = 0; i < 52; i++) {
            key[i] = i;
        }
        
        for (int i = 0; i < 52; i++) {
            hashmap.put(pk5[i], key[i]); //初始化hashmap
        }
    }

    public static void main(String[] args) {

        new StartupPoke();

    }

    public void 生成牌() {
        String[] pk1 = {"fang", "mei", "hong", "hei"};
        //   String[] pk2=new String[52];
        int j = 0;
        for (int i = 0; i < 4; i++) {
            for (int k = 1; k <= 13; k++) {
                pk2[j] = pk1[i] + k;
                j++;
            }

        }

    }

    public void 显示牌(String[] a) {
        for (int i = 0; i < a.length; i++) {
            System.out.println(a[i]);
        }
    }

    public void 打乱牌() {
        for (int i = 0; i < 52; i++) {
            pk5[i] = pk2[i];
        }
        for (int i = 0; i < 70; i++) {
            int k = (int) (0 + Math.random() * 52);
            int j = (int) (0 + Math.random() * 52);

            String temp;
            temp = pk2[k];
            pk2[k] = pk2[j];
            pk2[j] = temp;

        }

    }

    public void 发牌() {
        for (int i = 0; i < 13; i++) {
            pk3[i] = pk2[i];
        }
    }

    public void 排序牌(String[] c) {
        String[] temp = new String[13];

        int k = 0;

        for (int i = 0; i < 52; i++) {
            for (int j = 0; j < 13; j++) {
                if (pk5[i].equals(c[j])) {
                    temp[k] = c[j];
                    k++;

                }
            }
        }

        System.arraycopy(temp, 0, c, 0, c.length);

    }

    public void 发三人牌() {
        for (int i = 0; i < 13; i++) {
            puke1[i] = pk2[i];
        }
        for (int i = 13; i < 26; i++) {
            puke2[i - 13] = pk2[i];
        }
        for (int i = 26; i < 39; i++) {
            puke3[i - 26] = pk2[i];
        }
        for (int i = 39; i < 52; i++) {
            puke4[i - 39] = pk2[i];
        }

    }

    class Hashmaps {

    }
}
