/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.BinaryBitmap;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatReader;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.NotFoundException;
import com.google.zxing.Result;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.common.HybridBinarizer;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;
import javax.imageio.ImageIO;
import singleton.SingletonConnection;
/**
 *
 * @author ibrahim
 */
public class QRservices {


    /**
     *
     * @param A
     * @return
     */
    Connection connection;
    Statement stm;

    public String QR (String A) throws SQLException{
              
             try {
            String qrCodeData = " Today we have "+A+"";
            String filePath = "C:\\Users\\ibrahim\\Documents\\NetBeansProjects\\ExtremeTournament\\src\\img\\QR\\"+A+".png";
            
            String charset = "UTF-8"; // or "ISO-8859-1"
            Map < EncodeHintType, ErrorCorrectionLevel > hintMap = new HashMap < EncodeHintType, ErrorCorrectionLevel > ();
            
            hintMap.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.L);
            BitMatrix matrix = new MultiFormatWriter().encode(
                new String(qrCodeData.getBytes(charset), charset),
                BarcodeFormat.QR_CODE, 200, 200, hintMap);
            MatrixToImageWriter.writeToFile(matrix, filePath.substring(filePath
                .lastIndexOf('.') + 1), new File(filePath));
            System.out.println("QR Code created successfully!");
            return filePath;
        } catch (Exception e) {
            System.err.println(e);
            return "";
        }}
}
//
//            connection = SingletonConnection.getConn();
//
//            String query = "select * from tounroi";
//            Statement stmt = null;
//            stmt = connection.createStatement();
//            ResultSet rs = stmt.executeQuery(query);
//            while (rs.next()) {
//            	QRservices.generate_qr(rs.getString("sl_no"),rs.getString("tounroi"));
//            }
//		} catch (Exception e) {
//			// TODO: handle exception
//                        
//		}
//        return null;
       
    
//    
//    public static void generate_qr(String image_name,String qrCodeData) {
//        try {
//            String filePath = "C:\\Users\\ibrahim\\Desktop\\ExtremeTournament\\src\\QR\\"+image_name+".png";
//            String charset = "UTF-8"; // or "ISO-8859-1"
//            Map < EncodeHintType, ErrorCorrectionLevel > hintMap = new HashMap < EncodeHintType, ErrorCorrectionLevel > ();
//            hintMap.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.L);
//            BitMatrix matrix = new MultiFormatWriter().encode(
//                new String(qrCodeData.getBytes(charset), charset),
//                BarcodeFormat.QR_CODE, 200, 200, hintMap);
//            MatrixToImageWriter.writeToFile(matrix, filePath.substring(filePath
//                .lastIndexOf('.') + 1), new File(filePath));
//            System.out.println("QR Code image created successfully !!!");
//        } catch (Exception e) {
//            System.err.println(e);
//        }
    
     
    /**
     *
     * @param filePath
     * @param charset
     * @param hintMap
     * @return
     * @throws FileNotFoundException
     * @throws IOException
     * @throws NotFoundException
     */
//    public static String readQRCode(String filePath, String charset, Map hintMap)
//     throws FileNotFoundException, IOException, NotFoundException {
//        BinaryBitmap binaryBitmap = new BinaryBitmap(new HybridBinarizer(
//            new BufferedImageLuminanceSource(
//                ImageIO.read(new FileInputStream(filePath)))));
//        Result qrCodeResult = new MultiFormatReader().decode(binaryBitmap, hintMap);
//        return qrCodeResult.getText();
//    }
        
    

