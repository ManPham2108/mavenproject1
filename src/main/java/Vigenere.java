/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author man21
 */
public class Vigenere {
    static ArrayList<Integer> mangk = new ArrayList<Integer>();
    public ArrayList<Integer> giatriK(String k){
        for (int i = 0;i<k.length();i++){
            int a = (int) k.charAt(i) - 97;
            mangk.add(a);
        }
        return mangk;
    }
    public String maHoa(String k,String chuoi){
        String mahoa="";
        int h = 0;
        int c = 0;
        giatriK(k);
        for(int j = 0;j<chuoi.length();j++){
            int b = (int) chuoi.charAt(j) - 97;
            if(h<mangk.size()){
                c = (b + mangk.get(h)) % 26;
            }
            else{
                h = 0;
                c = (b + mangk.get(h)) % 26;
            }
            h++;
            c =  c + 97;
            mahoa += (char) c;
        }
        return mahoa;
        
    }
    public String giaiMa(String mahoa,String k){
        String giaima="";
        int h = 0;
        int c = 0;
        giatriK(k);
        for(int j = 0;j<mahoa.length();j++){
            int b = (int) mahoa.charAt(j) - 97;
            if(h<mangk.size()){
                c = (b - mangk.get(h)) % 26;
            }
            else{
                h = 0;
                c = (b - mangk.get(h)) % 26;
            }
            h++;
            if(c<0){
                c = c +26;
            }
            c = c + 97;
            giaima += (char) c;
        }
        return giaima;        
    }
    
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Vigenere vi = new Vigenere();
        String k;
        System.out.println("Nhap m: ");
        int m = sc.nextInt();
        sc.nextLine();
        System.out.println("Nhap khoa K: ");
        k = sc.nextLine();
        while(k.length()!=m){
            System.out.println("Vui long nhap lai khoa k. Khoa K phai trung voi do dai m: ");
            k = sc.nextLine();
            if(k.length()==m){
              break;
            }
        }
        System.out.println("Nhap chuoi can duoc ma hoa: ");
        String chuoi = sc.nextLine();
        System.out.println("Chuoi duoc ma hoa la: "+vi.maHoa(k,chuoi));
        String mahoa = vi.maHoa(k,chuoi);
        System.out.println("Chuoi duoc gia ma la: "+vi.giaiMa(mahoa,k));
    }
}
