package model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public abstract class Person{
   private String name;

private void setName(String value) {
    this.name = value;
}
private String getName() {
    return this.name;
}
private String email;

private void setEmail(String value) {
    this.email = value;
}
@Id
private String getEmail() {
    return this.email;
}
private String password;

private void setPassword(String value) {
    this.password = value;
}
private String getPassword() {
    return this.password;
}
private String uiTheme;

private void setUiTheme(String value) {
    this.uiTheme = value;
}
private String getUiTheme() {
    return this.uiTheme;
}
private String bio;

private void setBio(String value) {
    this.bio = value;
}
private String getBio() {
    return this.bio;
}
}
