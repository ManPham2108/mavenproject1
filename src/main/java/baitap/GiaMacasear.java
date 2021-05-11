/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package baitap;

import java.util.Scanner;

/**
 *
 * @author man21
 */
public class GiaMacasear {
   public String maHoa(String a, int k){
        String mahoa = "";
        for (int i =0; i<a.length();i++){
            char a1 = a.charAt(i);
            int m = (int) a1 - 97;
            //System.out.println("aa: "+m);
            m = Math.floorMod((m + k), 26);
            m = m + 97;
            mahoa += (char) m;
        }
        return mahoa;
    }
    public String giaMa(String mahoa,int k){
        String giama = "";
        for (int i =0; i<mahoa.length();i++){
            char a1 = mahoa.charAt(i);
            if(a1 != ' '){
                int m = (int) a1 - 97;
                m = Math.floorMod((m - k), 26);
                m = m + 97;
                giama += (char) m;
            }
            else{
                giama += ' ';
            }
        }
        return giama;
    }
    public static void main(String args[]) {
        // TODO code application logic here
        GiaMacasear bt = new GiaMacasear();
        String[] mang ={"xin","chao"};
        Scanner sc = new Scanner(System.in);
        System.out.println("Nhap chuoi a: ");
        String a = sc.nextLine();
        //System.out.println("Nhap so k: ");
        //int k = sc.nextInt();
       // System.out.println("Chuoi gia ma: " +bt.giaMa(a,k));
        for (int k =1;k<=26;k++){
            System.out.println("giai ma: "+bt.giaMa(a,k));
        }
    }
}
