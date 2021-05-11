
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author man21
 */
public class TrapdoorKnapsack {
    static Scanner sc = new Scanner(System.in);
    static ArrayList<Integer> vector = new ArrayList<>();
    public boolean ktSoNguyenTo(int n){
        int q =0;
        for(int i=1;i<=n;i++){
            if(n%i==0){
                q++;
            }
        }
        if(q==2){
            return true;
        }
        return false;
    }
    public int docFilePlanText() throws FileNotFoundException, IOException{
        BufferedReader readerpt = new BufferedReader(new FileReader("D:\\plantext.txt"));
        String linept = readerpt.readLine();
        ArrayList<Integer> pl = new ArrayList<>();
        while(linept != null){
            String pt = linept;
            for(int i = 0;i<pt.length();i++){
                pl.add(Integer.parseInt(pt.substring(i)));
            }
            linept = readerpt.readLine();
        }
        return pl.size();
    }
    public void taoVector() throws IOException{
        int n = docFilePlanText();
        Random rand = new Random();
        int a = rand.nextInt(100)+1;
        vector.add(a);
        for(int i = 1;i<n;i++){
            int s = 0;
            for(int j = 0;j<vector.size();j++){
                s = s + vector.get(j);
            }
            vector.add(s+1);
        }
    }
    public int khoiTaoM() throws IOException{
        int m = 0;
        for(int i = 0;i<vector.size();i++){
            m = m + vector.get(i);
        }
        m = m + 1;
        while(ktSoNguyenTo(m)==false){
            m = m+1; 
        }
        return m;
    }
    public int khoitaoW(){
        Random rand = new Random();
        int w = rand.nextInt(100)+1;
        while(ktSoNguyenTo(w)==false){
            w = w + 1;
        }
        return w;
    }
    public void taoPublicKey(int m, int w) throws IOException{
        FileWriter fw = new FileWriter("D:\\publickey.txt");
        for(int i = 0;i<vector.size();i++){
            fw.write((w*vector.get(i))%m+"\n");
        }
        fw.close();
    }
    public void taoPrivatekey(int m, int w) throws IOException{
        FileWriter fw = new FileWriter("D:\\privatekey.txt");
        for(int i = 0;i<vector.size();i++){
            if(i==vector.size()-1){
                fw.write(String.valueOf(vector.get(i))+";");
            }
            else{
                fw.write(String.valueOf(vector.get(i))+",");
            }
        }
        fw.write(m+";"+w);
        fw.close();
    }
    public void maHoa(int m) throws FileNotFoundException, IOException{
        BufferedReader readerpk = new BufferedReader(new FileReader("D:\\publickey.txt"));
        BufferedReader readerpt = new BufferedReader(new FileReader("D:\\plantext.txt"));
        FileWriter fw = new FileWriter("D:\\cipher.txt");
        String linepk = readerpk.readLine();
        int i;
        int t=0;
        while(linepk != null && (i=readerpt.read()) != -1){
            int pt = Integer.parseInt(linepk);
            int pk = Integer.parseInt(String.valueOf((char)i));
            t=t+(pt*pk);
            linepk = readerpk.readLine();
        }
        readerpk.close();
        readerpt.close();
        t=t%m;
        fw.write(String.valueOf(t));
        fw.close();
    }
    public void giaiMa() throws FileNotFoundException, IOException{
        BufferedReader readerprk = new BufferedReader(new FileReader("D:\\privatekey.txt"));
        BufferedReader readerc = new BufferedReader(new FileReader("D:\\cipher.txt"));
        String lineprk = readerprk.readLine();
        String linec = readerc.readLine();
        String [] mang = null;
        int t = 0;
        while(lineprk != null && linec != null){
            mang = lineprk.split(";");
            t = Integer.parseInt(linec);
            lineprk = readerprk.readLine();
            linec = readerc.readLine();
        }
        readerc.close();
        readerprk.close();
        int w = Integer.parseInt(mang[2]);       
        int m = Integer.parseInt(mang[1]);
        int w1=0;
        for(int i=1;i<=m;i++){
            if((w*i)%m==1){
                w1 = i;
                break;
            }
        }
        String [] manga = mang[0].split(",");
        int t1 = (t*w1)%m;
        String giaima = "";
        for(int j = manga.length-1;j>=0;j--){
            if(t1>=Integer.parseInt(manga[j])){               
                giaima +="1";
                t1=t1-Integer.parseInt(manga[j]);
            }
            else{
                giaima+="0";
            }
        }
        String dao = new StringBuffer(giaima).reverse().toString();
        FileWriter fw = new FileWriter("D:\\giaima.txt");
        fw.write(dao);
        fw.close();
    }
    public static void main(String[] args) throws IOException {
        TrapdoorKnapsack tk = new TrapdoorKnapsack();
        FileWriter fw = new FileWriter("D:\\plantext.txt");
        System.out.println("Vui long nhap chuoi bit de ma hoa");
        String pl = sc.nextLine();
        fw.write(pl);
        fw.close();
        tk.taoVector();
        int m = tk.khoiTaoM();
        int w = tk.khoitaoW();
        tk.taoPublicKey(m,w);
        tk.taoPrivatekey(m,w);
        tk.maHoa(m);
        tk.giaiMa();
        System.out.println("Vui long kiem tra o dia D da tao cac file: public key, private key, cipher, giai ma de thuc hien cong viec da thanh cong");
    }
}
