public class ListasDobles {

    private class Nodo { 
    int elem; 
    Nodo ant; 
    Nodo prox; 
 
    Nodo(int elem) { 
        this.elem = elem; 
        this.ant = null; 
        this.prox = null; 
    } 

    Nodo(Nodo ant, int elem, Nodo prox) { 
        this.ant = ant; 
        this.elem = elem; 
        this.prox = prox; 
    } 
} 
 
    private Nodo prim; 
    private Nodo ult; 
    private int cant; 
 
    public ListasDobles() { 
        prim = null; 
        ult = null; 
        cant = 0; 
    } 
 
    public boolean vacia() { return cant == 0; } 

    @Override 
    public String toString() { 
        String s = "["; 
        for (Nodo p = prim; p != null; p = p.prox) { 
            s += p.elem + (p.prox != null ? ", " : ""); 
        }    
        return s + "]"; 
    } 
    // metodos(Extremos y Medio) 
 
    public void insertarPrim(int x) { 
        Nodo nuevo = new Nodo(x); 
        if (vacia()) { 
            prim = ult = nuevo; 
        } else { 
            nuevo.prox = prim; 
            prim.ant = nuevo; 
            prim = nuevo; 
        } 
        cant++; 
    } 

    public void insertarUlt(int x) { 
        Nodo nuevo = new Nodo(x); 
        if (vacia()) { 
            prim = ult = nuevo; 
        } else { 
            ult.prox = nuevo; 
            nuevo.ant = ult; 
            ult = nuevo; 
        } 
        cant++; 
    } 
    public void insertarIesimo(int x, int i) { 
        if (i <= 0) { 
            insertarPrim(x); 
            return; 
        } 
        if (i >= cant) { 
            insertarUlt(x); 
            return; 
        }  
        Nodo p = prim; 
        for (int k = 0; k < i; k++) p = p.prox; 
        Nodo nuevo = new Nodo(p.ant, x, p); 
        p.ant.prox = nuevo; 
        p.ant = nuevo; 
        cant++; 
    }  
    // 5 MÉTODOS DE CONSULTA  
    // Consulta 1: seEncuentra(x) 
    public boolean seEncuentra(int x) { 
        for (Nodo p = prim; p != null; p = p.prox) { 
            if (p.elem == x) return true; 
        } 
        return false; 
    } 

    public boolean seEncuentraRec(int x) { 
        return seEncuentraRec(prim, x); 
    } 

    private boolean seEncuentraRec(Nodo p, int x) { 
        if (p == null) return false; 
        if (p.elem == x) return true; 
        return seEncuentraRec(p.prox, x); 
    }    

    // Consulta 2: frecuencia(x) 
    public int frecuencia(int x) { 
        int c = 0; 
        for (Nodo p = prim; p != null; p = p.prox) { 
            if (p.elem == x) c++; 
        } 
        return c; 
    } 

    public int frecuenciaRec(int x) { 
        return frecuenciaRec(prim, x); 
    } 

    private int frecuenciaRec(Nodo p, int x) { 
        if (p == null) return 0; 
        return (p.elem == x ? 1 : 0) + frecuenciaRec(p.prox, x); 
    } 

    // Consulta 3: existePar() 
    public boolean existePar() { 
        for (Nodo p = prim; p != null; p = p.prox) { 
            if (p.elem % 2 == 0) return true; 
        } 
        return false; 
    } 

    public boolean existeParRec() { 
        return existeParRec(prim); 
    } 

    private boolean existeParRec(Nodo p) { 
        if (p == null) return false; 
        if (p.elem % 2 == 0) return true; 
        return existeParRec(p.prox); 
    } 
    // Consulta 4: contarPares() 
    public int contarPares() { 
        int c = 0; 
        for (Nodo p = prim; p != null; p = p.prox) { 
            if (p.elem % 2 == 0) c++; 
        } 
        return c; 
    } 

    public int contarParesRec() { 
        return contarParesRec(prim); 
    } 

    private int contarParesRec(Nodo p) { 
        if (p == null) return 0; 
        return (p.elem % 2 == 0 ? 1 : 0) + contarParesRec(p.prox); 
    } 

    // Consulta 5: sumarTodo() 
    public int sumarTodo() { 
        int suma = 0; 
        for (Nodo p = prim; p != null; p = p.prox) { 
            suma += p.elem; 
        } 
        return suma; 
    } 

    public int sumarTodoRec() { 
        return sumarTodoRec(prim); 
    } 

    private int sumarTodoRec(Nodo p) { 
        if (p == null) return 0; 
        return p.elem + sumarTodoRec(p.prox); 
    } 

    // MÉTODO AUXILIAR PARA ELIMINAR 
    private void eliminarNodo(Nodo p) { 
        if (p == null) return; 
 
        if (p.ant != null) 
            p.ant.prox = p.prox; 
        else 
            prim = p.prox; 
 
        if (p.prox != null) 
            p.prox.ant = p.ant; 
        else 
            ult = p.ant;  
        cant--; 
    } 
    public void eliminarIesimo (int i)
    {
        Nodo p = prim;
        for (int k = 0; k < i && p != null; k++)
        {
            p = p.prox;
        }
        if (p != null)
        {
            eliminarNodo (p);
        }
    }
    // 5 MÉTODOS DE ELIMINAR 
 
    // Eliminar 1: eliminarPrim() (Extremo) 
    public void eliminarPrim() { 
        eliminarNodo(prim); 
    } 

    public void eliminarPrimRec() { 
        if (prim != null) eliminarNodo(prim); 
    } 

    // Eliminar 2: eliminarUlt() (Extremo) 
    public void eliminarUlt() { 
        eliminarNodo(ult); 
    } 

    public void eliminarUltRec() { 
        eliminarUltRec(prim); 
    } 

    private void eliminarUltRec(Nodo p) { 
        if (p == null) return; 
        if (p.prox == null) { 
            eliminarNodo(p); 
            return; 
        } 
        eliminarUltRec(p.prox); 
    } 

    // Eliminar 3: eliminarTodo(x) (Medio/Cualquiera) 
    public void eliminarTodo(int x) { 
        for (Nodo p = prim, sig; p != null; p = sig) { 
            sig = p.prox; 
            if (p.elem == x) eliminarNodo(p); 
        } 
    } 

    public void eliminarTodoRec(int x) { 
        eliminarTodoRec(prim, x); 
    } 

    private void eliminarTodoRec(Nodo p, int x) { 
        if (p == null) return; 
        Nodo sig = p.prox; 
        if (p.elem == x) eliminarNodo(p); 
        eliminarTodoRec(sig, x); 
    } 

    // Eliminar 4: eliminarPares() (Medio/Cualquiera) 
    public void eliminarPares() { 
        for (Nodo p = prim, sig; p != null; p = sig) { 
            sig = p.prox; 
            if (p.elem % 2 == 0) eliminarNodo(p); 
        } 
    } 

    public void eliminarParesRec() { 
        eliminarParesRec(prim); 
    } 

    private void eliminarParesRec(Nodo p) { 
        if (p == null) return; 
        Nodo sig = p.prox; 
        if (p.elem % 2 == 0) eliminarNodo(p); 
        eliminarParesRec(sig); 
    } 

    // Eliminar 5: eliminarMayoresA(x) (Medio/Cualquiera) 
    public void eliminarMayoresA(int x) { 
        for (Nodo p = prim, sig; p != null; p = sig) { 
            sig = p.prox; 
            if (p.elem > x) eliminarNodo(p); 
        } 
    }    

    public void eliminarMayoresARec(int x) { 
        eliminarMayoresARec(prim, x); 
    } 

    private void eliminarMayoresARec(Nodo p, int x) { 
        if (p == null) return; 
        Nodo sig = p.prox; 
        if (p.elem > x) eliminarNodo(p); 
        eliminarMayoresARec(sig, x); 
    }
}
//////////////////////////
public class Main {

    public static void main(String[] args) {

        ListasDobles L1 = new ListasDobles();

        L1.insertarUlt(4);
        L1.insertarUlt(8);
        L1.insertarUlt(10);
        L1.insertarUlt(8);
        L1.insertarUlt(6);
        L1.insertarUlt(10);
        L1.insertarUlt(6);

        System.out.println("Lista original: " + L1);
        
        System.out.println(L1.seEncuentra(10));
        System.out.println(L1.frecuencia(8));
        System.out.println(L1.existePar());
        System.out.println(L1.contarPares());
        System.out.println(L1.sumarTodo());
        
        //eliminar 
        L1.eliminarIesimo(0);
        System.out.println("Eliminar 0"+ L1);
        L1.eliminarPrim();
        System.out.println("Se elimino 8"+L1);
        L1.eliminarUlt();
        System.out.println("Se elimino 6"+L1);
        L1.eliminarTodo(10);
        System.out.println("Elimina todos los 10"+L1);
        L1.eliminarMayoresA(6);
        System.out.println("Se elimina numeros mayores a 6"+L1);
    }    
}




