public class Main {
    public static void main(String[] args) {

        byte[] key = new byte[2500];
        //  String binStr;
        int n = 2500;

        for (int i = 0; i < n; i++) {
            key[i] = (byte) (-128 + Math.random() * 256);

        }

        monoTest(key);
        System.out.println("\\\\\\\\\\\\\\\\\\");
        lenghtTest(key);
        System.out.println("\\\\\\\\\\\\\\\\\\");
        pokkerTest(key);
        System.out.println("\\\\\\\\\\\\\\\\\\");
        seriesTestOnes(key);
        System.out.println("\\\\\\\\\\\\\\\\\\");
        seriesTestZeros(key);

    }

    static void monoTest(byte[] key) {
        int c0 = 0, c1 = 0;
        for (int i = 0; i < key.length; i++) {
            String binStr = String.format("%8s", Integer.toBinaryString(key[i])).replace(' ', '0');
            if (key[i] < 0) {
                binStr = binStr.substring(24);
            }

            char[] tempChr = binStr.toCharArray();
            for (int j = 0; j < tempChr.length; j++) {
                if (tempChr[j] == '0') {
                    c0++;
                } else c1++;
            }
        }

        System.out.println(c0);
        System.out.println(c1);

        if (c0 < 10346 && c0 > 9654) {
            System.out.println("Mono-bit success");
        } else System.out.println("Mono-bit fail");
    }

    static void lenghtTest(byte[] key) {
        int c = 1, cMax = 0;
        char prev = ' ';
        for (int i = 0; i < key.length; i++) {
            String binStr = String.format("%8s", Integer.toBinaryString(key[i])).replace(' ', '0');
            if (key[i] < 0)
            {
                binStr = binStr.substring(24);
            }
            char[] tempChr = binStr.toCharArray();

            for (int j = 0; j < tempChr.length; j++) {

                if (tempChr[j] == prev) {
                    c++;
                }
                if (tempChr[j] != prev)
                {
                    if (c > cMax)
                    {
                        cMax = c;
                    }
                    c = 1;
                }
                prev = tempChr[j];
            }
        }
            System.out.println(cMax);

              if (cMax<=36){
                   System.out.println("Max length success");
                }
                else System.out.println("Max length fail");
        }



    static void pokkerTest(byte[] key) {
        int m = 4;
        int[] n = new int[16];
      //  int n=0;
        String[] posl = new String[16];

        //создаем последовательности
        for (int i = 0; i < 16; i++)
        {
            String temp = String.format("%4s", Integer.toBinaryString(i));
            temp = temp.replace(' ', '0');
            posl[i]=temp;

        //    System.out.println(temp);
        }


        char[] M = new char[4];

        for (int j = 0; j < 16; j++)
        {
            M = posl[j].toCharArray();

            for (int i = 0; i < key.length; i++) {
                String binStr = String.format("%8s", Integer.toBinaryString(key[i])).replace(' ', '0');
                if (key[i] < 0) {
                    binStr = binStr.substring(24);
                }
                char[] tempChr = binStr.toCharArray();

                if (tempChr[0] == M[0] && tempChr[1] == M[1] && tempChr[2] == M[2] && tempChr[3] == M[3]) {
                    n[j]++;
                }

                if (tempChr[4] == M[0] && tempChr[5] == M[1] && tempChr[6] == M[2] && tempChr[7] == M[3]) {
                    n[j]++;
                }

            }
        }

        double X, Y=0, Z;
        Z = Math.pow(2,m)/(20000/m);

        for(int i=0; i<16; i++)
        {
            Y = Y + Math.pow(n[i],2);
        }

        X = (Z*Y)-(20000/m);

        System.out.println(X);
        if (X>1.03 && X<57.4){
            System.out.println("Pokker success");
        }
        else System.out.println("Pocker fail");
    }


    static void seriesTestOnes(byte[] key) {
        int c = -1;
      //  int temptest = 0;
        boolean flag = false;
        int[] series =  {0, 0, 0, 0, 0, 0};
        for (int i = 0; i < key.length; i++) {
            String binStr = String.format("%8s", Integer.toBinaryString(key[i])).replace(' ', '0');
            if (key[i] < 0)
            {
                binStr = binStr.substring(24);
            }
            char[] tempChr = binStr.toCharArray();
          //  System.out.println(binStr);

            for (int j = 0; j < tempChr.length; j++) {

                if (tempChr[j] == '1') {
                    c++;
                }
                if (tempChr[j] == '0')
                {
                  //  temptest++;
                    if(c!=-1){ flag = true; }
                    if(c>=6){  c=5;  }
                    if(flag==true){series[c]++;}
                    flag = false;
                    c = -1;
                }
             //   prev = tempChr[j];
            }
        }


        System.out.println("Series of 1:");

        for (int i = 0; i < series.length; i++)
        {
            System.out.println(series[i]);
        }


        if (series[0]>2267&&series[0]<2733&&series[1]>1079&&series[1]<1421&&series[2]>502&&series[2]<748&&series[3]>223&&series[3]<402){
            System.out.println("Series test for '1' success");
        }
        else System.out.println("Series test for '1' fail");
    }

    static void seriesTestZeros(byte[] key) {
        int c = -1;
        //  int temptest = 0;
        boolean flag = false;
        int[] series =  {0, 0, 0, 0, 0, 0};
        for (int i = 0; i < key.length; i++) {
            String binStr = String.format("%8s", Integer.toBinaryString(key[i])).replace(' ', '0');
            if (key[i] < 0)
            {
                binStr = binStr.substring(24);
            }
            char[] tempChr = binStr.toCharArray();
            //  System.out.println(binStr);

            for (int j = 0; j < tempChr.length; j++) {

                if (tempChr[j] == '0') {
                    c++;
                }
                if (tempChr[j] == '1')
                {
                    //  temptest++;
                    if(c!=-1){ flag = true; }
                    if(c>=6){  c=5;  }
                    if(flag==true){series[c]++;}
                    flag = false;
                    c = -1;
                }
                //   prev = tempChr[j];
            }
        }


        System.out.println("Series of 0:");

        for (int i = 0; i < series.length; i++)
        {
            System.out.println(series[i]);
        }


        if (series[0]>2267&&series[0]<2733&&series[1]>1079&&series[1]<1421&&series[2]>502&&series[2]<748&&series[3]>223&&series[3]<402){
            System.out.println("Series test for '0' success");
        }
        else System.out.println("Series test for '0' fail");
    }


    }



