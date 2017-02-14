/**
 * 
 */
package fr.epita.iam.datamodel;

/**
 * @author arvind
 *
 */
public class Identity {

private String uid;
private String displayName;
private String email;
private String dob;

/**
 * @param uid
 * @param displayName
 * @param email
 * @param dob
 */
public Identity(String displayName, String email,String dob) {
	//this.uid = uid;
	this.displayName = displayName;
	this.email = email;
	this.dob=dob;
	
}

public Identity(String uid,String displayName, String email,String dob) {
	this.uid = uid;
	this.displayName = displayName;
	this.email = email;
	this.dob=dob;
	
}


/**
 * @return the dob
 */
public String getDob() {
	return dob;
}
/**
 * @param uid the dob to set
 */
public void setDob(String dob) {
	this.dob = dob;
}
/**
 * @return the uid
 */

public String getUid() {
	return uid;
}
/**
 * @param uid the uid to set
 */
public void setUid(String uid) {
	this.uid = uid;
}


/**
 * @return the displayName
 */
public String getDisplayName() {
	return displayName;
}
/**
 * @param displayName the displayName to set
 */
public void setDisplayName(String displayName) {
	this.displayName = displayName;
}
/**
 * @return the email
 */
public String getEmail() {
	return email;
}
/**
 * @param email the email to set
 */
public void setEmail(String email) {
	this.email = email;
}


/* (non-Javadoc)
 * @see java.lang.Object#toString()
 */
@Override
public String toString() {
	return "Identity [Uid=" + uid + ", displayName=" + displayName + ", email=" + email + " DOB=" +dob+"]";
}


}


