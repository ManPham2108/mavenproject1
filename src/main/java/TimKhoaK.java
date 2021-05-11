
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
public class TimKhoaK {
    public String timKhoaK(String chuoi,String mahoa,int m){
        String khoak = "";
        String chuoi1 = chuoi.substring(0, m);
        String mahoa1 = mahoa.substring(0, m);
        for(int i=0;i<chuoi1.length();i++){
            int a = (int) chuoi1.charAt(i) - 97;
            int b = (int) mahoa1.charAt(i) - 97;
            int k = (-a + b)%26;
            if(k<0){
                k = k + 26;
            }
            k = k +97;
            khoak += (char) k;
        }
        return khoak;
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        TimKhoaK vi = new TimKhoaK();
        System.out.println("Nhap m: ");
        int m = sc.nextInt();
        sc.nextLine();
        System.out.println("Nhap vao ban ro: ");
        String banro = sc.nextLine();
        System.out.println("Nhap vao ban ma: ");
        String mahoa = sc.nextLine();
        System.out.println("Chuoi K la: "+vi.timKhoaK(banro, mahoa, m));
    }
}
