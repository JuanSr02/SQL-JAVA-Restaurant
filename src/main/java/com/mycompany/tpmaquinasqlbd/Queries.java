
package com.mycompany.tpmaquinasqlbd;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;

public abstract class Queries {
     // Valores para la conexión a la base de datos (su nombre, URL, Usuario y Contraseña)
    private static final String DB_NAME = "El resto del Freaky";
    private static final String DB_URL = "jdbc:postgresql://localhost:5432/" + DB_NAME;
    private static final String DB_USER = "postgres";
    private static final String DB_PWD = "admin";
    
    // Objetos utilizados para interactuar con la base de datos
    // (conexión, realizar consultas con y sin parámetros, y recibir los resultados)
    private static Connection conn = null;
    private static Statement query = null;
    private static PreparedStatement p_query = null;
    private static ResultSet result = null;
    
public static void InitQueries() throws SQLException{
    try{
    conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PWD);
    }catch (Exception e){
        JOptionPane.showMessageDialog(null,"Hubo un error en la conexion con la base de datos", "ERROR", JOptionPane.ERROR_MESSAGE);
    }
        query = conn.createStatement();
        query.execute("CREATE TABLE IF NOT EXISTS Mozos("
                + "Mo_Cod INTEGER NOT NULL, "
                + "Mo_NombreApellido TEXT NOT NULL, "
                + "Mo_Domicilio TEXT NOT NULL, "
                + "Mo_DNI INTEGER NOT NULL,"
                + "PRIMARY KEY (Mo_Cod),"
                + "UNIQUE (Mo_DNI))");
        query.execute("CREATE TABLE IF NOT EXISTS Platos("
                + "P_Cod INTEGER NOT NULL, "
                + "P_Nombre TEXT NOT NULL, "
                + "P_Descripcion TEXT NOT NULL, "
                + "P_Tipo TEXT NOT NULL, "
                + "P_PrecioCosto INTEGER NOT NULL,"
                + "P_PrecioVenta INTEGER NOT NULL,"
                + "P_PrecioPromocion INTEGER NOT NULL,"
                + "PRIMARY KEY (P_Cod))");
        query.execute("CREATE TABLE IF NOT EXISTS Mesas("
                + "Me_Cod INTEGER NOT NULL, "
                + "Me_Sector TEXT NOT NULL, "
                + "Mo_Cod_Atiende INTEGER NOT NULL, "
                + "PRIMARY KEY (Me_Cod),"
                + "FOREIGN KEY (Mo_Cod_Atiende) REFERENCES Mozos(Mo_Cod))");
        query.execute("CREATE TABLE IF NOT EXISTS Consumos("
                + "C_Cod INTEGER NOT NULL, "
                + "C_Fecha DATE NOT NULL, "
                + "C_Hora TIME NOT NULL, "
                + "Me_Cod_Realiza INTEGER NOT NULL,"
                + "PRIMARY KEY (C_Cod),"
                + "FOREIGN KEY (Me_Cod_Realiza) REFERENCES Mesas(Me_Cod))");
        query.execute("CREATE TABLE IF NOT EXISTS Se_Consume("
                + "C_Cod INTEGER NOT NULL, "
                + "P_Cod INTEGER NOT NULL, "
                + "PRIMARY KEY (C_Cod,P_Cod),"
                + "FOREIGN KEY (C_Cod) REFERENCES Consumos(C_Cod),"
                + "FOREIGN KEY (P_Cod) REFERENCES Platos(P_Cod))");
        try{
            query.execute("INSERT INTO mozos VALUES (2000, 'Frodo Baggins', 'Laprida 112', 18921118)");
            query.execute("INSERT INTO mozos VALUES (2005, 'Bilbo Baggins', 'Chacabuco 1980', 17498107)");
            query.execute("INSERT INTO mozos VALUES (2110, 'Sam Gamgee', 'Maipu 345', 25152018)");
            query.execute("INSERT INTO mozos VALUES (2115, 'Gandalf el Gris', 'Av Sucre 1324', 17247118)");
            query.execute("INSERT INTO mozos VALUES (2120, 'Saruman el Blanco', 'Av Illia 247', 16901678)");
            query.execute("INSERT INTO mozos VALUES (2125, 'Merry BrandybucK', 'Av Sucre 2724', 29276295)");
            query.execute("INSERT INTO mozos VALUES (2130, 'Pippin Took', 'Don Bosco 1514', 29645938)");
            query.execute("INSERT INTO mozos VALUES (2135, 'Aragorn, hijo de Arathorn', 'Balcarce 890', 29535884)");
            query.execute("INSERT INTO mozos VALUES (2140, 'Legolas, hijo de Thranduil', 'Mitre 1674', 17528160)");
            query.execute("INSERT INTO mozos VALUES (2145, 'Gimli, hijo de Glóin', 'Junin 1120', 16592570)");
            query.execute("INSERT INTO mozos VALUES (2150, 'Sméagol', 'Av Sucre 1687', 29498143)");
            query.execute("INSERT INTO mozos VALUES (2155, 'Tyrael', 'Maipu 254', 28095858)");
            query.execute("INSERT INTO mozos VALUES (2160, 'Solid Snake', 'Mitre 324', 16823905)");
            query.execute("INSERT INTO mozos VALUES (2165, 'Thomas A. Anderson', 'Ayacucho 490', 26476718)");
            query.execute("INSERT INTO mozos VALUES (2170, 'Walter White', 'Belgrano 970', 25509663)");
            query.execute("INSERT INTO mozos VALUES (2175, 'Jesse Pinkman', 'Pringles 352', 28382362)");
            query.execute("INSERT INTO mozos VALUES (2180, 'Himura Kenshin', 'Pedernera 1324', 22371137)");
            query.execute("INSERT INTO mozos VALUES (2185, 'Sanosuke Sagara', 'Ej de los Andes 1714', 25108623)");
            query.execute("INSERT INTO mozos VALUES (2190, 'Saito Hajime', 'Rivadavia 567', 19636776)");
            query.execute("INSERT INTO mozos VALUES (2195, 'Robert Garcia', 'Quintana 300', 27677382)");
            query.execute("INSERT INTO mozos VALUES (2200, 'Deckard Cain', 'Belgrano 1324', 16454121)");
            query.execute("INSERT INTO mozos VALUES (2205, 'Alan Turing', 'Ayacucho 2389', 19524908)");
            query.execute("INSERT INTO platos VALUES (80, 'Lasaña', 'Tan rica como la lasaña de la abuela', 'Plato Principal', 280, 405, 360)");
            query.execute("INSERT INTO platos VALUES (90, 'Milanesas', 'Con carne de ternera de primera', 'Plato Principal', 315, 540, 345)");
            query.execute("INSERT INTO platos VALUES (100, 'Tiramisú', 'Hecho con buen queso mascarpone', 'Postre', 150, 240, 210)");
            query.execute("INSERT INTO platos VALUES (110, 'Canelones', 'Los canelones de toda la vida! con salsa blanca', 'Plato Principal', 330, 420, 390)");
            query.execute("INSERT INTO platos VALUES (120, 'Kare Raisu', 'Arroz con curry, verduras y carne', 'Plato Principal', 180, 270, 255)");
            query.execute("INSERT INTO platos VALUES (130, 'Lembas', 'Pan de Elfo', 'Plato Principal', 90, 900, 750)");
            query.execute("INSERT INTO platos VALUES (140, 'Ramen', 'Sopa japonesa, con los mejores Narutomakis', 'Plato Principal', 180, 360, 300)");
            query.execute("INSERT INTO platos VALUES (150, 'Manjuu', 'Bollos al vapor rellenas con anko (dulce)', 'Postre', 90, 150, 120)");
            query.execute("INSERT INTO platos VALUES (160, 'Semillas Senzu', 'Semillas del Ermitaño, ideal para recargar pilas', 'Plato Principal', 30, 630, 570)");
            query.execute("INSERT INTO platos VALUES (170, 'Chipá', 'Bollitos hechas con harina de mandioca y queso', 'Entrada', 15, 60, 45)");
            query.execute("INSERT INTO platos VALUES (180, 'Okonomiyaki', 'Tortilla japonesa, hecha con repollo y fideos soba', 'Plato Principal', 270, 330, 315)");
            query.execute("INSERT INTO platos VALUES (190, 'Ravioles', 'Con salsa boloñesa (receta italiana)', 'Plato Principal', 255, 480, 420)");
            query.execute("INSERT INTO mesas VALUES (1, 'niños', 2000)");
            query.execute("INSERT INTO mesas VALUES (2, 'niños', 2005)");
            query.execute("INSERT INTO mesas VALUES (3, 'niños', 2110)");
            query.execute("INSERT INTO mesas VALUES (4, 'karaoke', 2135)");
            query.execute("INSERT INTO mesas VALUES (5, 'karaoke', 2170)");
            query.execute("INSERT INTO mesas VALUES (6, 'karaoke', 2115)");
            query.execute("INSERT INTO mesas VALUES (7, 'fumadores', 2115)");
            query.execute("INSERT INTO mesas VALUES (8, 'fumadores', 2205)");
            query.execute("INSERT INTO mesas VALUES (9, 'fumadores', 2205)");
            query.execute("INSERT INTO mesas VALUES (10, 'fumadores', 2150)");
            query.execute("INSERT INTO mesas VALUES (11, 'no fumadores', 2115)");
            query.execute("INSERT INTO mesas VALUES (12, 'no fumadores', 2155)");
            query.execute("INSERT INTO mesas VALUES (13, 'no fumadores', 2135)");
            query.execute("INSERT INTO mesas VALUES (14, 'no fumadores', 2140)");
            query.execute("INSERT INTO mesas VALUES (15, 'no fumadores', 2135)");
            query.execute("INSERT INTO mesas VALUES (16, 'no fumadores', 2115)");
            query.execute("INSERT INTO consumos VALUES (1, '2021/04/22', '21:00', 15)");
            query.execute("INSERT INTO consumos VALUES (2, '2021/04/22', '20:30', 13)");
            query.execute("INSERT INTO consumos VALUES (3, '2021/04/23', '22:00', 11)");
            query.execute("INSERT INTO consumos VALUES (4, '2021/04/23', '21:00', 9)");
            query.execute("INSERT INTO consumos VALUES (5, '2021/04/23', '22:15', 7)");
            query.execute("INSERT INTO consumos VALUES (6, '2021/04/24', '23:00', 5)");
            query.execute("INSERT INTO consumos VALUES (7, '2021/04/24', '22:30', 3)");
            query.execute("INSERT INTO consumos VALUES (8, '2021/04/24', '21:00', 1)");
            query.execute("INSERT INTO consumos VALUES (9, '2021/04/24', '23:30', 2)");
            query.execute("INSERT INTO consumos VALUES (10, '2021/04/26', '22:00', 4)");
            query.execute("INSERT INTO consumos VALUES (11, '2021/04/26', '20:30', 6)");
            query.execute("INSERT INTO consumos VALUES (12, '2021/04/26', '22:15', 8)");
            query.execute("INSERT INTO consumos VALUES (13, '2021/04/26', '23:00', 10)");
            query.execute("INSERT INTO consumos VALUES (14, '2021/04/26', '20:30', 12)");
            query.execute("INSERT INTO consumos VALUES (15, '2021/04/26', '23:00', 14)");
            query.execute("INSERT INTO consumos VALUES (16, '2021/04/26', '23:30', 16)");
            query.execute("INSERT INTO consumos VALUES (17, '2021/04/26', '23:30', 13)");
            query.execute("INSERT INTO consumos VALUES (18, '2021/04/28', '21:00', 13)");
            query.execute("INSERT INTO consumos VALUES (19, '2021/04/28', '23:00', 6)");
            query.execute("INSERT INTO consumos VALUES (20, '2021/04/28', '22:15', 8)");
            query.execute("INSERT INTO consumos VALUES (21, '2021/05/01', '20:30', 8)");
            query.execute("INSERT INTO consumos VALUES (22, '2021/05/01', '22:30', 3)");
            query.execute("INSERT INTO consumos VALUES (23, '2021/05/01', '21:00', 3)");
            query.execute("INSERT INTO consumos VALUES (24, '2021/05/01', '22:00', 12)");
            query.execute("INSERT INTO se_consume VALUES (1, 100)");
            query.execute("INSERT INTO se_consume VALUES (2, 90)");
            query.execute("INSERT INTO se_consume VALUES (3, 120)");
            query.execute("INSERT INTO se_consume VALUES (4, 160)");
            query.execute("INSERT INTO se_consume VALUES (5, 90)");
            query.execute("INSERT INTO se_consume VALUES (6, 110)");
            query.execute("INSERT INTO se_consume VALUES (7, 80)");
            query.execute("INSERT INTO se_consume VALUES (8, 130)");
            query.execute("INSERT INTO se_consume VALUES (9, 80)");
            query.execute("INSERT INTO se_consume VALUES (10, 170)");
            query.execute("INSERT INTO se_consume VALUES (11, 140)");
            query.execute("INSERT INTO se_consume VALUES (12, 160)");
            query.execute("INSERT INTO se_consume VALUES (13, 130)");
            query.execute("INSERT INTO se_consume VALUES (13, 150)");
            query.execute("INSERT INTO se_consume VALUES (14, 130)");
            query.execute("INSERT INTO se_consume VALUES (15, 160)");
            query.execute("INSERT INTO se_consume VALUES (16, 130)");
            query.execute("INSERT INTO se_consume VALUES (16, 100)");
            query.execute("INSERT INTO se_consume VALUES (17, 140)");
            query.execute("INSERT INTO se_consume VALUES (18, 100)");
            query.execute("INSERT INTO se_consume VALUES (19, 190)");
            query.execute("INSERT INTO se_consume VALUES (20, 90)");
            query.execute("INSERT INTO se_consume VALUES (21, 180)");
            query.execute("INSERT INTO se_consume VALUES (22, 80)");
            query.execute("INSERT INTO se_consume VALUES (23, 180)");
            query.execute("INSERT INTO se_consume VALUES (24, 80)");
            query.execute("INSERT INTO Mozos VALUES(2010,'Krusty','Aristobulo del Valle 3432',25544555)");
            query.execute("UPDATE Platos SET P_PrecioVenta=690 WHERE P_Nombre='Semillas Senzu'");
            query.execute("DELETE FROM Mozos WHERE Mo_NombreApellido='Jesse Pinkman'");
            JOptionPane.showMessageDialog(null,"Se han realizado las operaciones correctamente", "Primera Inicializacion", JOptionPane.INFORMATION_MESSAGE);
        } catch (SQLException e){}
}
    
// ITEM 1
public static ResultSet MostrarMozos() throws SQLException{
      conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PWD);
      query = conn.createStatement();
      query.execute("SELECT * FROM MOZOS");
      result=query.getResultSet();
      return result;
  }
    
// ITEM 1    
public static ResultSet MostrarPlatos() throws SQLException{
      conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PWD);
      query = conn.createStatement();
      query.execute("SELECT * FROM PLATOS");
      result=query.getResultSet();
      return result;
  }
// AUXILIAR         
public static ResultSet MostrarMesas() throws SQLException{
      conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PWD);
      query = conn.createStatement();
      query.execute("SELECT * FROM Mesas");
      result=query.getResultSet();
      return result;
  }
// AUXILIAR             
public static ResultSet MostrarConsumos() throws SQLException{
      conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PWD);
      query = conn.createStatement();
      query.execute("SELECT * FROM Consumos");
      result=query.getResultSet();
      return result;
  }
// ITEM 2        
public static int InsertarPlato(int Cod,String Nombre,String Descripcion,String Tipo,int PrecioC,int PrecioV,int PrecioP) throws SQLException{
            conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PWD);
            p_query = conn.prepareStatement("INSERT INTO Platos VALUES(?,?,?,?,?,?,?)");
            // luego asignamos los valores reales a esos parámetros, dando primero su posición y luego su valor
            // del siguiente modo: p_query.setTIPO(POSICIÓN, VALOR)
            p_query.setInt(1,Cod);
            p_query.setString(2, Nombre);
            p_query.setString(3, Descripcion);
            p_query.setString(4, Tipo);
            p_query.setInt(5,PrecioC);
            p_query.setInt(6,PrecioV);
            p_query.setInt(7,PrecioP);
            // ejecutamos la consulta con los valores asignados
            return p_query.executeUpdate();
    }
// ITEM 3    
public static int EliminarMozo(int cod) throws SQLException{
        conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PWD);
        p_query = conn.prepareStatement("DELETE FROM Mozos where Mo_Cod=?");
        p_query.setInt(1,cod);
        return p_query.executeUpdate();
    }
    
// ITEM 4
public static ResultSet MesasXMozo(int cod) throws SQLException{
        conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PWD);
        query =conn.createStatement();
        query.execute("SELECT * FROM Mesas WHERE mo_cod_Atiende="+cod);
        ResultSet rs=query.getResultSet();
        return rs;
    }
// ITEM 5
public static ResultSet PlatosXMesa(int cod) throws SQLException{
        conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PWD);
        query =conn.createStatement();
        query.execute("SELECT Platos.P_Cod,Platos.P_nombre,Platos.P_Descripcion FROM Mesas,Consumos,Se_Consume,Platos "
                + "WHERE Me_Cod=Me_Cod_Realiza AND Se_consume.P_cod=Platos.P_Cod AND Consumos.C_Cod=Se_Consume.C_Cod AND me_cod="+cod);
        ResultSet rs=query.getResultSet();
        return rs;
    }
// ITEM 6
public static ResultSet PlatosEntreFechas(String x,String y) throws SQLException{
        conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PWD);
        query =conn.createStatement();
        query.execute("SELECT Platos.P_Cod,Platos.P_nombre FROM Consumos,Se_Consume,Platos "
                + "WHERE Se_consume.P_cod=Platos.P_Cod AND Se_Consume.C_cod=Consumos.C_cod AND C_Fecha BETWEEN '"+x+"'"+" AND '"+y+"'");
        ResultSet rs=query.getResultSet();
        return rs;
    }

// ITEM 7
public static ResultSet PlatoMasConsumidoXTipo() throws SQLException{
      conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PWD);
      query = conn.createStatement();
      query.execute("select idplato,p_tipo,cant"
              + " from(select p_tipo,max(cant) as maxcant"
              + " from(select se_consume.p_cod as plato, count(se_consume.p_cod) as cant"
              + " from se_consume,platos "
              + " where se_consume.p_cod=platos.p_cod group by(se_consume.p_cod))as foo, platos "
              + " where plato = p_cod"
              + " group by p_tipo) as maxxtipo,(select se_consume.p_cod as idplato, max(platos.p_tipo)as tipo2 ,count(se_consume.p_cod) as cant "
              + " from se_consume,platos where se_consume.p_cod=platos.p_cod group by(se_consume.p_cod))as consumoscan "
              + " where maxcant=cant and p_tipo=tipo2"); 
      result=query.getResultSet();
      return result;
  }
// ITEM 8    
public static ResultSet CantMozos() throws SQLException{
      conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PWD);
      query = conn.createStatement();
      query.execute("SELECT COUNT(Mo_Cod) FROM Mozos");
      result=query.getResultSet();
      return result;
  }
// ITEM 8    
public static ResultSet CantMesas() throws SQLException{
      conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PWD);
      query = conn.createStatement();
      query.execute("SELECT COUNT(Me_Cod) FROM Mesas");
      result=query.getResultSet();
      return result;
  }
// ITEM 8    
public static ResultSet CantPlatosPrincipales() throws SQLException{
      conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PWD);
      query = conn.createStatement();
      query.execute("SELECT COUNT(P_Cod) FROM Platos WHERE P_tipo='Plato Principal'");
      result=query.getResultSet();
      return result;
  }
// ITEM 8    
public static ResultSet CantPostres() throws SQLException{
      conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PWD);
      query = conn.createStatement();
      query.execute("SELECT COUNT(P_Cod) FROM Platos WHERE P_tipo='Postre'");
      result=query.getResultSet();
      return result;
  }
// ITEM 8    
public static ResultSet CantEntradas() throws SQLException{
      conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PWD);
      query = conn.createStatement();
      query.execute("SELECT COUNT(P_Cod) FROM Platos WHERE P_tipo='Entrada'");
      result=query.getResultSet();
      return result;
  }

// ITEM 9
public static ResultSet MozosMesasAlfabetico() throws SQLException{
      conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PWD);
      query = conn.createStatement();
      query.execute("SELECT Mo_NombreApellido,Count(Mo_Cod_Atiende) FROM Mozos,Mesas WHERE Mo_Cod=Mo_Cod_Atiende GROUP BY Mo_NombreApellido");
      result=query.getResultSet();
      return result;
    }
// ITEM 10    
public static ResultSet MozosSinMesas() throws SQLException{
      conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PWD);
      query = conn.createStatement();
      query.execute("SELECT Mo_Cod,Mo_NombreApellido FROM Mozos EXCEPT"
                 + " SELECT Mo_Cod,Mo_NombreApellido FROM Mozos,Mesas WHERE Mo_Cod=Mo_Cod_Atiende");
      result=query.getResultSet();
      return result;
    }
// ITEM 11    
public static ResultSet MaxMinAvgPrincipal() throws SQLException{
      conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PWD);
      query = conn.createStatement();
      query.execute("SELECT MAX(P_PrecioCosto),MIN(P_PrecioCosto),AVG(P_PrecioCosto) FROM Platos WHERE P_Tipo='Plato Principal'");
      result=query.getResultSet();
      return result;
    }
// ITEM 12    
public static ResultSet NombreDescPlatosNuncaConsumidos() throws SQLException{
      conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PWD);
      query = conn.createStatement();
      query.execute("SELECT P_Nombre,P_Descripcion FROM Platos EXCEPT"
     + " SELECT P_Nombre,P_Descripcion FROM Platos,Consumos,Se_Consume WHERE Platos.P_cod=Se_Consume.P_cod AND Consumos.C_cod=Se_Consume.C_cod");
      result=query.getResultSet();
      return result;
    }
// ITEM 13    
public static ResultSet TotalPlatosXMesa(int cod) throws SQLException{
        conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PWD);
        query =conn.createStatement();
        query.execute("SELECT Count(P_Cod) FROM Mesas,Consumos,Se_Consume "
                + "WHERE Me_Cod=Me_Cod_Realiza AND Consumos.C_Cod=Se_Consume.C_Cod AND me_cod="+cod);
        ResultSet rs=query.getResultSet();
        return rs;
    }
}
