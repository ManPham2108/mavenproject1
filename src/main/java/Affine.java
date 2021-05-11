/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author man21
 */
import java.util.Scanner;

public class Affine {
    public String mahoa(String p, int a, int b){
        String mahoa ="";
        for(int i=0;i<p.length();i++){
            char kitu = p.charAt(i);
            int m = (int) kitu - 97;
            int y = ((a*m + b) % 26)+97;
            mahoa += (char) y;
        }
        return mahoa;
        
    }
    public String giaima(String ma,int a,int b){
        String giaima ="";
        int t=0;
        for (int i = 0;i<26;i++){
            if ((a*i)%26 == 1){
                t = i;
            }
        }
        for(int i=0;i<ma.length();i++){
            char kitu = ma.charAt(i);
            int m = (int) kitu - 97;
            int y = ((t*(m - b)) % 26);
            System.out.println(y);
            if(y<0){
                y = (y + 97) + 26;
            }
            else{
                y = y + 97;
            }

            giaima += (char) y;
        }
        return giaima;
    }
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Affine aff = new Affine();
        int a,b;
        String p;
        System.out.println("Nhap chuoi p: ");
        p = scanner.nextLine();
        System.out.println("Nhập so a: ");
        a = scanner.nextInt();
        System.out.println("Nhập so b: ");
        b = scanner.nextInt();
        System.out.println("Chuoi ma hoa la: "+aff.mahoa(p,a,b));
        System.out.println("Chuoi giai ma la: "+aff.giaima(aff.mahoa(p,a,b),a,b));

    }
}
