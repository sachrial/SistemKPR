package com.juaracoding.sistem.kpr;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class main {
	
	static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
	static final String DB_URL = "jdbc:mysql://localhost/pinjamandb";
	static final String DB_User ="root";
	static final String DB_Password ="";
	
	
	static Connection conn;
	static Statement stat;
	static ResultSet rs;
	
	static InputStreamReader data = new InputStreamReader(System.in);
	static BufferedReader br = new BufferedReader(data);
	
	public static void main(String[] args) {
		try {
			Class.forName(JDBC_DRIVER);
			conn = DriverManager.getConnection(DB_URL, DB_User, DB_Password);
			
			stat= conn.createStatement();
			
			
			while (!conn.isClosed()) {
				showMenu();			
			}
			
			stat.close();
			conn.close();
			
		} catch (Exception e) {
			e.printStackTrace();			
		}
}
	
	static void showMenu() {
		System.out.println("===============================");
		System.out.println("1. Masukan Data");
		System.out.println("2. Tampilkan Data");
		System.out.println("3. Keluar");
		System.out.println("Masukan Pilihan = ");
//		int pil = sc.nextInt();
		try {
			int pil = Integer.parseInt(br.readLine());		
		switch (pil) {
		case 1: {
		    insertData();
		    break;
		}
		case 2: {
			showData();
			break;
		}
		case 3: {
			System.exit(0);
			break;
		}
		default:
			throw new IllegalArgumentException("Unexpected value: " + pil);
		}
	} catch (Exception e) {
		e.printStackTrace();
    }
}		
	
	static void insertData() {
		try {
			System.out.print("angsuranke = ");
			int angsuranke = Integer.parseInt(br.readLine());
			System.out.print("tanggal = ");
			String tanggal = br.readLine();
			System.out.print("totalAngsuran = ");
			int totalAngsuran = Integer.parseInt(br.readLine());
			System.out.print("angsuranBunga = ");
			int angsuranBunga = Integer.parseInt(br.readLine());
			System.out.print("angsuranPokok = ");
			int angsuranPokok = Integer.parseInt(br.readLine());
			System.out.print("sisaPinjaman = ");
			int sisaPinjaman = Integer.parseInt(br.readLine());
			
			String qry = "INSERT INTO `angsur`(`angsuranke`, `tanggal`, `totalAngsuran`, `angsuranBunga`, `angsuranPokok`,`sisaPinjaman`) VALUES (\"%d %t %d %d %d %d)";

			qry = String.format(qry, angsuranke,tanggal,totalAngsuran,angsuranBunga,angsuranPokok,sisaPinjaman);
			
			stat.execute(qry);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
		static void showData() {
			String qry = "SELECT * FROM angsur";
			try {
				rs = stat.executeQuery(qry);
				System.out.println("======Tampilkan======");
				while(rs.next()) {
					int angsuranke = rs.getInt("angsuranke");
					 Date tanggal= rs.getDate("tanggal");
					int totalAngsuran = rs.getInt("totalAngsuran");
					int angsuranBunga = rs.getInt("angsuranBunga");
					int angsuranPokok = rs.getInt("angsuranPokok");
					int sisaPinjaman = rs.getInt("sisaPinjaman");
					System.out.println(String.format("%d %t %d %d %d %d)",angsuranke,tanggal,totalAngsuran,angsuranBunga,angsuranPokok,sisaPinjaman));
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
}
