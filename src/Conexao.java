import javax.swing.*;
import java.sql.*;

public class Conexao {
    public static void main(String[] args) {

        Menu();

    }

    public static void Insert(){

        String dbURL = "jdbc:mysql://localhost:3306/dbteste";
        String username = "root";
        String password = "123456";

        try(Connection con = DriverManager.getConnection(dbURL, username, password)) {
            String sql = "INSERT INTO Users (name, password, fullname, email) VALUES (?, ?, ?, ?)";

            PreparedStatement statement = con.prepareStatement(sql);
            statement.setString(1, JOptionPane.showInputDialog("Name"));
            statement.setString(2, JOptionPane.showInputDialog("Password"));
            statement.setString(3, JOptionPane.showInputDialog("Full name"));
            statement.setString(4, JOptionPane.showInputDialog("Email"));

            int inserido = statement.executeUpdate();
            if (inserido > 0) {
                JOptionPane.showMessageDialog(null, "Usuário  inserido com sucesso!");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void Select(){

        String dbURL = "jdbc:mysql://localhost:3306/dbteste";
        String username = "root";
        String password = "123456";

        try(Connection con = DriverManager.getConnection(dbURL, username, password)) {
            String sql = "SELECT * FROM users";

            Statement statement = con.createStatement();
            ResultSet result = statement.executeQuery(sql);

            int count = 0;

            while (result.next()){
                String name = result.getString(2);
                String pass = result.getString(3);
                String fullname = result.getString("fullname");
                String email = result.getString("email");

                String output = "user #%d: %s - %s - %s - %s";
                JOptionPane.showMessageDialog(null, String.format(output, ++count, name, pass, fullname, email));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void Update(){
        String dbURL = "jdbc:mysql://localhost:3306/dbteste";
        String username = "root";
        String password = "123456";

        try(Connection con = DriverManager.getConnection(dbURL, username, password)) {
            String sql = "UPDATE Users SET password=?, fullname=?, email=? WHERE name=?";

            PreparedStatement statement = con.prepareStatement(sql);
            statement.setString(1, JOptionPane.showInputDialog("Password"));
            statement.setString(2, JOptionPane.showInputDialog("Full name"));
            statement.setString(3, JOptionPane.showInputDialog("Email"));
            statement.setString(4, JOptionPane.showInputDialog("Name"));

            int rowsUpdated = statement.executeUpdate();
            if (rowsUpdated > 0) {
                JOptionPane.showMessageDialog(null, "Usuário atualizado com sucesso!");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void Delete(){
        String dbURL = "jdbc:mysql://localhost:3306/dbteste";
        String username = "root";
        String password = "123456";

        try(Connection con = DriverManager.getConnection(dbURL, username, password)) {
            String sql = "DELETE FROM Users WHERE name=?";

            PreparedStatement statement = con.prepareStatement(sql);
            statement.setString(1, JOptionPane.showInputDialog("Name"));

            int rowsDeleted = statement.executeUpdate();
            if (rowsDeleted > 0) {
                JOptionPane.showMessageDialog(null, "Usuário deletado com sucesso!");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void Menu() {
        int op = Integer.parseInt(JOptionPane.showInputDialog("1 -  Inserir " +
                "\n 2 - Buscar \n 3 - Auterar \n 4 - Excluir \n 5 - Sair"));
        switch (op) {
            case 1:
                Insert();
                break;
            case 2:
                Select();
                break;
            case 3:
                Update();
                break;
            case 4:
                Delete();
                break;
            case 5:
                int sair = JOptionPane.showConfirmDialog(null, "Deseja Sair?");
                if (sair > 0) Menu();
                break;
            default:
                JOptionPane.showMessageDialog(null, "Opção inválida");
                Menu();
                break;
        }
    }

}


