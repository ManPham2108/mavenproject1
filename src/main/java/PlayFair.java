
import java.awt.Point;
import java.util.ArrayList;
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
public class PlayFair {
    static int bangmahoa[][] = new int[5][5];
    static ArrayList<Integer> chuoikitu = new ArrayList<>();
    static ArrayList<Integer> bangchucai = new ArrayList<>();
    static Scanner sc = new Scanner(System.in);
    public void duyetkytuIJ(){
        for (int h =0;h<chuoikitu.size();h++){
            for(int n = h+1;n<chuoikitu.size();n++){
                if(chuoikitu.get(h) == 8 || chuoikitu.get(h) == 9){
                    if(chuoikitu.get(h) == 8 && chuoikitu.get(n) == 9){
                        chuoikitu.remove(n);
                    }
                    if(chuoikitu.get(h) == 9 && chuoikitu.get(n) == 8){
                        chuoikitu.remove(n);
                    }
                }
                if(chuoikitu.get(h)==chuoikitu.get(n)){
                    chuoikitu.remove(n);
                }
            }
        }
        for (int h =0;h<chuoikitu.size();h++){
            for(int n=0;n<bangchucai.size();n++){
                if(chuoikitu.get(h)==8 || chuoikitu.get(h)==9){
                    if(bangchucai.get(n)==8 || bangchucai.get(n)==9){
                        bangchucai.remove(n);
                    }
                }
                else{
                    if(bangchucai.get(n)==9){
                        bangchucai.remove(n);
                    }
                }
            }
        }
    }
    public boolean duyetMang(int giatri){
        for (int i=0;i<chuoikitu.size();i++){
            if(chuoikitu.get(i)==giatri){
                return false;
            }
        }
        return true;
    }
    public void taoBangMaHoa(String k){
        int q = 0;
        int e = 0;
        for (int h=0;h<k.length();h++){
                chuoikitu.add((int) k.charAt(h) - 97);
        }
        for (int n =0;n<26;n++){
            bangchucai.add(n);
        }
        duyetkytuIJ();
        for (int i=0;i<5;i++){
            for(int j=0;j<5;j++){
                if(q<chuoikitu.size() && duyetMang(chuoikitu.get(q))==false){
                    bangmahoa[i][j] = chuoikitu.get(q);
                    q++;
                }
                else{
                    q++;
                    if(q<chuoikitu.size() && duyetMang(chuoikitu.get(q))==false){
                        bangmahoa[i][j] = chuoikitu.get(q);
                        q++;
                   }
                }
                if(q>chuoikitu.size()){
                    if(e<bangchucai.size() && duyetMang(bangchucai.get(e))==true){
                        bangmahoa[i][j]=bangchucai.get(e);
                        e++;
                    }
                    else{
                        e++;
                        while(duyetMang(bangchucai.get(e))==false){
                            e++;
                        }
                        if(e<bangchucai.size()){
                            bangmahoa[i][j]=bangchucai.get(e);
                            e++;
                        }
                    }
                }     
            }
        }
    }
    public String xuLiChuoi(String chuoi){
        chuoi = chuoi.replaceAll("\\s", "").trim();
        for(int i=0;i<chuoi.length();i=i+2){
            if((i+1)<chuoi.length() && chuoi.charAt(i)==chuoi.charAt(i+1)){
                chuoi = chuoi.substring(0,i+1)+"x"+chuoi.substring(i+1,chuoi.length());
            }
        }
        if((chuoi.length())%2 != 0){
            chuoi = chuoi + "x";
        }
        return chuoi;
    }
    public Point viTri(int giatri){
        Point pt = new Point(0,0);
        for (int i=0;i<5;i++){
            for(int j=0;j<5;j++){
                if(giatri == bangmahoa[i][j]){
                    pt = new Point(i,j);
                }
            }
        }
        return pt;
    }
    public String maHoa(String chuoi,String k){
        chuoi = xuLiChuoi(chuoi);
        taoBangMaHoa(k);
        String mahoa ="";
        int kytu1 = 0;
        int kytu2 = 0;
        System.out.println("x: "+chuoi);
        for(int i =0; i<chuoi.length();i=i+2){
            kytu1 = (int) chuoi.charAt(i) - 97;
            if(i+1<chuoi.length()){
                kytu2 = (int) chuoi.charAt(i+1) - 97; 
            }  
            int x1= (int) viTri(kytu1).getX();
            int x2= (int) viTri(kytu2).getX();
            int y1= (int) viTri(kytu1).getY();
            int y2= (int) viTri(kytu2).getY();
            if(x1==x2){
                y1=(y1 + 1 ) % 5;
                y2=(y2 + 1 ) % 5;         
            }
            else{
                if(y1==y2){
                    x1=(x1+1)%5;
                    x2=(x2+1)%5;
                }
                else{
                    int temp = y1;
                    y1 = y2;
                    y2 = temp;
                }
            }
            mahoa = mahoa + (char) (bangmahoa[x1][y1] + 97) + (char) (bangmahoa[x2][y2] + 97);
        }
        return mahoa;
    }
    public String giaMa(String mahoa,String k,String chuoi){
        String giaima="";
        taoBangMaHoa(k);
        int kytu1 =0;
        int kytu2 =0;
        for(int i=0;i<mahoa.length();i=i+2){
            kytu1 = (int) mahoa.charAt(i) - 97;
            if(i+1<mahoa.length()){
                kytu2 = (int) mahoa.charAt(i+1) - 97; 
            }  
            int x1= (int) viTri(kytu1).getX();
            int x2= (int) viTri(kytu2).getX();
            int y1= (int) viTri(kytu1).getY();
            int y2= (int) viTri(kytu2).getY();
            if(x1==x2){
                y1=(y1 + 4 ) % 5;
                y2=(y2 + 4 ) % 5;         
            }
            else{
                if(y1==y2){
                    x1=(x1+4)%5;
                    x2=(x2+4)%5;
                }
                else{
                    int temp = y1;
                    y1 = y2;
                    y2 = temp;
                }
            }
            giaima = giaima +(char) (bangmahoa[x1][y1] + 97) + (char) (bangmahoa[x2][y2] + 97);
        }
        if(giaima.charAt(giaima.length()-1)=='x'){
            giaima = giaima.substring(0,giaima.length()-1);
        }
        ArrayList<Integer> vitrikhoangtrang = new ArrayList<>();
        for(int i=0;i<chuoi.length();i++){
            if(chuoi.charAt(i)==' '){
                vitrikhoangtrang.add(i);
            }
        }
        int i = 0;
        for(int j=0;j<giaima.length();j++){
            if(i<vitrikhoangtrang.size() && j==vitrikhoangtrang.get(i)){
                giaima=giaima.substring(0,j)+" "+giaima.substring(j,giaima.length());
                i++;
            }
        }
        return giaima;
    }
    public static void main(String[] args){
        PlayFair pf = new PlayFair();
        System.out.println("Nhap chuoi can ma hoa: ");
        String chuoi = sc.nextLine();
        System.out.println("Nhap vao khoa K: ");
        String k = sc.nextLine();
        String mahoa = pf.maHoa(chuoi, k);
        System.out.println("Chuoi ma hoa la: "+mahoa);
        System.out.println("Chuoi giai ma la: "+pf.giaMa(mahoa,k, chuoi));
    }
}
