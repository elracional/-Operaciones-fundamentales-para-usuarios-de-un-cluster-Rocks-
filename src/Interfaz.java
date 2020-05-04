
import java.awt.event.*;
import java.io.*;
import java.util.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
public class Interfaz extends javax.swing.JFrame {
    private static String normalizada, postfix;
    JFileChooser abrirArchivo = null;
    DefaultTableModel modelo = new DefaultTableModel();
    String cad = "\n", n="",d="",v="",e="",p="",pp="", up="", sp="", ta="",an="", id="";

    public Interfaz() {
        initComponents();
        tabla.setModel(modelo);
        modelo.addColumn("Infija");
        modelo.addColumn("Postfija");
        modelo.addColumn("Conversion");
        modelo.addColumn("Comparativa");
    }   
    private static int jerarquiaNormalizacion(String tokens) {
        int valor=0;
        if (tokens.equals("(")) valor = 4;
        else if (tokens.equals("|")) valor = 3;
        else if (tokens.equals(")")) valor = 2;
        else if (tokens.equals("*")) valor = 2;
        else if (tokens.equals("+")) valor = 2;
        else if (tokens.equals("?")) valor = 2;
        else valor = 1;
        return valor;
    }
    
    public String  Normalizar(String cadena) {
            String separar="";
            Stack < String > Expresion = new Stack < String > (); 
            Stack < String > Normalizada = new Stack < String > (); 
            cadena.trim();
            String punto = ".";
            for (int i = 0; i < cadena.length(); i++) {
                separar += cadena.charAt(i) + " ";
                }
            String[] paraNormalizar = separar.split(" ");
            for (int i = paraNormalizar.length - 1; i >= 0; i--) {
                Expresion.push(paraNormalizar[i]);
            }    
            try {
              while (!Expresion.isEmpty()) {
                switch (jerarquiaNormalizacion(Expresion.peek())){
                  case 1:       	  
                      Normalizada.push(Expresion.pop());
                      Normalizada.push(punto);
                    break;
                  case 2:
                          Normalizada.pop();
                          Normalizada.push(Expresion.pop());
                          Normalizada.push(punto);
                      break;
                  case 3:
                          Normalizada.pop();
                          Normalizada.push(Expresion.pop());
                      break;
                  case 4:
                          Normalizada.push(Expresion.pop());
                      break;
                  } 
              }
              Normalizada.pop();
              normalizada = Normalizada.toString().replaceAll("[\\]\\[,]", "");
              String sin_espacios = normalizada.trim();
              normalizada = sin_espacios;
              normalizada = normalizada.replaceAll("\\s+", ""); //Elimina espacios en blanco
            }catch(Exception e){ 
              System.out.println("Error !");   
            }
            return normalizada+".#";
  }
    public void arbolizar(String aux){
    aux= aux.replaceAll("\\s+", ""); //Elimina espacios en blanco
    System.out.println("Entrada: "+aux+"\r\n");
    String cadena=aux;
    String alfabeto="abcdefghABCDEFGH#";
    int i,j;   
    LinkedList auxiliar=new LinkedList ();
    LinkedList nodo=new LinkedList();
    LinkedList cola=new LinkedList ();
    //genero 3 listas enlazadas para
    //auxiliar es para guardar estados temporales para construir el arbol
    //en la lista nodo se guarda la cadena final del arbol no se para que pero lo hice
    String []palabra=cadena.split("");
    String var="";
    String ides="";
    int t=palabra.length;
    //int []ids_aux=new int[t];
    for(i=0;i<t;i++){
        cola.add(palabra[i]);//aqui agrego letra por letra a la lista que se llama cola
        //para despues con la cola contruie el arbol XD
    }//fin del for
   //creo un arreglo de la clase nodos con los atributos nombre derecho izquierdo
   Nodo nodos[]=new Nodo[t];
   //declaro un objeto temporal para guardar los estados mientras se construye el arbol
    int cont=0;
    i=0; 
    for(j=0;j<t;j++){
        var+=cola.getFirst();
        if(alfabeto.contains(var)){
            i++;
            nodo.addLast(cola.removeFirst());
            nodos[j]=new Nodo(var,null,null,false,i,null,"hoja",Integer.toString(j),null,null,null,null);
            auxiliar.addFirst(Integer.toString(j));
        }//fin del if alfabeto
        else if(var.equals("*")||var.equals("+")||var.equals("?")){
            nodo.addLast(cola.removeFirst());
            nodos[j]=new Nodo(var,null,auxiliar.removeFirst().toString(),false,0,null,"operador",Integer.toString(j),null,null,null,null);
            auxiliar.addFirst(Integer.toString(j));
        }
        else if(var.equals(".")||var.equals("|")){
            nodo.addLast(cola.removeFirst());
            nodos[j]=new Nodo(var,auxiliar.remove().toString(),auxiliar.remove().toString(),false,0,null,"bioperador",Integer.toString(j),null,null,null,null);
            auxiliar.addFirst(Integer.toString(j));
        }    
        var="";
        cont++;
    }
    int s;
    boolean iz=false,de=true;
    int h;
   nodos[cont-1].setNodoTipo("raiz");
   nodos[cont-1].setNodoPadre("12");
   //nodos[1].busca_nodo_anulable(1,nodos[1].getNodoDerecho(),nodos,cont);
       for(s=0;s<cont;s++){
          nodos[s].anulable(s, nodos,cont);
          nodos[s].primera_pos(s,nodos,cont);
          nodos[s].ultima_pos(s,nodos,cont);
          nodos[s].padre(s,nodos,cont);
          //nodos[s].sig_pos(s,nodos,cont); 
      }
    
       String izq="",izq2="",der="",der2="";String hizq="",hder="",dad="",dady="",table="",nder="",nizq="";
       String hid="",ult="";
       boolean derecho=false;
       int w;
       
       for(i=0;i<cont;i++){
           dad=nodos[i].getNodoPadre();
           //dady=nodos[i].devuelve_nombre(dad, nodos, cont);
           derecho=nodos[i].derecho(nodos[i].getNodoId(), nodos, cont);
           if(derecho==false)
           {
               String soyDerecho="";
               String soyDerNom="";
               try{
                   soyDerecho=nodos[i].devuelve_hijoDerecho(dad, nodos, cont);
                   soyDerNom = nodos[i].devuelve_nombre(soyDerecho, nodos, cont);
               }catch(Exception e){
                   System.out.println("Este ejemplo mo tiene abuelos"+ e);
               }
               if(dady.equals(".")){
                   String gDad= nodos[i].devuelve_padre(dad,nodos,cont);
                   String gDadN= nodos[i].devuelve_nombre(gDad, nodos, cont);
                   if(soyDerNom.equals("*")){
                        table=nodos[i].devulvePrimeraPos(soyDerecho, nodos, cont)+","+nodos[i].recorrido_d(dad, nodos, cont);
                        nodos[i].setNodoTabla(table);
                    }else{
                        table=nodos[i].devulvePrimeraPos(soyDerecho, nodos, cont);
                        nodos[i].setNodoTabla(table);
                    }if (gDadN.equals("+") || gDadN.equals("*")){
                       //System.out.println("soy un padre * ó +; con un .");
                       table=nodos[i].devulvePrimeraPos(gDad, nodos, cont)+","+ nodos[i].devulveUltimaPos(gDad, nodos, cont);
                       nodos[i].setNodoTabla(table);
                   }
               }else if (!dady.equals(".") && (dady.equals("*") || dady.equals("+"))){
                table=nodos[i].recorrido_i(nodos[i].getNodoId(), nodos, cont);
                nodos[i].setNodoTabla(table);
               }if (dady.equals("|")){
                   //System.out.println("soy un padre * ó +; con un |");
                   String gDad= nodos[i].devuelve_padre(dad,nodos,cont);
                   String gDadN= nodos[i].devuelve_nombre(gDad, nodos, cont);
                   System.out.println(gDadN);
                   if (gDadN.equals("+") || gDadN.equals("*")){
                       //System.out.println("soy un padre * ó +; con un .");
                       String tem=nodos[i].devulvePrimeraPos(gDad, nodos, cont);
                       nodos[i].setNodoTabla(table);
                   }if (!soyDerNom.equals("*")){
                       String tem=nodos[i].recorrido_i(dad, nodos, cont);
                       nodos[i].setNodoTabla(tem);
                   }
               }
           }if(derecho==true){
                if(dady.equals(".")){
                    table=nodos[i].recorrido_d(nodos[i].getNodoId(), nodos, cont);
                    nodos[i].setNodoTabla(table);
                }
                if(!dady.equals(".")){
                   table=nodos[i].recorrido_d(nodos[i].getNodoId(), nodos, cont);
                   nodos[i].setNodoTabla(table);}
           }
       }
        //se imprime el arbol
       for(i=0;i<cont;i++){
        if(nodos[i].getNodoTipo().equals("hoja")){
            
            id = "Id: "+nodos[i].getNodoId();
            n = "Nombre: "+nodos[i].getNodoNombre();
            e = "Etiqueta: "+nodos[i].getNodoEtiqueta();
            d = "Derecho: "+nodos[i].getNodoDerecho();
            p = "Padre: "+nodos[i].getNodoPadre();
            pp = "Primera_Pos: "+nodos[i].getNodoPrimera_pos();
            up = "Ultima_Pos: "+nodos[i].getNodoUltima_pos();
            sp = "Sig_Pos: "+nodos[i].getNodoSig_pos();
            ta = "tabla: "+nodos[i].getNodoTabla();
            an = "Anulable: "+nodos[i].getNodoAnulable()+"\r\n";
            
            cad = cad + id+"\n"+n+"\n"+e+"\n"+"Tipo: "+nodos[i].getNodoTipo()+"\n"+d+"\n"+p+"\n"+"Tipo: "+nodos[i].getNodoTipo()+"\n"+pp+"\n"+up+"\n"+sp+"\n"+ta+"\n"+an+"\n";

        }
    }
            resultado.setText(cad);
    }
    public void  getArchivo(String ruta){
        FileReader fr = null;
        BufferedReader br = null;
        try{
            //ruta del archivo solo funciona con .txt
            fr = new FileReader( ruta );
            br = new BufferedReader( fr );
            String linea;
            //Se obtiene el contenido del archivo linea por linea
            while( ( linea = br.readLine() ) != null ){ 
                int x = linea.indexOf(',');
                String a = linea.substring(0, x);
                String b = linea.substring(x+1, linea.length());
                String c = transformar(Normalizar(a));
                String d ="";
                if(b.equals(c)) {d = "Correcto";
                }else { d = "Incorrecto";}
                String n = ""+a+" "+b+" "+c+" "+d+"";
                Object []o = new Object[4];
                o[0] = a; o[1] = b; o[2] = c; o[3] = d;
                modelo.addRow(o);
            }
        }catch( Exception e ){  }
        //finally se utiliza por si hay un error
        finally
        {
            try{
                br.close();
            }catch( Exception ex ){}
        }
    }
    public void abrir(){
     if( abrirArchivo == null ) abrirArchivo = new JFileChooser();
            //Con esto solamente se  abre el archivo
            abrirArchivo.setFileSelectionMode( JFileChooser.FILES_ONLY );
 
            int seleccion = abrirArchivo.showOpenDialog(this);
 
            if( seleccion == JFileChooser.APPROVE_OPTION )
            {
                File f = abrirArchivo.getSelectedFile();
                try
                {
                    String nombre = f.getName();
                    String direccion = f.getAbsolutePath();
                    getArchivo(direccion);
                    JOptionPane.showMessageDialog(null, "Operacion correcta!");
                }catch( Exception exp){}
            }
    }
    public String transformar(String cadena) {
	
            String dividir = "";
            Stack < String > Infija = new Stack < String > (); 
            Stack < String > Pila = new Stack < String > (); 
            Stack < String > Postfija = new Stack < String > (); 

            cadena.trim();
            cadena = "(" + cadena + ")";
            String simbols = ".|()";

            for (int i = 0; i < cadena.length(); i++) {
                if (simbols.contains("" + cadena.charAt(i))){
                        dividir += " " + cadena.charAt(i) + " ";
                        }else{
                                dividir += cadena.charAt(i);
                        }
                }

            String[] paraInfija = dividir.split(" ");

            for (int i = paraInfija.length - 1; i >= 0; i--) {
                Infija.push(paraInfija[i]);
            }

            try {

              while (!Infija.isEmpty()) {
                switch (jerarquia(Infija.peek())){
                  case 1:
                          Pila.push(Infija.pop());
                    break;
                  case 2:
                      while(!Pila.peek().equals("(")) {
                        Postfija.push(Pila.pop());
                      }
                      Pila.pop();
                      Infija.pop();
                      break; 
                  case 3:
                  case 4:
                    while(jerarquia(Pila.peek()) >= jerarquia(Infija.peek())) {
                        Postfija.push(Pila.pop());
                    }
                    Pila.push(Infija.pop());
                    break; 
                  default:
                          Postfija.push(Infija.pop()); 
                } 
              }

              postfix = Postfija.toString().replaceAll("[\\]\\[,]", "");
              postfix.replaceAll(" ", "");
            }catch(Exception e){ 
              System.out.println("Error!");

            }
            postfix= postfix.replaceAll("\\s+", ""); //Elimina espacios en blanco
            return postfix;
}       
  //Jerarquia de los operadores
  private static int jerarquia(String tokens) {
    int valor=0;
    if (tokens.equals(".") || tokens.equals("-")) valor = 4;
    if (tokens.equals("|") || tokens.equals("-")) valor = 3;
    if (tokens.equals(")")) valor = 2;
    if (tokens.equals("(")) valor = 1;
    return valor;
  }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        normalizado = new javax.swing.JTextField();
        salida = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabla = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        convertir = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        limpiar = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        entrada1 = new javax.swing.JTextField();
        jButton3 = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        resultado = new javax.swing.JTextArea();
        limpiar1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Sandra Esmeralda López López");
        setBackground(new java.awt.Color(102, 204, 255));
        setMinimumSize(new java.awt.Dimension(800, 350));
        setPreferredSize(new java.awt.Dimension(800, 450));
        setResizable(false);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        normalizado.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        normalizado.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        normalizado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                normalizadoActionPerformed(evt);
            }
        });
        normalizado.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                normalizadoKeyTyped(evt);
            }
        });
        getContentPane().add(normalizado, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 120, 170, -1));

        salida.setFont(new java.awt.Font("Arial Black", 1, 12)); // NOI18N
        salida.setForeground(new java.awt.Color(204, 0, 0));
        salida.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        salida.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                salidaActionPerformed(evt);
            }
        });
        getContentPane().add(salida, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 160, 170, 20));

        tabla.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Infija", "Postfija", "Conversion", "Comparativa"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(tabla);

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 80, 480, 110));

        jLabel1.setFont(new java.awt.Font("Arial Black", 0, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 0, 102));
        jLabel1.setText("Proyecto compiladores");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 20, 240, 20));

        jButton1.setFont(new java.awt.Font("Arial Rounded MT Bold", 1, 12)); // NOI18N
        jButton1.setText("Cargar");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 280, 130, -1));

        convertir.setFont(new java.awt.Font("Arial Rounded MT Bold", 1, 12)); // NOI18N
        convertir.setText("Covertir");
        convertir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                convertirActionPerformed(evt);
            }
        });
        getContentPane().add(convertir, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 240, 130, -1));

        jLabel2.setFont(new java.awt.Font("Aharoni", 0, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(0, 0, 204));
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel2.setText("Normalizada");
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 120, 90, 20));

        jLabel3.setFont(new java.awt.Font("Aharoni", 0, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(0, 0, 204));
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel3.setText("Postfija:");
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 160, 60, 20));

        limpiar.setFont(new java.awt.Font("Arial Rounded MT Bold", 1, 12)); // NOI18N
        limpiar.setText("Limpiar");
        limpiar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                limpiarActionPerformed(evt);
            }
        });
        getContentPane().add(limpiar, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 330, 120, -1));

        jLabel4.setFont(new java.awt.Font("Aharoni", 0, 14)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(0, 0, 204));
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel4.setText("Infija:");
        getContentPane().add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 80, 40, 20));

        entrada1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        entrada1.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        entrada1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                entrada1KeyTyped(evt);
            }
        });
        getContentPane().add(entrada1, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 80, 170, -1));

        jButton3.setFont(new java.awt.Font("Arial Rounded MT Bold", 1, 12)); // NOI18N
        jButton3.setText("Normalizar ");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton3, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 240, 130, -1));

        resultado.setBackground(new java.awt.Color(255, 204, 255));
        resultado.setColumns(20);
        resultado.setFont(new java.awt.Font("Mongolian Baiti", 1, 14)); // NOI18N
        resultado.setRows(5);
        jScrollPane2.setViewportView(resultado);

        getContentPane().add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 210, 480, 140));

        limpiar1.setFont(new java.awt.Font("Arial Rounded MT Bold", 1, 12)); // NOI18N
        limpiar1.setText("Arbol");
        limpiar1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                limpiar1ActionPerformed(evt);
            }
        });
        getContentPane().add(limpiar1, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 280, 130, -1));

        getAccessibleContext().setAccessibleDescription("Sandra Lopez Lopez");

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void salidaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_salidaActionPerformed

    }//GEN-LAST:event_salidaActionPerformed

    private void normalizadoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_normalizadoKeyTyped
        char letra = evt.getKeyChar();
        if((letra<'a' || letra>'c') 
                && letra!='*' && letra!='|' && letra!='(' 
                && letra!=')' && letra!='+' && letra!='?' 
                && letra != KeyEvent.VK_BACK_SPACE)
        {evt.consume();}
    }//GEN-LAST:event_normalizadoKeyTyped

    private void convertirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_convertirActionPerformed
        salida.setText(transformar(normalizado.getText()));
        if(!salida.equals(""))
            JOptionPane.showMessageDialog(null, "Expresión algebraica correcta!");
    }//GEN-LAST:event_convertirActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        abrir();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void limpiarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_limpiarActionPerformed
        normalizado.setText("");
        salida.setText("");
        normalizado.setText("");
        resultado.setText("");
        entrada1.setText("");
        modelo.setRowCount(0);
        tabla.setModel(modelo);
    }//GEN-LAST:event_limpiarActionPerformed

    private void entrada1KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_entrada1KeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_entrada1KeyTyped

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        Normalizar(entrada1.getText());
        normalizado.setText(Normalizar(entrada1.getText()));
    }//GEN-LAST:event_jButton3ActionPerformed

    private void normalizadoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_normalizadoActionPerformed
        
    }//GEN-LAST:event_normalizadoActionPerformed

    private void limpiar1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_limpiar1ActionPerformed
        arbolizar(salida.getText());  
    }//GEN-LAST:event_limpiar1ActionPerformed

    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Interfaz.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Interfaz.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Interfaz.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Interfaz.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new Interfaz().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton convertir;
    private javax.swing.JTextField entrada1;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JButton limpiar;
    private javax.swing.JButton limpiar1;
    private javax.swing.JTextField normalizado;
    private javax.swing.JTextArea resultado;
    private javax.swing.JTextField salida;
    private javax.swing.JTable tabla;
    // End of variables declaration//GEN-END:variables
}
