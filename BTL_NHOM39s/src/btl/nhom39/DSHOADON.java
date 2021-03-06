package btl.nhom39;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;

public class DSHOADON {

    ArrayList<HOADON> hd = new ArrayList();
    ArrayList<MATHANG> mh = new ArrayList();
    ArrayList<KHACHHANG> kh = new ArrayList();
    ArrayList<NHANVIEN> nv = new ArrayList();

    public void Data() {
        try {
            ArrayList<NHANVIEN> nv = new ArrayList();
            nv.clear();
            FileReader frnv = new FileReader("nhanvien.txt");
            BufferedReader brnv = new BufferedReader(frnv);
            String line = "";
            while (true) {
                line = brnv.readLine();
                if (line == null) {
                    break;
                }
                String txt[] = line.split("\t");
                String name = txt[0];
                String gTinhString = txt[1];
                String diaChi = txt[2];
                int ns = Integer.parseInt(txt[3]);
                String SDT = txt[4];
                String ma = txt[5];
                float hSL = Float.parseFloat(txt[6]);
                int nc = Integer.parseInt(txt[7]);

                this.nv.add(new NHANVIEN(name, gTinhString, diaChi, ns, SDT, ma, hSL, nc));
            }
            ArrayList<MATHANG> mh = new ArrayList<>();
            mh.clear();
            FileReader frmh = new FileReader("mathang.txt");
            BufferedReader brmh = new BufferedReader(frmh);

            while (true) {
                line = brmh.readLine();
                if (line == null) {
                    break;
                }
                String txt[] = line.split("\t");
                String name = txt[0];
                String hangSX = txt[1];
                String mahang = txt[2];
                int soluong = Integer.parseInt(txt[3]);
                float gia = Float.parseFloat(txt[4]);
                this.mh.add(new MATHANG(name, hangSX, mahang, soluong, gia));
            }
            ArrayList<KHACHHANG> kh = new ArrayList<>();
            kh.clear();
            FileReader frkh = new FileReader("khachhang.txt");
            BufferedReader brkh = new BufferedReader(frkh);
            while (true) {
                line = brkh.readLine();
                if (line == null) {
                    break;
                }
                String txt[] = line.split("\t");
                String name = txt[0];
                String gTinhString = txt[1];
                String diaChi = txt[2];
                int ns = Integer.parseInt(txt[3]);
                String SDT = txt[4];
                String ma = txt[5];

                this.kh.add(new KHACHHANG(name, gTinhString, diaChi, ns, SDT, ma));
                frkh.close();
                brkh.close();
                frnv.close();
                brnv.close();
                frmh.close();
                brmh.close();
            }

        } catch (Exception e) {
        }
    }

    public void nhapDS() {
        Scanner sc = new Scanner(System.in);
        Data();
        int n;
        int z = 0;
        do {
            System.out.print("\nNh???p s??? ho?? ????n: ");
            n = Integer.parseInt(sc.nextLine());
        } while (n <= 0);
        for (int i = 0; i < n; ++i) {
            System.out.printf("\nNh???p ho?? ????n th??? %d", i + 1);
            HOADON x = new HOADON();
            x.nhap();
            this.hd.add(x);

        }
    }

    public void xuatDS() {

        Data();
        for (HOADON a : hd) {
            System.out.printf("\nH??A D??N B??N H??NG\n");
            a.xuat();

        }

    }

    public void luuFile() throws FileNotFoundException, IOException {

        File fname = new File("hoadon.txt");
        FileOutputStream fout = new FileOutputStream(fname);
        ObjectOutputStream out = new ObjectOutputStream(fout);
        out.writeObject(hd);
    }

    public void docFile() throws FileNotFoundException, IOException, ClassNotFoundException {
        File fname = new File("hoadon.txt");
        FileInputStream fin = new FileInputStream(fname);
        ObjectInputStream in = new ObjectInputStream(fin);
        this.hd = (ArrayList<HOADON>) in.readObject();
        in.close();
        fin.close();
    }

    //HERE!!!
    public void xoa1hd() {
        Scanner SC = new Scanner(System.in);
        System.out.print("\n Nh???p m?? h??a ????n cho h??a ????n c???n xo??: ");
        boolean a = true;
        String xString = SC.nextLine();
        for (int i = 0; i < hd.size(); ++i) {
            if (hd.get(i).getMaHD().equals(xString)) {
                this.hd.remove(i);
                System.out.println("xoa th??nh c??ng!!!");
                a = false;
            }

        }
        if (a) {
            System.out.println("Kh??ng c?? h??a ????n n??o c?? m?? s??? n??y " + xString);
        }
    }

    public void tableH() {
        System.out.printf("%20s%20s%15s%15d%20.0f\n", "M?? nh??n vi??n b??n", "M?? Thu???", "M?? h??a ????n", "S??? l?????ng SP", "Gi?? ti???n");
    }

    public void tim1hd() {
        Scanner SC = new Scanner(System.in);
        System.out.print("\n Nh???p m?? h??a ????n c???n t??m: ");
        String xString = SC.nextLine();
        for (int i = 0; i < hd.size(); ++i) {

            if (hd.get(i).getMaHD().equals(xString)) {
                tableH();
                this.hd.get(i).xuat();
            }
        }

    }

    public void sapXepHoaDon() {
        Collections.sort(this.hd, new Comparator<HOADON>() {

            @Override
            public int compare(HOADON o1, HOADON o2) {

                return (o1.getMaHD().compareTo(o2.getMaHD()));

            }
        });
    }

    public void thayDoiMaNVBanTrongHoaDon() {
        Scanner SC = new Scanner(System.in);
        System.out.print("\n Nh???p m?? h??a ????n c???n thay ?????i th??ng tin: ");
        String xString = SC.nextLine();
        for (int i = 0; i < hd.size(); ++i) {

            if (hd.get(i).getMaHD().equals(xString)) {
                System.out.print("\nM???I nh???p m?? nh??n vi??n b??n m???i: ");
                String newName = SC.nextLine();
                hd.get(i).setMaNVBan(newName);
                System.out.print("\n thay ?????i th??nh c??ng!!!!");
            }

        }
    }
    public void emtpy() {
        try {
            (new FileOutputStream("hoadon.txt")).close();
            System.out.print("\nFile ???? r???ng!");
        } catch (Exception var2) {
            var2.printStackTrace();
        }

    }

}
