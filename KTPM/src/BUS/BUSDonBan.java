/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BUS;

import DAO.DAODonBan;
import DTO.DTOChiTietDonBan;
import DTO.DTODonBan;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author nguye
 */
public class BUSDonBan {
    public static ArrayList<DTODonBan> ds;
    DAODonBan DAO=new DAODonBan();

    public BUSDonBan() {
    }
    public void docDB(){
        try{
            if(ds==null)
            {
                ds=new ArrayList<>();
                ds=DAO.docDB();
                if(BUSChiTietDonBan.ds==null)
                {
                    BUSChiTietDonBan BUS=new BUSChiTietDonBan();
                    BUS.docDB();
                }
                tinhTongTien();
            }
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, "Không đọc được dữ liệu bảng taikhoan BUS");
        }
    }
    public void them(DTODonBan DTO) //cần ghi lại khi qua class khác
    {
        try{
            DAO.them(DTO);//ghi vào database
            ds.add(DTO);//cập nhật arraylist
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, "Không thêm được dữ liệu bảng taikhoan BUS");
        }
    }
    
    public void xoa(int ID, int index) //cần ghi lại khi qua class khác
    {
        try{
            DAO.xoa(ID); // Xóa dữ liệu trên database
            if(ds!=null)
                ds.remove(index);
    
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, "Không xóa được dữ liệu bảng taikhoan BUS");
        }
    }
    public void tinhTongTien(){
        for(DTODonBan DTO:ds)
        {
            int Tong=0;
            for(DTOChiTietDonBan DTO1:BUSChiTietDonBan.ds)
                if(DTO1.getIDDonBan()==DTO.getIDDonBan())
                    Tong+=DTO1.getDonGia();
            DTO.setTongTien(Tong);
        }
    }
}