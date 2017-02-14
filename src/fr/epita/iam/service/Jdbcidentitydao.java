/**
 * 
 */
package fr.epita.iam.service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import fr.epita.iam.datamodel.Identity;

/**
 * @author arvind
 *
 */
public class Jdbcidentitydao {private Connection connection;

/**
 * @throws SQLException 
 * 
 */
public Jdbcidentitydao() throws SQLException {
	this.connection = DriverManager.getConnection("jdbc:derby://localhost:1527/Prem;create=true","Prem","Prem");
	System.out.println(connection.getSchema());
}


public void writeIdentity(Identity identity) throws SQLException {
	String insertStatement = "insert into IDENTITIES (IDENTITIES_DISPLAYNAME,IDENTITIES_EMAIL,IDENTITIES_BIRTHDATE) values(?, ?, ?)";
	PreparedStatement pstmt_insert = connection.prepareStatement(insertStatement);
	pstmt_insert.setString(1, identity.getDisplayName());
	pstmt_insert.setString(2, identity.getEmail());
	pstmt_insert.setString(3, identity.getDob());
	
	pstmt_insert.execute();

}
public void Changeidentity(Identity identity,String id) throws SQLException {
String updateStatement = "Update IDENTITIES set IDENTITIES_DISPLAYNAME=?, IDENTITIES_EMAIL=?, IDENTITIES_BIRTHDATE=? Where IDENTITIES_UID=? ";
PreparedStatement pstmt_update = connection.prepareStatement(updateStatement);
pstmt_update.setString(1, identity.getDisplayName());
pstmt_update.setString(2, identity.getEmail());
pstmt_update.setString(3, identity.getDob());
pstmt_update.setString(4, id);
pstmt_update.execute();
}
public void Disposeidentity(String id) throws SQLException {
String deleteStatement = "Delete from IDENTITIES where IDENTITIES_UID=?";
PreparedStatement pstmt_delete = connection.prepareStatement(deleteStatement);
pstmt_delete.setString(1, id);
pstmt_delete.execute();
}


 
public List<Identity> readAll() throws SQLException {
	List<Identity> identities = new ArrayList<Identity>();
	PreparedStatement pstmt_select = connection.prepareStatement("select * from IDENTITIES");
	ResultSet rs = pstmt_select.executeQuery();
	while (rs.next()) {
		String id = rs.getString("IDENTITIES_UID");
		String displayName = rs.getString("IDENTITIES_DISPLAYNAME");
		String email = rs.getString("IDENTITIES_EMAIL");
		String dob = rs.getString("IDENTITIES_BIRTHDATE");
		Identity identity = new Identity(id,displayName, email,dob);
		identities.add(identity);
	}
	return identities;

}

public void listIdentity(String id) throws SQLException {
	PreparedStatement pstmt_select = connection.prepareStatement("select * from IDENTITIES where IDENTITIES_UID=?");
	pstmt_select.setString(1, id);
	ResultSet rs = pstmt_select.executeQuery();
	while (rs.next()) {
		System.out.println("Display Name:"+rs.getString("IDENTITIES_DISPLAYNAME"));
		System.out.println("Identity Email:"+rs.getString("IDENTITIES_EMAIL"));
        System.out.println("Identity Birthdate:"+rs.getString("IDENTITIES_BIRTHDATE"));
		}
	}
}




