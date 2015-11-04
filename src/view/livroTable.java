/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.swing.table.AbstractTableModel;
import main.Livro;

/**
 *
 * @author Rafael
 */
public class livroTable extends AbstractTableModel{
 /*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */


    private String[] colunas=new String[]{"id","Nome"};

    private List<Livro> lista=new ArrayList();

    
    public livroTable(List<Livro> lista){
        this.lista=lista;
    }

    public livroTable(){
    }


    @Override
    public int getRowCount() {
        return this.lista.size();
    }

    @Override
    public int getColumnCount() {
        return this.colunas.length;
    }

    @Override
    public String getColumnName(int index) {
        return this.colunas[index];
    }

    @Override
    public boolean isCellEditable(int row, int column) {
        return false;
        //if(column==0)
            //return false;
        //return true;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Livro livro = lista.get(rowIndex);
        switch (columnIndex) {
            case 0: return livro.getId();//if column 0 (code)
            case 1: return livro.getTitulo();//if column 1 (name)
            default : return null;
        }
    }

    @Override
    public void setValueAt(Object value, int row, int col) {
        Livro livro = lista.get(row);
        switch (col) {
            case 0:
                livro.setId((int) value); //if column 0 (code)
                break;
            case 1:
                livro.setTitulo((String) value);
                break;
            default:
        }
        this.fireTableCellUpdated(row, col);
    }

    public boolean removeContato(Livro customer) {
        int linha = this.lista.indexOf(customer);
        boolean result = this.lista.remove(customer);
        this.fireTableRowsDeleted(linha,linha);//update JTable
        return result;
    }

    public void adicionaContato(Livro customer) {
        this.lista.add(customer);
        //this.fireTableDataChanged();
        this.fireTableRowsInserted(lista.size()-1,lista.size()-1);//update JTable
    }

    public void setListaContatos(List<Livro> contatos) {
        this.lista = contatos;
        this.fireTableDataChanged();
        //this.fireTableRowsInserted(0,contatos.size()-1);//update JTable
    }

    public void limpaTabela() {
        int indice = lista.size()-1;
        if(indice<0)
            indice=0;
        this.lista = new ArrayList();
        this.fireTableRowsDeleted(0,indice);//update JTable
    }

    public Livro getContato(int linha){
        return lista.get(linha);
    }
    
    public List<Livro> getLista(int[] indices){
        List<Livro> lista = new ArrayList();
        for(int i=0; i<indices.length;i++){
            lista.add(this.lista.get(indices[i]));
        }
        return lista;
    }
    
}

