/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package extremetournament;

import entities.Commentaire;
import entities.Forum;
import entities.Like;
import entities.Publication;
import java.sql.Connection;
import java.sql.SQLException;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.stage.Stage;
import services.CommentaireService;
import services.ForumService;
import services.LikeService;
import services.PublicationService;
import singleton.SingletonConnection;

/**
 *
 * @author ASUS
 */
public class mainclass extends Application{
    
    
     @Override
    public void start(Stage primaryStage) {
       
    
    }
    
    
    
    
    
    
    
    
    
    
     public static void main(String[] args) throws SQLException {
         
         
          Connection conn = SingletonConnection.getConn();

    // launch(args);
     

//      Forum f ;
//      f = new Forum("ddddddddddddd", 28);
//      ForumService fs= new ForumService();
      
      Commentaire c ;
      c= new Commentaire( "accccccccccccccccc", new java.sql.Date(System.currentTimeMillis()),34);
     Commentaire c2 = new Commentaire( "aaaaaaaaaaaa", new java.sql.Date(System.currentTimeMillis()),43 );
      
      CommentaireService cs= new CommentaireService();
      
      
        Publication p =new Publication("bbbbbbbbbbbbbbbbb", "ccccccccccccc", new java.sql.Date(System.currentTimeMillis()),"src\\assets\\EgurU8UWoAA7g4o.jpg",31);
                Publication p1 =new Publication("dffdddddddddddddddd", "zzzzzzzzzzzzz", new java.sql.Date(System.currentTimeMillis()), "c:/",29);
//
//                                Publication p2 =new Publication("dffdddddddddddddddd", "zzzzzzzzzzzzz", new java.sql.Date(System.currentTimeMillis()),8, "c:/");

        PublicationService ps=new PublicationService();
      
        Like l=new Like();
        LikeService ls=new LikeService();
//        
//         System.out.println("aaaaaaaaaaaaa");
        
         

        //        try {
//            System.out.println(fs.afficherforum()); ;
//            
//            System.out.println("ajout avec succés");
//            
//        } 
//        catch (SQLException sq) {
//            System.out.println(sq.getMessage());
//        }
//        
        
//
//try {
//    cs.supprimercomment(15);
//    
//  
//
//    
//////    cs.ajoutercomment(c2, p);
//////    cs.supprimercomment(85);
//////    cs.ajoutercomment(c2,p11);
//////    cs.modifiercomment(c, 4);
////System.out.println(cs.affichercomment()); 
////System.out.println(cs.getNombreCommentaire(22));
//cs.Signalercomment(16);
//
//    System.out.println("ajout");
//            
//        } 
//        catch (SQLException sq) {
//            System.out.println(sq.getMessage());
//      }
//    
//
//   
////
////

//try {
////    ps.ajouterpublication(p1);
////    ps.ajouterpublication(p1);
//    System.out.println(ps.afficherpublication());
////    ps.modifierpublication(p1);
////    System.out.println(ps.triPubparlike());
//            System.out.println("ajout Savec succés");
//            
//      } 
//        catch (SQLException sq) {
//            System.out.println(sq.getMessage());
//      }

//try{
//    
//    ls.supprimelike(1);
////    System.out.println(ls.afficherlike());
//    
//    System.out.println("ajout avec");
//}
//catch(SQLException sq){
//    
//    
//    System.out.println(sq.getMessage());
//}
////    
//    
// 
//        System.out.println(ps.getNombrePublication());
//        System.out.println(cs.getNombreCommentaire(15));
//        System.out.println(cs.triComment());
//        

         
         
     }
    
}
