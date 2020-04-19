/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pokepai;

/**
 *
 * @author hasee-pc
 */
public class Poke5 {
    
    String a[],b[],c[],d[],cc[],dd[],g[];
    public static void main(String[] args) {
       Poke5 pk=new Poke5();
       //pk.抽取13张牌();
       pk.抽4人牌();
      }
public void 生成牌(){
     a= new String [52];//存放正常顺序的52张牌
     b=new String[52];
     c=new String[13];
     d=new String[13];
     cc=new String[13];
     dd=new String[13];
     g=new String[52];
    String name[]= {"fang","mei","hong","hei"};
    int k=0;
      for(int i=0;i<4;i++){
          for(int j=0;j<13;j++){
        a[k]= name[i]+(j+1)+".jpg" ;    
        b[k]=a[k];
        k++;     
          }   
    }
      //显示牌(a);
}
public void 显示牌(String[] s){
    for(int i=0;i<s.length;i++){
         System.out.println(s[i]);
             }
}
public void 打乱牌(){
     int x,y;
      String z;
      for(int i=0;i<100;i++){
           x= (int)(Math.random()*51);
           y=(int)(Math.random()*51);
           z= b[x];
           b[x]=b[y];
           b[y]=z;
      }
     // 显示牌(b);
}
public String[] 抽取13张牌(){ 
      生成牌();
      打乱牌();
      c=取牌(0);
      d=排序(c);
       System.out.println("排好顺序的13张牌");
  显示牌(d);
  return d;
    }  
public String[] 取牌(int s){
    int k=s;
    for(int i=0;i<=12;i++){
        cc[i]=b[k];
        k++;
    }
    return cc;
}
public String[] 排序(String[] sz){
     int k=0;
    for(int i=0;i<52;i++){
        for(int j=0;j<13;j++){
           //System.out.println(a[i]+ "    "+c[j]);
            if(a[i].equals(sz[j])){
                dd[k]=a[i];
                k++;
        }
        }    
    }   
    return dd;
}
public void 装牌(int s,int e){
    int k=0;
   for(int i=s;i<=e;i++) {
       g[i]=dd[k];
       k++;
   }
}
public String[] 抽4人牌(){
    生成牌();
    打乱牌();
    cc=取牌(0);
    dd=排序(cc);
    装牌(0,12);
    cc=取牌(13);
    dd=排序(cc);
    装牌(13,25);
    cc=取牌(26);
    dd=排序(cc);
    装牌(26,38);
    cc=取牌(39);
    dd=排序(cc);
    装牌(39,51);
   // 显示牌(g);
  return g;  
}
}
