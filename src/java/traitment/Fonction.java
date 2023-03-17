/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package traitment;

import annotation.Myannotation;
import etu1883.frameworki.Mapping;
import java.io.File;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Set;
import java.util.Vector;

/**
 *
 * @author ITU
 */
public class Fonction {
    
//    public Vector<Class> listeclasse(String myPackage,String pathProjet)throws Exception{
//        String pack=myPackage.replaceAll("\\.", "\\\\");
//        String path=pathProjet+"\\src\\java\\"+pack;
//        File file=new File(path);
//        File[] listefile=file.listFiles();
//        Vector<Class> listeclasse=new Vector<Class>();
//        for(File f : listefile){
//             String nomFichier=f.getName().substring(0,f.getName().lastIndexOf('.'));
//             Class classe=Class.forName(myPackage+"."+nomFichier);
//             listeclasse.add(classe);
//        }
//        return listeclasse;
//    }
    
    
    public HashMap<String,Mapping> listemethode(String nomClasse) throws ClassNotFoundException{
        Class classe=Class.forName(nomClasse);
        Method[] listemethode=classe.getDeclaredMethods();
        String annotation="Myannotation";
        HashMap<String,Mapping> map=new HashMap<String,Mapping>();
        for(int i=0;i<listemethode.length;i++){
            if(listemethode[i].isAnnotationPresent(Myannotation.class)){
                Myannotation an=listemethode[i].getAnnotation(Myannotation.class);
                Mapping mapping=new Mapping(classe.getName(),listemethode[i].getName());
                map.put(an.value(),mapping);
            }
        }
        return map;
    }
    
    public void getsousdossier(String pathProjet,String pack,Vector<String> tableau){
        String[] noslash=pack.split("\\.");
        File folder=new File(pathProjet+"\\"+noslash[noslash.length-1]);
        File[] listedossier=folder.listFiles();
        String enrepack=pack;
        String newPath="";
        String fileName="";
        for(File file : listedossier){
            if(file.isDirectory()){
                enrepack=enrepack+"."+file.getName();
                newPath=pathProjet+"\\"+pack;
                getsousdossier(newPath,enrepack,tableau);
            }else{
                fileName=file.getName();
                enrepack=enrepack+"."+fileName.substring(0,fileName.lastIndexOf('.'));
                tableau.add(enrepack);
            }
            enrepack=pack;
        }
    }
        
    public Vector<String> listeClasse(String pathProjet){
        String path=pathProjet+"\\WEB-INF\\classes";
        File folder=new File(path);
        File[] listedossier=folder.listFiles();
        Vector<String> enregistrement=new Vector<String>();
        for(File file : listedossier){
            if(file.isDirectory()){   //si file est un dossier
                Vector<String> mini=new Vector<String>();
                getsousdossier(path,file.getName(),mini);
                enregistrement.addAll(mini);
            }
        }
        return enregistrement;
    }
    
    public HashMap<String,Mapping> listeHashMapAllClass(String pathProjet) throws ClassNotFoundException{
        Vector<String> allClasse=listeClasse(pathProjet);
        HashMap<String,Mapping> newmap=new HashMap<String,Mapping>(); 
        String key="";
        for(int i=0;i<allClasse.size();i++){
            HashMap<String,Mapping> map=listemethode(allClasse.get(i));
            Set<String> keys=map.keySet();
            for(String j : keys){
               newmap.put(j,map.get(j));
            }
        }
        return newmap;
    }
    
    /*public HashMap<String,Mapping> mapValue(String valueAnnotation,String pathProjet) throws ClassNotFoundException{
        Fonction fonction=new Fonction();
        Vector<HashMap<String,Mapping>> listemap=fonction.listeHashMapAllClass(pathProjet);
        HashMap<String,Mapping> map=new HashMap<String,Mapping>(); 
        for(int i=0;i<listemap.size();i++){
            if(listemap.get(i).get("methodeget")!=null){
                map=listemap.get(i);
                break;
            }
       }
        return map;
    }*/

    
    
    public static void main(String[] args)throws Exception{
       Fonction fonction=new Fonction();
        
       
//       Vector<String> listepack=fonction.listeClasse("C:\\Users\\ITU\\Documents\\NetBeansProjects\\sprint2");
//       
//       Vector<HashMap<String,Mapping>> listemap=fonction.listeHashMapAllClass("C:\\Users\\ITU\\Documents\\NetBeansProjects\\sprint2");
//       
//       for(int i=0;i<listemap.size();i++){
//            System.out.println(listemap.get(i).get("methodeget"));
//       }

        HashMap<String,Mapping> map=fonction.listeHashMapAllClass("C:\\Users\\ITU\\Documents\\NetBeansProjects\\sprint2\\build\\web\\");
        System.out.println(map.get("methodeget"));
    }
}
