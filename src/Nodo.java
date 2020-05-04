
public class  Nodo{ 
    private String nombre;
    private String izquierdo;
    private String derecho;
    private boolean anulable;
    private int etiqueta;
    private String padre;
    private String tipo;
    private String id;
    private String primera_pos;
    private String ultima_pos;
    private String sig_pos;
    public String tabla;
   
    public Nodo(String nombre,String derecho, String izquierdo, 
            boolean anulable, int etiqueta, String padre, 
            String tipo,String id,String primera_pos, 
            String ultima_pos, String sig_pos,String tabla){
        this.nombre=nombre;
        this.izquierdo=izquierdo;
        this.derecho=derecho;
        this.anulable=anulable;
        this.etiqueta=etiqueta;
        this.padre=padre;
        this.tipo=tipo;
        this.id=id;
        this.primera_pos=primera_pos;
        this.ultima_pos=ultima_pos;
        this.sig_pos=sig_pos;
        
    }
    public String getNodoNombre(){
        return nombre;
       }
   public String getNodoIzquierdo(){
        return izquierdo;
       }
   public String getNodoDerecho(){
        return derecho;
       }
   public boolean getNodoAnulable(){
        return anulable;
       }
   public int getNodoEtiqueta(){
        return etiqueta;
       }
   public String getNodoPadre(){
        return padre;
       }
   public String getNodoTipo(){
        return tipo;
       }
    public String getNodoId(){
        return id;
       }
     public String getNodoPrimera_pos(){
        return primera_pos;
       }
    public String getNodoUltima_pos(){
        return ultima_pos;
       }
    public String getNodoSig_pos(){
        return sig_pos;
       }
   public String getNodoTabla(){
        return tabla;
       }
   public void setNodoPadre(String padre){
       this.padre=padre;
       }
   public void setNodoTipo(String tipo){
       this.tipo=tipo;
       }
   public void setNodoAnulable(Boolean anulable){
       this.anulable=anulable;
       }
   public void setNodoPrimera_pos(String primera_pos){
       this.primera_pos=primera_pos;
       }
    public void setNodoUltima_pos(String ultima_pos){
       this.ultima_pos=ultima_pos;
       }   
     public void setNodoSig_pos(String sig_pos){
       this.sig_pos=sig_pos;
       } 
   public void setNodoTabla(String tabla){
       this.tabla=tabla;
       } 
     public String busca_nodo_padre(int posicion,String nodo, Nodo arreglo[],int tope){
       int j=0,id;
       String resultado="";
       
      while(j<tope){
          //if(nodo.equals(arreglo[j].getNodoId())){
              if(nodo.equals(arreglo[j].getNodoIzquierdo())
                      ||nodo.equals(arreglo[j].getNodoDerecho())){
           resultado=arreglo[j].getNodoId();
           }
              
          j++;
       //}
      }return resultado;
   }
   public void padre(int s,Nodo nodos[],int cont){
       String papa=""; 
       if(nodos[s].getNodoTipo().equals("hoja")||nodos[s].getNodoTipo().equals("operador")
               ||nodos[s].getNodoTipo().equals("bioperador")){
       papa=nodos[s].busca_nodo_padre(cont, nodos[s].getNodoId(), nodos, cont);
        nodos[s].setNodoPadre(papa);}
       else if(nodos[s].getNodoTipo().equals("raiz")){
           nodos[s].setNodoPadre(nodos[s].getNodoId());
       }
   }
   public boolean busca_nodo_anulable(int posicion,String nodo, Nodo arreglo[],int tope){
       int j=0,id;
       boolean resultado=false;
       
      while(j<tope){
          if(nodo.equals(arreglo[j].getNodoId())){
           resultado=arreglo[j].getNodoAnulable();
           }
          j++;
       }
       return resultado;
   }
   public void anulable(int s,Nodo nodos[],int cont){
    boolean iz=false,de=true;
       if(nodos[s].getNodoTipo().equals("hoja")){
              nodos[s].setNodoAnulable(false);
          }
          else if(nodos[s].getNodoTipo().equals("operador")){
                if(nodos[s].getNodoNombre().equals("*")||nodos[s].getNodoNombre().equals("?")){
                    nodos[s].setNodoAnulable(true);
                }
                if(nodos[s].getNodoNombre().equals("+")){
                    iz=nodos[s].busca_nodo_anulable(s,nodos[s].getNodoIzquierdo(),nodos,cont);
                    nodos[s].setNodoAnulable(iz);
                }
          }//fin if operador
          else if(nodos[s].getNodoTipo().equals("bioperador")){
                    if(nodos[s].getNodoNombre().equals(".")){

                        iz=nodos[s].busca_nodo_anulable(s,nodos[s].getNodoIzquierdo(),nodos,cont);
                        de=nodos[s].busca_nodo_anulable(s, nodos[s].getNodoDerecho(), nodos, cont);
                        if(iz==true &&de==true){nodos[s].setNodoAnulable(true);}
                        else{nodos[s].setNodoAnulable(false);}
                    }
                    if(nodos[s].getNodoNombre().equals("|")){
                        iz=nodos[s].busca_nodo_anulable(s,nodos[s].getNodoIzquierdo(),nodos,cont);
                        de=nodos[s].busca_nodo_anulable(s, nodos[s].getNodoDerecho(), nodos, cont);
                        if(iz==false &&de==false){nodos[s].setNodoAnulable(false);}
                        else{nodos[s].setNodoAnulable(true);}
                    }
          }
    }
   public String busca_nodo_primera_pos(String nodo, Nodo arreglo[],int tope){
       int j=0,id;
       String resultado="";
      while(j<tope){
          if(nodo.equals(arreglo[j].getNodoId())){
           resultado=arreglo[j].getNodoPrimera_pos();
           }
          j++;
       }
       return resultado;
   }
   public void primera_pos(int s,Nodo nodos[],int cont){
      String iz="",der="",fin="";
        boolean anu=false; 
       String alfabeto="abcdefghABCDEFGH#";
    
        if(alfabeto.contains(nodos[s].getNodoNombre())){
           nodos[s].setNodoPrimera_pos(Integer.toString(nodos[s].getNodoEtiqueta()));
       }
       //if(nodos[s].getNodoTipo().equals("operador")){
       if(nodos[s].getNodoNombre().equals("*")
               ||nodos[s].getNodoNombre().equals("?")
               ||nodos[s].getNodoNombre().equals("+")){
                    iz=busca_nodo_primera_pos(nodos[s].getNodoIzquierdo(),nodos,cont);
                    nodos[s].setNodoPrimera_pos(iz);
                }
        //}//fin if operador
      //if(nodos[s].getNodoTipo().equals("bioperador")){
        if(nodos[s].getNodoNombre().equals("|")){
               der=busca_nodo_primera_pos(nodos[s].getNodoDerecho(),nodos,cont);
               iz=busca_nodo_primera_pos(nodos[s].getNodoIzquierdo(),nodos,cont);
               fin=iz+","+der;
               nodos[s].setNodoPrimera_pos(fin);
           }
        if(nodos[s].getNodoNombre().equals(".")){
               der=busca_nodo_primera_pos(nodos[s].getNodoDerecho(),nodos,cont);
               iz=busca_nodo_primera_pos(nodos[s].getNodoIzquierdo(),nodos,cont);
               fin=iz+","+der;
               anu=nodos[s].busca_nodo_anulable(s,nodos[s].getNodoIzquierdo(),nodos,cont);
        
               if(anu==true){
                    nodos[s].setNodoPrimera_pos(fin);
               }
            else if(anu==false){
                   nodos[s].setNodoPrimera_pos(iz);
               }
           }
       //}//finn if bioperadore 
        if(nodos[s].getNodoTipo().equals("raiz")){
          anu=nodos[s].busca_nodo_anulable(s,nodos[s].getNodoIzquierdo(),nodos,cont);
          der=busca_nodo_primera_pos(nodos[s].getNodoDerecho(),nodos,cont);
          iz=busca_nodo_primera_pos(nodos[s].getNodoIzquierdo(),nodos,cont);
               fin=iz+","+der;
               if(anu==true){
                    nodos[s].setNodoPrimera_pos(fin);
               }
               else if(anu==false){
                   nodos[s].setNodoPrimera_pos(iz);
               }
      }
   }
   public String busca_nodo_ultima_pos(String nodo, Nodo arreglo[],int tope){
       int j=0,id;
       String resultado="";
      while(j<tope){
          if(nodo.equals(arreglo[j].getNodoId())){
           resultado=arreglo[j].getNodoUltima_pos();
           }
          j++;
       }
       return resultado;
   }
   public void ultima_pos(int s,Nodo nodos[],int cont){
      String iz="",der="",fin="";
        boolean anu=true; 
        String alfabeto="abcdefghABCDEFGH#";
    
       if(alfabeto.contains(nodos[s].getNodoNombre())){
           nodos[s].setNodoUltima_pos(Integer.toString(nodos[s].getNodoEtiqueta()));
       }
       if(nodos[s].getNodoNombre().equals("*")
               ||nodos[s].getNodoNombre().equals("?")
               ||nodos[s].getNodoNombre().equals("+")){
                    iz=busca_nodo_ultima_pos(nodos[s].getNodoIzquierdo(),nodos,cont);
                    nodos[s].setNodoUltima_pos(iz);
                }
       
           if(nodos[s].getNodoNombre().equals("|")){
               der=busca_nodo_ultima_pos(nodos[s].getNodoDerecho(),nodos,cont);
               iz=busca_nodo_ultima_pos(nodos[s].getNodoIzquierdo(),nodos,cont);
               fin=iz+","+der;
               nodos[s].setNodoUltima_pos(fin);
           }
           
          if(nodos[s].getNodoNombre().equals(".")){
              anu=nodos[s].busca_nodo_anulable(s,nodos[s].getNodoDerecho(),nodos,cont);
               der=busca_nodo_ultima_pos(nodos[s].getNodoDerecho(),nodos,cont);
               iz=busca_nodo_ultima_pos(nodos[s].getNodoIzquierdo(),nodos,cont);
               fin=iz+","+der;
               if(anu==true){
                    nodos[s].setNodoUltima_pos(fin);
               }
               else if(anu==false){
                   nodos[s].setNodoUltima_pos(der);
               }
           }
       
      if(nodos[s].getNodoTipo().equals("raiz")){
          anu=nodos[s].busca_nodo_anulable(s,nodos[s].getNodoDerecho(),nodos,cont);
          der=busca_nodo_ultima_pos(nodos[s].getNodoDerecho(),nodos,cont);
               iz=busca_nodo_ultima_pos(nodos[s].getNodoIzquierdo(),nodos,cont);
               fin=der+","+iz;
               if(anu==true){
                    nodos[s].setNodoUltima_pos(fin);
               }
               else if(anu==false){
                   nodos[s].setNodoUltima_pos(der);
               }
      }
   }
   public int hijo_izq(int id,Nodo nodos[],int cont){
       int j=0; int izq=0;
       while(j<cont){
           if((nodos[j].getNodoId().equals(id))){
               izq=Integer.parseInt(nodos[j].getNodoPrimera_pos());
           }
           j++;
       }
       return izq;
   }
   public int hijo_der(int s,Nodo nodos[],int cont){
       int j=0;int der=0;
       while(j<cont){
           if((nodos[s].getNodoId().equals(s))){
               der=Integer.parseInt(nodos[s].getNodoDerecho());
           }
           j++;
       }
       return der;
   }
   public String derecho(int s,Nodo nodos[],int cont){
       int j=0;String der="";
       while(j<cont){
           if(nodos[s].getNodoId().equals(s)){
               der=nodos[s].getNodoUltima_pos();
           }
           j++;
       }
       
       return der;
   }
   public String nombre_hijo(String s,Nodo nodos[],int cont){
       int j=0;String nombre="";
       int x=Integer.parseInt(s);
       while(j<cont){
           if((nodos[x].getNodoId().equals(s))){
               nombre=nodos[x].getNodoNombre();
           }
           j++;
       }
       return nombre;
   }
     
   public String devuelve_primpos(String id,Nodo nodos[],int cont){
       int s=Integer.parseInt(id),i=0;String p="";
       while(i<cont){
           if(nodos[i].getNodoId().equals(id)){
               p=nodos[i].getNodoPrimera_pos();
           }
           i++;
       }
       return p;
   }
   public String devuelve_ultpos(String id,Nodo nodos[],int cont){
       int s=Integer.parseInt(id),i=0;String p="";
       while(i<cont){
           if(nodos[i].getNodoId().equals(id)){
               p=nodos[i].getNodoUltima_pos();
           }
           i++;
       }
       return p;
   }
   public String devuelve_izq(String id,Nodo nodos[],int cont){
       int s=Integer.parseInt(id),i=0;String p="";
       while(i<cont){
           if(nodos[i].getNodoId().equals(id)){
               p=nodos[i].getNodoIzquierdo();
           }
           i++;
       }
       return p;
   }
   public String devuelve_der(String id,Nodo nodos[],int cont){
       int s=Integer.parseInt(id),i=0;String p="";
       while(i<cont){
           if(nodos[i].getNodoId().equals(id)){
               p=nodos[i].getNodoDerecho();
           }
           i++;
       }
       return p;
   }
   public String devuelve_nombre(String id,Nodo nodos[],int cont){
       int s=Integer.parseInt(id),i=0;String p="";
       while(i<cont){
           if(nodos[i].getNodoId().equals(id)){
               p=nodos[i].getNodoNombre();
           }
           i++;
       }
       return p;
   }
   
   
   
   // ########################   Mis nuevas funciones  #########################
   public String devuelve_hijoDerecho(String id,Nodo nodos[],int cont){
       int s=Integer.parseInt(id),i=0;String p="";
       while(i<cont){
           if(nodos[i].getNodoId().equals(id)){
               p=nodos[i].getNodoDerecho();
           }
           i++;
       }
       return p;
   }
   
   
   public String devuelve_padre (String id, Nodo nodos[], int cont)
   {
       String gDad="";
       int s=Integer.parseInt(id),i=0;String p="";
       while(i<cont){
           if(nodos[i].getNodoId().equals(id)){
               gDad=nodos[i].getNodoPadre();
           }
           i++;
       }
       return gDad;
   }
   
   
   public String devulveUltimaPos(String id,Nodo nodos[],int cont)
   {
       String ultimaPos="";
       int s=Integer.parseInt(id),i=0;String p="";
       while(i<cont){
           if(nodos[i].getNodoId().equals(id)){
               ultimaPos=nodos[i].getNodoUltima_pos();
           }
           i++;
       }
       
       return ultimaPos;
   }
   // ##########################################################################
   
   
   public String devuelve_nexthoja(int s,Nodo nodos[],int cont){
       int i=s;String p="";
       while(i<cont){
           if(nodos[i].getNodoTipo().equals("hoja")){
               p=nodos[i].getNodoPrimera_pos();
               i=cont;
           }
           i++;
       }
       return p;
   } 
   public String devuelve_nextconcat(int s,Nodo nodos[],int cont){
       int i=s;String p="",dad="",dady="";int d=0;
       while(i<cont){
           dad=nodos[i].getNodoPadre();
           d=Integer.parseInt(nodos[i].getNodoPadre());
           dady=nodos[i].devuelve_nombre(dad,nodos,cont);
           if(dady.equals(".")){    
           p=nodos[d].getNodoPrimera_pos();
               i=cont;
           }
           
           i++;
       }
       return p;
   } 
   public String devuelve_nextconcat_d(int s,Nodo nodos[],int cont){
       int i=s;String p="",dad="",dady="",x="";int d=0;
       while(i<cont){
           dad=nodos[i].getNodoPadre();
           d=Integer.parseInt(nodos[i].getNodoPadre());
           dady=nodos[i].devuelve_nombre(dad,nodos,cont);
           if(dady.equals(".")){
               x=nodos[i].busca_nodo_padre(i,dad,nodos,cont);
           p=nodos[d].getNodoUltima_pos();
               i=cont;
           }
           
           
           i++;
       }
       return p;
   } 
   public boolean derecho (String id,Nodo nodos[],int cont){
       boolean derecho=false;
       int j=0;
       while (j<cont){
           if (id.equals(nodos[j].getNodoDerecho())){
               derecho=true;
           }
           j++;
       }
       return derecho;
   }
   public String recorrido_d(String id,Nodo nodos[],int cont)
   {
       String pos=""; int i=0;
       Boolean soyDer=false;
       
       while(i<cont)
       {
            if(id.equals(nodos[i].getNodoId()))
            {
                String dad=nodos[i].getNodoPadre();
                String dady=nodos[i].devuelve_nombre(dad, nodos, cont);
                soyDer=nodos[i].derecho(dad, nodos, cont);
           
                System.out.println(soyDer);
           
                if (soyDer)
                {
                    pos=nodos[i].recorrido_d(dad, nodos, cont);
                }
                else if(!soyDer)
                {
                    pos=nodos[i].recorrido_i(dad, nodos, cont);
                }
            }
                i++;
       }
       return pos;
   }
   public String recorrido_i(String id,Nodo nodos[],int cont){
       String pos=""; int i=0;
       
       while(i<cont)
       {
           if(id.equals(nodos[i].getNodoId()))
           {
                String dad=nodos[i].getNodoPadre();
                String dady=nodos[i].devuelve_nombre(dad, nodos, cont);

                if (dady.equals("."))
                {
                    String soyDerecho=nodos[i].devuelve_hijoDerecho(dad, nodos, cont);
                    String soyDerNom = nodos[i].devuelve_nombre(soyDerecho, nodos, cont);
                    
                    if(soyDerNom.equals("*"))
                    {
                        pos=nodos[i].devulvePrimeraPos(soyDerecho, nodos, cont)+","+nodos[i].recorrido_i(dad, nodos, cont);
                    }
                    else
                    {
                        pos=nodos[i].devulvePrimeraPos(soyDerecho, nodos, cont);
                    }
                }

                if(dady.equals("|"))
                {
                    pos=nodos[i].recorrido_i(dad, nodos, cont);
                }
                
                if(!dady.equals(".") && !dady.equals("|"))
                {
                    //   pos=nodos[i].recorrido_i(dad,nodos,cont);
                    if(dady.equals("+") || dady.equals("*"))
                    {
                        Boolean soyDer =nodos[i].derecho(dad, nodos, cont);
                        if (soyDer)
                        {
                            String tem =nodos[i].devulvePrimeraPos(nodos[i].getNodoId(),nodos,cont);
                            pos= tem +","+ nodos[i].recorrido_d(dad, nodos, cont);
                        }
                        else
                        {
                            String tem =nodos[i].devulvePrimeraPos(dad,nodos,cont);
                            pos= tem +","+ nodos[i].recorrido_i(dad, nodos, cont);
                        }
                        
                    }
                }
            }
            i++;
       }
       return pos;
   }
   
   
   public String devulvePrimeraPos(String id,Nodo nodos[],int cont)
   {
       String primeraPos="";
       int s=Integer.parseInt(id),i=0;String p="";
       while(i<cont){
           if(nodos[i].getNodoId().equals(id)){
               primeraPos=nodos[i].getNodoPrimera_pos();
           }
           i++;
       }
       
       return primeraPos;
   }
}