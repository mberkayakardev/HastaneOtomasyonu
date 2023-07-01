import java.sql.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;


  //connection sınıfımızı burda tanuımladık


public class baglanti {
    
private String kullanici_adi = "root";  //En başta veritabanımızı oluştururken bize şifre belirtmedigimiz için
private String parloa="";
private String db_ismi ="hastaneotomasyonveritabani"; //veritabanımızın ismi
private String host="localhost"; //bi sunucuda değil lokalde çalışacagımızdan kaynaklı olarak local hostu belirliedik
private int port = 3306; //mysql in port numarası xampp coıntol kısmında yazıyo

private Connection con =null;  //bağlantı fonksiyonumuz 

private Statement statement =null;//sql sorguları bu kodla çalışır

public String[] hastakaydigetir(int tc){
    
    String[] dizi=new String[6];
String sorgu="SELECT * FROM hastakayittablo WHERE hastatc="+tc;
    try {
        statement =con.createStatement();
        ResultSet rs = statement.executeQuery(sorgu);
        while  (rs.next()){
        int id =rs.getInt("hastatc");
        String hastaadi =rs.getString("hastaadi");
        String hastasoyadi=rs.getString("Hastasoyadi");
        String Hastaadresi=rs.getString("hastaevadresi");
        String hastatel=rs.getString(String.valueOf("hastatelno"));
        String hastacinsiyet=rs.getString("hastacinsiyet");
        
        
            dizi[0]=String.valueOf(id);
            dizi[1]=hastaadi;
            dizi[2]=hastasoyadi;
            dizi[3]=Hastaadresi;
            dizi[4]=hastatel;
            dizi[5]=hastacinsiyet;      
            
     
            
        }
    } catch (SQLException ex) {
        Logger.getLogger(baglanti.class.getName()).log(Level.SEVERE, null, ex);
    }

return dizi;
}

public String[] doktorgetir(int doktorno){
    
    String[] dizi=new String[5];
String sorgu="SELECT * FROM doktortablo WHERE doktorno="+doktorno;
    try {
        statement =con.createStatement();
        ResultSet rs = statement.executeQuery(sorgu);
        while  (rs.next()){
        int Doktorid =rs.getInt("doktorno");
        String doktorisim =rs.getString("doktorisim");
        String doktorsoyisim=rs.getString("doktorsoyisim");
        String doktorbrans=rs.getString("doktorbrans");
        String doktortc=rs.getString(String.valueOf("doktortc"));
        
        
        
            dizi[0]=String.valueOf(doktorno);
            dizi[1]=doktorisim;
            dizi[2]=doktorsoyisim;
            dizi[3]=doktorbrans;
            dizi[4]=doktortc;
            
     
            
        }
    } catch (SQLException ex) {
        Logger.getLogger(baglanti.class.getName()).log(Level.SEVERE, null, ex);
    }

return dizi;
}


public void hastaekle (int hastatc,String hastaadi,String Hastasoyadi,String hastaevadresi, int hastadogumtarihi,String hastatelno,String cinsiyet){
    try {
        statement =con.createStatement();
        
        String sorgu ="INSERT INTO hastakayittablo (hastatc,hastaadi,hastasoyadi,hastaevadresi,hastadogumtarih,hastatelno,hastacinsiyet) VALUES ("+hastatc+",'"+hastaadi+"','"+Hastasoyadi+"','"+hastaevadresi+"',"+hastadogumtarihi+",'"+hastatelno+"','"+cinsiyet+"')";
        statement.executeUpdate(sorgu);
        
    } catch (SQLException ex) {
        Logger.getLogger(baglanti.class.getName()).log(Level.SEVERE, null, ex);
    }



}
public void doktorekle (int doktorno,String doktorisim,String doktorsoyisim,String doktorbrans, int doktortc){
    try {
        statement =con.createStatement();
        
        String sorgu ="INSERT INTO doktortablo (doktorno,doktorisim,doktorsoyisim,doktorbrans,doktortc) VALUES ("+doktorno+",'"+doktorisim+"','"+doktorsoyisim+"','"+doktorbrans+"',"+doktortc+")";
        statement.executeUpdate(sorgu);
        
    } catch (SQLException ex) {
        Logger.getLogger(baglanti.class.getName()).log(Level.SEVERE, null, ex);
    }



}

public baglanti(){

  
    
    
    
    
    
String url="jdbc:mysql://"+host+":"+port+"/"+db_ismi+"?usiUnicode=true&characterEncoding=utf8";
try{   //serveri başlatıyoruz güvenlik sebebi ile ve program çalışma açısından rtye casth a alıyoruz
Class.forName("com.mysql.jdbc.Driver");
}
catch(ClassNotFoundException ex){
    Logger.getLogger(baglanti.class.getName()).log(Level.SEVERE,null,ex); 
    System.out.println("Driver kaynaklı bir problem var veya driver bulunamadı hatası");
    
}


    try {
        con=DriverManager.getConnection(url, kullanici_adi, parloa);
        System.out.println("Bağlantı Başarılı");
    } catch (SQLException ex) {
        System.out.println("Baglantı basarısız");
    }
}

public static void main(String[] args){
baglanti bag = new baglanti();

}

    String[] doktorgetir(String id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
 